package jjto.com.workportal.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jhon on 21/02/2018.
 */

@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context mContext) {
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    Context provideContext(){
        return mContext;
    }

}
