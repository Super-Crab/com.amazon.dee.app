package com.amazon.alexa.sensor.location;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.sensor.location.-$$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM implements OnFailureListener {
    public static final /* synthetic */ $$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM INSTANCE = new $$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM();

    private /* synthetic */ $$Lambda$LocationApiManager$XmyIUPOaft7fzA7tLBtUCD3LUeM() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        Log.e(LocationApiManager.TAG, "Cannot start all location tracking", exc);
    }
}
