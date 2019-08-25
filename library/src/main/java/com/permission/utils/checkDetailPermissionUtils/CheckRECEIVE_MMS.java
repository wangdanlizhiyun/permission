package com.permission.utils.checkDetailPermissionUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.permission.entity.PermissionType;

public class CheckRECEIVE_MMS implements Check {
    @Override
    public Boolean check(Context context) throws Exception {
        return ContextCompat.checkSelfPermission(context, PermissionType.RECEIVE_MMS) == PackageManager.PERMISSION_GRANTED;

    }
}
