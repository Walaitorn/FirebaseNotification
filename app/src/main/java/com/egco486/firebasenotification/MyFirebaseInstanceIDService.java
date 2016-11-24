package com.egco486.firebasenotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by 6272user on 11/24/2016 AD.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh(){
        Log.i("Result","onTokenRefresh: "+ FirebaseInstanceId.getInstance().getToken());
    }
}
