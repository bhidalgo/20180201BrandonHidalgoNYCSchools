package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Our School Entity class which defines the relative schema of the School Room Persistence database
 */
@SuppressWarnings("unused")
@Entity(tableName = "schools")
public class SchoolEntity {
    @PrimaryKey
    @NonNull
    private String databaseNumber;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "borough")
    private String borough;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "math")
    private String mathScore;

    @ColumnInfo(name = "writing")
    private String writingScore;

    @ColumnInfo(name = "reading")
    private String readingScore;

    public SchoolEntity(@NonNull String databaseNumber, String name, String borough, String description, String mathScore, String writingScore, String readingScore) {
        this.databaseNumber = databaseNumber;

        this.name = name;

        this.borough = borough;

        this.description = description;

        this.mathScore = mathScore;

        this.writingScore = writingScore;

        this.readingScore = readingScore;
    }

    @NonNull
    String getDatabaseNumber() {
        return databaseNumber;
    }

    public void setDatabaseNumber(@NonNull String databaseNumber) {
        this.databaseNumber = databaseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMathScore() {
        return mathScore;
    }

    public void setMathScore(String mathScore) {
        this.mathScore = mathScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(String writingScore) {
        this.writingScore = writingScore;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(String readingScore) {
        this.readingScore = readingScore;
    }
}
