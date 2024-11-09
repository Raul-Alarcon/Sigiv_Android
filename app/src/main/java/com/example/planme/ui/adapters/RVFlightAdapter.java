package com.example.planme.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planme.databinding.Example5EventItemViewBinding;
import com.example.planme.ui.models.FlightUI;
import com.example.planme.utils.DateFormatHelper;

import java.util.ArrayList;
import java.util.List;

public class RVFlightAdapter extends RecyclerView.Adapter<RVFlightAdapter.Example5FlightsViewHolder>{

    private List<FlightUI> flights = new ArrayList<>();

    @NonNull
    @Override
    public Example5FlightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Example5EventItemViewBinding binding = Example5EventItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Example5FlightsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Example5FlightsViewHolder holder, int position) {
        holder.bind(flights.get(position));
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class Example5FlightsViewHolder extends RecyclerView.ViewHolder {
        private Example5EventItemViewBinding binding;

        public Example5FlightsViewHolder(Example5EventItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FlightUI flight) {
            binding.itemFlightDateText.setText(DateFormatHelper.format(flight.getTime(), "EEE'\n'dd MMM'\n'HH:mm"));
            binding.itemFlightDateText.setBackgroundColor(itemView.getContext().getColor(flight.getColor()));

            binding.itemDepartureAirportCodeText.setText(flight.getDeparture().getCode());
            binding.itemDepartureAirportCityText.setText(flight.getDeparture().getCity());

            binding.itemDestinationAirportCodeText.setText(flight.getDestination().getCode());
            binding.itemDestinationAirportCityText.setText(flight.getDestination().getCity());
        }
    }
}

