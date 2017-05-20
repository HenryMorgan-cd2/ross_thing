class Station {
  private String mName;
  private Route[] mRoutes;

  public Station(String name) {
    mName = name;
    mRoutes  = new Route[5];
  }

  public String getName() {
    return mName;
  }

  public Route[] getRoutes() {
    return mRoutes;
  }

  public Route getRouteFor(Station station) {
    return getRouteFor(station.getName());
  }

  public Route getRouteFor(String stationName) {
    switch (stationName) {
      case "Leicester":
        return mRoutes[0];
      case "Loughborough":
        return mRoutes[1];
      case "Nottingham":
        return mRoutes[2];
      case "Derby":
        return mRoutes[3];
      case "York":
        return mRoutes[4];
    }
    return null;
  }

  public Route addRoute(Route route) {
    switch (route.getEndStation().getName()) {
      case "Leicester":
        this.mRoutes[0] = route;
        break;
      case "Loughborough":
        this.mRoutes[1] = route;
        break;
      case "Nottingham":
        this.mRoutes[2] = route;
        break;
      case "Derby":
        this.mRoutes[3] = route;
        break;
      case "York":
        this.mRoutes[4] = route;
        break;
    }
    return route;
  }

}
