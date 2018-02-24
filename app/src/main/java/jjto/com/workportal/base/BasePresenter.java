package jjto.com.workportal.base;

import javax.inject.Inject;

import jjto.com.workportal.mvp.view.BaseView;

/**
 * Created by jhon on 21/02/2018.
 */

public class BasePresenter <V extends BaseView>{

    @Inject protected V mView;

    protected V getView() {
        return mView;
    }

}
