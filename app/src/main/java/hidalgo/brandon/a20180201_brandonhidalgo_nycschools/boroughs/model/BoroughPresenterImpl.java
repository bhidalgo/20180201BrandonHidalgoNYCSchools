package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;

/**
 * A class for building a List of Boroughs
 */
public class BoroughPresenterImpl implements BoroughPresenter{
    private BoroughView mView;

    public BoroughPresenterImpl(BoroughView view) {
        mView = view;
    }

    @Override
    public List<Borough> getBoroughs() {
        return null;
    }
}
