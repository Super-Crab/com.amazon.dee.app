package com.amazon.deecomms.calling.incallcommands.models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public abstract class InCallCommandModel {
    private static final String TAG = HalloInCallCommandModel.class.getSimpleName();
    protected String callId;
    protected AlexaContextProvider context;
    protected AlexaHeader header;
    protected String inCallCommandName;
    protected AlexaPayload payload;

    public InCallCommandModel(@NonNull Intent intent) {
        Bundle extras = intent.getExtras();
        this.inCallCommandName = extras.getString("inCallEvent", "");
        this.callId = extras.getString("callId", "");
        if (this.callId.isEmpty() || this.inCallCommandName.isEmpty()) {
            Log.i(TAG, toString());
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("callId is: ");
            outline1.append(this.callId);
            outline1.append("or inCallCommand: ");
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline91(outline1, this.inCallCommandName, " is empty"));
        }
    }

    @Nullable
    public synchronized AlexaContextProvider getContext() {
        return this.context;
    }

    @NonNull
    public synchronized AlexaHeader getHeader() {
        return this.header;
    }

    @NonNull
    public synchronized String getInCallCommandName() {
        return this.inCallCommandName;
    }

    @NonNull
    public synchronized AlexaPayload getPayload() {
        return this.payload;
    }

    public abstract boolean hasContext();

    @NonNull
    public synchronized String toString() {
        StringBuilder outline1;
        outline1 = GeneratedOutlineSupport.outline1("callId: ");
        outline1.append(this.callId);
        outline1.append(", inCallCommandName: ");
        outline1.append(this.inCallCommandName);
        return outline1.toString();
    }

    public InCallCommandModel(@NonNull AlexaEvent alexaEvent, @NonNull AlexaContextProvider alexaContextProvider, @NonNull String str) {
        this.header = alexaEvent.getAlexaHeader();
        this.payload = alexaEvent.getAlexaPayload();
        this.context = alexaContextProvider;
        this.inCallCommandName = this.header.getName();
        this.callId = str;
    }
}
