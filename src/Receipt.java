public class Receipt {

    public void printReceipt(String time, String price, boolean isFreeRide) {
        if (!isFreeRide) {
            System.out.println("Damn Fast Taxis\n" +
                    "---------------\n" +
                    "Time: " + time + " seconds.\n" +
                    "Price per second: 2.25 dollars.\n" +
                    "Total price: " + price + " dollars.\n");
        } else {
            System.out.println("Damn Fast Taxis\n" +
                    "---------------\n" +
                    "Time: " + time + " seconds.\n" +
                    "Price per second: 0 dollars.\n" +
                    "Total price: " + price + " dollars.\n");
        }
    }
}
