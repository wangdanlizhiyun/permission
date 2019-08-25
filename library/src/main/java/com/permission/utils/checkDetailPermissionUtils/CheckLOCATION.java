package com.permission.utils.checkDetailPermissionUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.permission.entity.PermissionType;

/**
 * Created by lizhiyun on 2018/2/13.
 */

public class CheckLOCATION implements Check {
    @Override
    public Boolean check(Context context) throws Exception {

        return ContextCompat.checkSelfPermission(context, PermissionType.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, PermissionType.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

}
