package com.example.deepaks.krishiseva.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.util.GlobalUtils;
import com.example.deepaks.krishiseva.util.MessageUtils;
import com.example.deepaks.krishiseva.view.BaseActivity;
import com.example.deepaks.krishiseva.view.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_email)
    EditText mEmailEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.rb_farmer)
    RadioButton mFarmerRb;
    @BindView(R.id.rb_vendor)
    RadioButton mVendorRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
    }

    /**
     * @return the layout file for the activity
     * @author deepaks
     */
    @Override
    protected int getResourceLayout() {
        return R.layout.activity_login;
    }

    /**
     * @return setup the activity components for the activity
     * @author deepaks
     */
    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
    }


    @OnClick(R.id.rb_vendor)
    void vendorSelected() {
    }

    @OnClick(R.id.rb_farmer)
    void farmerSelected() {
    }

    @OnClick(R.id.btn_login)
    void loginClicked() {
        if (validateCredential()) {

        }
    }

    /**
     * @return the validation flag
     * @author deepaks
     * @description method to validate the login credential
     */
    private boolean validateCredential() {
        if (TextUtils.isEmpty(mEmailEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this
                    , getString(R.string.enter_email_label));
            return false;
        } else if (GlobalUtils.isValidEmail(mEmailEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this, getString(R.string.enter_valid_email_message));
            return false;
        } else if (TextUtils.isEmpty(mPasswordEt.getEditableText().toString())) {
            MessageUtils.showToastMessage(this
                    , getString(R.string.enter_password_label));
            return false;
        }
        return true;
    }

    @OnClick(R.id.tv_sign_up)
    void signUpClicked() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
        moveHead(this);
    }
}
