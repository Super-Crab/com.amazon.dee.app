package com.amazon.alexa.voice.enablement;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.enablement.-$$Lambda$VoiceIdentityAdapter$9Oul4WF0H-NEb-M2BeOibLD0shE  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$VoiceIdentityAdapter$9Oul4WF0HNEbM2BeOibLD0shE implements Action1 {
    public static final /* synthetic */ $$Lambda$VoiceIdentityAdapter$9Oul4WF0HNEbM2BeOibLD0shE INSTANCE = new $$Lambda$VoiceIdentityAdapter$9Oul4WF0HNEbM2BeOibLD0shE();

    private /* synthetic */ $$Lambda$VoiceIdentityAdapter$9Oul4WF0HNEbM2BeOibLD0shE() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(VoiceIdentityAdapter.TAG, "Error refreshing user during EULA failure.", (Throwable) obj);
    }
}
