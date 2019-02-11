public class Receipt {

    public void printReceipt(String time, String price, boolean isFreeRide) {
        double pricePerSecond;
        if (!isFreeRide) {
            pricePerSecond = 0;
        } else {
            pricePerSecond = 2.25;
        }
            System.out.println("Damn Fast Taxis\n" +
                    "---------------\n" +
                    "Time: " + time + " seconds.\n" +
                    "Price per second: " + pricePerSecond + " dollars.\n" +
                    "Total price: " + price + " dollars.\n");
    }
}
