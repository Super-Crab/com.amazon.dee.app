package com.amazon.alexa.voice.handsfree.mike.vesper.notifications.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.voice.handsfree.mike.vesper.notifications.services.AppDownloadNotifierService;
/* loaded from: classes11.dex */
public class AppDownloadTimeBasedReceiver extends BroadcastReceiver {
    private static final String TAG = AppDownloadTimeBasedReceiver.class.getSimpleName();
    private final Initializer mInitializer = InitializerProvider.getInitializer();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        this.mInitializer.initialize(context);
        AppDownloadNotifierService.enqueueWork(context, intent);
    }
}
