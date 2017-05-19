import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Calendar;

class JCalendar extends JPanel {
  public String selectedDate;
  public String selectedMonth;
  public String selectedYear;
  public JPanel panel;
  private JComboBox inputMonthCombo;
  private int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private String[] monthList = new String[]{ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
  public static int currentYear = Calendar.getInstance().get(Calendar.YEAR);

  public JCalendar() {
    add(buildInputCalendarPanel());
  }

  private JPanel buildInputCalendarPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildInputCalendarComboPanel());
    panel.add(buildInputCalendarDatePanel());

    return panel;
  }

  private JPanel buildInputCalendarComboPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    inputMonthCombo = new JComboBox(monthList);
    panel.add(inputMonthCombo);
    inputMonthCombo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onInputMonthComboClicked(e);
      }
    });

    panel.add(buildInputYearCombo());

    return panel;
  }

  private JSpinner buildInputYearCombo() {
    SpinnerModel model =
        new SpinnerNumberModel(currentYear, //initial value
                               currentYear, //min
                               currentYear + 100, //max
                               1);                //step
    JSpinner spinner = new JSpinner(model);
    spinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        onInputYearSpinnerChanged(e);
      }
    });
    return spinner;
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

  private void onInputMonthComboClicked(ActionEvent e) {
    System.out.println("inputMonthCombo button clicked");
  }

  private void onInputYearSpinnerChanged(ChangeEvent e) {
    System.out.println("inputYearSpinner changed");

  }

  private void onInputDateButtonClicked(int date, ActionEvent e) {
    System.out.println(String.valueOf(date) + " button clicked");
  }

  private boolean isLeapYear():
    if ((year % 4 == 0) && (year % 100 != 0))
    or (year modulo 400 is 0)
      return true;
    } else {
      return false;}


}
