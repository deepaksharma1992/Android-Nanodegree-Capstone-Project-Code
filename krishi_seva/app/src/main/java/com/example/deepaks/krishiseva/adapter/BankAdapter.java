package com.example.deepaks.krishiseva.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.Bank;
import com.example.deepaks.krishiseva.util.ColorUtils;
import com.example.deepaks.krishiseva.util.GlobalConstant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {

    private final List<Bank> mBankList;
    private final Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_bank_back)
        RelativeLayout mBankBackground;
        @BindView(R.id.tv_bank_name)
        TextView mBankText;
        @BindView(R.id.tv_bank_type)
        TextView mBankTypeText;
        @BindView(R.id.tv_interest_rate)
        TextView mBankInterest;
        @BindView(R.id.rb_bank_rating)
        RatingBar mBankRating;
        @BindView(R.id.tv_phone_number)
        TextView mPhoneText;

        @SuppressWarnings("WeakerAccess")
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public BankAdapter(Context context, List<Bank> bankList) {
        this.mBankList = bankList;
        this.mContext = context;
    }

    @Override
    public BankAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_bank_list, parent, false);
        return new BankAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final BankAdapter.MyViewHolder holder, final int position) {
        Bank bankData = mBankList.get(holder.getAdapterPosition());
        holder.mBankText.setText(bankData.getBankName());

        if (bankData.isGovtBank()) {
            holder.mBankTypeText.setTextColor(ContextCompat.getColor(mContext, R.color.color_green_500));
            holder.mBankTypeText.setText(mContext.getString(R.string.govt_bank_label));
        } else {
            holder.mBankTypeText.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            holder.mBankTypeText.setText(mContext.getString(R.string.private_bank_label));
        }

        holder.mBankRating.setRating(bankData.getRating());
        holder.mBankInterest.setText(bankData.getInterestRate()
                + GlobalConstant.PERCENT_SYMBOL
                + GlobalConstant.SPACE_1
                + mContext.getString(R.string.intrest_rate_label)
        );
        holder.mPhoneText.setText(mContext.getString(R.string.call_label)
                + GlobalConstant.SPACE_1
                + bankData.getPhoneNumber());

        ColorUtils.setDrawableColor(mContext, holder.mBankBackground);

    }


    /**
     * @return the list count
     * @author deepaks
     * @description method for getting the item count
     */
    @Override
    public int getItemCount() {
        return mBankList.size();
    }
}

