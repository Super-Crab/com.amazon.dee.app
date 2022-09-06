package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
final class System {
    private static final String TAG = "System";

    private System() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setBaseURLs(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaBaseURLs alexaBaseURLs) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaBaseURLs, "The provided AlexaBaseURLs was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).setBaseURLs(alexaServicesConnection.getClient(), alexaBaseURLs);
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
