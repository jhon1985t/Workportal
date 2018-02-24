package jjto.com.workportal.mvp.view;

import jjto.com.workportal.mvp.model.Users;

/**
 * Created by jhon on 21/02/2018.
 */

public interface MainView extends BaseView {

    void onUserLoaded(Users userResponseIds);
    void onShowDialog(String message);
    void onHideDialog();
    void onShowToast(String message);
    void onClearItems();

}
