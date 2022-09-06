package com.amazon.alexa.comms.mediaInsights;

import android.util.Base64;
import java.nio.charset.Charset;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TraceHelper {
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    @NonNull
    TraceRecorder traceRecorder;

    public TraceHelper(@NonNull TraceRecorder traceRecorder) {
        if (traceRecorder != null) {
            this.traceRecorder = traceRecorder;
            return;
        }
        throw new IllegalArgumentException("traceRecorder is null");
    }

    public static String base64EncodePayload(String str) {
        return Base64.encodeToString(str.getBytes(UTF8_CHARSET), 2);
    }

    public void recordChildTrace(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Trace trace) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("payloadEncoding is null");
            }
            if (str3 == null) {
                throw new IllegalArgumentException("payloadData is null");
            }
            if (trace != null) {
                try {
                    trace.newChildTrace().withType(str).withPayloadData(base64EncodePayload(str3)).withPayloadEncoding(str2).build().record();
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            throw new IllegalArgumentException("trace is null");
        }
        throw new IllegalArgumentException("type is null");
    }

    public void recordTrace(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("payloadEncoding is null");
            }
            if (str3 != null) {
                try {
                    Trace.builder(this.traceRecorder).withType(str).withPayloadData(base64EncodePayload(str3)).withPayloadEncoding(str2).build().record();
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            throw new IllegalArgumentException("payloadData is null");
        }
        throw new IllegalArgumentException("type is null");
    }
}
