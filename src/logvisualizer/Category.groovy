package logvisualizer

public interface Category {
  String getLabel()

  boolean applies(String line)

  String getTimesStamp(String line)

  Date parseTimeStamp(String line)
}