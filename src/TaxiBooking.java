import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

abstract class CustomerTravelDetails {
  static boolean isTrue = true;

  public char getLocation() throws IOException {
    char location = '\0';
    while (isTrue) {
      System.out.println("Enter A to F only!!");
      String input = App.in.readLine();
      if (!input.equals(""))
        location = input.toUpperCase().charAt(0);
      if ((location == 'A' || location == 'B' || location == 'C' || location == 'D' || location == 'E'
          || location == 'F')
          && !input.equals(""))
        break;
      else
        System.out.println("enter only A to F only");
    }
    return location;
  }

  public int getTime() throws IOException {
    int time = 0;
    while (isTrue) {
      String input = App.in.readLine();
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
    char pickupPoint = '\0';
    char dropPoint = '\0';
    int pickupTime = 0;

    while (true) {
      String input = "";
      char yesOrNo = '\0';
      while (isTrue) {
        System.out.println("Do u want to book a taxi?\nEnter y or n: ");
        input = App.in.readLine();
        if (!input.equals(""))
          yesOrNo = input.toUpperCase().charAt(0);
        if ((yesOrNo == 'F' || yesOrNo == 'M') && !input.equals(""))
          break;
        else
          System.out.println("enter only f or m");
      }
      if (yesOrNo == 'Y') {
        id += 1;
        pickupPoint = getPickupPoint();
        dropPoint = getDropPoint();
        pickupTime = obj.getTime();
        getAvailableTaxi(pickupPoint, dropPoint, pickupTime);
      } else
        break;
    }
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

  public static void getAvailableTaxi(char pickupPoint, char dropPoint, int pickupTime) { 
    char nearestPoint = pickupPoint;
    int convertedPoint = (int)pickupPoint-64;
    int availabeCount = getAvailableTaxiCount(pickupPoint).size();
    boolean check = true;
    int i = 1;

    while(check){ 
      if(availabeCount==0){
        if((convertedPoint-i)<=0)
          continue;
        if((convertedPoint-i)>0){
          nearestPoint = (char)(convertedPoint-i);
          availabeCount = getAvailableTaxiCount(nearestPoint).size();
          if(availabeCount==0){
            if((convertedPoint+i)<7){
              nearestPoint = (char)(convertedPoint+i);
              availabeCount = getAvailableTaxiCount(nearestPoint).size();
              if(availabeCount==0)
                i++;
            } 
          }
        
      }
      else  
        break;
    }
  }

  public static ArrayList<Taxi> getAvailableTaxiCount(char pickupPoint) {
    ArrayList<Taxi> taxiList = TaxiDetails.getInstance().taxiList;
    ArrayList<Taxi> availableTaxi = new ArrayList<>();
    for (Taxi taxi : taxiList) {
      if (taxi.avaiablePoint == pickupPoint)
        availableTaxi.add(taxi);
    }
    Collections.sort(availableTaxi, new Comparator<Taxi>() {
      public int compare(Taxi e1, Taxi e2) {
        return Integer.valueOf(e1.getEarned()).compareTo(Integer.valueOf(e2.getEarned()));
      }
    });
    return availableTaxi;
  }
}
