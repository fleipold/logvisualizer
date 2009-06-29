package logvisualizer

import java.util.regex.Pattern
import java.text.DateFormat
import java.text.SimpleDateFormat

class RegExCategory implements Category {


  String label;
  Pattern regex;

  static DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
                                    //%d/%m/%y-%H:%M:%S

  def RegExCategory(label, regex) {
    this.label = label;
    this.regex = regex;
  }

  public boolean applies(String line) {
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
