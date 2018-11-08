package com.example.deepaks.krishiseva.view.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.Electricity;
import com.example.deepaks.krishiseva.util.GlobalUtils;
import com.example.deepaks.krishiseva.util.MessageUtils;
import com.example.deepaks.krishiseva.view.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText mUsernameEt;
    @BindView(R.id.et_email)
    EditText mEmailEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.et_confirm_password)
    EditText mConfirmPasswordEt;
    @BindView(R.id.et_phone)
    EditText mPhoneEt;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();

        getElectricityData();
    }


    private void getElectricityData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("electricity");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Electricity post = postSnapshot.getValue(Electricity.class);
                    Log.e("Get Data", post.getCutOffDate());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rb_farmer)
    void farmerSelected() {
    }

    @OnClick(R.id.rb_vendor)
    void vendorSelected() {

    }

    @OnClick(R.id.btn_sign_up)
    void signUpSelected() {
        if (validateSignUp()) {

        }
    }

    /**
     * @return the boolean flag of validation of sign up screen
     * @author deepaks
     * @description method to check the validation on the sign up screen
     */
    private boolean validateSignUp() {
        if (TextUtils.isEmpty(mUsernameEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_username_label));
            return false;
        } else if (TextUtils.isEmpty(mEmailEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_email_label));
            return false;
        } else if (GlobalUtils.isValidEmail(mEmailEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_valid_email_message));
            return false;
        } else if (TextUtils.isEmpty(mPhoneEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_phone_label));
            return false;
        } else if (!GlobalUtils.isValidPhoneNumber(mPhoneEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_valid_phone_message));
            return false;
        } else if (TextUtils.isEmpty(mPasswordEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_password_label));
            return false;
        } else if (TextUtils.isEmpty(mConfirmPasswordEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_conifirm_passowrd_label));
            return false;
        } else if (!mPasswordEt.getEditableText().toString().equals(
                mConfirmPasswordEt.getEditableText().toString()
        )) {
            MessageUtils.showToastMessage(this, getString(R.string.password_mismatch_message));
            return false;
        }
        return true;
    }

    @OnClick(R.id.tv_login)
    void loginClicked() {

    }
}
