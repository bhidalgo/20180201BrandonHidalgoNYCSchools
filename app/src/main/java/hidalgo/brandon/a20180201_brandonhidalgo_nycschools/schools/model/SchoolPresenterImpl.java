package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

public class SchoolPresenterImpl implements SchoolPresenter{
    private SchoolView mView;

    public SchoolPresenterImpl() {

    }

    public SchoolPresenterImpl(SchoolView view){
        mView = view;
    }

    @Override
    public void showSchoolName(String schoolName) {
        //
    }

    @Override
    public void showSchoolMathScores(String schoolName) {
        //
    }

    @Override
    public void showSchoolReadingScores(String schoolName) {
        //
    }

    @Override
    public void showSchoolDescription(String schoolName) {
        //
    }

    @Override
    public SchoolPresenterImpl makePresenter(SchoolView view) {
        return new SchoolPresenterImpl(view);
    }
}
