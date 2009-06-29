package logvisualizer

public interface Event {

  String getLabel()

  boolean appliesTo(String line)

  String getTimesStamp(String line)

  Date parseTimeStamp(String line)
}