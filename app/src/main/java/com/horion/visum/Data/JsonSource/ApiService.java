package com.horion.visum.Data.JsonSource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("5f589ac6ad23b57ef90ec567/{fileName}")
    Call<JsonResponse> getJsonFromServer(@Path("fileName") String fileName);
}
