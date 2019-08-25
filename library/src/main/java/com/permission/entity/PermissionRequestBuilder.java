package com.permission.entity;

import android.text.TextUtils;

import java.util.ArrayList;
import com.permission.listener.PermissionDeniedListener;
import com.permission.utils.PermissionUtil;

public class PermissionRequestBuilder {
    ArrayList<String> mPermissions;
    PermissionDeniedListener mPermissionDeniedListener;
    Runnable mRunnable;

    public PermissionRequestBuilder() {
        mPermissions = new ArrayList<>(5);
    }

    public PermissionRequestBuilder permission(String... permissions) {
        for (String permission : permissions
                ) {
            if (!TextUtils.isEmpty(permission)) {
                mPermissions.add(permission);
            }
        }
        return this;
    }

    public PermissionRequestBuilder deny(PermissionDeniedListener listener) {
        mPermissionDeniedListener = listener;
        return this;
    }


    public void run(Runnable runnable) {
        mRunnable = runnable;
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setPermissions(mPermissions.toArray(new String[]{}));
        permissionRequest.setPermissionDeniedListener(mPermissionDeniedListener);
        permissionRequest.setRunnable(mRunnable);
        PermissionUtil.requestPermission(permissionRequest);
    }
}
