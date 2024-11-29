package com.example.planme.ui.views.calendar;

import static com.kizitonwose.calendar.core.ExtensionsKt.daysOfWeek;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.planme.R;
import com.example.planme.databinding.CalendarDayBinding;
import com.example.planme.databinding.CalendarHeaderBinding;
import com.example.planme.databinding.FragmentCalendarBinding;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.adapters.RVFlightAdapter;
import com.example.planme.ui.models.FlightUI;
import com.example.planme.ui.views.home.HomeViewModel;
import com.kizitonwose.calendar.core.CalendarDay;
import com.kizitonwose.calendar.core.CalendarMonth;
import com.kizitonwose.calendar.core.DayPosition;
import com.kizitonwose.calendar.view.MonthDayBinder;
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder;
import com.kizitonwose.calendar.view.ViewContainer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment{

    private FragmentCalendarBinding binding;
    private HomeViewModel homeViewModel;
    NavController navController; private LocalDate selectedDate;
    private List<FlightUI> flights = new ArrayList<>();
    private RVFlightAdapter flightAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        CalendarViewModel calendarViewModel = new ViewModelProvider(this)
                .get(CalendarViewModel.class);
        this.binding = FragmentCalendarBinding.inflate(inflater, container, false);

        return this.binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        YearMonth currentMonth = YearMonth.now();
        YearMonth startMonth = currentMonth;
        YearMonth endMonth = currentMonth.plusMonths(200);
        List<DayOfWeek> daysOfWeek = daysOfWeek();


        flightAdapter = new RVFlightAdapter();
        binding.exFiveRv.setAdapter(flightAdapter);

        DayOfWeek day  = daysOfWeek.get(0);
        configureBinders(daysOfWeek);
        binding.exFiveCalendar.setup(startMonth, endMonth, day);
       //binding.exFiveCalendar.scrollToMonth(currentMonth);


        binding.exFiveCalendar.setMonthScrollListener(month -> {
            YearMonth currentYearMonth = month.getYearMonth();
            binding.exFiveMonthYearText.setText(formatMonthYear(currentYearMonth));

            if (selectedDate != null) {
                LocalDate previousSelectedDate = selectedDate;
                selectedDate = null; // Limpia la selección actual
                binding.exFiveCalendar.notifyDateChanged(previousSelectedDate); // Notifica al calendario para actualizar la vista
                //updateAdapterForDate(null); // Actualiza el adaptador (esto depende de tu implementación específica)
            }

            return null; // Devuelve null ya que no necesitamos devolver un valor específico
        });

        // Botón para navegar al próximo mes
        binding.exFiveNextMonthImage.setOnClickListener(v -> {
            com.kizitonwose.calendar.core.CalendarMonth firstVisibleMonth = binding.exFiveCalendar.findFirstVisibleMonth();
            if (firstVisibleMonth != null) {
                binding.exFiveCalendar.smoothScrollToMonth(firstVisibleMonth.getYearMonth().plusMonths(1));
            }
        });

// Botón para navegar al mes anterior
        binding.exFivePreviousMonthImage.setOnClickListener(v -> {
            com.kizitonwose.calendar.core.CalendarMonth firstVisibleMonth = binding.exFiveCalendar.findFirstVisibleMonth();
            if (firstVisibleMonth != null) {
                binding.exFiveCalendar.smoothScrollToMonth(firstVisibleMonth.getYearMonth().minusMonths(1));
            }
        });


    }

    private void updateAdapterForDate(LocalDate date) {
        flightAdapter.getFlights().clear();
       /* if (date != null && flights.containsKey(date)) {
           // flightAdapter.getFlights().addAll(flights.get(date));
        }*/
        flightAdapter.notifyDataSetChanged();
    }


    private void configureBinders(List<DayOfWeek> daysOfWeek) {
        // Clase para manejar las vistas del día
        class DayViewContainer extends ViewContainer {
            com.kizitonwose.calendar.core.CalendarDay day; // Será configurado cuando este contenedor sea enlazado.
            CalendarDayBinding binding;

            DayViewContainer(View view) {
                super(view);
                binding = CalendarDayBinding.bind(view);

                view.setOnClickListener(v -> {
                    if (day.getPosition() == DayPosition.MonthDate) {
                        if (!day.getDate().equals(selectedDate)) {
                            LocalDate oldDate = selectedDate;
                            selectedDate = day.getDate();
                            FragmentCalendarBinding binding = CalendarFragment.this.binding;
                            binding.exFiveCalendar.notifyDateChanged(day.getDate());
                            if (oldDate != null) {
                                binding.exFiveCalendar.notifyDateChanged(oldDate);
                            }
                            updateAdapterForDate(day.getDate());
                        }
                    }
                });
            }
        }

        // Configuración del dayBinder
        binding.exFiveCalendar.setDayBinder(new MonthDayBinder<DayViewContainer>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void bind(@NonNull DayViewContainer container, CalendarDay calendarDay) {
                LocalDate today = LocalDate.now();
                container.day = calendarDay;
                Context context = container.binding.getRoot().getContext();
                TextView textView = container.binding.exFiveDayText;
                View layout = container.binding.exFiveDayLayout;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));
                }

                View flightTopView = container.binding.exFiveDayFlightTop;
                View flightBottomView = container.binding.exFiveDayFlightBottom;

                flightTopView.setBackground(null);
                flightBottomView.setBackground(null);

                if (calendarDay.getPosition() == DayPosition.MonthDate) {
                    TypedValue typedValue = new TypedValue();
                    context.getTheme().resolveAttribute(R.attr.primaryButton, typedValue, true);
                    int color = typedValue.data;
                    textView.setTextColor(color);
                    layout.setBackgroundResource(
                            selectedDate != null && selectedDate.equals(calendarDay.getDate())
                                    ? R.drawable.selected_bg
                                    : 0
                    );

                    if (calendarDay.getDate().equals(today)) {
                        layout.setBackgroundResource(R.drawable.selected_bg); // Estilo para el día actual
                    } else if (selectedDate != null && selectedDate.equals(calendarDay.getDate())) {
                        layout.setBackgroundResource(R.drawable.selected_bg); // Estilo para el día seleccionado
                    } else {
                        layout.setBackgroundResource(0);
                    }

                   /* List<FlightUI> flights = flights.get(calendarDay.getDate());
                    if (flights != null) {
                        if (flights.size() == 1) {
                            flightBottomView.setBackgroundColor(context.getColorCompat(flights.get(0).getColor()));
                        } else {
                            flightTopView.setBackgroundColor(context.getColorCompat(flights.get(0).getColor()));
                            flightBottomView.setBackgroundColor(context.getColorCompat(flights.get(1).getColor()));
                        }
                    }*/
                } else {
                   /* textView.setTextColorRes(R.color.example_5_text_grey_light);*/
                    layout.setBackground(null);
                }

            }

            @NonNull
            @Override
            public DayViewContainer create(@NonNull View view) {
                return new DayViewContainer(view);
            }
        });

        // Clase para manejar las vistas del encabezado del mes
        class MonthViewContainer extends ViewContainer {
            ViewGroup legendLayout;

            MonthViewContainer(View view) {
                super(view);
                legendLayout = CalendarHeaderBinding.bind(view).legendLayout.getRoot();
            }
        }

        Typeface typeface = Typeface.create("sans-serif-light", Typeface.NORMAL);

        // Configuración del monthHeaderBinder
        binding.exFiveCalendar.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthViewContainer>() {

            @Override
            public void bind(@NonNull MonthViewContainer container, CalendarMonth calendarMonth) {
                // Configuración del encabezado del mes si aún no está configurado
                if (container.legendLayout.getTag() == null) {
                    container.legendLayout.setTag(true);
                    for (int i = 0; i < daysOfWeek.size(); i++) {
                        TextView textView = (TextView) container.legendLayout.getChildAt(i);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            textView.setText(daysOfWeek.get(i)
                                    .getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase());
                        }
                        //textView.setTextColorRes(R.color.white);
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
                        textView.setTypeface(typeface);
                    }
                }

            }

            @NonNull
            @Override
            public MonthViewContainer create(@NonNull View view) {
                return new MonthViewContainer(view);

            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String formatMonthYear(YearMonth yearMonth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return yearMonth.format(formatter);
    }

}