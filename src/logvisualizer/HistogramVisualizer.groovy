package logvisualizer
/**
 * User: fleipold
 * Date: Jun 29, 2009
 * Time: 3:08:05 PM
 */

interface TimeBin extends Comparable{
  boolean isInBin(Date date);
  String getLabel();
}

class HourOfDayBin implements TimeBin {
  final int hour;


  def HourOfDayBin(hour) {
    this.hour = hour;
  }

  public String getLabel() {
    return ""+hour;
  }

  public boolean isInBin(Date date) {
    if (date == null){
      return false;
    }
    return date.getHours().equals(hour);
  }

  public int compareTo(Object o) {
    return hour.compareTo(((HourOfDayBin)o).hour);
  }
}


public class HistogramVisualizer {


  logvisualizer.Event category
  java.util.List timebins

  static List HOUR_OF_DAY_BINS = (0..23).collect({hour -> return new HourOfDayBin(hour) });
 

  // currently we only support a single category...
  def HistogramVisualizer(Event category, List timebins) {
    this.category = category;
    this.timebins = timebins;

  }

  def visualize(InputStream input){
    Map counters = new HashMap();
    timebins.each({TimeBin bin -> counters.put(bin, 0)});
    int sum = 0;

    input.eachLine {String line ->

      if (category.appliesTo(line)){
        sum++;
        timebins.each {
         TimeBin bin ->
            if(bin.isInBin(category.parseTimeStamp(line))){
              counters[bin]=counters[bin]+1
            }
        }
      }


    }
    new File("histogram.dat").withWriter { Writer writer ->
      timebins.sort().each{
        TimeBin bin->
          writer.write("${counters[bin]} ${bin.getLabel()} \n")
      }


    }

    new File("histogram.plt").withWriter{Writer writer ->
      writer.write("""
set style data histogram
plot "histogram.dat" using 1:xtic(2)
       """)
    }

    "gnuplot histogram.plt".execute()

  }

}

