package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.school_list.view.components;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.R;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit.School;
import hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room.SchoolEntity;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private List<SchoolEntity> mList;

    public SchoolRecyclerViewAdapter(Context context, List<SchoolEntity> list) {
        mContext = context;

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
        SchoolEntity currentSchool = mList.get(position);

        TextView textView = holder.itemView.findViewById(R.id.schoolTextView);

        textView.setText(currentSchool.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class SchoolViewHolder extends RecyclerView.ViewHolder {
        View mItemView;

        public SchoolViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
        }
    }
}
