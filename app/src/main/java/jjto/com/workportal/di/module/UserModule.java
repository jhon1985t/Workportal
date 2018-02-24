package jjto.com.workportal.di.module;

import dagger.Module;
import dagger.Provides;
import jjto.com.workportal.di.scope.PerActivity;
import jjto.com.workportal.mvp.view.MainView;

/**
 * Created by jhon on 21/02/2018.
 */

@Module
public class UserModule {

    private MainView mView;

    public UserModule(MainView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    MainView provideView(){
        return mView;
    }

}
