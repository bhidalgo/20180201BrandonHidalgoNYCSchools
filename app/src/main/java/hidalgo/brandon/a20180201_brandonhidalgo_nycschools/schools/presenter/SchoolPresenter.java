package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

public interface SchoolPresenter {
    void showSchoolName(String schoolName);

    void showSchoolMathScores(String schoolName);

    void showSchoolReadingScores(String schoolName);

    void showSchoolDescription(String schoolName);

    SchoolPresenter makePresenter(SchoolView view);
}
