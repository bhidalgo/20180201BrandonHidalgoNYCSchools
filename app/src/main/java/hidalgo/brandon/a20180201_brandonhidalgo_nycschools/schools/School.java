package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools;

/**
 * A POJO for abstracting Retrofit data for schools
 */
public class School {
    private String name;

    private String mathScore;

    private String readingScore;

    private String writingScore;

    public String getName() {
        return name;
    }

    public String getMathScore() {
        return mathScore;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public String getWritingScore() {
        return writingScore;
    }
}
