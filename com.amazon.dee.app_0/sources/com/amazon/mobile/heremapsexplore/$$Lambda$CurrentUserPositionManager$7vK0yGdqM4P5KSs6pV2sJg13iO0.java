package com.amazon.mobile.heremapsexplore;

import android.util.Log;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.mobile.heremapsexplore.-$$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0  reason: invalid class name */
/* loaded from: classes13.dex */
public final /* synthetic */ class $$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0 implements Consumer {
    public static final /* synthetic */ $$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0 INSTANCE = new $$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0();

    private /* synthetic */ $$Lambda$CurrentUserPositionManager$7vK0yGdqM4P5KSs6pV2sJg13iO0() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Log.e(Constants.LOG_TAG, "Failed to get most recent user location");
    }
}
