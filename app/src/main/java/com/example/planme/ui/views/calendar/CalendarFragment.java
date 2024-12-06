package com.example.planme.ui.views.calendar;

import static com.kizitonwose.calendar.core.ExtensionsKt.daysOfWeek;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planme.R;
import com.example.planme.data.models.Task;
import com.example.planme.databinding.CalendarDayBinding;
import com.example.planme.databinding.CalendarHeaderBinding;
import com.example.planme.databinding.FragmentCalendarBinding;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.adapters.RVFlightAdapter;
import com.example.planme.ui.models.FlightUI;
import com.example.planme.ui.views.home.HomeViewModel;
import com.example.planme.utils.DateFormatHelper;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class CalendarFragment extends Fragment{

    private FragmentCalendarBinding binding;
    private HomeViewModel homeViewModel;
    NavController navController; private LocalDate selectedDate;
    private List<FlightUI> flights = new ArrayList<>();
    private RVFlightAdapter flightAdapter;
    CalendarViewModel calendarViewModel;

    private Map<LocalDate, List<FlightUI>> taskMap = new HashMap<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        calendarViewModel = new ViewModelProvider(this)
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

        flightAdapter.setOnClickListener( flightUI -> {
            this.showEditEventDialog(selectedDate);
        });


        // Configuración del ItemTouchHelper para swipe
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // No es necesario mover ítems
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Obtén el evento que fue deslizado
                FlightUI event = flightAdapter.getFlights().get(viewHolder.getAdapterPosition());
                deleteEvent(event); // Llama a la función que elimina el evento
            }
        };

        // Asocia el ItemTouchHelper al RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.exFiveRv);

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
                updateAdapterForDate(selectedDate); // Actualiza el adaptador (esto depende de tu implementación específica)
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

        binding.exThreeAddButton.setOnClickListener(v -> {
            if (selectedDate != null) {
                showAddEventDialog(selectedDate);
            } else {
                // Mostrar un mensaje si no se ha seleccionado una fecha
                Toast.makeText(getContext(), "Por favor, selecciona una fecha primero", Toast.LENGTH_SHORT).show();
            }
        });

        calendarViewModel.getDataFlight().observe(getViewLifecycleOwner(),flightUIS -> {
            flightAdapter.setFlights(flightUIS);

            Map<LocalDate, List<FlightUI>> _taskMap = new HashMap<>();

            flightUIS.forEach( f -> {
                LocalDate formatter = DateFormatHelper.stringToLocalDate(f.getTime());
                _taskMap.put(formatter, Collections.singletonList(f));

                binding.exFiveCalendar.notifyDateChanged(formatter);
            });

            taskMap = _taskMap;
        });

        if (!calendarViewModel.taskDataCache.isEmpty()) {
            flightAdapter.setFlights(calendarViewModel.taskDataCache);
            calendarViewModel.taskDataCache.forEach( flightUI -> {
                LocalDate dateFormatter = DateFormatHelper.stringToLocalDate(flightUI.getTime());
                taskMap.put(dateFormatter, Collections.singletonList(flightUI));
            });
        }

        if (!flightAdapter.getFlights().isEmpty()) {
            LocalDate dd = selectedDate;
            flightAdapter.notifyDataSetChanged();
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showAddEventDialog(LocalDate selectedDate) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_event, null);

        final EditText titleEditText = dialogView.findViewById(R.id.editTextTitle);
        final EditText descriptionEditText = dialogView.findViewById(R.id.editTextDescription);

        new AlertDialog.Builder(getContext())
                .setTitle("Agregar Evento")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {

                    String title = titleEditText.getText().toString().trim();
                    String description = descriptionEditText.getText().toString().trim();

                    if (!title.isEmpty() && !description.isEmpty()) {
                        saveEvent(title, description);

                        // Limpiar los campos
                        titleEditText.setText("");
                        descriptionEditText.setText("");

                        // Actualizar el adaptador para mostrar el nuevo evento
                        updateAdapterForDate(selectedDate);
                    } else {
                        Toast.makeText(getContext(), "Por favor ingresa un título y una descripción", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showEditEventDialog(LocalDate selectedDate) {
        List<FlightUI> eventList = taskMap.getOrDefault(selectedDate, new ArrayList<>());
        // Si hay eventos en ese día, permitir editar el primero (o el que elijas)
        if (!eventList.isEmpty()) {
            FlightUI event = eventList.get(0); // Aquí puedes personalizar la lógica para elegir un evento
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_event, null);

            final EditText titleEditText = dialogView.findViewById(R.id.editTextTitle);
            final EditText descriptionEditText = dialogView.findViewById(R.id.editTextDescription);

            // Rellenar los campos con los valores actuales
            titleEditText.setText(event.getTopic());
            descriptionEditText.setText(event.getTxt());

            new AlertDialog.Builder(getContext())
                    .setTitle("Editar Evento")
                    .setView(dialogView)
                    .setPositiveButton("Guardar", (dialog, which) -> {
                        // Guardar los cambios
                        String newTitle = titleEditText.getText().toString().trim();
                        String newDescription = descriptionEditText.getText().toString().trim();
                        if (!newTitle.isEmpty() && !newDescription.isEmpty()) {
                            event.setTopic(newTitle);
                            event.setTxt(newDescription);
                            calendarViewModel.UpdateTask(event).thenAccept(isUpdated ->{
                                if (isUpdated){
                                    updateAdapterForDate(selectedDate);  // Actualizar la lista con los cambios
                                }else {
                                    Toast.makeText(getContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(getContext(), "Por favor ingresa un título y una descripción", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveEvent(String Title, String text) {
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "Las cajas no pueden estar vacias", Toast.LENGTH_LONG).show();
        } else {
            if (selectedDate != null) {
                String dateFormatter = DateFormatHelper.localDateToString(selectedDate);
                FlightUI task = new FlightUI(UUID.randomUUID().toString(), Title, text, dateFormatter);
                calendarViewModel.AddDataTask(task).thenAccept(isAdd -> {
                    if (isAdd){
                        List<FlightUI> eventList = taskMap.getOrDefault(selectedDate, new ArrayList<>());
                        eventList.add(task);
                        taskMap.put(selectedDate, eventList);
                        updateAdapterForDate(selectedDate);
                    }else {
                        Toast.makeText(getContext(), "La Tarea no pudo ser agregada", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        binding.exFiveCalendar.notifyDateChanged(selectedDate);
    }

    private void updateAdapterForDate(LocalDate date) {
        flightAdapter.getFlights().clear();
        if (date != null && taskMap.containsKey(date)) {
            flightAdapter.getFlights().addAll(taskMap.get(date));
        }
        flightAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void deleteEvent(FlightUI event) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar este evento?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Eliminar el evento si el usuario confirma
                    LocalDate date = DateFormatHelper.stringToLocalDate(event.getTime());
                    List<FlightUI> eventList = taskMap.getOrDefault(date, new ArrayList<>());
                    calendarViewModel.DeleteTask(event).thenAccept(isDeleted ->{
                        if (isDeleted){
                            eventList.remove(event);
                            taskMap.put(date, eventList);
                            updateAdapterForDate(date);  // Actualizar el adaptador para reflejar los cambios
                            Toast.makeText(getContext(), "Evento eliminado", Toast.LENGTH_SHORT).show(); // Mensaje de éxito
                        }else {
                            Toast.makeText(getContext(), "Evento no seleccionado", Toast.LENGTH_SHORT).show();
                        }
                    });

                })
                .setNegativeButton("No", null)  // Si el usuario cancela, no hacer nada
                .show();
    }


    private void configureBinders(List<DayOfWeek> daysOfWeek) {
        // Clase para manejar las vistas del día
        class DayViewContainer extends ViewContainer {
            com.kizitonwose.calendar.core.CalendarDay day; // Será configurado cuando este contenedor sea enlazado.
            CalendarDayBinding binding;

            DayViewContainer(View view) {
                super(view);
                binding = CalendarDayBinding.bind(view);

                view.setOnLongClickListener(v -> {
                    if (day.getPosition() == DayPosition.MonthDate) {
                        // Lógica para editar el evento
                        showEditEventDialog(day.getDate());
                        return true; // Indica que el evento ha sido consumido
                    }
                    return false;
                });
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
                        layout.setBackgroundResource(R.drawable.selected_bg);
                        binding.exThreeSelectedDateText.setText(calendarDay.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));// Estilo para el día actual
                    } else if (selectedDate != null && selectedDate.equals(calendarDay.getDate())) {
                        binding.exThreeSelectedDateText.setText(calendarDay.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
                        layout.setBackgroundResource(R.drawable.selected_bg); // Estilo para el día seleccionado
                    } else {
                        layout.setBackgroundResource(0);
                    }
                    List<FlightUI> flightsForDay = taskMap.get(calendarDay.getDate());
                    if (flightsForDay != null && !flightsForDay.isEmpty()) {
                        // Resolver colores desde atributos de tema
                        context.getTheme().resolveAttribute(R.attr.primaryButton, typedValue, true);
                        int primaryTextColor = typedValue.data;

                        context.getTheme().resolveAttribute(R.attr.utilBrown, typedValue, true);
                        int utilBrown = typedValue.data;

                        // Aplicar colores a las barras
                        flightTopView.setBackgroundColor(primaryTextColor);
                        flightBottomView.setBackgroundColor(utilBrown);
                    }
                } else {
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