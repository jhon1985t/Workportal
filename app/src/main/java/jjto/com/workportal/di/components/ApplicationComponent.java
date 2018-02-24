package jjto.com.workportal.di.components;

import android.content.Context;

import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Component;
import jjto.com.workportal.di.module.ApplicationModule;

/**
 * Created by jhon on 21/02/2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context exposeContext();

}
