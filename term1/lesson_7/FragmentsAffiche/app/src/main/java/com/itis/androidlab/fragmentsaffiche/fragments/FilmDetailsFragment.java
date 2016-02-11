package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Film;

public class FilmDetailsFragment extends Fragment {

    public static final String FILM = "film";

    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;
    private TextView mDirectorTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_details, container, false);
        initViews(view);

        Bundle args = getArguments();
        if (args != null)
            setFilm((Film) args.getParcelable(FILM));

        return view;
    }

    private void initViews(View view) {
        mTitleTextView = (TextView) view.findViewById(R.id.film_title);
        mDateTextView = (TextView) view.findViewById(R.id.film_date);
        mDescriptionTextView = (TextView) view.findViewById(R.id.film_description);
        mDirectorTextView = (TextView) view.findViewById(R.id.film_director);
    }

    public void setFilm(Film film) {
        mTitleTextView.setText(film.getTitle());
        mDateTextView.setText(film.getDate());
        mDescriptionTextView.setText(film.getDescription());
        mDirectorTextView.setText(String.format(getResources().getString(R.string.film_director), film.getDirector()));
    }
}
