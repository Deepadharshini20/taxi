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
    ArrayList<Taxi> taxiList = TaxiDetails.getInstance().taxiList;
    ArrayList<Taxi> availableTaxi;
    char tempPoint = pickupPoint;
    while(true){
      availableTaxi = new ArrayList<>();

      for (Taxi taxi : taxiList) {
        if (taxi.avaiablePoint == tempPoint)
          availableTaxi.add(taxi);
      }
      if(availableTaxi.size()==0){
        if((int)pickupTime==65)
          tempPoint = (char)((int)tempPoint+1);
        else
          if(((int)pickupPoint>=65 && (int)pickupPoint<=69) && tempPoint=='A')
            tempPoint = (char)((int)pickupTime+1);
          else
            tempPoint = (char)((int)tempPoint-1);
      }
      else  
        break;
    }
    
    Collections.sort(availableTaxi, new Comparator<Taxi>() {
      public int compare(Taxi e1, Taxi e2) {
        return Integer.valueOf(e1.getEarned()).compareTo(Integer.valueOf(e2.getEarned()));
      }
    });

  }
}
