import java.util.ArrayList;
import java.util.List;

public class Taxi {

    private long startTime;
    private long endTime;
    private boolean inMotion = false;
    private List<Taxi> taxiList = new ArrayList<>();

    public void addTaxi(int count) {
        for (int i = 0; i < count; i++) {
            Taxi taxi = new Taxi();
            taxiList.add(taxi);
        }
    }

    public void printTaxis() {
        for(int i = 1; i < taxiList.size() + 1; i++) {

            if (taxiList.get(i-1).isInMotion() && taxiList.get(i-1).getStartTime() > 0) {
                System.out.println("Taxi: " + i + " is driving.");
            } else {
                System.out.println("Taxi: " + i);
            }
        }
    }

    public Taxi chooseTaxi(int choice) {
        return taxiList.get(choice - 1);
    }

    public List<Taxi> getTaxiList() {
        return taxiList;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isInMotion() {
        return inMotion;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void setEndTime() {
        this.endTime = System.currentTimeMillis();
    }

    public void setMotion(boolean stopped) {
        inMotion = stopped;
    }
}
