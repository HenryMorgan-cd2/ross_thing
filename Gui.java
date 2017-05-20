import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

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


  //////////////////ALL EVENT LISTENERS

  private EventHandler mEventHandler;

  public void setEventHandler(EventHandler eventHandler) {
    mEventHandler = eventHandler;
  }

  public interface EventHandler {
    public void onAdminButtonClicked(ActionEvent e);
    public void onBackButtonClicked(ActionEvent e);
    public void onResetButtonClicked(ActionEvent e);
    public void onGoButtonClicked(ActionEvent e);
    public void onInputMonthComboClicked(ActionEvent e);
    public void onInputYearComboClicked(ActionEvent e);
    public void onInputDateButtonClicked(int date, ActionEvent e);
  }


  ////GUI

  public Gui() {
    super("Train Route Finder");

    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    windowPanel = new JPanel();
    CardLayout layout = new CardLayout();
    windowPanel.setLayout(layout);

    windowPanel.add(buildFrontEndPanel(), FRONT_END_VIEW_KEY);
    windowPanel.add(buildAdminPanel(), ADMIN_VIEW_KEY);

    add(windowPanel);
    layout.show(windowPanel, FRONT_END_VIEW_KEY);

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
        System.out.println(mEventHandler);
        if (mEventHandler != null) {
          mEventHandler.onAdminButtonClicked(e);
        }
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
        if (mEventHandler != null) {
          mEventHandler.onResetButtonClicked(e);
        }
      }
    });
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

  private JPanel buildInputCalendarComboPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    inputMonthCombo = new JComboBox(stationNames);
    panel.add(inputMonthCombo);
    inputMonthCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onInputMonthComboClicked(e);
        }
      }
    });
    inputYearCombo = new JComboBox(stationNames);
    panel.add(inputYearCombo);
    inputYearCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (mEventHandler != null) {
          mEventHandler.onInputYearComboClicked(e);
        }
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
        if (mEventHandler != null) {
          mEventHandler.onInputDateButtonClicked(date, e);
        }
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
        if (mEventHandler != null) {
          mEventHandler.onBackButtonClicked(e);
        }
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
    inputAdminStartCombo = new JComboBox(stationNames);
    panel.add(inputAdminStartCombo);
    panel.add(new JLabel("End station: "));
    inputAdminEndCombo = new JComboBox(stationNames);
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

  ///////////////////GUI SETTERS

  public void showAdmin() {
    CardLayout cardLayout = (CardLayout) windowPanel.getLayout();
    cardLayout.show(windowPanel, ADMIN_VIEW_KEY);
  }

  public void showWindow() {
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

}
