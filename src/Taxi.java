import java.util.ArrayList;

public class Taxi {
  static int counter = 1;
  int taxiNumber;
  char avaiablePoint;
  int earned;
  ArrayList<BookedTaxiDetails> details;
  boolean isFree;
  Taxi() {
    taxiNumber=counter++;
    avaiablePoint = 'A';
    earned = 0;
    isFree = true;
    details = new ArrayList<>();
  }

  public int getEarned() {
    return earned;
  }

  public void setEarned(int earned) {
    this.earned = earned;
  }

  public int gettaxiNumber() {
    return taxiNumber;
  }

  public void settaxiNumber(int taxiNumber) {
    this.taxiNumber = taxiNumber;
  }

  public char getAvaiablePoint() {
    return avaiablePoint;
  }

  public void setAvaiablePoint(char avaiablePoint) {
    this.avaiablePoint = avaiablePoint;
  }
}
