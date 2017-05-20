import java.awt.event.*;


class TrainRouteApp implements Gui.EventHandler {

  String[] stationNames = {"Leicester", "Loughborough", "Nottingham", "Derby", "York"};
  private Station[] stations = new Station[5];

  Gui gui;

  public TrainRouteApp(){
    gui = new Gui();
    gui.setEventHandler(this);
    
    for(int i=0; i<5; i++) {
      stations[i] = new Station(stationNames[i]);
    }

  }

  public void onAdminButtonClicked(ActionEvent e) {
    gui.showAdmin();
  }

  public void onBackButtonClicked(ActionEvent e) {
    gui.showWindow();
  }

  public void onResetButtonClicked(ActionEvent e) {
    System.out.println("resetButton button clicked");
  }

  public void onGoButtonClicked(ActionEvent e) {
    System.out.println("goButton button clicked");
  }

  public void onInputMonthComboClicked(ActionEvent e) {
    System.out.println("inputMonthCombo button clicked");
  }

  public void onInputYearComboClicked(ActionEvent e) {
    System.out.println("inputYearCombo button clicked");
  }

  public void onInputDateButtonClicked(int date, ActionEvent e) {
    System.out.println(String.valueOf(date) + " button clicked");
  }




  public static void main(String[] args) {
    new TrainRouteApp();
  }
}
