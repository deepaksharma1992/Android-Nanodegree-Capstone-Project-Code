package com.example.deepaks.krishiseva.view.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.User;
import com.example.deepaks.krishiseva.util.DatabaseUserUtils;
import com.example.deepaks.krishiseva.util.GlobalUtils;
import com.example.deepaks.krishiseva.util.MessageUtils;
import com.example.deepaks.krishiseva.util.SignUpListener;
import com.example.deepaks.krishiseva.view.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUpListener {

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

    @Override
    public void getAllUsers(List<User> userList) {
        boolean isExist = false;
        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(mEmailEt.getEditableText().toString())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            DatabaseUserUtils.insertUser(new User(mUsernameEt.getEditableText().toString().trim()
                    , mEmailEt.getEditableText().toString().trim()
                    , mPasswordEt.getEditableText().toString().trim()
                    , mPhoneEt.getEditableText().toString().trim()
                    , ""));
            finish();
        } else {
            MessageUtils.showToastMessage(this, getString(R.string.user_already_exist));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign_up)
    void signUpSelected() {
        if (validateSignUp()) {
            DatabaseUserUtils.getAllUserList(this);
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
        finish();
    }
}
