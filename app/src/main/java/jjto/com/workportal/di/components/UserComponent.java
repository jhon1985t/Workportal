package jjto.com.workportal.di.components;

import dagger.Component;
import jjto.com.workportal.modules.details.DetailActivity;
import jjto.com.workportal.modules.home.MainActivity;
import jjto.com.workportal.di.module.UserModule;
import jjto.com.workportal.di.scope.PerActivity;

/**
 * Created by jhon on 21/02/2018.
 */

@PerActivity
@Component( modules = UserModule.class, dependencies = ApplicationComponent.class)
public interface UserComponent {

    void inject(MainActivity activity);
    void inject(DetailActivity activity);

}
