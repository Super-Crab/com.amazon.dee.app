package com.amazon.whisperjoin.protobuf;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public final class ProtobufWhisperJoinBlePacketClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufWhisperJoinBlePacket_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufWhisperJoinBlePacket extends GeneratedMessageV3 implements ProtobufWhisperJoinBlePacketOrBuilder {
        public static final int ADDITIONALCHUNKS_FIELD_NUMBER = 3;
        public static final int CHUNKINDEX_FIELD_NUMBER = 2;
        public static final int PACKETKEY_FIELD_NUMBER = 1;
        public static final int PAYLOAD_FIELD_NUMBER = 4;
        private static final long serialVersionUID = 0;
        private boolean additionalChunks_;
        private int bitField0_;
        private int chunkIndex_;
        private byte memoizedIsInitialized;
        private int packetKey_;
        private ByteString payload_;
        private static final ProtobufWhisperJoinBlePacket DEFAULT_INSTANCE = new ProtobufWhisperJoinBlePacket();
        @Deprecated
        public static final Parser<ProtobufWhisperJoinBlePacket> PARSER = new AbstractParser<ProtobufWhisperJoinBlePacket>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufWhisperJoinBlePacket mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufWhisperJoinBlePacket(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufWhisperJoinBlePacket getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufWhisperJoinBlePacketClass.internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufWhisperJoinBlePacket parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufWhisperJoinBlePacket> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufWhisperJoinBlePacket)) {
                return super.equals(obj);
            }
            ProtobufWhisperJoinBlePacket protobufWhisperJoinBlePacket = (ProtobufWhisperJoinBlePacket) obj;
            boolean z = hasPacketKey() == protobufWhisperJoinBlePacket.hasPacketKey();
            if (hasPacketKey()) {
                z = z && getPacketKey() == protobufWhisperJoinBlePacket.getPacketKey();
            }
            boolean z2 = z && hasChunkIndex() == protobufWhisperJoinBlePacket.hasChunkIndex();
            if (hasChunkIndex()) {
                z2 = z2 && getChunkIndex() == protobufWhisperJoinBlePacket.getChunkIndex();
            }
            boolean z3 = z2 && hasAdditionalChunks() == protobufWhisperJoinBlePacket.hasAdditionalChunks();
            if (hasAdditionalChunks()) {
                z3 = z3 && getAdditionalChunks() == protobufWhisperJoinBlePacket.getAdditionalChunks();
            }
            boolean z4 = z3 && hasPayload() == protobufWhisperJoinBlePacket.hasPayload();
            if (hasPayload()) {
                z4 = z4 && getPayload().equals(protobufWhisperJoinBlePacket.getPayload());
            }
            return z4 && this.unknownFields.equals(protobufWhisperJoinBlePacket.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public boolean getAdditionalChunks() {
            return this.additionalChunks_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public int getChunkIndex() {
            return this.chunkIndex_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public int getPacketKey() {
            return this.packetKey_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufWhisperJoinBlePacket> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public ByteString getPayload() {
            return this.payload_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeSInt32Size(1, this.packetKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeSInt32Size(2, this.chunkIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBoolSize(3, this.additionalChunks_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBytesSize(4, this.payload_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public boolean hasAdditionalChunks() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public boolean hasChunkIndex() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public boolean hasPacketKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
        public boolean hasPayload() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasPacketKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getPacketKey();
            }
            if (hasChunkIndex()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getChunkIndex();
            }
            if (hasAdditionalChunks()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + Internal.hashBoolean(getAdditionalChunks());
            }
            if (hasPayload()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getPayload().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufWhisperJoinBlePacketClass.internal_static_protobuf_ProtobufWhisperJoinBlePacket_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWhisperJoinBlePacket.class, Builder.class);
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
            if (!hasPacketKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasChunkIndex()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasAdditionalChunks()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasPayload()) {
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
                codedOutputStream.writeSInt32(1, this.packetKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeSInt32(2, this.chunkIndex_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBool(3, this.additionalChunks_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.payload_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufWhisperJoinBlePacketOrBuilder {
            private boolean additionalChunks_;
            private int bitField0_;
            private int chunkIndex_;
            private int packetKey_;
            private ByteString payload_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufWhisperJoinBlePacketClass.internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearAdditionalChunks() {
                this.bitField0_ &= -5;
                this.additionalChunks_ = false;
                onChanged();
                return this;
            }

            public Builder clearChunkIndex() {
                this.bitField0_ &= -3;
                this.chunkIndex_ = 0;
                onChanged();
                return this;
            }

            public Builder clearPacketKey() {
                this.bitField0_ &= -2;
                this.packetKey_ = 0;
                onChanged();
                return this;
            }

            public Builder clearPayload() {
                this.bitField0_ &= -9;
                this.payload_ = ProtobufWhisperJoinBlePacket.getDefaultInstance().getPayload();
                onChanged();
                return this;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public boolean getAdditionalChunks() {
                return this.additionalChunks_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public int getChunkIndex() {
                return this.chunkIndex_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufWhisperJoinBlePacketClass.internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public int getPacketKey() {
                return this.packetKey_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public ByteString getPayload() {
                return this.payload_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public boolean hasAdditionalChunks() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public boolean hasChunkIndex() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public boolean hasPacketKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacketOrBuilder
            public boolean hasPayload() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufWhisperJoinBlePacketClass.internal_static_protobuf_ProtobufWhisperJoinBlePacket_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufWhisperJoinBlePacket.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasPacketKey() && hasChunkIndex() && hasAdditionalChunks() && hasPayload();
            }

            public Builder setAdditionalChunks(boolean z) {
                this.bitField0_ |= 4;
                this.additionalChunks_ = z;
                onChanged();
                return this;
            }

            public Builder setChunkIndex(int i) {
                this.bitField0_ |= 2;
                this.chunkIndex_ = i;
                onChanged();
                return this;
            }

            public Builder setPacketKey(int i) {
                this.bitField0_ |= 1;
                this.packetKey_ = i;
                onChanged();
                return this;
            }

            public Builder setPayload(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.payload_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.payload_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufWhisperJoinBlePacket mo10084build() {
                ProtobufWhisperJoinBlePacket mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufWhisperJoinBlePacket mo10085buildPartial() {
                ProtobufWhisperJoinBlePacket protobufWhisperJoinBlePacket = new ProtobufWhisperJoinBlePacket(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufWhisperJoinBlePacket.packetKey_ = this.packetKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufWhisperJoinBlePacket.chunkIndex_ = this.chunkIndex_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufWhisperJoinBlePacket.additionalChunks_ = this.additionalChunks_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufWhisperJoinBlePacket.payload_ = this.payload_;
                protobufWhisperJoinBlePacket.bitField0_ = i2;
                onBuilt();
                return protobufWhisperJoinBlePacket;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufWhisperJoinBlePacket mo10094getDefaultInstanceForType() {
                return ProtobufWhisperJoinBlePacket.getDefaultInstance();
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
                this.packetKey_ = 0;
                this.bitField0_ &= -2;
                this.chunkIndex_ = 0;
                this.bitField0_ &= -3;
                this.additionalChunks_ = false;
                this.bitField0_ &= -5;
                this.payload_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.payload_ = ByteString.EMPTY;
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
                if (message instanceof ProtobufWhisperJoinBlePacket) {
                    return mergeFrom((ProtobufWhisperJoinBlePacket) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufWhisperJoinBlePacket protobufWhisperJoinBlePacket) {
                if (protobufWhisperJoinBlePacket == ProtobufWhisperJoinBlePacket.getDefaultInstance()) {
                    return this;
                }
                if (protobufWhisperJoinBlePacket.hasPacketKey()) {
                    setPacketKey(protobufWhisperJoinBlePacket.getPacketKey());
                }
                if (protobufWhisperJoinBlePacket.hasChunkIndex()) {
                    setChunkIndex(protobufWhisperJoinBlePacket.getChunkIndex());
                }
                if (protobufWhisperJoinBlePacket.hasAdditionalChunks()) {
                    setAdditionalChunks(protobufWhisperJoinBlePacket.getAdditionalChunks());
                }
                if (protobufWhisperJoinBlePacket.hasPayload()) {
                    setPayload(protobufWhisperJoinBlePacket.getPayload());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufWhisperJoinBlePacket).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass$ProtobufWhisperJoinBlePacket> r1 = com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass$ProtobufWhisperJoinBlePacket r3 = (com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass$ProtobufWhisperJoinBlePacket r4 = (com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass$ProtobufWhisperJoinBlePacket$Builder");
            }
        }

        public static Builder newBuilder(ProtobufWhisperJoinBlePacket protobufWhisperJoinBlePacket) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufWhisperJoinBlePacket);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufWhisperJoinBlePacket(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufWhisperJoinBlePacket parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufWhisperJoinBlePacket mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufWhisperJoinBlePacket() {
            this.memoizedIsInitialized = (byte) -1;
            this.packetKey_ = 0;
            this.chunkIndex_ = 0;
            this.additionalChunks_ = false;
            this.payload_ = ByteString.EMPTY;
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        private ProtobufWhisperJoinBlePacket(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.bitField0_ |= 1;
                                    this.packetKey_ = codedInputStream.readSInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.chunkIndex_ = codedInputStream.readSInt32();
                                } else if (readTag == 24) {
                                    this.bitField0_ |= 4;
                                    this.additionalChunks_ = codedInputStream.readBool();
                                } else if (readTag != 34) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 8;
                                    this.payload_ = codedInputStream.readBytes();
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

        public static ProtobufWhisperJoinBlePacket parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufWhisperJoinBlePacket parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufWhisperJoinBlePacket) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufWhisperJoinBlePacketOrBuilder extends MessageOrBuilder {
        boolean getAdditionalChunks();

        int getChunkIndex();

        int getPacketKey();

        ByteString getPayload();

        boolean hasAdditionalChunks();

        boolean hasChunkIndex();

        boolean hasPacketKey();

        boolean hasPayload();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nEWhisperJoinProtocolBuffersModel/schema/ble/WhisperJoinBlePacket.proto\u0012\bprotobuf\"p\n\u001cProtobufWhisperJoinBlePacket\u0012\u0011\n\tpacketKey\u0018\u0001 \u0002(\u0011\u0012\u0012\n\nchunkIndex\u0018\u0002 \u0002(\u0011\u0012\u0018\n\u0010additionalChunks\u0018\u0003 \u0002(\b\u0012\u000f\n\u0007payload\u0018\u0004 \u0002(\fBD\n\u001fcom.amazon.whisperjoin.protobufB!ProtobufWhisperJoinBlePacketClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufWhisperJoinBlePacketClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufWhisperJoinBlePacket_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufWhisperJoinBlePacket_descriptor, new String[]{"PacketKey", "ChunkIndex", "AdditionalChunks", DatabaseHelper.appInfo_Payload});
    }

    private ProtobufWhisperJoinBlePacketClass() {
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
