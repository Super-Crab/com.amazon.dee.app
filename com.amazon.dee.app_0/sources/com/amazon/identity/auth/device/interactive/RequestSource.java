package com.amazon.identity.auth.device.interactive;

import android.content.Context;
/* loaded from: classes12.dex */
public interface RequestSource {
    Object getBackingObject();

    Context getContext();

    InteractiveState getInteractiveState();

    void onStartRequest(InteractiveRequestRecord interactiveRequestRecord);
}
