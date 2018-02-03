package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.model;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.presenter.SchoolListPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.SchoolListView;

public class SchoolListPresenterImpl implements SchoolListPresenter{
    private SchoolListView mView;

    public SchoolListPresenterImpl(SchoolListView view) {
        mView = view;
    }

    @Override
    public void showSchoolList() {
        mView.displaySchoolList();
    }
}
