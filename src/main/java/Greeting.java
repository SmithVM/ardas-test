import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Dmitry Natalenko on 15.11.2015.
 */
public class Greeting {

    public static void main(String[] args) throws IOException {
        int timeOfDay = getTimeOfDay();
        System.out.println(greeting(timeOfDay));
    }

    public static String greeting(int timeOfDay){

        String result = "";

        try {
            Locale current = Locale.getDefault();
            ResourceBundle bundle = ResourceBundle.getBundle("message", current);

            String morning = bundle.getString("morning");
            String day = bundle.getString("day");
            String evening = bundle.getString("evening");
            String night = bundle.getString("night");

            if(timeOfDay >= 6 && timeOfDay < 9){
                result = morning;
            }else if(timeOfDay >= 9 && timeOfDay < 19){
                result = day;
            }else if(timeOfDay >= 19 && timeOfDay < 23){
                result = evening;
            }else if(timeOfDay >= 23 || timeOfDay < 6){
                result = night;
            }

            // throw new ArithmeticException();

        } catch (Exception e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Exception in greeting method", e);
        }

        return result;
    }

    public static int getTimeOfDay(){
        LocalDateTime currentTime = new LocalDateTime();
        return currentTime.getHourOfDay();
    }
}
