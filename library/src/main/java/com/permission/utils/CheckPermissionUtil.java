package com.permission.utils;

import android.content.Context;
import android.util.LruCache;
import com.permission.entity.PermissionType;
import com.permission.utils.checkDetailPermissionUtils.*;

public class CheckPermissionUtil {
    private static LruCache<String, Check> mCheckCahche = new LruCache<>(10);
    private static Check getCheck(String permission){
        Check check = mCheckCahche.get(permission);
        if (check != null){
            return check;
        }
        switch (permission){
            case PermissionType.READ_CALENDAR:
                check = new CheckREAD_CALENDAR();
                break;
            case PermissionType.WRITE_CALENDAR:
                check = new CheckWRITE_CALENDAR();
                break;
            case PermissionType.CAMERA:
                check = new CheckCamera();
                break;
            case PermissionType.READ_CONTACTS:
                check = new CheckREAD_CONTACTS();
                break;
            case PermissionType.WRITE_CONTACTS:
                check = new CheckWRITE_CONTACTS();
                break;
            case PermissionType.GET_ACCOUNTS:
                check = new CheckGET_ACCOUNTS();
                break;
            case PermissionType.ACCESS_COARSE_LOCATION:
            case PermissionType.ACCESS_FINE_LOCATION:
                check = new CheckLOCATION();
                break;
            case PermissionType.RECORD_AUDIO:
                check = new CheckRECORD_AUDIO();
                break;
            case PermissionType.READ_PHONE_STATE:
                check = new CheckREAD_PHONE_STATE();
                break;
            case PermissionType.CALL_PHONE:
                check = new CheckCALL_PHONE();
                break;
            case PermissionType.READ_CALL_LOG:
                check = new CheckREAD_CALL_LOG();
                break;
            case PermissionType.WRITE_CALL_LOG:
                check = new CheckWRITE_CALL_LOG();
                break;
            case PermissionType.ADD_VOICEMAIL:
                check = new CheckADD_VOICEMAIL();
                break;
            case PermissionType.USE_SIP:
                check = new CheckUSE_SIP();
                break;
            case PermissionType.PROCESS_OUTGOING_CALLS:
                check = new CheckPROCESS_OUTGOING_CALLS();
                break;
            case PermissionType.BODY_SENSORS:
                check = new CheckBODY_SENSORS();
                break;
            case PermissionType.SEND_SMS:
                check = new CheckSEND_SMS();
                break;
            case PermissionType.RECEIVE_MMS:
                check = new CheckRECEIVE_MMS();
                break;
            case PermissionType.READ_SMS:
                check = new CheckREAD_SMS();
                break;
            case PermissionType.RECEIVE_WAP_PUSH:
                check = new CheckRECEIVE_WAP_PUSH();
                break;
            case PermissionType.RECEIVE_SMS:
                check = new CheckRECEIVE_SMS();
                break;
            case PermissionType.READ_EXTERNAL_STORAGE:
                check = new CheckREAD_EXTERNAL_STORAGE();
                break;
            case PermissionType.WRITE_EXTERNAL_STORAGE:
                check = new CheckWRITE_EXTERNAL_STORAGE();
                break;
        }
        if (check != null){
            mCheckCahche.put(permission,check);
        }
        return check;
    }
    public static boolean checkHasPermisson(Context context, String permission){
        try {
            Check check = getCheck(permission);
            if (check != null){
                return check.check(context);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



}
