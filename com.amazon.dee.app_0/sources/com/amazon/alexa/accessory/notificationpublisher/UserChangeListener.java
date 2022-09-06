package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.identity.api.UserIdentity;
/* loaded from: classes.dex */
public interface UserChangeListener {
    void onUserLogin(UserIdentity userIdentity);

    void onUserLogout(UserIdentity userIdentity);

    void onUserProfileUpdated(UserIdentity userIdentity);
}
