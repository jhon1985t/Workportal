package jjto.com.workportal.modules.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import jjto.com.workportal.Constants.Utils;
import jjto.com.workportal.R;
import jjto.com.workportal.base.BaseActivity;
import jjto.com.workportal.di.components.DaggerUserComponent;
import jjto.com.workportal.di.module.UserModule;
import jjto.com.workportal.modules.home.adapter.MainAdapter;
import jjto.com.workportal.mvp.model.Users;
import jjto.com.workportal.mvp.presenter.UserPresenter;
import jjto.com.workportal.mvp.view.MainView;
import jjto.com.workportal.utilities.NetworkUtils;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.user_list) protected RecyclerView mUserList;

    @Inject protected UserPresenter mPresenter;

    private FirebaseRecyclerAdapter mUserAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent){
        super.onViewReady(savedInstanceState, intent);
        mUserList = findViewById(R.id.user_list);
        initializeList();
        loadUsers();
    }

    private void loadUsers() {
        if (NetworkUtils.isNetAvailable(this)){
            mPresenter.getUsers();
        } else {
            mPresenter.getUsersFromdDatabase();
        }
    }

    private void initializeList() {
        Firebase placeListReference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        String sortOrder = sharedPreferences.getString(Utils.LIST_ODER_PREFERENCE, Utils.ORDER_BY_KEY);
        Query sortQuery;

        if (sortOrder.equals(Utils.ORDER_BY_KEY)){
            sortQuery = placeListReference.orderByKey();
        } else {
            sortQuery = placeListReference.orderByChild("approved").equalTo(sortOrder);
        }

        mUserAdapter = new FirebaseRecyclerAdapter<Users, MainAdapter>(Users.class,
                R.layout.list_users_layout,
                MainAdapter.class,
                sortQuery) {
            @Override
            protected void populateViewHolder(MainAdapter publicListViewHolder, final Users placesList, int i) {
                publicListViewHolder.populate(placesList, getApplicationContext());
                onHideDialog();
            }
        };
        mUserList.setLayoutManager(new LinearLayoutManager(this));
        mUserList.setAdapter(mUserAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
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
