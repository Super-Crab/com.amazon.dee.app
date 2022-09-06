package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class SessionProto extends GeneratedMessageV3 implements SessionProtoOrBuilder {
    public static final int DURATION_FIELD_NUMBER = 4;
    public static final int SESSIONID_FIELD_NUMBER = 1;
    public static final int STARTTIMESTAMP_FIELD_NUMBER = 2;
    public static final int STOPTIMESTAMP_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private long duration_;
    private byte memoizedIsInitialized;
    private volatile Object sessionId_;
    private long startTimestamp_;
    private long stopTimestamp_;
    private static final SessionProto DEFAULT_INSTANCE = new SessionProto();
    private static final Parser<SessionProto> PARSER = new AbstractParser<SessionProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.SessionProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public SessionProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SessionProto(codedInputStream, extensionRegistryLite);
        }
    };

    public static SessionProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionProtoOuterClass.internal_static_protobuf_SessionProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static SessionProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SessionProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<SessionProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionProto)) {
            return super.equals(obj);
        }
        SessionProto sessionProto = (SessionProto) obj;
        return ((((getSessionId().equals(sessionProto.getSessionId())) && (getStartTimestamp() > sessionProto.getStartTimestamp() ? 1 : (getStartTimestamp() == sessionProto.getStartTimestamp() ? 0 : -1)) == 0) && (getStopTimestamp() > sessionProto.getStopTimestamp() ? 1 : (getStopTimestamp() == sessionProto.getStopTimestamp() ? 0 : -1)) == 0) && (getDuration() > sessionProto.getDuration() ? 1 : (getDuration() == sessionProto.getDuration() ? 0 : -1)) == 0) && this.unknownFields.equals(sessionProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
    public long getDuration() {
        return this.duration_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<SessionProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getSessionIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.sessionId_);
        }
        long j = this.startTimestamp_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(2, j);
        }
        long j2 = this.stopTimestamp_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeInt64Size(3, j2);
        }
        long j3 = this.duration_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeInt64Size(4, j3);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
    public String getSessionId() {
        Object obj = this.sessionId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sessionId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
    public ByteString getSessionIdBytes() {
        Object obj = this.sessionId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sessionId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
    public long getStartTimestamp() {
        return this.startTimestamp_;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
    public long getStopTimestamp() {
        return this.stopTimestamp_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getSessionId().hashCode();
        int hashLong = Internal.hashLong(getStartTimestamp());
        int hashLong2 = Internal.hashLong(getStopTimestamp());
        int hashLong3 = Internal.hashLong(getDuration());
        int hashCode2 = this.unknownFields.hashCode() + ((hashLong3 + ((((hashLong2 + ((((hashLong + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 29);
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionProtoOuterClass.internal_static_protobuf_SessionProto_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionProto.class, Builder.class);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getSessionIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.sessionId_);
        }
        long j = this.startTimestamp_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        long j2 = this.stopTimestamp_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(3, j2);
        }
        long j3 = this.duration_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(4, j3);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SessionProtoOrBuilder {
        private long duration_;
        private Object sessionId_;
        private long startTimestamp_;
        private long stopTimestamp_;

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionProtoOuterClass.internal_static_protobuf_SessionProto_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearDuration() {
            this.duration_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearSessionId() {
            this.sessionId_ = SessionProto.getDefaultInstance().getSessionId();
            onChanged();
            return this;
        }

        public Builder clearStartTimestamp() {
            this.startTimestamp_ = 0L;
            onChanged();
            return this;
        }

        public Builder clearStopTimestamp() {
            this.stopTimestamp_ = 0L;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionProtoOuterClass.internal_static_protobuf_SessionProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
        public long getDuration() {
            return this.duration_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
        public String getSessionId() {
            Object obj = this.sessionId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.sessionId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
        public ByteString getSessionIdBytes() {
            Object obj = this.sessionId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.sessionId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
        public long getStartTimestamp() {
            return this.startTimestamp_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.SessionProtoOrBuilder
        public long getStopTimestamp() {
            return this.stopTimestamp_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionProtoOuterClass.internal_static_protobuf_SessionProto_fieldAccessorTable.ensureFieldAccessorsInitialized(SessionProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setDuration(long j) {
            this.duration_ = j;
            onChanged();
            return this;
        }

        public Builder setSessionId(String str) {
            if (str != null) {
                this.sessionId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSessionIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.sessionId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setStartTimestamp(long j) {
            this.startTimestamp_ = j;
            onChanged();
            return this;
        }

        public Builder setStopTimestamp(long j) {
            this.stopTimestamp_ = j;
            onChanged();
            return this;
        }

        private Builder() {
            this.sessionId_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public SessionProto mo10084build() {
            SessionProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public SessionProto mo10085buildPartial() {
            SessionProto sessionProto = new SessionProto(this);
            sessionProto.sessionId_ = this.sessionId_;
            sessionProto.startTimestamp_ = this.startTimestamp_;
            sessionProto.stopTimestamp_ = this.stopTimestamp_;
            sessionProto.duration_ = this.duration_;
            onBuilt();
            return sessionProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public SessionProto mo10094getDefaultInstanceForType() {
            return SessionProto.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            this.sessionId_ = "";
            this.startTimestamp_ = 0L;
            this.stopTimestamp_ = 0L;
            this.duration_ = 0L;
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sessionId_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof SessionProto) {
                return mergeFrom((SessionProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SessionProto sessionProto) {
            if (sessionProto == SessionProto.getDefaultInstance()) {
                return this;
            }
            if (!sessionProto.getSessionId().isEmpty()) {
                this.sessionId_ = sessionProto.sessionId_;
                onChanged();
            }
            if (sessionProto.getStartTimestamp() != 0) {
                setStartTimestamp(sessionProto.getStartTimestamp());
            }
            if (sessionProto.getStopTimestamp() != 0) {
                setStopTimestamp(sessionProto.getStopTimestamp());
            }
            if (sessionProto.getDuration() != 0) {
                setDuration(sessionProto.getDuration());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) sessionProto).unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.SessionProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.SessionProto.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.SessionProto r3 = (com.amazon.alexa.mobilytics.protobuf.SessionProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.amazon.alexa.mobilytics.protobuf.SessionProto r4 = (com.amazon.alexa.mobilytics.protobuf.SessionProto) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.SessionProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.SessionProto$Builder");
        }
    }

    public static Builder newBuilder(SessionProto sessionProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(sessionProto);
    }

    public static SessionProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private SessionProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static SessionProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SessionProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public SessionProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static SessionProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private SessionProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.sessionId_ = "";
        this.startTimestamp_ = 0L;
        this.stopTimestamp_ = 0L;
        this.duration_ = 0L;
    }

    public static SessionProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static SessionProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static SessionProto parseFrom(InputStream inputStream) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SessionProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    private SessionProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.sessionId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.startTimestamp_ = codedInputStream.readInt64();
                            } else if (readTag == 24) {
                                this.stopTimestamp_ = codedInputStream.readInt64();
                            } else if (readTag != 32) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                this.duration_ = codedInputStream.readInt64();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.mo10084build();
                    makeExtensionsImmutable();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public static SessionProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SessionProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
