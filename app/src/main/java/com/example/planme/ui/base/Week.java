package com.example.planme.ui.base;

import com.kizitonwose.calendar.core.WeekDay;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Week implements Serializable {
    private final List<WeekDay> days;

    public Week(List<WeekDay> days) {
        this.days = days;
    }

    public List<WeekDay> getDays() {
        return days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Week week = (Week) o;
        return Objects.equals(days.get(0), week.days.get(0)) &&
                Objects.equals(days.get(days.size() - 1), week.days.get(week.days.size() - 1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(days.get(0), days.get(days.size() - 1));
    }

    @Override
    public String toString() {
        return "Week { " +
                "first = " + days.get(0) + ", " +
                "last = " + days.get(days.size() - 1) +
                " } ";
    }
}
