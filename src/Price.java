import java.text.DecimalFormat;

public class Price {

    private String          tempTime;
    private double          totalTime;
    private DecimalFormat   costFormat = new DecimalFormat("#.00");
    private Time time =     new Time();

    // I metoden herunder får vi en pris tilbage som en String. Da vi kun kan regne med punktummer og ikke kommaer, skal vi derfor udskifte dette.

    public String calculatePrice(long start, long end, long pauseTime, DecimalFormat decimalFormat) {
        tempTime = time.calculateTime(start, end, pauseTime, decimalFormat);
        for (int i = 0; i < tempTime.length(); i++) {
            if (tempTime.charAt(i) == ',') {
                tempTime = tempTime.substring(0, i) + "." + tempTime.substring(i + 1);
            }
        }
        totalTime = Double.parseDouble(tempTime);
        return costFormat.format(totalTime * 2.25);
    }
}