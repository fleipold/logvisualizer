package logvisualizer

import java.util.regex.Pattern
import java.text.DateFormat
import java.text.SimpleDateFormat

class RegExEvent implements Event {

  String label;
  Pattern regex;

  static DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
                                    //%d/%m/%y-%H:%M:%S

  def RegExEvent(String label, Pattern regex) {
    this.label = label;
    this.regex = regex;
  }

  public boolean appliesTo(String line) {
    return regex.matcher(line).find();
  }


  public String getTimesStamp(String line) {
    String[] fields = line.split("\\s")
    return fields[0] + '-' + fields[1];



  }

  public Date parseTimeStamp(String line) {
    return df.parse(getTimesStamp(line))

  }
}
