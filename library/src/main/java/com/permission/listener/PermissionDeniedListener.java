package com.permission.listener;

import java.util.List;


public interface PermissionDeniedListener {
    void onDenied(List<String> perms);
}
