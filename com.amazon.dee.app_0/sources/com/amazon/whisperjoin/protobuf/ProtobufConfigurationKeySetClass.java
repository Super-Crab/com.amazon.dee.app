package com.amazon.whisperjoin.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes13.dex */
public final class ProtobufConfigurationKeySetClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufConfigurationKeySet_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufConfigurationKeySet_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufConfigurationKeySet extends GeneratedMessageV3 implements ProtobufConfigurationKeySetOrBuilder {
        public static final int KEYSET_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private LazyStringList keySet_;
        private byte memoizedIsInitialized;
        private static final ProtobufConfigurationKeySet DEFAULT_INSTANCE = new ProtobufConfigurationKeySet();
        @Deprecated
        public static final Parser<ProtobufConfigurationKeySet> PARSER = new AbstractParser<ProtobufConfigurationKeySet>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufConfigurationKeySet mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufConfigurationKeySet(codedInputStream, extensionRegistryLite);
            }
        };

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufConfigurationKeySetOrBuilder {
            private int bitField0_;
            private LazyStringList keySet_;

            private void ensureKeySetIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.keySet_ = new LazyStringArrayList(this.keySet_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufConfigurationKeySetClass.internal_static_protobuf_ProtobufConfigurationKeySet_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder addAllKeySet(Iterable<String> iterable) {
                ensureKeySetIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.keySet_);
                onChanged();
                return this;
            }

            public Builder addKeySet(String str) {
                if (str != null) {
                    ensureKeySetIsMutable();
                    this.keySet_.add((LazyStringList) str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addKeySetBytes(ByteString byteString) {
                if (byteString != null) {
                    ensureKeySetIsMutable();
                    this.keySet_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearKeySet() {
                this.keySet_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufConfigurationKeySetClass.internal_static_protobuf_ProtobufConfigurationKeySet_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
            public String getKeySet(int i) {
                return this.keySet_.get(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
            public ByteString getKeySetBytes(int i) {
                return this.keySet_.getByteString(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
            public int getKeySetCount() {
                return this.keySet_.size();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufConfigurationKeySetClass.internal_static_protobuf_ProtobufConfigurationKeySet_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufConfigurationKeySet.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public Builder setKeySet(int i, String str) {
                if (str != null) {
                    ensureKeySetIsMutable();
                    this.keySet_.set(i, (int) str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
            /* renamed from: getKeySetList */
            public ProtocolStringList mo5862getKeySetList() {
                return this.keySet_.getUnmodifiableView();
            }

            private Builder() {
                this.keySet_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufConfigurationKeySet mo10084build() {
                ProtobufConfigurationKeySet mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufConfigurationKeySet mo10085buildPartial() {
                ProtobufConfigurationKeySet protobufConfigurationKeySet = new ProtobufConfigurationKeySet(this);
                if ((this.bitField0_ & 1) == 1) {
                    this.keySet_ = this.keySet_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                protobufConfigurationKeySet.keySet_ = this.keySet_;
                onBuilt();
                return protobufConfigurationKeySet;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufConfigurationKeySet mo10094getDefaultInstanceForType() {
                return ProtobufConfigurationKeySet.getDefaultInstance();
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
                this.keySet_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.keySet_ = LazyStringArrayList.EMPTY;
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
                if (message instanceof ProtobufConfigurationKeySet) {
                    return mergeFrom((ProtobufConfigurationKeySet) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufConfigurationKeySet protobufConfigurationKeySet) {
                if (protobufConfigurationKeySet == ProtobufConfigurationKeySet.getDefaultInstance()) {
                    return this;
                }
                if (!protobufConfigurationKeySet.keySet_.isEmpty()) {
                    if (this.keySet_.isEmpty()) {
                        this.keySet_ = protobufConfigurationKeySet.keySet_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureKeySetIsMutable();
                        this.keySet_.addAll(protobufConfigurationKeySet.keySet_);
                    }
                    onChanged();
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufConfigurationKeySet).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass$ProtobufConfigurationKeySet> r1 = com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass$ProtobufConfigurationKeySet r3 = (com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass$ProtobufConfigurationKeySet r4 = (com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySet.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass$ProtobufConfigurationKeySet$Builder");
            }
        }

        public static ProtobufConfigurationKeySet getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufConfigurationKeySetClass.internal_static_protobuf_ProtobufConfigurationKeySet_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufConfigurationKeySet parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufConfigurationKeySet parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufConfigurationKeySet> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufConfigurationKeySet)) {
                return super.equals(obj);
            }
            ProtobufConfigurationKeySet protobufConfigurationKeySet = (ProtobufConfigurationKeySet) obj;
            return (mo5862getKeySetList().equals(protobufConfigurationKeySet.mo5862getKeySetList())) && this.unknownFields.equals(protobufConfigurationKeySet.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
        public String getKeySet(int i) {
            return this.keySet_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
        public ByteString getKeySetBytes(int i) {
            return this.keySet_.getByteString(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
        public int getKeySetCount() {
            return this.keySet_.size();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufConfigurationKeySet> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.keySet_.size(); i3++) {
                i2 += GeneratedMessageV3.computeStringSizeNoTag(this.keySet_.getRaw(i3));
            }
            int serializedSize = this.unknownFields.getSerializedSize() + (mo5862getKeySetList().size() * 1) + 0 + i2;
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
            int hashCode = getDescriptor().hashCode() + 779;
            if (getKeySetCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + mo5862getKeySetList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufConfigurationKeySetClass.internal_static_protobuf_ProtobufConfigurationKeySet_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufConfigurationKeySet.class, Builder.class);
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
            for (int i = 0; i < this.keySet_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.keySet_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(ProtobufConfigurationKeySet protobufConfigurationKeySet) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufConfigurationKeySet);
        }

        public static ProtobufConfigurationKeySet parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.ProtobufConfigurationKeySetOrBuilder
        /* renamed from: getKeySetList */
        public ProtocolStringList mo5862getKeySetList() {
            return this.keySet_;
        }

        private ProtobufConfigurationKeySet(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufConfigurationKeySet parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufConfigurationKeySet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufConfigurationKeySet mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufConfigurationKeySet parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufConfigurationKeySet() {
            this.memoizedIsInitialized = (byte) -1;
            this.keySet_ = LazyStringArrayList.EMPTY;
        }

        public static ProtobufConfigurationKeySet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufConfigurationKeySet parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufConfigurationKeySet parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufConfigurationKeySet(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (z) {
                        break;
                    }
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag != 10) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    ByteString readBytes = codedInputStream.readBytes();
                                    if (!(z2 & true)) {
                                        this.keySet_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.keySet_.add(readBytes);
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        if (z2 & true) {
                            this.keySet_ = this.keySet_.getUnmodifiableView();
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufConfigurationKeySet parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufConfigurationKeySet parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufConfigurationKeySet parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufConfigurationKeySet) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufConfigurationKeySetOrBuilder extends MessageOrBuilder {
        String getKeySet(int i);

        ByteString getKeySetBytes(int i);

        int getKeySetCount();

        /* renamed from: getKeySetList */
        List<String> mo5862getKeySetList();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n`WhisperJoinProtocolBuffersModel/schema/provisioning/data/configuration/ConfigurationKeySet.proto\u0012\bprotobuf\"-\n\u001bProtobufConfigurationKeySet\u0012\u000e\n\u0006keySet\u0018\u0001 \u0003(\tBC\n\u001fcom.amazon.whisperjoin.protobufB ProtobufConfigurationKeySetClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufConfigurationKeySetClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufConfigurationKeySetClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufConfigurationKeySet_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufConfigurationKeySet_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufConfigurationKeySet_descriptor, new String[]{"KeySet"});
    }

    private ProtobufConfigurationKeySetClass() {
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
