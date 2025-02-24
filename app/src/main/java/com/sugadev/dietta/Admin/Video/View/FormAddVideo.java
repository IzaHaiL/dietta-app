package com.sugadev.dietta.Admin.Video.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sugadev.dietta.API.BaseUrlConfig;
import com.sugadev.dietta.API.JsonPlaceHolderAPI;
import com.sugadev.dietta.R;
import com.sugadev.dietta.User.Video.Model.Video;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormAddVideo extends AppCompatActivity {

    EditText etUrl, etThumbnail, etTitle, etDesc, etCategory;

    String title, description, url, thumbnail, category;

    Retrofit retrofit;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_video);

        initialization();
        getData();

    }

    private void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlConfig.BASE_URL_VIDEO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

    }

    private void initialization(){
        etUrl = findViewById(R.id.etVideoUrl);
        etThumbnail = findViewById(R.id.etThumbnail);
        etTitle = findViewById(R.id.etTitleVid);
        etDesc = findViewById(R.id.etDeskripsiVid);
        etCategory = findViewById(R.id.etCategory);
    }

    public void tambahVideo(View view) {
        url = etUrl.getText().toString();
        thumbnail = etThumbnail.getText().toString();
        title = etTitle.getText().toString();
        description = etDesc.getText().toString();
        category = etCategory.getText().toString();

        Video video = new Video(0, title, description, category, url, thumbnail);

        Call<Video> call = jsonPlaceHolderAPI.addVideo(video);

        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(FormAddVideo.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(FormAddVideo.this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });
    }
}