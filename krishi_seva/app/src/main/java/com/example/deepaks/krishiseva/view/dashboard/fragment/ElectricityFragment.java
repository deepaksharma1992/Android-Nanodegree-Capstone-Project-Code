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
import com.example.deepaks.krishiseva.adapter.ElectricityAdapter;
import com.example.deepaks.krishiseva.bean.Electricity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ElectricityFragment extends Fragment {
    private DatabaseReference mDatabase;

    @BindView(R.id.rv_list)
    RecyclerView mElectricityRv;
    @BindView(R.id.pb_loading)
    ProgressBar mLoadingProgress;
    @BindView(R.id.tv_no_data)
    TextView mTvNoElectricityText;

    private List<Electricity> mElectricityList;
    private ElectricityAdapter mElectricityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setUpFragmentComponent(view);
        setUpAdapter();
        getElectricityData();
        return view;
    }

    private void getElectricityData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Electricity electricityData = postSnapshot.getValue(Electricity.class);
                    mElectricityList.add(electricityData);
                }
                mElectricityAdapter.notifyDataSetChanged();
                mLoadingProgress.setVisibility(View.INVISIBLE);
                mTvNoElectricityText.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        mLoadingProgress.setVisibility(View.INVISIBLE);
        mTvNoElectricityText.setText(getString(R.string.no_electricity_message));
        mTvNoElectricityText.setVisibility(View.VISIBLE);
    }

    private void setUpAdapter() {
        mElectricityAdapter = new ElectricityAdapter(getActivity(), mElectricityList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mElectricityRv.setLayoutManager(mLayoutManager);
        mElectricityRv.setItemAnimator(new DefaultItemAnimator());
        mElectricityRv.setAdapter(mElectricityAdapter);
    }

    private void setUpFragmentComponent(View view) {
        ButterKnife.bind(this, view);
        mDatabase = FirebaseDatabase.getInstance().getReference("electricity");
        mElectricityList = new ArrayList<>();
    }
}
