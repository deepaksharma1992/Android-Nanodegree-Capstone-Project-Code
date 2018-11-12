package com.example.deepaks.krishiseva.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.User;
import com.example.deepaks.krishiseva.util.DatabaseUserUtils;
import com.example.deepaks.krishiseva.util.GlobalConstant;
import com.example.deepaks.krishiseva.util.GlobalUtils;
import com.example.deepaks.krishiseva.util.MessageUtils;
import com.example.deepaks.krishiseva.util.SignUpListener;
import com.example.deepaks.krishiseva.view.BaseActivity;
import com.example.deepaks.krishiseva.view.signup.SignUpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements SignUpListener {

    @BindView(R.id.et_email)
    EditText mEmailEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;


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


    @OnClick(R.id.btn_login)
    void loginClicked() {
        if (validateCredential()) {
            DatabaseUserUtils.getAllUserList(this);
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

    @Override
    public void getAllUsers(List<User> userList) {
        boolean isValidUser = false;
        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(mEmailEt.getEditableText().toString().trim())) {
                if (user.getPassword().equals(mPasswordEt.getEditableText().toString().trim())) {
                    // logged in successfully
                    isValidUser = true;
                } else {
                    isValidUser = true;
                    mPasswordEt.setText(GlobalConstant.BLANK);
                    MessageUtils.showToastMessage(this, getString(R.string.wrong_password));
                }
            }
        }

        if (!isValidUser) {
            mEmailEt.setText(GlobalConstant.BLANK);
            mPasswordEt.setText(GlobalConstant.BLANK);
            MessageUtils.showToastMessage(this, getString(R.string.no_user));
        }
    }
}
