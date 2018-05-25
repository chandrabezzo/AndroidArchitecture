package com.chandraabdulfattah.coremvp.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bezzo on 05/10/17.
 */

public class DateTimeUtils {

    // EPOCH CONVERTER

    public static String epochToDate(long epoch){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(new Date(epoch*1000));
    }

    public static String epochToDateTime(long epoch){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(new Date(epoch*1000));
    }

    public static CalendarDay epochToCalendarDay(long epoch){
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(new Date(epoch*1000));

        String[] splitDate = date.split("/");

        CalendarDay calendar = CalendarDay.from(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1])-1,
                Integer.parseInt(splitDate[0]));

        return calendar;
    }

    public static long dateToEpoch(String date){

        long epoch = 0;

        try {
            epoch = (new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .parse(date).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }

    public static long dateTimeToEpoch(String dateTime){

        long epoch = 0;

        try {
            epoch = (new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .parse(dateTime).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }

    // END EPOCH CONVERTER

    // DATE COMPONENT

    public static String getCurrentDate(){
        return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
    }

    public static String getCurrentTime(){
        return getCurrentHour() + ":" + getCurrentMinute() + ":" + getCurrentSecond();
    }

    public static int getCurrentDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    // January = 0
    public static int getCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getCurrentSecond(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }

    // END DATE COMPONENT

    // Calendar CONVERTER

    public static Calendar getTodayInTime(){
        return Calendar.getInstance();
    }

    public static Calendar getAddedDateInTime(String date, int addDay){
        Calendar calendar = Calendar.getInstance();
        String[] splitDate = date.split("/");

        int today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
        int selected = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
        calendar.add(Calendar.DAY_OF_MONTH, addDay);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        if (today == selected){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return calendar;
    }

    public static Calendar dateToCalendar(String date){
        String[] splitDate = date.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));

        return calendar;
    }

    public static Calendar createDateTime(String dateTime){
        String[] datetimeSplit = dateTime.split(" ");
        String[] dateSplit = datetimeSplit[0].split("-");
        String[] timeSplit = datetimeSplit[1].split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timeSplit[2]));

        return calendar;
    }

    // END Calendar CONVERTER

    public static String getMonth(int position){
        String[] months = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "Oktober", "November", "December"};

        return months[position];
    }

    public static String getDayOfWeek(int position){
        String[] dayOfWeek = new String[]{"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        return dayOfWeek[position];
    }

    public static String getAddedDateInFormat(String date, int addDay){
        String[] splitDate = date.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]));
        calendar.add(Calendar.DAY_OF_MONTH, addDay);

        String addedDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1)
                + "/" + calendar.get(Calendar.YEAR);

        return addedDate;
    }

    public static String getHour(Integer hour){
        String textHour = "";

        if (hour < 10){
            textHour = "0"+hour;
        }
        else {
            textHour = hour.toString();
        }

        return textHour;
    }

    public static String getMinute(Integer minute){
        String textMinute = "";

        if (minute < 10){
            textMinute = "0"+minute;
        }
        else {
            textMinute = minute.toString();
        }

        return textMinute;
    }
}
