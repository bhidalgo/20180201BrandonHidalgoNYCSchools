package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs;

/**
 * A class containing borough information
 */
public class Borough {
    private String boroughName;

    public Borough(String boroughName) {
        this.boroughName = boroughName;
    }

    public String getBoroughName() {
        return boroughName;
    }

    public void setBoroughName(String boroughName) {
        this.boroughName = boroughName;
    }
}
