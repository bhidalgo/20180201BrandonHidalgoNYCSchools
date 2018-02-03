package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs;

/**
 * A class containing borough information
 */
public class Borough {
    private String mBoroughName;

    public Borough(String boroughName) {
        mBoroughName = boroughName;
    }

    public String getBoroughName() {
        return mBoroughName;
    }

    public void setBoroughName(String boroughName) {
        mBoroughName = boroughName;
    }
}
