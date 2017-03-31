package com.sample.retrofit;

import android.app.Application;
import android.content.Context;

/**
 * Created by 482127 on 11/18/2016.
 */

public class RetroSampleApplication extends Application {

    public static Context mContext;
    public static  Context getAppContext(){
       return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }
}
