package com.fullana.proyectopokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fullana.proyectopokedex.databinding.ActivityMainBinding;
import com.fullana.proyectopokedex.poqueapi.PokeApiService;
import com.fullana.proyectopokedex.poqueapi.Pokemon;
import com.fullana.proyectopokedex.poqueapi.PokemonTypeColor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;


public class MainActivity extends AppCompatActivity {

    int cantidad=0;
    ActivityMainBinding binding;
    ArrayList<Pokemon> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cargarMasDatos(cantidad, cantidad+30);
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                // Manejar las acciones del menú
                if (id == R.id.ItemPokemons) {
                    Toast.makeText(getApplicationContext(), "Estás Aquí", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (id == R.id.ItemItems) {
                    startActivity(new Intent(getApplicationContext(),ItemActivity.class));
                    return true;
                }
                if (id == R.id.ItemBerrys) {
                    startActivity(new Intent(getApplicationContext(),BerryActivity.class));
                    return true;
                }
                return false;
            }
        });
        AdapterRecyclerPokemon adapter = new AdapterRecyclerPokemon(list, getApplicationContext());
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = binding.recycler.getLayoutManager().getItemCount();
                int lastVisibleItem = ((GridLayoutManager) binding.recycler.getLayoutManager()).findLastVisibleItemPosition();

                int threshold = 5; // Cantidad de elementos antes de llegar al final para cargar más

                if (totalItemCount <= (lastVisibleItem + threshold)) {
                    // Llegaste al final, carga más datos
                    cargarMasDatos(cantidad, cantidad + 30);
                }
            }
        });
    }

    private void cargarMasDatos(int cantidad, int i) {
        llamadaApi(cantidad, i);
        this.cantidad+=30;
    }

    private void llamadaApi(int offset, int limit) {
        for (; offset < limit; offset++)
            pokemons(new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PokeApiService.class), offset);
    }

    public void pokemons(PokeApiService pokeService, int id) {
        Call<Pokemon> pokeCall = pokeService.getPokemonById(String.valueOf(id));
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon foundPoke = response.body();
                if (foundPoke != null) {
                    list.add(foundPoke);
                    if(list.size()%15==0)
                        binding.recycler.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                System.out.println("no se ejecuto");
            }
        });
    }
}

class AdapterRecyclerPokemon extends RecyclerView.Adapter<AdapterRecyclerPokemon.ViewHolderPokemon> {


    private Context context;
    private final ArrayList<Pokemon> list;

    public AdapterRecyclerPokemon(ArrayList<Pokemon> list, Context context) {
        this.list = list;
        this.context = context;
    }


    static class ViewHolderPokemon extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;
        public CardView constraintLayout;

        public ViewHolderPokemon(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePoke);
            textView = itemView.findViewById(R.id.namePoke);
            constraintLayout = itemView.findViewById(R.id.cardView);
        }
    }


    @NonNull
    @Override
    public ViewHolderPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poemon_recycler, parent, false);

        return new ViewHolderPokemon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPokemon holder, int position) {
        construye(list.get(position), holder);
    }

    private void construye(Pokemon pokemon, ViewHolderPokemon holderPokemon) {

        holderPokemon.constraintLayout.setCardBackgroundColor(ContextCompat.getColor(context, PokemonTypeColor.getTypeColor(pokemon.getType(0))));
        holderPokemon.textView.setText(pokemon.getName());
        String imagePoke = pokemon.getSprites();
        Glide.with(context).load(imagePoke).placeholder(R.drawable.placeholder).error(R.drawable.error).into(holderPokemon.imageView);
        Glide.with(context).load(imagePoke)
                .listener(
                        GlidePalette.with(imagePoke).use(BitmapPalette.Profile.MUTED_LIGHT)
                                .intoCallBack(palette -> {
                                    if (palette != null && palette.getDominantSwatch() != null) {
                                        int rgbHexCode = palette.getDominantSwatch().getRgb();
                                        holderPokemon.constraintLayout.setCardBackgroundColor(rgbHexCode);
                                    }
                                }).crossfade(true))
                .into(holderPokemon.imageView);
        Picasso.get().load(pokemon.getSprites()).into(holderPokemon.imageView);
        holderPokemon.itemView.setOnClickListener((l) -> {
            Intent intent = new Intent(context, PokemonDetallado.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("pokemon", pokemon);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}