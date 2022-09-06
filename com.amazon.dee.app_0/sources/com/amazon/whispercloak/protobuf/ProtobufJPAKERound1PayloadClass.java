package com.amazon.whispercloak.protobuf;

import com.amazon.whispercloak.protobuf.ProtobufBigIntegerClass;
import com.amazon.whispercloak.protobuf.ProtobufCalculateKnowledgeProofClass;
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
public final class ProtobufJPAKERound1PayloadClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufJPAKERound1Payload_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufJPAKERound1Payload extends GeneratedMessageV3 implements ProtobufJPAKERound1PayloadOrBuilder {
        public static final int CURVENAME_FIELD_NUMBER = 6;
        public static final int GX1_FIELD_NUMBER = 2;
        public static final int GX2_FIELD_NUMBER = 3;
        public static final int KNOWLEDGEPROOFFORX1_FIELD_NUMBER = 4;
        public static final int KNOWLEDGEPROOFFORX2_FIELD_NUMBER = 5;
        public static final int PARTICIPANTID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private volatile Object curveName_;
        private ProtobufBigIntegerClass.ProtobufBigInteger gx1_;
        private ProtobufBigIntegerClass.ProtobufBigInteger gx2_;
        private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX1_;
        private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX2_;
        private byte memoizedIsInitialized;
        private volatile Object participantId_;
        private static final ProtobufJPAKERound1Payload DEFAULT_INSTANCE = new ProtobufJPAKERound1Payload();
        @Deprecated
        public static final Parser<ProtobufJPAKERound1Payload> PARSER = new AbstractParser<ProtobufJPAKERound1Payload>() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufJPAKERound1Payload mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufJPAKERound1Payload(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufJPAKERound1Payload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufJPAKERound1PayloadClass.internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufJPAKERound1Payload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufJPAKERound1Payload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufJPAKERound1Payload> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufJPAKERound1Payload)) {
                return super.equals(obj);
            }
            ProtobufJPAKERound1Payload protobufJPAKERound1Payload = (ProtobufJPAKERound1Payload) obj;
            boolean z = hasParticipantId() == protobufJPAKERound1Payload.hasParticipantId();
            if (hasParticipantId()) {
                z = z && getParticipantId().equals(protobufJPAKERound1Payload.getParticipantId());
            }
            boolean z2 = z && hasGx1() == protobufJPAKERound1Payload.hasGx1();
            if (hasGx1()) {
                z2 = z2 && getGx1().equals(protobufJPAKERound1Payload.getGx1());
            }
            boolean z3 = z2 && hasGx2() == protobufJPAKERound1Payload.hasGx2();
            if (hasGx2()) {
                z3 = z3 && getGx2().equals(protobufJPAKERound1Payload.getGx2());
            }
            boolean z4 = z3 && hasKnowledgeProofForX1() == protobufJPAKERound1Payload.hasKnowledgeProofForX1();
            if (hasKnowledgeProofForX1()) {
                z4 = z4 && getKnowledgeProofForX1().equals(protobufJPAKERound1Payload.getKnowledgeProofForX1());
            }
            boolean z5 = z4 && hasKnowledgeProofForX2() == protobufJPAKERound1Payload.hasKnowledgeProofForX2();
            if (hasKnowledgeProofForX2()) {
                z5 = z5 && getKnowledgeProofForX2().equals(protobufJPAKERound1Payload.getKnowledgeProofForX2());
            }
            boolean z6 = z5 && hasCurveName() == protobufJPAKERound1Payload.hasCurveName();
            if (hasCurveName()) {
                z6 = z6 && getCurveName().equals(protobufJPAKERound1Payload.getCurveName());
            }
            return z6 && this.unknownFields.equals(protobufJPAKERound1Payload.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public String getCurveName() {
            Object obj = this.curveName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.curveName_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ByteString getCurveNameBytes() {
            Object obj = this.curveName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.curveName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getGx1() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx1_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx1OrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx1_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getGx2() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx2_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx2OrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx2_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX1() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX1_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX1OrBuilder() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX1_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2OrBuilder() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufJPAKERound1Payload> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public String getParticipantId() {
            Object obj = this.participantId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.participantId_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public ByteString getParticipantIdBytes() {
            Object obj = this.participantId_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.participantId_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.participantId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getGx1());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(3, getGx2());
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeMessageSize(4, getKnowledgeProofForX1());
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeMessageSize(5, getKnowledgeProofForX2());
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += GeneratedMessageV3.computeStringSize(6, this.curveName_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasCurveName() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasGx1() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasGx2() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasKnowledgeProofForX1() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasKnowledgeProofForX2() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
        public boolean hasParticipantId() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasParticipantId()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getParticipantId().hashCode();
            }
            if (hasGx1()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getGx1().hashCode();
            }
            if (hasGx2()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getGx2().hashCode();
            }
            if (hasKnowledgeProofForX1()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getKnowledgeProofForX1().hashCode();
            }
            if (hasKnowledgeProofForX2()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + getKnowledgeProofForX2().hashCode();
            }
            if (hasCurveName()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53) + getCurveName().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufJPAKERound1PayloadClass.internal_static_protobuf_ProtobufJPAKERound1Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound1Payload.class, Builder.class);
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
            if (!hasParticipantId()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasGx1()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasGx2()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasKnowledgeProofForX1()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasKnowledgeProofForX2()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getGx1().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getGx2().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getKnowledgeProofForX1().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getKnowledgeProofForX2().isInitialized()) {
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
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.participantId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getGx1());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, getGx2());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(4, getKnowledgeProofForX1());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeMessage(5, getKnowledgeProofForX2());
            }
            if ((this.bitField0_ & 32) == 32) {
                GeneratedMessageV3.writeString(codedOutputStream, 6, this.curveName_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufJPAKERound1PayloadOrBuilder {
            private int bitField0_;
            private Object curveName_;
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> gx1Builder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger gx1_;
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> gx2Builder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger gx2_;
            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> knowledgeProofForX1Builder_;
            private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX1_;
            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> knowledgeProofForX2Builder_;
            private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX2_;
            private Object participantId_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufJPAKERound1PayloadClass.internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getGx1FieldBuilder() {
                if (this.gx1Builder_ == null) {
                    this.gx1Builder_ = new SingleFieldBuilderV3<>(getGx1(), getParentForChildren(), isClean());
                    this.gx1_ = null;
                }
                return this.gx1Builder_;
            }

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getGx2FieldBuilder() {
                if (this.gx2Builder_ == null) {
                    this.gx2Builder_ = new SingleFieldBuilderV3<>(getGx2(), getParentForChildren(), isClean());
                    this.gx2_ = null;
                }
                return this.gx2Builder_;
            }

            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> getKnowledgeProofForX1FieldBuilder() {
                if (this.knowledgeProofForX1Builder_ == null) {
                    this.knowledgeProofForX1Builder_ = new SingleFieldBuilderV3<>(getKnowledgeProofForX1(), getParentForChildren(), isClean());
                    this.knowledgeProofForX1_ = null;
                }
                return this.knowledgeProofForX1Builder_;
            }

            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> getKnowledgeProofForX2FieldBuilder() {
                if (this.knowledgeProofForX2Builder_ == null) {
                    this.knowledgeProofForX2Builder_ = new SingleFieldBuilderV3<>(getKnowledgeProofForX2(), getParentForChildren(), isClean());
                    this.knowledgeProofForX2_ = null;
                }
                return this.knowledgeProofForX2Builder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getGx1FieldBuilder();
                    getGx2FieldBuilder();
                    getKnowledgeProofForX1FieldBuilder();
                    getKnowledgeProofForX2FieldBuilder();
                }
            }

            public Builder clearCurveName() {
                this.bitField0_ &= -33;
                this.curveName_ = ProtobufJPAKERound1Payload.getDefaultInstance().getCurveName();
                onChanged();
                return this;
            }

            public Builder clearGx1() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.gx1_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearGx2() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.gx2_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearKnowledgeProofForX1() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX1_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clearKnowledgeProofForX2() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX2_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clearParticipantId() {
                this.bitField0_ &= -2;
                this.participantId_ = ProtobufJPAKERound1Payload.getDefaultInstance().getParticipantId();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public String getCurveName() {
                Object obj = this.curveName_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.curveName_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ByteString getCurveNameBytes() {
                Object obj = this.curveName_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.curveName_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufJPAKERound1PayloadClass.internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getGx1() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx1_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getGx1Builder() {
                this.bitField0_ |= 2;
                onChanged();
                return getGx1FieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx1OrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx1_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getGx2() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx2_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getGx2Builder() {
                this.bitField0_ |= 4;
                onChanged();
                return getGx2FieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx2OrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.gx2_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX1() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX1_;
                    return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder getKnowledgeProofForX1Builder() {
                this.bitField0_ |= 8;
                onChanged();
                return getKnowledgeProofForX1FieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX1OrBuilder() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX1_;
                return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2_;
                    return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder getKnowledgeProofForX2Builder() {
                this.bitField0_ |= 16;
                onChanged();
                return getKnowledgeProofForX2FieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2OrBuilder() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2_;
                return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public String getParticipantId() {
                Object obj = this.participantId_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.participantId_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public ByteString getParticipantIdBytes() {
                Object obj = this.participantId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.participantId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasCurveName() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasGx1() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasGx2() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasKnowledgeProofForX1() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasKnowledgeProofForX2() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1PayloadOrBuilder
            public boolean hasParticipantId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufJPAKERound1PayloadClass.internal_static_protobuf_ProtobufJPAKERound1Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound1Payload.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasParticipantId() && hasGx1() && hasGx2() && hasKnowledgeProofForX1() && hasKnowledgeProofForX2() && getGx1().isInitialized() && getGx2().isInitialized() && getKnowledgeProofForX1().isInitialized() && getKnowledgeProofForX2().isInitialized();
            }

            public Builder mergeGx1(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (protobufBigInteger2 = this.gx1_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.gx1_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.gx1_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.gx1_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeGx2(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4 && (protobufBigInteger2 = this.gx2_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.gx2_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.gx2_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.gx2_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeKnowledgeProofForX1(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof2;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8 && (protobufCalculateKnowledgeProof2 = this.knowledgeProofForX1_) != null && protobufCalculateKnowledgeProof2 != ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance()) {
                        this.knowledgeProofForX1_ = ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder(this.knowledgeProofForX1_).mergeFrom(protobufCalculateKnowledgeProof).mo10085buildPartial();
                    } else {
                        this.knowledgeProofForX1_ = protobufCalculateKnowledgeProof;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufCalculateKnowledgeProof);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeKnowledgeProofForX2(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof2;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 16) == 16 && (protobufCalculateKnowledgeProof2 = this.knowledgeProofForX2_) != null && protobufCalculateKnowledgeProof2 != ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance()) {
                        this.knowledgeProofForX2_ = ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder(this.knowledgeProofForX2_).mergeFrom(protobufCalculateKnowledgeProof).mo10085buildPartial();
                    } else {
                        this.knowledgeProofForX2_ = protobufCalculateKnowledgeProof;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufCalculateKnowledgeProof);
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setCurveName(String str) {
                if (str != null) {
                    this.bitField0_ |= 32;
                    this.curveName_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setCurveNameBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 32;
                    this.curveName_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setGx1(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.gx1_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setGx2(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.gx2_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setKnowledgeProofForX1(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufCalculateKnowledgeProof);
                } else if (protobufCalculateKnowledgeProof != null) {
                    this.knowledgeProofForX1_ = protobufCalculateKnowledgeProof;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setKnowledgeProofForX2(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufCalculateKnowledgeProof);
                } else if (protobufCalculateKnowledgeProof != null) {
                    this.knowledgeProofForX2_ = protobufCalculateKnowledgeProof;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setParticipantId(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.participantId_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setParticipantIdBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.participantId_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.participantId_ = "";
                this.gx1_ = null;
                this.gx2_ = null;
                this.knowledgeProofForX1_ = null;
                this.knowledgeProofForX2_ = null;
                this.curveName_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufJPAKERound1Payload mo10084build() {
                ProtobufJPAKERound1Payload mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufJPAKERound1Payload mo10085buildPartial() {
                ProtobufJPAKERound1Payload protobufJPAKERound1Payload = new ProtobufJPAKERound1Payload(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufJPAKERound1Payload.participantId_ = this.participantId_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    protobufJPAKERound1Payload.gx1_ = this.gx1_;
                } else {
                    protobufJPAKERound1Payload.gx1_ = singleFieldBuilderV3.build();
                }
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV32 = this.gx2Builder_;
                if (singleFieldBuilderV32 == null) {
                    protobufJPAKERound1Payload.gx2_ = this.gx2_;
                } else {
                    protobufJPAKERound1Payload.gx2_ = singleFieldBuilderV32.build();
                }
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV33 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV33 == null) {
                    protobufJPAKERound1Payload.knowledgeProofForX1_ = this.knowledgeProofForX1_;
                } else {
                    protobufJPAKERound1Payload.knowledgeProofForX1_ = singleFieldBuilderV33.build();
                }
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV34 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV34 == null) {
                    protobufJPAKERound1Payload.knowledgeProofForX2_ = this.knowledgeProofForX2_;
                } else {
                    protobufJPAKERound1Payload.knowledgeProofForX2_ = singleFieldBuilderV34.build();
                }
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                protobufJPAKERound1Payload.curveName_ = this.curveName_;
                protobufJPAKERound1Payload.bitField0_ = i2;
                onBuilt();
                return protobufJPAKERound1Payload;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufJPAKERound1Payload mo10094getDefaultInstanceForType() {
                return ProtobufJPAKERound1Payload.getDefaultInstance();
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
                this.participantId_ = "";
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.gx1_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV32 = this.gx2Builder_;
                if (singleFieldBuilderV32 == null) {
                    this.gx2_ = null;
                } else {
                    singleFieldBuilderV32.clear();
                }
                this.bitField0_ &= -5;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV33 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV33 == null) {
                    this.knowledgeProofForX1_ = null;
                } else {
                    singleFieldBuilderV33.clear();
                }
                this.bitField0_ &= -9;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV34 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV34 == null) {
                    this.knowledgeProofForX2_ = null;
                } else {
                    singleFieldBuilderV34.clear();
                }
                this.bitField0_ &= -17;
                this.curveName_ = "";
                this.bitField0_ &= -33;
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
                if (message instanceof ProtobufJPAKERound1Payload) {
                    return mergeFrom((ProtobufJPAKERound1Payload) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setGx1(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx1Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.gx1_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setGx2(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.gx2Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.gx2_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setKnowledgeProofForX1(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder builder) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX1Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX1_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setKnowledgeProofForX2(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder builder) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2Builder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX2_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder mergeFrom(ProtobufJPAKERound1Payload protobufJPAKERound1Payload) {
                if (protobufJPAKERound1Payload == ProtobufJPAKERound1Payload.getDefaultInstance()) {
                    return this;
                }
                if (protobufJPAKERound1Payload.hasParticipantId()) {
                    this.bitField0_ |= 1;
                    this.participantId_ = protobufJPAKERound1Payload.participantId_;
                    onChanged();
                }
                if (protobufJPAKERound1Payload.hasGx1()) {
                    mergeGx1(protobufJPAKERound1Payload.getGx1());
                }
                if (protobufJPAKERound1Payload.hasGx2()) {
                    mergeGx2(protobufJPAKERound1Payload.getGx2());
                }
                if (protobufJPAKERound1Payload.hasKnowledgeProofForX1()) {
                    mergeKnowledgeProofForX1(protobufJPAKERound1Payload.getKnowledgeProofForX1());
                }
                if (protobufJPAKERound1Payload.hasKnowledgeProofForX2()) {
                    mergeKnowledgeProofForX2(protobufJPAKERound1Payload.getKnowledgeProofForX2());
                }
                if (protobufJPAKERound1Payload.hasCurveName()) {
                    this.bitField0_ |= 32;
                    this.curveName_ = protobufJPAKERound1Payload.curveName_;
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufJPAKERound1Payload).unknownFields);
                onChanged();
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.participantId_ = "";
                this.gx1_ = null;
                this.gx2_ = null;
                this.knowledgeProofForX1_ = null;
                this.knowledgeProofForX2_ = null;
                this.curveName_ = "";
                maybeForceBuilderInitialization();
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass$ProtobufJPAKERound1Payload> r1 = com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass$ProtobufJPAKERound1Payload r3 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass$ProtobufJPAKERound1Payload r4 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.ProtobufJPAKERound1Payload.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass$ProtobufJPAKERound1Payload$Builder");
            }
        }

        public static Builder newBuilder(ProtobufJPAKERound1Payload protobufJPAKERound1Payload) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufJPAKERound1Payload);
        }

        public static ProtobufJPAKERound1Payload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufJPAKERound1Payload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufJPAKERound1Payload(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufJPAKERound1Payload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufJPAKERound1Payload mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufJPAKERound1Payload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufJPAKERound1Payload() {
            this.memoizedIsInitialized = (byte) -1;
            this.participantId_ = "";
            this.curveName_ = "";
        }

        public static ProtobufJPAKERound1Payload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufJPAKERound1Payload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufJPAKERound1Payload parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufJPAKERound1Payload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufJPAKERound1Payload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder builder = null;
                                    ProtobufBigIntegerClass.ProtobufBigInteger.Builder mo10081toBuilder = null;
                                    ProtobufBigIntegerClass.ProtobufBigInteger.Builder mo10081toBuilder2 = null;
                                    ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder mo10081toBuilder3 = null;
                                    if (readTag == 18) {
                                        mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.gx1_.mo10081toBuilder() : mo10081toBuilder;
                                        this.gx1_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom(this.gx1_);
                                            this.gx1_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 2;
                                    } else if (readTag == 26) {
                                        mo10081toBuilder2 = (this.bitField0_ & 4) == 4 ? this.gx2_.mo10081toBuilder() : mo10081toBuilder2;
                                        this.gx2_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                        if (mo10081toBuilder2 != null) {
                                            mo10081toBuilder2.mergeFrom(this.gx2_);
                                            this.gx2_ = mo10081toBuilder2.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 4;
                                    } else if (readTag == 34) {
                                        mo10081toBuilder3 = (this.bitField0_ & 8) == 8 ? this.knowledgeProofForX1_.mo10081toBuilder() : mo10081toBuilder3;
                                        this.knowledgeProofForX1_ = (ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof) codedInputStream.readMessage(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.PARSER, extensionRegistryLite);
                                        if (mo10081toBuilder3 != null) {
                                            mo10081toBuilder3.mergeFrom(this.knowledgeProofForX1_);
                                            this.knowledgeProofForX1_ = mo10081toBuilder3.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 8;
                                    } else if (readTag == 42) {
                                        builder = (this.bitField0_ & 16) == 16 ? this.knowledgeProofForX2_.mo10081toBuilder() : builder;
                                        this.knowledgeProofForX2_ = (ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof) codedInputStream.readMessage(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.PARSER, extensionRegistryLite);
                                        if (builder != null) {
                                            builder.mergeFrom(this.knowledgeProofForX2_);
                                            this.knowledgeProofForX2_ = builder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 16;
                                    } else if (readTag != 50) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ |= 32;
                                        this.curveName_ = readBytes;
                                    }
                                } else {
                                    ByteString readBytes2 = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.participantId_ = readBytes2;
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

        public static ProtobufJPAKERound1Payload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufJPAKERound1Payload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound1Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufJPAKERound1PayloadOrBuilder extends MessageOrBuilder {
        String getCurveName();

        ByteString getCurveNameBytes();

        ProtobufBigIntegerClass.ProtobufBigInteger getGx1();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx1OrBuilder();

        ProtobufBigIntegerClass.ProtobufBigInteger getGx2();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getGx2OrBuilder();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX1();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX1OrBuilder();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2OrBuilder();

        String getParticipantId();

        ByteString getParticipantIdBytes();

        boolean hasCurveName();

        boolean hasGx1();

        boolean hasGx2();

        boolean hasKnowledgeProofForX1();

        boolean hasKnowledgeProofForX2();

        boolean hasParticipantId();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n@WhisperCloakProtocolBuffersModel/schema/JPAKERound1Payload.proto\u0012\bprotobuf\u001a8WhisperCloakProtocolBuffersModel/schema/BigInteger.proto\u001aEWhisperCloakProtocolBuffersModel/schema/CalculateKnowledgeProof.proto\"¬\u0002\n\u001aProtobufJPAKERound1Payload\u0012\u0015\n\rparticipantId\u0018\u0001 \u0002(\t\u0012)\n\u0003gx1\u0018\u0002 \u0002(\u000b2\u001c.protobuf.ProtobufBigInteger\u0012)\n\u0003gx2\u0018\u0003 \u0002(\u000b2\u001c.protobuf.ProtobufBigInteger\u0012F\n\u0013knowledgeProofForX1\u0018\u0004 \u0002(\u000b2).protobuf.ProtobufCalculateKnowledgeProof\u0012F\n\u0013knowledgeProofForX2\u0018\u0005 \u0002(\u000b2).protobuf.ProtobufCalculateKnowledgeProof\u0012\u0011\n\tcurveName\u0018\u0006 \u0001(\tBC\n com.amazon.whispercloak.protobufB\u001fProtobufJPAKERound1PayloadClass"}, new Descriptors.FileDescriptor[]{ProtobufBigIntegerClass.getDescriptor(), ProtobufCalculateKnowledgeProofClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound1PayloadClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufJPAKERound1PayloadClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufJPAKERound1Payload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufJPAKERound1Payload_descriptor, new String[]{"ParticipantId", "Gx1", "Gx2", "KnowledgeProofForX1", "KnowledgeProofForX2", "CurveName"});
        ProtobufBigIntegerClass.getDescriptor();
        ProtobufCalculateKnowledgeProofClass.getDescriptor();
    }

    private ProtobufJPAKERound1PayloadClass() {
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
