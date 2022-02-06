package sample;

import java.text.NumberFormat;
import java.util.Objects;

public class Moon {

    private int mYear;
    private int mMonth;
    private int mDay;
    private String mPhase;

    public Moon(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
        allFunctions(year, month, day);
    }

    public String allFunctions(int year, int month, int day) {
        calculateMoonPhaseNum(year, month, day);
        findPhase(year, month, day);
        setMoonPhase(year, month, day);
        return mPhase;
    }

    public double calculateMoonPhaseNum(int year, int month, int day) {
        if (month == 1 || month == 2) {
            year = year - 1;
            month = month + 12;
        }

        int A = year / 100;
        int B = A / 4;
        int C = 2 - A + B;
        double E = 365.25 * (year + 4716);
        double F = 30.6001 * (month + 1);
        double julianDay = C + day + E + F - 1524.5;

        double daySinceNew = julianDay - 2451549.5;
        double newMoons = daySinceNew / 29.53;

        int decimal = (int) newMoons;
        double fractional = (newMoons - decimal);

        double daysIntoCycle = fractional * 29.53;
        return daysIntoCycle;
    }

    //1.875
    public String findPhase(int year, int month, int day) {
        double check = calculateMoonPhaseNum(year, month, day);
        if (check > 27.625 || check < 1.875) {
            return "New Moon";
        } else if (check > 1.875 && check < 5.625) {
            return "Waning Crescent";
        } else if (check > 5.625 && check < 9.375) {
            return "Third Quarter";
        } else if (check > 9.375 && check < 11.25) {
            return "Waning Gibbous";
        } else if (check > 13.125 && check < 16.875) {
            return "Full Moon / Gibbous";
        } else if (check > 16.875 && check < 20.625) {
            return "Waxing Gibbous";
        } else if (check > 20.625 && check < 24.375) {
            return "First Quarter";
        } else if (check > 24.375 && check < 27.625) {
            return "Waxing Crescent";
        } else {
            return "Full Moon / Gibbous";
        }
    }

    public void setMoonPhase(int year, int month, int day) {
        mPhase = findPhase(year, month, day);
    }

    public String getPhase() {
        return mPhase;
    }

    public void setPhase(String phase) {
        mPhase = phase;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moon moon = (Moon) o;
        return (mYear == moon.mYear) && (mMonth == moon.mMonth) &&
                (mDay == moon.mDay) && Objects.equals(mPhase, moon.mPhase);
    }

    public String toString() {
        return "Moon[" + "Phase= " + mPhase + ", Year= " + mYear + ", Month= " + mMonth  + ", Day= " + mDay + ']';
    }
}
