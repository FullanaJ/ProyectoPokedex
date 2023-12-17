package com.fullana.proyectopokedex;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fullana.proyectopokedex.databinding.ActivityMainBinding;
import com.fullana.proyectopokedex.poqueapi.Item;
import com.fullana.proyectopokedex.poqueapi.PokeApiService;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Item> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        llamadaApi(0, 150);
        binding.recycler.setAdapter(new AdapterRecyclerItem(list, getApplicationContext()));
        binding.recycler.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
    }

    private void llamadaApi(int offset, int limit) {
        for (; offset < limit; offset++)
            berrys(new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PokeApiService.class), offset);
    }

    public void berrys(PokeApiService pokeService, int id) {
        Call<Item> pokeCall = pokeService.getItemByID(String.valueOf(id));
        pokeCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item foundPoke = response.body();
                if (foundPoke != null) {
                    list.add(foundPoke);
                    binding.recycler.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
            }
        });
    }
}

class AdapterRecyclerItem extends RecyclerView.Adapter<AdapterRecyclerItem.ViewHolderItem> {


    private Context context;
    private final ArrayList<Item> list;

    public AdapterRecyclerItem(ArrayList<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }


    static class ViewHolderItem extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public CardView constraintLayout;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePoke);
            textView = itemView.findViewById(R.id.namePoke);
            constraintLayout = itemView.findViewById(R.id.cardView);
        }
    }


    @NonNull
    @Override
    public AdapterRecyclerItem.ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poemon_recycler, parent, false);

        return new AdapterRecyclerItem.ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {
        construye(list.get(position), holder);
    }


    private void construye(Item item, ViewHolderItem holderPokemon) {
        holderPokemon.textView.setText(item.getName());

        String mydefault = item.sprites.getMydefault();
        Glide.with(context).load(mydefault).placeholder(R.drawable.placeholder).error(R.drawable.error).into(holderPokemon.imageView);
        Glide.with(context).load(mydefault)
                .listener(
                        GlidePalette.with(mydefault).use(BitmapPalette.Profile.MUTED_LIGHT)
                                .intoCallBack(palette -> {
                                    if (palette != null && palette.getDominantSwatch() != null) {
                                        int rgbHexCode = palette.getDominantSwatch().getRgb();
                                        holderPokemon.constraintLayout.setCardBackgroundColor(rgbHexCode);
                                    }
                                }).crossfade(true))
                .into(holderPokemon.imageView);
        Picasso.get().load(mydefault).into(holderPokemon.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

