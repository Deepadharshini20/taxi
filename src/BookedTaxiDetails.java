public class BookedTaxiDetails {
  static int count1 = 1;
  static int count2 = 1;
  int bookingId,customerId;
  char from;
  char to;
  int pickupTime;
  int dropTime;
  double amount;

  BookedTaxiDetails(char from,char to,int pickupTime,int dropTime,double amount){
    bookingId = count1++;
    customerId = count2++;
    this.from = from;
    this.to = to;
    this.pickupTime = pickupTime;
    this.dropTime = dropTime;
    this.amount = amount;
  }
}
