package com.amazon.wellness.io.format.abs;

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
/* loaded from: classes13.dex */
public final class SoftwareVersionV1 extends GeneratedMessageV3 implements SoftwareVersionV1OrBuilder {
    public static final int BUILD_VERSION_FIELD_NUMBER = 4;
    public static final int MAJOR_VERSION_FIELD_NUMBER = 1;
    public static final int MINOR_VERSION_FIELD_NUMBER = 2;
    public static final int PATCH_VERSION_FIELD_NUMBER = 3;
    public static final int VERSION_DESCRIPTOR_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int buildVersion_;
    private int majorVersion_;
    private byte memoizedIsInitialized;
    private int minorVersion_;
    private int patchVersion_;
    private volatile Object versionDescriptor_;
    private static final SoftwareVersionV1 DEFAULT_INSTANCE = new SoftwareVersionV1();
    private static final Parser<SoftwareVersionV1> PARSER = new AbstractParser<SoftwareVersionV1>() { // from class: com.amazon.wellness.io.format.abs.SoftwareVersionV1.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public SoftwareVersionV1 mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SoftwareVersionV1(codedInputStream, extensionRegistryLite);
        }
    };

    public static SoftwareVersionV1 getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HeaderOuterClass.internal_static_SoftwareVersionV1_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static SoftwareVersionV1 parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SoftwareVersionV1 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<SoftwareVersionV1> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SoftwareVersionV1)) {
            return super.equals(obj);
        }
        SoftwareVersionV1 softwareVersionV1 = (SoftwareVersionV1) obj;
        return (((((getMajorVersion() == softwareVersionV1.getMajorVersion()) && getMinorVersion() == softwareVersionV1.getMinorVersion()) && getPatchVersion() == softwareVersionV1.getPatchVersion()) && getBuildVersion() == softwareVersionV1.getBuildVersion()) && getVersionDescriptor().equals(softwareVersionV1.getVersionDescriptor())) && this.unknownFields.equals(softwareVersionV1.unknownFields);
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public int getBuildVersion() {
        return this.buildVersion_;
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public int getMajorVersion() {
        return this.majorVersion_;
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public int getMinorVersion() {
        return this.minorVersion_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<SoftwareVersionV1> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public int getPatchVersion() {
        return this.patchVersion_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.majorVersion_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
        }
        int i4 = this.minorVersion_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i4);
        }
        int i5 = this.patchVersion_;
        if (i5 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(3, i5);
        }
        int i6 = this.buildVersion_;
        if (i6 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(4, i6);
        }
        if (!getVersionDescriptorBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.versionDescriptor_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public String getVersionDescriptor() {
        Object obj = this.versionDescriptor_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.versionDescriptor_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
    public ByteString getVersionDescriptorBytes() {
        Object obj = this.versionDescriptor_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.versionDescriptor_ = copyFromUtf8;
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
        int majorVersion = getMajorVersion();
        int minorVersion = getMinorVersion();
        int patchVersion = getPatchVersion();
        int buildVersion = getBuildVersion();
        int hashCode = getVersionDescriptor().hashCode();
        int hashCode2 = this.unknownFields.hashCode() + ((hashCode + ((((buildVersion + ((((patchVersion + ((((minorVersion + ((((majorVersion + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 29);
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HeaderOuterClass.internal_static_SoftwareVersionV1_fieldAccessorTable.ensureFieldAccessorsInitialized(SoftwareVersionV1.class, Builder.class);
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
        int i = this.majorVersion_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.minorVersion_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
        int i3 = this.patchVersion_;
        if (i3 != 0) {
            codedOutputStream.writeUInt32(3, i3);
        }
        int i4 = this.buildVersion_;
        if (i4 != 0) {
            codedOutputStream.writeUInt32(4, i4);
        }
        if (!getVersionDescriptorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.versionDescriptor_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SoftwareVersionV1OrBuilder {
        private int buildVersion_;
        private int majorVersion_;
        private int minorVersion_;
        private int patchVersion_;
        private Object versionDescriptor_;

        public static final Descriptors.Descriptor getDescriptor() {
            return HeaderOuterClass.internal_static_SoftwareVersionV1_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearBuildVersion() {
            this.buildVersion_ = 0;
            onChanged();
            return this;
        }

        public Builder clearMajorVersion() {
            this.majorVersion_ = 0;
            onChanged();
            return this;
        }

        public Builder clearMinorVersion() {
            this.minorVersion_ = 0;
            onChanged();
            return this;
        }

        public Builder clearPatchVersion() {
            this.patchVersion_ = 0;
            onChanged();
            return this;
        }

        public Builder clearVersionDescriptor() {
            this.versionDescriptor_ = SoftwareVersionV1.getDefaultInstance().getVersionDescriptor();
            onChanged();
            return this;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public int getBuildVersion() {
            return this.buildVersion_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HeaderOuterClass.internal_static_SoftwareVersionV1_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public int getMajorVersion() {
            return this.majorVersion_;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public int getMinorVersion() {
            return this.minorVersion_;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public int getPatchVersion() {
            return this.patchVersion_;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public String getVersionDescriptor() {
            Object obj = this.versionDescriptor_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.versionDescriptor_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.amazon.wellness.io.format.abs.SoftwareVersionV1OrBuilder
        public ByteString getVersionDescriptorBytes() {
            Object obj = this.versionDescriptor_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.versionDescriptor_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HeaderOuterClass.internal_static_SoftwareVersionV1_fieldAccessorTable.ensureFieldAccessorsInitialized(SoftwareVersionV1.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setBuildVersion(int i) {
            this.buildVersion_ = i;
            onChanged();
            return this;
        }

        public Builder setMajorVersion(int i) {
            this.majorVersion_ = i;
            onChanged();
            return this;
        }

        public Builder setMinorVersion(int i) {
            this.minorVersion_ = i;
            onChanged();
            return this;
        }

        public Builder setPatchVersion(int i) {
            this.patchVersion_ = i;
            onChanged();
            return this;
        }

        public Builder setVersionDescriptor(String str) {
            if (str != null) {
                this.versionDescriptor_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setVersionDescriptorBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.versionDescriptor_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private Builder() {
            this.versionDescriptor_ = "";
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public SoftwareVersionV1 mo10084build() {
            SoftwareVersionV1 mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public SoftwareVersionV1 mo10085buildPartial() {
            SoftwareVersionV1 softwareVersionV1 = new SoftwareVersionV1(this);
            softwareVersionV1.majorVersion_ = this.majorVersion_;
            softwareVersionV1.minorVersion_ = this.minorVersion_;
            softwareVersionV1.patchVersion_ = this.patchVersion_;
            softwareVersionV1.buildVersion_ = this.buildVersion_;
            softwareVersionV1.versionDescriptor_ = this.versionDescriptor_;
            onBuilt();
            return softwareVersionV1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public SoftwareVersionV1 mo10094getDefaultInstanceForType() {
            return SoftwareVersionV1.getDefaultInstance();
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
            this.majorVersion_ = 0;
            this.minorVersion_ = 0;
            this.patchVersion_ = 0;
            this.buildVersion_ = 0;
            this.versionDescriptor_ = "";
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.versionDescriptor_ = "";
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
            if (message instanceof SoftwareVersionV1) {
                return mergeFrom((SoftwareVersionV1) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SoftwareVersionV1 softwareVersionV1) {
            if (softwareVersionV1 == SoftwareVersionV1.getDefaultInstance()) {
                return this;
            }
            if (softwareVersionV1.getMajorVersion() != 0) {
                setMajorVersion(softwareVersionV1.getMajorVersion());
            }
            if (softwareVersionV1.getMinorVersion() != 0) {
                setMinorVersion(softwareVersionV1.getMinorVersion());
            }
            if (softwareVersionV1.getPatchVersion() != 0) {
                setPatchVersion(softwareVersionV1.getPatchVersion());
            }
            if (softwareVersionV1.getBuildVersion() != 0) {
                setBuildVersion(softwareVersionV1.getBuildVersion());
            }
            if (!softwareVersionV1.getVersionDescriptor().isEmpty()) {
                this.versionDescriptor_ = softwareVersionV1.versionDescriptor_;
                onChanged();
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) softwareVersionV1).unknownFields);
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
        public com.amazon.wellness.io.format.abs.SoftwareVersionV1.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.SoftwareVersionV1.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.SoftwareVersionV1 r3 = (com.amazon.wellness.io.format.abs.SoftwareVersionV1) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.SoftwareVersionV1 r4 = (com.amazon.wellness.io.format.abs.SoftwareVersionV1) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.SoftwareVersionV1.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.SoftwareVersionV1$Builder");
        }
    }

    public static Builder newBuilder(SoftwareVersionV1 softwareVersionV1) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(softwareVersionV1);
    }

    public static SoftwareVersionV1 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private SoftwareVersionV1(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static SoftwareVersionV1 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SoftwareVersionV1 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public SoftwareVersionV1 mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static SoftwareVersionV1 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private SoftwareVersionV1() {
        this.memoizedIsInitialized = (byte) -1;
        this.majorVersion_ = 0;
        this.minorVersion_ = 0;
        this.patchVersion_ = 0;
        this.buildVersion_ = 0;
        this.versionDescriptor_ = "";
    }

    public static SoftwareVersionV1 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static SoftwareVersionV1 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static SoftwareVersionV1 parseFrom(InputStream inputStream) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SoftwareVersionV1 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SoftwareVersionV1 parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private SoftwareVersionV1(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 8) {
                                    this.majorVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.minorVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.patchVersion_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.buildVersion_ = codedInputStream.readUInt32();
                                } else if (readTag != 42) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.versionDescriptor_ = codedInputStream.readStringRequireUtf8();
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

    public static SoftwareVersionV1 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SoftwareVersionV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
