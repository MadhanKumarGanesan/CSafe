package com.madcodes.kavalanclone.ui.nearbyStations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NearbyStationsModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NearbyStationsModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}