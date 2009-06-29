import logvisualizer.RegExCategory
import logvisualizer.Category
import logvisualizer.TimeLineVisualizer
import logvisualizer.HistogramVisualizer




RegExCategory REQUEST = new RegExCategory("Request", ~/FINISHED 'Persist application form'/)

def categories = [
        new RegExCategory("Error", ~/ERROR 1026/),
        REQUEST,
        new RegExCategory("Success", ~/FINISHED 'AUTOMATED_ACCOUNT_CREATION'/)

]

/*new TimeLineVisualizer(System.in)
        .visualize()
  */



new HistogramVisualizer(REQUEST, HistogramVisualizer.HOUR_OF_DAY_BINS).visualize(System.in)
