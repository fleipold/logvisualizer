import logvisualizer.RegExEvent
import logvisualizer.Event
import logvisualizer.TimeLineVisualizer
import logvisualizer.HistogramVisualizer
import logvisualizer.RegExEvent




Event EVENT1 = new RegExEvent("Event 1", ~/Event 1/)
Event EVENT2 = new RegExEvent("Event 2", ~/Event 2/)
Event ERROR = new RegExEvent("Error", ~/ERROR/)


def logFile = new File("test.log");

logFile.withInputStream {InputStream stream ->
  def visualizer = new TimeLineVisualizer([
          EVENT1,
          EVENT2,
          ERROR
  ]);
  visualizer.visualize(stream)
}


logFile.withInputStream {InputStream stream ->
  def visualizer = new HistogramVisualizer(EVENT2, HistogramVisualizer.HOUR_OF_DAY_BINS)
  visualizer.visualize(stream)
}