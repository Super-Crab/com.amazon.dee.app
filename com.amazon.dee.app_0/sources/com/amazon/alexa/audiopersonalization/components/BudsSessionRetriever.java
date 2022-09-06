package com.amazon.alexa.audiopersonalization.components;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessoryclient.client.AlexaAccessoryClient;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public class BudsSessionRetriever {
    private static final String TAG = "BudsSessionRetriever";
    private boolean hasStarted = false;
    private AlexaAccessoryClient mAccessoryClient;

    public BudsSessionRetriever(@NonNull AlexaAccessoryClient alexaAccessoryClient) {
        this.mAccessoryClient = alexaAccessoryClient;
    }

    @Nullable
    public AccessorySession getSessionFromMacAddress(String str) {
        if (!this.hasStarted) {
            Log.i(TAG, "getSessionFromMacAddress: session retriever has not been started");
            return null;
        }
        AccessorySession session = this.mAccessoryClient.getAccessories().getSessionSupplier().getSession(str);
        String str2 = TAG;
        Log.i(str2, "getSessionFromMacAddress: session retriever got session " + session);
        return session;
    }

    public void start() {
        if (this.hasStarted) {
            Log.i(TAG, "Attempted to start, when is already running");
            return;
        }
        Log.i(TAG, "Constructed a buds session monitor");
        this.hasStarted = true;
        this.mAccessoryClient.initialize();
    }

    public void stop() {
        Log.i(TAG, "Shutting down buds session monitor");
        this.mAccessoryClient.disconnect();
        this.hasStarted = false;
    }
}
