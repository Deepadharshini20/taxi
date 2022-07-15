import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException {
    TaxiDetails.getInstance().taxiList.add(new Taxi());
    TaxiDetails.getInstance().taxiList.add(new Taxi());
    TaxiDetails.getInstance().taxiList.add(new Taxi());
    TaxiDetails.getInstance().taxiList.add(new Taxi());
    homePage();
  }
  public static void homePage() throws IOException {
    while (true) {
      int n = 0;
      while (true) {
        System.out.println(" Welcome To Taxi Booking Application");
        System.out.println("1.Book taxi\n2.Display taxi\n3.Stop the Application");
        String input = in.readLine();
        try {
          n = Integer.parseInt(input);
          if (n > 3)
            System.out.println("Enter 1 to 3 to start the booking process....\n");
          else
            break;
        } catch (NumberFormatException e) {
          System.out.println("Enter only number!!!\n");
        }
      }
      switch(n){
        case 1:
          TaxiBooking.book();
          break;
        case 2:
          //DisplayTaxi.details();
          break;
        case 3:
          System.out.println("Bus application was stopped");
          System.exit(0);
      }
    }
  }
}
