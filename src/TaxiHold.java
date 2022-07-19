class taxiHold extends Thread {
  Taxi t;
  int distance;
  char dropPoint;
  taxiHold(Taxi t,int distance,char dropPoint) {
    this.t = t;
    this.distance = distance;
    this.dropPoint = dropPoint;
  }

  public void run() {
    t.isFree = false;
    try {
      System.out.println("Taxi id " + t.taxiNumber + " is Assigned ");
      Thread.sleep(distance*60000);// 10 sec but calculation = Math.abs(pp-dp)*60*60*1000
      t.isFree = true;
      t.setCurrentPoint(dropPoint);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}