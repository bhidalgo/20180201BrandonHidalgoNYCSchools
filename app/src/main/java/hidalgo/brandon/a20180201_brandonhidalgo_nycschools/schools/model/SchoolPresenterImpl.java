package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolDatabase;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

/**
 * The MVP Model for the School package
 */
public class SchoolPresenterImpl implements SchoolPresenter {
    private Context mContext;

    private SchoolView mView;

    /**
     * Constructor
     *
     * @param view the SchoolView to be associated with this presenter
     */
    public SchoolPresenterImpl(SchoolView view) {
        try {
            mContext = (Context) view;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("SchoolView must extend Context.");
        }

        mView = view;
    }

    /**
     * Instructs the view to display school information
     *
     * @param school the school to be displayed
     */
    @Override
    public void showSchoolInfo(String school) {
        GetSchoolTask task = new GetSchoolTask(mContext, mView);

        task.execute(school);
    }

    /**
     * An Async Task for getting data from the school db specific to one school
     */
    private static class GetSchoolTask extends AsyncTask<String, Void, SchoolEntity> {
        WeakReference<Context> contextReference;

        WeakReference<SchoolView> presenterReference;

        /**
         * Constructor
         *
         * @param context The context who's reference will be held
         * @param view    The school view which will display the results
         */
        GetSchoolTask(Context context, SchoolView view) {
            contextReference = new WeakReference<>(context);

            presenterReference = new WeakReference<>(view);
        }

        @Override
        protected SchoolEntity doInBackground(String... strings) {
            //Get the school data from the database
            return SchoolDatabase.getDatabase(contextReference.get())
                    .schoolDao()
                    .getByName(strings[0]);
        }

        @Override
        protected void onPostExecute(SchoolEntity schoolEntity) {
            super.onPostExecute(schoolEntity);

            presenterReference.get().displaySchoolInfo(schoolEntity);
        }
    }
}
