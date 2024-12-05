package com.example.planme.ui.base;

import androidx.annotation.NonNull;

import com.kizitonwose.calendar.core.DayPosition;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public final class CalendarDay implements Serializable {

    private final LocalDate date;
    private final DayPosition position;

    public CalendarDay(@NonNull LocalDate date, @NonNull DayPosition position) {
        this.date = Objects.requireNonNull(date, "date cannot be null");
        this.position = Objects.requireNonNull(position, "position cannot be null");
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    @NonNull
    public DayPosition getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarDay that = (CalendarDay) o;
        return date.equals(that.date) && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, position);
    }

    @Override
    public String toString() {
        return "CalendarDay{" +
                "date=" + date +
                ", position=" + position +
                '}';
    }
}
