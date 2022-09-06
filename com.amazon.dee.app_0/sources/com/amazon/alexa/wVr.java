package com.amazon.alexa;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.client.annotations.NonNull;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: MuteStateChangedEventSender.java */
@Singleton
/* loaded from: classes.dex */
public class wVr {
    public final Context BIo;
    public final String zZm;

    @Inject
    public wVr(@NonNull Context context) {
        this.BIo = context;
        this.zZm = context.getApplicationContext().getPackageName();
    }

    public void zZm(boolean z) {
        Intent intent = new Intent("com.amazon.alexa.intent.action.MUTE_STATE_CHANGED_ACTION");
        intent.setPackage(this.zZm);
        intent.putExtra("isMute", z);
        this.BIo.sendBroadcast(intent);
    }
}
