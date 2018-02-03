package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.boroughs.view.components;

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

    /**
     * An interface for handling clicks of children view within the adapter
     */
    public interface OnBoroughItemSelectedListener {
        void receiveBoroughSelected(String borough);
    }

    /**
     * Constructor
     * @param context the context instantiating this adapter
     * @param list the list of Boroughs to display
     */
    BoroughRecyclerViewAdapter(Context context, List<Borough> list) {
        //Initialize our listener
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

        View itemView = inflater.inflate(R.layout.borough_view, parent, false);

        return new BoroughViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Get the item view of the view holder
        View itemView = holder.itemView;

        //get the borough at this position in the list
        final Borough currentBorough = mList.get(position);

        //Accept clicks for this child view
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.receiveBoroughSelected(currentBorough.getBoroughName());
            }
        });

        //Initialize our views from the holder and fill with the values of the current borough
        TextView textView = itemView.findViewById(R.id.textView);

        textView.setText(currentBorough.getBoroughName());

        ImageView imageView = itemView.findViewById(R.id.imageView);

        Picasso.with(mContext).load(getPhotoResourceIdByBorough(currentBorough.getBoroughName())).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * Gets the resource ID of the Drawable to use for the header image view.
     * @param boroughName the name of the borough
     * @return int
     */
    private int getPhotoResourceIdByBorough(String boroughName) {
        int photoResourceId = 0;

        switch (boroughName) {
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

        return photoResourceId;
    }

    /**
     * ViewHolder class for BoroughViews
     */
    private class BoroughViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        BoroughViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
        }
    }
}
