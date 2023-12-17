package com.fullana.proyectopokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fullana.proyectopokedex.databinding.TypeItemBinding;
import com.fullana.proyectopokedex.poqueapi.PokemonTypeColor;
import com.fullana.proyectopokedex.poqueapi.PokemonTypes;
import com.fullana.proyectopokedex.poqueapi.Type;

import java.util.ArrayList;
import java.util.List;

public class RecyclerList extends ListAdapter<Type, RecyclerList.RecyclerViewViewHolder> {

    private final Context context;
    private ArrayList<PokemonTypes> typeList ;

    public RecyclerList(List<PokemonTypes> types, Context context, @NonNull DiffUtil.ItemCallback<Type> diffItemCallback) {
        super(diffItemCallback);
        this.typeList = new ArrayList<>(types);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TypeItemBinding typeItemBinding = TypeItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new RecyclerViewViewHolder(typeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        Type item = typeList.get(position).getType();

        String typePoke = item.getName();
        holder.typeItemBinding.typePoke.setText(typePoke);
        holder.typeItemBinding.cardView.setCardBackgroundColor(context.getColor(PokemonTypeColor.getTypeColor(typePoke)));
    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        private final TypeItemBinding typeItemBinding;

        public RecyclerViewViewHolder(TypeItemBinding typeItemBinding) {
            super(typeItemBinding.getRoot());
            this.typeItemBinding = typeItemBinding;
        }
    }
}
