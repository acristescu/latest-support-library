package com.example.bbcplaylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bbcplaylist.model.PlaylistResponse;
import com.example.bbcplaylist.model.Song;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<PlaylistResponse>{

	@BindView(R.id.recycler) RecyclerView mRecycler;

	private List<Song> mSongs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mRecycler.setLayoutManager(new LinearLayoutManager(this));
		mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		asyncFetchData();
	}

	private void asyncFetchData() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://www.bbc.co.uk/radio1/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		BBCRadioService service = retrofit.create(BBCRadioService.class);

		service.getPlaylistResponse().enqueue(this);
	}

	@Override
	public void onResponse(Call<PlaylistResponse> call, Response<PlaylistResponse> response) {
		if(isDestroyed()) {
			return;
		}
		mSongs = response.body().getPlaylist().getSongs();
		mRecycler.setAdapter(new SongListAdapter(mSongs));

		for(Song song : response.body().getPlaylist().getSongs()) {
			System.out.println(song.getTitle());
		}
	}

	@Override
	public void onFailure(Call<PlaylistResponse> call, Throwable t) {
		Log.e("", "Error fetching songs", t);
	}
}
