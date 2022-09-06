package com.amazon.alexa.mode.service;

import android.content.Context;
import com.amazon.alexa.mode.ModeService;
/* loaded from: classes9.dex */
public final class ModeServiceFactory {
    private ModeServiceFactory() {
        throw new IllegalStateException("No instances!");
    }

    public static ModeService create(Context context) {
        return new ModeServiceWrapper(context);
    }
}
