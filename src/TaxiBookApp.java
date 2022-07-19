import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TaxiBookApp {
  static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  TaxiBookApp(){
    for(int i=0;i<4;i++){
      TaxiDetails.getInstance().taxiList.add(new Taxi());
    }
  }
  public void homePage() throws IOException {
    while (true) {
      int n = 0;
      while (true) {
        System.out.println(" Welcome To Taxi Booking Application");
        System.out.println("1.Book taxi\n2.Taxi Details\n3.Taxi Status\n4.Stop the Application");
        String input = in.readLine();
        try {
          n = Integer.parseInt(input);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Enter only number!!!\n");
        }
      }
      switch(n){
        case 1:
          TaxiBooking.book();
          homePage();
          break;
        case 2:
          DisplayTaxi.display();
          homePage();
          break;
        case 3:
          DisplayTaxi.taxiStatus();
          homePage();
        case 4:
          System.exit(0);;
        default:
          System.out.println("Enter valid choice");
          homePage();
          break;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    TaxiBookApp tb = new TaxiBookApp(); 
    tb.homePage();
  }
}
