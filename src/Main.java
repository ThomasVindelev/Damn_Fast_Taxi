import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // Vores Main-klasse fungerer som en "hub" eller menu for alle funktionerne i applikationen.


        Taxi taxi =                         new Taxi();
        Price price =                       new Price();
        Receipt receipt =                   new Receipt();
        DecimalFormat decimalFormat =       new DecimalFormat("#.0");
        Scanner taxiNumber =                new Scanner(System.in);

        boolean isEnd = false;
        String tempNumber;
        int numberChoice;

        System.out.println("Welcome to Damn Fast Taxis");

        while (!isEnd) {
            Taxi chosenTaxi;
            System.out.println(
                    "(1) Start a taxi\n" +
                    "(2) Stop a taxi\n" +
                    "(3) Pause a ride\n" +
                    "(4) Ask for price\n" +
                    "(5) Free ride\n" +
                    "(6) Add taxi(s)\n" +
                    "(0) Exit program\n" +
                    "Choose a <number> and hit “enter”:"
            );

            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            // Vi har valgt at lave switch-cases med strings i stedet for integers, da man ved tastefejl kan forårsage et crash.

            switch (choice) {
                case "1":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();
                        tempNumber = taxiNumber.nextLine();

                        // "matches" funktionen skal bruge en String. Derefter omdanner vi den til en int, så den kan bruges til at sammenligne med.

                        if (tempNumber.matches("[0-9]+") && Integer.parseInt(tempNumber) <= taxi.getTaxiList().size()) {
                            numberChoice = Integer.parseInt(tempNumber);
                            chosenTaxi = taxi.chooseTaxi(numberChoice);
                            if (!chosenTaxi.isInMotion()) {
                                chosenTaxi.setStartTime();
                                chosenTaxi.unpauseTime(chosenTaxi);
                            } else {
                                System.out.println("Taxi is already driving.");
                            }
                        } else {
                            System.out.println("Not a valid request.");
                        }
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                    break;

                case "2":

                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();
                        tempNumber = taxiNumber.nextLine();
                        if (tempNumber.matches("[0-9]+") && Integer.parseInt(tempNumber) <= taxi.getTaxiList().size()) {
                            numberChoice = Integer.parseInt(tempNumber);
                            chosenTaxi = taxi.chooseTaxi(numberChoice);
                            chosenTaxi.setEndTime();
                            if (chosenTaxi.isInMotion() && !chosenTaxi.isFreeRide()) {
                                receipt.printReceipt(decimalFormat.format((((chosenTaxi.getEndTime() - chosenTaxi.getStartTime()) / 100) + chosenTaxi.getPauseTime()) * 0.1), price.calculatePrice(chosenTaxi.getStartTime(), chosenTaxi.getEndTime(), chosenTaxi.getPauseTime(), decimalFormat), chosenTaxi.isFreeRide());
                                chosenTaxi.resetTaxi();
                            } else if ((chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) && !chosenTaxi.isFreeRide()) {
                                receipt.printReceipt(decimalFormat.format((chosenTaxi.getPauseTime() * 0.1)), price.calculatePrice(0, 0, chosenTaxi.getPauseTime(), decimalFormat), chosenTaxi.isFreeRide());
                                chosenTaxi.resetTaxi();
                            } else if (chosenTaxi.isFreeRide() && !chosenTaxi.isInMotion()) {
                                receipt.printReceipt(decimalFormat.format(chosenTaxi.getPauseTime() * 0.1), "0", chosenTaxi.isFreeRide());
                                chosenTaxi.resetTaxi();
                            } else if (chosenTaxi.isFreeRide() && chosenTaxi.isInMotion()) {
                                receipt.printReceipt(decimalFormat.format((((chosenTaxi.getEndTime() - chosenTaxi.getStartTime()) / 100) + chosenTaxi.getPauseTime() ) * 0.1), "0", chosenTaxi.isFreeRide());
                                chosenTaxi.resetTaxi();
                            } else {
                                System.out.println("This taxi is already stopped.");
                            }
                        } else {
                            System.out.println("Not a valid request.");
                        }
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                    break;

                case "3":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();
                        tempNumber = taxiNumber.nextLine();
                        if (tempNumber.matches("[0-9]+") && Integer.parseInt(tempNumber) <= taxi.getTaxiList().size()) {
                            numberChoice = Integer.parseInt(tempNumber);
                            chosenTaxi = taxi.chooseTaxi(numberChoice);
                            if (!chosenTaxi.isPaused() && chosenTaxi.isInMotion()) {
                                chosenTaxi.setEndTime();
                                chosenTaxi.setPauseTime(chosenTaxi.getStartTime(), chosenTaxi.getEndTime(), chosenTaxi);
                            } else if (chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) {
                                System.out.println("Taxi is already paused.");
                            } else if (!chosenTaxi.isPaused() && !chosenTaxi.isInMotion()) {
                                System.out.println("Taxi is not booked.");
                            }
                        } else {
                            System.out.println("Not a valid request.");
                        }
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                    break;

                case "4":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();
                        tempNumber = taxiNumber.nextLine();
                        if (tempNumber.matches("[0-9]+") && Integer.parseInt(tempNumber) <= taxi.getTaxiList().size()) {
                            numberChoice = Integer.parseInt(tempNumber);
                            Taxi currentChosenTaxi = taxi.chooseTaxi(numberChoice);
                            currentChosenTaxi.setEndTime();
                            if (!currentChosenTaxi.isFreeRide() && currentChosenTaxi.isInMotion()) {
                                String currentPrice = price.calculatePrice(currentChosenTaxi.getStartTime(), currentChosenTaxi.getEndTime(), currentChosenTaxi.getPauseTime(), decimalFormat);
                                System.out.println("Price: " + currentPrice + " dollars.");
                            } else if (!currentChosenTaxi.isFreeRide() && !currentChosenTaxi.isInMotion() && currentChosenTaxi.isPaused()) {
                                String currentPrice = price.calculatePrice(0, 0, currentChosenTaxi.getPauseTime(), decimalFormat);
                                System.out.println("Price: " + currentPrice + " dollars.");
                            } else if (currentChosenTaxi.isFreeRide()) {
                                System.out.println("This taxi is driving for free");
                            } else {
                                System.out.println("This taxi has already been stopped.");
                            }
                        } else {
                            System.out.println("Not a valid request.");
                        }
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                    break;

                case "5":
                    if (taxi.getTaxiList().size() >= 1) {
                        taxi.printTaxis();
                        tempNumber = taxiNumber.nextLine();
                        if (tempNumber.matches("[0-9]+") && Integer.parseInt(tempNumber) <= taxi.getTaxiList().size()) {
                            numberChoice = Integer.parseInt(tempNumber);
                            Taxi currentChosenTaxi = taxi.chooseTaxi(numberChoice);
                            if (!currentChosenTaxi.isFreeRide() && !currentChosenTaxi.isInMotion()) {
                                currentChosenTaxi.setFreeRide(true);
                                currentChosenTaxi.setStartTime();
                                currentChosenTaxi.unpauseTime(currentChosenTaxi);
                            } else if (!currentChosenTaxi.isFreeRide() && currentChosenTaxi.isInMotion()) {
                                currentChosenTaxi.setFreeRide(true);
                            } else if (currentChosenTaxi.isFreeRide() && (!currentChosenTaxi.isInMotion() || currentChosenTaxi.isPaused())) {
                                currentChosenTaxi.setStartTime();
                                currentChosenTaxi.unpauseTime(currentChosenTaxi);
                            } else {
                                System.out.println("This taxi is already driving for free");
                            }
                        } else {
                            System.out.println("Not a valid request.");
                        }
                    } else {
                        System.out.println("No taxis have been registered in the system.");
                    }
                    break;

                case "6":
                    System.out.println("Enter how many taxis [1-50] you would like to add: ");
                    String numberOfTaxis = taxiNumber.nextLine();
                    if (numberOfTaxis.matches("[0-9]+")) {
                        numberChoice = Integer.parseInt(numberOfTaxis);
                        if (numberChoice > 0 && numberChoice <= 50) {
                            taxi.addTaxi(numberChoice);
                            String taxiText = " taxis";
                            if (numberChoice == 1) {
                                taxiText = " taxi";
                            }
                            System.out.println(numberOfTaxis + taxiText + " have been added!");
                        } else {
                            System.out.println("Not a valid request.");
                        }
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