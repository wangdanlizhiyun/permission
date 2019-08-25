package com.permission.entity;


import com.permission.listener.PermissionDeniedListener;

public class PermissionRequest {
    String[] permissions;
    PermissionDeniedListener permissionDeniedListener;
    Runnable runnable;
    public Boolean hasRequested = false;

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public PermissionDeniedListener getPermissionDeniedListener() {
        return permissionDeniedListener;
    }

    public void setPermissionDeniedListener(PermissionDeniedListener permissionDeniedListener) {
        this.permissionDeniedListener = permissionDeniedListener;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
