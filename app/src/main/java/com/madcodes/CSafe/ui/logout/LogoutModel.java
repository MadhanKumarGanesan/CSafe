package com.madcodes.CSafe.ui.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LogoutModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LogoutModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}