import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * User: fleipold
 * Date: Jun 29, 2009
 * Time: 4:42:08 PM
 */
DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

class EventDef {
  double probability;
  String logLine;
}

def events = [
        new EventDef(probability: 0.001, logLine:"Event 1"),
        new EventDef(probability:0.003, logLine:"Event 2"),
        new EventDef(probability: 0.001, logLine:"ERROR"),
        new EventDef(probability: 0.05, logLine:"garbage")
];

int increment = 101000;
int steps = 10000;

long time=0;
for (int  i=0; i<steps; i++){
  time += increment;
  events.each{ EventDef event ->
    if (Math.random()<event.probability){
      println(df.format(new Date(time))+" "+ event.logLine);
    }
    
  }


}



