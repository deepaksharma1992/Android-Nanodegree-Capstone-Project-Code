package com.example.deepaks.krishiseva.view.dashboard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.view.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        setUpFragmentComponents(view);
        return view;
    }

    private void setUpFragmentComponents(View view) {
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_login_sign_up)
    void onLoginSignUpButtonClick() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
