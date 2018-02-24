package jjto.com.workportal.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jjto.com.workportal.mvp.model.Storage;
import jjto.com.workportal.mvp.model.Users;

/**
 * Created by jhon on 21/02/2018.
 */

public class UserMapper {

    @Inject
    public UserMapper(){}

    public List<Users> mapUsers(Storage storage, Users response){
        List<Users> userList = new ArrayList<>();

        if (response != null){
            for (Users user : userList){
                Users userResponseIds = new Users();
                userResponseIds.setProcessId(user.getProcessId());
                userResponseIds.setProcess(user.getProcess());
                userResponseIds.setActivityId(user.getActivityId());
                userResponseIds.setActivity(user.getActivity());
                userResponseIds.setRequestDate(user.getRequestDate());
                userResponseIds.setEmployee(user.getEmployee());
                userResponseIds.setBeginDate(user.getBeginDate());
                userResponseIds.setEndDate(user.getEndDate());
                userResponseIds.setLastVacationOn(user.getLastVacationOn());
                userResponseIds.setApproved(user.getApproved());
            }
        }
        return userList;
    }

}
