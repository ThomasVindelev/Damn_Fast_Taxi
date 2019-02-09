import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Taxi taxi = new Taxi();
        boolean isEnd = false;
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        System.out.println("Welcome to Damn Fast Taxis");
        while(!isEnd) {

            Taxi chosenTaxi;

            System.out.println("(1) Start a taxi\n" +
                    "(2) Stop a taxi\n" +
                    "(3) Pause a ride\n" +
                    "(4) Ask for price\n" +
                    "(5) Free ride\n" +
                    "(6) Add taxi(s)\n" +
                    "Choose a <number> and hit “enter”:");

            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    if (taxi.getTaxiList().size()>=1) {
                        taxi.printTaxis();
                        Scanner startTaxiNumber = new Scanner(System.in);
                        int numberChoice = startTaxiNumber.nextInt();
                        chosenTaxi = taxi.chooseTaxi(numberChoice);
                        if (chosenTaxi.isInMotion()) {
                            System.out.println("Taxi is already driving.");
                        } else {
                            chosenTaxi.setStartTime();
                            chosenTaxi.setMotion(true);
                        }
                        break;
                    } else {
                        System.out.println("No taxies have been registered in the system.");
                        break;
                    }

                case "2":

                    if (taxi.getTaxiList().size()>=1) {

                        taxi.printTaxis();

                        Scanner endTaxiNumber = new Scanner(System.in);
                        int numberChoice = endTaxiNumber.nextInt();
                        chosenTaxi = taxi.chooseTaxi(numberChoice);

                        chosenTaxi.setEndTime();

                        if (chosenTaxi.isInMotion()) {
                            System.out.println("Ride duration " + decimalFormat.format(((chosenTaxi.getEndTime() - chosenTaxi.getStartTime()) / 100)*0.1) + " seconds.");
                            Price price = new Price();
                            String finalPrice = price.calculatePrice(chosenTaxi.getStartTime(), chosenTaxi.getEndTime(), decimalFormat);
                            System.out.println("Price: " + finalPrice + " dollars.");
                            chosenTaxi.setMotion(false);
                        } else {
                            System.out.println("This taxi has already been stopped.");
                        }
                        break;
                    } else {
                        System.out.println("No taxies have been registered in the system.");
                    }
                case "3":
                    break;
                case "4":

                    if (taxi.getTaxiList().size()>=1) {

                        taxi.printTaxis();

                        Scanner currentPriceTaxiNumber = new Scanner(System.in);
                        int numberChoice = currentPriceTaxiNumber.nextInt();
                        Taxi currentChosenTaxi = taxi.chooseTaxi(numberChoice);

                        currentChosenTaxi.setEndTime();
                        if (currentChosenTaxi.isInMotion()) {
                            Price priceNow = new Price();
                            String currentPrice = priceNow.calculatePrice(currentChosenTaxi.getStartTime(), currentChosenTaxi.getEndTime(), decimalFormat);
                            System.out.println("Price: " + currentPrice + " dollars.");
                        } else {
                            System.out.println("This taxi has already been stopped.");
                        }
                        break;
                    } else {
                        System.out.println("No taxies have been registered in the system.");
                        break;
                    }

                case "5":

                    break;
                case "6":
                    System.out.println("How many taxies would you like to add?");
                    Scanner taxaNumber = new Scanner(System.in);
                    int number = taxaNumber.nextInt();
                    taxi.addTaxi(number);
                    System.out.println(number + " " + "Taxies have been added!");
                    break;
                default:
                    isEnd = true;
                    break;
            }


        }

    }

}