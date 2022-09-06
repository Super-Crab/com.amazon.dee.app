package com.amazon.alexa.comms.mediaInsights.service.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TransportPayload {
    @NonNull
    private byte[] bytes;
    @NonNull
    private String encoding;
    @NonNull
    private String type;

    /* loaded from: classes6.dex */
    public static class TransportPayloadBuilder {
        private byte[] bytes;
        private String encoding;
        private String type;

        TransportPayloadBuilder() {
        }

        public TransportPayload build() {
            return new TransportPayload(this.bytes, this.encoding, this.type);
        }

        public TransportPayloadBuilder bytes(byte[] bArr) {
            this.bytes = bArr;
            return this;
        }

        public TransportPayloadBuilder encoding(String str) {
            this.encoding = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransportPayload.TransportPayloadBuilder(bytes=");
            outline107.append(Arrays.toString(this.bytes));
            outline107.append(", encoding=");
            outline107.append(this.encoding);
            outline107.append(", type=");
            return GeneratedOutlineSupport1.outline91(outline107, this.type, ")");
        }

        public TransportPayloadBuilder type(String str) {
            this.type = str;
            return this;
        }
    }

    TransportPayload(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2) {
        if (bArr != null) {
            if (str == null) {
                throw new IllegalArgumentException("encoding is null");
            }
            if (str2 == null) {
                throw new IllegalArgumentException("type is null");
            }
            this.bytes = bArr;
            this.encoding = str;
            this.type = str2;
            return;
        }
        throw new IllegalArgumentException("bytes is null");
    }

    public static TransportPayloadBuilder builder() {
        return new TransportPayloadBuilder();
    }

    @NonNull
    public byte[] getBytes() {
        return this.bytes;
    }

    @NonNull
    public String getEncoding() {
        return this.encoding;
    }

    @NonNull
    public String getType() {
        return this.type;
    }
}
