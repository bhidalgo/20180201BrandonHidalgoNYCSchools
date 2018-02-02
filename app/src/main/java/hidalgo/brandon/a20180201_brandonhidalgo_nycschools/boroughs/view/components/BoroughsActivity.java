package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components;

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

public class BoroughsActivity extends AppCompatActivity implements BoroughView{
    private BoroughPresenter presenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new BoroughPresenterImpl(this);

        BoroughsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.boroughs_activity);

        binding.setActivity(this);

        mRecyclerView = binding.boroughRecyclerView;
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

    public void openSchoolsActivity(View view) {
        //
    }
}
