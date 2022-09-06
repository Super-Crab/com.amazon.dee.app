package com.amazon.alexa.identity.api;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public interface UserProfileManager {
    @Nullable
    UserProfile getCurrentProfile();

    UserProfile getCurrentProfile(String str);
}
