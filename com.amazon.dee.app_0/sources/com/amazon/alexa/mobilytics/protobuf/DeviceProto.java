package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
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
import com.google.protobuf.Internal;
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
public final class DeviceProto extends GeneratedMessageV3 implements DeviceProtoOrBuilder {
    public static final int CARRIER_FIELD_NUMBER = 7;
    public static final int DEVICEID_FIELD_NUMBER = 1;
    public static final int DEVICETYPE_FIELD_NUMBER = 2;
    public static final int LOCALE_FIELD_NUMBER = 9;
    public static final int MAKE_FIELD_NUMBER = 3;
    public static final int MODEL_FIELD_NUMBER = 4;
    public static final int NETWORKTYPE_FIELD_NUMBER = 6;
    public static final int PLATFORM_FIELD_NUMBER = 5;
    public static final int SCREEN_FIELD_NUMBER = 8;
    public static final int TIMEZONE_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    private volatile Object carrier_;
    private volatile Object deviceId_;
    private volatile Object deviceType_;
    private Locale locale_;
    private volatile Object make_;
    private byte memoizedIsInitialized;
    private volatile Object model_;
    private volatile Object networkType_;
    private Platform platform_;
    private Screen screen_;
    private volatile Object timezone_;
    private static final DeviceProto DEFAULT_INSTANCE = new DeviceProto();
    private static final Parser<DeviceProto> PARSER = new AbstractParser<DeviceProto>() { // from class: com.amazon.alexa.mobilytics.protobuf.DeviceProto.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public DeviceProto mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DeviceProto(codedInputStream, extensionRegistryLite);
        }
    };

    /* loaded from: classes9.dex */
    public static final class Locale extends GeneratedMessageV3 implements LocaleOrBuilder {
        public static final int COUNTRY_FIELD_NUMBER = 1;
        public static final int LANGUAGE_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private volatile Object country_;
        private volatile Object language_;
        private byte memoizedIsInitialized;
        private static final Locale DEFAULT_INSTANCE = new Locale();
        private static final Parser<Locale> PARSER = new AbstractParser<Locale>() { // from class: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Locale mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Locale(codedInputStream, extensionRegistryLite);
            }
        };

        public static Locale getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Locale_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Locale parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Locale) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Locale parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<Locale> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Locale)) {
                return super.equals(obj);
            }
            Locale locale = (Locale) obj;
            return ((getCountry().equals(locale.getCountry())) && getLanguage().equals(locale.getLanguage())) && this.unknownFields.equals(locale.unknownFields);
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
        public String getCountry() {
            Object obj = this.country_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.country_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
        public ByteString getCountryBytes() {
            Object obj = this.country_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.country_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
        public String getLanguage() {
            Object obj = this.language_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.language_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
        public ByteString getLanguageBytes() {
            Object obj = this.language_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.language_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<Locale> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getCountryBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.country_);
            }
            if (!getLanguageBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.language_);
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
            int hashCode = getCountry().hashCode();
            int hashCode2 = getLanguage().hashCode();
            int hashCode3 = this.unknownFields.hashCode() + ((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 29);
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Locale_fieldAccessorTable.ensureFieldAccessorsInitialized(Locale.class, Builder.class);
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
            if (!getCountryBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.country_);
            }
            if (!getLanguageBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.language_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LocaleOrBuilder {
            private Object country_;
            private Object language_;

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Locale_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearCountry() {
                this.country_ = Locale.getDefaultInstance().getCountry();
                onChanged();
                return this;
            }

            public Builder clearLanguage() {
                this.language_ = Locale.getDefaultInstance().getLanguage();
                onChanged();
                return this;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
            public String getCountry() {
                Object obj = this.country_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.country_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
            public ByteString getCountryBytes() {
                Object obj = this.country_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.country_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Locale_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
            public String getLanguage() {
                Object obj = this.language_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.language_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.LocaleOrBuilder
            public ByteString getLanguageBytes() {
                Object obj = this.language_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.language_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Locale_fieldAccessorTable.ensureFieldAccessorsInitialized(Locale.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder setCountry(String str) {
                if (str != null) {
                    this.country_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setCountryBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.country_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setLanguage(String str) {
                if (str != null) {
                    this.language_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setLanguageBytes(ByteString byteString) {
                if (byteString != null) {
                    AbstractMessageLite.checkByteStringIsUtf8(byteString);
                    this.language_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.country_ = "";
                this.language_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public Locale mo10084build() {
                Locale mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public Locale mo10085buildPartial() {
                Locale locale = new Locale(this);
                locale.country_ = this.country_;
                locale.language_ = this.language_;
                onBuilt();
                return locale;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Locale mo10094getDefaultInstanceForType() {
                return Locale.getDefaultInstance();
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
                this.country_ = "";
                this.language_ = "";
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.country_ = "";
                this.language_ = "";
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
                if (message instanceof Locale) {
                    return mergeFrom((Locale) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Locale locale) {
                if (locale == Locale.getDefaultInstance()) {
                    return this;
                }
                if (!locale.getCountry().isEmpty()) {
                    this.country_ = locale.country_;
                    onChanged();
                }
                if (!locale.getLanguage().isEmpty()) {
                    this.language_ = locale.language_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) locale).unknownFields);
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
            public com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale.access$2800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Locale r3 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Locale r4 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Locale.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.DeviceProto$Locale$Builder");
            }
        }

        public static Builder newBuilder(Locale locale) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(locale);
        }

        public static Locale parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private Locale(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Locale parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Locale parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Locale mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static Locale parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private Locale() {
            this.memoizedIsInitialized = (byte) -1;
            this.country_ = "";
            this.language_ = "";
        }

        public static Locale parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Locale parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Locale parseFrom(InputStream inputStream) throws IOException {
            return (Locale) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private Locale(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.country_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 18) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.language_ = codedInputStream.readStringRequireUtf8();
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

        public static Locale parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Locale parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Locale) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Locale parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Locale) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes9.dex */
    public interface LocaleOrBuilder extends MessageOrBuilder {
        String getCountry();

        ByteString getCountryBytes();

        String getLanguage();

        ByteString getLanguageBytes();
    }

    /* loaded from: classes9.dex */
    public static final class Platform extends GeneratedMessageV3 implements PlatformOrBuilder {
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int VERSION_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private volatile Object version_;
        private static final Platform DEFAULT_INSTANCE = new Platform();
        private static final Parser<Platform> PARSER = new AbstractParser<Platform>() { // from class: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Platform mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Platform(codedInputStream, extensionRegistryLite);
            }
        };

        public static Platform getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Platform_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Platform parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Platform) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Platform parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<Platform> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Platform)) {
                return super.equals(obj);
            }
            Platform platform = (Platform) obj;
            return ((getName().equals(platform.getName())) && getVersion().equals(platform.getVersion())) && this.unknownFields.equals(platform.unknownFields);
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
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
        public Parser<Platform> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getNameBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.name_);
            }
            if (!getVersionBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.version_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
        public String getVersion() {
            Object obj = this.version_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.version_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
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
            int hashCode = getName().hashCode();
            int hashCode2 = getVersion().hashCode();
            int hashCode3 = this.unknownFields.hashCode() + ((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 29);
            this.memoizedHashCode = hashCode3;
            return hashCode3;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Platform_fieldAccessorTable.ensureFieldAccessorsInitialized(Platform.class, Builder.class);
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
            if (!getNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if (!getVersionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.version_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PlatformOrBuilder {
            private Object name_;
            private Object version_;

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Platform_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearName() {
                this.name_ = Platform.getDefaultInstance().getName();
                onChanged();
                return this;
            }

            public Builder clearVersion() {
                this.version_ = Platform.getDefaultInstance().getVersion();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Platform_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
            public String getName() {
                Object obj = this.name_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
            public ByteString getNameBytes() {
                Object obj = this.name_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.name_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
            public String getVersion() {
                Object obj = this.version_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.version_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.PlatformOrBuilder
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
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Platform_fieldAccessorTable.ensureFieldAccessorsInitialized(Platform.class, Builder.class);
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
                this.name_ = "";
                this.version_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public Platform mo10084build() {
                Platform mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public Platform mo10085buildPartial() {
                Platform platform = new Platform(this);
                platform.name_ = this.name_;
                platform.version_ = this.version_;
                onBuilt();
                return platform;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Platform mo10094getDefaultInstanceForType() {
                return Platform.getDefaultInstance();
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
                this.name_ = "";
                this.version_ = "";
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.version_ = "";
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
                if (message instanceof Platform) {
                    return mergeFrom((Platform) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Platform platform) {
                if (platform == Platform.getDefaultInstance()) {
                    return this;
                }
                if (!platform.getName().isEmpty()) {
                    this.name_ = platform.name_;
                    onChanged();
                }
                if (!platform.getVersion().isEmpty()) {
                    this.version_ = platform.version_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) platform).unknownFields);
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
            public com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Platform r3 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Platform r4 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Platform.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.DeviceProto$Platform$Builder");
            }
        }

        public static Builder newBuilder(Platform platform) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(platform);
        }

        public static Platform parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private Platform(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Platform parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Platform) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Platform parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Platform mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static Platform parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private Platform() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.version_ = "";
        }

        public static Platform parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Platform parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Platform parseFrom(InputStream inputStream) throws IOException {
            return (Platform) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private Platform(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.name_ = codedInputStream.readStringRequireUtf8();
                                    } else if (readTag != 18) {
                                        if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.version_ = codedInputStream.readStringRequireUtf8();
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

        public static Platform parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Platform) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Platform parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Platform) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Platform parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Platform) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes9.dex */
    public interface PlatformOrBuilder extends MessageOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getVersion();

        ByteString getVersionBytes();
    }

    /* loaded from: classes9.dex */
    public static final class Screen extends GeneratedMessageV3 implements ScreenOrBuilder {
        private static final Screen DEFAULT_INSTANCE = new Screen();
        private static final Parser<Screen> PARSER = new AbstractParser<Screen>() { // from class: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public Screen mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Screen(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SCREENDENSITY_FIELD_NUMBER = 3;
        public static final int SCREENHEIGHT_FIELD_NUMBER = 2;
        public static final int SCREENWIDTH_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private double screenDensity_;
        private long screenHeight_;
        private long screenWidth_;

        public static Screen getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Screen_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static Screen parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Screen) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Screen parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<Screen> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Screen)) {
                return super.equals(obj);
            }
            Screen screen = (Screen) obj;
            return ((((getScreenWidth() > screen.getScreenWidth() ? 1 : (getScreenWidth() == screen.getScreenWidth() ? 0 : -1)) == 0) && (getScreenHeight() > screen.getScreenHeight() ? 1 : (getScreenHeight() == screen.getScreenHeight() ? 0 : -1)) == 0) && (Double.doubleToLongBits(getScreenDensity()) > Double.doubleToLongBits(screen.getScreenDensity()) ? 1 : (Double.doubleToLongBits(getScreenDensity()) == Double.doubleToLongBits(screen.getScreenDensity()) ? 0 : -1)) == 0) && this.unknownFields.equals(screen.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<Screen> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
        public double getScreenDensity() {
            return this.screenDensity_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
        public long getScreenHeight() {
            return this.screenHeight_;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
        public long getScreenWidth() {
            return this.screenWidth_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            long j = this.screenWidth_;
            if (j != 0) {
                i2 = 0 + CodedOutputStream.computeInt64Size(1, j);
            }
            long j2 = this.screenHeight_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(2, j2);
            }
            double d = this.screenDensity_;
            if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                i2 += CodedOutputStream.computeDoubleSize(3, d);
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
            int hashLong = Internal.hashLong(getScreenWidth());
            int hashLong2 = Internal.hashLong(getScreenHeight());
            int hashLong3 = Internal.hashLong(Double.doubleToLongBits(getScreenDensity()));
            int hashCode = this.unknownFields.hashCode() + ((hashLong3 + ((((hashLong2 + ((((hashLong + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 29);
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Screen_fieldAccessorTable.ensureFieldAccessorsInitialized(Screen.class, Builder.class);
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
            long j = this.screenWidth_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            long j2 = this.screenHeight_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(2, j2);
            }
            double d = this.screenDensity_;
            if (d != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                codedOutputStream.writeDouble(3, d);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes9.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScreenOrBuilder {
            private double screenDensity_;
            private long screenHeight_;
            private long screenWidth_;

            public static final Descriptors.Descriptor getDescriptor() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Screen_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearScreenDensity() {
                this.screenDensity_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                onChanged();
                return this;
            }

            public Builder clearScreenHeight() {
                this.screenHeight_ = 0L;
                onChanged();
                return this;
            }

            public Builder clearScreenWidth() {
                this.screenWidth_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Screen_descriptor;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
            public double getScreenDensity() {
                return this.screenDensity_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
            public long getScreenHeight() {
                return this.screenHeight_;
            }

            @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProto.ScreenOrBuilder
            public long getScreenWidth() {
                return this.screenWidth_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_Screen_fieldAccessorTable.ensureFieldAccessorsInitialized(Screen.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder setScreenDensity(double d) {
                this.screenDensity_ = d;
                onChanged();
                return this;
            }

            public Builder setScreenHeight(long j) {
                this.screenHeight_ = j;
                onChanged();
                return this;
            }

            public Builder setScreenWidth(long j) {
                this.screenWidth_ = j;
                onChanged();
                return this;
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public Screen mo10084build() {
                Screen mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public Screen mo10085buildPartial() {
                Screen screen = new Screen(this);
                screen.screenWidth_ = this.screenWidth_;
                screen.screenHeight_ = this.screenHeight_;
                screen.screenDensity_ = this.screenDensity_;
                onBuilt();
                return screen;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public Screen mo10094getDefaultInstanceForType() {
                return Screen.getDefaultInstance();
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

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clear */
            public Builder mo10087clear() {
                super.mo10087clear();
                this.screenWidth_ = 0L;
                this.screenHeight_ = 0L;
                this.screenDensity_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
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
                if (message instanceof Screen) {
                    return mergeFrom((Screen) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Screen screen) {
                if (screen == Screen.getDefaultInstance()) {
                    return this;
                }
                if (screen.getScreenWidth() != 0) {
                    setScreenWidth(screen.getScreenWidth());
                }
                if (screen.getScreenHeight() != 0) {
                    setScreenHeight(screen.getScreenHeight());
                }
                if (screen.getScreenDensity() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                    setScreenDensity(screen.getScreenDensity());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) screen).unknownFields);
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
            public com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Screen r3 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.amazon.alexa.mobilytics.protobuf.DeviceProto$Screen r4 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Screen.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.DeviceProto$Screen$Builder");
            }
        }

        public static Builder newBuilder(Screen screen) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(screen);
        }

        public static Screen parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private Screen(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static Screen parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Screen) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Screen parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Screen mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static Screen parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private Screen() {
            this.memoizedIsInitialized = (byte) -1;
            this.screenWidth_ = 0L;
            this.screenHeight_ = 0L;
            this.screenDensity_ = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }

        public static Screen parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static Screen parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static Screen parseFrom(InputStream inputStream) throws IOException {
            return (Screen) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Screen parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Screen) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private Screen(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.screenWidth_ = codedInputStream.readInt64();
                                } else if (readTag == 16) {
                                    this.screenHeight_ = codedInputStream.readInt64();
                                } else if (readTag != 25) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.screenDensity_ = codedInputStream.readDouble();
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

        public static Screen parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Screen) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Screen parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Screen) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes9.dex */
    public interface ScreenOrBuilder extends MessageOrBuilder {
        double getScreenDensity();

        long getScreenHeight();

        long getScreenWidth();
    }

    public static DeviceProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static DeviceProto parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DeviceProto parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<DeviceProto> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceProto)) {
            return super.equals(obj);
        }
        DeviceProto deviceProto = (DeviceProto) obj;
        boolean z = ((((getDeviceId().equals(deviceProto.getDeviceId())) && getDeviceType().equals(deviceProto.getDeviceType())) && getMake().equals(deviceProto.getMake())) && getModel().equals(deviceProto.getModel())) && hasPlatform() == deviceProto.hasPlatform();
        if (hasPlatform()) {
            z = z && getPlatform().equals(deviceProto.getPlatform());
        }
        boolean z2 = ((z && getNetworkType().equals(deviceProto.getNetworkType())) && getCarrier().equals(deviceProto.getCarrier())) && hasScreen() == deviceProto.hasScreen();
        if (hasScreen()) {
            z2 = z2 && getScreen().equals(deviceProto.getScreen());
        }
        boolean z3 = z2 && hasLocale() == deviceProto.hasLocale();
        if (hasLocale()) {
            z3 = z3 && getLocale().equals(deviceProto.getLocale());
        }
        return (z3 && getTimezone().equals(deviceProto.getTimezone())) && this.unknownFields.equals(deviceProto.unknownFields);
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getCarrier() {
        Object obj = this.carrier_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.carrier_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getCarrierBytes() {
        Object obj = this.carrier_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.carrier_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getDeviceId() {
        Object obj = this.deviceId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.deviceId_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getDeviceIdBytes() {
        Object obj = this.deviceId_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.deviceId_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getDeviceType() {
        Object obj = this.deviceType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.deviceType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getDeviceTypeBytes() {
        Object obj = this.deviceType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.deviceType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public Locale getLocale() {
        Locale locale = this.locale_;
        return locale == null ? Locale.getDefaultInstance() : locale;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public LocaleOrBuilder getLocaleOrBuilder() {
        return getLocale();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getMake() {
        Object obj = this.make_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.make_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getMakeBytes() {
        Object obj = this.make_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.make_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getModel() {
        Object obj = this.model_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.model_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getModelBytes() {
        Object obj = this.model_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.model_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getNetworkType() {
        Object obj = this.networkType_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.networkType_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getNetworkTypeBytes() {
        Object obj = this.networkType_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.networkType_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<DeviceProto> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public Platform getPlatform() {
        Platform platform = this.platform_;
        return platform == null ? Platform.getDefaultInstance() : platform;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public PlatformOrBuilder getPlatformOrBuilder() {
        return getPlatform();
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public Screen getScreen() {
        Screen screen = this.screen_;
        return screen == null ? Screen.getDefaultInstance() : screen;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ScreenOrBuilder getScreenOrBuilder() {
        return getScreen();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getDeviceIdBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.deviceId_);
        }
        if (!getDeviceTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(2, this.deviceType_);
        }
        if (!getMakeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.make_);
        }
        if (!getModelBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.model_);
        }
        if (this.platform_ != null) {
            i2 += CodedOutputStream.computeMessageSize(5, getPlatform());
        }
        if (!getNetworkTypeBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.networkType_);
        }
        if (!getCarrierBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(7, this.carrier_);
        }
        if (this.screen_ != null) {
            i2 += CodedOutputStream.computeMessageSize(8, getScreen());
        }
        if (this.locale_ != null) {
            i2 += CodedOutputStream.computeMessageSize(9, getLocale());
        }
        if (!getTimezoneBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(10, this.timezone_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public String getTimezone() {
        Object obj = this.timezone_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.timezone_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public ByteString getTimezoneBytes() {
        Object obj = this.timezone_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.timezone_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public boolean hasLocale() {
        return this.locale_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public boolean hasPlatform() {
        return this.platform_ != null;
    }

    @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
    public boolean hasScreen() {
        return this.screen_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getDeviceId().hashCode();
        int hashCode2 = getDeviceType().hashCode();
        int hashCode3 = getMake().hashCode();
        int hashCode4 = getModel().hashCode() + ((((hashCode3 + ((((hashCode2 + ((((hashCode + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53);
        if (hasPlatform()) {
            hashCode4 = GeneratedOutlineSupport1.outline4(hashCode4, 37, 5, 53) + getPlatform().hashCode();
        }
        int outline4 = GeneratedOutlineSupport1.outline4(hashCode4, 37, 6, 53);
        int hashCode5 = getCarrier().hashCode() + ((((getNetworkType().hashCode() + outline4) * 37) + 7) * 53);
        if (hasScreen()) {
            hashCode5 = GeneratedOutlineSupport1.outline4(hashCode5, 37, 8, 53) + getScreen().hashCode();
        }
        if (hasLocale()) {
            hashCode5 = GeneratedOutlineSupport1.outline4(hashCode5, 37, 9, 53) + getLocale().hashCode();
        }
        int outline42 = GeneratedOutlineSupport1.outline4(hashCode5, 37, 10, 53);
        int hashCode6 = this.unknownFields.hashCode() + ((getTimezone().hashCode() + outline42) * 29);
        this.memoizedHashCode = hashCode6;
        return hashCode6;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DeviceProto.class, Builder.class);
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
        if (!getDeviceIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.deviceId_);
        }
        if (!getDeviceTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.deviceType_);
        }
        if (!getMakeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.make_);
        }
        if (!getModelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.model_);
        }
        if (this.platform_ != null) {
            codedOutputStream.writeMessage(5, getPlatform());
        }
        if (!getNetworkTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.networkType_);
        }
        if (!getCarrierBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.carrier_);
        }
        if (this.screen_ != null) {
            codedOutputStream.writeMessage(8, getScreen());
        }
        if (this.locale_ != null) {
            codedOutputStream.writeMessage(9, getLocale());
        }
        if (!getTimezoneBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.timezone_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes9.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DeviceProtoOrBuilder {
        private Object carrier_;
        private Object deviceId_;
        private Object deviceType_;
        private SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> localeBuilder_;
        private Locale locale_;
        private Object make_;
        private Object model_;
        private Object networkType_;
        private SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> platformBuilder_;
        private Platform platform_;
        private SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> screenBuilder_;
        private Screen screen_;
        private Object timezone_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_descriptor;
        }

        private SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> getLocaleFieldBuilder() {
            if (this.localeBuilder_ == null) {
                this.localeBuilder_ = new SingleFieldBuilderV3<>(getLocale(), getParentForChildren(), isClean());
                this.locale_ = null;
            }
            return this.localeBuilder_;
        }

        private SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> getPlatformFieldBuilder() {
            if (this.platformBuilder_ == null) {
                this.platformBuilder_ = new SingleFieldBuilderV3<>(getPlatform(), getParentForChildren(), isClean());
                this.platform_ = null;
            }
            return this.platformBuilder_;
        }

        private SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> getScreenFieldBuilder() {
            if (this.screenBuilder_ == null) {
                this.screenBuilder_ = new SingleFieldBuilderV3<>(getScreen(), getParentForChildren(), isClean());
                this.screen_ = null;
            }
            return this.screenBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearCarrier() {
            this.carrier_ = DeviceProto.getDefaultInstance().getCarrier();
            onChanged();
            return this;
        }

        public Builder clearDeviceId() {
            this.deviceId_ = DeviceProto.getDefaultInstance().getDeviceId();
            onChanged();
            return this;
        }

        public Builder clearDeviceType() {
            this.deviceType_ = DeviceProto.getDefaultInstance().getDeviceType();
            onChanged();
            return this;
        }

        public Builder clearLocale() {
            if (this.localeBuilder_ == null) {
                this.locale_ = null;
                onChanged();
            } else {
                this.locale_ = null;
                this.localeBuilder_ = null;
            }
            return this;
        }

        public Builder clearMake() {
            this.make_ = DeviceProto.getDefaultInstance().getMake();
            onChanged();
            return this;
        }

        public Builder clearModel() {
            this.model_ = DeviceProto.getDefaultInstance().getModel();
            onChanged();
            return this;
        }

        public Builder clearNetworkType() {
            this.networkType_ = DeviceProto.getDefaultInstance().getNetworkType();
            onChanged();
            return this;
        }

        public Builder clearPlatform() {
            if (this.platformBuilder_ == null) {
                this.platform_ = null;
                onChanged();
            } else {
                this.platform_ = null;
                this.platformBuilder_ = null;
            }
            return this;
        }

        public Builder clearScreen() {
            if (this.screenBuilder_ == null) {
                this.screen_ = null;
                onChanged();
            } else {
                this.screen_ = null;
                this.screenBuilder_ = null;
            }
            return this;
        }

        public Builder clearTimezone() {
            this.timezone_ = DeviceProto.getDefaultInstance().getTimezone();
            onChanged();
            return this;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getCarrier() {
            Object obj = this.carrier_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.carrier_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getCarrierBytes() {
            Object obj = this.carrier_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.carrier_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_descriptor;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getDeviceId() {
            Object obj = this.deviceId_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceId_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getDeviceIdBytes() {
            Object obj = this.deviceId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getDeviceType() {
            Object obj = this.deviceType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.deviceType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getDeviceTypeBytes() {
            Object obj = this.deviceType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.deviceType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public Locale getLocale() {
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV3 = this.localeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Locale locale = this.locale_;
                return locale == null ? Locale.getDefaultInstance() : locale;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Locale.Builder getLocaleBuilder() {
            onChanged();
            return getLocaleFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public LocaleOrBuilder getLocaleOrBuilder() {
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV3 = this.localeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Locale locale = this.locale_;
            return locale == null ? Locale.getDefaultInstance() : locale;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getMake() {
            Object obj = this.make_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.make_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getMakeBytes() {
            Object obj = this.make_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.make_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getModel() {
            Object obj = this.model_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.model_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getModelBytes() {
            Object obj = this.model_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.model_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getNetworkType() {
            Object obj = this.networkType_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.networkType_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getNetworkTypeBytes() {
            Object obj = this.networkType_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.networkType_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public Platform getPlatform() {
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 == null) {
                Platform platform = this.platform_;
                return platform == null ? Platform.getDefaultInstance() : platform;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Platform.Builder getPlatformBuilder() {
            onChanged();
            return getPlatformFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public PlatformOrBuilder getPlatformOrBuilder() {
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Platform platform = this.platform_;
            return platform == null ? Platform.getDefaultInstance() : platform;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public Screen getScreen() {
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV3 = this.screenBuilder_;
            if (singleFieldBuilderV3 == null) {
                Screen screen = this.screen_;
                return screen == null ? Screen.getDefaultInstance() : screen;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Screen.Builder getScreenBuilder() {
            onChanged();
            return getScreenFieldBuilder().getBuilder();
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ScreenOrBuilder getScreenOrBuilder() {
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV3 = this.screenBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Screen screen = this.screen_;
            return screen == null ? Screen.getDefaultInstance() : screen;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public String getTimezone() {
            Object obj = this.timezone_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.timezone_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public ByteString getTimezoneBytes() {
            Object obj = this.timezone_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.timezone_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public boolean hasLocale() {
            return (this.localeBuilder_ == null && this.locale_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public boolean hasPlatform() {
            return (this.platformBuilder_ == null && this.platform_ == null) ? false : true;
        }

        @Override // com.amazon.alexa.mobilytics.protobuf.DeviceProtoOrBuilder
        public boolean hasScreen() {
            return (this.screenBuilder_ == null && this.screen_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceProtoOuterClass.internal_static_protobuf_DeviceProto_fieldAccessorTable.ensureFieldAccessorsInitialized(DeviceProto.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeLocale(Locale locale) {
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV3 = this.localeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Locale locale2 = this.locale_;
                if (locale2 != null) {
                    this.locale_ = Locale.newBuilder(locale2).mergeFrom(locale).mo10085buildPartial();
                } else {
                    this.locale_ = locale;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(locale);
            }
            return this;
        }

        public Builder mergePlatform(Platform platform) {
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 == null) {
                Platform platform2 = this.platform_;
                if (platform2 != null) {
                    this.platform_ = Platform.newBuilder(platform2).mergeFrom(platform).mo10085buildPartial();
                } else {
                    this.platform_ = platform;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(platform);
            }
            return this;
        }

        public Builder mergeScreen(Screen screen) {
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV3 = this.screenBuilder_;
            if (singleFieldBuilderV3 == null) {
                Screen screen2 = this.screen_;
                if (screen2 != null) {
                    this.screen_ = Screen.newBuilder(screen2).mergeFrom(screen).mo10085buildPartial();
                } else {
                    this.screen_ = screen;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(screen);
            }
            return this;
        }

        public Builder setCarrier(String str) {
            if (str != null) {
                this.carrier_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCarrierBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.carrier_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDeviceId(String str) {
            if (str != null) {
                this.deviceId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDeviceIdBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.deviceId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDeviceType(String str) {
            if (str != null) {
                this.deviceType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setDeviceTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.deviceType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setLocale(Locale locale) {
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV3 = this.localeBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(locale);
            } else if (locale != null) {
                this.locale_ = locale;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMake(String str) {
            if (str != null) {
                this.make_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setMakeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.make_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setModel(String str) {
            if (str != null) {
                this.model_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setModelBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.model_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setNetworkType(String str) {
            if (str != null) {
                this.networkType_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setNetworkTypeBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.networkType_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setPlatform(Platform platform) {
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(platform);
            } else if (platform != null) {
                this.platform_ = platform;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setScreen(Screen screen) {
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV3 = this.screenBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(screen);
            } else if (screen != null) {
                this.screen_ = screen;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setTimezone(String str) {
            if (str != null) {
                this.timezone_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setTimezoneBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.timezone_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.deviceId_ = "";
            this.deviceType_ = "";
            this.make_ = "";
            this.model_ = "";
            this.platform_ = null;
            this.networkType_ = "";
            this.carrier_ = "";
            this.screen_ = null;
            this.locale_ = null;
            this.timezone_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public DeviceProto mo10084build() {
            DeviceProto mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public DeviceProto mo10085buildPartial() {
            DeviceProto deviceProto = new DeviceProto(this);
            deviceProto.deviceId_ = this.deviceId_;
            deviceProto.deviceType_ = this.deviceType_;
            deviceProto.make_ = this.make_;
            deviceProto.model_ = this.model_;
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 == null) {
                deviceProto.platform_ = this.platform_;
            } else {
                deviceProto.platform_ = singleFieldBuilderV3.build();
            }
            deviceProto.networkType_ = this.networkType_;
            deviceProto.carrier_ = this.carrier_;
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV32 = this.screenBuilder_;
            if (singleFieldBuilderV32 == null) {
                deviceProto.screen_ = this.screen_;
            } else {
                deviceProto.screen_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV33 = this.localeBuilder_;
            if (singleFieldBuilderV33 == null) {
                deviceProto.locale_ = this.locale_;
            } else {
                deviceProto.locale_ = singleFieldBuilderV33.build();
            }
            deviceProto.timezone_ = this.timezone_;
            onBuilt();
            return deviceProto;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public DeviceProto mo10094getDefaultInstanceForType() {
            return DeviceProto.getDefaultInstance();
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
            this.deviceId_ = "";
            this.deviceType_ = "";
            this.make_ = "";
            this.model_ = "";
            if (this.platformBuilder_ == null) {
                this.platform_ = null;
            } else {
                this.platform_ = null;
                this.platformBuilder_ = null;
            }
            this.networkType_ = "";
            this.carrier_ = "";
            if (this.screenBuilder_ == null) {
                this.screen_ = null;
            } else {
                this.screen_ = null;
                this.screenBuilder_ = null;
            }
            if (this.localeBuilder_ == null) {
                this.locale_ = null;
            } else {
                this.locale_ = null;
                this.localeBuilder_ = null;
            }
            this.timezone_ = "";
            return this;
        }

        public Builder setLocale(Locale.Builder builder) {
            SingleFieldBuilderV3<Locale, Locale.Builder, LocaleOrBuilder> singleFieldBuilderV3 = this.localeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.locale_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setPlatform(Platform.Builder builder) {
            SingleFieldBuilderV3<Platform, Platform.Builder, PlatformOrBuilder> singleFieldBuilderV3 = this.platformBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.platform_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setScreen(Screen.Builder builder) {
            SingleFieldBuilderV3<Screen, Screen.Builder, ScreenOrBuilder> singleFieldBuilderV3 = this.screenBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.screen_ = builder.mo10084build();
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
            if (message instanceof DeviceProto) {
                return mergeFrom((DeviceProto) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DeviceProto deviceProto) {
            if (deviceProto == DeviceProto.getDefaultInstance()) {
                return this;
            }
            if (!deviceProto.getDeviceId().isEmpty()) {
                this.deviceId_ = deviceProto.deviceId_;
                onChanged();
            }
            if (!deviceProto.getDeviceType().isEmpty()) {
                this.deviceType_ = deviceProto.deviceType_;
                onChanged();
            }
            if (!deviceProto.getMake().isEmpty()) {
                this.make_ = deviceProto.make_;
                onChanged();
            }
            if (!deviceProto.getModel().isEmpty()) {
                this.model_ = deviceProto.model_;
                onChanged();
            }
            if (deviceProto.hasPlatform()) {
                mergePlatform(deviceProto.getPlatform());
            }
            if (!deviceProto.getNetworkType().isEmpty()) {
                this.networkType_ = deviceProto.networkType_;
                onChanged();
            }
            if (!deviceProto.getCarrier().isEmpty()) {
                this.carrier_ = deviceProto.carrier_;
                onChanged();
            }
            if (deviceProto.hasScreen()) {
                mergeScreen(deviceProto.getScreen());
            }
            if (deviceProto.hasLocale()) {
                mergeLocale(deviceProto.getLocale());
            }
            if (!deviceProto.getTimezone().isEmpty()) {
                this.timezone_ = deviceProto.timezone_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) deviceProto).unknownFields);
            onChanged();
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.deviceId_ = "";
            this.deviceType_ = "";
            this.make_ = "";
            this.model_ = "";
            this.platform_ = null;
            this.networkType_ = "";
            this.carrier_ = "";
            this.screen_ = null;
            this.locale_ = null;
            this.timezone_ = "";
            maybeForceBuilderInitialization();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.amazon.alexa.mobilytics.protobuf.DeviceProto.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.alexa.mobilytics.protobuf.DeviceProto.access$4700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.alexa.mobilytics.protobuf.DeviceProto r3 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.alexa.mobilytics.protobuf.DeviceProto r4 = (com.amazon.alexa.mobilytics.protobuf.DeviceProto) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.protobuf.DeviceProto.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.alexa.mobilytics.protobuf.DeviceProto$Builder");
        }
    }

    public static Builder newBuilder(DeviceProto deviceProto) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(deviceProto);
    }

    public static DeviceProto parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private DeviceProto(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static DeviceProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DeviceProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public DeviceProto mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static DeviceProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private DeviceProto() {
        this.memoizedIsInitialized = (byte) -1;
        this.deviceId_ = "";
        this.deviceType_ = "";
        this.make_ = "";
        this.model_ = "";
        this.networkType_ = "";
        this.carrier_ = "";
        this.timezone_ = "";
    }

    public static DeviceProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static DeviceProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static DeviceProto parseFrom(InputStream inputStream) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DeviceProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DeviceProto parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DeviceProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeviceProto) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    private DeviceProto(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            Platform.Builder builder = null;
                            Locale.Builder mo10081toBuilder = null;
                            Screen.Builder mo10081toBuilder2 = null;
                            switch (readTag) {
                                case 0:
                                    break;
                                case 10:
                                    this.deviceId_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 18:
                                    this.deviceType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 26:
                                    this.make_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 34:
                                    this.model_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 42:
                                    builder = this.platform_ != null ? this.platform_.mo10081toBuilder() : builder;
                                    this.platform_ = (Platform) codedInputStream.readMessage(Platform.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.platform_);
                                        this.platform_ = builder.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 50:
                                    this.networkType_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 58:
                                    this.carrier_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                case 66:
                                    mo10081toBuilder2 = this.screen_ != null ? this.screen_.mo10081toBuilder() : mo10081toBuilder2;
                                    this.screen_ = (Screen) codedInputStream.readMessage(Screen.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder2 != null) {
                                        mo10081toBuilder2.mergeFrom(this.screen_);
                                        this.screen_ = mo10081toBuilder2.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 74:
                                    mo10081toBuilder = this.locale_ != null ? this.locale_.mo10081toBuilder() : mo10081toBuilder;
                                    this.locale_ = (Locale) codedInputStream.readMessage(Locale.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.locale_);
                                        this.locale_ = mo10081toBuilder.mo10085buildPartial();
                                    } else {
                                        continue;
                                    }
                                case 82:
                                    this.timezone_ = codedInputStream.readStringRequireUtf8();
                                    continue;
                                default:
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        break;
                                    } else {
                                        continue;
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
}
