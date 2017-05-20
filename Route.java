class Route {
  private Station startStation;
  private Station endStation;
  private double singlePrice;
  private double returnPrice;
  private int journeyTime;
  private String[] stations;

  public Route(Station startStation, Station endStation, double singlePrice, double returnPrice, int journeyTime) {
    this.startStation = startStation;
    this.endStation = endStation;
    this.singlePrice = singlePrice;
    this.returnPrice = returnPrice;
    this.journeyTime = journeyTime;
    startStation.addRoute(this);
  }

  public Station getStartstation() {
    return startStation;
  }

  public Station getEndStation() {
    return endStation;
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


  public String[] path() {
    String[] route = new String[stations.length + 2];
    route[0] = startStation.getName();
    for (int i = 1; i <= stations.length; i++) {
      route[i] = stations[i - 1];
    }
    route[route.length - 1] = endStation.getName();
    return route;
  }
}
