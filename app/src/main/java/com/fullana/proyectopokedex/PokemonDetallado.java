package com.fullana.proyectopokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.fullana.proyectopokedex.ViewModels.VMPokemonDetallado;
import com.fullana.proyectopokedex.databinding.ActivityPokemonDetalladoBinding;
import com.fullana.proyectopokedex.poqueapi.Pokemon;
import com.fullana.proyectopokedex.poqueapi.Type;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.squareup.picasso.Picasso;

public class PokemonDetallado extends AppCompatActivity {

    private ActivityPokemonDetalladoBinding binding;
    private VMPokemonDetallado model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonDetalladoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(VMPokemonDetallado.class);
        configuraModel();
        createLayout(getIntent().getExtras().getSerializable("pokemon", Pokemon.class));
    }

    private void configuraModel() {

        model.getHeight().observe(this,(h)->binding.height.setText(""+h));
        model.getWeight().observe(this,(w)-> binding.weight.setText(""+w));
        model.getName().observe(this,name->binding.namePoke.setText(name));
        model.getImage().observe(this, imagePoke -> {
            Glide.with(getApplicationContext()).load(imagePoke).placeholder(R.drawable.placeholder).error(R.drawable.error).into(binding.imagePoke);
            Glide.with(getApplicationContext()).load(imagePoke)
                    .listener(
                            GlidePalette.with(imagePoke).use(BitmapPalette.Profile.MUTED_LIGHT)
                                    .intoCallBack(palette -> {
                                        if (palette != null && palette.getDominantSwatch() != null) {
                                            int rgbHexCode = palette.getDominantSwatch().getRgb();
                                            binding.cardView.setCardBackgroundColor(rgbHexCode);
                                        }
                                    }).crossfade(true))
                    .into(binding.imagePoke);
            Picasso.get().load(imagePoke).into(binding.imagePoke);
        });
        model.getAtk().observe(this, (aFloat) -> {
            binding.progressAtk.setLabelText(aFloat.intValue() + "/" + Pokemon.MAXATK);
            binding.progressAtk.setProgress(aFloat);
        });
        model.getHp().observe(this, (hp) -> {
            binding.progressHp.setLabelText(hp.intValue() + "/" + Pokemon.MAXHP);
            binding.progressHp.setProgress(hp);
        });
        model.getSpd().observe(this, (spd) -> {
            binding.progressSpd.setLabelText(spd.intValue() + "/" + Pokemon.MAXSPD);
            binding.progressSpd.setProgress(spd);
        });
        model.getDef().observe(this, (def) -> {
            binding.progressDef.setLabelText(def.intValue() + "/" + Pokemon.MAXDEF);
            binding.progressDef.setProgress(def);
        });
        model.getExp().observe(this, (exp) -> {
            binding.progressExp.setLabelText(exp.intValue() + "/" + Pokemon.MAXEXP);
            binding.progressExp.setProgress(exp);
        });
        model.getTypes().observe(this,pokemonTypes -> {
            binding.typeList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            binding.typeList.setAdapter(new RecyclerList(pokemonTypes, getApplicationContext(), new DiffUtil.ItemCallback<Type>() {
                @Override
                public boolean areItemsTheSame(@NonNull Type oldItem, @NonNull Type newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Type oldItem, @NonNull Type newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            }));
        });
        binding.arrow.setOnClickListener((l)-> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }

    private void createLayout(Pokemon pokemon) {
        model.getWeight().setValue(pokemon.getWeight());
        model.getHeight().setValue(pokemon.getHeight());
        model.getName().setValue(pokemon.getName());
        model.getImage().setValue(pokemon.getSprites());
        model.getTypes().setValue(pokemon.getTypes());
        model.getAtk().setValue(Pokemon.createStats(Pokemon.DEFAULT_ATK, Pokemon.MAXATK));
        model.getDef().setValue(Pokemon.createStats(Pokemon.DEFAULT_DEF, Pokemon.MAXDEF));
        model.getExp().setValue(Pokemon.createStats(Pokemon.DEFAULT_EXP, Pokemon.MAXEXP));
        model.getSpd().setValue(Pokemon.createStats(Pokemon.DEFAULT_SPD, Pokemon.MAXSPD));
        model.getHp().setValue(Pokemon.createStats(Pokemon.DEFAULT_HP, Pokemon.MAXHP));
    }
}