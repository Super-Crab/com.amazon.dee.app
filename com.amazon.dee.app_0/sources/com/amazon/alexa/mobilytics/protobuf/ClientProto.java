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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class ClientProto extends GeneratedMessageV3 implements ClientProtoOrBuilder {
    public static final int CLIENTID_FIELD_NUMBER = 2;
    public static final int COGNITOID_FIELD_NUMBER = 1;
    private static final ClientProto DEFAULT_INSTANCE = new ClientProto();
    private static final Parser<ClientProto> PARSER = new AbstractParser<ClientProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.ClientProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public ClientProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ClientProto(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private volatile Object clientId_;
    private volatile Object cognitoId_;
    private byte memoizedIsInitialized;

    public static ClientProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ClientProtoOuterClass.internal_static_protobuf_ClientProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static ClientProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ClientProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<ClientProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientProto)) {
            return super.equals(obj);
        }
        ClientProto clientProto = (ClientProto) obj;
        return ((getCognitoId().equals(clientProto.getCognitoId())) && getClientId().equals(clientProto.getClientId())) && this.unknownFields.equals(clientProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
    public String getClientId() {
        Object obj = this.clientId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.clientId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
    public ByteString getClientIdBytes() {
        Object obj = this.clientId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.clientId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
    public String getCognitoId() {
        Object obj = this.cognitoId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.cognitoId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
    public ByteString getCognitoIdBytes() {
        Object obj = this.cognitoId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.cognitoId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<ClientProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getCognitoIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.cognitoId_);
        }
        if (!getClientIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.clientId_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
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
        int hashCode = getCognitoId().hashCode();
        int hashCode2 = getClientId().hashCode();
        int hashCode3 = this.unknownFields.hashCode() + ((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 29);
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ClientProtoOuterClass.internal_static_protobuf_ClientProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientProto.class, Builder.class);
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
        if (!getCognitoIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.cognitoId_);
        }
        if (!getClientIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.clientId_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClientProtoOrBuilder {
        private Object clientId_;
        private Object cognitoId_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ClientProtoOuterClass.internal_static_protobuf_ClientProto_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearClientId() {
            this.clientId_ = ClientProto.getDefaultInstance().getClientId();
            onChanged();
            return this;
        }

        public Builder clearCognitoId() {
            this.cognitoId_ = ClientProto.getDefaultInstance().getCognitoId();
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
        public String getClientId() {
            Object obj = this.clientId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.clientId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
        public ByteString getClientIdBytes() {
            Object obj = this.clientId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clientId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
        public String getCognitoId() {
            Object obj = this.cognitoId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.cognitoId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ClientProtoOrBuilder
        public ByteString getCognitoIdBytes() {
            Object obj = this.cognitoId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cognitoId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ClientProtoOuterClass.internal_static_protobuf_ClientProto_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ClientProtoOuterClass.internal_static_protobuf_ClientProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setClientId(String str) {
            if (str != null) {
                this.clientId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setClientIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.clientId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCognitoId(String str) {
            if (str != null) {
                this.cognitoId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCognitoIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.cognitoId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.cognitoId_ = "";
            this.clientId_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public ClientProto mo10084build() {
            ClientProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public ClientProto mo10085buildPartial() {
            ClientProto clientProto = new ClientProto(this);
            clientProto.cognitoId_ = this.cognitoId_;
            clientProto.clientId_ = this.clientId_;
            onBuilt();
            return clientProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ClientProto mo10094getDefaultInstanceForType() {
            return ClientProto.getDefaultInstance();
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
            this.cognitoId_ = "";
            this.clientId_ = "";
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.cognitoId_ = "";
            this.clientId_ = "";
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
            if (message instanceof ClientProto) {
                return mergeFrom((ClientProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ClientProto clientProto) {
            if (clientProto == ClientProto.getDefaultInstance()) {
                return this;
            }
            if (!clientProto.getCognitoId().isEmpty()) {
                this.cognitoId_ = clientProto.cognitoId_;
                onChanged();
            }
            if (!clientProto.getClientId().isEmpty()) {
                this.clientId_ = clientProto.clientId_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) clientProto).unknownFields);
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
        public com.amazon.alexa.mobilytics.protobuf.ClientProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.ClientProto.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.ClientProto r3 = (com.amazon.alexa.mobilytics.protobuf.ClientProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.ClientProto r4 = (com.amazon.alexa.mobilytics.protobuf.ClientProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.ClientProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.ClientProto$Builder");
        }
    }

    public static Builder newBuilder(ClientProto clientProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(clientProto);
    }

    public static ClientProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private ClientProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static ClientProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public ClientProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static ClientProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private ClientProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.cognitoId_ = "";
        this.clientId_ = "";
    }

    public static ClientProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static ClientProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static ClientProto parseFrom(InputStream inputStream) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    private ClientProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.cognitoId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag != 18) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.clientId_ = codedInputStream.readStringRequireUtf8();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
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

    public static ClientProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ClientProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
