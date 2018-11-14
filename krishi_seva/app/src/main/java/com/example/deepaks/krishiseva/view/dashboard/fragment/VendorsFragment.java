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
import com.example.deepaks.krishiseva.adapter.VendorAdapter;
import com.example.deepaks.krishiseva.bean.Vendor;
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

public class VendorsFragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView mVendorsRv;
    @BindView(R.id.tv_no_data)
    TextView mNoVendorText;
    @BindView(R.id.pb_loading)
    ProgressBar mLoadingProgress;

    private DatabaseReference mDatabase;
    private List<Vendor> mVendorList;
    private VendorAdapter mVendorAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setUpFragmentComponents(view);
        setUpAdapter();
        getVendorListData();
        return view;
    }

    private void setUpFragmentComponents(View view) {
        ButterKnife.bind(this, view);
        mDatabase = FirebaseDatabase.getInstance().getReference(DatabaseConstant.VENDOR_TAG);
        mVendorList = new ArrayList<>();
    }

    private void getVendorListData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Vendor vendorData = postSnapshot.getValue(Vendor.class);
                    mVendorList.add(vendorData);
                }
                mVendorAdapter.notifyDataSetChanged();
                mLoadingProgress.setVisibility(View.INVISIBLE);
                mNoVendorText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        mLoadingProgress.setVisibility(View.INVISIBLE);
        mNoVendorText.setText(getString(R.string.no_bank_message));
        mNoVendorText.setVisibility(View.VISIBLE);
    }

    private void setUpAdapter() {
        mVendorAdapter = new VendorAdapter(getActivity(), mVendorList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mVendorsRv.setLayoutManager(mLayoutManager);
        mVendorsRv.setItemAnimator(new DefaultItemAnimator());
        mVendorsRv.setAdapter(mVendorAdapter);
    }

}
