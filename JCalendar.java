import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

class JCalendar extends JPanel {
  public int month;
  public int year;

  public int selectedDate;
  public int selectedMonth;
  public int selectedYear;

  JButton[] dateButtons = new JButton[31];

  private int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private String[] monthList = new String[]{ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
  public static int currentYear = Calendar.getInstance().get(Calendar.YEAR);

  JLabel selectedLabel;

  public JCalendar() {
    selectedDate = Calendar.getInstance().get(Calendar.DATE);;
    selectedMonth = Calendar.getInstance().get(Calendar.MONTH);;
    selectedYear = Calendar.getInstance().get(Calendar.YEAR);;

    month = this.selectedMonth;
    year = this.selectedYear;

    add(buildMainPanel());

    dateUpdated();
  }

  private JPanel buildMainPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(buildControlPanel());
    panel.add(buildDatePanel());
    panel.add(buildSelectedPanel());

    return panel;
  }

  private JPanel buildControlPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(buildMonthCombo());
    panel.add(buildYearCombo());
    return panel;
  }

  private JComboBox buildMonthCombo() {
    JComboBox combo = new JComboBox(monthList);
    combo.setSelectedItem(monthList[this.selectedMonth]);
    combo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String month = ((String)((JComboBox) e.getSource()).getSelectedItem());
        int monthIdx = java.util.Arrays.asList(monthList).indexOf(month);
        onMonthChanged(monthIdx);
      }
    });
    return combo;
  }

  private JSpinner buildYearCombo() {
    SpinnerModel model =
        new SpinnerNumberModel(currentYear, //initial value
                               currentYear, //min
                               currentYear + 100, //max
                               1);                //step
    final JSpinner spinner = new JSpinner(model);
    spinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        onYearChanged((int)spinner.getValue());
      }
    });
    return spinner;
  }

  private JPanel buildDatePanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 7));
    for (int i = 1; i <= 31; i++) {
      panel.add(buildDateButton(i));
    }
    return panel;
  }

  private JButton buildDateButton(final int date) {
    JButton button = new JButton(String.valueOf(date));
    dateButtons[date-1] = button;
    button.setBorder(null);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        onDateClicked(date);
      }
    });
    return button;
  }

  private JLabel buildSelectedPanel() {
    this.selectedLabel = new JLabel("4th October 1990");
    return this.selectedLabel;
  }

  private void onYearChanged(int year) {
    this.year = year;
    updateDaysInMonth();
    dateUpdated();
  }

  private void onMonthChanged(int month) {
    this.month = month;
    updateDaysInMonth();
    dateUpdated();
  }

  private void onDateClicked(int date) {
    this.selectedDate = date;
    this.selectedMonth = this.month;
    this.selectedYear = this.year;

    dateUpdated();
  }

  private void updateDaysInMonth() {
    int daysInMonth = this.daysInMonth[month];
    System.out.println(daysInMonth);
    dateButtons[28].setVisible(!(this.month == 1 && isLeapYear(this.year)));
    for (int i=29; i<31; i++) {
      System.out.println(i < daysInMonth);

      dateButtons[i].setVisible(i < daysInMonth);
    }
  }


  private void dateUpdated() {
    // resets all buttons
    for (JButton btn : this.dateButtons) {
      btn.setBackground(null);
    }

    //show selected button
    if (this.year == this.selectedYear && this.month == this.selectedMonth) {
      this.dateButtons[this.selectedDate-1].setBackground(Color.YELLOW);
    } else {
      this.dateButtons[this.selectedDate-1].setBackground(null);
    }

    updateSelectedLabel();
  }

  private void updateSelectedLabel() {
    String monthName = this.monthList[this.selectedMonth];
    selectedLabel.setText(this.selectedDate + " " + monthName + " " + this.selectedYear);
  }


  public boolean isLastDay() {
    return this.selectedDate == this.daysInMonth[month];

  }

  private boolean isLeapYear() {
    return isLeapYear(this.selectedYear);
  }

  private boolean isLeapYear(int year) {
    return !((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
  }


}
