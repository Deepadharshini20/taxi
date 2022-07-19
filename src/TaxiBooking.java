import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

abstract class CustomerTravelDetails extends Thread{
  static boolean isTrue = true;

  public char getLocation() throws IOException {
    char location = '\0';
    while (isTrue) {
      System.out.println("Enter A to F..");
      String input = TaxiBookApp.in.readLine();
      if (!input.equals(""))
        location = input.toUpperCase().charAt(0);
      if ((location>='A' && location<='F')
          && !input.equals(""))
        break;
      else
        System.out.println("Enter only A to F!!");
    }
    return location;
  }

  public int getTime() throws IOException {
    int time = 0;
    while (isTrue) {
      System.out.println("Enter pickup time: ");
      String input = TaxiBookApp.in.readLine();
      try {
        time = Integer.parseInt(input);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Enter only number!!!\n");
      }
    }
    return time;
  }

  abstract void print(String inputPoint);
}

class TaxiBooking extends CustomerTravelDetails {
  static TaxiBooking obj = new TaxiBooking();
  static int id = 0;

  public static void book() throws IOException {
    id += 1;
    char pickupPoint = getPickupPoint();
    char dropPoint = getDropPoint();
    int pickupTime = obj.getTime();
    bookTaxi(pickupPoint, dropPoint, pickupTime);
  }

  public static char getPickupPoint() throws IOException {
    obj.print("Pickup Point");
    return obj.getLocation();
  }

  public static char getDropPoint() throws IOException {
    obj.print("Drop Point");
    return obj.getLocation();
  }

  @Override
  void print(String inputPoint) {
    System.out.println("Enter " + inputPoint + " location");
  }

  public static void bookTaxi(char pickupPoint, char dropPoint, int pickupTime) {
    char nearestPoint = pickupPoint;
    int convertedPoint = (int) pickupPoint - 64;
    ArrayList<Taxi> availableTaxi = getAvailableTaxi(pickupPoint);
    ArrayList<Taxi> availableTaxileft = new ArrayList<>();
    int availabeCount = availableTaxi.size();
    boolean check = true;
    int i = 1;
    int rej  = 1;
    for(Taxi t:TaxiDetails.getInstance().taxiList){
      if(t.isFree)
        rej = 0;
    }
    if(rej == 1){
      System.out.println("sorry No taxi is Available Now :-( ");
      System.out.println("sorry your booking is rejected ");
      return;
    }
    while (check) {
      if (availabeCount == 0) {
        if ((convertedPoint - i) < 1)
          continue;
        if ((convertedPoint - i) > 0) {
          nearestPoint = (char) (pickupPoint - i);
          availableTaxileft = getAvailableTaxi(nearestPoint);
          if ((convertedPoint + i) < 7) {
            nearestPoint = (char) (pickupPoint + i);
            availableTaxi = getAvailableTaxi(nearestPoint);
            availableTaxi.addAll(availableTaxileft);
            availabeCount = availableTaxi.size();
            if (availabeCount >0){
              System.out.println("Taxi can be allotted");
              bookingProcess(availableTaxi, pickupPoint, dropPoint, pickupTime);
              check = false;
              break;
            }
          } 
          if(availableTaxileft.size()>0){
            System.out.println("Taxi can be allotted");
            bookingProcess(availableTaxileft, pickupPoint, dropPoint, pickupTime);
            check = false;
            break;
          }
          if((availabeCount==0)||(convertedPoint+i)>6){
            i++;
            continue;
          }
            
          if((convertedPoint+i)>6 && (convertedPoint-i)<1)
            return;
        }
      } 
      else {
        System.out.println("Taxi can be allotted");
        bookingProcess(availableTaxi, pickupPoint, dropPoint, pickupTime);
        break;
      }
    }
    
  }

  public static ArrayList<Taxi> getAvailableTaxi(char pickupPoint) {
    ArrayList<Taxi> availableTaxi = new ArrayList<>();
    for (Taxi taxi : TaxiDetails.getInstance().taxiList) {
      if (taxi.currentPoint == pickupPoint && taxi.isFree)
        availableTaxi.add(taxi);
    }
    Collections.sort(availableTaxi, new Comparator<Taxi>() {
      public int compare(Taxi e1, Taxi e2) {
        return Integer.valueOf(e1.getEarned()).compareTo(Integer.valueOf(e2.getEarned()));
      }
    });
    return availableTaxi;
  }

  public static void bookingProcess(ArrayList<Taxi> taxiList, char pickupPoint, char dropPoint, int pickupTime) {   
    int distance = Math.abs((int) pickupPoint - (int) dropPoint);
    int totalCharge = ((distance * 15) - 5) * 10 + 100;
    int taxiNumber = taxiList.get(0).gettaxiNumber();
    System.out.println("Amount to pay: " + totalCharge);

    for (Taxi taxi : TaxiDetails.getInstance().taxiList) {
      if (taxi.gettaxiNumber() == taxiNumber) {
        taxi.details.add(new BookedTaxiDetails(pickupPoint, dropPoint, pickupTime, pickupTime + distance, totalCharge));
        taxi.earned += totalCharge;
        taxiHold th = new taxiHold(taxi, distance,dropPoint);
        th.start();
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }
}
