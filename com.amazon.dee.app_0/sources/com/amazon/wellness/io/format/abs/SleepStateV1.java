package com.amazon.wellness.io.format.abs;

import com.google.protobuf.AbstractMessage;
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
/* loaded from: classes13.dex */
public final class SleepStateV1 extends GeneratedMessageV3 implements SleepStateV1OrBuilder {
    public static final int INTEND_TO_SLEEP_FIELD_NUMBER = 5;
    public static final int INTEND_TO_SLEEP_LAST_CHANGE_MS_UPTIME_H_FIELD_NUMBER = 2;
    public static final int INTEND_TO_SLEEP_LAST_CHANGE_MS_UPTIME_L_FIELD_NUMBER = 3;
    public static final int PERCENTAGE_ASLEEP_FIELD_NUMBER = 1;
    public static final int SHOULD_BE_PROCESSED_FOR_SLEEP_FIELD_NUMBER = 4;
    private static final long serialVersionUID = 0;
    private int intendToSleepLastChangeMsUptimeH_;
    private int intendToSleepLastChangeMsUptimeL_;
    private boolean intendToSleep_;
    private byte memoizedIsInitialized;
    private int percentageAsleep_;
    private boolean shouldBeProcessedForSleep_;
    private static final SleepStateV1 DEFAULT_INSTANCE = new SleepStateV1();
    private static final Parser<SleepStateV1> PARSER = new AbstractParser<SleepStateV1>() { // from class: com.amazon.wellness.io.format.abs.SleepStateV1.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public SleepStateV1 mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SleepStateV1(codedInputStream, extensionRegistryLite);
        }
    };

    public static SleepStateV1 getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return Biometric.internal_static_SleepStateV1_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static SleepStateV1 parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SleepStateV1 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<SleepStateV1> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SleepStateV1)) {
            return super.equals(obj);
        }
        SleepStateV1 sleepStateV1 = (SleepStateV1) obj;
        return (((((getPercentageAsleep() == sleepStateV1.getPercentageAsleep()) && getIntendToSleepLastChangeMsUptimeH() == sleepStateV1.getIntendToSleepLastChangeMsUptimeH()) && getIntendToSleepLastChangeMsUptimeL() == sleepStateV1.getIntendToSleepLastChangeMsUptimeL()) && getShouldBeProcessedForSleep() == sleepStateV1.getShouldBeProcessedForSleep()) && getIntendToSleep() == sleepStateV1.getIntendToSleep()) && this.unknownFields.equals(sleepStateV1.unknownFields);
    }

    @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
    public boolean getIntendToSleep() {
        return this.intendToSleep_;
    }

    @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
    public int getIntendToSleepLastChangeMsUptimeH() {
        return this.intendToSleepLastChangeMsUptimeH_;
    }

    @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
    public int getIntendToSleepLastChangeMsUptimeL() {
        return this.intendToSleepLastChangeMsUptimeL_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<SleepStateV1> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
    public int getPercentageAsleep() {
        return this.percentageAsleep_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.percentageAsleep_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
        }
        int i4 = this.intendToSleepLastChangeMsUptimeH_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i4);
        }
        int i5 = this.intendToSleepLastChangeMsUptimeL_;
        if (i5 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(3, i5);
        }
        boolean z = this.shouldBeProcessedForSleep_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(4, z);
        }
        boolean z2 = this.intendToSleep_;
        if (z2) {
            i2 += CodedOutputStream.computeBoolSize(5, z2);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
    public boolean getShouldBeProcessedForSleep() {
        return this.shouldBeProcessedForSleep_;
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
        int percentageAsleep = getPercentageAsleep();
        int intendToSleepLastChangeMsUptimeH = getIntendToSleepLastChangeMsUptimeH();
        int intendToSleepLastChangeMsUptimeL = getIntendToSleepLastChangeMsUptimeL();
        int hashBoolean = Internal.hashBoolean(getShouldBeProcessedForSleep());
        int hashBoolean2 = Internal.hashBoolean(getIntendToSleep());
        int hashCode = this.unknownFields.hashCode() + ((hashBoolean2 + ((((hashBoolean + ((((intendToSleepLastChangeMsUptimeL + ((((intendToSleepLastChangeMsUptimeH + ((((percentageAsleep + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 29);
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Biometric.internal_static_SleepStateV1_fieldAccessorTable.ensureFieldAccessorsInitialized(SleepStateV1.class, Builder.class);
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
        int i = this.percentageAsleep_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.intendToSleepLastChangeMsUptimeH_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
        int i3 = this.intendToSleepLastChangeMsUptimeL_;
        if (i3 != 0) {
            codedOutputStream.writeUInt32(3, i3);
        }
        boolean z = this.shouldBeProcessedForSleep_;
        if (z) {
            codedOutputStream.writeBool(4, z);
        }
        boolean z2 = this.intendToSleep_;
        if (z2) {
            codedOutputStream.writeBool(5, z2);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SleepStateV1OrBuilder {
        private int intendToSleepLastChangeMsUptimeH_;
        private int intendToSleepLastChangeMsUptimeL_;
        private boolean intendToSleep_;
        private int percentageAsleep_;
        private boolean shouldBeProcessedForSleep_;

        public static final Descriptors.Descriptor getDescriptor() {
            return Biometric.internal_static_SleepStateV1_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearIntendToSleep() {
            this.intendToSleep_ = false;
            onChanged();
            return this;
        }

        public Builder clearIntendToSleepLastChangeMsUptimeH() {
            this.intendToSleepLastChangeMsUptimeH_ = 0;
            onChanged();
            return this;
        }

        public Builder clearIntendToSleepLastChangeMsUptimeL() {
            this.intendToSleepLastChangeMsUptimeL_ = 0;
            onChanged();
            return this;
        }

        public Builder clearPercentageAsleep() {
            this.percentageAsleep_ = 0;
            onChanged();
            return this;
        }

        public Builder clearShouldBeProcessedForSleep() {
            this.shouldBeProcessedForSleep_ = false;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return Biometric.internal_static_SleepStateV1_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
        public boolean getIntendToSleep() {
            return this.intendToSleep_;
        }

        @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
        public int getIntendToSleepLastChangeMsUptimeH() {
            return this.intendToSleepLastChangeMsUptimeH_;
        }

        @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
        public int getIntendToSleepLastChangeMsUptimeL() {
            return this.intendToSleepLastChangeMsUptimeL_;
        }

        @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
        public int getPercentageAsleep() {
            return this.percentageAsleep_;
        }

        @Override // com.amazon.wellness.io.format.abs.SleepStateV1OrBuilder
        public boolean getShouldBeProcessedForSleep() {
            return this.shouldBeProcessedForSleep_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Biometric.internal_static_SleepStateV1_fieldAccessorTable.ensureFieldAccessorsInitialized(SleepStateV1.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setIntendToSleep(boolean z) {
            this.intendToSleep_ = z;
            onChanged();
            return this;
        }

        public Builder setIntendToSleepLastChangeMsUptimeH(int i) {
            this.intendToSleepLastChangeMsUptimeH_ = i;
            onChanged();
            return this;
        }

        public Builder setIntendToSleepLastChangeMsUptimeL(int i) {
            this.intendToSleepLastChangeMsUptimeL_ = i;
            onChanged();
            return this;
        }

        public Builder setPercentageAsleep(int i) {
            this.percentageAsleep_ = i;
            onChanged();
            return this;
        }

        public Builder setShouldBeProcessedForSleep(boolean z) {
            this.shouldBeProcessedForSleep_ = z;
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
        public SleepStateV1 mo10084build() {
            SleepStateV1 mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public SleepStateV1 mo10085buildPartial() {
            SleepStateV1 sleepStateV1 = new SleepStateV1(this);
            sleepStateV1.percentageAsleep_ = this.percentageAsleep_;
            sleepStateV1.intendToSleepLastChangeMsUptimeH_ = this.intendToSleepLastChangeMsUptimeH_;
            sleepStateV1.intendToSleepLastChangeMsUptimeL_ = this.intendToSleepLastChangeMsUptimeL_;
            sleepStateV1.shouldBeProcessedForSleep_ = this.shouldBeProcessedForSleep_;
            sleepStateV1.intendToSleep_ = this.intendToSleep_;
            onBuilt();
            return sleepStateV1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public SleepStateV1 mo10094getDefaultInstanceForType() {
            return SleepStateV1.getDefaultInstance();
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
            this.percentageAsleep_ = 0;
            this.intendToSleepLastChangeMsUptimeH_ = 0;
            this.intendToSleepLastChangeMsUptimeL_ = 0;
            this.shouldBeProcessedForSleep_ = false;
            this.intendToSleep_ = false;
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
            if (message instanceof SleepStateV1) {
                return mergeFrom((SleepStateV1) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SleepStateV1 sleepStateV1) {
            if (sleepStateV1 == SleepStateV1.getDefaultInstance()) {
                return this;
            }
            if (sleepStateV1.getPercentageAsleep() != 0) {
                setPercentageAsleep(sleepStateV1.getPercentageAsleep());
            }
            if (sleepStateV1.getIntendToSleepLastChangeMsUptimeH() != 0) {
                setIntendToSleepLastChangeMsUptimeH(sleepStateV1.getIntendToSleepLastChangeMsUptimeH());
            }
            if (sleepStateV1.getIntendToSleepLastChangeMsUptimeL() != 0) {
                setIntendToSleepLastChangeMsUptimeL(sleepStateV1.getIntendToSleepLastChangeMsUptimeL());
            }
            if (sleepStateV1.getShouldBeProcessedForSleep()) {
                setShouldBeProcessedForSleep(sleepStateV1.getShouldBeProcessedForSleep());
            }
            if (sleepStateV1.getIntendToSleep()) {
                setIntendToSleep(sleepStateV1.getIntendToSleep());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) sleepStateV1).unknownFields);
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
        public com.amazon.wellness.io.format.abs.SleepStateV1.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.SleepStateV1.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.SleepStateV1 r3 = (com.amazon.wellness.io.format.abs.SleepStateV1) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.SleepStateV1 r4 = (com.amazon.wellness.io.format.abs.SleepStateV1) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.SleepStateV1.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.SleepStateV1$Builder");
        }
    }

    public static Builder newBuilder(SleepStateV1 sleepStateV1) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(sleepStateV1);
    }

    public static SleepStateV1 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private SleepStateV1(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static SleepStateV1 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SleepStateV1 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public SleepStateV1 mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static SleepStateV1 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private SleepStateV1() {
        this.memoizedIsInitialized = (byte) -1;
        this.percentageAsleep_ = 0;
        this.intendToSleepLastChangeMsUptimeH_ = 0;
        this.intendToSleepLastChangeMsUptimeL_ = 0;
        this.shouldBeProcessedForSleep_ = false;
        this.intendToSleep_ = false;
    }

    public static SleepStateV1 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static SleepStateV1 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static SleepStateV1 parseFrom(InputStream inputStream) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SleepStateV1 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SleepStateV1 parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private SleepStateV1(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.percentageAsleep_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.intendToSleepLastChangeMsUptimeH_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.intendToSleepLastChangeMsUptimeL_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.shouldBeProcessedForSleep_ = codedInputStream.readBool();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.intendToSleep_ = codedInputStream.readBool();
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

    public static SleepStateV1 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SleepStateV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
