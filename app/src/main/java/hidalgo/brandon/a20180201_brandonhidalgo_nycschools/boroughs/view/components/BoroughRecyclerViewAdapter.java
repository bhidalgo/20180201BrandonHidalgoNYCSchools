package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components;

import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.Borough;

public class BoroughRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<Borough> mList;

    private OnBoroughItemSelectedListener listener;

    public interface OnBoroughItemSelectedListener {
        void receiveBoroughSelected(String borough);
    }

    public BoroughRecyclerViewAdapter(Context context, List<Borough> list) {
        try {
            listener = (OnBoroughItemSelectedListener) context;
        }
        catch(ClassCastException e) {
            Log.e("BoroughRVAdapter", "Context must implement OnBoroughItemSelectedListener");
        }

        mContext = context;

        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        //assertNotNull(inflater);

        View itemView = inflater.inflate(R.layout.borough_view, parent, false);

        //if(mContext instanceof BoroughsActivity)
        //binding.setContext((BoroughsActivity) mContext);

        return new BoroughViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;

        final Borough currentBorough = mList.get(position);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.receiveBoroughSelected(currentBorough.getName());
            }
        });

        TextView textView = itemView.findViewById(R.id.textView);

        textView.setText(currentBorough.getName());

        ImageView imageView = itemView.findViewById(R.id.imageView);

        int photoResourceId = 0;

        switch (currentBorough.getName()) {
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

        if (photoResourceId != 0)
            Picasso.with(mContext).load(photoResourceId).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class BoroughViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        BoroughViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
        }
    }
}
