class Route {
  private String startStn;
  private String endStn;
  private double singlePrice;
  private double returnPrice;
  private int journeyTime;
  private String[] stations;

  public Route(Station startStn, Station endStn, double singlePrice, double returnPrice, int journeyTime) {
    this.startStn = startStn;
    this.endStn = endStn;
    this.singlePrice = singlePrice;
    this.returnPrice = returnPrice;
    this.journeyTime = journeyTime;
    startStn.addRoute(this);
  }

  public double getSinglePrice() {
    return singlePrice;
  }

  public double getReturnPrice() {
    return returnPrice;
  }

  public int getJourneyTime() {
    return journeyTime;
  }

  public void setStations(String[] stations) {
    this.stations = stations;
  }

  public String[] getStations() {
    return stations;
  }

  public String getEndStation() {
    return endStn;
  }

  public String[] path() {
    String[] route = new String[stations.length + 2];
    route[0] = startStn.getName();
    for (int i = 1; i <= stations.length; i++) {
      route[i] = stations[i - 1];
    }
    route[route.length - 1] = endStn.getName();
    return route;
  }
}
