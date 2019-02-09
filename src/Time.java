import java.text.DecimalFormat;

public class Time {

    private double totalTime;

    public String calculateTime(long start, long end, long pauseTime, DecimalFormat decimalFormat) {

        totalTime = ((end - start) / 100) + pauseTime;
        return decimalFormat.format(totalTime*0.1);

    }

}
