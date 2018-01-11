package com.gregoriopalama.carservice.ui;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gregoriopalama.carservice.BR;

/**
 * Created by gregorio on 09/01/2018.
 */

public class GenericViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;
    public GenericViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        binding.setVariable(BR.obj, obj);
        binding.executePendingBindings();
    }
}


