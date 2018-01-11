package com.gregoriopalama.carservice.ui.cars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gregoriopalama.carservice.data.Car;
import com.gregoriopalama.carservice.databinding.RowCarBinding;
import com.gregoriopalama.carservice.ui.GenericViewHolder;

import java.util.List;

/**
 * Created by gregorio on 09/01/2018.
 */

public class CarListAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private List<Car> items;

    public CarListAdapter(List<Car> items) {
        this.items = items;
    }

    public void setItems(List<Car> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        RowCarBinding binding =
                RowCarBinding.inflate(layoutInflater, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
