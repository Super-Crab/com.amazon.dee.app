package com.google.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.Value;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public final class ListValue extends GeneratedMessageV3 implements ListValueOrBuilder {
    private static final ListValue DEFAULT_INSTANCE = new ListValue();
    private static final Parser<ListValue> PARSER = new AbstractParser<ListValue>() { // from class: com.google.protobuf.ListValue.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public ListValue mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListValue(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VALUES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Value> values_;

    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListValueOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> valuesBuilder_;
        private List<Value> values_;

        private Builder() {
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.values_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void ensureValuesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.values_ = new ArrayList(this.values_);
                this.bitField0_ |= 1;
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        private RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> getValuesFieldBuilder() {
            if (this.valuesBuilder_ == null) {
                List<Value> list = this.values_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.valuesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.values_ = null;
            }
            return this.valuesBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getValuesFieldBuilder();
            }
        }

        public Builder addAllValues(Iterable<? extends Value> iterable) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.values_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField  reason: collision with other method in class */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        public Builder addValues(int i, Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder addValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, value);
            } else if (value == null) {
                throw new NullPointerException();
            } else {
                ensureValuesIsMutable();
                this.values_.add(i, value);
                onChanged();
            }
            return this;
        }

        public Builder addValues(Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.add(builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder addValues(Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(value);
            } else if (value == null) {
                throw new NullPointerException();
            } else {
                ensureValuesIsMutable();
                this.values_.add(value);
                onChanged();
            }
            return this;
        }

        public Value.Builder addValuesBuilder() {
            return getValuesFieldBuilder().addBuilder(Value.getDefaultInstance());
        }

        public Value.Builder addValuesBuilder(int i) {
            return getValuesFieldBuilder().addBuilder(i, Value.getDefaultInstance());
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public ListValue mo10084build() {
            ListValue mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public ListValue mo10085buildPartial() {
            List<Value> build;
            ListValue listValue = new ListValue(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.values_ = Collections.unmodifiableList(this.values_);
                    this.bitField0_ &= -2;
                }
                build = this.values_;
            } else {
                build = repeatedFieldBuilderV3.build();
            }
            listValue.values_ = build;
            onBuilt();
            return listValue;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField  reason: collision with other method in class */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        public Builder clearValues() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.values_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone  reason: collision with other method in class */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public ListValue mo10094getDefaultInstanceForType() {
            return ListValue.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_ListValue_descriptor;
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public Value getValues(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? this.values_.get(i) : repeatedFieldBuilderV3.getMessage(i);
        }

        public Value.Builder getValuesBuilder(int i) {
            return getValuesFieldBuilder().getBuilder(i);
        }

        public List<Value.Builder> getValuesBuilderList() {
            return getValuesFieldBuilder().getBuilderList();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public int getValuesCount() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? this.values_.size() : repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public List<Value> getValuesList() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.values_) : repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public ValueOrBuilder getValuesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return (ValueOrBuilder) (repeatedFieldBuilderV3 == null ? this.values_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
        }

        @Override // com.google.protobuf.ListValueOrBuilder
        public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.values_);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.google.protobuf.ListValue.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.ListValue.access$400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.protobuf.ListValue r3 = (com.google.protobuf.ListValue) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.protobuf.ListValue r4 = (com.google.protobuf.ListValue) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ListValue.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.ListValue$Builder");
        }

        public Builder mergeFrom(ListValue listValue) {
            if (listValue == ListValue.getDefaultInstance()) {
                return this;
            }
            if (this.valuesBuilder_ == null) {
                if (!listValue.values_.isEmpty()) {
                    if (this.values_.isEmpty()) {
                        this.values_ = listValue.values_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureValuesIsMutable();
                        this.values_.addAll(listValue.values_);
                    }
                    onChanged();
                }
            } else if (!listValue.values_.isEmpty()) {
                if (this.valuesBuilder_.isEmpty()) {
                    this.valuesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.valuesBuilder_ = null;
                    this.values_ = listValue.values_;
                    this.bitField0_ &= -2;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getValuesFieldBuilder();
                    }
                    this.valuesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.valuesBuilder_.addAllMessages(listValue.values_);
                }
            }
            mo10099mergeUnknownFields(listValue.unknownFields);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof ListValue) {
                return mergeFrom((ListValue) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        public Builder removeValues(int i) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField  reason: collision with other method in class */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField  reason: collision with other method in class */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields  reason: collision with other method in class */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public Builder setValues(int i, Value.Builder builder) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureValuesIsMutable();
                this.values_.set(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder setValues(int i, Value value) {
            RepeatedFieldBuilderV3<Value, Value.Builder, ValueOrBuilder> repeatedFieldBuilderV3 = this.valuesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, value);
            } else if (value == null) {
                throw new NullPointerException();
            } else {
                ensureValuesIsMutable();
                this.values_.set(i, value);
                onChanged();
            }
            return this;
        }
    }

    private ListValue() {
        this.memoizedIsInitialized = (byte) -1;
        this.values_ = Collections.emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ListValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    if (!(z2 & true)) {
                                        this.values_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.values_.add(codedInputStream.readMessage(Value.parser(), extensionRegistryLite));
                                } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
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
                    if (z2 & true) {
                        this.values_ = Collections.unmodifiableList(this.values_);
                    }
                    this.unknownFields = newBuilder.mo10084build();
                    makeExtensionsImmutable();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    private ListValue(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static ListValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_ListValue_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static Builder newBuilder(ListValue listValue) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(listValue);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    public static ListValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(InputStream inputStream) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListValue) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static ListValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    public static ListValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<ListValue> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListValue)) {
            return super.equals(obj);
        }
        ListValue listValue = (ListValue) obj;
        return (getValuesList().equals(listValue.getValuesList())) && this.unknownFields.equals(listValue.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public ListValue mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<ListValue> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.values_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.values_.get(i3));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public Value getValues(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public int getValuesCount() {
        return this.values_.size();
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public List<Value> getValuesList() {
        return this.values_;
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public ValueOrBuilder getValuesOrBuilder(int i) {
        return this.values_.get(i);
    }

    @Override // com.google.protobuf.ListValueOrBuilder
    public List<? extends ValueOrBuilder> getValuesOrBuilderList() {
        return this.values_;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        if (getValuesCount() > 0) {
            hashCode = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53) + getValuesList().hashCode();
        }
        int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_ListValue_fieldAccessorTable.ensureFieldAccessorsInitialized(ListValue.class, Builder.class);
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

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    /* renamed from: newBuilderForType */
    public Builder mo10080newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder mo10081toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.values_.size(); i++) {
            codedOutputStream.writeMessage(1, this.values_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
