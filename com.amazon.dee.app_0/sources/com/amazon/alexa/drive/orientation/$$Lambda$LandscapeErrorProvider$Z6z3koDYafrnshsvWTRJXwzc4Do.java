package com.amazon.alexa.drive.orientation;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.drive.orientation.-$$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do implements Consumer {
    public static final /* synthetic */ $$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do INSTANCE = new $$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do();

    private /* synthetic */ $$Lambda$LandscapeErrorProvider$Z6z3koDYafrnshsvWTRJXwzc4Do() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(LandscapeErrorProvider.TAG, "Exception in connectedAutomotiveDeviceGroups", (Throwable) obj);
    }
}
