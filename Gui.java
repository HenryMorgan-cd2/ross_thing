import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.swing.event.*;

class Gui extends JFrame {

  public final static int WINDOW_WIDTH = 600;
  public final static int WINDOW_HEIGHT = 300;

  String[] stationNames = {"Leicester", "Loughborough", "Nottingham", "Derby", "York"};

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
  private JComboBox adminStartCombo;
  private JComboBox adminEndCombo;

  private JRadioButton inputSingleRadio;
  private JRadioButton inputReturnRadio;
  private JRadioButton routeOptionABCRadio;
  private JRadioButton routeOptionOrderRadio;

  private JButton goButton;

  private JTextArea inputAdminTextArea;

  private JCalendar mCalendar;


  //////////////////ALL EVENT LISTENERS

  private EventHandler mEventHandler;

  public void setEventHandler(EventHandler eventHandler) {
    mEventHandler = eventHandler;
  }

  public interface EventHandler {
    public void onGoButtonClicked(ActionEvent e);

    public void onSortOrderChange();

    public void onAdminStartChanged(String stationName);
    public void onAdminEndChanged(String stationName);

    public void onAdminSaveClicked();
    public void onAdminLoadClicked();
    public void onAdminUpdateClicked();
  }


  ////GUI

  public Gui() {
    super("Train Route Finder");

    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    windowPanel = new JPanel();
    add(windowPanel);

    CardLayout layout = new CardLayout();
    windowPanel.setLayout(layout);

    windowPanel.add(buildFrontEndPanel(), FRONT_END_VIEW_KEY);
    windowPanel.add(buildAdminPanel(), ADMIN_VIEW_KEY);

    layout.show(windowPanel, FRONT_END_VIEW_KEY);

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
        showAdmin();
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
    panel.add(buildOutputRouteOptionsPanel());
    return panel;
  }

  private JPanel buildInputPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildInputDepartingPanel());
    panel.add(buildInputArrivingPanel());
    mCalendar = new JCalendar();
    panel.add(mCalendar);
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

  private JPanel buildOutputRouteOptionsPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    routeOptionABCRadio = new JRadioButton("ABC");
    routeOptionABCRadio.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onSortOrderChange();
        }
      }
    });
    routeOptionOrderRadio = new JRadioButton("Route order");
    routeOptionOrderRadio.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onSortOrderChange();
        }
      }
    });
    ButtonGroup group = new ButtonGroup();
    group.add(routeOptionABCRadio);
    group.add(routeOptionOrderRadio);
    routeOptionOrderRadio.setSelected(true);
    panel.add(routeOptionABCRadio);
    panel.add(routeOptionOrderRadio);
    return panel;
  }

  private JPanel buildInputDepartingPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(new JLabel("departing: "));
    inputDepartingCombo = new JComboBox(stationNames);
    panel.add(inputDepartingCombo);
    return panel;
  }
  private JPanel buildInputArrivingPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(new JLabel("arriving: "));
    inputArrivingCombo = new JComboBox(stationNames);
    panel.add(inputArrivingCombo);
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
    panel.add(new Jlabel("Submit: "));
    goButton = new JButton("Go!");
    panel.add(goButton);
    goButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onGoButtonClicked(e);
        }
      }
    });
    return panel;
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
        showWindow();
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
    adminStartCombo = new JComboBox(stationNames);
    adminStartCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onAdminStartChanged(adminStartCombo.getSelectedItem().toString());
        }
      }
    });
    panel.add(adminStartCombo);
    panel.add(new JLabel(" to "));
    adminEndCombo = new JComboBox(stationNames);
    adminEndCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onAdminEndChanged(adminEndCombo.getSelectedItem().toString());
        }
      }
    });
    panel.add(adminEndCombo);
    return panel;
  }

  private JPanel buildAdminStationPanel() {
    JPanel panel = new JPanel();
    inputAdminTextArea = new JTextArea(5, 20);
    panel.add(new JScrollPane(inputAdminTextArea));
    return panel;
  }

  private JPanel buildAdminButtonPanel() {
    JPanel panel = new JPanel();
    JButton save = new JButton("Save");
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onAdminSaveClicked();
        }
      }
    });
    panel.add(save);
    JButton load = new JButton("Load");
    load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onAdminLoadClicked();
        }
      }
    });
    panel.add(load);
    JButton update = new JButton("Update");
    update.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onAdminUpdateClicked();
        }
      }
    });
    panel.add(update);
    return panel;
  }

  ///////////////////SETTERS

  public void showAdmin() {
    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, ADMIN_VIEW_KEY);
  }

  public void showWindow() {
    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, FRONT_END_VIEW_KEY);
  }

  public void setOutputPrice(String price) {
    outputPriceLabel.setText(price);
  }

  public void setOutputTime(String time) {
    outputTimeLabel.setText(time);
  }

  public void setOutputRoute(String route) {
    outputRouteLabel.setText(route);
  }

  public void setOutputRoute(Route route) {
    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    for (String part : route.path(isSortAlphabetical())) {
      builder.append(part);
      builder.append("<br>");
    }
    builder.append("</html>");
    setOutputRoute(builder.toString());
  }

  public void setAdminRoute(Route route) {
    String s = "";
    for (String part : route.getStations()) {
      s+=part+"\n";
    }
    inputAdminTextArea.setText(s);
  }


  ////////////////////GETTERS

  public String getAdminStartStation() {
    return adminStartCombo.getSelectedItem().toString();
  }
  public String getAdminEndStation() {
    return adminEndCombo.getSelectedItem().toString();

  }

  public String[] getAdminRoute() {
    return inputAdminTextArea.getText().split("\\r?\\n");
  }


  public String getDepartingStationName() {
    return inputDepartingCombo.getSelectedItem().toString();
  }

  public String getArrivingStationName() {
    return inputArrivingCombo.getSelectedItem().toString();
  }

  public boolean isSingle() {
    return inputSingleRadio.isSelected();
  }

  public JCalendar getCalendar() {
    return this.mCalendar;
  }

  public boolean isSortAlphabetical() {
    return routeOptionABCRadio.isSelected();
  }

}
