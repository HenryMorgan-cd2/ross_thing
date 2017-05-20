class Station {
  private String name;
  private Route[] routes;

  public Station(String name) {
    this.name = name;
    this.routes  = new Route[4];
  }

  public Route addRoute(Route route) {
    switch (route.getEndStation().getName()) {
      case "Leicester" :
        this.routes[0] = route;
        break;

      case "Loughborough" :
        this.routes[1] = route;
        break;

      case "Nottingham" :
        this.routes[2] = route;
        break;

      case "Derby" :
        this.routes[3] = route;
        break;

      case "York" :
        this.routes[4] = route;
        break;
    }
    return route;
  }
  
  public String getName() {
    return name;
  }
}
