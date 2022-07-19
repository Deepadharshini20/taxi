public class DisplayTaxi {
  public static void display() {
    for(Taxi taxi:TaxiDetails.getInstance().taxiList){
      System.out.println("Taxi - "+taxi.gettaxiNumber()+"     "+"Total Earnings: "+taxi.getEarned());
      System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
      for(BookedTaxiDetails travelDetail : taxi.details){
        System.out.print("   "+travelDetail.bookingId);
        System.out.print("            "+travelDetail.customerId);
        System.out.print("            "+travelDetail.from);
        System.out.print("      "+travelDetail.to);
        System.out.print("        "+travelDetail.pickupTime);
        System.out.print("          "+travelDetail.dropTime);
        System.out.print("          "+travelDetail.amount);
        System.out.println();
      }
      System.out.println("\n");
    }
  }
  static void taxiStatus(){
		for(Taxi t: TaxiDetails.getInstance().taxiList){
			if(t.isFree==true)
				System.out.println("Taxi id "+t.taxiNumber+" is free( Available Now )");
			else
				System.out.println("Taxi id "+t.taxiNumber+" is busy ( Not Available Now ) ");
		}
	}
}
