package jjto.com.workportal.modules.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import jjto.com.workportal.R;
import jjto.com.workportal.base.BaseActivity;
import jjto.com.workportal.di.components.DaggerUserComponent;
import jjto.com.workportal.di.module.UserModule;
import jjto.com.workportal.mvp.model.Users;
import jjto.com.workportal.mvp.presenter.UserPresenter;
import jjto.com.workportal.mvp.view.MainView;
import jjto.com.workportal.utilities.NetworkUtils;

/**
 * Created by jhon on 21/02/2018.
 */

public class DetailActivity extends BaseActivity implements MainView {

    public static final String USER = "user";

    String activityId;

    @BindView(R.id.nameRequester) protected TextView nameRequester;
    @BindView(R.id.vacations) protected  TextView vacations;
    @BindView(R.id.approved) protected TextView approved;
    @BindView(R.id.fromTo) protected TextView fromTo;
    @BindView(R.id.endTo) protected TextView endTo;
    @BindView(R.id.list_user_itemView) protected ImageView list_user_itemView;

    @Inject
    protected UserPresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showBackArrow();

        loadUsers();

        mPresenter.isApproved(activityId, list_user_itemView);
        list_user_itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.acceptVacations(activityId, list_user_itemView);
            }
        });

    }

    private void loadUsers() {
        if (NetworkUtils.isNetAvailable(this)){
            mPresenter.getDetail(nameRequester, vacations, approved, fromTo, endTo, activityId);
            onHideDialog();
        } else {
            mPresenter.getUsersFromdDatabase();
        }
    }

    @Override
    protected int getContentView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("activityId")!=null){
            activityId = bundle.getString("activityId");
        } else {

        }
        return R.layout.activity_detail;
    }

    @Override
    protected void resolveDaggerDependency(){
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .userModule(new UserModule(this))
                .build().inject(this);
    }

    @Override
    public void onUserLoaded(Users userResponseIds) {
        //mUserAdapter.populate(userResponseIds, this);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {

    }
}
