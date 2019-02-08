import java.text.DecimalFormat;
import java.util.ArrayList;

public class Taxi {

    ArrayList<Taxi> taxiArrayList = new ArrayList<Taxi>();
    DecimalFormat decimalFormat = new DecimalFormat("#.0");
    Time time = new Time();
    private double totalPrice;
    private String tempTime;
    private double totalTime;
    private long startTime;
    private long endTime;

    public Taxi() {

    }

    public Taxi(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addTaxi(Taxi taxi) {
        taxiArrayList.add(taxi);
    }

    public long startTime() {
        return time.getTime();
    }

    public long endTime() {
        return time.getTime();
    }

    public double getPrice(long start, long end) {
        tempTime = time.calculateTime(start, end);
        for (int i = 0; i < tempTime.length(); i++) {
            if (tempTime.charAt(i) == ',') {
                tempTime = tempTime.substring(0, i) + "." + tempTime.substring(i+1);
            }
        }
        totalTime = Double.parseDouble(tempTime);
        return totalPrice = totalTime*2.25;
    }
}