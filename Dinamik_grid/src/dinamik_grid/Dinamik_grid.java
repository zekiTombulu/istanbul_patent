/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinamik_grid;


import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author zeki
 */
public class Dinamik_grid extends JFrame implements ActionListener {

    private JPanel content_panel,setting_panel;
   
    TextField rooms_tf;
    JButton add_room_btn, last_month_btn, next_month_btn, current_month_btn;
    JMonthChooser months_chooser;
    JYearChooser year_chooser;
    int selected_month,selected_year;
    
   
    String total_room_count;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dinamik_grid frame = new Dinamik_grid();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Dinamik_grid() {

        super("Oda Paneli");
        create_setting_panel();

        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent Ae) {

        JButton source = (JButton) Ae.getSource();
        if (source.getActionCommand().equals("Oda ekle/oluştur")) {
            try {
                total_room_count = this.rooms_tf.getText();
                selected_month = months_chooser.getMonth() + 1;
                selected_year = year_chooser.getYear();
                Calculations calculations_ = new Calculations();
                int ay_gun_sayisi = calculations_.calculate_month_number_of_days(selected_month, selected_year);

                create_content_panel(Integer.parseInt(total_room_count), ay_gun_sayisi);
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Hatalı işlem yaptınız.");
                return;
            }

        } else if (source.getActionCommand().equals("<<")) {
            selected_month = selected_month - 1;
            months_chooser.setMonth(selected_month - 1);
            Calculations calculations_ = new Calculations();
            int days = calculations_.calculate_month_number_of_days(selected_month, selected_year);

            create_content_panel(Integer.parseInt(total_room_count), days);

        } else if (source.getActionCommand().equals(">>")) {

            selected_month = selected_month + 1;
            months_chooser.setMonth(selected_month - 1);
            Calculations calculations_ = new Calculations();
            int days = calculations_.calculate_month_number_of_days(selected_month, selected_year);

            create_content_panel(Integer.parseInt(total_room_count), days);
        } else if (source.getActionCommand().equals("Bu ay")) {

            java.util.Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            

            months_chooser.setMonth(month);
            Calculations hesaplamalar = new Calculations();
            int days = hesaplamalar.calculate_month_number_of_days(month + 1, selected_year);

            create_content_panel(Integer.parseInt(total_room_count), days);

        }
    }

    public void create_setting_panel() {

        setting_panel = new JPanel();
        setting_panel.setLayout(new GridLayout(1, 7));

        rooms_tf = new TextField();
        rooms_tf.setSize(50, 20);
        setting_panel.add(rooms_tf);
        add_room_btn = new JButton("Oda ekle/oluştur");
        add_room_btn.setName("oda_ekle");
        add_room_btn.addActionListener(this);

        setting_panel.add(add_room_btn);
        last_month_btn = new JButton("<<");
        last_month_btn.addActionListener(this);

        setting_panel.add(last_month_btn);
        months_chooser = new JMonthChooser();
        setting_panel.add(months_chooser);
        year_chooser = new JYearChooser();
        setting_panel.add(year_chooser);

        next_month_btn = new JButton(">>");
        next_month_btn.addActionListener(this);
        setting_panel.add(next_month_btn);

        current_month_btn = new JButton("Bu ay");
        current_month_btn.addActionListener(this);
        setting_panel.add(current_month_btn);

        add(setting_panel);

        add(setting_panel, BorderLayout.NORTH);

    }

    public void create_content_panel(int day_count, int room_count) {

        content_panel = new JPanel();

        content_panel.setLayout(null);
        int width = day_count * 50;
        int height = room_count * 50;
        for (int i = 1; i <= room_count + 1; i++) {
            for (int j = 1; j <= day_count + 1; j++) {

                final int datetime = i - 1;
                final int room_number = j - 1;

                if (i == 1 & j == 1) {
                    final JButton btn_ij = new JButton("No");

                    btn_ij.setFont(new Font("Tahoma", Font.PLAIN, 10));
                    btn_ij.setBounds(i * 30, j * 30, 47, 27);
                    btn_ij.setBackground(Color.red);
                    btn_ij.setForeground(Color.black);
                    btn_ij.setName("Oda/Tarih");

                    content_panel.add(btn_ij);

                } else if (i == 1 & j != 1) {

                    final JButton btn_ij = new JButton(Integer.toString(j - 1));

                    btn_ij.setFont(new Font("Tahoma", Font.PLAIN, 8));
                    btn_ij.setBounds(i * 30, j * 30, 47, 27);

                    btn_ij.setBackground(Color.red);
                    btn_ij.setForeground(Color.black);
                    btn_ij.setName("Oda/Tarih");

                    content_panel.add(btn_ij);

                } else if (j == 1 & i != 1) {

                    final JButton btn_ij = new JButton(Integer.toString(i - 1));

                    btn_ij.setFont(new Font("Tahoma", Font.PLAIN, 6));
                    btn_ij.setBounds(i * 42, j * 30, 40, 27);

                    btn_ij.setBackground(Color.yellow);
                    btn_ij.setForeground(Color.black);
                    btn_ij.setName("Oda/Tarih");

                    content_panel.add(btn_ij);

                } else {

                    final JButton btn_ij = new JButton();

                    btn_ij.setFont(new Font("Tahoma", Font.PLAIN, 6));
                    btn_ij.setBounds(i * 42, j * 30, 37, 27);
                    btn_ij.setBackground(Color.green);
                    btn_ij.setForeground(Color.black);
                    btn_ij.setName(Integer.toString(i) + Integer.toString(j));
                    content_panel.add(btn_ij);

                    btn_ij.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String mesaj = "Oda no: " + Integer.toString(room_number)
                                    + " Tarih :" + Integer.toString(datetime) + "-" + selected_month + "-" + selected_year;
                            JOptionPane.showMessageDialog(null, mesaj);
                        }
                    });

                }
            }

        }
        content_panel.revalidate();
        content_panel.repaint();
        pack();
        setSize(height, width + 100);

        add(content_panel, BorderLayout.CENTER);
    }

}
