package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model;

import java.util.ArrayList;
import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;

/**
 * A class for building a List of Boroughs
 */
public class BoroughPresenterImpl implements BoroughPresenter{
    private BoroughView mView;

    /**
     * Constructor
     * @param view the view to be associated with this instance.
     */
    public BoroughPresenterImpl(BoroughView view) {
        mView = view;
    }

    /**
     * Builds a list of Boroughs to pass to the view;
     */
    @Override
    public void getBoroughs() {
        //Bronx, Brooklyn, Manhattan, Queens, Staten Island
        int numberOfBoroughs = 5;

        List<Borough> result = new ArrayList<>();

        Borough currentBorough;

        //Build the list
        for(int i = 0; i < numberOfBoroughs; i++) {
            switch (i) {
                case 0:
                    currentBorough = new Borough("BRONX    ");

                    break;
                case 1:
                    currentBorough = new Borough("BROOKLYN ");

                    break;
                case 2:
                    currentBorough = new Borough("MANHATTAN");

                    break;
                case 3:
                    currentBorough = new Borough("QUEENS   ");

                    break;
                case 4:
                    currentBorough = new Borough("STATEN IS");

                    break;
                default:
                    currentBorough = new Borough("Borough Name");
                    break;
            }
            result.add(currentBorough);
        }

        //Pass the list to the view
        mView.displayBoroughsList(result);
    }

    /**
     * Instructs the view of this instance to start the next activity
     * @param borough the borough to load
     */
    @Override
    public void loadBorough(String borough) {
        mView.startSchoolListActivity(borough);
    }
}
