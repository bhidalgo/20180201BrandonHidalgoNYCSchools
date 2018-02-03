package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model.BoroughPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.BoroughsActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components.SchoolListActivity;

/**
 * An activity that displays a list of Boroughs
 */
public class BoroughsActivity extends AppCompatActivity implements BoroughView, BoroughRecyclerViewAdapter.OnBoroughItemSelectedListener {
    private BoroughPresenter presenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new BoroughPresenterImpl(this);

        BoroughsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.boroughs_activity);

        binding.setActivity(this);

        mRecyclerView = binding.boroughRecyclerView;

        setTitle(R.string.borough_title);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.getBoroughs();
    }

    /**
     * Configures the RecyclerView to display a list of Boroughs.
     *
     * @param list
     */
    @Override
    public void displayBoroughsList(List<Borough> list) {
        //Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set the adapter
        mRecyclerView.setAdapter(new BoroughRecyclerViewAdapter(this, list));
    }

    /**
     * Starts a SchoolListActivity of the given borough.
     *
     * @param borough the borough to display in the next activity
     */
    @Override
    public void startSchoolListActivity(String borough) {
        //Build the next activity intent and start the activity
        Intent intent = new Intent(this, SchoolListActivity.class);

        intent.putExtra("borough", borough);

        startActivity(intent);
    }

    /**
     * Interface implementation for handling the click of a Borough in BoroughRecyclerViewAdapter.
     *
     * @param borough the borough selected
     */
    @Override
    public void receiveBoroughSelected(String borough) {
        presenter.loadBorough(borough);
    }
}
