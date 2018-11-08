package com.example.deepaks.krishiseva.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.Electricity;
import com.example.deepaks.krishiseva.util.ColorUtils;
import com.example.deepaks.krishiseva.util.GlobalConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ElectricityAdapter extends RecyclerView.Adapter<ElectricityAdapter.MyViewHolder> {

    private final List<Electricity> mElectricityList;
    private final Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder
            {
        @BindView(R.id.rl_electricity_back)
        RelativeLayout mElectricityIconBackground;
        @BindView(R.id.tv_place)
        TextView mPlaceText;
        @BindView(R.id.tv_date)
        TextView mDateText;
        @BindView(R.id.tv_from_to_time)
        TextView mFromToTime;


        @SuppressWarnings("WeakerAccess")
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

    public ElectricityAdapter(Context context, List<Electricity> electricityList) {
        this.mElectricityList = electricityList;
        this.mContext = context;
    }

    @Override
    public ElectricityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_electricity_list, parent, false);
        return new ElectricityAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ElectricityAdapter.MyViewHolder holder, final int position) {
        Electricity electricityData = mElectricityList.get(holder.getAdapterPosition());
        ColorUtils.setDrawableColor(mContext, holder.mElectricityIconBackground);
        holder.mPlaceText.setText(electricityData.getCutOfRegion());
        holder.mDateText.setText(electricityData.getCutOffDate());
        holder.mFromToTime.setText(
                electricityData.getFromTime()
                        + GlobalConstant.SPACE_1
                        + electricityData.getToTime()
                        + GlobalConstant.START_BRACKET
                        + electricityData.getCutOffTime()
                        + GlobalConstant.CLOSE_BRACKET
        );
    }


    /**
     * @return the list count
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mElectricityList.size();
    }
}

