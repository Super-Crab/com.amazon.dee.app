package com.amazon.alexa.eventbus.api;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Base64;
import android.util.JsonWriter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes7.dex */
public final class Message implements Parcelable {
    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() { // from class: com.amazon.alexa.eventbus.api.Message.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public Message mo1286createFromParcel(Parcel parcel) {
            return new Message(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public Message[] mo1287newArray(int i) {
            return new Message[i];
        }
    };
    private static final String JSON_FIELD_DATE = "date";
    private static final String JSON_FIELD_EVENT_TYPE = "eventType";
    private static final String JSON_FIELD_PAYLOAD = "payload";
    private static final String JSON_FIELD_PAYLOAD_TYPE = "payloadType";
    private static final String JSON_FIELD_SOURCE = "source";
    private static final String JSON_FIELD_TTL = "ttl";
    private static final String JSON_FIELD_UUID = "uuid";
    private final long date;
    private final String eventType;
    private final byte[] payload;
    private final PayloadType payloadType;
    private final Source source;
    private final long ttl;
    private final UUID uuid;

    /* renamed from: com.amazon.alexa.eventbus.api.Message$2  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType = new int[PayloadType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType[PayloadType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType[PayloadType.Binary.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public enum PayloadType {
        String,
        Binary;
        
        private static final String BINARY = "binary";
        private static final String STRING = "string";

        public static PayloadType fromString(@Nullable String str) {
            if (str == null) {
                return null;
            }
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1388966911) {
                if (hashCode == -891985903 && str.equals(STRING)) {
                    c = 0;
                }
            } else if (str.equals(BINARY)) {
                c = 1;
            }
            if (c == 0) {
                return String;
            }
            if (c == 1) {
                return Binary;
            }
            return null;
        }

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase(Locale.US);
        }
    }

    /* loaded from: classes7.dex */
    public enum Source {
        Local,
        TComm;
        
        private static final String LOCAL = "Local";
        private static final String TCOMM = "TComm";

        public static Source fromString(@Nullable String str) {
            if (str == null) {
                return Local;
            }
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != 73592651) {
                if (hashCode == 79681920 && str.equals("TComm")) {
                    c = 1;
                }
            } else if (str.equals(LOCAL)) {
                c = 0;
            }
            if (c == 0) {
                return Local;
            }
            if (c != 1) {
                return Local;
            }
            return TComm;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (r3.equals("uuid") != false) goto L9;
     */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.amazon.alexa.eventbus.api.Message fromJSON(@androidx.annotation.NonNull android.util.JsonReader r8) throws java.lang.Exception {
        /*
            com.amazon.alexa.eventbus.api.Message$Builder r0 = new com.amazon.alexa.eventbus.api.Message$Builder
            r0.<init>()
            r8.beginObject()
            r1 = 0
            r2 = r1
        La:
            boolean r3 = r8.hasNext()
            r4 = 0
            r5 = 1
            if (r3 == 0) goto Lb6
            java.lang.String r3 = r8.nextName()
            r6 = -1
            int r7 = r3.hashCode()
            switch(r7) {
                case -896505829: goto L5a;
                case -786701938: goto L50;
                case 115180: goto L46;
                case 3076014: goto L3c;
                case 3601339: goto L33;
                case 31430900: goto L29;
                case 909929960: goto L1f;
                default: goto L1e;
            }
        L1e:
            goto L64
        L1f:
            java.lang.String r4 = "payloadType"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = 5
            goto L65
        L29:
            java.lang.String r4 = "eventType"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = 2
            goto L65
        L33:
            java.lang.String r5 = "uuid"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L64
            goto L65
        L3c:
            java.lang.String r4 = "date"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = 3
            goto L65
        L46:
            java.lang.String r4 = "ttl"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = 4
            goto L65
        L50:
            java.lang.String r4 = "payload"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = 6
            goto L65
        L5a:
            java.lang.String r4 = "source"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L64
            r4 = r5
            goto L65
        L64:
            r4 = r6
        L65:
            switch(r4) {
                case 0: goto La9;
                case 1: goto L9c;
                case 2: goto L93;
                case 3: goto L8a;
                case 4: goto L82;
                case 5: goto L75;
                case 6: goto L6c;
                default: goto L68;
            }
        L68:
            r8.skipValue()
            goto La
        L6c:
            java.lang.String r2 = r8.nextString()     // Catch: java.lang.IllegalStateException -> L71
            goto La
        L71:
            r8.skipValue()
            goto La
        L75:
            java.lang.String r3 = r8.nextString()     // Catch: java.lang.IllegalStateException -> L7e
            com.amazon.alexa.eventbus.api.Message$PayloadType r1 = com.amazon.alexa.eventbus.api.Message.PayloadType.fromString(r3)     // Catch: java.lang.IllegalStateException -> L7e
            goto La
        L7e:
            r8.skipValue()
            goto La
        L82:
            long r3 = r8.nextLong()
            r0.setTTL(r3)
            goto La
        L8a:
            long r3 = r8.nextLong()
            r0.setDate(r3)
            goto La
        L93:
            java.lang.String r3 = r8.nextString()
            r0.setEventType(r3)
            goto La
        L9c:
            java.lang.String r3 = r8.nextString()
            com.amazon.alexa.eventbus.api.Message$Source r3 = com.amazon.alexa.eventbus.api.Message.Source.fromString(r3)
            r0.setSource(r3)
            goto La
        La9:
            java.lang.String r3 = r8.nextString()
            java.util.UUID r3 = java.util.UUID.fromString(r3)
            com.amazon.alexa.eventbus.api.Message.Builder.access$100(r0, r3)
            goto La
        Lb6:
            r8.endObject()
            if (r1 == 0) goto Lcf
            int r8 = r1.ordinal()
            if (r8 == 0) goto Lcc
            if (r8 == r5) goto Lc4
            goto Lcf
        Lc4:
            byte[] r8 = android.util.Base64.decode(r2, r4)
            r0.setPayload(r8)
            goto Lcf
        Lcc:
            r0.setPayload(r2)
        Lcf:
            com.amazon.alexa.eventbus.api.Message r8 = r0.build()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.eventbus.api.Message.fromJSON(android.util.JsonReader):com.amazon.alexa.eventbus.api.Message");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Message.class == obj.getClass()) {
            return this.uuid.equals(((Message) obj).uuid);
        }
        return false;
    }

    public long getDate() {
        return this.date;
    }

    public String getEventType() {
        return this.eventType;
    }

    public byte[] getPayload() {
        byte[] bArr = this.payload;
        if (bArr == null) {
            return null;
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getPayloadAsString() {
        return this.payloadType == PayloadType.String ? new String(this.payload, StandardCharsets.UTF_8) : "";
    }

    public PayloadType getPayloadType() {
        return this.payloadType;
    }

    public Source getSource() {
        return this.source;
    }

    public long getTTL() {
        return this.ttl;
    }

    @NonNull
    public UUID getUUID() {
        return this.uuid;
    }

    public int hashCode() {
        return Objects.hash(this.uuid);
    }

    public void writeToJSON(@NonNull JsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject().name("uuid").value(this.uuid.toString()).name("source").value(this.source.toString()).name(JSON_FIELD_EVENT_TYPE).value(this.eventType).name("date").value(this.date).name("ttl").value(this.ttl);
        if (this.payloadType == null) {
            jsonWriter.name(JSON_FIELD_PAYLOAD_TYPE).nullValue().name("payload").nullValue();
        } else {
            jsonWriter.name(JSON_FIELD_PAYLOAD_TYPE).value(this.payloadType.toString());
            int ordinal = this.payloadType.ordinal();
            if (ordinal == 0) {
                jsonWriter.name("payload").value(new String(this.payload, StandardCharsets.UTF_8));
            } else if (ordinal != 1) {
                jsonWriter.name("payload").nullValue();
            } else {
                jsonWriter.name("payload").value(Base64.encodeToString(this.payload, 0));
            }
        }
        jsonWriter.endObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(new ParcelUuid(this.uuid), i);
        parcel.writeSerializable(this.source);
        parcel.writeString(this.eventType);
        parcel.writeLong(this.date);
        parcel.writeLong(this.ttl);
        parcel.writeSerializable(this.payloadType);
        byte[] bArr = this.payload;
        if (bArr == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(bArr.length);
        parcel.writeByteArray(this.payload);
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private String eventType;
        private byte[] payload;
        private PayloadType payloadType;
        private Source source;
        private UUID uuid;
        private long date = System.currentTimeMillis();
        private long ttl = 0;

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public Builder setUUID(@NonNull UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        @NonNull
        public Message build() {
            return new Message(this.source, this.eventType, this.date, this.ttl, this.payloadType, this.payload, this.uuid);
        }

        @NonNull
        public Builder setDate(long j) {
            this.date = j;
            return this;
        }

        @NonNull
        public Builder setEventType(String str) {
            this.eventType = str;
            return this;
        }

        @NonNull
        public Builder setPayload(byte[] bArr) {
            this.payload = Arrays.copyOf(bArr, bArr.length);
            this.payloadType = PayloadType.Binary;
            return this;
        }

        @NonNull
        public Builder setSource(@NonNull Source source) {
            this.source = source;
            return this;
        }

        @NonNull
        public Builder setTTL(long j) {
            this.ttl = j;
            return this;
        }

        public Builder setPayload(String str) {
            this.payload = str.getBytes(StandardCharsets.UTF_8);
            this.payloadType = PayloadType.String;
            return this;
        }
    }

    private Message(Source source, String str, long j, long j2, PayloadType payloadType, byte[] bArr, @Nullable UUID uuid) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.source = source == null ? Source.Local : source;
        this.eventType = str;
        this.date = j;
        this.ttl = j2;
        this.payloadType = payloadType;
        this.payload = bArr;
    }

    private Message(Parcel parcel) {
        this.uuid = ((ParcelUuid) parcel.readParcelable(Message.class.getClassLoader())).getUuid();
        this.source = (Source) parcel.readSerializable();
        this.eventType = parcel.readString();
        this.date = parcel.readLong();
        this.ttl = parcel.readLong();
        this.payloadType = (PayloadType) parcel.readSerializable();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.payload = new byte[readInt];
            parcel.readByteArray(this.payload);
            return;
        }
        this.payload = null;
    }
}
