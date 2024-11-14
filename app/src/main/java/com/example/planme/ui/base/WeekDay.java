package com.example.planme.ui.base;

import com.kizitonwose.calendar.core.WeekDayPosition;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class WeekDay implements Serializable {
    private final LocalDate date;
    private final WeekDayPosition position;

    public WeekDay(LocalDate date, WeekDayPosition position) {
        this.date = date;
        this.position = position;
    }

    public LocalDate getDate() {
        return date;
    }

    public WeekDayPosition getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeekDay weekDay = (WeekDay) o;
        return Objects.equals(date, weekDay.date) &&
                position == weekDay.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, position);
    }

    @Override
    public String toString() {
        return "WeekDay{" +
                "date=" + date +
                ", position=" + position +
                '}';
    }
}
