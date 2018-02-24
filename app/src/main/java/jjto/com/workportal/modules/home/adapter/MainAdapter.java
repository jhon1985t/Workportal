package jjto.com.workportal.modules.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jjto.com.workportal.R;
import jjto.com.workportal.modules.details.DetailActivity;
import jjto.com.workportal.mvp.model.Users;

/**
 * Created by jhon on 23/02/2018.
 */

public class MainAdapter extends RecyclerView.ViewHolder{
    Context ctx;

    @BindView(R.id.card_view) protected CardView cardView;
    @BindView(R.id.nameRequester) protected TextView nameRequester;
    @BindView(R.id.numDays) protected TextView numDays;
    @BindView(R.id.fromTo) protected TextView fromTo;
    @BindView(R.id.endTo) protected TextView endTo;

    public MainAdapter(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Users users, final Context ctx){
        this.ctx = ctx;

        final String activityId;
        nameRequester.setText(users.getEmployee());
        numDays.setText(users.getLastVacationOn());
        fromTo.setText("From : " + users.getBeginDate());
        endTo.setText("To : " + users.getEndDate());
        activityId = users.getActivityId();

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("activityId", activityId);
                    intent.setClass(ctx, DetailActivity.class);
                    ctx.startActivity(intent);
                }
            });

    }

}
