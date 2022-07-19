import java.util.ArrayList;
public class Taxi {
  static int counter = 1;
  int taxiNumber;
  char currentPoint;
  double earned;
  ArrayList<BookedTaxiDetails> details;
  boolean isFree;
  Taxi() {
    taxiNumber=counter++;
    currentPoint = 'A';
    earned = 0.00;
    isFree = true;
    details = new ArrayList<>();
  }

  public double getEarned() {
    return earned;
  }

  public void setEarned(double earned) {
    this.earned = earned;
  }

  public int gettaxiNumber() {
    return taxiNumber;
  }

  public void settaxiNumber(int taxiNumber) {
    this.taxiNumber = taxiNumber;
  }

  public char getCurrentPoint() {
    return currentPoint;
  }

  public void setCurrentPoint(char currentPoint) {
    this.currentPoint = currentPoint;
  }
}
