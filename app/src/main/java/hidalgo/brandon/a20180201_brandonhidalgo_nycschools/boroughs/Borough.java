package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs;

/**
 * A class containing borough information
 */
public class Borough {
    private String name;

    public Borough(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
