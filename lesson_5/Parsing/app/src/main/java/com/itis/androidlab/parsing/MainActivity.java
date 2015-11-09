package com.itis.androidlab.parsing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itis.androidlab.parsing.models.VkParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private StringRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));
        ParseJson parseJson = new ParseJson();
        parseJson.execute();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(mAdapter = new StringRecyclerViewAdapter());
    }

    public class ParseJson extends AsyncTask<Void, Void, List<VkPost>> {
        ProgressDialog mProgressDialog;
        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Обработка");
            mProgressDialog.show();
        }

        @Override
        protected List<VkPost> doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
                InputStream postsIS = MainActivity.this.getAssets().open("json/vk_wall.json");
                String s;
                StringBuilder builder = new StringBuilder();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(postsIS));
                    while ((s = bufferedReader.readLine()) != null) {
                        builder.append(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<VkPost> posts = VkParser.parsePosts(new JSONObject(builder.toString()));
                for (VkPost post : posts) {
                    Log.d("POST", post.toString());
                }

                return posts;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<VkPost> values) {
            mAdapter.setValues(values);
            mAdapter.notifyDataSetChanged();
            mProgressDialog.hide();
        }
    }

    public class StringRecyclerViewAdapter
            extends RecyclerView.Adapter<StringRecyclerViewAdapter.ViewHolder> {

        private List<VkPost> mValues;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view;
            }
        }

        public VkPost getValueAt(int position) {
            return mValues.get(position);
        }

        public StringRecyclerViewAdapter() {
            this.mValues = new ArrayList<>();
        }

        public StringRecyclerViewAdapter(List<VkPost> items) {
            this.mValues = items;
        }

        public void setValues(List<VkPost> values) {
            this.mValues = values;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mTextView.setText(mValues.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

}
