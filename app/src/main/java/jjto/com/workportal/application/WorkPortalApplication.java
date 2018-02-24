package jjto.com.workportal.application;

import android.app.Application;

import com.firebase.client.Firebase;

import jjto.com.workportal.Constants.Utils;
import jjto.com.workportal.di.components.ApplicationComponent;
import jjto.com.workportal.di.components.DaggerApplicationComponent;
import jjto.com.workportal.di.module.ApplicationModule;

/**
 * Created by jhon on 21/02/2018.
 */

public class WorkPortalApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getmApplicationComponent(){
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
