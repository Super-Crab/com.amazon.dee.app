package com.amazon.alexa.sensor.location;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.sensor.location.-$$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608 implements OnFailureListener {
    public static final /* synthetic */ $$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608 INSTANCE = new $$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608();

    private /* synthetic */ $$Lambda$LocationApiManager$dVGYKOHOwgR6nM83Gi8PzET_608() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        Log.e(LocationApiManager.TAG, "Cannot start significant location tracking", exc);
    }
}
