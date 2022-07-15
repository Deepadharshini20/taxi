public class Taxi {
  static int taxiCount = 0;
  char avaiablePoint;
  int earned;
  Taxi() {
    taxiCount = taxiCount + 1;
    avaiablePoint = 'A';
    earned = 0;
  }

  public int getEarned() {
    return earned;
  }

  public void setEarned(int earned) {
    this.earned = earned;
  }

  public static int getTaxiCount() {
    return taxiCount;
  }

  public static void setTaxiCount(int taxiCount) {
    Taxi.taxiCount = taxiCount;
  }

  public char getAvaiablePoint() {
    return avaiablePoint;
  }

  public void setAvaiablePoint(char avaiablePoint) {
    this.avaiablePoint = avaiablePoint;
  }
}
