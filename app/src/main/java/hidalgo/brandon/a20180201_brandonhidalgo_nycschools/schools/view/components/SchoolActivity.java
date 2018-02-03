package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.components;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.SchoolActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.model.SchoolPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.presenter.SchoolPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.SchoolView;

/**
 * An Activity display a school's SAT scores and description
 */
public class SchoolActivity extends AppCompatActivity implements SchoolView {
    private SchoolActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SchoolPresenter presenter = new SchoolPresenterImpl(this);

        String schoolName = getIntent().getStringExtra("school");

        binding = DataBindingUtil.setContentView(this, R.layout.school_activity);

        setTitle(R.string.school_title);

        presenter.showSchoolInfo(schoolName);
    }

    /**
     * Sets the school parameter of the activity binding class.
     *
     * @param school the school to be displayed
     */
    @Override
    public void displaySchoolInfo(SchoolEntity school) {
        binding.setSchool(school);
    }
}
