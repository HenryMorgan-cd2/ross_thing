import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;


class TrainRouteApp {

  public final static int WINDOW_WIDTH = 600;
  public final static int WINDOW_HEIGHT = 300;

  public final static String FRONT_END_VIEW_KEY = "1";
  public final static String ADMIN_VIEW_KEY = "2";

  private JFrame frame;

  private JPanel frontEndPanel;
  private JPanel windowPanel;

  private JLabel outputPriceLabel;
  private JLabel outputTimeLabel;
  private JLabel outputRouteLabel;

  private JComboBox inputDepartingCombo;
  private JComboBox inputArrivingCombo;
  private JComboBox inputMonthCombo;
  private JComboBox inputYearCombo;
  private JComboBox inputAdminStartCombo;
  private JComboBox inputAdminEndCombo;

  private JRadioButton inputSingleRadio;
  private JRadioButton inputReturnRadio;

  private JButton goButton;
  private JButton resetButton;
  private JButton saveButton;
  private JButton loadButton;
  private JButton updateButton;

  private JTextArea inputAdminTextArea;

  String[] petStrings = new String[]{ "Bird", "Cat", "Dog", "Rabbit", "Pig" };

  public TrainRouteApp(){
    frame = new JFrame("Train Route Finder");

    windowPanel = new JPanel();
    windowPanel.setLayout(new CardLayout());
    windowPanel.add(buildFrontEndPanel(), FRONT_END_VIEW_KEY);
    windowPanel.add(buildAdminPanel(), ADMIN_VIEW_KEY);

    frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    frame.setVisible(true);
    frame.add(windowPanel);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, FRONT_END_VIEW_KEY);

    setGuiOutputPrice("12.00");
    setGuiOutputTime("50");
    setGuiOutputRoute("Leicester");
  }

  private JPanel buildFrontEndPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(buildTitleBar(), BorderLayout.NORTH);
    panel.add(buildMainBody(), BorderLayout.CENTER);
    return panel;
  }

  private JPanel buildTitleBar() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JButton adminButton = new JButton("Admin");
    panel.add(adminButton, BorderLayout.EAST);

    adminButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onAdminButtonClicked(e);
      }
    });

    return panel;
  }

  private JPanel buildMainBody() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(buildInputPanel());
    panel.add(buildOutputPanel());

    return panel;
  }

  private JPanel buildOutputPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildOutputPricePanel());
    panel.add(buildOutputTimePanel());
    panel.add(buildOutputRoutePanel());
    return panel;
  }

  private JPanel buildInputPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildInputDepartingPanel());
    panel.add(buildInputArrivingPanel());
    panel.add(new JCalendar());
    panel.add(buildInputSingleReturnPanel());
    panel.add(buildInputActionsPanel());
    return panel;
  }

  private JPanel buildOutputPricePanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Price: £"));
    outputPriceLabel = new JLabel("");
    panel.add(outputPriceLabel);
    return panel;
  }
  private JPanel buildOutputTimePanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Journey time: "));
    outputTimeLabel = new JLabel("");
    panel.add(outputTimeLabel);
    panel.add(new JLabel(" minutes"));
    return panel;
  }
  private JPanel buildOutputRoutePanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Stations on route: "));
    outputRouteLabel = new JLabel("");
    panel.add(outputRouteLabel);
    return panel;
  }
  private JPanel buildInputDepartingPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(new JLabel("departing: "));
    inputDepartingCombo = new JComboBox(petStrings);
    panel.add(inputDepartingCombo);
    return panel;
  }
  private JPanel buildInputArrivingPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(new JLabel("arriving: "));
    inputArrivingCombo = new JComboBox(petStrings);
    panel.add(inputArrivingCombo);
    return panel;
  }
  private JPanel buildInputCalendarPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildInputCalendarComboPanel());
    panel.add(buildInputCalendarDatePanel());

    return panel;
  }
  private JPanel buildInputSingleReturnPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    inputSingleRadio = new JRadioButton("Single");
    inputReturnRadio = new JRadioButton("Return");
    ButtonGroup group = new ButtonGroup();
    group.add(inputSingleRadio);
    group.add(inputReturnRadio);
    inputSingleRadio.setSelected(true);
    panel.add(inputSingleRadio);
    panel.add(inputReturnRadio);
    return panel;
  }
  private JPanel buildInputActionsPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    resetButton = new JButton("Reset");
    panel.add(resetButton);
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onResetButtonClicked(e);
      }
    });
    goButton = new JButton("Go!");
    panel.add(goButton);
    goButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onGoButtonClicked(e);
      }
    });
    return panel;
  }

  private JPanel buildInputCalendarComboPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    inputMonthCombo = new JComboBox(petStrings);
    panel.add(inputMonthCombo);
    inputMonthCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onInputMonthComboClicked(e);
      }
    });
    inputYearCombo = new JComboBox(petStrings);
    panel.add(inputYearCombo);
    inputYearCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onInputYearComboClicked(e);
      }
    });
    return panel;
  }

  private JPanel buildInputCalendarDatePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 7));
    for (int i = 1; i <= 31; i++) {
      panel.add(buildInputCalendarDateButton(i));
    }
    return panel;
  }

  private JButton buildInputCalendarDateButton(final int date) {
    JButton button = new JButton(String.valueOf(date));
    button.setBorder(null);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onInputDateButtonClicked(date, e);
      }
    });
    return button;
  }

  //admin GUI

  private JPanel buildAdminPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(buildAdminTitleBar(), BorderLayout.NORTH);
    panel.add(buildAdminBody(), BorderLayout.CENTER);
    return panel;
  }

  private JPanel buildAdminTitleBar() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    JButton button = new JButton("< Back");
    panel.add(button, BorderLayout.WEST);

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onBackButtonClicked(e);
      }
    });

    return panel;
  }

  private JPanel buildAdminBody() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 1));
    panel.add(buildAdminInputPanel());
    panel.add(buildAdminStationPanel());
    panel.add(buildAdminButtonPanel());
    return panel;
  }

  private JPanel buildAdminInputPanel() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Start station: "));
    inputAdminStartCombo = new JComboBox(petStrings);
    panel.add(inputAdminStartCombo);
    panel.add(new JLabel("End station: "));
    inputAdminEndCombo = new JComboBox(petStrings);
    panel.add(inputAdminEndCombo);
    return panel;
  }

  private JPanel buildAdminStationPanel() {
    JPanel panel = new JPanel();
    inputAdminTextArea = new JTextArea(5, 20);
    panel.add(inputAdminTextArea);
    return panel;
  }

  private JPanel buildAdminButtonPanel() {
    JPanel panel = new JPanel();
    saveButton = new JButton("Save");
    panel.add(saveButton);
    loadButton = new JButton("Load");
    panel.add(loadButton);
    updateButton = new JButton("Update");
    panel.add(updateButton);
    return panel;
  }


  //////////////////ALL EVENT LISTENERS

  private void onAdminButtonClicked(ActionEvent e) {
    setGuiToAdmin();
  }

  private void onBackButtonClicked(ActionEvent e) {
    setGuiToWindow();
  }

  private void onResetButtonClicked(ActionEvent e) {
    System.out.println("resetButton button clicked");
  }

  private void onGoButtonClicked(ActionEvent e) {
    System.out.println("goButton button clicked");
  }

  private void onInputMonthComboClicked(ActionEvent e) {
    System.out.println("inputMonthCombo button clicked");
  }

  private void onInputYearComboClicked(ActionEvent e) {
    System.out.println("inputYearCombo button clicked");
  }

  private void onInputDateButtonClicked(int date, ActionEvent e) {
    System.out.println(String.valueOf(date) + " button clicked");
  }

  ///////////////////GUI SETTERS

  public void setGuiToAdmin() {
    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, ADMIN_VIEW_KEY);
  }

  public void setGuiToWindow() {
    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, FRONT_END_VIEW_KEY);
  }

  public void setGuiOutputPrice(String price) {
    outputPriceLabel.setText(price);
  }

  public void setGuiOutputTime(String time) {
    outputTimeLabel.setText(time);
  }

  public void setGuiOutputRoute(String route) {
    outputRouteLabel.setText(route);
  }



  public static void main(String[] args) {
    new TrainRouteApp();
  }
}
