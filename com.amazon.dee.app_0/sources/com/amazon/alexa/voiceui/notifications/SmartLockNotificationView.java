package com.amazon.alexa.voiceui.notifications;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes11.dex */
public class SmartLockNotificationView {
    @VisibleForTesting
    static final String NOTIFICATION_ACTION = "amazon.alexa.voice.handsfree.REPORT_UTTERANCE_ON_LOCK_SCREEN";
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public SmartLockNotificationView(Context context) {
        this.context = context;
    }

    public void showNotification() {
        Intent intent = new Intent(NOTIFICATION_ACTION);
        intent.setPackage(this.context.getPackageName());
        this.context.sendBroadcast(intent);
    }
}
