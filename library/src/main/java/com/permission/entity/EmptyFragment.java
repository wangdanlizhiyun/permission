package com.permission.entity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.permission.utils.PermissionUtil;

import java.util.List;

public class EmptyFragment extends Fragment {
    public static final int REQUEST_CODE = 0xffff - 13;
    PermissionRequest mPermissionRequest;

    public EmptyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void doRequestPermissions(Context context, PermissionRequest permissionRequest) {
        this.mPermissionRequest = permissionRequest;
        if (mIsAttached) {
            doRequest(context);
        }
    }

    void doRequest(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.M) {
            List<String> deniedPermissions = PermissionUtil.findDeniedPermissions(context, mPermissionRequest.getPermissions());
            if (deniedPermissions.size() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(deniedPermissions.toArray(new String[]{}), REQUEST_CODE);
                }
                mPermissionRequest.hasRequested = true;
            } else {
                if (mPermissionRequest.getRunnable() != null) {
                    mPermissionRequest.getRunnable().run();
                    mPermissionRequest = null;
                }
            }
        }else {

            if (mPermissionRequest.getRunnable() != null) {
                mPermissionRequest.getRunnable().run();
                mPermissionRequest = null;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                List<String> deniedPermissions = PermissionUtil.findDeniedPermissions(getActivity(), mPermissionRequest.getPermissions());
                if (deniedPermissions.size() > 0) {
                    if (mPermissionRequest.getPermissionDeniedListener() != null) {
                        mPermissionRequest.getPermissionDeniedListener().onDenied(deniedPermissions);
                    }
                } else {
                    if (mPermissionRequest.getRunnable() != null) {
                        mPermissionRequest.getRunnable().run();
                    }
                }
                break;
        }
    }

    Boolean mIsAttached = false;

    @Override
    public void onDetach() {
        super.onDetach();
        mIsAttached = false;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mIsAttached = true;
        if (mPermissionRequest != null && !mPermissionRequest.hasRequested) {
            doRequest(activity);
        }
    }
}
