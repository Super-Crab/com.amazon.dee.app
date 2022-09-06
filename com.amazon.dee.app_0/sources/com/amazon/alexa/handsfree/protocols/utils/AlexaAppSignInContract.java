package com.amazon.alexa.handsfree.protocols.utils;

import android.content.Context;
/* loaded from: classes8.dex */
public interface AlexaAppSignInContract {
    boolean getSignInState(Context context, boolean z);

    void setup(Context context, boolean z);

    void teardown(Context context);
}
