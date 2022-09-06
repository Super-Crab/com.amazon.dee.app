package com.amazon.device.messaging.test;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.adm.server.Message;
import com.amazon.adm.server.MulticastResult;
import com.amazon.adm.server.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class ADMEnqueuer {
    static final String ARG_CLIENTID = "clientId";
    static final String ARG_MESSAGE = "message";
    static final String ARG_REGID = "regId";
    static final String ARG_REGIDS = "regIds";
    static final String ARG_RETRIES = "retries";
    static final String ARG_SECRET = "secret";
    static final String CALL_EXCEPTION = "exception";
    static final String CALL_RESPONSE = "response";
    static final String METHOD_SEND = "send";
    private final String mClientId;
    private final ContentResolver mResolver;
    private final String mSecret;
    static final String AUTHORITY = "com.amazon.device.messaging.enqueuer";
    static final Uri CONTENT_URI = new Uri.Builder().scheme("content").authority(AUTHORITY).build();

    public ADMEnqueuer(Context context, String str, String str2) {
        this.mResolver = context.getContentResolver();
        this.mClientId = str;
        this.mSecret = str2;
    }

    public Result send(Message message, String str, int i) throws IOException {
        Bundle bundle = new Bundle();
        bundle.putString("clientId", this.mClientId);
        bundle.putString(ARG_SECRET, this.mSecret);
        bundle.putSerializable("message", message);
        bundle.putString(ARG_REGID, str);
        bundle.putInt(ARG_RETRIES, i);
        return (Result) send(bundle);
    }

    public Result sendNoRetry(Message message, String str) throws IOException {
        return send(message, str, 0);
    }

    public MulticastResult sendNoRetry(Message message, List<String> list) throws IOException {
        return send(message, list, 0);
    }

    public MulticastResult send(Message message, List<String> list, int i) throws IOException {
        Bundle bundle = new Bundle();
        bundle.putString("clientId", this.mClientId);
        bundle.putString(ARG_SECRET, this.mSecret);
        bundle.putSerializable("message", message);
        bundle.putStringArrayList(ARG_REGIDS, new ArrayList<>(list));
        bundle.putInt(ARG_RETRIES, i);
        return (MulticastResult) send(bundle);
    }

    private <T> T send(Bundle bundle) throws IOException {
        Bundle call = this.mResolver.call(CONTENT_URI, METHOD_SEND, (String) null, bundle);
        if (!call.containsKey("exception")) {
            return (T) call.getSerializable("response");
        }
        throw ((IOException) call.getSerializable("exception"));
    }
}
