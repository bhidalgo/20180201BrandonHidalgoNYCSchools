package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.data_retrieval;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components.BoroughsActivity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit.NYCSchoolsClient;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit.School;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit.Scores;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolDatabase;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRetrievalActivity extends AppCompatActivity {
    private final String LOG_TAG = "Data Retrieval";

    private final String SHARED_PREF_KEY = "databases_populated";

    private NYCSchoolsClient client;

    private List<School> schoolList;

    private List<Scores> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.data_retrieval_activity);

        if(databaseLoaded())
            startNextActivity();
        else
            getDataFromClient();
    }

    private boolean databaseLoaded() {
        SharedPreferences preference = getPreferences(MODE_PRIVATE);

        return preference.getInt(SHARED_PREF_KEY, 0) == 1;
    }

    private void getDataFromClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(NYCSchoolsClient.class);

        Call<List<School>> schoolCall = client.getSchoolsData();

        schoolCall.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(@NonNull Call<List<School>> call, @NonNull Response<List<School>> response) {
                if(response.code() == 200) {
                    //Populate the list of schools
                    schoolList = response.body();

                    //Sort the school list
                    if(schoolList != null)
                        Collections.sort(schoolList);

                    getSchoolScores();
                }
                else {
                    Toast.makeText(DataRetrievalActivity.this, "Failed to get SchoolEntity data :(", Toast.LENGTH_SHORT).show();

                    Log.e(LOG_TAG, "Something went wrong getting school data error code: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<School>> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void getSchoolScores() {
        Call<List<Scores>> scoresCall = client.getSchoolsScores();

        scoresCall.enqueue(new Callback<List<Scores>>() {
            @Override
            public void onResponse(@NonNull Call<List<Scores>> call, @NonNull Response<List<Scores>> response) {
                if (response.code() == 200) {
                    //Finish populating the database with school scores
                    scoreList = response.body();

                    if(scoreList != null)
                        Collections.sort(scoreList);

                    loadDatabase();

                    setDatabaseLoaded();

                    startNextActivity();
                }
                else {
                    Toast.makeText(DataRetrievalActivity.this, "Failed to get SchoolEntity scores :(", Toast.LENGTH_SHORT).show();

                    Log.e(LOG_TAG, "Something went wrong getting school scores error code: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Scores>> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void loadDatabase() {
        List<SchoolEntity> schoolsForDatabase = new ArrayList<>();

        HashMap<String, Scores> scoresHashMap = new HashMap<>();

        for(int i = 0; i < schoolList.size(); i++) {
            School currentSchool = schoolList.get(i);

            Scores currentSchoolScores = null;

            if(scoresHashMap.containsKey(currentSchool.getSchoolDatabaseNumber())) {
                currentSchoolScores = scoresHashMap.get(currentSchool.getSchoolDatabaseNumber());
            }
            else {
                for(int j = 0; j < scoreList.size(); j++) {
                    Scores currentScore = scoreList.get(j);

                    if(currentSchool.getSchoolDatabaseNumber().equals(currentScore.getSchoolDatabaseNumber())) {
                        currentSchoolScores = currentScore;

                        scoreList.remove(currentScore);

                        break;
                    }
                    else
                        scoresHashMap.put(currentScore.getSchoolDatabaseNumber(), currentScore);
                }
            }
            SchoolEntity newSchoolEntity;

            if(currentSchoolScores != null) {
                newSchoolEntity = new SchoolEntity(
                        currentSchool.getSchoolDatabaseNumber(),
                        currentSchool.getName(),
                        currentSchool.getBorough(),
                        currentSchool.getDescription(),
                        currentSchoolScores.getMathScore(),
                        currentSchoolScores.getWritingScore(),
                        currentSchoolScores.getReadingScore()
                );
            }
            else {
                newSchoolEntity = new SchoolEntity(
                        currentSchool.getSchoolDatabaseNumber(),
                        currentSchool.getName(),
                        currentSchool.getBorough(),
                        currentSchool.getDescription(),
                        "N/A",
                        "N/A",
                        "N/A");
            }

            schoolsForDatabase.add(newSchoolEntity);
        }

        List<Long> result = SchoolDatabase.getDatabase(this).schoolDao().insertSchools(schoolsForDatabase);

        if(result.size() > 0)
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }

    private void setDatabaseLoaded() {
        SharedPreferences preference = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = preference.edit();

        editor.putInt(SHARED_PREF_KEY, 1);

        editor.apply();
    }

    private void startNextActivity() {
        Intent intent = new Intent(DataRetrievalActivity.this, BoroughsActivity.class);

        startActivity(intent);

        finish();
    }
}
