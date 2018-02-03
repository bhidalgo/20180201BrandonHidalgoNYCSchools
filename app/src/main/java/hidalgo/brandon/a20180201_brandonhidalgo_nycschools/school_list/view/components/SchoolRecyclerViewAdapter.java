package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<SchoolEntity> mList;

    private OnSchoolSelectedListener mListener;

    /**
     * An interface for listening to clicks on this adapter's children view
     */
    public interface OnSchoolSelectedListener {
        void startSchoolActivity(String schoolName);
    }

    /**
     * Constructor
     *
     * @param context the OnSchoolSelectedListener to be attached
     * @param list    the data to be used by the adapter
     */
    SchoolRecyclerViewAdapter(Context context, List<SchoolEntity> list) {
        //Try to initialize the mListener
        try {
            mListener = (OnSchoolSelectedListener) context;
        } catch (ClassCastException e) {
            Log.e("SchoolRVAdapter", "Context must implement OnSchoolSelectedListener");
        }

        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.school_list_item, parent, false);

        return new SchoolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Get the current school by the position of this adapter
        final SchoolEntity currentSchool = mList.get(position);

        //Set the on click for the child view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.startSchoolActivity(currentSchool.getName());
            }
        });

        //Bind the data to this child view
        TextView textView = holder.itemView.findViewById(R.id.schoolTextView);

        textView.setText(currentSchool.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * A ViewHolder class for Schools
     */
    private class SchoolViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        SchoolViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
        }
    }
}
