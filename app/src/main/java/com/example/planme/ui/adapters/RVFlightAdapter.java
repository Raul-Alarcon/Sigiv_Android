package com.example.planme.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.databinding.EventItemViewBinding;
import com.example.planme.ui.base.OnClickListener;
import com.example.planme.ui.models.FlightUI;
import com.example.planme.utils.DateFormatHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RVFlightAdapter extends RecyclerView.Adapter<RVFlightAdapter.Example5FlightsViewHolder>{

    private List<FlightUI> flights = new ArrayList<>();
    private onClickListener listener;

    public List<FlightUI> getFlights(){
        return this.flights;
    }
    public void setOnClickListener(onClickListener listener){
        this.listener = listener;
    }

    public interface onClickListener {
        void onClick(FlightUI flightUI);
    }

    @NonNull
    @Override
    public Example5FlightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventItemViewBinding binding = EventItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Example5FlightsViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Example5FlightsViewHolder holder, int position) {

        FlightUI flightUI = flights.get(position);

        holder.itemView.setOnClickListener( __ -> {
            if (this.listener != null){
                this.listener.onClick(flightUI);
            }
        });

        holder.bind(flightUI);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class Example5FlightsViewHolder extends RecyclerView.ViewHolder {
        private EventItemViewBinding binding;

        public Example5FlightsViewHolder(EventItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(FlightUI flight) {
            binding.itemTaskDateText.setText(flight.getTime().format(DateTimeFormatter.ofPattern("EEE \nMMM dd")));
            binding.itemTopicText.setText(flight.getTopic());
            binding.itemTaskText.setText(flight.getTxt());


        }
    }
}

