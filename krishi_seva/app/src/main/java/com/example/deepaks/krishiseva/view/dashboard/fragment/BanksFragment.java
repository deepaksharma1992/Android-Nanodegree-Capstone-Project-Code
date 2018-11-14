package com.example.deepaks.krishiseva.view.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.adapter.BankAdapter;
import com.example.deepaks.krishiseva.bean.Bank;
import com.example.deepaks.krishiseva.util.DatabaseConstant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BanksFragment extends Fragment {
    @BindView(R.id.rv_list)
    RecyclerView mBankRv;
    @BindView(R.id.tv_no_data)
    TextView mNoBankText;
    @BindView(R.id.pb_loading)
    ProgressBar mLoadingProgress;

    private DatabaseReference mDatabase;
    private List<Bank> mBankList;
    private BankAdapter mBankAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setUpFragmentComponents(view);
        setUpAdapter();
        getBankData();
        return view;
    }

    private void setUpFragmentComponents(View view) {
        ButterKnife.bind(this, view);
        mDatabase = FirebaseDatabase.getInstance().getReference(DatabaseConstant.BANK_TAG);
        mBankList = new ArrayList<>();
    }

    private void getBankData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Bank bankData = postSnapshot.getValue(Bank.class);
                    mBankList.add(bankData);
                }
                mBankAdapter.notifyDataSetChanged();
                mLoadingProgress.setVisibility(View.INVISIBLE);
                mNoBankText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        mLoadingProgress.setVisibility(View.INVISIBLE);
        mNoBankText.setText(getString(R.string.no_bank_message));
        mNoBankText.setVisibility(View.VISIBLE);
    }

    private void setUpAdapter() {
        mBankAdapter = new BankAdapter(getActivity(), mBankList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mBankRv.setLayoutManager(mLayoutManager);
        mBankRv.setItemAnimator(new DefaultItemAnimator());
        mBankRv.setAdapter(mBankAdapter);
    }

}
