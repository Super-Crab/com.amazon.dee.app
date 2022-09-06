package com.amazon.whisperjoin.protobuf;

import com.amazon.identity.auth.map.device.token.MAPCookie;
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
public final class ProtobufValidatableUuidClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufUuidCollection_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufUuidCollection_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufValidatableUuid_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufValidatableUuid_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufUuidCollection extends GeneratedMessageV3 implements ProtobufUuidCollectionOrBuilder {
        private static final ProtobufUuidCollection DEFAULT_INSTANCE = new ProtobufUuidCollection();
        @Deprecated
        public static final Parser<ProtobufUuidCollection> PARSER = new AbstractParser<ProtobufUuidCollection>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufUuidCollection mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufUuidCollection(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PROTOBUFUUIDCOLLECTION_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private List<ProtobufValidatableUuid> protobufUuidCollection_;

        public static ProtobufUuidCollection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufUuidCollection_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufUuidCollection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufUuidCollection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufUuidCollection> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufUuidCollection)) {
                return super.equals(obj);
            }
            ProtobufUuidCollection protobufUuidCollection = (ProtobufUuidCollection) obj;
            return (getProtobufUuidCollectionList().equals(protobufUuidCollection.getProtobufUuidCollectionList())) && this.unknownFields.equals(protobufUuidCollection.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufUuidCollection> mo9954getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
        public ProtobufValidatableUuid getProtobufUuidCollection(int i) {
            return this.protobufUuidCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
        public int getProtobufUuidCollectionCount() {
            return this.protobufUuidCollection_.size();
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
        public List<ProtobufValidatableUuid> getProtobufUuidCollectionList() {
            return this.protobufUuidCollection_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
        public ProtobufValidatableUuidOrBuilder getProtobufUuidCollectionOrBuilder(int i) {
            return this.protobufUuidCollection_.get(i);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
        public List<? extends ProtobufValidatableUuidOrBuilder> getProtobufUuidCollectionOrBuilderList() {
            return this.protobufUuidCollection_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.protobufUuidCollection_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.protobufUuidCollection_.get(i3));
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
            if (getProtobufUuidCollectionCount() > 0) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getProtobufUuidCollectionList().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufUuidCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufUuidCollection.class, Builder.class);
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
            for (int i = 0; i < getProtobufUuidCollectionCount(); i++) {
                if (!getProtobufUuidCollection(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.protobufUuidCollection_.size(); i++) {
                codedOutputStream.writeMessage(1, this.protobufUuidCollection_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufUuidCollectionOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> protobufUuidCollectionBuilder_;
            private List<ProtobufValidatableUuid> protobufUuidCollection_;

            private void ensureProtobufUuidCollectionIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.protobufUuidCollection_ = new ArrayList(this.protobufUuidCollection_);
                    this.bitField0_ |= 1;
                }
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufUuidCollection_descriptor;
            }

            private RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> getProtobufUuidCollectionFieldBuilder() {
                if (this.protobufUuidCollectionBuilder_ == null) {
                    List<ProtobufValidatableUuid> list = this.protobufUuidCollection_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.protobufUuidCollectionBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                    this.protobufUuidCollection_ = null;
                }
                return this.protobufUuidCollectionBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getProtobufUuidCollectionFieldBuilder();
                }
            }

            public Builder addAllProtobufUuidCollection(Iterable<? extends ProtobufValidatableUuid> iterable) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufUuidCollectionIsMutable();
                    AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.protobufUuidCollection_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder addProtobufUuidCollection(ProtobufValidatableUuid protobufValidatableUuid) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(protobufValidatableUuid);
                } else if (protobufValidatableUuid != null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.add(protobufValidatableUuid);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public ProtobufValidatableUuid.Builder addProtobufUuidCollectionBuilder() {
                return getProtobufUuidCollectionFieldBuilder().addBuilder(ProtobufValidatableUuid.getDefaultInstance());
            }

            public Builder clearProtobufUuidCollection() {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufUuidCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufUuidCollection_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
            public ProtobufValidatableUuid getProtobufUuidCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufUuidCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public ProtobufValidatableUuid.Builder getProtobufUuidCollectionBuilder(int i) {
                return getProtobufUuidCollectionFieldBuilder().getBuilder(i);
            }

            public List<ProtobufValidatableUuid.Builder> getProtobufUuidCollectionBuilderList() {
                return getProtobufUuidCollectionFieldBuilder().getBuilderList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
            public int getProtobufUuidCollectionCount() {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufUuidCollection_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
            public List<ProtobufValidatableUuid> getProtobufUuidCollectionList() {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.protobufUuidCollection_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
            public ProtobufValidatableUuidOrBuilder getProtobufUuidCollectionOrBuilder(int i) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.protobufUuidCollection_.get(i);
                }
                return repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollectionOrBuilder
            public List<? extends ProtobufValidatableUuidOrBuilder> getProtobufUuidCollectionOrBuilderList() {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.protobufUuidCollection_);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufUuidCollection_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufUuidCollection.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                for (int i = 0; i < getProtobufUuidCollectionCount(); i++) {
                    if (!getProtobufUuidCollection(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder removeProtobufUuidCollection(int i) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Builder setProtobufUuidCollection(int i, ProtobufValidatableUuid protobufValidatableUuid) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.setMessage(i, protobufValidatableUuid);
                } else if (protobufValidatableUuid != null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.set(i, protobufValidatableUuid);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            private Builder() {
                this.protobufUuidCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufUuidCollection mo10084build() {
                ProtobufUuidCollection mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufUuidCollection mo10085buildPartial() {
                ProtobufUuidCollection protobufUuidCollection = new ProtobufUuidCollection(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) == 1) {
                        this.protobufUuidCollection_ = Collections.unmodifiableList(this.protobufUuidCollection_);
                        this.bitField0_ &= -2;
                    }
                    protobufUuidCollection.protobufUuidCollection_ = this.protobufUuidCollection_;
                } else {
                    protobufUuidCollection.protobufUuidCollection_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return protobufUuidCollection;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufUuidCollection mo10094getDefaultInstanceForType() {
                return ProtobufUuidCollection.getDefaultInstance();
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

            public ProtobufValidatableUuid.Builder addProtobufUuidCollectionBuilder(int i) {
                return getProtobufUuidCollectionFieldBuilder().addBuilder(i, ProtobufValidatableUuid.getDefaultInstance());
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
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.protobufUuidCollection_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.protobufUuidCollection_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public Builder addProtobufUuidCollection(int i, ProtobufValidatableUuid protobufValidatableUuid) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    repeatedFieldBuilderV3.addMessage(i, protobufValidatableUuid);
                } else if (protobufValidatableUuid != null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.add(i, protobufValidatableUuid);
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
                if (message instanceof ProtobufUuidCollection) {
                    return mergeFrom((ProtobufUuidCollection) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setProtobufUuidCollection(int i, ProtobufValidatableUuid.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.set(i, builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
                }
                return this;
            }

            public Builder mergeFrom(ProtobufUuidCollection protobufUuidCollection) {
                if (protobufUuidCollection == ProtobufUuidCollection.getDefaultInstance()) {
                    return this;
                }
                if (this.protobufUuidCollectionBuilder_ == null) {
                    if (!protobufUuidCollection.protobufUuidCollection_.isEmpty()) {
                        if (this.protobufUuidCollection_.isEmpty()) {
                            this.protobufUuidCollection_ = protobufUuidCollection.protobufUuidCollection_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureProtobufUuidCollectionIsMutable();
                            this.protobufUuidCollection_.addAll(protobufUuidCollection.protobufUuidCollection_);
                        }
                        onChanged();
                    }
                } else if (!protobufUuidCollection.protobufUuidCollection_.isEmpty()) {
                    if (!this.protobufUuidCollectionBuilder_.isEmpty()) {
                        this.protobufUuidCollectionBuilder_.addAllMessages(protobufUuidCollection.protobufUuidCollection_);
                    } else {
                        this.protobufUuidCollectionBuilder_.dispose();
                        RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = null;
                        this.protobufUuidCollectionBuilder_ = null;
                        this.protobufUuidCollection_ = protobufUuidCollection.protobufUuidCollection_;
                        this.bitField0_ &= -2;
                        if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                            repeatedFieldBuilderV3 = getProtobufUuidCollectionFieldBuilder();
                        }
                        this.protobufUuidCollectionBuilder_ = repeatedFieldBuilderV3;
                    }
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufUuidCollection).unknownFields);
                onChanged();
                return this;
            }

            public Builder addProtobufUuidCollection(ProtobufValidatableUuid.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.add(builder.mo10084build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.mo10084build());
                }
                return this;
            }

            public Builder addProtobufUuidCollection(int i, ProtobufValidatableUuid.Builder builder) {
                RepeatedFieldBuilderV3<ProtobufValidatableUuid, ProtobufValidatableUuid.Builder, ProtobufValidatableUuidOrBuilder> repeatedFieldBuilderV3 = this.protobufUuidCollectionBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureProtobufUuidCollectionIsMutable();
                    this.protobufUuidCollection_.add(i, builder.mo10084build());
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
            public com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufUuidCollection> r1 = com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufUuidCollection r3 = (com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufUuidCollection r4 = (com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufUuidCollection.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufUuidCollection$Builder");
            }
        }

        public static Builder newBuilder(ProtobufUuidCollection protobufUuidCollection) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufUuidCollection);
        }

        public static ProtobufUuidCollection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufUuidCollection(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufUuidCollection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufUuidCollection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufUuidCollection mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufUuidCollection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufUuidCollection() {
            this.memoizedIsInitialized = (byte) -1;
            this.protobufUuidCollection_ = Collections.emptyList();
        }

        public static ProtobufUuidCollection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufUuidCollection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufUuidCollection parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private ProtobufUuidCollection(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.protobufUuidCollection_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.protobufUuidCollection_.add(codedInputStream.readMessage(ProtobufValidatableUuid.PARSER, extensionRegistryLite));
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
                            this.protobufUuidCollection_ = Collections.unmodifiableList(this.protobufUuidCollection_);
                        }
                        this.unknownFields = newBuilder.mo10084build();
                        makeExtensionsImmutable();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public static ProtobufUuidCollection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufUuidCollection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufUuidCollection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufUuidCollection) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufUuidCollectionOrBuilder extends MessageOrBuilder {
        ProtobufValidatableUuid getProtobufUuidCollection(int i);

        int getProtobufUuidCollectionCount();

        List<ProtobufValidatableUuid> getProtobufUuidCollectionList();

        ProtobufValidatableUuidOrBuilder getProtobufUuidCollectionOrBuilder(int i);

        List<? extends ProtobufValidatableUuidOrBuilder> getProtobufUuidCollectionOrBuilderList();
    }

    /* loaded from: classes13.dex */
    public static final class ProtobufValidatableUuid extends GeneratedMessageV3 implements ProtobufValidatableUuidOrBuilder {
        private static final ProtobufValidatableUuid DEFAULT_INSTANCE = new ProtobufValidatableUuid();
        @Deprecated
        public static final Parser<ProtobufValidatableUuid> PARSER = new AbstractParser<ProtobufValidatableUuid>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufValidatableUuid mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufValidatableUuid(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private ByteString value_;

        public static ProtobufValidatableUuid getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufValidatableUuid_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufValidatableUuid parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufValidatableUuid parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufValidatableUuid> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufValidatableUuid)) {
                return super.equals(obj);
            }
            ProtobufValidatableUuid protobufValidatableUuid = (ProtobufValidatableUuid) obj;
            boolean z = hasValue() == protobufValidatableUuid.hasValue();
            if (hasValue()) {
                z = z && getValue().equals(protobufValidatableUuid.getValue());
            }
            return z && this.unknownFields.equals(protobufValidatableUuid.unknownFields);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufValidatableUuid> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.value_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuidOrBuilder
        public ByteString getValue() {
            return this.value_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuidOrBuilder
        public boolean hasValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasValue()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getValue().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufValidatableUuid_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufValidatableUuid.class, Builder.class);
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
            if (!hasValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufValidatableUuidOrBuilder {
            private int bitField0_;
            private ByteString value_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufValidatableUuid_descriptor;
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            public Builder clearValue() {
                this.bitField0_ &= -2;
                this.value_ = ProtobufValidatableUuid.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufValidatableUuid_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuidOrBuilder
            public ByteString getValue() {
                return this.value_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuidOrBuilder
            public boolean hasValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufValidatableUuidClass.internal_static_protobuf_ProtobufValidatableUuid_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufValidatableUuid.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasValue();
            }

            public Builder setValue(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.value_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.value_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufValidatableUuid mo10084build() {
                ProtobufValidatableUuid mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufValidatableUuid mo10085buildPartial() {
                ProtobufValidatableUuid protobufValidatableUuid = new ProtobufValidatableUuid(this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                protobufValidatableUuid.value_ = this.value_;
                protobufValidatableUuid.bitField0_ = i;
                onBuilt();
                return protobufValidatableUuid;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufValidatableUuid mo10094getDefaultInstanceForType() {
                return ProtobufValidatableUuid.getDefaultInstance();
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
                this.value_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.value_ = ByteString.EMPTY;
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
                if (message instanceof ProtobufValidatableUuid) {
                    return mergeFrom((ProtobufValidatableUuid) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProtobufValidatableUuid protobufValidatableUuid) {
                if (protobufValidatableUuid == ProtobufValidatableUuid.getDefaultInstance()) {
                    return this;
                }
                if (protobufValidatableUuid.hasValue()) {
                    setValue(protobufValidatableUuid.getValue());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufValidatableUuid).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufValidatableUuid> r1 = com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufValidatableUuid r3 = (com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufValidatableUuid r4 = (com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.ProtobufValidatableUuid.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass$ProtobufValidatableUuid$Builder");
            }
        }

        public static Builder newBuilder(ProtobufValidatableUuid protobufValidatableUuid) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufValidatableUuid);
        }

        public static ProtobufValidatableUuid parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufValidatableUuid(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufValidatableUuid parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufValidatableUuid parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufValidatableUuid mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufValidatableUuid parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufValidatableUuid() {
            this.memoizedIsInitialized = (byte) -1;
            this.value_ = ByteString.EMPTY;
        }

        public static ProtobufValidatableUuid parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufValidatableUuid parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufValidatableUuid parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufValidatableUuid(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    if (readTag != 10) {
                                        if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                        }
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.value_ = codedInputStream.readBytes();
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

        public static ProtobufValidatableUuid parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufValidatableUuid parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufValidatableUuid parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufValidatableUuid) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufValidatableUuidOrBuilder extends MessageOrBuilder {
        ByteString getValue();

        boolean hasValue();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nNWhisperJoinProtocolBuffersModel/schema/provisioning/data/ValidatableUuid.proto\u0012\bprotobuf\"(\n\u0017ProtobufValidatableUuid\u0012\r\n\u0005value\u0018\u0001 \u0002(\f\"[\n\u0016ProtobufUuidCollection\u0012A\n\u0016protobufUuidCollection\u0018\u0001 \u0003(\u000b2!.protobuf.ProtobufValidatableUuidB?\n\u001fcom.amazon.whisperjoin.protobufB\u001cProtobufValidatableUuidClass"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufValidatableUuidClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufValidatableUuidClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufValidatableUuid_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufValidatableUuid_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufValidatableUuid_descriptor, new String[]{MAPCookie.KEY_VALUE});
        internal_static_protobuf_ProtobufUuidCollection_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_protobuf_ProtobufUuidCollection_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufUuidCollection_descriptor, new String[]{"ProtobufUuidCollection"});
    }

    private ProtobufValidatableUuidClass() {
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
