package com.google.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Field;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Option;
import com.google.protobuf.SourceContext;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public final class Type extends GeneratedMessageV3 implements TypeOrBuilder {
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ONEOFS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    public static final int SOURCE_CONTEXT_FIELD_NUMBER = 5;
    public static final int SYNTAX_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private List<Field> fields_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private LazyStringList oneofs_;
    private List<Option> options_;
    private SourceContext sourceContext_;
    private int syntax_;
    private static final Type DEFAULT_INSTANCE = new Type();
    private static final Parser<Type> PARSER = new AbstractParser<Type>() { // from class: com.google.protobuf.Type.1
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public Type mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Type(codedInputStream, extensionRegistryLite);
        }
    };

    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TypeOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> fieldsBuilder_;
        private List<Field> fields_;
        private Object name_;
        private LazyStringList oneofs_;
        private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> optionsBuilder_;
        private List<Option> options_;
        private SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> sourceContextBuilder_;
        private SourceContext sourceContext_;
        private int syntax_;

        private Builder() {
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.fields_ = Collections.emptyList();
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.options_ = Collections.emptyList();
            this.sourceContext_ = null;
            this.syntax_ = 0;
            maybeForceBuilderInitialization();
        }

        private void ensureFieldsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.fields_ = new ArrayList(this.fields_);
                this.bitField0_ |= 2;
            }
        }

        private void ensureOneofsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.oneofs_ = new LazyStringArrayList(this.oneofs_);
                this.bitField0_ |= 4;
            }
        }

        private void ensureOptionsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.options_ = new ArrayList(this.options_);
                this.bitField0_ |= 8;
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        private RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> getFieldsFieldBuilder() {
            if (this.fieldsBuilder_ == null) {
                this.fieldsBuilder_ = new RepeatedFieldBuilderV3<>(this.fields_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.fields_ = null;
            }
            return this.fieldsBuilder_;
        }

        private RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> getOptionsFieldBuilder() {
            if (this.optionsBuilder_ == null) {
                this.optionsBuilder_ = new RepeatedFieldBuilderV3<>(this.options_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                this.options_ = null;
            }
            return this.optionsBuilder_;
        }

        private SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> getSourceContextFieldBuilder() {
            if (this.sourceContextBuilder_ == null) {
                this.sourceContextBuilder_ = new SingleFieldBuilderV3<>(getSourceContext(), getParentForChildren(), isClean());
                this.sourceContext_ = null;
            }
            return this.sourceContextBuilder_;
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                getFieldsFieldBuilder();
                getOptionsFieldBuilder();
            }
        }

        public Builder addAllFields(Iterable<? extends Field> iterable) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fields_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder addAllOneofs(Iterable<String> iterable) {
            ensureOneofsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.oneofs_);
            onChanged();
            return this;
        }

        public Builder addAllOptions(Iterable<? extends Option> iterable) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.options_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder addFields(int i, Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.add(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder addFields(int i, Field field) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.add(i, field);
                onChanged();
            }
            return this;
        }

        public Builder addFields(Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.add(builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder addFields(Field field) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.add(field);
                onChanged();
            }
            return this;
        }

        public Field.Builder addFieldsBuilder() {
            return getFieldsFieldBuilder().addBuilder(Field.getDefaultInstance());
        }

        public Field.Builder addFieldsBuilder(int i) {
            return getFieldsFieldBuilder().addBuilder(i, Field.getDefaultInstance());
        }

        public Builder addOneofs(String str) {
            if (str != null) {
                ensureOneofsIsMutable();
                this.oneofs_.add((LazyStringList) str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addOneofsBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                ensureOneofsIsMutable();
                this.oneofs_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addOptions(int i, Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.add(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder addOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.add(i, option);
                onChanged();
            }
            return this;
        }

        public Builder addOptions(Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.add(builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.mo10084build());
            }
            return this;
        }

        public Builder addOptions(Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.add(option);
                onChanged();
            }
            return this;
        }

        public Option.Builder addOptionsBuilder() {
            return getOptionsFieldBuilder().addBuilder(Option.getDefaultInstance());
        }

        public Option.Builder addOptionsBuilder(int i) {
            return getOptionsFieldBuilder().addBuilder(i, Option.getDefaultInstance());
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10083addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public Type mo10084build() {
            Type mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public Type mo10085buildPartial() {
            List<Field> build;
            List<Option> build2;
            Type type = new Type(this);
            type.name_ = this.name_;
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.fields_ = Collections.unmodifiableList(this.fields_);
                    this.bitField0_ &= -3;
                }
                build = this.fields_;
            } else {
                build = repeatedFieldBuilderV3.build();
            }
            type.fields_ = build;
            if ((this.bitField0_ & 4) == 4) {
                this.oneofs_ = this.oneofs_.getUnmodifiableView();
                this.bitField0_ &= -5;
            }
            type.oneofs_ = this.oneofs_;
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.options_ = Collections.unmodifiableList(this.options_);
                    this.bitField0_ &= -9;
                }
                build2 = this.options_;
            } else {
                build2 = repeatedFieldBuilderV32.build();
            }
            type.options_ = build2;
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            type.sourceContext_ = singleFieldBuilderV3 == null ? this.sourceContext_ : singleFieldBuilderV3.build();
            type.syntax_ = this.syntax_;
            type.bitField0_ = 0;
            onBuilt();
            return type;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public Builder mo10087clear() {
            super.mo10087clear();
            this.name_ = "";
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV32 = this.optionsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            this.sourceContext_ = null;
            if (singleFieldBuilderV3 != null) {
                this.sourceContextBuilder_ = null;
            }
            this.syntax_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.mo10088clearField(fieldDescriptor);
        }

        public Builder clearFields() {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fields_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder clearName() {
            this.name_ = Type.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clearOneof  reason: collision with other method in class */
        public Builder mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.mo10090clearOneof(oneofDescriptor);
        }

        public Builder clearOneofs() {
            this.oneofs_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        public Builder clearOptions() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.options_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder clearSourceContext() {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            this.sourceContext_ = null;
            if (singleFieldBuilderV3 == null) {
                onChanged();
            } else {
                this.sourceContextBuilder_ = null;
            }
            return this;
        }

        public Builder clearSyntax() {
            this.syntax_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone  reason: collision with other method in class */
        public Builder mo10093clone() {
            return (Builder) super.mo10093clone();
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public Type mo10094getDefaultInstanceForType() {
            return Type.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TypeProto.internal_static_google_protobuf_Type_descriptor;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Field getFields(int i) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.fields_.get(i) : repeatedFieldBuilderV3.getMessage(i);
        }

        public Field.Builder getFieldsBuilder(int i) {
            return getFieldsFieldBuilder().getBuilder(i);
        }

        public List<Field.Builder> getFieldsBuilderList() {
            return getFieldsFieldBuilder().getBuilderList();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getFieldsCount() {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.fields_.size() : repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<Field> getFieldsList() {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.fields_) : repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public FieldOrBuilder getFieldsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            return (FieldOrBuilder) (repeatedFieldBuilderV3 == null ? this.fields_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.fields_);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public String getOneofs(int i) {
            return this.oneofs_.get(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public ByteString getOneofsBytes(int i) {
            return this.oneofs_.getByteString(i);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getOneofsCount() {
            return this.oneofs_.size();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        /* renamed from: getOneofsList */
        public ProtocolStringList mo9894getOneofsList() {
            return this.oneofs_.getUnmodifiableView();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Option getOptions(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.options_.get(i) : repeatedFieldBuilderV3.getMessage(i);
        }

        public Option.Builder getOptionsBuilder(int i) {
            return getOptionsFieldBuilder().getBuilder(i);
        }

        public List<Option.Builder> getOptionsBuilderList() {
            return getOptionsFieldBuilder().getBuilderList();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getOptionsCount() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? this.options_.size() : repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<Option> getOptionsList() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 == null ? Collections.unmodifiableList(this.options_) : repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public OptionOrBuilder getOptionsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return (OptionOrBuilder) (repeatedFieldBuilderV3 == null ? this.options_.get(i) : repeatedFieldBuilderV3.getMessageOrBuilder(i));
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            return repeatedFieldBuilderV3 != null ? repeatedFieldBuilderV3.getMessageOrBuilderList() : Collections.unmodifiableList(this.options_);
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public SourceContext getSourceContext() {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceContext sourceContext = this.sourceContext_;
                return sourceContext == null ? SourceContext.getDefaultInstance() : sourceContext;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public SourceContext.Builder getSourceContextBuilder() {
            onChanged();
            return getSourceContextFieldBuilder().getBuilder();
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public SourceContextOrBuilder getSourceContextOrBuilder() {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            SourceContext sourceContext = this.sourceContext_;
            return sourceContext == null ? SourceContext.getDefaultInstance() : sourceContext;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public Syntax getSyntax() {
            Syntax valueOf = Syntax.valueOf(this.syntax_);
            return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public int getSyntaxValue() {
            return this.syntax_;
        }

        @Override // com.google.protobuf.TypeOrBuilder
        public boolean hasSourceContext() {
            return (this.sourceContextBuilder_ == null && this.sourceContext_ == null) ? false : true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
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
        public com.google.protobuf.Type.Builder mo10097mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.protobuf.Type.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.mo10082parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.protobuf.Type r3 = (com.google.protobuf.Type) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.protobuf.Type r4 = (com.google.protobuf.Type) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Type.Builder.mo10097mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.Type$Builder");
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public Builder mo10096mergeFrom(Message message) {
            if (message instanceof Type) {
                return mergeFrom((Type) message);
            }
            super.mo10096mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Type type) {
            if (type == Type.getDefaultInstance()) {
                return this;
            }
            if (!type.getName().isEmpty()) {
                this.name_ = type.name_;
                onChanged();
            }
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.fieldsBuilder_ == null) {
                if (!type.fields_.isEmpty()) {
                    if (this.fields_.isEmpty()) {
                        this.fields_ = type.fields_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureFieldsIsMutable();
                        this.fields_.addAll(type.fields_);
                    }
                    onChanged();
                }
            } else if (!type.fields_.isEmpty()) {
                if (this.fieldsBuilder_.isEmpty()) {
                    this.fieldsBuilder_.dispose();
                    this.fieldsBuilder_ = null;
                    this.fields_ = type.fields_;
                    this.bitField0_ &= -3;
                    this.fieldsBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getFieldsFieldBuilder() : null;
                } else {
                    this.fieldsBuilder_.addAllMessages(type.fields_);
                }
            }
            if (!type.oneofs_.isEmpty()) {
                if (this.oneofs_.isEmpty()) {
                    this.oneofs_ = type.oneofs_;
                    this.bitField0_ &= -5;
                } else {
                    ensureOneofsIsMutable();
                    this.oneofs_.addAll(type.oneofs_);
                }
                onChanged();
            }
            if (this.optionsBuilder_ == null) {
                if (!type.options_.isEmpty()) {
                    if (this.options_.isEmpty()) {
                        this.options_ = type.options_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureOptionsIsMutable();
                        this.options_.addAll(type.options_);
                    }
                    onChanged();
                }
            } else if (!type.options_.isEmpty()) {
                if (this.optionsBuilder_.isEmpty()) {
                    this.optionsBuilder_.dispose();
                    this.optionsBuilder_ = null;
                    this.options_ = type.options_;
                    this.bitField0_ &= -9;
                    if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getOptionsFieldBuilder();
                    }
                    this.optionsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.optionsBuilder_.addAllMessages(type.options_);
                }
            }
            if (type.hasSourceContext()) {
                mergeSourceContext(type.getSourceContext());
            }
            if (type.syntax_ != 0) {
                setSyntaxValue(type.getSyntaxValue());
            }
            mo10099mergeUnknownFields(type.unknownFields);
            onChanged();
            return this;
        }

        public Builder mergeSourceContext(SourceContext sourceContext) {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceContext sourceContext2 = this.sourceContext_;
                if (sourceContext2 != null) {
                    sourceContext = SourceContext.newBuilder(sourceContext2).mergeFrom(sourceContext).mo10085buildPartial();
                }
                this.sourceContext_ = sourceContext;
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sourceContext);
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeUnknownFields  reason: collision with other method in class */
        public final Builder mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mo10099mergeUnknownFields(unknownFieldSet);
        }

        public Builder removeFields(int i) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder removeOptions(int i) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.mo10100setField(fieldDescriptor, obj);
        }

        public Builder setFields(int i, Field.Builder builder) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldsIsMutable();
                this.fields_.set(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder setFields(int i, Field field) {
            RepeatedFieldBuilderV3<Field, Field.Builder, FieldOrBuilder> repeatedFieldBuilderV3 = this.fieldsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, field);
            } else if (field == null) {
                throw new NullPointerException();
            } else {
                ensureFieldsIsMutable();
                this.fields_.set(i, field);
                onChanged();
            }
            return this;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setOneofs(int i, String str) {
            if (str != null) {
                ensureOneofsIsMutable();
                this.oneofs_.set(i, (int) str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setOptions(int i, Option.Builder builder) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureOptionsIsMutable();
                this.options_.set(i, builder.mo10084build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.mo10084build());
            }
            return this;
        }

        public Builder setOptions(int i, Option option) {
            RepeatedFieldBuilderV3<Option, Option.Builder, OptionOrBuilder> repeatedFieldBuilderV3 = this.optionsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, option);
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                ensureOptionsIsMutable();
                this.options_.set(i, option);
                onChanged();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.mo10101setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder setSourceContext(SourceContext.Builder builder) {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            SourceContext mo10084build = builder.mo10084build();
            if (singleFieldBuilderV3 == null) {
                this.sourceContext_ = mo10084build;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(mo10084build);
            }
            return this;
        }

        public Builder setSourceContext(SourceContext sourceContext) {
            SingleFieldBuilderV3<SourceContext, SourceContext.Builder, SourceContextOrBuilder> singleFieldBuilderV3 = this.sourceContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sourceContext);
            } else if (sourceContext == null) {
                throw new NullPointerException();
            } else {
                this.sourceContext_ = sourceContext;
                onChanged();
            }
            return this;
        }

        public Builder setSyntax(Syntax syntax) {
            if (syntax != null) {
                this.syntax_ = syntax.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSyntaxValue(int i) {
            this.syntax_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public final Builder mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }
    }

    private Type() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.fields_ = Collections.emptyList();
        this.oneofs_ = LazyStringArrayList.EMPTY;
        this.options_ = Collections.emptyList();
        this.syntax_ = 0;
    }

    private Type(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        List list;
        MessageLite readMessage;
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
                                if (readTag == 18) {
                                    if (!(z2 & true)) {
                                        this.fields_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    list = this.fields_;
                                    readMessage = codedInputStream.readMessage(Field.parser(), extensionRegistryLite);
                                } else if (readTag == 26) {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!(z2 & true)) {
                                        this.oneofs_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.oneofs_.add((LazyStringList) readStringRequireUtf8);
                                } else if (readTag == 34) {
                                    if (!(z2 & true)) {
                                        this.options_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    list = this.options_;
                                    readMessage = codedInputStream.readMessage(Option.parser(), extensionRegistryLite);
                                } else if (readTag == 42) {
                                    SourceContext.Builder mo10081toBuilder = this.sourceContext_ != null ? this.sourceContext_.mo10081toBuilder() : null;
                                    this.sourceContext_ = (SourceContext) codedInputStream.readMessage(SourceContext.parser(), extensionRegistryLite);
                                    if (mo10081toBuilder != null) {
                                        mo10081toBuilder.mergeFrom(this.sourceContext_);
                                        this.sourceContext_ = mo10081toBuilder.mo10085buildPartial();
                                    }
                                } else if (readTag == 48) {
                                    this.syntax_ = codedInputStream.readEnum();
                                } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                                list.add(readMessage);
                            } else {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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
                        this.fields_ = Collections.unmodifiableList(this.fields_);
                    }
                    if (z2 & true) {
                        this.oneofs_ = this.oneofs_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.options_ = Collections.unmodifiableList(this.options_);
                    }
                    this.unknownFields = newBuilder.mo10084build();
                    makeExtensionsImmutable();
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    private Type(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    public static Type getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TypeProto.internal_static_google_protobuf_Type_descriptor;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.mo10081toBuilder();
    }

    public static Builder newBuilder(Type type) {
        return DEFAULT_INSTANCE.mo10081toBuilder().mergeFrom(type);
    }

    public static Type parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Type parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.mo8396parseFrom(byteString);
    }

    public static Type parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8397parseFrom(byteString, extensionRegistryLite);
    }

    public static Type parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Type parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Type parseFrom(InputStream inputStream) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Type parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Type) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Type parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.mo8402parseFrom(byteBuffer);
    }

    public static Type parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8403parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Type parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.mo8404parseFrom(bArr);
    }

    public static Type parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.mo8407parseFrom(bArr, extensionRegistryLite);
    }

    public static Parser<Type> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return super.equals(obj);
        }
        Type type = (Type) obj;
        boolean z = ((((getName().equals(type.getName())) && getFieldsList().equals(type.getFieldsList())) && mo9894getOneofsList().equals(type.mo9894getOneofsList())) && getOptionsList().equals(type.getOptionsList())) && hasSourceContext() == type.hasSourceContext();
        if (hasSourceContext()) {
            z = z && getSourceContext().equals(type.getSourceContext());
        }
        return (z && this.syntax_ == type.syntax_) && this.unknownFields.equals(type.unknownFields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public Type mo10094getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public Field getFields(int i) {
        return this.fields_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getFieldsCount() {
        return this.fields_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<Field> getFieldsList() {
        return this.fields_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public FieldOrBuilder getFieldsOrBuilder(int i) {
        return this.fields_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<? extends FieldOrBuilder> getFieldsOrBuilderList() {
        return this.fields_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public String getOneofs(int i) {
        return this.oneofs_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public ByteString getOneofsBytes(int i) {
        return this.oneofs_.getByteString(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getOneofsCount() {
        return this.oneofs_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    /* renamed from: getOneofsList */
    public ProtocolStringList mo9894getOneofsList() {
        return this.oneofs_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public Option getOptions(int i) {
        return this.options_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getOptionsCount() {
        return this.options_.size();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<Option> getOptionsList() {
        return this.options_;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public OptionOrBuilder getOptionsOrBuilder(int i) {
        return this.options_.get(i);
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public List<? extends OptionOrBuilder> getOptionsOrBuilderList() {
        return this.options_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<Type> mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        for (int i2 = 0; i2 < this.fields_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, this.fields_.get(i2));
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.oneofs_.size(); i4++) {
            i3 += GeneratedMessageV3.computeStringSizeNoTag(this.oneofs_.getRaw(i4));
        }
        int size = (mo9894getOneofsList().size() * 1) + computeStringSize + i3;
        for (int i5 = 0; i5 < this.options_.size(); i5++) {
            size += CodedOutputStream.computeMessageSize(4, this.options_.get(i5));
        }
        if (this.sourceContext_ != null) {
            size += CodedOutputStream.computeMessageSize(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            size += CodedOutputStream.computeEnumSize(6, this.syntax_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + size;
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public SourceContext getSourceContext() {
        SourceContext sourceContext = this.sourceContext_;
        return sourceContext == null ? SourceContext.getDefaultInstance() : sourceContext;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public SourceContextOrBuilder getSourceContextOrBuilder() {
        return getSourceContext();
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public Syntax getSyntax() {
        Syntax valueOf = Syntax.valueOf(this.syntax_);
        return valueOf == null ? Syntax.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public int getSyntaxValue() {
        return this.syntax_;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @Override // com.google.protobuf.TypeOrBuilder
    public boolean hasSourceContext() {
        return this.sourceContext_ != null;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = getName().hashCode() + ((((getDescriptor().hashCode() + 779) * 37) + 1) * 53);
        if (getFieldsCount() > 0) {
            hashCode = getFieldsList().hashCode() + GeneratedOutlineSupport1.outline4(hashCode, 37, 2, 53);
        }
        if (getOneofsCount() > 0) {
            hashCode = mo9894getOneofsList().hashCode() + GeneratedOutlineSupport1.outline4(hashCode, 37, 3, 53);
        }
        if (getOptionsCount() > 0) {
            hashCode = getOptionsList().hashCode() + GeneratedOutlineSupport1.outline4(hashCode, 37, 4, 53);
        }
        if (hasSourceContext()) {
            hashCode = getSourceContext().hashCode() + GeneratedOutlineSupport1.outline4(hashCode, 37, 5, 53);
        }
        int hashCode2 = this.unknownFields.hashCode() + ((GeneratedOutlineSupport1.outline4(hashCode, 37, 6, 53) + this.syntax_) * 29);
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TypeProto.internal_static_google_protobuf_Type_fieldAccessorTable.ensureFieldAccessorsInitialized(Type.class, Builder.class);
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.fields_.size(); i++) {
            codedOutputStream.writeMessage(2, this.fields_.get(i));
        }
        for (int i2 = 0; i2 < this.oneofs_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.oneofs_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.options_.size(); i3++) {
            codedOutputStream.writeMessage(4, this.options_.get(i3));
        }
        if (this.sourceContext_ != null) {
            codedOutputStream.writeMessage(5, getSourceContext());
        }
        if (this.syntax_ != Syntax.SYNTAX_PROTO2.getNumber()) {
            codedOutputStream.writeEnum(6, this.syntax_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
