package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.SchoolListActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.model.SchoolListPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.presenter.SchoolListPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.SchoolListView;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.schools.view.components.SchoolActivity;

public class SchoolListActivity extends AppCompatActivity implements SchoolListView, SchoolRecyclerViewAdapter.OnSchoolSelectedListener {

    private ImageView mImageView;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SchoolListPresenter presenter = new SchoolListPresenterImpl(this);

        String borough = getIntent().getStringExtra("borough");

        setTitle(borough);

        SchoolListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.school_list_activity);

        mImageView = binding.headerImageView;

        presenter.getHeaderDrawable(borough);

        mRecyclerView = binding.schoolRecyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.getSchoolList(borough);
    }

    /**
     * Configures the RecyclerView
     *
     * @param list the data to be displayed in the RecyclerView
     */
    @Override
    public void displaySchoolList(List<SchoolEntity> list) {
        //Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the adapter
        mRecyclerView.setAdapter(new SchoolRecyclerViewAdapter(this, list));

        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * Sets the image drawable for the header ImageView
     *
     * @param imageResourceId the resource ID of the image to be used
     */
    @Override
    public void displayHeaderImage(int imageResourceId) {
        mImageView.setImageDrawable(getDrawable(imageResourceId));
    }

    /**
     * Starts the SchoolActivity of the selected school
     *
     * @param schoolName the name of the selected school
     */
    @Override
    public void startSchoolActivity(String schoolName) {
        //Build intent
        Intent intent = new Intent(this, SchoolActivity.class);

        //Load our argument
        intent.putExtra("school", schoolName);

        //Start the activity
        startActivity(intent);
    }
}
