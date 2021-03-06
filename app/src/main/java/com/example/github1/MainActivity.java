package com.example.github1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GithubService githubService = GithubService.retrofit.create(GithubService.class);
                final Call<List<Contributor>> call =
                        githubService.repoContributors("square", "picasso");

                call.enqueue(new Callback<List<Contributor>>() {
                    @Override
                    public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                        final TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Contributor>> call, Throwable throwable) {
                        final TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText("Что-то пошло не так: " + throwable.getMessage());
                    }
                });
            }
        });
    }
}