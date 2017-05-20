import java.awt.event.*;
import javax.swing.JFileChooser;
import java.text.DecimalFormat;
import java.io.*;


class TrainRouteApp implements Gui.EventHandler {

  String[] stationNames = {"Leicester", "Loughborough", "Nottingham", "Derby", "York"};

  public Station leic;
  public Station lboro;
  public Station derby;
  public Station nott;
  public Station york;


  Gui gui;

  public TrainRouteApp(){
    gui = new Gui();
    gui.setEventHandler(this);

    leic = new Station("Leicester");
    lboro = new Station("Loughborough");
    derby = new Station("Derby");
    nott = new Station("Nottingham");
    york = new Station("York");

    new Route(leic, leic, 0, 0, 0);
    new Route(leic, lboro, 2.50, 4.00, 10);
    new Route(leic, derby, 3.50, 6.20, 30);
    new Route(leic, nott, 3.70, 7.00, 48);
    new Route(leic, york, 23.50, 25.00, 65);

    new Route(lboro, leic, 2.50, 4.00, 10);
    new Route(lboro, lboro, 0, 0, 0);
    new Route(lboro, derby, 1.50, 2.50, 15);
    new Route(lboro, nott, 1.25, 2.50, 23);
    new Route(lboro, york, 11.50, 20.00, 60);

    new Route(derby, leic, 3.50, 6.20, 30);
    new Route(derby, lboro, 1.50, 2.50, 15);
    new Route(derby, derby, 0, 0, 0);
    new Route(derby, nott, 2.50, 3.00, 12);
    new Route(derby, york, 11.50, 16.00, 40);

    new Route(nott, leic, 3.70, 7.00, 48);
    new Route(nott, lboro, 2.00, 2.50, 25);
    new Route(nott, derby, 1.50, 3.00, 10);
    new Route(nott, nott, 0, 0, 0);
    new Route(nott, york, 7.20, 16.00, 85);

    new Route(york, leic, 12.20, 25, 70);
    new Route(york, lboro, 12.00, 20.00, 60);
    new Route(york, derby, 8.20, 16.00, 40);
    new Route(york, nott, 11.20, 16.00, 75).setStations(new String[]{"Stat 1"});
    new Route(york, york, 0, 0, 0);
  }

  @Override
  public void onGoButtonClicked(ActionEvent e) {
    String start = gui.getDepartingStationName();
    String end = gui.getArrivingStationName();
    Station startStation = getStationFromName(start);
    Route route = startStation.getRouteFor(end);

    double price;
    if  (gui.isSingle()) {
      price =route.getSinglePrice();
    } else {
      price =route.getReturnPrice();
    }
    if (gui.getCalendar().isLastDay()) {
      price *= 0.9;
    }
    DecimalFormat df = new DecimalFormat("##.00");
    gui.setOutputPrice(df.format(price));

    String duration = String.valueOf(route.getJourneyTime());
    gui.setOutputTime(duration);

    gui.setOutputRoute(route);
  }

  @Override
  public void onSortOrderChange() {
    Station start = getStationFromName(gui.getAdminStartStation());
    Station end = getStationFromName(gui.getAdminEndStation());
    Route route = start.getRouteFor(end);
    gui.setOutputRoute(route);
  }






  public void onAdminStartChanged(String stationName) {
    loadInterStations();
  }

  public void onAdminEndChanged(String stationName) {
    loadInterStations();
  }

  private void loadInterStations() {
    Station start = getStationFromName(gui.getAdminStartStation());
    Station end = getStationFromName(gui.getAdminEndStation());
    Route route = start.getRouteFor(end);
    gui.setAdminRoute(route);

    for (String s : route.getStations()) {
      System.out.println(s);

    }

    System.out.println(route.getStartStation().getName());
  }


  public void onAdminSaveClicked() {
    onAdminUpdateClicked();
    String start = gui.getAdminStartStation();
    String end = gui.getAdminEndStation();
    Route route = getStationFromName(start).getRouteFor(end);

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setSelectedFile(new File(start+"_to_"+end+".stations"));
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        PrintWriter out = new PrintWriter(file);
        for(String s: route.getStations()) {
          out.println(s);
        }
        out.close();

        gui.setAdminRoute(route);
        // load from file
      } catch (IOException e) {
        System.out.println("Sorry an error has occurred");
      }
    }
  }

  public void onAdminLoadClicked() {
    String start = gui.getAdminStartStation();
    String end = gui.getAdminEndStation();
    Route route = getStationFromName(start).getRouteFor(end);

    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        String str = new String(data, "UTF-8");
        String lines[] = str.split("\\r?\\n");
        System.out.println(str);
        route.setStations(lines);
        gui.setAdminRoute(route);
        // load from file
      } catch (IOException e) {
        System.out.println("Sorry an error has occurred");
      }
    }
  }
  public void onAdminUpdateClicked() {
    String start = gui.getAdminStartStation();
    String end = gui.getAdminEndStation();
    Route route = getStationFromName(start).getRouteFor(end);

    String[] stations = gui.getAdminRoute();
    route.setStations(stations);
    gui.setAdminRoute(route);
  }







  public Station getStationFromName(String name) {
    switch (name) {
      case "Leicester":
        return leic;
      case "Loughborough":
        return lboro;
      case "Derby":
        return derby;
      case "Nottingham":
        return nott;
      case "York":
        return york;
    }
    return null;
  }



  public static void main(String[] args) {
    new TrainRouteApp();
  }
}
