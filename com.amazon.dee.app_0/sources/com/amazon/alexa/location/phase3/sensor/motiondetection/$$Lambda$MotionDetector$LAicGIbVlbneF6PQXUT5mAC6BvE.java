package com.amazon.alexa.location.phase3.sensor.motiondetection;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.phase3.sensor.motiondetection.-$$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE implements Consumer {
    public static final /* synthetic */ $$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE INSTANCE = new $$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE();

    private /* synthetic */ $$Lambda$MotionDetector$LAicGIbVlbneF6PQXUT5mAC6BvE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(MotionDetector.TAG, "Failed to store", (Throwable) obj);
    }
}
