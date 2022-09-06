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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public final class ProtobufDataMapClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufDataMap_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufDataMap_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufMapEntry_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufMapEntry_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufDataMap extends GeneratedMessageV3 implements ProtobufDataMapOrBuilder {
        public static final int DATAMAP_FIELD_NUMBER = 1;
        private static final ProtobufDataMap DEFAULT_INSTANCE = new ProtobufDataMap();
        @Deprecated
        public static final Parser<ProtobufDataMap> PARSER = new AbstractParser<ProtobufDataMap>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufDataMap mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufDataMap(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private List<ProtobufMapEntry> dataMap_;
        private byte memoizedIsInitialized;

        public static ProtobufDataMap getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufDataMapClass.internal_static_protobuf_ProtobufDataMap_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufDataMap parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufDataMap parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufDataMap> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufDataMap)) {
                return super.equals(obj);
            }
            ProtobufDataMap protobufDataMap = (ProtobufDataMap) obj;
            return (getDataMapList().equals(protobufDataMap.getDataMapList())) && this.unknownFields.equals(protobufDataMap.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
        public ProtobufMapEntry getDataMap(int i) {
            return this.dataMap_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
        public int getDataMapCount() {
            return this.dataMap_.size();
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
        public List<ProtobufMapEntry> getDataMapList() {
            return this.dataMap_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
        public ProtobufMapEntryOrBuilder getDataMapOrBuilder(int i) {
            return this.dataMap_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
        public List<? extends ProtobufMapEntryOrBuilder> getDataMapOrBuilderList() {
            return this.dataMap_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufDataMap> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.dataMap_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.dataMap_.get(i3));
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
            int hashCode = getDescriptor().hashCode() + 779;
            if (getDataMapCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getDataMapList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufDataMapClass.internal_static_protobuf_ProtobufDataMap_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufDataMap.class, Builder.class);
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
            for (int i = 0; i < getDataMapCount(); i++) {
                if (!getDataMap(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.dataMap_.size(); i++) {
                codedOutputStream.writeMessage(1, this.dataMap_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufDataMapOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> dataMapBuilder_;
            private List<ProtobufMapEntry> dataMap_;

            private void ensureDataMapIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.dataMap_ = new ArrayList(this.dataMap_);
                    this.bitField0_ |= 1;
                }
            }

            private RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> getDataMapFieldBuilder() {
                if (this.dataMapBuilder_ == null) {
                    List<ProtobufMapEntry> list = this.dataMap_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.dataMapBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.dataMap_ = null;
                }
                return this.dataMapBuilder_;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufDataMap_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getDataMapFieldBuilder();
                }
            }

            public Builder addAllDataMap(Iterable<? extends ProtobufMapEntry> iterable) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataMapIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.dataMap_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addDataMap(ProtobufMapEntry protobufMapEntry) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(protobufMapEntry);
                } else if (protobufMapEntry != null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.add(protobufMapEntry);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public ProtobufMapEntry.Builder addDataMapBuilder() {
                return getDataMapFieldBuilder().addBuilder(ProtobufMapEntry.getDefaultInstance());
            }

            public Builder clearDataMap() {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataMap_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
            public ProtobufMapEntry getDataMap(int i) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataMap_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public ProtobufMapEntry.Builder getDataMapBuilder(int i) {
                return getDataMapFieldBuilder().getBuilder(i);
            }

            public List<ProtobufMapEntry.Builder> getDataMapBuilderList() {
                return getDataMapFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
            public int getDataMapCount() {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataMap_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
            public List<ProtobufMapEntry> getDataMapList() {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.dataMap_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
            public ProtobufMapEntryOrBuilder getDataMapOrBuilder(int i) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.dataMap_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMapOrBuilder
            public List<? extends ProtobufMapEntryOrBuilder> getDataMapOrBuilderList() {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.dataMap_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufDataMap_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufDataMap_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufDataMap.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getDataMapCount(); i++) {
                    if (!getDataMap(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder removeDataMap(int i) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setDataMap(int i, ProtobufMapEntry protobufMapEntry) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, protobufMapEntry);
                } else if (protobufMapEntry != null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.set(i, protobufMapEntry);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.dataMap_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufDataMap mo10084build() {
                ProtobufDataMap mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufDataMap mo10085buildPartial() {
                ProtobufDataMap protobufDataMap = new ProtobufDataMap(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.dataMap_ = Collections.unmodifiableList(this.dataMap_);
                        this.bitField0_ &= -2;
                    }
                    protobufDataMap.dataMap_ = this.dataMap_;
                } else {
                    protobufDataMap.dataMap_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return protobufDataMap;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufDataMap mo10094getDefaultInstanceForType() {
                return ProtobufDataMap.getDefaultInstance();
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

            public ProtobufMapEntry.Builder addDataMapBuilder(int i) {
                return getDataMapFieldBuilder().addBuilder(i, ProtobufMapEntry.getDefaultInstance());
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
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.dataMap_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.dataMap_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public Builder addDataMap(int i, ProtobufMapEntry protobufMapEntry) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, protobufMapEntry);
                } else if (protobufMapEntry != null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.add(i, protobufMapEntry);
                    onChanged();
                } else {
                    throw new NullPointerException();
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
                if (message instanceof ProtobufDataMap) {
                    return mergeFrom((ProtobufDataMap) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setDataMap(int i, ProtobufMapEntry.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder mergeFrom(ProtobufDataMap protobufDataMap) {
                if (protobufDataMap == ProtobufDataMap.getDefaultInstance()) {
                    return this;
                }
                if (this.dataMapBuilder_ == null) {
                    if (!protobufDataMap.dataMap_.isEmpty()) {
                        if (this.dataMap_.isEmpty()) {
                            this.dataMap_ = protobufDataMap.dataMap_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureDataMapIsMutable();
                            this.dataMap_.addAll(protobufDataMap.dataMap_);
                        }
                        onChanged();
                    }
                } else if (!protobufDataMap.dataMap_.isEmpty()) {
                    if (!this.dataMapBuilder_.isEmpty()) {
                        this.dataMapBuilder_.addAllMessages(protobufDataMap.dataMap_);
                    } else {
                        this.dataMapBuilder_.dispose();
                        RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = null;
                        this.dataMapBuilder_ = null;
                        this.dataMap_ = protobufDataMap.dataMap_;
                        this.bitField0_ &= -2;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getDataMapFieldBuilder();
                        }
                        this.dataMapBuilder_ = repeatedFieldBuilderV3;
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufDataMap).unknownFields);
                onChanged();
                return this;
            }

            public Builder addDataMap(ProtobufMapEntry.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addDataMap(int i, ProtobufMapEntry.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufMapEntry, ProtobufMapEntry.Builder, ProtobufMapEntryOrBuilder> repeatedFieldBuilderV3 = this.dataMapBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureDataMapIsMutable();
                    this.dataMap_.add(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
                }
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: mergeFrom */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufDataMap> r1 = com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufDataMap r3 = (com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufDataMap r4 = (com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufDataMap.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufDataMap$Builder");
            }
        }

        public static Builder newBuilder(ProtobufDataMap protobufDataMap) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufDataMap);
        }

        public static ProtobufDataMap parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufDataMap(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufDataMap parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufDataMap parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufDataMap mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufDataMap parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufDataMap() {
            this.memoizedIsInitialized = (byte) -1;
            this.dataMap_ = Collections.emptyList();
        }

        public static ProtobufDataMap parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufDataMap parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufDataMap parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ProtobufDataMap(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (!(z2 & true)) {
                                        this.dataMap_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.dataMap_.add(codedInputStream.readMessage(ProtobufMapEntry.PARSER, extensionRegistryLite));
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
                            this.dataMap_ = Collections.unmodifiableList(this.dataMap_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufDataMap parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufDataMap parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufDataMap parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufDataMap) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufDataMapOrBuilder extends MessageOrBuilder {
        ProtobufMapEntry getDataMap(int i);

        int getDataMapCount();

        List<ProtobufMapEntry> getDataMapList();

        ProtobufMapEntryOrBuilder getDataMapOrBuilder(int i);

        List<? extends ProtobufMapEntryOrBuilder> getDataMapOrBuilderList();
    }

    /* loaded from: classes13.dex */
    public static final class ProtobufMapEntry extends GeneratedMessageV3 implements ProtobufMapEntryOrBuilder {
        public static final int BOOLVALUE_FIELD_NUMBER = 5;
        public static final int BYTESVALUE_FIELD_NUMBER = 2;
        public static final int KEY_FIELD_NUMBER = 1;
        public static final int SINT32VALUE_FIELD_NUMBER = 4;
        public static final int STRINGVALUE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private boolean boolValue_;
        private ByteString bytesValue_;
        private volatile Object key_;
        private byte memoizedIsInitialized;
        private int sint32Value_;
        private volatile Object stringValue_;
        private static final ProtobufMapEntry DEFAULT_INSTANCE = new ProtobufMapEntry();
        @Deprecated
        public static final Parser<ProtobufMapEntry> PARSER = new AbstractParser<ProtobufMapEntry>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufMapEntry mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufMapEntry(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufMapEntry getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufDataMapClass.internal_static_protobuf_ProtobufMapEntry_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufMapEntry parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufMapEntry parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufMapEntry> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufMapEntry)) {
                return super.equals(obj);
            }
            ProtobufMapEntry protobufMapEntry = (ProtobufMapEntry) obj;
            boolean z = hasKey() == protobufMapEntry.hasKey();
            if (hasKey()) {
                z = z && getKey().equals(protobufMapEntry.getKey());
            }
            boolean z2 = z && hasBytesValue() == protobufMapEntry.hasBytesValue();
            if (hasBytesValue()) {
                z2 = z2 && getBytesValue().equals(protobufMapEntry.getBytesValue());
            }
            boolean z3 = z2 && hasStringValue() == protobufMapEntry.hasStringValue();
            if (hasStringValue()) {
                z3 = z3 && getStringValue().equals(protobufMapEntry.getStringValue());
            }
            boolean z4 = z3 && hasSint32Value() == protobufMapEntry.hasSint32Value();
            if (hasSint32Value()) {
                z4 = z4 && getSint32Value() == protobufMapEntry.getSint32Value();
            }
            boolean z5 = z4 && hasBoolValue() == protobufMapEntry.hasBoolValue();
            if (hasBoolValue()) {
                z5 = z5 && getBoolValue() == protobufMapEntry.getBoolValue();
            }
            return z5 && this.unknownFields.equals(protobufMapEntry.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean getBoolValue() {
            return this.boolValue_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public ByteString getBytesValue() {
            return this.bytesValue_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public String getKey() {
            Object obj = this.key_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.key_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public ByteString getKeyBytes() {
            Object obj = this.key_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.key_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufMapEntry> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.key_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.bytesValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.stringValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeSInt32Size(4, this.sint32Value_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeBoolSize(5, this.boolValue_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public int getSint32Value() {
            return this.sint32Value_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public String getStringValue() {
            Object obj = this.stringValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.stringValue_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public ByteString getStringValueBytes() {
            Object obj = this.stringValue_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.stringValue_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean hasBoolValue() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean hasBytesValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean hasKey() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean hasSint32Value() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
        public boolean hasStringValue() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasKey()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getKey().hashCode();
            }
            if (hasBytesValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getBytesValue().hashCode();
            }
            if (hasStringValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53) + getStringValue().hashCode();
            }
            if (hasSint32Value()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53) + getSint32Value();
            }
            if (hasBoolValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53) + Internal.hashBoolean(getBoolValue());
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufDataMapClass.internal_static_protobuf_ProtobufMapEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufMapEntry.class, Builder.class);
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
            if (!hasKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.bytesValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.stringValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeSInt32(4, this.sint32Value_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBool(5, this.boolValue_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufMapEntryOrBuilder {
            private int bitField0_;
            private boolean boolValue_;
            private ByteString bytesValue_;
            private Object key_;
            private int sint32Value_;
            private Object stringValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufMapEntry_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearBoolValue() {
                this.bitField0_ &= -17;
                this.boolValue_ = false;
                onChanged();
                return this;
            }

            public Builder clearBytesValue() {
                this.bitField0_ &= -3;
                this.bytesValue_ = ProtobufMapEntry.getDefaultInstance().getBytesValue();
                onChanged();
                return this;
            }

            public Builder clearKey() {
                this.bitField0_ &= -2;
                this.key_ = ProtobufMapEntry.getDefaultInstance().getKey();
                onChanged();
                return this;
            }

            public Builder clearSint32Value() {
                this.bitField0_ &= -9;
                this.sint32Value_ = 0;
                onChanged();
                return this;
            }

            public Builder clearStringValue() {
                this.bitField0_ &= -5;
                this.stringValue_ = ProtobufMapEntry.getDefaultInstance().getStringValue();
                onChanged();
                return this;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean getBoolValue() {
                return this.boolValue_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public ByteString getBytesValue() {
                return this.bytesValue_;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufMapEntry_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public String getKey() {
                Object obj = this.key_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.key_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public ByteString getKeyBytes() {
                Object obj = this.key_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.key_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public int getSint32Value() {
                return this.sint32Value_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public String getStringValue() {
                Object obj = this.stringValue_;
                if (!(obj instanceof String)) {
                    ByteString byteString = (ByteString) obj;
                    String stringUtf8 = byteString.toStringUtf8();
                    if (byteString.isValidUtf8()) {
                        this.stringValue_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public ByteString getStringValueBytes() {
                Object obj = this.stringValue_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.stringValue_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean hasBoolValue() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean hasBytesValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean hasKey() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean hasSint32Value() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntryOrBuilder
            public boolean hasStringValue() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufDataMapClass.internal_static_protobuf_ProtobufMapEntry_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufMapEntry.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasKey();
            }

            public Builder setBoolValue(boolean z) {
                this.bitField0_ |= 16;
                this.boolValue_ = z;
                onChanged();
                return this;
            }

            public Builder setBytesValue(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.bytesValue_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setKey(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.key_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setKeyBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.key_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setSint32Value(int i) {
                this.bitField0_ |= 8;
                this.sint32Value_ = i;
                onChanged();
                return this;
            }

            public Builder setStringValue(String str) {
                if (str != null) {
                    this.bitField0_ |= 4;
                    this.stringValue_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder setStringValueBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.stringValue_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.key_ = "";
                this.bytesValue_ = ByteString.EMPTY;
                this.stringValue_ = "";
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufMapEntry mo10084build() {
                ProtobufMapEntry mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufMapEntry mo10085buildPartial() {
                ProtobufMapEntry protobufMapEntry = new ProtobufMapEntry(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufMapEntry.key_ = this.key_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                protobufMapEntry.bytesValue_ = this.bytesValue_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                protobufMapEntry.stringValue_ = this.stringValue_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                protobufMapEntry.sint32Value_ = this.sint32Value_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                protobufMapEntry.boolValue_ = this.boolValue_;
                protobufMapEntry.bitField0_ = i2;
                onBuilt();
                return protobufMapEntry;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufMapEntry mo10094getDefaultInstanceForType() {
                return ProtobufMapEntry.getDefaultInstance();
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
                this.key_ = "";
                this.bitField0_ &= -2;
                this.bytesValue_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.stringValue_ = "";
                this.bitField0_ &= -5;
                this.sint32Value_ = 0;
                this.bitField0_ &= -9;
                this.boolValue_ = false;
                this.bitField0_ &= -17;
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
                if (message instanceof ProtobufMapEntry) {
                    return mergeFrom((ProtobufMapEntry) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.key_ = "";
                this.bytesValue_ = ByteString.EMPTY;
                this.stringValue_ = "";
                maybeForceBuilderInitialization();
            }

            public Builder mergeFrom(ProtobufMapEntry protobufMapEntry) {
                if (protobufMapEntry == ProtobufMapEntry.getDefaultInstance()) {
                    return this;
                }
                if (protobufMapEntry.hasKey()) {
                    this.bitField0_ |= 1;
                    this.key_ = protobufMapEntry.key_;
                    onChanged();
                }
                if (protobufMapEntry.hasBytesValue()) {
                    setBytesValue(protobufMapEntry.getBytesValue());
                }
                if (protobufMapEntry.hasStringValue()) {
                    this.bitField0_ |= 4;
                    this.stringValue_ = protobufMapEntry.stringValue_;
                    onChanged();
                }
                if (protobufMapEntry.hasSint32Value()) {
                    setSint32Value(protobufMapEntry.getSint32Value());
                }
                if (protobufMapEntry.hasBoolValue()) {
                    setBoolValue(protobufMapEntry.getBoolValue());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufMapEntry).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufMapEntry> r1 = com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufMapEntry r3 = (com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufMapEntry r4 = (com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.ProtobufMapEntry.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufDataMapClass$ProtobufMapEntry$Builder");
            }
        }

        public static Builder newBuilder(ProtobufMapEntry protobufMapEntry) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufMapEntry);
        }

        public static ProtobufMapEntry parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufMapEntry(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufMapEntry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufMapEntry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufMapEntry mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufMapEntry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufMapEntry() {
            this.memoizedIsInitialized = (byte) -1;
            this.key_ = "";
            this.bytesValue_ = ByteString.EMPTY;
            this.stringValue_ = "";
            this.sint32Value_ = 0;
            this.boolValue_ = false;
        }

        public static ProtobufMapEntry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufMapEntry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufMapEntry parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ProtobufMapEntry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufMapEntry parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        private ProtobufMapEntry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag == 10) {
                                        ByteString readBytes = codedInputStream.readBytes();
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.key_ = readBytes;
                                    } else if (readTag == 18) {
                                        this.bitField0_ |= 2;
                                        this.bytesValue_ = codedInputStream.readBytes();
                                    } else if (readTag == 26) {
                                        ByteString readBytes2 = codedInputStream.readBytes();
                                        this.bitField0_ |= 4;
                                        this.stringValue_ = readBytes2;
                                    } else if (readTag == 32) {
                                        this.bitField0_ |= 8;
                                        this.sint32Value_ = codedInputStream.readSInt32();
                                    } else if (readTag != 40) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.bitField0_ |= 16;
                                        this.boolValue_ = codedInputStream.readBool();
                                    }
                                }
                                z = true;
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
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

        public static ProtobufMapEntry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufMapEntry) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufMapEntryOrBuilder extends MessageOrBuilder {
        boolean getBoolValue();

        ByteString getBytesValue();

        String getKey();

        ByteString getKeyBytes();

        int getSint32Value();

        String getStringValue();

        ByteString getStringValueBytes();

        boolean hasBoolValue();

        boolean hasBytesValue();

        boolean hasKey();

        boolean hasSint32Value();

        boolean hasStringValue();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nTWhisperJoinProtocolBuffersModel/schema/provisioning/data/configuration/DataMap.proto\u0012\bprotobuf\"p\n\u0010ProtobufMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012\u0012\n\nbytesValue\u0018\u0002 \u0001(\f\u0012\u0013\n\u000bstringValue\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bsint32Value\u0018\u0004 \u0001(\u0011\u0012\u0011\n\tboolValue\u0018\u0005 \u0001(\b\">\n\u000fProtobufDataMap\u0012+\n\u0007dataMap\u0018\u0001 \u0003(\u000b2\u001a.protobuf.ProtobufMapEntryB7\n\u001fcom.amazon.whisperjoin.protobufB\u0014ProtobufDataMapClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufDataMapClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufDataMapClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufMapEntry_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufMapEntry_descriptor, new String[]{"Key", "BytesValue", "StringValue", "Sint32Value", "BoolValue"});
        internal_static_protobuf_ProtobufDataMap_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_ProtobufDataMap_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufDataMap_descriptor, new String[]{"DataMap"});
    }

    private ProtobufDataMapClass() {
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
