package com.beacheslav.androidcourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beacheslav.androidcourse.POJO.TrumpQuotes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {

    private static final String TAG = "ApiActivity";

    private EditText mEditSearch;
    private Button mSearchButton;
    private RecyclerView mRecyclerView;
    private TrumpQuotes mTrumpQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        mTrumpQuotes = new TrumpQuotes();

        mEditSearch = (EditText) findViewById(R.id.edit_search);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final QuotesAdapter adapter = new QuotesAdapter();
        mRecyclerView.setAdapter(adapter);

        mSearchButton = (Button) findViewById(R.id.button_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mEditSearch.getText().toString();
                if (query.length() == 0){
                    Toast.makeText(ApiActivity.this, "Please enter text and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                App.getApi().getData(query).enqueue(new Callback<TrumpQuotes>() {
                    @Override
                    public void onResponse(Call<TrumpQuotes> call, Response<TrumpQuotes> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            mTrumpQuotes = response.body();
                            adapter.setQuotes(mTrumpQuotes);
                            mRecyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrumpQuotes> call, Throwable t) {
                        Toast.makeText(ApiActivity.this, "An error occurred during networking ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, " " + t);

                    }
                });
            }
        });


    }
}
