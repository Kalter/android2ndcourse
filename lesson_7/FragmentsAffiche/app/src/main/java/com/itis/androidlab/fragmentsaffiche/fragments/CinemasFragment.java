package com.itis.androidlab.fragmentsaffiche.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.androidlab.fragmentsaffiche.R;
import com.itis.androidlab.fragmentsaffiche.models.Cinema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CinemasFragment extends Fragment {

    public static final String FILM_ID = "film_id";

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinemas, container, false);

        initRecyclerView(view);

        Bundle args = getArguments();
        List<Cinema> cinemas;
        if (args != null) {
            Long filmId = args.getLong(FILM_ID);
            cinemas = new ArrayList<>();
            List<Cinema> allCinemas = readCinemasFromJson();
            for (Cinema c : allCinemas) {
                for (Long film : c.getFilms()) {
                    if (film == filmId)
                        cinemas.add(c);
                }

            }
        } else
            cinemas = readCinemasFromJson();

        mRecyclerView.setAdapter(new CinemasRecyclerViewAdapter(cinemas));
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

    }


    public class CinemasRecyclerViewAdapter
            extends RecyclerView.Adapter<CinemasRecyclerViewAdapter.ViewHolder> {

        private List<Cinema> mCinemas;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final TextView mNameTextView;
            public final TextView mAddressTextView;

            public ViewHolder(View view) {
                super(view);
                mNameTextView = (TextView) view.findViewById(R.id.name);
                mAddressTextView = (TextView) view.findViewById(R.id.address);
            }

        }

        public Cinema getValueAt(int position) {
            return mCinemas.get(position);
        }

        public CinemasRecyclerViewAdapter(List<Cinema> items) {
            if (items != null)
                mCinemas = items;
            else
                mCinemas = new ArrayList<>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cinemas_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Cinema cinema = getValueAt(position);
            holder.mNameTextView.setText(cinema.getName());
            holder.mAddressTextView.setText(cinema.getAddress());
        }

        @Override
        public int getItemCount() {
            return mCinemas.size();
        }
    }

    @Nullable
    private List<Cinema> readCinemasFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cinema.CinemasArray cinemasArray = mapper.readValue(getActivity().getAssets().open("cinemas.json"),
                    new TypeReference<Cinema.CinemasArray>() {
                    });
            return cinemasArray.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
