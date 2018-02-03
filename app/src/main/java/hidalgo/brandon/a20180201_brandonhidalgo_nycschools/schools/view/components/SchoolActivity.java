package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.components;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolDatabase;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.SchoolActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model.SchoolPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

public class SchoolActivity extends AppCompatActivity implements SchoolView{
    private String mSchoolName;

    private SchoolActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSchoolName = getIntent().getStringExtra("school");

        binding = DataBindingUtil.setContentView(this, R.layout.school_activity);

        setTitle("About");
    }

    @Override
    protected void onStart() {
        super.onStart();

        GetSchoolTask task = new GetSchoolTask(this, new SchoolPresenterImpl(this));

        task.execute(mSchoolName);
    }

    @Override
    public void displaySchoolInfo(SchoolEntity school) {
        binding.setSchool(school);
    }

    private static class GetSchoolTask extends AsyncTask<String, Void, SchoolEntity> {
        ProgressDialog dialog;

        WeakReference<Context> contextReference;

        WeakReference<SchoolPresenter> presenterReference;

        GetSchoolTask(Context context, SchoolPresenter presenter) {
            contextReference = new WeakReference<>(context);

            presenterReference = new WeakReference<>(presenter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = ProgressDialog.show(contextReference.get(), "School Database", "Loading school information...");
        }

        @Override
        protected SchoolEntity doInBackground(String... strings) {
            return SchoolDatabase.getDatabase(contextReference.get())
                    .schoolDao()
                    .getByName(strings[0]);
        }

        @Override
        protected void onPostExecute(SchoolEntity schoolEntity) {
            super.onPostExecute(schoolEntity);

            dialog.dismiss();

            presenterReference.get().showSchoolInfo(schoolEntity);
        }
    }
}
