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

/**
 * An activity that fetches the relevant data from the internet and loads it into a local db for use.
 */
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
            startFetchingData();
    }

    private boolean databaseLoaded() {
        SharedPreferences preference = getPreferences(MODE_PRIVATE);

        return preference.getInt(SHARED_PREF_KEY, 0) == 1;
    }

    /**
     * Starts the fetching process by creating our client, and enqueuing the first and last call to the NYC Schools Database
     */
    private void startFetchingData() {
        //Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Get our client
        client = retrofit.create(NYCSchoolsClient.class);

        //Our first call is to get the schools data
        Call<List<School>> schoolCall = client.getSchoolsData();

        //Enqueue the first call
        schoolCall.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(@NonNull Call<List<School>> call, @NonNull Response<List<School>> response) {
                if(response.code() == 200) {
                    //Populate the list of schools
                    schoolList = response.body();

                    //Sort the school list
                    if(schoolList != null)
                        Collections.sort(schoolList);

                    //Start the second and last call
                    getSchoolScores();
                }
                else {
                    //Show an error message
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

    /**
     * Enqueues the second and last call to the NYC Schools Database
     */
    private void getSchoolScores() {
        //Create the second call
        Call<List<Scores>> scoresCall = client.getSchoolsScores();

        //Enqueue the second call
        scoresCall.enqueue(new Callback<List<Scores>>() {
            @Override
            public void onResponse(@NonNull Call<List<Scores>> call, @NonNull Response<List<Scores>> response) {
                if (response.code() == 200) {
                    //Finish populating the database with school scores
                    scoreList = response.body();

                    //Sort the list to match the school list
                    if(scoreList != null)
                        Collections.sort(scoreList);

                    loadDatabase();

                    setDatabaseLoaded();

                    startNextActivity();
                }
                else {
                    //Show an error message
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

    /**
     * Builds a list of SchoolEntities and passes it to our Room Persistent db.
     */
    private void loadDatabase() {
        //Initialize the list
        List<SchoolEntity> schoolsForDatabase = new ArrayList<>();

        //This will make it easier to obtain scores by storing them in a map with their DBN as the key
        HashMap<String, Scores> scoresHashMap = new HashMap<>();

        //Traverse the school list
        for(int i = 0; i < schoolList.size(); i++) {
            School currentSchool = schoolList.get(i);

            //Find the current school's score
            Scores currentSchoolScores = null;

            //If it's in the HashMap, no need to traverse the score list, just return from the map
            if(scoresHashMap.containsKey(currentSchool.getSchoolDatabaseNumber())) {
                currentSchoolScores = scoresHashMap.get(currentSchool.getSchoolDatabaseNumber());
            }
            else {
                //Traverse the score list, removing the current score with each step from the list since
                //it will be stored in the HashMap for later use anyways
                for(int j = 0; j < scoreList.size(); j++) {
                    Scores currentScore = scoreList.get(j);

                    if(currentSchool.getSchoolDatabaseNumber().equals(currentScore.getSchoolDatabaseNumber())) {
                        //Set the current school score
                        currentSchoolScores = currentScore;

                        //Shrink the list
                        scoreList.remove(currentScore);

                        break;
                    }
                    else {
                        //Store the score in the HashMap for easier retrieval
                        scoresHashMap.put(currentScore.getSchoolDatabaseNumber(), currentScore);

                        //Shrink the list
                        scoreList.remove(currentScore);
                    }
                }
            }

            //Create the new SchoolEntity
            SchoolEntity newSchoolEntity;

            //Some schools did not report SAT scores in 2012.
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

            //Add the Entity to the entity list
            schoolsForDatabase.add(newSchoolEntity);
        }

        //Insert the entities into the db.
        //It's ok to call the school DAO from the main thread since this is the only procedure
        //and blocking the main thread momentarily won't hurt. This is not best practice though.
        List<Long> result = SchoolDatabase.getDatabase(this).schoolDao().insertSchools(schoolsForDatabase);

        //Confirm whether the operation was successful
        String resultString = result.size() > 0 ? "Successfully loaded the database!" : "Failed loading the database. Please try again.";

        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the shared preference to remember whether the database was loaded or not.
     */
    private void setDatabaseLoaded() {
        SharedPreferences preference = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = preference.edit();

        editor.putInt(SHARED_PREF_KEY, 1);

        editor.apply();
    }

    /**
     * Starts the Borough activity.
     */
    private void startNextActivity() {
        Intent intent = new Intent(DataRetrievalActivity.this, BoroughsActivity.class);

        startActivity(intent);

        finish();
    }
}
