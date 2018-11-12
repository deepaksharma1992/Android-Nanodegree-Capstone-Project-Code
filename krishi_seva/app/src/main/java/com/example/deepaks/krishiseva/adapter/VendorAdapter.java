package com.example.deepaks.krishiseva.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.Vendor;
import com.example.deepaks.krishiseva.util.ColorUtils;
import com.example.deepaks.krishiseva.util.GlobalConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.MyViewHolder> {

    private final List<Vendor> mVendorList;
    private final Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_start)
        TextView mVendorStartText;
        @BindView(R.id.tv_vendor_name)
        TextView mVendorName;
        @BindView(R.id.tv_selling)
        TextView mSellingCrop;
        @BindView(R.id.tv_best_price)
        TextView mBestPrice;
        @BindView(R.id.tv_phone_number)
        TextView mPhoneNumber;

        @SuppressWarnings("WeakerAccess")
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public VendorAdapter(Context context, List<Vendor> vendorList) {
        this.mVendorList = vendorList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vendor_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Vendor vendorData = mVendorList.get(holder.getAdapterPosition());
        ColorUtils.setDrawableColor(mContext, holder.mVendorStartText);
        holder.mVendorStartText.setText(vendorData.getVendorName().substring(0, 1));
        holder.mVendorName.setText(vendorData.getVendorName());
        holder.mPhoneNumber.setText(mContext.getString(R.string.call_label)
                + GlobalConstant.SPACE_1
                + vendorData.getPhoneNumber());
        holder.mSellingCrop.setText(vendorData.getItems());
        holder.mBestPrice.setText(mContext.getString(R.string.best_price_label)
                + GlobalConstant.SPACE_1
                + vendorData.getBestPrice());

    }


    /**
     * @return the list count
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mVendorList.size();
    }
}


