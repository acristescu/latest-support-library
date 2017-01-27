package com.example.bbcplaylist;

import com.example.bbcplaylist.model.PlaylistResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by acristescu on 26/01/2017.
 */

public interface BBCRadioService {

	@GET("playlist.json")
	Call<PlaylistResponse> getPlaylistResponse();
}
