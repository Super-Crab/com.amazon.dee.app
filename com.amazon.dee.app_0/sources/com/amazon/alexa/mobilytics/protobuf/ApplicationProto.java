package com.amazon.alexa.mobilytics.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class ApplicationProto extends GeneratedMessageV3 implements ApplicationProtoOrBuilder {
    public static final int APPID_FIELD_NUMBER = 3;
    public static final int COGNITOIDENTITYPOOLID_FIELD_NUMBER = 5;
    public static final int PACKAGENAME_FIELD_NUMBER = 2;
    public static final int SDK_FIELD_NUMBER = 7;
    public static final int TITLE_FIELD_NUMBER = 1;
    public static final int VERSIONCODE_FIELD_NUMBER = 6;
    public static final int VERSIONNAME_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private volatile Object appId_;
    private volatile Object cognitoIdentityPoolId_;
    private byte memoizedIsInitialized;
    private volatile Object packageName_;
    private Sdk sdk_;
    private volatile Object title_;
    private volatile Object versionCode_;
    private volatile Object versionName_;
    private static final ApplicationProto DEFAULT_INSTANCE = new ApplicationProto();
    private static final Parser<ApplicationProto> PARSER = new AbstractParser<ApplicationProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.ApplicationProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public ApplicationProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ApplicationProto(codedInputStream, extensionRegistryLite);
        }
    };

    /* loaded from: classes9.dex */
    public static final class Sdk extends GeneratedMessageV3 implements SdkOrBuilder {
        public static final int NAME_FIELD_NUMBER = 2;
        public static final int VERSION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private volatile Object version_;
        private static final Sdk DEFAULT_INSTANCE = new Sdk();
        private static final Parser<Sdk> PARSER = new AbstractParser<Sdk>() { // from class: com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Sdk mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Sdk(codedInputStream, extensionRegistryLite);
            }
        };

        public static Sdk getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_Sdk_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Sdk parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Sdk) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Sdk parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<Sdk> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Sdk)) {
                return super.equals(obj);
            }
            Sdk sdk = (Sdk) obj;
            return ((getVersion().equals(sdk.getVersion())) && getName().equals(sdk.getName())) && this.unknownFields.equals(sdk.unknownFields);
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<Sdk> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getVersionBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.version_);
            }
            if (!getNameBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.name_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
        public String getVersion() {
            Object obj = this.version_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.version_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
        public ByteString getVersionBytes() {
            Object obj = this.version_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.version_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getVersion().hashCode();
            int hashCode2 = getName().hashCode();
            int hashCode3 = this.unknownFields.hashCode() + ((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 29);
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_Sdk_fieldAccessorTable.ensureFieldAccessorsInitialized(Sdk.class, Builder.class);
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
            if (!getVersionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.version_);
            }
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.name_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SdkOrBuilder {
            private Object name_;
            private Object version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_Sdk_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearName() {
                this.name_ = Sdk.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.version_ = Sdk.getDefaultInstance().getVersion();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_Sdk_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
            public String getVersion() {
                Object obj = this.version_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.version_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProto.SdkOrBuilder
            public ByteString getVersionBytes() {
                Object obj = this.version_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.version_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_Sdk_fieldAccessorTable.ensureFieldAccessorsInitialized(Sdk.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder setName(String str) {
                if (str != null) {
                    this.name_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setNameBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.name_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setVersion(String str) {
                if (str != null) {
                    this.version_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setVersionBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.version_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.version_ = "";
                this.name_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public Sdk mo10084build() {
                Sdk mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public Sdk mo10085buildPartial() {
                Sdk sdk = new Sdk(this);
                sdk.version_ = this.version_;
                sdk.name_ = this.name_;
                onBuilt();
                return sdk;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Sdk mo10094getDefaultInstanceForType() {
                return Sdk.getDefaultInstance();
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
                this.version_ = "";
                this.name_ = "";
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.version_ = "";
                this.name_ = "";
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
                if (message instanceof Sdk) {
                    return mergeFrom((Sdk) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Sdk sdk) {
                if (sdk == Sdk.getDefaultInstance()) {
                    return this;
                }
                if (!sdk.getVersion().isEmpty()) {
                    this.version_ = sdk.version_;
                    onChanged();
                }
                if (!sdk.getName().isEmpty()) {
                    this.name_ = sdk.name_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) sdk).unknownFields);
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
            public com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.ApplicationProto$Sdk r3 = (com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.ApplicationProto$Sdk r4 = (com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Sdk.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.ApplicationProto$Sdk$Builder");
            }
        }

        public static Builder newBuilder(Sdk sdk) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(sdk);
        }

        public static Sdk parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private Sdk(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Sdk parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Sdk) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Sdk parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Sdk mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static Sdk parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private Sdk() {
            this.memoizedIsInitialized = (byte) -1;
            this.version_ = "";
            this.name_ = "";
        }

        public static Sdk parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Sdk parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Sdk parseFrom(InputStream inputStream) throws IOException {
            return (Sdk) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private Sdk(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.version_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 18) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
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

        public static Sdk parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Sdk) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Sdk parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Sdk) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Sdk parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Sdk) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes9.dex */
    public interface SdkOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getVersion();

        ByteString getVersionBytes();
    }

    public static ApplicationProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static ApplicationProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ApplicationProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<ApplicationProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationProto)) {
            return super.equals(obj);
        }
        ApplicationProto applicationProto = (ApplicationProto) obj;
        boolean z = ((((((getTitle().equals(applicationProto.getTitle())) && getPackageName().equals(applicationProto.getPackageName())) && getAppId().equals(applicationProto.getAppId())) && getVersionName().equals(applicationProto.getVersionName())) && getCognitoIdentityPoolId().equals(applicationProto.getCognitoIdentityPoolId())) && getVersionCode().equals(applicationProto.getVersionCode())) && hasSdk() == applicationProto.hasSdk();
        if (hasSdk()) {
            z = z && getSdk().equals(applicationProto.getSdk());
        }
        return z && this.unknownFields.equals(applicationProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getAppId() {
        Object obj = this.appId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.appId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getAppIdBytes() {
        Object obj = this.appId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.appId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getCognitoIdentityPoolId() {
        Object obj = this.cognitoIdentityPoolId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.cognitoIdentityPoolId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getCognitoIdentityPoolIdBytes() {
        Object obj = this.cognitoIdentityPoolId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.cognitoIdentityPoolId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getPackageName() {
        Object obj = this.packageName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.packageName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getPackageNameBytes() {
        Object obj = this.packageName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.packageName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<ApplicationProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public Sdk getSdk() {
        Sdk sdk = this.sdk_;
        return sdk == null ? Sdk.getDefaultInstance() : sdk;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public SdkOrBuilder getSdkOrBuilder() {
        return getSdk();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getTitleBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.title_);
        }
        if (!getPackageNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.packageName_);
        }
        if (!getAppIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.appId_);
        }
        if (!getVersionNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.versionName_);
        }
        if (!getCognitoIdentityPoolIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.cognitoIdentityPoolId_);
        }
        if (!getVersionCodeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.versionCode_);
        }
        if (this.sdk_ != null) {
            i2 += CodedOutputStream.computeMessageSize(7, getSdk());
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getTitle() {
        Object obj = this.title_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.title_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getTitleBytes() {
        Object obj = this.title_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.title_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getVersionCode() {
        Object obj = this.versionCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.versionCode_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getVersionCodeBytes() {
        Object obj = this.versionCode_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.versionCode_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public String getVersionName() {
        Object obj = this.versionName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.versionName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public ByteString getVersionNameBytes() {
        Object obj = this.versionName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.versionName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
    public boolean hasSdk() {
        return this.sdk_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getTitle().hashCode();
        int hashCode2 = getPackageName().hashCode();
        int hashCode3 = getAppId().hashCode();
        int hashCode4 = getVersionName().hashCode();
        int hashCode5 = getCognitoIdentityPoolId().hashCode();
        int hashCode6 = getVersionCode().hashCode() + ((((hashCode5 + ((((hashCode4 + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 37) + 6) * 53);
        if (hasSdk()) {
            hashCode6 = GeneratedOutlineSupport1.outline4(hashCode6, 37, 7, 53) + getSdk().hashCode();
        }
        int hashCode7 = this.unknownFields.hashCode() + (hashCode6 * 29);
        this.memoizedHashCode = hashCode7;
        return hashCode7;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ApplicationProto.class, Builder.class);
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
        if (!getTitleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.title_);
        }
        if (!getPackageNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.packageName_);
        }
        if (!getAppIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.appId_);
        }
        if (!getVersionNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.versionName_);
        }
        if (!getCognitoIdentityPoolIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.cognitoIdentityPoolId_);
        }
        if (!getVersionCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.versionCode_);
        }
        if (this.sdk_ != null) {
            codedOutputStream.writeMessage(7, getSdk());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ApplicationProtoOrBuilder {
        private Object appId_;
        private Object cognitoIdentityPoolId_;
        private Object packageName_;
        private SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> sdkBuilder_;
        private Sdk sdk_;
        private Object title_;
        private Object versionCode_;
        private Object versionName_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_descriptor;
        }

        private SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> getSdkFieldBuilder() {
            if (this.sdkBuilder_ == null) {
                this.sdkBuilder_ = new SingleFieldBuilderV3<>(getSdk(), getParentForChildren(), isClean());
                this.sdk_ = null;
            }
            return this.sdkBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearAppId() {
            this.appId_ = ApplicationProto.getDefaultInstance().getAppId();
            onChanged();
            return this;
        }

        public Builder clearCognitoIdentityPoolId() {
            this.cognitoIdentityPoolId_ = ApplicationProto.getDefaultInstance().getCognitoIdentityPoolId();
            onChanged();
            return this;
        }

        public Builder clearPackageName() {
            this.packageName_ = ApplicationProto.getDefaultInstance().getPackageName();
            onChanged();
            return this;
        }

        public Builder clearSdk() {
            if (this.sdkBuilder_ == null) {
                this.sdk_ = null;
                onChanged();
            } else {
                this.sdk_ = null;
                this.sdkBuilder_ = null;
            }
            return this;
        }

        public Builder clearTitle() {
            this.title_ = ApplicationProto.getDefaultInstance().getTitle();
            onChanged();
            return this;
        }

        public Builder clearVersionCode() {
            this.versionCode_ = ApplicationProto.getDefaultInstance().getVersionCode();
            onChanged();
            return this;
        }

        public Builder clearVersionName() {
            this.versionName_ = ApplicationProto.getDefaultInstance().getVersionName();
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getAppId() {
            Object obj = this.appId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.appId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getAppIdBytes() {
            Object obj = this.appId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.appId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getCognitoIdentityPoolId() {
            Object obj = this.cognitoIdentityPoolId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.cognitoIdentityPoolId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getCognitoIdentityPoolIdBytes() {
            Object obj = this.cognitoIdentityPoolId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.cognitoIdentityPoolId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getPackageName() {
            Object obj = this.packageName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.packageName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getPackageNameBytes() {
            Object obj = this.packageName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.packageName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public Sdk getSdk() {
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 == null) {
                Sdk sdk = this.sdk_;
                return sdk == null ? Sdk.getDefaultInstance() : sdk;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Sdk.Builder getSdkBuilder() {
            onChanged();
            return getSdkFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public SdkOrBuilder getSdkOrBuilder() {
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Sdk sdk = this.sdk_;
            return sdk == null ? Sdk.getDefaultInstance() : sdk;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getTitle() {
            Object obj = this.title_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.title_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getTitleBytes() {
            Object obj = this.title_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.title_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getVersionCode() {
            Object obj = this.versionCode_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.versionCode_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getVersionCodeBytes() {
            Object obj = this.versionCode_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.versionCode_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public String getVersionName() {
            Object obj = this.versionName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.versionName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public ByteString getVersionNameBytes() {
            Object obj = this.versionName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.versionName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.ApplicationProtoOrBuilder
        public boolean hasSdk() {
            return (this.sdkBuilder_ == null && this.sdk_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ApplicationProtoOuterClass.internal_static_protobuf_ApplicationProto_fieldAccessorTable.ensureFieldAccessorsInitialized(ApplicationProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeSdk(Sdk sdk) {
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 == null) {
                Sdk sdk2 = this.sdk_;
                if (sdk2 != null) {
                    this.sdk_ = Sdk.newBuilder(sdk2).mergeFrom(sdk).mo10085buildPartial();
                } else {
                    this.sdk_ = sdk;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sdk);
            }
            return this;
        }

        public Builder setAppId(String str) {
            if (str != null) {
                this.appId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setAppIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.appId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCognitoIdentityPoolId(String str) {
            if (str != null) {
                this.cognitoIdentityPoolId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCognitoIdentityPoolIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.cognitoIdentityPoolId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPackageName(String str) {
            if (str != null) {
                this.packageName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPackageNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.packageName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSdk(Sdk sdk) {
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sdk);
            } else if (sdk != null) {
                this.sdk_ = sdk;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.title_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setVersionCode(String str) {
            if (str != null) {
                this.versionCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setVersionCodeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.versionCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setVersionName(String str) {
            if (str != null) {
                this.versionName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setVersionNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.versionName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.title_ = "";
            this.packageName_ = "";
            this.appId_ = "";
            this.versionName_ = "";
            this.cognitoIdentityPoolId_ = "";
            this.versionCode_ = "";
            this.sdk_ = null;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public ApplicationProto mo10084build() {
            ApplicationProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public ApplicationProto mo10085buildPartial() {
            ApplicationProto applicationProto = new ApplicationProto(this);
            applicationProto.title_ = this.title_;
            applicationProto.packageName_ = this.packageName_;
            applicationProto.appId_ = this.appId_;
            applicationProto.versionName_ = this.versionName_;
            applicationProto.cognitoIdentityPoolId_ = this.cognitoIdentityPoolId_;
            applicationProto.versionCode_ = this.versionCode_;
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 == null) {
                applicationProto.sdk_ = this.sdk_;
            } else {
                applicationProto.sdk_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return applicationProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ApplicationProto mo10094getDefaultInstanceForType() {
            return ApplicationProto.getDefaultInstance();
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
            this.title_ = "";
            this.packageName_ = "";
            this.appId_ = "";
            this.versionName_ = "";
            this.cognitoIdentityPoolId_ = "";
            this.versionCode_ = "";
            if (this.sdkBuilder_ == null) {
                this.sdk_ = null;
            } else {
                this.sdk_ = null;
                this.sdkBuilder_ = null;
            }
            return this;
        }

        public Builder setSdk(Sdk.Builder builder) {
            SingleFieldBuilderV3<Sdk, Sdk.Builder, SdkOrBuilder> singleFieldBuilderV3 = this.sdkBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sdk_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof ApplicationProto) {
                return mergeFrom((ApplicationProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ApplicationProto applicationProto) {
            if (applicationProto == ApplicationProto.getDefaultInstance()) {
                return this;
            }
            if (!applicationProto.getTitle().isEmpty()) {
                this.title_ = applicationProto.title_;
                onChanged();
            }
            if (!applicationProto.getPackageName().isEmpty()) {
                this.packageName_ = applicationProto.packageName_;
                onChanged();
            }
            if (!applicationProto.getAppId().isEmpty()) {
                this.appId_ = applicationProto.appId_;
                onChanged();
            }
            if (!applicationProto.getVersionName().isEmpty()) {
                this.versionName_ = applicationProto.versionName_;
                onChanged();
            }
            if (!applicationProto.getCognitoIdentityPoolId().isEmpty()) {
                this.cognitoIdentityPoolId_ = applicationProto.cognitoIdentityPoolId_;
                onChanged();
            }
            if (!applicationProto.getVersionCode().isEmpty()) {
                this.versionCode_ = applicationProto.versionCode_;
                onChanged();
            }
            if (applicationProto.hasSdk()) {
                mergeSdk(applicationProto.getSdk());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) applicationProto).unknownFields);
            onChanged();
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.title_ = "";
            this.packageName_ = "";
            this.appId_ = "";
            this.versionName_ = "";
            this.cognitoIdentityPoolId_ = "";
            this.versionCode_ = "";
            this.sdk_ = null;
            maybeForceBuilderInitialization();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.ApplicationProto.access$2300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.ApplicationProto r3 = (com.amazon.alexa.mobilytics.protobuf.ApplicationProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.ApplicationProto r4 = (com.amazon.alexa.mobilytics.protobuf.ApplicationProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.ApplicationProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.ApplicationProto$Builder");
        }
    }

    public static Builder newBuilder(ApplicationProto applicationProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(applicationProto);
    }

    public static ApplicationProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private ApplicationProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static ApplicationProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ApplicationProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public ApplicationProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static ApplicationProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private ApplicationProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.title_ = "";
        this.packageName_ = "";
        this.appId_ = "";
        this.versionName_ = "";
        this.cognitoIdentityPoolId_ = "";
        this.versionCode_ = "";
    }

    public static ApplicationProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static ApplicationProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static ApplicationProto parseFrom(InputStream inputStream) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ApplicationProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ApplicationProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private ApplicationProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.title_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.packageName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.appId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.versionName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.cognitoIdentityPoolId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 50) {
                                this.versionCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag != 58) {
                                if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            } else {
                                Sdk.Builder mo10081toBuilder = this.sdk_ != null ? this.sdk_.mo10081toBuilder() : null;
                                this.sdk_ = (Sdk) codedInputStream.readMessage(Sdk.parser(), extensionRegistryLite);
                                if (mo10081toBuilder != null) {
                                    mo10081toBuilder.mergeFrom(this.sdk_);
                                    this.sdk_ = mo10081toBuilder.mo10085buildPartial();
                                }
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

    public static ApplicationProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ApplicationProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
