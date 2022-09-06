package com.amazon.wellness.io.format.abs;

import com.amazon.wellness.io.format.abs.SoftwareVersionV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class Header extends GeneratedMessageV3 implements HeaderOrBuilder {
    public static final int COMPRESSION_ALGORITHM_FIELD_NUMBER = 1;
    private static final Header DEFAULT_INSTANCE = new Header();
    private static final Parser<Header> PARSER = new AbstractParser<Header>() { // from class: com.amazon.wellness.io.format.abs.Header.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public Header mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Header(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOFTWARE_VERSION1_V1_FIELD_NUMBER = 2;
    public static final int SOFTWARE_VERSION2_V1_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private int compressionAlgorithm_;
    private byte memoizedIsInitialized;
    private SoftwareVersionV1 softwareVersion1V1_;
    private SoftwareVersionV1 softwareVersion2V1_;

    public static Header getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return HeaderOuterClass.internal_static_Header_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static Header parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Header) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Header parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Parser<Header> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return super.equals(obj);
        }
        Header header = (Header) obj;
        boolean z = (this.compressionAlgorithm_ == header.compressionAlgorithm_) && hasSoftwareVersion1V1() == header.hasSoftwareVersion1V1();
        if (hasSoftwareVersion1V1()) {
            z = z && getSoftwareVersion1V1().equals(header.getSoftwareVersion1V1());
        }
        boolean z2 = z && hasSoftwareVersion2V1() == header.hasSoftwareVersion2V1();
        if (hasSoftwareVersion2V1()) {
            z2 = z2 && getSoftwareVersion2V1().equals(header.getSoftwareVersion2V1());
        }
        return z2 && this.unknownFields.equals(header.unknownFields);
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public CompressionAlgorithm getCompressionAlgorithm() {
        CompressionAlgorithm valueOf = CompressionAlgorithm.valueOf(this.compressionAlgorithm_);
        return valueOf == null ? CompressionAlgorithm.UNRECOGNIZED : valueOf;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public int getCompressionAlgorithmValue() {
        return this.compressionAlgorithm_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<Header> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.compressionAlgorithm_ != CompressionAlgorithm.NONE.getNumber()) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.compressionAlgorithm_);
        }
        if (this.softwareVersion1V1_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getSoftwareVersion1V1());
        }
        if (this.softwareVersion2V1_ != null) {
            i2 += CodedOutputStream.computeMessageSize(3, getSoftwareVersion2V1());
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public SoftwareVersionV1 getSoftwareVersion1V1() {
        SoftwareVersionV1 softwareVersionV1 = this.softwareVersion1V1_;
        return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public SoftwareVersionV1OrBuilder getSoftwareVersion1V1OrBuilder() {
        return getSoftwareVersion1V1();
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public SoftwareVersionV1 getSoftwareVersion2V1() {
        SoftwareVersionV1 softwareVersionV1 = this.softwareVersion2V1_;
        return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public SoftwareVersionV1OrBuilder getSoftwareVersion2V1OrBuilder() {
        return getSoftwareVersion2V1();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public boolean hasSoftwareVersion1V1() {
        return this.softwareVersion1V1_ != null;
    }

    @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
    public boolean hasSoftwareVersion2V1() {
        return this.softwareVersion2V1_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53) + this.compressionAlgorithm_;
        if (hasSoftwareVersion1V1()) {
            hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getSoftwareVersion1V1().hashCode();
        }
        if (hasSoftwareVersion2V1()) {
            hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getSoftwareVersion2V1().hashCode();
        }
        int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return HeaderOuterClass.internal_static_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
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
        if (this.compressionAlgorithm_ != CompressionAlgorithm.NONE.getNumber()) {
            codedOutputStream.writeEnum(1, this.compressionAlgorithm_);
        }
        if (this.softwareVersion1V1_ != null) {
            codedOutputStream.writeMessage(2, getSoftwareVersion1V1());
        }
        if (this.softwareVersion2V1_ != null) {
            codedOutputStream.writeMessage(3, getSoftwareVersion2V1());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    /* loaded from: classes13.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderOrBuilder {
        private int compressionAlgorithm_;
        private SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> softwareVersion1V1Builder_;
        private SoftwareVersionV1 softwareVersion1V1_;
        private SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> softwareVersion2V1Builder_;
        private SoftwareVersionV1 softwareVersion2V1_;

        public static final Descriptors.Descriptor getDescriptor() {
            return HeaderOuterClass.internal_static_Header_descriptor;
        }

        private SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> getSoftwareVersion1V1FieldBuilder() {
            if (this.softwareVersion1V1Builder_ == null) {
                this.softwareVersion1V1Builder_ = new SingleFieldBuilderV3<>(getSoftwareVersion1V1(), getParentForChildren(), isClean());
                this.softwareVersion1V1_ = null;
            }
            return this.softwareVersion1V1Builder_;
        }

        private SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> getSoftwareVersion2V1FieldBuilder() {
            if (this.softwareVersion2V1Builder_ == null) {
                this.softwareVersion2V1Builder_ = new SingleFieldBuilderV3<>(getSoftwareVersion2V1(), getParentForChildren(), isClean());
                this.softwareVersion2V1_ = null;
            }
            return this.softwareVersion2V1Builder_;
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
        }

        public Builder clearCompressionAlgorithm() {
            this.compressionAlgorithm_ = 0;
            onChanged();
            return this;
        }

        public Builder clearSoftwareVersion1V1() {
            if (this.softwareVersion1V1Builder_ == null) {
                this.softwareVersion1V1_ = null;
                onChanged();
            } else {
                this.softwareVersion1V1_ = null;
                this.softwareVersion1V1Builder_ = null;
            }
            return this;
        }

        public Builder clearSoftwareVersion2V1() {
            if (this.softwareVersion2V1Builder_ == null) {
                this.softwareVersion2V1_ = null;
                onChanged();
            } else {
                this.softwareVersion2V1_ = null;
                this.softwareVersion2V1Builder_ = null;
            }
            return this;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public CompressionAlgorithm getCompressionAlgorithm() {
            CompressionAlgorithm valueOf = CompressionAlgorithm.valueOf(this.compressionAlgorithm_);
            return valueOf == null ? CompressionAlgorithm.UNRECOGNIZED : valueOf;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public int getCompressionAlgorithmValue() {
            return this.compressionAlgorithm_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return HeaderOuterClass.internal_static_Header_descriptor;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public SoftwareVersionV1 getSoftwareVersion1V1() {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 == null) {
                SoftwareVersionV1 softwareVersionV1 = this.softwareVersion1V1_;
                return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public SoftwareVersionV1.Builder getSoftwareVersion1V1Builder() {
            onChanged();
            return getSoftwareVersion1V1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public SoftwareVersionV1OrBuilder getSoftwareVersion1V1OrBuilder() {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SoftwareVersionV1 softwareVersionV1 = this.softwareVersion1V1_;
            return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public SoftwareVersionV1 getSoftwareVersion2V1() {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV3 == null) {
                SoftwareVersionV1 softwareVersionV1 = this.softwareVersion2V1_;
                return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public SoftwareVersionV1.Builder getSoftwareVersion2V1Builder() {
            onChanged();
            return getSoftwareVersion2V1FieldBuilder().getBuilder();
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public SoftwareVersionV1OrBuilder getSoftwareVersion2V1OrBuilder() {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SoftwareVersionV1 softwareVersionV1 = this.softwareVersion2V1_;
            return softwareVersionV1 == null ? SoftwareVersionV1.getDefaultInstance() : softwareVersionV1;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public boolean hasSoftwareVersion1V1() {
            return (this.softwareVersion1V1Builder_ == null && this.softwareVersion1V1_ == null) ? false : true;
        }

        @Override // com.amazon.wellness.io.format.abs.HeaderOrBuilder
        public boolean hasSoftwareVersion2V1() {
            return (this.softwareVersion2V1Builder_ == null && this.softwareVersion2V1_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return HeaderOuterClass.internal_static_Header_fieldAccessorTable.ensureFieldAccessorsInitialized(Header.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeSoftwareVersion1V1(SoftwareVersionV1 softwareVersionV1) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 == null) {
                SoftwareVersionV1 softwareVersionV12 = this.softwareVersion1V1_;
                if (softwareVersionV12 != null) {
                    this.softwareVersion1V1_ = SoftwareVersionV1.newBuilder(softwareVersionV12).mergeFrom(softwareVersionV1).mo10085buildPartial();
                } else {
                    this.softwareVersion1V1_ = softwareVersionV1;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(softwareVersionV1);
            }
            return this;
        }

        public Builder mergeSoftwareVersion2V1(SoftwareVersionV1 softwareVersionV1) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV3 == null) {
                SoftwareVersionV1 softwareVersionV12 = this.softwareVersion2V1_;
                if (softwareVersionV12 != null) {
                    this.softwareVersion2V1_ = SoftwareVersionV1.newBuilder(softwareVersionV12).mergeFrom(softwareVersionV1).mo10085buildPartial();
                } else {
                    this.softwareVersion2V1_ = softwareVersionV1;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(softwareVersionV1);
            }
            return this;
        }

        public Builder setCompressionAlgorithm(CompressionAlgorithm compressionAlgorithm) {
            if (compressionAlgorithm != null) {
                this.compressionAlgorithm_ = compressionAlgorithm.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setCompressionAlgorithmValue(int i) {
            this.compressionAlgorithm_ = i;
            onChanged();
            return this;
        }

        public Builder setSoftwareVersion1V1(SoftwareVersionV1 softwareVersionV1) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(softwareVersionV1);
            } else if (softwareVersionV1 != null) {
                this.softwareVersion1V1_ = softwareVersionV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSoftwareVersion2V1(SoftwareVersionV1 softwareVersionV1) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(softwareVersionV1);
            } else if (softwareVersionV1 != null) {
                this.softwareVersion2V1_ = softwareVersionV1;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        private Builder() {
            this.compressionAlgorithm_ = 0;
            this.softwareVersion1V1_ = null;
            this.softwareVersion2V1_ = null;
            maybeForceBuilderInitialization();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public Header mo10084build() {
            Header mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public Header mo10085buildPartial() {
            Header header = new Header(this);
            header.compressionAlgorithm_ = this.compressionAlgorithm_;
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 == null) {
                header.softwareVersion1V1_ = this.softwareVersion1V1_;
            } else {
                header.softwareVersion1V1_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV32 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV32 == null) {
                header.softwareVersion2V1_ = this.softwareVersion2V1_;
            } else {
                header.softwareVersion2V1_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return header;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Header mo10094getDefaultInstanceForType() {
            return Header.getDefaultInstance();
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
            this.compressionAlgorithm_ = 0;
            if (this.softwareVersion1V1Builder_ == null) {
                this.softwareVersion1V1_ = null;
            } else {
                this.softwareVersion1V1_ = null;
                this.softwareVersion1V1Builder_ = null;
            }
            if (this.softwareVersion2V1Builder_ == null) {
                this.softwareVersion2V1_ = null;
            } else {
                this.softwareVersion2V1_ = null;
                this.softwareVersion2V1Builder_ = null;
            }
            return this;
        }

        public Builder setSoftwareVersion1V1(SoftwareVersionV1.Builder builder) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion1V1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.softwareVersion1V1_ = builder.mo10084build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder setSoftwareVersion2V1(SoftwareVersionV1.Builder builder) {
            SingleFieldBuilderV3<SoftwareVersionV1, SoftwareVersionV1.Builder, SoftwareVersionV1OrBuilder> singleFieldBuilderV3 = this.softwareVersion2V1Builder_;
            if (singleFieldBuilderV3 == null) {
                this.softwareVersion2V1_ = builder.mo10084build();
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
            if (message instanceof Header) {
                return mergeFrom((Header) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.compressionAlgorithm_ = 0;
            this.softwareVersion1V1_ = null;
            this.softwareVersion2V1_ = null;
            maybeForceBuilderInitialization();
        }

        public Builder mergeFrom(Header header) {
            if (header == Header.getDefaultInstance()) {
                return this;
            }
            if (header.compressionAlgorithm_ != 0) {
                setCompressionAlgorithmValue(header.getCompressionAlgorithmValue());
            }
            if (header.hasSoftwareVersion1V1()) {
                mergeSoftwareVersion1V1(header.getSoftwareVersion1V1());
            }
            if (header.hasSoftwareVersion2V1()) {
                mergeSoftwareVersion2V1(header.getSoftwareVersion2V1());
            }
            mo10099mergeUnknownFields(((GeneratedMessageV3) header).unknownFields);
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
        public com.amazon.wellness.io.format.abs.Header.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.amazon.wellness.io.format.abs.Header.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.amazon.wellness.io.format.abs.Header r3 = (com.amazon.wellness.io.format.abs.Header) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.amazon.wellness.io.format.abs.Header r4 = (com.amazon.wellness.io.format.abs.Header) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.wellness.io.format.abs.Header.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.wellness.io.format.abs.Header$Builder");
        }
    }

    public static Builder newBuilder(Header header) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(header);
    }

    public static Header parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    private Header(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Header parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Header) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Header parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public Header mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    public static Header parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    private Header() {
        this.memoizedIsInitialized = (byte) -1;
        this.compressionAlgorithm_ = 0;
    }

    public static Header parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static Header parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static Header parseFrom(InputStream inputStream) throws IOException {
        return (Header) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    private Header(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag != 8) {
                                SoftwareVersionV1.Builder builder = null;
                                if (readTag == 18) {
                                    builder = this.softwareVersion1V1_ != null ? this.softwareVersion1V1_.mo10081toBuilder() : builder;
                                    this.softwareVersion1V1_ = (SoftwareVersionV1) codedInputStream.readMessage(SoftwareVersionV1.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.softwareVersion1V1_);
                                        this.softwareVersion1V1_ = builder.mo10085buildPartial();
                                    }
                                } else if (readTag != 26) {
                                    if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    builder = this.softwareVersion2V1_ != null ? this.softwareVersion2V1_.mo10081toBuilder() : builder;
                                    this.softwareVersion2V1_ = (SoftwareVersionV1) codedInputStream.readMessage(SoftwareVersionV1.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.softwareVersion2V1_);
                                        this.softwareVersion2V1_ = builder.mo10085buildPartial();
                                    }
                                }
                            } else {
                                this.compressionAlgorithm_ = codedInputStream.readEnum();
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

    public static Header parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Header) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Header parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Header) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Header parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Header) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }
}
