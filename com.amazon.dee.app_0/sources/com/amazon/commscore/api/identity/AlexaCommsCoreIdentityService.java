package com.amazon.commscore.api.identity;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface AlexaCommsCoreIdentityService {
    public static final String EVENT_IDENTITY_CHANGED = "alexa::comms::core::identity_changed";

    @Nullable
    CommsCoreIdentity getIdentity();
}
