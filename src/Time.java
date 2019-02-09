import java.text.DecimalFormat;

public class Time {

    private double totalTime;

    public String calculateTime(long start, long end, DecimalFormat decimalFormat) {

        totalTime = (end - start) / 100;
        return decimalFormat.format(totalTime*0.1);

    }

}
