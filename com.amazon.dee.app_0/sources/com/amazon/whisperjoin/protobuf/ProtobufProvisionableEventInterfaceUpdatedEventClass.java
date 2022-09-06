package com.amazon.whisperjoin.protobuf;

import com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventTypeClass;
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
public final class ProtobufProvisionableEventInterfaceUpdatedEventClass {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_fieldAccessorTable;

    /* loaded from: classes13.dex */
    public static final class ProtobufProvisionableEventInterfaceUpdatedEvent extends GeneratedMessageV3 implements ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder {
        public static final int EVENTDATA_FIELD_NUMBER = 2;
        public static final int UUID_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection eventData_;
        private byte memoizedIsInitialized;
        private ByteString uuid_;
        private static final ProtobufProvisionableEventInterfaceUpdatedEvent DEFAULT_INSTANCE = new ProtobufProvisionableEventInterfaceUpdatedEvent();
        @Deprecated
        public static final Parser<ProtobufProvisionableEventInterfaceUpdatedEvent> PARSER = new AbstractParser<ProtobufProvisionableEventInterfaceUpdatedEvent>() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.1
            @Override // com.google.protobuf.Parser
            /* renamed from: parsePartialFrom */
            public ProtobufProvisionableEventInterfaceUpdatedEvent mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProtobufProvisionableEventInterfaceUpdatedEvent(codedInputStream, extensionRegistryLite);
            }
        };

        public static ProtobufProvisionableEventInterfaceUpdatedEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ProtobufProvisionableEventInterfaceUpdatedEventClass.internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.mo10081toBuilder();
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.mo8402parseFrom(byteBuffer);
        }

        public static Parser<ProtobufProvisionableEventInterfaceUpdatedEvent> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ProtobufProvisionableEventInterfaceUpdatedEvent)) {
                return super.equals(obj);
            }
            ProtobufProvisionableEventInterfaceUpdatedEvent protobufProvisionableEventInterfaceUpdatedEvent = (ProtobufProvisionableEventInterfaceUpdatedEvent) obj;
            boolean z = hasUuid() == protobufProvisionableEventInterfaceUpdatedEvent.hasUuid();
            if (hasUuid()) {
                z = z && getUuid().equals(protobufProvisionableEventInterfaceUpdatedEvent.getUuid());
            }
            boolean z2 = z && hasEventData() == protobufProvisionableEventInterfaceUpdatedEvent.hasEventData();
            if (hasEventData()) {
                z2 = z2 && getEventData().equals(protobufProvisionableEventInterfaceUpdatedEvent.getEventData());
            }
            return z2 && this.unknownFields.equals(protobufProvisionableEventInterfaceUpdatedEvent.unknownFields);
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
        public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection getEventData() {
            ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = this.eventData_;
            return protobufProvisionableEventTypeCollection == null ? ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.getDefaultInstance() : protobufProvisionableEventTypeCollection;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
        public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder getEventDataOrBuilder() {
            ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = this.eventData_;
            return protobufProvisionableEventTypeCollection == null ? ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.getDefaultInstance() : protobufProvisionableEventTypeCollection;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: getParserForType */
        public Parser<ProtobufProvisionableEventInterfaceUpdatedEvent> mo9954getParserForType() {
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.uuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, getEventData());
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
        public ByteString getUuid() {
            return this.uuid_;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
        public boolean hasEventData() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
        public boolean hasUuid() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int hashCode = getDescriptor().hashCode() + 779;
            if (hasUuid()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getUuid().hashCode();
            }
            if (hasEventData()) {
                hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53) + getEventData().hashCode();
            }
            int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ProtobufProvisionableEventInterfaceUpdatedEventClass.internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventInterfaceUpdatedEvent.class, Builder.class);
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
            if (!hasUuid()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!hasEventData()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            } else if (!getEventData().isInitialized()) {
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
                codedOutputStream.writeBytes(1, this.uuid_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getEventData());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        /* loaded from: classes13.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder {
            private int bitField0_;
            private SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> eventDataBuilder_;
            private ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection eventData_;
            private ByteString uuid_;

            public static final Descriptors.Descriptor getDescriptor() {
                return ProtobufProvisionableEventInterfaceUpdatedEventClass.internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor;
            }

            private SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> getEventDataFieldBuilder() {
                if (this.eventDataBuilder_ == null) {
                    this.eventDataBuilder_ = new SingleFieldBuilderV3<>(getEventData(), getParentForChildren(), isClean());
                    this.eventData_ = null;
                }
                return this.eventDataBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getEventDataFieldBuilder();
                }
            }

            public Builder clearEventData() {
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.eventData_ = null;
                    onChanged();
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clearUuid() {
                this.bitField0_ &= -2;
                this.uuid_ = ProtobufProvisionableEventInterfaceUpdatedEvent.getDefaultInstance().getUuid();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ProtobufProvisionableEventInterfaceUpdatedEventClass.internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
            public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection getEventData() {
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = this.eventData_;
                    return protobufProvisionableEventTypeCollection == null ? ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.getDefaultInstance() : protobufProvisionableEventTypeCollection;
                }
                return singleFieldBuilderV3.getMessage();
            }

            public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder getEventDataBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getEventDataFieldBuilder().getBuilder();
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
            public ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder getEventDataOrBuilder() {
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection = this.eventData_;
                return protobufProvisionableEventTypeCollection == null ? ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.getDefaultInstance() : protobufProvisionableEventTypeCollection;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
            public ByteString getUuid() {
                return this.uuid_;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
            public boolean hasEventData() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder
            public boolean hasUuid() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ProtobufProvisionableEventInterfaceUpdatedEventClass.internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_fieldAccessorTable.ensureFieldAccessorsInitialized(ProtobufProvisionableEventInterfaceUpdatedEvent.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasUuid() && hasEventData() && getEventData().isInitialized();
            }

            public Builder mergeEventData(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection) {
                ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection2;
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 2) == 2 && (protobufProvisionableEventTypeCollection2 = this.eventData_) != null && protobufProvisionableEventTypeCollection2 != ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.getDefaultInstance()) {
                        this.eventData_ = ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.newBuilder(this.eventData_).mergeFrom(protobufProvisionableEventTypeCollection).mo10085buildPartial();
                    } else {
                        this.eventData_ = protobufProvisionableEventTypeCollection;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(protobufProvisionableEventTypeCollection);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setEventData(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection protobufProvisionableEventTypeCollection) {
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    singleFieldBuilderV3.setMessage(protobufProvisionableEventTypeCollection);
                } else if (protobufProvisionableEventTypeCollection != null) {
                    this.eventData_ = protobufProvisionableEventTypeCollection;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setUuid(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.uuid_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private Builder() {
                this.uuid_ = ByteString.EMPTY;
                this.eventData_ = null;
                maybeForceBuilderInitialization();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: addRepeatedField */
            public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: build */
            public ProtobufProvisionableEventInterfaceUpdatedEvent mo10084build() {
                ProtobufProvisionableEventInterfaceUpdatedEvent mo10085buildPartial = mo10085buildPartial();
                if (mo10085buildPartial.isInitialized()) {
                    return mo10085buildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /* renamed from: buildPartial */
            public ProtobufProvisionableEventInterfaceUpdatedEvent mo10085buildPartial() {
                ProtobufProvisionableEventInterfaceUpdatedEvent protobufProvisionableEventInterfaceUpdatedEvent = new ProtobufProvisionableEventInterfaceUpdatedEvent(this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                protobufProvisionableEventInterfaceUpdatedEvent.uuid_ = this.uuid_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    protobufProvisionableEventInterfaceUpdatedEvent.eventData_ = this.eventData_;
                } else {
                    protobufProvisionableEventInterfaceUpdatedEvent.eventData_ = singleFieldBuilderV3.build();
                }
                protobufProvisionableEventInterfaceUpdatedEvent.bitField0_ = i2;
                onBuilt();
                return protobufProvisionableEventInterfaceUpdatedEvent;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            /* renamed from: clearField */
            public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.mo10088clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            /* renamed from: getDefaultInstanceForType */
            public ProtobufProvisionableEventInterfaceUpdatedEvent mo10094getDefaultInstanceForType() {
                return ProtobufProvisionableEventInterfaceUpdatedEvent.getDefaultInstance();
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
                this.uuid_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.eventData_ = null;
                } else {
                    singleFieldBuilderV3.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.uuid_ = ByteString.EMPTY;
                this.eventData_ = null;
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
                if (message instanceof ProtobufProvisionableEventInterfaceUpdatedEvent) {
                    return mergeFrom((ProtobufProvisionableEventInterfaceUpdatedEvent) message);
                }
                super.mo10096mergeFrom(message);
                return this;
            }

            public Builder setEventData(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder builder) {
                SingleFieldBuilderV3<ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder, ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder> singleFieldBuilderV3 = this.eventDataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.eventData_ = builder.mo10084build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.mo10084build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeFrom(ProtobufProvisionableEventInterfaceUpdatedEvent protobufProvisionableEventInterfaceUpdatedEvent) {
                if (protobufProvisionableEventInterfaceUpdatedEvent == ProtobufProvisionableEventInterfaceUpdatedEvent.getDefaultInstance()) {
                    return this;
                }
                if (protobufProvisionableEventInterfaceUpdatedEvent.hasUuid()) {
                    setUuid(protobufProvisionableEventInterfaceUpdatedEvent.getUuid());
                }
                if (protobufProvisionableEventInterfaceUpdatedEvent.hasEventData()) {
                    mergeEventData(protobufProvisionableEventInterfaceUpdatedEvent.getEventData());
                }
                mo10099mergeUnknownFields(((GeneratedMessageV3) protobufProvisionableEventInterfaceUpdatedEvent).unknownFields);
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
            public com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass$ProtobufProvisionableEventInterfaceUpdatedEvent> r1 = com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass$ProtobufProvisionableEventInterfaceUpdatedEvent r3 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
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
                    com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass$ProtobufProvisionableEventInterfaceUpdatedEvent r4 = (com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent) r4     // Catch: java.lang.Throwable -> Lf
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
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.ProtobufProvisionableEventInterfaceUpdatedEvent.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass$ProtobufProvisionableEventInterfaceUpdatedEvent$Builder");
            }
        }

        public static Builder newBuilder(ProtobufProvisionableEventInterfaceUpdatedEvent protobufProvisionableEventInterfaceUpdatedEvent) {
            return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(protobufProvisionableEventInterfaceUpdatedEvent);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
        }

        private ProtobufProvisionableEventInterfaceUpdatedEvent(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.mo8396parseFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ProtobufProvisionableEventInterfaceUpdatedEvent mo10094getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: toBuilder */
        public Builder mo10081toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        /* renamed from: newBuilderForType */
        public Builder mo10079newBuilderForType() {
            return newBuilder();
        }

        private ProtobufProvisionableEventInterfaceUpdatedEvent() {
            this.memoizedIsInitialized = (byte) -1;
            this.uuid_ = ByteString.EMPTY;
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.mo8404parseFrom(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        /* renamed from: newBuilderForType */
        public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(InputStream inputStream) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        private ProtobufProvisionableEventInterfaceUpdatedEvent(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.bitField0_ |= 1;
                                    this.uuid_ = codedInputStream.readBytes();
                                } else if (readTag != 18) {
                                    if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                    }
                                } else {
                                    ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.Builder mo10081toBuilder = (this.bitField0_ & 2) == 2 ? this.eventData_.mo10081toBuilder() : null;
                                    this.eventData_ = (ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection) codedInputStream.readMessage(ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection.PARSER, extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.eventData_);
                                        this.eventData_ = mo10081toBuilder.mo10085buildPartial();
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

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ProtobufProvisionableEventInterfaceUpdatedEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProtobufProvisionableEventInterfaceUpdatedEvent) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }
    }

    /* loaded from: classes13.dex */
    public interface ProtobufProvisionableEventInterfaceUpdatedEventOrBuilder extends MessageOrBuilder {
        ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollection getEventData();

        ProtobufProvisionableEventTypeClass.ProtobufProvisionableEventTypeCollectionOrBuilder getEventDataOrBuilder();

        ByteString getUuid();

        boolean hasEventData();

        boolean hasUuid();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n_WhisperJoinProtocolBuffersModel/schema/ble/events/ProvisionableEventInterfaceUpdatedEvent.proto\u0012\bprotobuf\u001aNWhisperJoinProtocolBuffersModel/schema/ble/events/ProvisionableEventType.proto\"\u0086\u0001\n/ProtobufProvisionableEventInterfaceUpdatedEvent\u0012\f\n\u0004uuid\u0018\u0001 \u0002(\f\u0012E\n\teventData\u0018\u0002 \u0002(\u000b22.protobuf.ProtobufProvisionableEventTypeCollectionBW\n\u001fcom.amazon.whisperjoin.protobufB4ProtobufProvisionableEventInterfaceUpdatedEventClass"}, new Descriptors.FileDescriptor[]{ProtobufProvisionableEventTypeClass.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.whisperjoin.protobuf.ProtobufProvisionableEventInterfaceUpdatedEventClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = ProtobufProvisionableEventInterfaceUpdatedEventClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_protobuf_ProtobufProvisionableEventInterfaceUpdatedEvent_descriptor, new String[]{"Uuid", "EventData"});
        ProtobufProvisionableEventTypeClass.getDescriptor();
    }

    private ProtobufProvisionableEventInterfaceUpdatedEventClass() {
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
