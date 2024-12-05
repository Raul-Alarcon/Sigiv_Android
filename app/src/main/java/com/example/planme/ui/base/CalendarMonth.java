package com.example.planme.ui.base;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;

public class CalendarMonth implements Serializable {

    private final YearMonth yearMonth;
    private final List<List<CalendarDay>> weekDays;

    public CalendarMonth(@NonNull YearMonth yearMonth, @NonNull List<List<CalendarDay>> weekDays) {
        this.yearMonth = Objects.requireNonNull(yearMonth, "yearMonth cannot be null");
        this.weekDays = Objects.requireNonNull(weekDays, "weekDays cannot be null");
    }

    @NonNull
    public YearMonth getYearMonth() {
        return yearMonth;
    }

    @NonNull
    public List<List<CalendarDay>> getWeekDays() {
        return weekDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarMonth that = (CalendarMonth) o;

        return yearMonth.equals(that.yearMonth) &&
                Objects.equals(firstDay(), that.firstDay()) &&
                Objects.equals(lastDay(), that.lastDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearMonth, firstDay(), lastDay());
    }

    @Override
    public String toString() {
        return "CalendarMonth { " +
                "yearMonth = " + yearMonth +
                ", firstDay = " + firstDay() +
                ", lastDay = " + lastDay() +
                " }";
    }

    private CalendarDay firstDay() {
        return weekDays.get(0).get(0);
    }

    private CalendarDay lastDay() {
        List<CalendarDay> lastWeek = weekDays.get(weekDays.size() - 1);
        return lastWeek.get(lastWeek.size() - 1);
    }
}
