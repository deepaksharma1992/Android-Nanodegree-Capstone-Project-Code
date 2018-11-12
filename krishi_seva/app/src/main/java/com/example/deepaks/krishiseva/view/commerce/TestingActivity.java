package com.example.deepaks.krishiseva.view.commerce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.deepaks.krishiseva.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class TestingActivity extends AppCompatActivity {

    private PlaceAutocompleteFragment placeAutocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter autocompleteFilter =
                new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS).build();
        placeAutocompleteFragment.setFilter(autocompleteFilter);
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Toast.makeText(getApplicationContext(), place.getName().toString()
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext()
                        , status.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

