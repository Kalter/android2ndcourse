package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Film;

import java.io.IOException;
import java.util.List;

public class FilmChooserFragment extends Fragment implements View.OnClickListener {

    private List<Film> mFilms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_chooser, container, false);
        mFilms = readFilmsFromJson();
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        Button film1 = (Button) view.findViewById(R.id.film1);
        film1.setText(mFilms.get(0).getTitle());
        film1.setOnClickListener(this);

        Button film2 = (Button) view.findViewById(R.id.film2);
        film2.setText(mFilms.get(1).getTitle());
        film2.setOnClickListener(this);

        Button cinemas = (Button) view.findViewById(R.id.cinemas);
        cinemas.setOnClickListener(this);

    }

    private List<Film> readFilmsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Film.FilmArray filmArray = mapper.readValue(getActivity().getAssets().open("films.json"),
                    new TypeReference<Film.FilmArray>() {
                    });
            return filmArray.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        Film film = null;
        switch (v.getId()) {
            case R.id.film1:
                film = mFilms.get(0);
                break;
            case R.id.film2:
                film = mFilms.get(1);
                break;
        }
        FilmChooserProcessor listener = (FilmChooserProcessor) getActivity();
        if (film != null)
            listener.onFilmChosen(film);
        else
            listener.showCinemas();
    }

    public interface FilmChooserProcessor {
        void onFilmChosen(Film film);
        void showCinemas();
    }
}
