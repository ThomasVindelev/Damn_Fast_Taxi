import java.util.ArrayList;
import java.util.List;

public class Taxi {

    private long startTime;
    private long endTime;
    private long tempTime;
    private long pauseTime;
    private boolean isFreeRide = false;
    private boolean inMotion = false;
    private boolean isPaused = false;
    private List<Taxi> taxiList = new ArrayList<>();

    public void addTaxi(int count) {
        for (int i = 0; i < count; i++) {
            Taxi taxi = new Taxi();
            taxiList.add(taxi);
        }
    }

    public void setPauseTime(long startTime, long endTime, Taxi taxi) {
        tempTime = (endTime - startTime) / 100;
        pauseTime += tempTime;
        taxi.pauseTime = pauseTime;
        taxi.setMotion(false);
        taxi.isPaused = true;
    }

    public void unpauseTime(Taxi taxi) {
        taxi.setStartTime();
        taxi.isPaused = false;
    }

    public void printTaxis() {
        for(int i = 1; i < taxiList.size() + 1; i++) {

            if (taxiList.get(i-1).isInMotion() && taxiList.get(i-1).getStartTime() > 0 && !taxiList.get(i-1).isFreeRide()) {
                System.out.println("Taxi: " + i + " is driving.");
            } else if (taxiList.get(i-1).isPaused && taxiList.get(i-1).getStartTime() > 0 && (!taxiList.get(i-1).isFreeRide() || taxiList.get(i-1).isFreeRide())) {
                System.out.println("Taxi: " + i + " is paused");
            } else if (taxiList.get(i-1).isFreeRide()) {
                System.out.println("Taxi: " + i + " is on a free ride");
            } else {
                System.out.println("Taxi: " + i);
            }
        }
    }

    public void resetTaxi() {
        startTime = 0;
        endTime = 0;
        pauseTime = 0;
        inMotion = false;
        isPaused = false;
        isFreeRide = false;
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

    public boolean isPaused() {
        return isPaused;
    }

    public long getTempTime() {
        return tempTime;
    }

    public long getPauseTime() {
        return pauseTime;
    }

    public boolean isFreeRide() {
        return isFreeRide;
    }

    public void setFreeRide(boolean freeRide) {
        isFreeRide = freeRide;
    }
}
