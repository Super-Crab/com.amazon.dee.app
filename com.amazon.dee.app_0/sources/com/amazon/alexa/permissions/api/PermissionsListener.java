package com.amazon.alexa.permissions.api;

import java.util.List;
/* loaded from: classes9.dex */
public interface PermissionsListener {
    void onBlocked(List<String> list, List<String> list2);

    void onDenied(List<String> list);

    void onGranted();
}
