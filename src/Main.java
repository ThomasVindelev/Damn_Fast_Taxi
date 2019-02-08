import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean isOver = false;

        Taxi taxi = new Taxi();

        while (!isOver) {

            System.out.println("Welcome to Damn Fast Taxis\n" +
                    "(1) Start a taxi\n" +
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

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":
                    System.out.print("How many taxis do you want to add? Max 50 per addition: ");
                    Scanner countScanner = new Scanner(System.in);
                    int count = countScanner.nextInt();
                    if (count > 0 && count < 50) {
                        for (int i = 0; i < count; i++) {
                            taxi.addTaxi(taxi);
                        }
                        System.out.println(count + " taxis added!");
                    } else {
                        System.out.println("Not a valid amount.");
                    }

                    break;
                case "0":

                    break;
            }
        }


    }
}