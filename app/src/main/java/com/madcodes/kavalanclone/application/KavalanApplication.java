package com.madcodes.kavalanclone.application;

import android.app.Application;
import com.madcodes.kavalanclone.Utils.KavalanPreferences;

public class KavalanApplication extends Application {

    public static final String TAG = KavalanApplication.class.getSimpleName();
    private static KavalanApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        KavalanPreferences.setUpDefaults(getApplicationContext());

        mInstance = this;
    }

    /**
     *  DocketApplication Instance
     *
     * @return application instance
     */
    public static synchronized KavalanApplication getInstance() {
        return mInstance;
    }
}
