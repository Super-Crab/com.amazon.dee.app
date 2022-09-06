package com.amazon.whispercloak.protobuf;

import com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
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
/* loaded from: classes13.dex */
public final class ProtobufCalculateKnowledgeProofClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufCalculateKnowledgeProof_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufCalculateKnowledgeProof extends GeneratedMessageV3 implements ProtobufCalculateKnowledgeProofOrBuilder {
        public static final int GV_FIELD_NUMBER = 1;
        public static final int R_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ProtobufBigIntegerClass.ProtobufBigInteger gv_;
        private byte memoizedIsInitialized;
        private ProtobufBigIntegerClass.ProtobufBigInteger r_;
        private static final ProtobufCalculateKnowledgeProof DEFAULT_INSTANCE = new ProtobufCalculateKnowledgeProof();
        @Deprecated
        public static final Parser<ProtobufCalculateKnowledgeProof> PARSER = new AbstractParser<ProtobufCalculateKnowledgeProof>() { // from class: com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufCalculateKnowledgeProof mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufCalculateKnowledgeProof(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufCalculateKnowledgeProof getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufCalculateKnowledgeProofClass.internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufCalculateKnowledgeProof parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufCalculateKnowledgeProof> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufCalculateKnowledgeProof)) {
                return super.equals(obj);
            }
            ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = (ProtobufCalculateKnowledgeProof) obj;
            boolean z = hasGv() == protobufCalculateKnowledgeProof.hasGv();
            if (hasGv()) {
                z = z && getGv().equals(protobufCalculateKnowledgeProof.getGv());
            }
            boolean z2 = z && hasR() == protobufCalculateKnowledgeProof.hasR();
            if (hasR()) {
                z2 = z2 && getR().equals(protobufCalculateKnowledgeProof.getR());
            }
            return z2 && this.unknownFields.equals(protobufCalculateKnowledgeProof.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getGv() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gv_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGvOrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gv_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufCalculateKnowledgeProof> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getR() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.r_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getROrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.r_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getGv());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getR());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public boolean hasGv() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
        public boolean hasR() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasGv()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getGv().hashCode();
            }
            if (hasR()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getR().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufCalculateKnowledgeProofClass.internal_static_protobuf_ProtobufCalculateKnowledgeProof_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCalculateKnowledgeProof.class, Builder.class);
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
            if (!hasGv()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasR()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getGv().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getR().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, getGv());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getR());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufCalculateKnowledgeProofOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> gvBuilder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger gv_;
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> rBuilder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger r_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufCalculateKnowledgeProofClass.internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getGvFieldBuilder() {
                if (this.gvBuilder_ == null) {
                    this.gvBuilder_ = new SingleFieldBuilderV3<>(getGv(), getParentForChildren(), isClean());
                    this.gv_ = null;
                }
                return this.gvBuilder_;
            }

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getRFieldBuilder() {
                if (this.rBuilder_ == null) {
                    this.rBuilder_ = new SingleFieldBuilderV3<>(getR(), getParentForChildren(), isClean());
                    this.r_ = null;
                }
                return this.rBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getGvFieldBuilder();
                    getRFieldBuilder();
                }
            }

            public Builder clearGv() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.gv_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clearR() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.r_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufCalculateKnowledgeProofClass.internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getGv() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gv_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getGvBuilder() {
                this.bitField0_ |= 1;
                onChanged();
                return getGvFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGvOrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gv_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getR() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.r_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getRBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getRFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getROrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.r_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public boolean hasGv() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder
            public boolean hasR() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufCalculateKnowledgeProofClass.internal_static_protobuf_ProtobufCalculateKnowledgeProof_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufCalculateKnowledgeProof.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasGv() && hasR() && getGv().isInitialized() && getR().isInitialized();
            }

            public Builder mergeGv(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) == 1 && (protobufBigInteger2 = this.gv_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.gv_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.gv_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.gv_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeR(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (protobufBigInteger2 = this.r_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.r_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.r_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.r_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setGv(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.gv_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setR(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.r_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            private Builder() {
                this.gv_ = null;
                this.r_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufCalculateKnowledgeProof mo10084build() {
                ProtobufCalculateKnowledgeProof mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufCalculateKnowledgeProof mo10085buildPartial() {
                ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = new ProtobufCalculateKnowledgeProof(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    protobufCalculateKnowledgeProof.gv_ = this.gv_;
                } else {
                    protobufCalculateKnowledgeProof.gv_ = singleFieldBuilderV3.build();
                }
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV32 = this.rBuilder_;
                if (singleFieldBuilderV32 == null) {
                    protobufCalculateKnowledgeProof.r_ = this.r_;
                } else {
                    protobufCalculateKnowledgeProof.r_ = singleFieldBuilderV32.build();
                }
                protobufCalculateKnowledgeProof.bitField0_ = i2;
                onBuilt();
                return protobufCalculateKnowledgeProof;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufCalculateKnowledgeProof mo10094getDefaultInstanceForType() {
                return ProtobufCalculateKnowledgeProof.getDefaultInstance();
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
                return (Builder) super.mo10102setUnknownFields(unknownFieldSet);
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
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.gv_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV32 = this.rBuilder_;
                if (singleFieldBuilderV32 == null) {
                    this.r_ = null;
                } else {
                    singleFieldBuilderV32.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.gv_ = null;
                this.r_ = null;
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
                if (message instanceof ProtobufCalculateKnowledgeProof) {
                    return mergeFrom((ProtobufCalculateKnowledgeProof) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setGv(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gvBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.gv_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setR(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.rBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.r_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeFrom(ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                if (protobufCalculateKnowledgeProof == ProtobufCalculateKnowledgeProof.getDefaultInstance()) {
                    return this;
                }
                if (protobufCalculateKnowledgeProof.hasGv()) {
                    mergeGv(protobufCalculateKnowledgeProof.getGv());
                }
                if (protobufCalculateKnowledgeProof.hasR()) {
                    mergeR(protobufCalculateKnowledgeProof.getR());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufCalculateKnowledgeProof).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass$ProtobufCalculateKnowledgeProof> r1 = com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass$ProtobufCalculateKnowledgeProof r3 = (com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass$ProtobufCalculateKnowledgeProof r4 = (com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass$ProtobufCalculateKnowledgeProof$Builder");
            }
        }

        public static Builder newBuilder(ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufCalculateKnowledgeProof);
        }

        public static ProtobufCalculateKnowledgeProof parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufCalculateKnowledgeProof(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufCalculateKnowledgeProof mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufCalculateKnowledgeProof() {
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        private ProtobufCalculateKnowledgeProof(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder = null;
                                if (readTag == 10) {
                                    builder = (this.bitField0_ & 1) == 1 ? this.gv_.mo10081toBuilder() : builder;
                                    this.gv_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.gv_);
                                        this.gv_ = builder.mo10085buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    builder = (this.bitField0_ & 2) == 2 ? this.r_.mo10081toBuilder() : builder;
                                    this.r_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.r_);
                                        this.r_ = builder.mo10085buildPartial();
                                    }
                                    this.bitField0_ |= 2;
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

        public static ProtobufCalculateKnowledgeProof parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufCalculateKnowledgeProof parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufCalculateKnowledgeProof) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufCalculateKnowledgeProofOrBuilder extends MessageOrBuilder {
        ProtobufBigIntegerClass.ProtobufBigInteger getGv();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGvOrBuilder();

        ProtobufBigIntegerClass.ProtobufBigInteger getR();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getROrBuilder();

        boolean hasGv();

        boolean hasR();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nEWhisperCloakProtocolBuffersModel/schema/CalculateKnowledgeProof.proto\u0012\bprotobuf\u001a8WhisperCloakProtocolBuffersModel/schema/BigInteger.proto\"t\n\u001fProtobufCalculateKnowledgeProof\u0012(\n\u0002gv\u0018\u0001 \u0002(\u000b2\u001c.protobuf.ProtobufBigInteger\u0012'\n\u0001r\u0018\u0002 \u0002(\u000b2\u001c.protobuf.ProtobufBigIntegerBH\n com.amazon.whispercloak.protobufB$ProtobufCalculateKnowledgeProofClass"}, new Descriptors.FileDescriptor[]{ProtobufBigIntegerClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufCalculateKnowledgeProofClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufCalculateKnowledgeProof_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufCalculateKnowledgeProof_descriptor, new String[]{"Gv", "R"});
        ProtobufBigIntegerClass.getDescriptor();
    }

    private ProtobufCalculateKnowledgeProofClass() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
