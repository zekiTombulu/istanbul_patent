/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinamik_grid;

/**
 *
 * @author zeki
 */
public class Calculations {

    public boolean calculate_leap_years(int year_value) {
        if ((year_value % 400 == 0) || ((year_value % 4 == 0) && (year_value % 100 != 0))) {
            return true;
        } else {
            return false;
        }

    }

    public int calculate_month_number_of_days(int month_value, int year_value_) {

        boolean year_type = calculate_leap_years(year_value_);
        int days = 0;
        if (month_value == 1 | month_value == 3 | month_value == 5 | month_value == 7 | month_value == 8 | month_value == 10 | month_value == 12) {
            days = 31;
        } else if (month_value == 4 | month_value == 6 | month_value == 9 | month_value == 11) {
            days = 30;
        } else if (month_value == 2 && year_type == false) {
            days = 28;
        } else if (month_value == 2 && year_type == true) {
            days = 29;
        }
        return days;
    }
}
