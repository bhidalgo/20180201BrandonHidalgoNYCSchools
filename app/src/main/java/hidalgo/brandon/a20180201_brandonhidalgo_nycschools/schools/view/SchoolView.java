package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;

/**
 * The MVP View of the School Package
 */
public interface SchoolView {
    void displaySchoolInfo(SchoolEntity school);
}
