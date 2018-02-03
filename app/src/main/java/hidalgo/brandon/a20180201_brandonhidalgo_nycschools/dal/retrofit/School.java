package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A POJO for abstracting Retrofit data for schools
 */
@SuppressWarnings("unused")
public class School implements Comparable<School> {
    @SerializedName("dbn")
    @Expose
    private String schoolDatabaseNumber;

    @SerializedName("school_name")
    @Expose
    private String name;

    @SerializedName("borough")
    @Expose
    private String borough;

    @SerializedName("overview_paragraph")
    @Expose
    private String description;

    public String getSchoolDatabaseNumber() {
        return schoolDatabaseNumber;
    }

    public String getName() {
        return name;
    }

    public String getBorough() {
        return borough;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(@NonNull School otherSchool) {
        return schoolDatabaseNumber.compareToIgnoreCase(otherSchool.schoolDatabaseNumber);
    }
}
