package com.permission.utils.checkDetailPermissionUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.permission.entity.PermissionType;


public class CheckPROCESS_OUTGOING_CALLS implements Check {
    @Override
    public Boolean check(Context context) throws Exception {
        return ContextCompat.checkSelfPermission(context, PermissionType.PROCESS_OUTGOING_CALLS) == PackageManager.PERMISSION_GRANTED;
    }
}
