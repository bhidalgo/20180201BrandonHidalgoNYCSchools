package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.presenter;

/**
 * The MVP Presenter for SchoolList
 */
public interface SchoolListPresenter {
    void getSchoolList(String boroughName);

    void getHeaderDrawable(String boroughName);
}
