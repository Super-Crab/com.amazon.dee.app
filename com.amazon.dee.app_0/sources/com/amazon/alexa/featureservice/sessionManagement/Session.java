package com.amazon.alexa.featureservice.sessionManagement;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.util.UUID;
import org.joda.time.DateTimeUtils;
/* loaded from: classes7.dex */
public class Session {
    private static final String TAG = "Session";
    @VisibleForTesting
    long appBackgroundedTime;
    private UUID sessionId = UUID.randomUUID();

    public Session() {
        Log.i(TAG, String.format("New session created with session ID: %s", this.sessionId));
        this.appBackgroundedTime = -1L;
    }

    public long getAppBackgroundedTime() {
        return this.appBackgroundedTime;
    }

    public UUID getSessionId() {
        return this.sessionId;
    }

    public void setAppBackgroundedTime() {
        this.appBackgroundedTime = DateTimeUtils.currentTimeMillis();
    }
}
