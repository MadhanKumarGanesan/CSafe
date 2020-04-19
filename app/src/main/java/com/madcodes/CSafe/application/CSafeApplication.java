package com.madcodes.CSafe.application;

import android.app.Application;
import com.madcodes.CSafe.Utils.CSafePreferences;

public class CSafeApplication extends Application {

    public static final String TAG = CSafeApplication.class.getSimpleName();
    private static CSafeApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        CSafePreferences.setUpDefaults(getApplicationContext());

        mInstance = this;
    }

    /**
     *  DocketApplication Instance
     *
     * @return application instance
     */
    public static synchronized CSafeApplication getInstance() {
        return mInstance;
    }
}
