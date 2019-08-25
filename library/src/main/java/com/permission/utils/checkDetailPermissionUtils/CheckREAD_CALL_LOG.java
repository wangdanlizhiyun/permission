package com.permission.utils.checkDetailPermissionUtils;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;


public class CheckREAD_CALL_LOG implements Check {

    @Override
    public Boolean check(Context context) throws Exception {
        ContentResolver resolver = context.getContentResolver();
        String[] projection = new String[]{CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.TYPE};
        @SuppressLint("MissingPermission")
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            try {
                CursorReadUtil.read(cursor);
            } finally {
                cursor.close();
            }
            return true;
        } else {
            return false;
        }
    }
}
