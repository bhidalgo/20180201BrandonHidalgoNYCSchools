package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;

/**
 * The MVP View of SchoolList
 */
public interface SchoolListView {
    void displaySchoolList(List<SchoolEntity> list);

    void displayHeaderImage(int imageResourceId);
}
