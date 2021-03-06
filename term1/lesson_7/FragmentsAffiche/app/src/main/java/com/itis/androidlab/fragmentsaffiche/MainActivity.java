package com.itis.androidlab.fragmentsaffiche;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.itis.androidlab.fragmentsaffiche.fragments.FilmChooserFragment;
import com.itis.androidlab.fragmentsaffiche.fragments.FilmDetailsFragment;
import com.itis.androidlab.fragmentsaffiche.models.Film;

public class MainActivity extends AppCompatActivity implements FilmChooserFragment.FilmChooserProcessor {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFilmChosen(Film film) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FilmDetailsFragment.FILM, film);
        filmDetailsFragment.setArguments(bundle);
        if (fragmentManager.findFragmentById(R.id.film_details) != null) {
            ft.replace(R.id.film_details, filmDetailsFragment, "fragment2");
            ft.commit();
        } else {
            ft.add(R.id.film_details, filmDetailsFragment, FilmDetailsFragment.class.getSimpleName());
            ft.commit();
        }
    }
}
