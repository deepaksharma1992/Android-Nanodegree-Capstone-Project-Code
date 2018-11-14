package com.example.deepaks.krishiseva.view.signup;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.bean.User;
import com.example.deepaks.krishiseva.util.DatabaseUserUtils;
import com.example.deepaks.krishiseva.util.DialogUtils;
import com.example.deepaks.krishiseva.util.GlobalUtils;
import com.example.deepaks.krishiseva.util.LocationUtils;
import com.example.deepaks.krishiseva.util.MessageUtils;
import com.example.deepaks.krishiseva.util.SignUpListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SignUpActivity extends AppCompatActivity implements SignUpListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

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
    @BindView(R.id.tv_location_address)
    TextView mLocationAddressText;

    private String mLocationString = null;
    private PlaceAutocompleteFragment placeAutocompleteFragment;

    private GoogleApiClient mGoogleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private LocationRequest mLocationRequest;
    private long UPDATE_INTERVAL = 60000;  /* 60 secs */
    private long FASTEST_INTERVAL = 60000; /* 60 secs */

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private static final String TAG = SignUpActivity.class.getSimpleName();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    private Location mLocation;

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
                    , mLocationString));
            finish();
            MessageUtils.showToastMessage(this, getString(R.string.sign_up_success_message));
        } else {
            MessageUtils.showToastMessage(this, getString(R.string.user_already_exist));
        }

        DialogUtils.dismissProgressDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpActivityComponents();
        setUpPlacesFragment();
    }

    private void setUpPlacesFragment() {
        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.place_fragment);
        AutocompleteFilter autocompleteFilter =
                new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();
        placeAutocompleteFragment.setFilter(autocompleteFilter);
        placeAutocompleteFragment.setHint(getString(R.string.search_location));
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mLocationString = place.getName().toString();
                mLocationAddressText.setText(mLocationString);
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext()
                        , status.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @author deepaks
     * @description method to initialize the
     */
    private void setUpActivityComponents() {
        ButterKnife.bind(this);
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
    }

    @OnClick(R.id.btn_sign_up)
    void signUpSelected() {
        if (validateSignUp()) {
            DialogUtils.showProgressDialog(this, getString(R.string.loading_message));
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
        } else if (mLocationString == null && TextUtils.isEmpty(mLocationString)) {
            MessageUtils.showToastMessage(this, getString(R.string.select_loction));
            return false;
        }
        return true;
    }

    @OnClick(R.id.tv_login)
    void loginClicked() {
        finish();
    }

    @OnClick(R.id.iv_detect_location)
    void detectLocationListener() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0)
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

        mGoogleApiClient.connect();
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation != null) {
            mLocationString = LocationUtils.getAddress(this, mLocation.getLatitude()
                    , mLocation.getLongitude());
            mLocationAddressText.setText(mLocationString);
        }
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            mLocationString = LocationUtils.getAddress(this, mLocation.getLatitude()
                    , mLocation.getLongitude());
            mLocationAddressText.setText(mLocationString = LocationUtils.getAddress(this
                    , mLocation.getLatitude()
                    , mLocation.getLongitude()));
        }
    }

    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), getString(R.string.enable_permission_label)
                    , Toast.LENGTH_LONG).show();
        }
        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        settingsBuilder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, settingsBuilder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(SignUpActivity.this, 101);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel(getString(R.string.permission_message),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                } else {
                    startLocationUpdates();
                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok_label), okListener)
                .setNegativeButton(getString(R.string.cancel_label), null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null)
            stopLocationUpdates();
    }

    public void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }
}
