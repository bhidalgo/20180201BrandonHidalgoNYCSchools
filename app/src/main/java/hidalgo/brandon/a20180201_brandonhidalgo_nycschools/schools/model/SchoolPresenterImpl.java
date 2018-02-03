package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

public class SchoolPresenterImpl implements SchoolPresenter{
    private SchoolView mView;

    public SchoolPresenterImpl(SchoolView view){
        mView = view;
    }

    @Override
    public void showSchoolInfo(SchoolEntity school) {
        mView.displaySchoolInfo(school);
    }
}
