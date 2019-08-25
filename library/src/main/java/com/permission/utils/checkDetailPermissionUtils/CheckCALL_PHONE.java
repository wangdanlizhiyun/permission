package com.permission.utils.checkDetailPermissionUtils;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.permission.entity.PermissionType;


public class CheckCALL_PHONE implements Check {
    @Override
    public Boolean check(Context context) throws Exception {
        return ContextCompat.checkSelfPermission(context, PermissionType.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }
}
