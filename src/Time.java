import java.text.DecimalFormat;

public class Time {

    private double tempTime;
    private double totalTime;
    private DecimalFormat decimalFormat = new DecimalFormat("#.0");

    public long getTime() {
        return System.currentTimeMillis();
    }

    public String calculateTime(long start, long end) {

        tempTime = end - start / 100;
        totalTime = tempTime / 10;
        return decimalFormat.format(totalTime);

    }

}
