package com.amazon.dee.app.services.messaging;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.messaging.MessagingService;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
/* loaded from: classes12.dex */
public class FirebaseMessageHandler extends FirebaseMessagingService {
    private static final String TAG = Log.tag(FirebaseMessageHandler.class);
    private FirebaseCloudMessagingService messagingService;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MessagingService messagingService = ((AlexaApplication) getApplication()).getComponent().messagingService();
        if (messagingService instanceof FirebaseCloudMessagingService) {
            this.messagingService = (FirebaseCloudMessagingService) messagingService;
        } else {
            Log.w(TAG, "Messaging service is not firebase but firebase instance id listener is invoked!");
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String from = remoteMessage.getFrom();
        Map<String, String> data = remoteMessage.getData();
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        String str = "From: " + from;
        String str2 = "Message: " + bundle;
        if (this.messagingService == null || bundle.isEmpty()) {
            return;
        }
        this.messagingService.handleMessage(bundle);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(@NonNull String str) {
        GeneratedOutlineSupport1.outline158("Refreshed token: ", str);
        FirebaseCloudMessagingService firebaseCloudMessagingService = this.messagingService;
        if (firebaseCloudMessagingService != null) {
            firebaseCloudMessagingService.register(true);
        } else {
            Log.w(TAG, "Messaging service is null; new token received but not used.");
        }
    }
}
