package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class cd {
    private static final String a = bj.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaVisualTask alexaVisualTask) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaVisualTask, "The provided AlexaVisualTask was null.");
        if (alexaServicesConnection.isVisualTaskScheduled(alexaVisualTask)) {
            String str = a;
            Log.w(str, "Task: " + alexaVisualTask + " is already scheduled.");
            return;
        }
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).schedule(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaVisualTask));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaVisualTask alexaVisualTask) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaVisualTask, "The provided AlexaVisualTask was null.");
        MessageReceiver<ca> removeListener = alexaServicesConnection.removeListener(alexaVisualTask);
        if (removeListener == null) {
            Log.e(a, "can't unschedule AlexaVisualTask");
            return;
        }
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).unschedule(alexaServicesConnection.getClient(), removeListener);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
