package com.permission.utils;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.permission.entity.EmptyFragment;
import com.permission.entity.PermissionRequest;
import com.permission.entity.PermissionRequestBuilder;
import com.permission.listener.ActivityLifecycleCallbacksAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class PermissionUtil {

    private static Handler sHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            pendingRequestManagerFragments.remove(msg.obj);
        }
    };
    private static HashMap<FragmentManager, EmptyFragment> pendingRequestManagerFragments = new HashMap();

    public static PermissionRequestBuilder permission(String... permissions) {
        return new PermissionRequestBuilder().permission(permissions);
    }

    private static void requestPermission(@NonNull final Activity activity, final PermissionRequest permissionRequest) {
        if (activity != null) {
            getFragment(activity).doRequestPermissions(activity, permissionRequest);
        }
    }

    public static void requestPermission(PermissionRequest permissionRequest) {
        requestPermission(getActivity(), permissionRequest);
    }


    public static List<String> findDeniedPermissions(@NonNull Context context, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (!CheckPermissionUtil.checkHasPermisson(context, value)) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    private static final String TAG = "EmptyFragment_Tag";
    private static WeakReference<Activity> mWeakReferenceActivity;


    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksAdapter() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);

                mWeakReferenceActivity = new WeakReference<>(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {
                super.onActivityStarted(activity);
                mWeakReferenceActivity = new WeakReference<>(activity);

            }

        });
    }


    public static EmptyFragment getFragment(Activity activity) {
        return getFragment(activity.getFragmentManager());
    }

    public static EmptyFragment getFragment(FragmentManager fragmentManager) {
        EmptyFragment fragment = (EmptyFragment) fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = pendingRequestManagerFragments.get(fragmentManager);
            if (fragment == null){
                fragment = new EmptyFragment();
                pendingRequestManagerFragments.put(fragmentManager,fragment);
                fragmentManager
                        .beginTransaction()
                        .add(fragment, TAG)
                        .commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                sHandler.obtainMessage(0,fragmentManager).sendToTarget();
            }

        }
        return fragment;
    }

    public static Activity getActivity() {
        if (mWeakReferenceActivity != null) {
            return mWeakReferenceActivity.get();
        }
        return null;
    }

}
