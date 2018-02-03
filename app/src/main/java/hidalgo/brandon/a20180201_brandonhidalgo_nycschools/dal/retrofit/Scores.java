package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scores implements Comparable<Scores>{
    @SerializedName("dbn")
    @Expose
    private String schoolDatabaseNumber;

    @SerializedName("sat_critical_reading_avg_score")
    @Expose
    private String readingScore;

    @SerializedName("sat_math_avg_score")
    @Expose
    private String mathScore;

    @SerializedName("sat_writing_avg_score")
    @Expose
    private String writingScore;

    public String getSchoolDatabaseNumber() {
        return schoolDatabaseNumber;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public String getMathScore() {
        return mathScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    @Override
    public int compareTo(@NonNull Scores otherScore) {
        return schoolDatabaseNumber.compareToIgnoreCase(otherScore.schoolDatabaseNumber);
    }
}
