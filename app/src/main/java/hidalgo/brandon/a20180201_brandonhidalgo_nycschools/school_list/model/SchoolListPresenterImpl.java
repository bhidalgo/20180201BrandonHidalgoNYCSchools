package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.model;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolDatabase;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.presenter.SchoolListPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.SchoolListView;

/**
 * The MVP Model for SchoolList
 */
public class SchoolListPresenterImpl implements SchoolListPresenter {
    private Context mContext;

    private SchoolListView mView;

    /**
     * Constructor
     */
    public SchoolListPresenterImpl(SchoolListView view) {
        mView = view;

        try {
            mContext = (Context) view;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("SchoolListView must extends Context class.");
        }
    }

    /**
     * Starts an ASync task to read a list of schools from the db
     *
     * @param boroughName the name of the borough
     */
    @Override
    public void getSchoolList(String boroughName) {
        GetSchoolListTask task = new GetSchoolListTask(mContext, mView);

        task.execute(boroughName);
    }

    /**
     * Instructs to view to show the header image by passing it a resource id.
     *
     * @param boroughName the name of the borough who's image will be used
     */
    @Override
    public void getHeaderDrawable(String boroughName) {
        int photoResourceId = 0;

        switch (boroughName) {
            case "BRONX    ":
                photoResourceId = R.drawable.bronx;

                break;
            case "BROOKLYN ":
                photoResourceId = R.drawable.brooklyn;

                break;
            case "MANHATTAN":
                photoResourceId = R.drawable.manhattan;

                break;
            case "QUEENS   ":
                photoResourceId = R.drawable.queens;

                break;
            case "STATEN IS":
                photoResourceId = R.drawable.staten_island;

                break;
        }

        mView.displayHeaderImage(photoResourceId);
    }

    /**
     * An AsyncTask to retrieve a list of schools from the database.
     */
    private static class GetSchoolListTask extends AsyncTask<String, Void, List<SchoolEntity>> {
        WeakReference<Context> contextReference;

        WeakReference<SchoolListView> viewReference;

        /**
         * Construct
         *
         * @param context a Context who's weak reference will be held
         * @param view    a SchoolListPresenter who's waek reference will be head
         */
        GetSchoolListTask(Context context, SchoolListView view) {
            contextReference = new WeakReference<>(context);

            viewReference = new WeakReference<>(view);
        }

        @Override
        protected List<SchoolEntity> doInBackground(String... strings) {
            //Call the database for a list of schools
            return SchoolDatabase.getDatabase(contextReference.get())
                    .schoolDao()
                    .getByBorough(strings[0]);
        }

        @Override
        protected void onPostExecute(List<SchoolEntity> schoolEntities) {
            //Show the list
            viewReference.get().displaySchoolList(schoolEntities);
        }
    }
}
