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
public final class MovementSummaryV1 extends GeneratedMessageV3 implements MovementSummaryV1OrBuilder {
    public static final int MOVEMENT_SUMMARY_AGGREGATE_FIELD_NUMBER = 4;
    public static final int MOVEMENT_SUMMARY_MEAN_FIELD_NUMBER = 1;
    public static final int MOVEMENT_SUMMARY_STANDARD_DEVIATION_FIELD_NUMBER = 2;
    public static final int MOVEMENT_SUMMARY_TOTAL_FIELD_NUMBER = 3;
    public static final int MOVEMENT_WINDOW_SIZE_IN_SECONDS_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private float movementSummaryAggregate_;
    private float movementSummaryMean_;
    private float movementSummaryStandardDeviation_;
    private float movementSummaryTotal_;
    private int movementWindowSizeInSeconds_;
    private static final MovementSummaryV1 DEFAULT_INSTANCE = new MovementSummaryV1();
    private static final Parser<MovementSummaryV1> PARSER = new AbstractParser<MovementSummaryV1>() { // from class: com.amazon.wellness.io.format.abs.MovementSummaryV1.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public MovementSummaryV1 mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MovementSummaryV1(codedInputStream, extensionRegistryLite);
        }
    };

    public static MovementSummaryV1 getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return Biometric.internal_static_MovementSummaryV1_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static MovementSummaryV1 parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MovementSummaryV1 parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<MovementSummaryV1> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MovementSummaryV1)) {
            return super.equals(obj);
        }
        MovementSummaryV1 movementSummaryV1 = (MovementSummaryV1) obj;
        return (((((Float.floatToIntBits(getMovementSummaryMean()) == Float.floatToIntBits(movementSummaryV1.getMovementSummaryMean())) && Float.floatToIntBits(getMovementSummaryStandardDeviation()) == Float.floatToIntBits(movementSummaryV1.getMovementSummaryStandardDeviation())) && Float.floatToIntBits(getMovementSummaryTotal()) == Float.floatToIntBits(movementSummaryV1.getMovementSummaryTotal())) && Float.floatToIntBits(getMovementSummaryAggregate()) == Float.floatToIntBits(movementSummaryV1.getMovementSummaryAggregate())) && getMovementWindowSizeInSeconds() == movementSummaryV1.getMovementWindowSizeInSeconds()) && this.unknownFields.equals(movementSummaryV1.unknownFields);
    }

    @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
    public float getMovementSummaryAggregate() {
        return this.movementSummaryAggregate_;
    }

    @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
    public float getMovementSummaryMean() {
        return this.movementSummaryMean_;
    }

    @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
    public float getMovementSummaryStandardDeviation() {
        return this.movementSummaryStandardDeviation_;
    }

    @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
    public float getMovementSummaryTotal() {
        return this.movementSummaryTotal_;
    }

    @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
    public int getMovementWindowSizeInSeconds() {
        return this.movementWindowSizeInSeconds_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<MovementSummaryV1> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        float f = this.movementSummaryMean_;
        if (f != 0.0f) {
            i2 = 0 + CodedOutputStream.computeFloatSize(1, f);
        }
        float f2 = this.movementSummaryStandardDeviation_;
        if (f2 != 0.0f) {
            i2 += CodedOutputStream.computeFloatSize(2, f2);
        }
        float f3 = this.movementSummaryTotal_;
        if (f3 != 0.0f) {
            i2 += CodedOutputStream.computeFloatSize(3, f3);
        }
        float f4 = this.movementSummaryAggregate_;
        if (f4 != 0.0f) {
            i2 += CodedOutputStream.computeFloatSize(4, f4);
        }
        int i3 = this.movementWindowSizeInSeconds_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(5, i3);
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
        int floatToIntBits = Float.floatToIntBits(getMovementSummaryMean());
        int floatToIntBits2 = Float.floatToIntBits(getMovementSummaryStandardDeviation());
        int floatToIntBits3 = Float.floatToIntBits(getMovementSummaryTotal());
        int floatToIntBits4 = Float.floatToIntBits(getMovementSummaryAggregate());
        int movementWindowSizeInSeconds = getMovementWindowSizeInSeconds();
        int hashCode = this.unknownFields.hashCode() + ((movementWindowSizeInSeconds + ((((floatToIntBits4 + ((((floatToIntBits3 + ((((floatToIntBits2 + ((((floatToIntBits + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53)) * 37) + 2) * 53)) * 37) + 3) * 53)) * 37) + 4) * 53)) * 37) + 5) * 53)) * 29);
        this.memoizedHashCode = hashCode;
        return hashCode;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Biometric.internal_static_MovementSummaryV1_fieldAccessorTable.ensureFieldAccessorsInitialized(MovementSummaryV1.class, Builder.class);
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
        float f = this.movementSummaryMean_;
        if (f != 0.0f) {
            codedOutputStream.writeFloat(1, f);
        }
        float f2 = this.movementSummaryStandardDeviation_;
        if (f2 != 0.0f) {
            codedOutputStream.writeFloat(2, f2);
        }
        float f3 = this.movementSummaryTotal_;
        if (f3 != 0.0f) {
            codedOutputStream.writeFloat(3, f3);
        }
        float f4 = this.movementSummaryAggregate_;
        if (f4 != 0.0f) {
            codedOutputStream.writeFloat(4, f4);
        }
        int i = this.movementWindowSizeInSeconds_;
        if (i != 0) {
            codedOutputStream.writeUInt32(5, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MovementSummaryV1OrBuilder {
        private float movementSummaryAggregate_;
        private float movementSummaryMean_;
        private float movementSummaryStandardDeviation_;
        private float movementSummaryTotal_;
        private int movementWindowSizeInSeconds_;

        public static final Descriptors.Descriptor getDescriptor() {
            return Biometric.internal_static_MovementSummaryV1_descriptor;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearMovementSummaryAggregate() {
            this.movementSummaryAggregate_ = 0.0f;
            onChanged();
            return this;
        }

        public Builder clearMovementSummaryMean() {
            this.movementSummaryMean_ = 0.0f;
            onChanged();
            return this;
        }

        public Builder clearMovementSummaryStandardDeviation() {
            this.movementSummaryStandardDeviation_ = 0.0f;
            onChanged();
            return this;
        }

        public Builder clearMovementSummaryTotal() {
            this.movementSummaryTotal_ = 0.0f;
            onChanged();
            return this;
        }

        public Builder clearMovementWindowSizeInSeconds() {
            this.movementWindowSizeInSeconds_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return Biometric.internal_static_MovementSummaryV1_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
        public float getMovementSummaryAggregate() {
            return this.movementSummaryAggregate_;
        }

        @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
        public float getMovementSummaryMean() {
            return this.movementSummaryMean_;
        }

        @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
        public float getMovementSummaryStandardDeviation() {
            return this.movementSummaryStandardDeviation_;
        }

        @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
        public float getMovementSummaryTotal() {
            return this.movementSummaryTotal_;
        }

        @Override // com.amazon.wellness.io.format.abs.MovementSummaryV1OrBuilder
        public int getMovementWindowSizeInSeconds() {
            return this.movementWindowSizeInSeconds_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return Biometric.internal_static_MovementSummaryV1_fieldAccessorTable.ensureFieldAccessorsInitialized(MovementSummaryV1.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder setMovementSummaryAggregate(float f) {
            this.movementSummaryAggregate_ = f;
            onChanged();
            return this;
        }

        public Builder setMovementSummaryMean(float f) {
            this.movementSummaryMean_ = f;
            onChanged();
            return this;
        }

        public Builder setMovementSummaryStandardDeviation(float f) {
            this.movementSummaryStandardDeviation_ = f;
            onChanged();
            return this;
        }

        public Builder setMovementSummaryTotal(float f) {
            this.movementSummaryTotal_ = f;
            onChanged();
            return this;
        }

        public Builder setMovementWindowSizeInSeconds(int i) {
            this.movementWindowSizeInSeconds_ = i;
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
        public MovementSummaryV1 mo10084build() {
            MovementSummaryV1 mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public MovementSummaryV1 mo10085buildPartial() {
            MovementSummaryV1 movementSummaryV1 = new MovementSummaryV1(this);
            movementSummaryV1.movementSummaryMean_ = this.movementSummaryMean_;
            movementSummaryV1.movementSummaryStandardDeviation_ = this.movementSummaryStandardDeviation_;
            movementSummaryV1.movementSummaryTotal_ = this.movementSummaryTotal_;
            movementSummaryV1.movementSummaryAggregate_ = this.movementSummaryAggregate_;
            movementSummaryV1.movementWindowSizeInSeconds_ = this.movementWindowSizeInSeconds_;
            onBuilt();
            return movementSummaryV1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public MovementSummaryV1 mo10094getDefaultInstanceForType() {
            return MovementSummaryV1.getDefaultInstance();
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
            this.movementSummaryMean_ = 0.0f;
            this.movementSummaryStandardDeviation_ = 0.0f;
            this.movementSummaryTotal_ = 0.0f;
            this.movementSummaryAggregate_ = 0.0f;
            this.movementWindowSizeInSeconds_ = 0;
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
            if (message instanceof MovementSummaryV1) {
                return mergeFrom((MovementSummaryV1) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MovementSummaryV1 movementSummaryV1) {
            if (movementSummaryV1 == MovementSummaryV1.getDefaultInstance()) {
                return this;
            }
            if (movementSummaryV1.getMovementSummaryMean() != 0.0f) {
                setMovementSummaryMean(movementSummaryV1.getMovementSummaryMean());
            }
            if (movementSummaryV1.getMovementSummaryStandardDeviation() != 0.0f) {
                setMovementSummaryStandardDeviation(movementSummaryV1.getMovementSummaryStandardDeviation());
            }
            if (movementSummaryV1.getMovementSummaryTotal() != 0.0f) {
                setMovementSummaryTotal(movementSummaryV1.getMovementSummaryTotal());
            }
            if (movementSummaryV1.getMovementSummaryAggregate() != 0.0f) {
                setMovementSummaryAggregate(movementSummaryV1.getMovementSummaryAggregate());
            }
            if (movementSummaryV1.getMovementWindowSizeInSeconds() != 0) {
                setMovementWindowSizeInSeconds(movementSummaryV1.getMovementWindowSizeInSeconds());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) movementSummaryV1).unknownFields);
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
        public com.amazon.wellness.io.format.abs.MovementSummaryV1.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.MovementSummaryV1.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.MovementSummaryV1 r3 = (com.amazon.wellness.io.format.abs.MovementSummaryV1) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.MovementSummaryV1 r4 = (com.amazon.wellness.io.format.abs.MovementSummaryV1) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.MovementSummaryV1.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.MovementSummaryV1$Builder");
        }
    }

    public static Builder newBuilder(MovementSummaryV1 movementSummaryV1) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(movementSummaryV1);
    }

    public static MovementSummaryV1 parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private MovementSummaryV1(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static MovementSummaryV1 parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MovementSummaryV1 parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public MovementSummaryV1 mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static MovementSummaryV1 parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private MovementSummaryV1() {
        this.memoizedIsInitialized = (byte) -1;
        this.movementSummaryMean_ = 0.0f;
        this.movementSummaryStandardDeviation_ = 0.0f;
        this.movementSummaryTotal_ = 0.0f;
        this.movementSummaryAggregate_ = 0.0f;
        this.movementWindowSizeInSeconds_ = 0;
    }

    public static MovementSummaryV1 parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static MovementSummaryV1 parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static MovementSummaryV1 parseFrom(InputStream inputStream) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MovementSummaryV1 parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MovementSummaryV1 parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    private MovementSummaryV1(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (readTag == 13) {
                                    this.movementSummaryMean_ = codedInputStream.readFloat();
                                } else if (readTag == 21) {
                                    this.movementSummaryStandardDeviation_ = codedInputStream.readFloat();
                                } else if (readTag == 29) {
                                    this.movementSummaryTotal_ = codedInputStream.readFloat();
                                } else if (readTag == 37) {
                                    this.movementSummaryAggregate_ = codedInputStream.readFloat();
                                } else if (readTag != 40) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.movementWindowSizeInSeconds_ = codedInputStream.readUInt32();
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

    public static MovementSummaryV1 parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MovementSummaryV1) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
