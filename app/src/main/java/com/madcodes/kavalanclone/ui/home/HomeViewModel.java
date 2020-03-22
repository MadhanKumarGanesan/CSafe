package com.madcodes.kavalanclone.ui.home;

import android.media.Image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Image> mImage;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("PRESS SOS BUTTON TO CONNECT & CONTACT WITH NEAR BY COPS");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<Image> getImage() {
        return mImage;
    }
}