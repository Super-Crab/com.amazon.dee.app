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
public final class ProtobufJPAKERound3PayloadClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufJPAKERound3Payload_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufJPAKERound3Payload extends GeneratedMessageV3 implements ProtobufJPAKERound3PayloadOrBuilder {
        public static final int MACTAG_FIELD_NUMBER = 2;
        public static final int PARTICIPANTID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ProtobufBigIntegerClass.ProtobufBigInteger macTag_;
        private byte memoizedIsInitialized;
        private volatile Object participantId_;
        private static final ProtobufJPAKERound3Payload DEFAULT_INSTANCE = new ProtobufJPAKERound3Payload();
        @Deprecated
        public static final Parser<ProtobufJPAKERound3Payload> PARSER = new AbstractParser<ProtobufJPAKERound3Payload>() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufJPAKERound3Payload mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufJPAKERound3Payload(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufJPAKERound3Payload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufJPAKERound3PayloadClass.internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufJPAKERound3Payload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufJPAKERound3Payload parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufJPAKERound3Payload> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufJPAKERound3Payload)) {
                return super.equals(obj);
            }
            ProtobufJPAKERound3Payload protobufJPAKERound3Payload = (ProtobufJPAKERound3Payload) obj;
            boolean z = hasParticipantId() == protobufJPAKERound3Payload.hasParticipantId();
            if (hasParticipantId()) {
                z = z && getParticipantId().equals(protobufJPAKERound3Payload.getParticipantId());
            }
            boolean z2 = z && hasMacTag() == protobufJPAKERound3Payload.hasMacTag();
            if (hasMacTag()) {
                z2 = z2 && getMacTag().equals(protobufJPAKERound3Payload.getMacTag());
            }
            return z2 && this.unknownFields.equals(protobufJPAKERound3Payload.unknownFields);
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigInteger getMacTag() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.macTag_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
        public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getMacTagOrBuilder() {
            ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.macTag_;
            return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufJPAKERound3Payload> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
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

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
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
                i2 += CodedOutputStream.computeMessageSize(2, getMacTag());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
        public boolean hasMacTag() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
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
            if (hasMacTag()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getMacTag().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufJPAKERound3PayloadClass.internal_static_protobuf_ProtobufJPAKERound3Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound3Payload.class, Builder.class);
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
            } else if (!hasMacTag()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getMacTag().isInitialized()) {
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
                codedOutputStream.writeMessage(2, getMacTag());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufJPAKERound3PayloadOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> macTagBuilder_;
            private ProtobufBigIntegerClass.ProtobufBigInteger macTag_;
            private Object participantId_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufJPAKERound3PayloadClass.internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> getMacTagFieldBuilder() {
                if (this.macTagBuilder_ == null) {
                    this.macTagBuilder_ = new SingleFieldBuilderV3<>(getMacTag(), getParentForChildren(), isClean());
                    this.macTag_ = null;
                }
                return this.macTagBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getMacTagFieldBuilder();
                }
            }

            public Builder clearMacTag() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.macTag_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearParticipantId() {
                this.bitField0_ &= -2;
                this.participantId_ = ProtobufJPAKERound3Payload.getDefaultInstance().getParticipantId();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufJPAKERound3PayloadClass.internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigInteger getMacTag() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.macTag_;
                    return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufBigIntegerClass.ProtobufBigInteger.Builder getMacTagBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getMacTagFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
            public ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getMacTagOrBuilder() {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger = this.macTag_;
                return protobufBigInteger == null ? ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance() : protobufBigInteger;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
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

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
            public ByteString getParticipantIdBytes() {
                Object obj = this.participantId_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.participantId_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
            public boolean hasMacTag() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3PayloadOrBuilder
            public boolean hasParticipantId() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufJPAKERound3PayloadClass.internal_static_protobuf_ProtobufJPAKERound3Payload_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufJPAKERound3Payload.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasParticipantId() && hasMacTag() && getMacTag().isInitialized();
            }

            public Builder mergeMacTag(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger2;
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (protobufBigInteger2 = this.macTag_) != null && protobufBigInteger2 != ProtobufBigIntegerClass.ProtobufBigInteger.getDefaultInstance()) {
                        this.macTag_ = ProtobufBigIntegerClass.ProtobufBigInteger.newBuilder(this.macTag_).mergeFrom(protobufBigInteger).mo10085buildPartial();
                    } else {
                        this.macTag_ = protobufBigInteger;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufBigInteger);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setMacTag(ProtobufBigIntegerClass.ProtobufBigInteger protobufBigInteger) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufBigInteger);
                } else if (protobufBigInteger != null) {
                    this.macTag_ = protobufBigInteger;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
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
                this.macTag_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufJPAKERound3Payload mo10084build() {
                ProtobufJPAKERound3Payload mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufJPAKERound3Payload mo10085buildPartial() {
                ProtobufJPAKERound3Payload protobufJPAKERound3Payload = new ProtobufJPAKERound3Payload(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufJPAKERound3Payload.participantId_ = this.participantId_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    protobufJPAKERound3Payload.macTag_ = this.macTag_;
                } else {
                    protobufJPAKERound3Payload.macTag_ = singleFieldBuilderV3.build();
                }
                protobufJPAKERound3Payload.bitField0_ = i2;
                onBuilt();
                return protobufJPAKERound3Payload;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufJPAKERound3Payload mo10094getDefaultInstanceForType() {
                return ProtobufJPAKERound3Payload.getDefaultInstance();
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
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.macTag_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.participantId_ = "";
                this.macTag_ = null;
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
                if (message instanceof ProtobufJPAKERound3Payload) {
                    return mergeFrom((ProtobufJPAKERound3Payload) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setMacTag(ProtobufBigIntegerClass.ProtobufBigInteger.Builder builder) {
                SingleFieldBuilderV3<ProtobufBigIntegerClass.ProtobufBigInteger, ProtobufBigIntegerClass.ProtobufBigInteger.Builder, ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder> singleFieldBuilderV3 = this.macTagBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.macTag_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeFrom(ProtobufJPAKERound3Payload protobufJPAKERound3Payload) {
                if (protobufJPAKERound3Payload == ProtobufJPAKERound3Payload.getDefaultInstance()) {
                    return this;
                }
                if (protobufJPAKERound3Payload.hasParticipantId()) {
                    this.bitField0_ |= 1;
                    this.participantId_ = protobufJPAKERound3Payload.participantId_;
                    onChanged();
                }
                if (protobufJPAKERound3Payload.hasMacTag()) {
                    mergeMacTag(protobufJPAKERound3Payload.getMacTag());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufJPAKERound3Payload).unknownFields);
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
            public com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass$ProtobufJPAKERound3Payload> r1 = com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass$ProtobufJPAKERound3Payload r3 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass$ProtobufJPAKERound3Payload r4 = (com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.ProtobufJPAKERound3Payload.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass$ProtobufJPAKERound3Payload$Builder");
            }
        }

        public static Builder newBuilder(ProtobufJPAKERound3Payload protobufJPAKERound3Payload) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufJPAKERound3Payload);
        }

        public static ProtobufJPAKERound3Payload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufJPAKERound3Payload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufJPAKERound3Payload(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufJPAKERound3Payload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufJPAKERound3Payload mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufJPAKERound3Payload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufJPAKERound3Payload() {
            this.memoizedIsInitialized = (byte) -1;
            this.participantId_ = "";
        }

        public static ProtobufJPAKERound3Payload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufJPAKERound3Payload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufJPAKERound3Payload parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufJPAKERound3Payload(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    ByteString readBytes = codedInputStream.readBytes();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.participantId_ = readBytes;
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    ProtobufBigIntegerClass.ProtobufBigInteger.Builder mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.macTag_.mo10081toBuilder() : null;
                                    this.macTag_ = (ProtobufBigIntegerClass.ProtobufBigInteger) codedInputStream.readMessage(ProtobufBigIntegerClass.ProtobufBigInteger.PARSER, extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.macTag_);
                                        this.macTag_ = mo10081toBuilder.mo10085buildPartial();
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

        public static ProtobufJPAKERound3Payload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufJPAKERound3Payload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufJPAKERound3Payload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufJPAKERound3Payload) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufJPAKERound3PayloadOrBuilder extends MessageOrBuilder {
        ProtobufBigIntegerClass.ProtobufBigInteger getMacTag();

        ProtobufBigIntegerClass.ProtobufBigIntegerOrBuilder getMacTagOrBuilder();

        String getParticipantId();

        ByteString getParticipantIdBytes();

        boolean hasMacTag();

        boolean hasParticipantId();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n@WhisperCloakProtocolBuffersModel/schema/JPAKERound3Payload.proto\u0012\bprotobuf\u001a8WhisperCloakProtocolBuffersModel/schema/BigInteger.proto\"a\n\u001aProtobufJPAKERound3Payload\u0012\u0015\n\rparticipantId\u0018\u0001 \u0002(\t\u0012,\n\u0006macTag\u0018\u0002 \u0002(\u000b2\u001c.protobuf.ProtobufBigIntegerBC\n com.amazon.whispercloak.protobufB\u001fProtobufJPAKERound3PayloadClass"}, new Descriptors.FileDescriptor[]{ProtobufBigIntegerClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whispercloak.protobuf.ProtobufJPAKERound3PayloadClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufJPAKERound3PayloadClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufJPAKERound3Payload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufJPAKERound3Payload_descriptor, new String[]{"ParticipantId", "MacTag"});
        ProtobufBigIntegerClass.getDescriptor();
    }

    private ProtobufJPAKERound3PayloadClass() {
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
