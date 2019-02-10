import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        Price price = new Price();
        boolean isEnd = false;
        int numberChoice;
        String numberOfTaxis;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        System.out.println("Welcome to Damn Fast Taxis");
        Scanner taxiNumber = new Scanner(System.in);
        while (!isEnd) {

            Taxi chosenTaxi;

            System.out.println("(1) Start a taxi\n" +
                    "(2) Stop a taxi\n" +
                    "(3) Pause a ride\n" +
                    "(4) Ask for price\n" +
                    "(5) Free ride\n" +
                    "(6) Add taxi(s)\n" +
                    "(0) Exit program\n" +
                    "Choose a <number> and hit “enter”:");

            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();

                        numberChoice = taxiNumber.nextInt();
                        chosenTaxi = taxi.chooseTaxi(numberChoice);
                        if (chosenTaxi.isInMotion()) {
                            System.out.println("Taxi is already driving.");
                        } else {
                            chosenTaxi.setStartTime();
                            chosenTaxi.setMotion(true);
                            chosenTaxi.unpauseTime(chosenTaxi);
                        }
                        break;
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                        break;
                    }

                case "2":

                    if (taxi.getTaxiList().size() >= 1) {

                        taxi.printTaxis();

                        numberChoice = taxiNumber.nextInt();
                        chosenTaxi = taxi.chooseTaxi(numberChoice);

                        chosenTaxi.setEndTime();

                        if (chosenTaxi.isInMotion() && !chosenTaxi.isFreeRide()) {
                            System.out.println("Damn Fast Taxis\n" +
                                    "---------------\n" +
                                    "Time: " + decimalFormat.format(((((chosenTaxi.getEndTime() - chosenTaxi.getStartTime())) / 100) + chosenTaxi.getPauseTime()) * 0.1) + " seconds." + "\n" +
                                    "Price per second: 2.25 dollars.\n" +
                                    "Total price: " + price.calculatePrice(chosenTaxi.getStartTime(), chosenTaxi.getEndTime(), chosenTaxi.getPauseTime(), decimalFormat) + " dollars." + "\n");
                            chosenTaxi.setMotion(false);
                            chosenTaxi.resetTaxi();
                        } else if ((chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) && !chosenTaxi.isFreeRide()) {
                            System.out.println("Damn Fast Taxis\n" +
                                    "---------------\n" +
                                    "Time: " + decimalFormat.format((chosenTaxi.getPauseTime() * 0.1)) + " seconds." + "\n" +
                                    "Price per second: 2.25 dollars.\n" +
                                    "Total price: " + price.calculatePrice(0, 0, chosenTaxi.getPauseTime(), decimalFormat) + " dollars." + "\n");
                            chosenTaxi.resetTaxi();
                        } else if (chosenTaxi.isFreeRide()) {
                            System.out.println("Damn Fast Taxis\n" +
                                    "---------------\n" +
                                    "Time: " + decimalFormat.format(((((chosenTaxi.getEndTime() - chosenTaxi.getStartTime())) / 100) + chosenTaxi.getPauseTime() ) * 0.1) + " seconds." + "\n" +
                                    "Price per second: 0 dollars.\n" +
                                    "Total price: 0 dollars.\n");
                            chosenTaxi.resetTaxi();
                        } else {
                            System.out.println("This taxi is already stopped.");
                        }
                        break;
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                case "3":

                    if (taxi.getTaxiList().size() >= 1) {

                        taxi.printTaxis();

                        numberChoice = taxiNumber.nextInt();

                        chosenTaxi = taxi.chooseTaxi(numberChoice);

                        if (!chosenTaxi.isPaused() && chosenTaxi.isInMotion()) {
                            chosenTaxi.setEndTime();
                            chosenTaxi.setPauseTime(chosenTaxi.getStartTime(), chosenTaxi.getEndTime(), chosenTaxi);
                        } else if (chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) {
                            System.out.println("Taxi is already paused.");
                        } else if (!chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) {
                            System.out.println("Taxi is not booked.");
                        }
                    }

                    break;
                case "4":

                    if (taxi.getTaxiList().size() >= 1) {

                        taxi.printTaxis();
                        numberChoice = taxiNumber.nextInt();
                        Taxi currentChosenTaxi = taxi.chooseTaxi(numberChoice);
                        currentChosenTaxi.setEndTime();

                        if (currentChosenTaxi.isInMotion()) {
                            String currentPrice = price.calculatePrice(currentChosenTaxi.getStartTime(), currentChosenTaxi.getEndTime(), currentChosenTaxi.getPauseTime(), decimalFormat);
                            System.out.println("Price: " + currentPrice + " dollars.");
                        } else if (!currentChosenTaxi.isInMotion() && currentChosenTaxi.isPaused()) {
                            String currentPrice = price.calculatePrice(0, 0, currentChosenTaxi.getPauseTime(), decimalFormat);
                            System.out.println("Price: " + currentPrice + " dollars.");
                        } else {
                            System.out.println("This taxi has already been stopped.");
                        }
                        break;
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                        break;
                    }

                case "5":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();

                        numberChoice = taxiNumber.nextInt();
                        Taxi currentChosenTaxi = taxi.chooseTaxi(numberChoice);

                        if (!currentChosenTaxi.isFreeRide()) {
                            currentChosenTaxi.setFreeRide(true);
                            currentChosenTaxi.setStartTime();
                            currentChosenTaxi.setMotion(true);
                            currentChosenTaxi.unpauseTime(currentChosenTaxi);

                        } else {
                            System.out.println("This taxi is already driving for free");
                        }
                    }
                    break;
                case "6":
                    System.out.println("How many taxis would you like to add?");
                    numberOfTaxis = taxiNumber.nextLine();
                    if (numberOfTaxis.matches("[0-50]+")) {
                        numberChoice = Integer.parseInt(numberOfTaxis);
                        taxi.addTaxi(numberChoice);
                        System.out.println(numberOfTaxis + " taxis have been added!\n");
                    } else {
                        System.out.println("Not a valid request.");
                    }
                    break;
                case "0":
                    System.out.println("Program shutting down...");
                    isEnd = true;
                    break;
                default:
                    System.out.println("Not a valid request.");
                    break;
            }
        }
    }
}