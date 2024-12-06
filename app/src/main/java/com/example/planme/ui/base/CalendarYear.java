package com.example.planme.ui.base;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

public class CalendarYear implements Serializable {

    private final Year year;
    private final List<CalendarMonth> months;

    public CalendarYear(@NonNull Year year, @NonNull List<CalendarMonth> months) {
        this.year = Objects.requireNonNull(year, "year cannot be null");
        this.months = Objects.requireNonNull(months, "months cannot be null");
    }

    @NonNull
    public Year getYear() {
        return year;
    }

    @NonNull
    public List<CalendarMonth> getMonths() {
        return months;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarYear that = (CalendarYear) o;

        return year.equals(that.year) &&
                Objects.equals(firstMonth(), that.firstMonth()) &&
                Objects.equals(lastMonth(), that.lastMonth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, firstMonth(), lastMonth());
    }

    @Override
    public String toString() {
        return "CalendarYear { " +
                "year = " + year +
                ", firstMonth = " + firstMonth() +
                ", lastMonth = " + lastMonth() +
                " }";
    }

    private CalendarMonth firstMonth() {
        return months.get(0);
    }

    private CalendarMonth lastMonth() {
        return months.get(months.size() - 1);
    }
}
