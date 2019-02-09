import java.text.DecimalFormat;

public class Price {

    private String  tempTime;
    private double  totalTime;
    private DecimalFormat   costFormat = new DecimalFormat("#.00");
    private Time time =     new Time();

    public String calculatePrice(long start, long end, DecimalFormat decimalFormat) {
        tempTime = time.calculateTime(start, end, decimalFormat);
        for (int i = 0; i < tempTime.length(); i++) {
            if (tempTime.charAt(i) == ',') {
                tempTime = tempTime.substring(0, i) + "." + tempTime.substring(i + 1);
            }
        }
        totalTime = Double.parseDouble(tempTime);
        return costFormat.format(totalTime * 2.25);
    }
}