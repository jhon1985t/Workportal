package jjto.com.workportal.mvp.presenter;

import android.app.Dialog;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.List;

import javax.inject.Inject;

import jjto.com.workportal.Constants.Utils;
import jjto.com.workportal.R;
import jjto.com.workportal.base.BasePresenter;
import jjto.com.workportal.mapper.UserMapper;
import jjto.com.workportal.mvp.model.Storage;
import jjto.com.workportal.mvp.model.Users;
import jjto.com.workportal.mvp.view.MainView;
import rx.Observer;

/**
 * Created by jhon on 21/02/2018.
 */

public class UserPresenter extends BasePresenter<MainView> implements Observer<Users> {

    @Inject protected UserMapper mUserMapper;
    @Inject protected Storage mStorage;

    @Inject
    public UserPresenter(){
    }

    public void getUsers(){
        getView().onShowDialog("Loading users..");
        Firebase usersReference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE);
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Users users = dataSnapshot.getValue(Users.class);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users users = snapshot.getValue(Users.class);
                    System.out.println(users.toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getDetail(final TextView nameRequester, final TextView vacations, final TextView approved, final TextView fromTo,
                          final TextView endTo, final String activityId){
        getView().onShowDialog("Loading user..");
        Firebase usersReference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE);
        usersReference.orderByChild("activityId").equalTo(activityId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Users users = dataSnapshot.getValue(Users.class);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users users = snapshot.getValue(Users.class);
                    nameRequester.setText(users.getEmployee());
                    vacations.setText(users.getProcess());
                    approved.setText(users.getApproved());
                    fromTo.setText(users.getBeginDate());
                    endTo.setText(users.getEndDate());
                    System.out.println(users.toString());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void isApproved(final String activityId, final ImageView imageView){
        Firebase usersReference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE);
        final boolean[] bool = new boolean[1];
        usersReference.orderByChild("activityId").equalTo(activityId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Users users = dataSnapshot.getValue(Users.class);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Users users = snapshot.getValue(Users.class);
                    if (users.getApproved().equals("approved")){
                        imageView.setImageResource(R.mipmap.ic_check);
                    } else {
                        imageView.setImageResource(R.mipmap.ic_plus);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void acceptVacations(final String activityId, final ImageView imageView){
        Firebase usersReference = new Firebase(Utils.FIRE_BASE_USER_REFERENCE + "/" + activityId);
        //Query query = usersReference.orderByChild("activityId").equalTo(activityId);
        usersReference.child("approved").setValue("approved", new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                imageView.setImageResource(R.mipmap.ic_check);
            }
        });
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Load of Users complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading users " + e.getMessage());
    }

    @Override
    public void onNext(Users userResponse) {
        Users user = mStorage.getSavedUsers();
        getView().onClearItems();
        getView().onUserLoaded(user);
    }

    public void getUsersFromdDatabase(){
        Users  userResponseIds = mStorage.getSavedUsers();
        getView().onClearItems();
        getView().onUserLoaded(userResponseIds);
    }

}
