import java.util.ArrayList;

public class TaxiDetails {
  public ArrayList<Taxi> taxiList;
  private static TaxiDetails taxiObj = null;

  public static TaxiDetails getInstance() {
    if(taxiObj==null)
      taxiObj = new TaxiDetails();
    return taxiObj;
  }

  TaxiDetails(){
    taxiList = new ArrayList<>();
  }

  public void add(Taxi taxi){
    taxiList.add(taxi);
    System.out.println("jeeva");
  }
  
}
