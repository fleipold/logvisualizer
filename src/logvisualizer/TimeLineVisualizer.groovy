package logvisualizer

public class TimeLineVisualizer {
  def categories;


  def filename(int index) {
    return "data-" + index + ".dat"
  }



  def TimeLineVisualizer(categories) {
    this.categories = categories;


  }

  def visualize( InputStream input ) {
    filterLogData(input);
    createPlotFile();

    "gnuplot plot.plt".execute();
  }

  private def createPlotFile() {
    def start = -0.5;
    def end = categories.size() - 0.5

    def script = "set yrange [$start:$end]\n";
    int index = 0;
    script += "set ytics (" + categories.collect {category -> '"' + category.getLabel() + '" ' + index++ }.join(",") + ")\n"

    script += """
set xdata time
set timefmt "%d/%m/%y-%H:%M:%S"
""";



    index = 0;
    script += "plot " + categories.collect({category -> "\"${filename(index++)}\" using 1:2 notitle" }).join(",") + "\n"


    new File("plot.plt").withWriter {out ->
      out.write(script);
    }
  }

  private def filterLogData( InputStream input ) {
    def outfiles = new Writer[categories.size()];
    categories.eachWithIndex({cat, index ->
      outfiles[index] = new FileWriter(filename(index));
    })



    input.eachLine {line ->
      categories.eachWithIndex({Category cat, index ->
        if (cat.applies(line)) {
          outfiles[index].write("${cat.getTimesStamp(line)} ${index}\n");
        }
      })

    }

    categories.eachWithIndex({cat, index ->
      outfiles[index].close()
    })
  }
}
