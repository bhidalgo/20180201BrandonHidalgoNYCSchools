package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit.School;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolDatabase;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.SchoolListActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.model.SchoolListPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.presenter.SchoolListPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.SchoolListView;

public class SchoolListActivity extends AppCompatActivity implements SchoolListView{
    private String mBorough;

    private SchoolListPresenter mPresenter;

    private ImageView mImageView;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SchoolListPresenterImpl(this);

        SchoolListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.school_list_activity);

        mRecyclerView = binding.schoolRecyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBorough = getIntent().getStringExtra("borough");

        mImageView = binding.headerImageView;

        int photoResourceId = 0;

        switch (mBorough) {
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

        mImageView.setImageDrawable(getDrawable(photoResourceId));

        setTitle(mBorough);

        mPresenter.showSchoolList();
    }

    @Override
    public void displaySchoolList() {
        GetSchoolListTask task = new GetSchoolListTask();

        task.execute(mBorough);
    }

    private class GetSchoolListTask extends AsyncTask<String, Void, List<SchoolEntity>> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = ProgressDialog.show(SchoolListActivity.this, "Database Task", "Loading schools...");
        }

        @Override
        protected List<SchoolEntity> doInBackground(String... strings) {
            List<SchoolEntity> list = SchoolDatabase.getDatabase(SchoolListActivity.this)
                    .schoolDao()
                    .getByBorough(strings[0].toUpperCase());

            Log.v("SchoolListActivity", "Got list size of " + list.size());

            return list;
        }

        @Override
        protected void onPostExecute(List<SchoolEntity> schoolEntities) {
            dialog.dismiss();

            mRecyclerView.setAdapter(new SchoolRecyclerViewAdapter(SchoolListActivity.this, schoolEntities));

            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
