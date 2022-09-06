package com.amazon.whispercloak.protobuf;

import androidx.exifinterface.media.ExifInterface;
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
public final class ProtobufJPAKERound2PayloadClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufJPAKERound2Payload_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufJPAKERound2Payload extends GeneratedMessageV3 implements ProtobufJPAKERound2PayloadOrBuilder {
        public static final int A_FIELD_NUMBER = 2;
        public static final int KNOWLEDGEPROOFFORX2S_FIELD_NUMBER = 3;
        public static final int PARTICIPANTID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private ProtobufBigIntegerClass.ProtobufBigInteger a_;
        private int bitField0_;
        private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX2S_;
        private byte memoizedIsInitialized;
        private volatile Object participantId_;
        private static final ProtobufJPAKERound2Payload DEFAULT_INSTANCE = new ProtobufJPAKERound2Payload();
        @Deprecated
        public static final Parser<ProtobufJPAKERound2Payload> PARSER = new AbstractParser<ProtobufJPAKERound2Payload>() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufJPAKERound2Payload mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufJPAKERound2Payload(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufJPAKERound2Payload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufJPAKERound2PayloadClass.internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufJPAKERound2Payload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufJPAKERound2Payload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufJPAKERound2Payload> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufJPAKERound2Payload)) {
                return super.equals(obj);
            }
            ProtobufJPAKERound2Payload protobufJPAKERound2Payload = (ProtobufJPAKERound2Payload) obj;
            boolean z = hasParticipantId() == protobufJPAKERound2Payload.hasParticipantId();
            if (hasParticipantId()) {
                z = z && getParticipantId().equals(protobufJPAKERound2Payload.getParticipantId());
            }
            boolean z2 = z && hasA() == protobufJPAKERound2Payload.hasA();
            if (hasA()) {
                z2 = z2 && getA().equals(protobufJPAKERound2Payload.getA());
            }
            boolean z3 = z2 && hasKnowledgeProofForX2S() == protobufJPAKERound2Payload.hasKnowledgeProofForX2S();
            if (hasKnowledgeProofForX2S()) {
                z3 = z3 && getKnowledgeProofForX2S().equals(protobufJPAKERound2Payload.getKnowledgeProofForX2S());
            }
            return z3 && this.unknownFields.equals(protobufJPAKERound2Payload.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getA() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.a_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getAOrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.a_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2S() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2S_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2SOrBuilder() {
            ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2S_;
            return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufJPAKERound2Payload> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
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

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
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
                i2 += CodedOutputStream.computeMessageSize(2, getA());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeMessageSize(3, getKnowledgeProofForX2S());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public boolean hasA() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
        public boolean hasKnowledgeProofForX2S() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
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
            if (hasA()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getA().hashCode();
            }
            if (hasKnowledgeProofForX2S()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getKnowledgeProofForX2S().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufJPAKERound2PayloadClass.internal_static_protobuf_ProtobufJPAKERound2Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound2Payload.class, Builder.class);
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
            } else if (!hasA()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasKnowledgeProofForX2S()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getA().isInitialized()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getKnowledgeProofForX2S().isInitialized()) {
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
                codedOutputStream.writeMessage(2, getA());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, getKnowledgeProofForX2S());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufJPAKERound2PayloadOrBuilder {
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> aBuilder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger a_;
            private int bitField0_;
            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> knowledgeProofForX2SBuilder_;
            private ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof knowledgeProofForX2S_;
            private Object participantId_;

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getAFieldBuilder() {
                if (this.aBuilder_ == null) {
                    this.aBuilder_ = new SingleFieldBuilderV3<>(getA(), getParentForChildren(), isClean());
                    this.a_ = null;
                }
                return this.aBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufJPAKERound2PayloadClass.internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> getKnowledgeProofForX2SFieldBuilder() {
                if (this.knowledgeProofForX2SBuilder_ == null) {
                    this.knowledgeProofForX2SBuilder_ = new SingleFieldBuilderV3<>(getKnowledgeProofForX2S(), getParentForChildren(), isClean());
                    this.knowledgeProofForX2S_ = null;
                }
                return this.knowledgeProofForX2SBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getAFieldBuilder();
                    getKnowledgeProofForX2SFieldBuilder();
                }
            }

            public Builder clearA() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.a_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearKnowledgeProofForX2S() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX2S_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clearParticipantId() {
                this.bitField0_ &= -2;
                this.participantId_ = ProtobufJPAKERound2Payload.getDefaultInstance().getParticipantId();
                onChanged();
                return this;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getA() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.a_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getABuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getAFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getAOrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.a_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufJPAKERound2PayloadClass.internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2S() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2S_;
                    return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder getKnowledgeProofForX2SBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return getKnowledgeProofForX2SFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2SOrBuilder() {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof = this.knowledgeProofForX2S_;
                return protobufCalculateKnowledgeProof == null ? ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance() : protobufCalculateKnowledgeProof;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
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

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public ByteString getParticipantIdBytes() {
                Object obj = this.participantId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.participantId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public boolean hasA() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public boolean hasKnowledgeProofForX2S() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2PayloadOrBuilder
            public boolean hasParticipantId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufJPAKERound2PayloadClass.internal_static_protobuf_ProtobufJPAKERound2Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound2Payload.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasParticipantId() && hasA() && hasKnowledgeProofForX2S() && getA().isInitialized() && getKnowledgeProofForX2S().isInitialized();
            }

            public Builder mergeA(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (protobufBigInteger2 = this.a_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.a_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.a_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.a_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeKnowledgeProofForX2S(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof2;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 4) == 4 && (protobufCalculateKnowledgeProof2 = this.knowledgeProofForX2S_) != null && protobufCalculateKnowledgeProof2 != ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.getDefaultInstance()) {
                        this.knowledgeProofForX2S_ = ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.newBuilder(this.knowledgeProofForX2S_).mergeFrom(protobufCalculateKnowledgeProof).mo10085buildPartial();
                    } else {
                        this.knowledgeProofForX2S_ = protobufCalculateKnowledgeProof;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufCalculateKnowledgeProof);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setA(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.a_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setKnowledgeProofForX2S(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof protobufCalculateKnowledgeProof) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufCalculateKnowledgeProof);
                } else if (protobufCalculateKnowledgeProof != null) {
                    this.knowledgeProofForX2S_ = protobufCalculateKnowledgeProof;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
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
                this.a_ = null;
                this.knowledgeProofForX2S_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufJPAKERound2Payload mo10084build() {
                ProtobufJPAKERound2Payload mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufJPAKERound2Payload mo10085buildPartial() {
                ProtobufJPAKERound2Payload protobufJPAKERound2Payload = new ProtobufJPAKERound2Payload(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufJPAKERound2Payload.participantId_ = this.participantId_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    protobufJPAKERound2Payload.a_ = this.a_;
                } else {
                    protobufJPAKERound2Payload.a_ = singleFieldBuilderV3.build();
                }
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV32 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV32 == null) {
                    protobufJPAKERound2Payload.knowledgeProofForX2S_ = this.knowledgeProofForX2S_;
                } else {
                    protobufJPAKERound2Payload.knowledgeProofForX2S_ = singleFieldBuilderV32.build();
                }
                protobufJPAKERound2Payload.bitField0_ = i2;
                onBuilt();
                return protobufJPAKERound2Payload;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufJPAKERound2Payload mo10094getDefaultInstanceForType() {
                return ProtobufJPAKERound2Payload.getDefaultInstance();
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
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.a_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV32 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV32 == null) {
                    this.knowledgeProofForX2S_ = null;
                } else {
                    singleFieldBuilderV32.clear();
                }
                this.bitField0_ &= -5;
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
                if (message instanceof ProtobufJPAKERound2Payload) {
                    return mergeFrom((ProtobufJPAKERound2Payload) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setA(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.aBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.a_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setKnowledgeProofForX2S(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder builder) {
                SingleFieldBuilderV3<ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.Builder, ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder> singleFieldBuilderV3 = this.knowledgeProofForX2SBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.knowledgeProofForX2S_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.participantId_ = "";
                this.a_ = null;
                this.knowledgeProofForX2S_ = null;
                maybeForceBuilderInitialization();
            }

            public Builder mergeFrom(ProtobufJPAKERound2Payload protobufJPAKERound2Payload) {
                if (protobufJPAKERound2Payload == ProtobufJPAKERound2Payload.getDefaultInstance()) {
                    return this;
                }
                if (protobufJPAKERound2Payload.hasParticipantId()) {
                    this.bitField0_ |= 1;
                    this.participantId_ = protobufJPAKERound2Payload.participantId_;
                    onChanged();
                }
                if (protobufJPAKERound2Payload.hasA()) {
                    mergeA(protobufJPAKERound2Payload.getA());
                }
                if (protobufJPAKERound2Payload.hasKnowledgeProofForX2S()) {
                    mergeKnowledgeProofForX2S(protobufJPAKERound2Payload.getKnowledgeProofForX2S());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufJPAKERound2Payload).unknownFields);
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
            public com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass$ProtobufJPAKERound2Payload> r1 = com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass$ProtobufJPAKERound2Payload r3 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass$ProtobufJPAKERound2Payload r4 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.ProtobufJPAKERound2Payload.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass$ProtobufJPAKERound2Payload$Builder");
            }
        }

        public static Builder newBuilder(ProtobufJPAKERound2Payload protobufJPAKERound2Payload) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufJPAKERound2Payload);
        }

        public static ProtobufJPAKERound2Payload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufJPAKERound2Payload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufJPAKERound2Payload(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufJPAKERound2Payload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufJPAKERound2Payload mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufJPAKERound2Payload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufJPAKERound2Payload() {
            this.memoizedIsInitialized = (byte) -1;
            this.participantId_ = "";
        }

        public static ProtobufJPAKERound2Payload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufJPAKERound2Payload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufJPAKERound2Payload parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufJPAKERound2Payload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 18) {
                                        mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.a_.mo10081toBuilder() : mo10081toBuilder;
                                        this.a_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                        if (mo10081toBuilder != null) {
                                            mo10081toBuilder.mergeFrom(this.a_);
                                            this.a_ = mo10081toBuilder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 2;
                                    } else if (readTag != 26) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        builder = (this.bitField0_ & 4) == 4 ? this.knowledgeProofForX2S_.mo10081toBuilder() : builder;
                                        this.knowledgeProofForX2S_ = (ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof) codedInputStream.readMessage(ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof.PARSER, extensionRegistryLite);
                                        if (builder != null) {
                                            builder.mergeFrom(this.knowledgeProofForX2S_);
                                            this.knowledgeProofForX2S_ = builder.mo10085buildPartial();
                                        }
                                        this.bitField0_ |= 4;
                                    }
                                } else {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.participantId_ = readBytes;
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

        public static ProtobufJPAKERound2Payload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufJPAKERound2Payload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufJPAKERound2Payload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound2Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufJPAKERound2PayloadOrBuilder extends MessageOrBuilder {
        ProtobufBigIntegerClass.ProtobufBigInteger getA();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getAOrBuilder();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProof getKnowledgeProofForX2S();

        ProtobufCalculateKnowledgeProofClass.ProtobufCalculateKnowledgeProofOrBuilder getKnowledgeProofForX2SOrBuilder();

        String getParticipantId();

        ByteString getParticipantIdBytes();

        boolean hasA();

        boolean hasKnowledgeProofForX2S();

        boolean hasParticipantId();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n@WhisperCloakProtocolBuffersModel/schema/JPAKERound2Payload.proto\u0012\bprotobuf\u001a8WhisperCloakProtocolBuffersModel/schema/BigInteger.proto\u001aEWhisperCloakProtocolBuffersModel/schema/CalculateKnowledgeProof.proto\"¥\u0001\n\u001aProtobufJPAKERound2Payload\u0012\u0015\n\rparticipantId\u0018\u0001 \u0002(\t\u0012'\n\u0001a\u0018\u0002 \u0002(\u000b2\u001c.protobuf.ProtobufBigInteger\u0012G\n\u0014knowledgeProofForX2s\u0018\u0003 \u0002(\u000b2).protobuf.ProtobufCalculateKnowledgeProofBC\n com.amazon.whispercloak.protobufB\u001fProtobufJPAKERound2PayloadClass"}, new Descriptors.FileDescriptor[]{ProtobufBigIntegerClass.getDescriptor(), ProtobufCalculateKnowledgeProofClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound2PayloadClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufJPAKERound2PayloadClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufJPAKERound2Payload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufJPAKERound2Payload_descriptor, new String[]{"ParticipantId", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "KnowledgeProofForX2S"});
        ProtobufBigIntegerClass.getDescriptor();
        ProtobufCalculateKnowledgeProofClass.getDescriptor();
    }

    private ProtobufJPAKERound2PayloadClass() {
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
