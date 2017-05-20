import java.util.Arrays;

class Route {
  private Station startStation;
  private Station endStation;
  private double singlePrice;
  private double returnPrice;
  private int journeyTime;
  private String[] stations = {};

  public Route(Station startStation, Station endStation, double singlePrice, double returnPrice, int journeyTime) {
    this.startStation = startStation;
    this.endStation = endStation;
    this.singlePrice = singlePrice;
    this.returnPrice = returnPrice;
    this.journeyTime = journeyTime;
    startStation.addRoute(this);
  }

  public Station getStartStation() {
    return startStation;
  }

  public Station getEndStation() {
    return endStation;
  }

  public double getSinglePrice() {
    System.out.println(singlePrice);
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
    return path(false);
  }

  public String[] path(boolean sortAlpha) {
    String[] route = new String[this.stations.length + 2];

    String[] interStations = stations.clone();

    if (sortAlpha) {
      Arrays.sort(interStations);
    }

    for (int i = 1; i <= interStations.length; i++) {
      route[i] = interStations[i - 1];
    }

    route[0] = startStation.getName();
    route[route.length - 1] = endStation.getName();

    return route;
  }
}
