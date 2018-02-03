package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.model.BoroughPresenterImpl;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.presenter.BoroughPresenter;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.BoroughView;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.databinding.BoroughsActivityBinding;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components.SchoolListActivity;

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

    @Override
    public void displayBoroughsList(List<Borough> list) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new BoroughRecyclerViewAdapter(this, list));
    }

    @Override
    public void startSchoolListActivity(String borough) {
        Intent intent = new Intent(this, SchoolListActivity.class);

        intent.putExtra("borough", borough);

        startActivity(intent);
    }

    @Override
    public void receiveBoroughSelected(String borough) {
        startSchoolListActivity(borough);
    }
}
