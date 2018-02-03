package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;

public interface BoroughView {
    void displayBoroughsList(List<Borough> list);

    void startSchoolListActivity(String borough);
}
