package com.amazon.wellness.io.format.abs;

import com.google.protobuf.AbstractMessage;
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
public final class SynchronizeRealTime extends GeneratedMessageV3 implements SynchronizeRealTimeOrBuilder {
    public static final int MILLISECONDS_SINCE_THE_EPOCH_HI_FIELD_NUMBER = 2;
    public static final int MILLISECONDS_SINCE_THE_EPOCH_LO_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int millisecondsSinceTheEpochHi_;
    private int millisecondsSinceTheEpochLo_;
    private static final SynchronizeRealTime DEFAULT_INSTANCE = new SynchronizeRealTime();
    private static final Parser<SynchronizeRealTime> PARSER = new AbstractParser<SynchronizeRealTime>() { // from class: com.amazon.wellness.io.format.abs.SynchronizeRealTime.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public SynchronizeRealTime mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SynchronizeRealTime(codedInputStream, extensionRegistryLite);
        }
    };

    public static SynchronizeRealTime getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return Time.internal_static_SynchronizeRealTime_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static SynchronizeRealTime parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SynchronizeRealTime parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<SynchronizeRealTime> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SynchronizeRealTime)) {
            return super.equals(obj);
        }
        SynchronizeRealTime synchronizeRealTime = (SynchronizeRealTime) obj;
        return ((getMillisecondsSinceTheEpochLo() == synchronizeRealTime.getMillisecondsSinceTheEpochLo()) && getMillisecondsSinceTheEpochHi() == synchronizeRealTime.getMillisecondsSinceTheEpochHi()) && this.unknownFields.equals(synchronizeRealTime.unknownFields);
    }

    @Override // com.amazon.wellness.io.format.abs.SynchronizeRealTimeOrBuilder
    public int getMillisecondsSinceTheEpochHi() {
        return this.millisecondsSinceTheEpochHi_;
    }

    @Override // com.amazon.wellness.io.format.abs.SynchronizeRealTimeOrBuilder
    public int getMillisecondsSinceTheEpochLo() {
        return this.millisecondsSinceTheEpochLo_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<SynchronizeRealTime> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.millisecondsSinceTheEpochLo_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
        }
        int i4 = this.millisecondsSinceTheEpochHi_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i4);
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
        int millisecondsSinceTheEpochLo = getMillisecondsSinceTheEpochLo();
        int millisecondsSinceTheEpochHi = getMillisecondsSinceTheEpochHi();
        int hashCode = this.unknownFields.hashCode() + ((millisecondsSinceTheEpochHi + ((((millisecondsSinceTheEpochLo + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 29);
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Time.internal_static_SynchronizeRealTime_fieldAccessorTable.ensureFieldAccessorsInitialized(SynchronizeRealTime.class, Builder.class);
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
        int i = this.millisecondsSinceTheEpochLo_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.millisecondsSinceTheEpochHi_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SynchronizeRealTimeOrBuilder {
        private int millisecondsSinceTheEpochHi_;
        private int millisecondsSinceTheEpochLo_;

        public static final Descriptors.Descriptor getDescriptor() {
            return Time.internal_static_SynchronizeRealTime_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearMillisecondsSinceTheEpochHi() {
            this.millisecondsSinceTheEpochHi_ = 0;
            onChanged();
            return this;
        }

        public Builder clearMillisecondsSinceTheEpochLo() {
            this.millisecondsSinceTheEpochLo_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return Time.internal_static_SynchronizeRealTime_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.SynchronizeRealTimeOrBuilder
        public int getMillisecondsSinceTheEpochHi() {
            return this.millisecondsSinceTheEpochHi_;
        }

        @Override // com.amazon.wellness.io.format.abs.SynchronizeRealTimeOrBuilder
        public int getMillisecondsSinceTheEpochLo() {
            return this.millisecondsSinceTheEpochLo_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Time.internal_static_SynchronizeRealTime_fieldAccessorTable.ensureFieldAccessorsInitialized(SynchronizeRealTime.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setMillisecondsSinceTheEpochHi(int i) {
            this.millisecondsSinceTheEpochHi_ = i;
            onChanged();
            return this;
        }

        public Builder setMillisecondsSinceTheEpochLo(int i) {
            this.millisecondsSinceTheEpochLo_ = i;
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
        public SynchronizeRealTime mo10084build() {
            SynchronizeRealTime mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public SynchronizeRealTime mo10085buildPartial() {
            SynchronizeRealTime synchronizeRealTime = new SynchronizeRealTime(this);
            synchronizeRealTime.millisecondsSinceTheEpochLo_ = this.millisecondsSinceTheEpochLo_;
            synchronizeRealTime.millisecondsSinceTheEpochHi_ = this.millisecondsSinceTheEpochHi_;
            onBuilt();
            return synchronizeRealTime;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public SynchronizeRealTime mo10094getDefaultInstanceForType() {
            return SynchronizeRealTime.getDefaultInstance();
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
            this.millisecondsSinceTheEpochLo_ = 0;
            this.millisecondsSinceTheEpochHi_ = 0;
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
            if (message instanceof SynchronizeRealTime) {
                return mergeFrom((SynchronizeRealTime) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SynchronizeRealTime synchronizeRealTime) {
            if (synchronizeRealTime == SynchronizeRealTime.getDefaultInstance()) {
                return this;
            }
            if (synchronizeRealTime.getMillisecondsSinceTheEpochLo() != 0) {
                setMillisecondsSinceTheEpochLo(synchronizeRealTime.getMillisecondsSinceTheEpochLo());
            }
            if (synchronizeRealTime.getMillisecondsSinceTheEpochHi() != 0) {
                setMillisecondsSinceTheEpochHi(synchronizeRealTime.getMillisecondsSinceTheEpochHi());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) synchronizeRealTime).unknownFields);
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
        public com.amazon.wellness.io.format.abs.SynchronizeRealTime.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.SynchronizeRealTime.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.SynchronizeRealTime r3 = (com.amazon.wellness.io.format.abs.SynchronizeRealTime) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.SynchronizeRealTime r4 = (com.amazon.wellness.io.format.abs.SynchronizeRealTime) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.SynchronizeRealTime.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.SynchronizeRealTime$Builder");
        }
    }

    public static Builder newBuilder(SynchronizeRealTime synchronizeRealTime) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(synchronizeRealTime);
    }

    public static SynchronizeRealTime parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private SynchronizeRealTime(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static SynchronizeRealTime parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynchronizeRealTime parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public SynchronizeRealTime mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static SynchronizeRealTime parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private SynchronizeRealTime() {
        this.memoizedIsInitialized = (byte) -1;
        this.millisecondsSinceTheEpochLo_ = 0;
        this.millisecondsSinceTheEpochHi_ = 0;
    }

    public static SynchronizeRealTime parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static SynchronizeRealTime parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static SynchronizeRealTime parseFrom(InputStream inputStream) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    private SynchronizeRealTime(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.millisecondsSinceTheEpochLo_ = codedInputStream.readUInt32();
                                } else if (readTag != 16) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.millisecondsSinceTheEpochHi_ = codedInputStream.readUInt32();
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

    public static SynchronizeRealTime parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SynchronizeRealTime parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SynchronizeRealTime parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SynchronizeRealTime) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
