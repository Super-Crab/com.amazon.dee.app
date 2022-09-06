package com.google.protobuf;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes3.dex */
public final class Value extends GeneratedMessageV3 implements ValueOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 4;
    public static final int LIST_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 1;
    public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
    public static final int STRING_VALUE_FIELD_NUMBER = 3;
    public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private int kindCase_;
    private Object kind_;
    private byte memoizedIsInitialized;
    private static final Value DEFAULT_INSTANCE = new Value();
    private static final Parser<Value> PARSER = new AbstractParser<Value>() { // from class: com.google.protobuf.Value.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public Value mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Value(codedInputStream, extensionRegistryLite);
        }
    };

    /* renamed from: com.google.protobuf.Value$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Value$KindCase = new int[KindCase.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.NULL_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.NUMBER_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.STRING_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.BOOL_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.STRUCT_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.LIST_VALUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Value$KindCase[KindCase.KIND_NOT_SET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ValueOrBuilder {
        private int kindCase_;
        private Object kind_;
        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> listValueBuilder_;
        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> structValueBuilder_;

        private Builder() {
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.kindCase_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        private SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> getListValueFieldBuilder() {
            if (this.listValueBuilder_ == null) {
                if (this.kindCase_ != 6) {
                    this.kind_ = ListValue.getDefaultInstance();
                }
                this.listValueBuilder_ = new SingleFieldBuilderV3<>((ListValue) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 6;
            onChanged();
            return this.listValueBuilder_;
        }

        private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getStructValueFieldBuilder() {
            if (this.structValueBuilder_ == null) {
                if (this.kindCase_ != 5) {
                    this.kind_ = Struct.getDefaultInstance();
                }
                this.structValueBuilder_ = new SingleFieldBuilderV3<>((Struct) this.kind_, getParentForChildren(), isClean());
                this.kind_ = null;
            }
            this.kindCase_ = 5;
            onChanged();
            return this.structValueBuilder_;
        }

        private void maybeForceBuilderInitialization() {
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public Value mo10084build() {
            Value mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public Value mo10085buildPartial() {
            Value value = new Value(this);
            if (this.kindCase_ == 1) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 2) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 3) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 4) {
                value.kind_ = this.kind_;
            }
            if (this.kindCase_ == 5) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
                value.kind_ = singleFieldBuilderV3 == null ? this.kind_ : singleFieldBuilderV3.build();
            }
            if (this.kindCase_ == 6) {
                SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV32 = this.listValueBuilder_;
                value.kind_ = singleFieldBuilderV32 == null ? this.kind_ : singleFieldBuilderV32.build();
            }
            value.kindCase_ = this.kindCase_;
            onBuilt();
            return value;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            this.kindCase_ = 0;
            this.kind_ = null;
            return this;
        }

        public Builder clearBoolValue() {
            if (this.kindCase_ == 4) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        public Builder clearKind() {
            this.kindCase_ = 0;
            this.kind_ = null;
            onChanged();
            return this;
        }

        public Builder clearListValue() {
            if (this.listValueBuilder_ != null) {
                if (this.kindCase_ == 6) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.listValueBuilder_.clear();
            } else if (this.kindCase_ == 6) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearNullValue() {
            if (this.kindCase_ == 1) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearNumberValue() {
            if (this.kindCase_ == 2) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof  reason: collision with other method in class */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        public Builder clearStringValue() {
            if (this.kindCase_ == 3) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        public Builder clearStructValue() {
            if (this.structValueBuilder_ != null) {
                if (this.kindCase_ == 5) {
                    this.kindCase_ = 0;
                    this.kind_ = null;
                }
                this.structValueBuilder_.clear();
            } else if (this.kindCase_ == 5) {
                this.kindCase_ = 0;
                this.kind_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone  reason: collision with other method in class */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean getBoolValue() {
            if (this.kindCase_ == 4) {
                return ((Boolean) this.kind_).booleanValue();
            }
            return false;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Value mo10094getDefaultInstanceForType() {
            return Value.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return StructProto.internal_static_google_protobuf_Value_descriptor;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public KindCase getKindCase() {
            return KindCase.forNumber(this.kindCase_);
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValue getListValue() {
            Object message;
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 6) {
                    return ListValue.getDefaultInstance();
                }
                message = this.kind_;
            } else if (this.kindCase_ != 6) {
                return ListValue.getDefaultInstance();
            } else {
                message = singleFieldBuilderV3.getMessage();
            }
            return (ListValue) message;
        }

        public ListValue.Builder getListValueBuilder() {
            return getListValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ListValueOrBuilder getListValueOrBuilder() {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3;
            return (this.kindCase_ != 6 || (singleFieldBuilderV3 = this.listValueBuilder_) == null) ? this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance() : singleFieldBuilderV3.getMessageOrBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public NullValue getNullValue() {
            if (this.kindCase_ == 1) {
                NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
                return valueOf == null ? NullValue.UNRECOGNIZED : valueOf;
            }
            return NullValue.NULL_VALUE;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public int getNullValueValue() {
            if (this.kindCase_ == 1) {
                return ((Integer) this.kind_).intValue();
            }
            return 0;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public double getNumberValue() {
            return this.kindCase_ == 2 ? ((Double) this.kind_).doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public String getStringValue() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.kindCase_ == 3) {
                    this.kind_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public ByteString getStringValueBytes() {
            String str = this.kindCase_ == 3 ? this.kind_ : "";
            if (str instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.kindCase_ == 3) {
                    this.kind_ = copyFromUtf8;
                }
                return copyFromUtf8;
            }
            return (ByteString) str;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public Struct getStructValue() {
            Object message;
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ != 5) {
                    return Struct.getDefaultInstance();
                }
                message = this.kind_;
            } else if (this.kindCase_ != 5) {
                return Struct.getDefaultInstance();
            } else {
                message = singleFieldBuilderV3.getMessage();
            }
            return (Struct) message;
        }

        public Struct.Builder getStructValueBuilder() {
            return getStructValueFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public StructOrBuilder getStructValueOrBuilder() {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
            return (this.kindCase_ != 5 || (singleFieldBuilderV3 = this.structValueBuilder_) == null) ? this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance() : singleFieldBuilderV3.getMessageOrBuilder();
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean hasListValue() {
            return this.kindCase_ == 6;
        }

        @Override // com.google.protobuf.ValueOrBuilder
        public boolean hasStructValue() {
            return this.kindCase_ == 5;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
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
        public com.google.protobuf.Value.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Value.access$500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.protobuf.Value r3 = (com.google.protobuf.Value) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.protobuf.Value r4 = (com.google.protobuf.Value) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Value$Builder");
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof Value) {
                return mergeFrom((Value) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Value value) {
            if (value == Value.getDefaultInstance()) {
                return this;
            }
            int ordinal = value.getKindCase().ordinal();
            if (ordinal == 0) {
                setNullValueValue(value.getNullValueValue());
            } else if (ordinal == 1) {
                setNumberValue(value.getNumberValue());
            } else if (ordinal == 2) {
                this.kindCase_ = 3;
                this.kind_ = value.kind_;
                onChanged();
            } else if (ordinal == 3) {
                setBoolValue(value.getBoolValue());
            } else if (ordinal == 4) {
                mergeStructValue(value.getStructValue());
            } else if (ordinal == 5) {
                mergeListValue(value.getListValue());
            }
            mo10099mergeUnknownFields(value.unknownFields);
            onChanged();
            return this;
        }

        public Builder mergeListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 6 && this.kind_ != ListValue.getDefaultInstance()) {
                    listValue = ListValue.newBuilder((ListValue) this.kind_).mergeFrom(listValue).mo10085buildPartial();
                }
                this.kind_ = listValue;
                onChanged();
            } else {
                if (this.kindCase_ == 6) {
                    singleFieldBuilderV3.mergeFrom(listValue);
                }
                this.listValueBuilder_.setMessage(listValue);
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder mergeStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.kindCase_ == 5 && this.kind_ != Struct.getDefaultInstance()) {
                    struct = Struct.newBuilder((Struct) this.kind_).mergeFrom(struct).mo10085buildPartial();
                }
                this.kind_ = struct;
                onChanged();
            } else {
                if (this.kindCase_ == 5) {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                this.structValueBuilder_.setMessage(struct);
            }
            this.kindCase_ = 5;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields  reason: collision with other method in class */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        public Builder setBoolValue(boolean z) {
            this.kindCase_ = 4;
            this.kind_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        public Builder setListValue(ListValue.Builder builder) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            ListValue mo10084build = builder.mo10084build();
            if (singleFieldBuilderV3 == null) {
                this.kind_ = mo10084build;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(mo10084build);
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder setListValue(ListValue listValue) {
            SingleFieldBuilderV3<ListValue, ListValue.Builder, ListValueOrBuilder> singleFieldBuilderV3 = this.listValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(listValue);
            } else if (listValue == null) {
                throw new NullPointerException();
            } else {
                this.kind_ = listValue;
                onChanged();
            }
            this.kindCase_ = 6;
            return this;
        }

        public Builder setNullValue(NullValue nullValue) {
            if (nullValue != null) {
                this.kindCase_ = 1;
                this.kind_ = Integer.valueOf(nullValue.getNumber());
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setNullValueValue(int i) {
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(i);
            onChanged();
            return this;
        }

        public Builder setNumberValue(double d) {
            this.kindCase_ = 2;
            this.kind_ = Double.valueOf(d);
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder setStringValue(String str) {
            if (str != null) {
                this.kindCase_ = 3;
                this.kind_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setStringValueBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.kindCase_ = 3;
                this.kind_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setStructValue(Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            Struct mo10084build = builder.mo10084build();
            if (singleFieldBuilderV3 == null) {
                this.kind_ = mo10084build;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(mo10084build);
            }
            this.kindCase_ = 5;
            return this;
        }

        public Builder setStructValue(Struct struct) {
            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.structValueBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else if (struct == null) {
                throw new NullPointerException();
            } else {
                this.kind_ = struct;
                onChanged();
            }
            this.kindCase_ = 5;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }
    }

    /* loaded from: classes3.dex */
    public enum KindCase implements Internal.EnumLite {
        NULL_VALUE(1),
        NUMBER_VALUE(2),
        STRING_VALUE(3),
        BOOL_VALUE(4),
        STRUCT_VALUE(5),
        LIST_VALUE(6),
        KIND_NOT_SET(0);
        
        private final int value;

        KindCase(int i) {
            this.value = i;
        }

        public static KindCase forNumber(int i) {
            switch (i) {
                case 0:
                    return KIND_NOT_SET;
                case 1:
                    return NULL_VALUE;
                case 2:
                    return NUMBER_VALUE;
                case 3:
                    return STRING_VALUE;
                case 4:
                    return BOOL_VALUE;
                case 5:
                    return STRUCT_VALUE;
                case 6:
                    return LIST_VALUE;
                default:
                    return null;
            }
        }

        @Deprecated
        public static KindCase valueOf(int i) {
            return forNumber(i);
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public int getNumber() {
            return this.value;
        }
    }

    private Value() {
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Value(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        Object valueOf;
        int i;
        if (extensionRegistryLite != null) {
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                int readEnum = codedInputStream.readEnum();
                                this.kindCase_ = 1;
                                valueOf = Integer.valueOf(readEnum);
                            } else if (readTag == 17) {
                                this.kindCase_ = 2;
                                valueOf = Double.valueOf(codedInputStream.readDouble());
                            } else if (readTag == 26) {
                                valueOf = codedInputStream.readStringRequireUtf8();
                                this.kindCase_ = 3;
                            } else if (readTag != 32) {
                                Struct.Builder builder = null;
                                ListValue.Builder mo10081toBuilder = null;
                                if (readTag == 42) {
                                    i = 5;
                                    builder = this.kindCase_ == 5 ? ((Struct) this.kind_).mo10081toBuilder() : builder;
                                    this.kind_ = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom((Struct) this.kind_);
                                        this.kind_ = builder.mo10085buildPartial();
                                    }
                                } else if (readTag == 50) {
                                    i = 6;
                                    mo10081toBuilder = this.kindCase_ == 6 ? ((ListValue) this.kind_).mo10081toBuilder() : mo10081toBuilder;
                                    this.kind_ = codedInputStream.readMessage(ListValue.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom((ListValue) this.kind_);
                                        this.kind_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                                this.kindCase_ = i;
                            } else {
                                this.kindCase_ = 4;
                                valueOf = Boolean.valueOf(codedInputStream.readBool());
                            }
                            this.kind_ = valueOf;
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

    private Value(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.kindCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StructProto.internal_static_google_protobuf_Value_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static Builder newBuilder(Value value) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(value);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<Value> parser() {
        return PARSER;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0039, code lost:
        if (getListValue().equals(r8.getListValue()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
        if (getStructValue().equals(r8.getStructValue()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (getBoolValue() == r8.getBoolValue()) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006b, code lost:
        if (getStringValue().equals(r8.getStringValue()) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0082, code lost:
        if (java.lang.Double.doubleToLongBits(getNumberValue()) == java.lang.Double.doubleToLongBits(r8.getNumberValue())) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
        if (getNullValueValue() == r8.getNullValueValue()) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0094  */
    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L4
            return r0
        L4:
            boolean r1 = r8 instanceof com.google.protobuf.Value
            if (r1 != 0) goto Ld
            boolean r8 = super.equals(r8)
            return r8
        Ld:
            com.google.protobuf.Value r8 = (com.google.protobuf.Value) r8
            com.google.protobuf.Value$KindCase r1 = r7.getKindCase()
            com.google.protobuf.Value$KindCase r2 = r8.getKindCase()
            boolean r1 = r1.equals(r2)
            r2 = 0
            if (r1 == 0) goto L20
            r1 = r0
            goto L21
        L20:
            r1 = r2
        L21:
            if (r1 != 0) goto L24
            return r2
        L24:
            int r3 = r7.kindCase_
            switch(r3) {
                case 1: goto L85;
                case 2: goto L6e;
                case 3: goto L5d;
                case 4: goto L50;
                case 5: goto L3f;
                case 6: goto L2b;
                default: goto L29;
            }
        L29:
            goto L92
        L2b:
            if (r1 == 0) goto L3d
            com.google.protobuf.ListValue r1 = r7.getListValue()
            com.google.protobuf.ListValue r3 = r8.getListValue()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3d
        L3b:
            r1 = r0
            goto L92
        L3d:
            r1 = r2
            goto L92
        L3f:
            if (r1 == 0) goto L3d
            com.google.protobuf.Struct r1 = r7.getStructValue()
            com.google.protobuf.Struct r3 = r8.getStructValue()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3d
            goto L3b
        L50:
            if (r1 == 0) goto L3d
            boolean r1 = r7.getBoolValue()
            boolean r3 = r8.getBoolValue()
            if (r1 != r3) goto L3d
            goto L3b
        L5d:
            if (r1 == 0) goto L3d
            java.lang.String r1 = r7.getStringValue()
            java.lang.String r3 = r8.getStringValue()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L3d
            goto L3b
        L6e:
            if (r1 == 0) goto L3d
            double r3 = r7.getNumberValue()
            long r3 = java.lang.Double.doubleToLongBits(r3)
            double r5 = r8.getNumberValue()
            long r5 = java.lang.Double.doubleToLongBits(r5)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L3d
            goto L3b
        L85:
            if (r1 == 0) goto L3d
            int r1 = r7.getNullValueValue()
            int r3 = r8.getNullValueValue()
            if (r1 != r3) goto L3d
            goto L3b
        L92:
            if (r1 == 0) goto L9f
            com.google.protobuf.UnknownFieldSet r1 = r7.unknownFields
            com.google.protobuf.UnknownFieldSet r8 = r8.unknownFields
            boolean r8 = r1.equals(r8)
            if (r8 == 0) goto L9f
            goto La0
        L9f:
            r0 = r2
        La0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.equals(java.lang.Object):boolean");
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean getBoolValue() {
        if (this.kindCase_ == 4) {
            return ((Boolean) this.kind_).booleanValue();
        }
        return false;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public Value mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValue getListValue() {
        return this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ListValueOrBuilder getListValueOrBuilder() {
        return this.kindCase_ == 6 ? (ListValue) this.kind_ : ListValue.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.kindCase_ == 1) {
            NullValue valueOf = NullValue.valueOf(((Integer) this.kind_).intValue());
            return valueOf == null ? NullValue.UNRECOGNIZED : valueOf;
        }
        return NullValue.NULL_VALUE;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public int getNullValueValue() {
        if (this.kindCase_ == 1) {
            return ((Integer) this.kind_).intValue();
        }
        return 0;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public double getNumberValue() {
        return this.kindCase_ == 2 ? ((Double) this.kind_).doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<Value> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.kindCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            i2 += CodedOutputStream.computeDoubleSize(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            i2 += GeneratedMessageV3.computeStringSize(3, this.kind_);
        }
        if (this.kindCase_ == 4) {
            i2 += CodedOutputStream.computeBoolSize(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            i2 += CodedOutputStream.computeMessageSize(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            i2 += CodedOutputStream.computeMessageSize(6, (ListValue) this.kind_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public String getStringValue() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.kindCase_ == 3) {
            this.kind_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public ByteString getStringValueBytes() {
        String str = this.kindCase_ == 3 ? this.kind_ : "";
        if (str instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.kindCase_ == 3) {
                this.kind_ = copyFromUtf8;
            }
            return copyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public Struct getStructValue() {
        return this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public StructOrBuilder getStructValueOrBuilder() {
        return this.kindCase_ == 5 ? (Struct) this.kind_ : Struct.getDefaultInstance();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean hasListValue() {
        return this.kindCase_ == 6;
    }

    @Override // com.google.protobuf.ValueOrBuilder
    public boolean hasStructValue() {
        return this.kindCase_ == 5;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int outline4;
        int nullValueValue;
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getDescriptor().hashCode() + 779;
        switch (this.kindCase_) {
            case 1:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 1, 53);
                nullValueValue = getNullValueValue();
                hashCode = outline4 + nullValueValue;
                int hashCode2 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode2;
                return hashCode2;
            case 2:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53);
                nullValueValue = Internal.hashLong(Double.doubleToLongBits(getNumberValue()));
                hashCode = outline4 + nullValueValue;
                int hashCode22 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode22;
                return hashCode22;
            case 3:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53);
                nullValueValue = getStringValue().hashCode();
                hashCode = outline4 + nullValueValue;
                int hashCode222 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode222;
                return hashCode222;
            case 4:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53);
                nullValueValue = Internal.hashBoolean(getBoolValue());
                hashCode = outline4 + nullValueValue;
                int hashCode2222 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode2222;
                return hashCode2222;
            case 5:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53);
                nullValueValue = getStructValue().hashCode();
                hashCode = outline4 + nullValueValue;
                int hashCode22222 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode22222;
                return hashCode22222;
            case 6:
                outline4 = GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53);
                nullValueValue = getListValue().hashCode();
                hashCode = outline4 + nullValueValue;
                int hashCode222222 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode222222;
                return hashCode222222;
            default:
                int hashCode2222222 = this.unknownFields.hashCode() + (hashCode * 29);
                this.memoizedHashCode = hashCode2222222;
                return hashCode2222222;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StructProto.internal_static_google_protobuf_Value_fieldAccessorTable.ensureFieldAccessorsInitialized(Value.class, Builder.class);
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
    /* renamed from: newBuilderForType  reason: collision with other method in class */
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
        if (this.kindCase_ == 1) {
            codedOutputStream.writeEnum(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            codedOutputStream.writeDouble(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.kind_);
        }
        if (this.kindCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            codedOutputStream.writeMessage(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            codedOutputStream.writeMessage(6, (ListValue) this.kind_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
