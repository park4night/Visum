package com.horion.visum.Domain.UseCases;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.horion.visum.Data.Database;
import com.horion.visum.Data.Entities.SubjectEntity;
import com.horion.visum.Data.JsonSource.ApiService;
import com.horion.visum.Data.JsonSource.JsonResponse;
import com.horion.visum.Data.JsonSource.RetrofitClient;
import com.horion.visum.Data.SubjectOperations.SubjectDao;
import com.horion.visum.Domain.Model.Subject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class UpdateFromServerUseCase {

    Retrofit retrofit;
    ApiService apiService;
    Context context;
    SubjectDao subjectDao;
    JsonResponse result;

    public UpdateFromServerUseCase(Context context) {
        this.context = context;
        subjectDao = Database.getDatabase(context).subjectDao();
        retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiService.class);
    }

    public void execute(){
        Executors.newSingleThreadExecutor().execute(() -> {
            Call<JsonResponse> call = apiService.getJsonFromServer("3");
            try {
                result = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<SubjectEntity> subjectsEntities = result.getEntites();
            for (SubjectEntity subjectEntity : subjectsEntities) {
                try {
                    subjectDao.insert(subjectEntity);
                } catch (SQLiteConstraintException exception) {
                    subjectDao.update(subjectEntity);
                }
            }
        });
    }
}
