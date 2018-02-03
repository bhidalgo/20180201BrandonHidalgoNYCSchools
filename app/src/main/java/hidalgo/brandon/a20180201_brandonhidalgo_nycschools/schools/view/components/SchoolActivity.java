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

/**
 * An Activity display a school's SAT scores and description
 */
public class SchoolActivity extends AppCompatActivity implements SchoolView{
    private String mSchoolName;

    private SchoolActivityBinding binding;

    private SchoolPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new SchoolPresenterImpl(this);

        mSchoolName = getIntent().getStringExtra("school");

        binding = DataBindingUtil.setContentView(this, R.layout.school_activity);

        setTitle("About");

        mPresenter.showSchoolInfo(mSchoolName);
    }

    /**
     * Sets the school parameter of the activity binding class.
     * @param school the school to be displayed
     */
    @Override
    public void displaySchoolInfo(SchoolEntity school) {
        binding.setSchool(school);
    }
}
