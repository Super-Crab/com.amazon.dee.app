package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes3.dex */
public final class UnknownFieldSet implements MessageLite {
    private final Map<Integer, Field> fields;
    private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(Collections.emptyMap(), Collections.emptyMap());
    private static final Parser PARSER = new Parser();

    /* loaded from: classes3.dex */
    public static final class Builder implements MessageLite.Builder {
        private Map<Integer, Field> fields;
        private Field.Builder lastField;
        private int lastFieldNumber;

        private Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            Builder builder = new Builder();
            builder.reinitialize();
            return builder;
        }

        private Field.Builder getFieldBuilder(int i) {
            Field.Builder builder = this.lastField;
            if (builder != null) {
                int i2 = this.lastFieldNumber;
                if (i == i2) {
                    return builder;
                }
                addField(i2, builder.build());
            }
            if (i == 0) {
                return null;
            }
            Field field = this.fields.get(Integer.valueOf(i));
            this.lastFieldNumber = i;
            this.lastField = Field.newBuilder();
            if (field != null) {
                this.lastField.mergeFrom(field);
            }
            return this.lastField;
        }

        private void reinitialize() {
            this.fields = Collections.emptyMap();
            this.lastFieldNumber = 0;
            this.lastField = null;
        }

        public Builder addField(int i, Field field) {
            if (i != 0) {
                if (this.lastField != null && this.lastFieldNumber == i) {
                    this.lastField = null;
                    this.lastFieldNumber = 0;
                }
                if (this.fields.isEmpty()) {
                    this.fields = new TreeMap();
                }
                this.fields.put(Integer.valueOf(i), field);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public Map<Integer, Field> asMap() {
            getFieldBuilder(0);
            return Collections.unmodifiableMap(this.fields);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build  reason: collision with other method in class */
        public UnknownFieldSet mo10084build() {
            getFieldBuilder(0);
            UnknownFieldSet defaultInstance = this.fields.isEmpty() ? UnknownFieldSet.getDefaultInstance() : new UnknownFieldSet(Collections.unmodifiableMap(this.fields), null);
            this.fields = null;
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial  reason: collision with other method in class */
        public UnknownFieldSet mo10085buildPartial() {
            return mo10084build();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear  reason: collision with other method in class */
        public Builder mo10087clear() {
            reinitialize();
            return this;
        }

        public Builder clearField(int i) {
            if (i != 0) {
                if (this.lastField != null && this.lastFieldNumber == i) {
                    this.lastField = null;
                    this.lastFieldNumber = 0;
                }
                if (this.fields.containsKey(Integer.valueOf(i))) {
                    this.fields.remove(Integer.valueOf(i));
                }
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone  reason: collision with other method in class */
        public Builder mo10093clone() {
            getFieldBuilder(0);
            return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields, null));
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType  reason: collision with other method in class */
        public UnknownFieldSet mo10094getDefaultInstanceForType() {
            return UnknownFieldSet.getDefaultInstance();
        }

        public boolean hasField(int i) {
            if (i != 0) {
                return i == this.lastFieldNumber || this.fields.containsKey(Integer.valueOf(i));
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            mo9967mergeFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(read, inputStream)));
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return mergeDelimitedFrom(inputStream);
        }

        public Builder mergeField(int i, Field field) {
            if (i != 0) {
                if (hasField(i)) {
                    getFieldBuilder(i).mergeFrom(field);
                } else {
                    addField(i, field);
                }
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public boolean mergeFieldFrom(int i, CodedInputStream codedInputStream) throws IOException {
            int tagFieldNumber = WireFormat.getTagFieldNumber(i);
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType == 0) {
                getFieldBuilder(tagFieldNumber).addVarint(codedInputStream.readInt64());
                return true;
            } else if (tagWireType == 1) {
                getFieldBuilder(tagFieldNumber).addFixed64(codedInputStream.readFixed64());
                return true;
            } else if (tagWireType == 2) {
                getFieldBuilder(tagFieldNumber).addLengthDelimited(codedInputStream.readBytes());
                return true;
            } else if (tagWireType == 3) {
                Builder newBuilder = UnknownFieldSet.newBuilder();
                codedInputStream.readGroup(tagFieldNumber, newBuilder, ExtensionRegistry.getEmptyRegistry());
                getFieldBuilder(tagFieldNumber).addGroup(newBuilder.mo10084build());
                return true;
            } else if (tagWireType == 4) {
                return false;
            } else {
                if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                getFieldBuilder(tagFieldNumber).addFixed32(codedInputStream.readFixed32());
                return true;
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9962mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newCodedInput = byteString.newCodedInput();
                mo9964mergeFrom(newCodedInput);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9963mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return mo9962mergeFrom(byteString);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9964mergeFrom(CodedInputStream codedInputStream) throws IOException {
            int readTag;
            do {
                readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                }
            } while (mergeFieldFrom(readTag, codedInputStream));
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo10097mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return mo9964mergeFrom(codedInputStream);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9966mergeFrom(MessageLite messageLite) {
            if (messageLite instanceof UnknownFieldSet) {
                return mergeFrom((UnknownFieldSet) messageLite);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }

        public Builder mergeFrom(UnknownFieldSet unknownFieldSet) {
            if (unknownFieldSet != UnknownFieldSet.getDefaultInstance()) {
                for (Map.Entry entry : unknownFieldSet.fields.entrySet()) {
                    mergeField(((Integer) entry.getKey()).intValue(), (Field) entry.getValue());
                }
            }
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9967mergeFrom(InputStream inputStream) throws IOException {
            CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
            mo9964mergeFrom(newInstance);
            newInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9968mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return mo9967mergeFrom(inputStream);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9969mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr);
                mo9964mergeFrom(newInstance);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9970mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(bArr, i, i2);
                mo9964mergeFrom(newInstance);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9971mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return mo9970mergeFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom  reason: collision with other method in class */
        public Builder mo9972mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return mo9969mergeFrom(bArr);
        }

        public Builder mergeLengthDelimitedField(int i, ByteString byteString) {
            if (i != 0) {
                getFieldBuilder(i).addLengthDelimited(byteString);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }

        public Builder mergeVarintField(int i, int i2) {
            if (i != 0) {
                getFieldBuilder(i).addVarint(i2);
                return this;
            }
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
    }

    /* loaded from: classes3.dex */
    public static final class Field {
        private static final Field fieldDefaultInstance = newBuilder().build();
        private List<Integer> fixed32;
        private List<Long> fixed64;
        private List<UnknownFieldSet> group;
        private List<ByteString> lengthDelimited;
        private List<Long> varint;

        /* loaded from: classes3.dex */
        public static final class Builder {
            private Field result;

            private Builder() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                Builder builder = new Builder();
                builder.result = new Field();
                return builder;
            }

            public Builder addFixed32(int i) {
                if (this.result.fixed32 == null) {
                    this.result.fixed32 = new ArrayList();
                }
                this.result.fixed32.add(Integer.valueOf(i));
                return this;
            }

            public Builder addFixed64(long j) {
                if (this.result.fixed64 == null) {
                    this.result.fixed64 = new ArrayList();
                }
                this.result.fixed64.add(Long.valueOf(j));
                return this;
            }

            public Builder addGroup(UnknownFieldSet unknownFieldSet) {
                if (this.result.group == null) {
                    this.result.group = new ArrayList();
                }
                this.result.group.add(unknownFieldSet);
                return this;
            }

            public Builder addLengthDelimited(ByteString byteString) {
                if (this.result.lengthDelimited == null) {
                    this.result.lengthDelimited = new ArrayList();
                }
                this.result.lengthDelimited.add(byteString);
                return this;
            }

            public Builder addVarint(long j) {
                if (this.result.varint == null) {
                    this.result.varint = new ArrayList();
                }
                this.result.varint.add(Long.valueOf(j));
                return this;
            }

            public Field build() {
                Field field;
                List unmodifiableList;
                Field field2;
                List unmodifiableList2;
                Field field3;
                List unmodifiableList3;
                Field field4;
                List unmodifiableList4;
                Field field5;
                List unmodifiableList5;
                if (this.result.varint == null) {
                    field = this.result;
                    unmodifiableList = Collections.emptyList();
                } else {
                    field = this.result;
                    unmodifiableList = Collections.unmodifiableList(field.varint);
                }
                field.varint = unmodifiableList;
                if (this.result.fixed32 == null) {
                    field2 = this.result;
                    unmodifiableList2 = Collections.emptyList();
                } else {
                    field2 = this.result;
                    unmodifiableList2 = Collections.unmodifiableList(field2.fixed32);
                }
                field2.fixed32 = unmodifiableList2;
                if (this.result.fixed64 == null) {
                    field3 = this.result;
                    unmodifiableList3 = Collections.emptyList();
                } else {
                    field3 = this.result;
                    unmodifiableList3 = Collections.unmodifiableList(field3.fixed64);
                }
                field3.fixed64 = unmodifiableList3;
                if (this.result.lengthDelimited == null) {
                    field4 = this.result;
                    unmodifiableList4 = Collections.emptyList();
                } else {
                    field4 = this.result;
                    unmodifiableList4 = Collections.unmodifiableList(field4.lengthDelimited);
                }
                field4.lengthDelimited = unmodifiableList4;
                if (this.result.group == null) {
                    field5 = this.result;
                    unmodifiableList5 = Collections.emptyList();
                } else {
                    field5 = this.result;
                    unmodifiableList5 = Collections.unmodifiableList(field5.group);
                }
                field5.group = unmodifiableList5;
                Field field6 = this.result;
                this.result = null;
                return field6;
            }

            public Builder clear() {
                this.result = new Field();
                return this;
            }

            public Builder mergeFrom(Field field) {
                if (!field.varint.isEmpty()) {
                    if (this.result.varint == null) {
                        this.result.varint = new ArrayList();
                    }
                    this.result.varint.addAll(field.varint);
                }
                if (!field.fixed32.isEmpty()) {
                    if (this.result.fixed32 == null) {
                        this.result.fixed32 = new ArrayList();
                    }
                    this.result.fixed32.addAll(field.fixed32);
                }
                if (!field.fixed64.isEmpty()) {
                    if (this.result.fixed64 == null) {
                        this.result.fixed64 = new ArrayList();
                    }
                    this.result.fixed64.addAll(field.fixed64);
                }
                if (!field.lengthDelimited.isEmpty()) {
                    if (this.result.lengthDelimited == null) {
                        this.result.lengthDelimited = new ArrayList();
                    }
                    this.result.lengthDelimited.addAll(field.lengthDelimited);
                }
                if (!field.group.isEmpty()) {
                    if (this.result.group == null) {
                        this.result.group = new ArrayList();
                    }
                    this.result.group.addAll(field.group);
                }
                return this;
            }
        }

        private Field() {
        }

        public static Field getDefaultInstance() {
            return fieldDefaultInstance;
        }

        private Object[] getIdentityArray() {
            return new Object[]{this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group};
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(Field field) {
            return newBuilder().mergeFrom(field);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Field) {
                return Arrays.equals(getIdentityArray(), ((Field) obj).getIdentityArray());
            }
            return false;
        }

        public List<Integer> getFixed32List() {
            return this.fixed32;
        }

        public List<Long> getFixed64List() {
            return this.fixed64;
        }

        public List<UnknownFieldSet> getGroupList() {
            return this.group;
        }

        public List<ByteString> getLengthDelimitedList() {
            return this.lengthDelimited;
        }

        public int getSerializedSize(int i) {
            int i2 = 0;
            for (Long l : this.varint) {
                i2 += CodedOutputStream.computeUInt64Size(i, l.longValue());
            }
            for (Integer num : this.fixed32) {
                i2 += CodedOutputStream.computeFixed32Size(i, num.intValue());
            }
            for (Long l2 : this.fixed64) {
                i2 += CodedOutputStream.computeFixed64Size(i, l2.longValue());
            }
            for (ByteString byteString : this.lengthDelimited) {
                i2 += CodedOutputStream.computeBytesSize(i, byteString);
            }
            for (UnknownFieldSet unknownFieldSet : this.group) {
                i2 += CodedOutputStream.computeGroupSize(i, unknownFieldSet);
            }
            return i2;
        }

        public int getSerializedSizeAsMessageSetExtension(int i) {
            int i2 = 0;
            for (ByteString byteString : this.lengthDelimited) {
                i2 += CodedOutputStream.computeRawMessageSetExtensionSize(i, byteString);
            }
            return i2;
        }

        public List<Long> getVarintList() {
            return this.varint;
        }

        public int hashCode() {
            return Arrays.hashCode(getIdentityArray());
        }

        public void writeAsMessageSetExtensionTo(int i, CodedOutputStream codedOutputStream) throws IOException {
            for (ByteString byteString : this.lengthDelimited) {
                codedOutputStream.writeRawMessageSetExtension(i, byteString);
            }
        }

        public void writeTo(int i, CodedOutputStream codedOutputStream) throws IOException {
            for (Long l : this.varint) {
                codedOutputStream.writeUInt64(i, l.longValue());
            }
            for (Integer num : this.fixed32) {
                codedOutputStream.writeFixed32(i, num.intValue());
            }
            for (Long l2 : this.fixed64) {
                codedOutputStream.writeFixed64(i, l2.longValue());
            }
            for (ByteString byteString : this.lengthDelimited) {
                codedOutputStream.writeBytes(i, byteString);
            }
            for (UnknownFieldSet unknownFieldSet : this.group) {
                codedOutputStream.writeGroup(i, unknownFieldSet);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class Parser extends AbstractParser<UnknownFieldSet> {
        @Override // com.google.protobuf.Parser
        /* renamed from: parsePartialFrom */
        public UnknownFieldSet mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            Builder newBuilder = UnknownFieldSet.newBuilder();
            try {
                newBuilder.mo9964mergeFrom(codedInputStream);
                return newBuilder.mo10085buildPartial();
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(newBuilder.mo10085buildPartial());
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(newBuilder.mo10085buildPartial());
            }
        }
    }

    private UnknownFieldSet() {
        this.fields = null;
    }

    UnknownFieldSet(Map<Integer, Field> map, Map<Integer, Field> map2) {
        this.fields = map;
    }

    public static UnknownFieldSet getDefaultInstance() {
        return defaultInstance;
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static Builder newBuilder(UnknownFieldSet unknownFieldSet) {
        return newBuilder().mergeFrom(unknownFieldSet);
    }

    public static UnknownFieldSet parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return newBuilder().mo9962mergeFrom(byteString).mo10084build();
    }

    public static UnknownFieldSet parseFrom(CodedInputStream codedInputStream) throws IOException {
        return newBuilder().mo9964mergeFrom(codedInputStream).mo10084build();
    }

    public static UnknownFieldSet parseFrom(InputStream inputStream) throws IOException {
        return newBuilder().mo9967mergeFrom(inputStream).mo10084build();
    }

    public static UnknownFieldSet parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return newBuilder().mo9969mergeFrom(bArr).mo10084build();
    }

    public Map<Integer, Field> asMap() {
        return this.fields;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UnknownFieldSet) && this.fields.equals(((UnknownFieldSet) obj).fields);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType  reason: collision with other method in class */
    public UnknownFieldSet mo10094getDefaultInstanceForType() {
        return defaultInstance;
    }

    public Field getField(int i) {
        Field field = this.fields.get(Integer.valueOf(i));
        return field == null ? Field.getDefaultInstance() : field;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType  reason: collision with other method in class */
    public final Parser mo9954getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            i += entry.getValue().getSerializedSize(entry.getKey().intValue());
        }
        return i;
    }

    public int getSerializedSizeAsMessageSet() {
        int i = 0;
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            i += entry.getValue().getSerializedSizeAsMessageSetExtension(entry.getKey().intValue());
        }
        return i;
    }

    public boolean hasField(int i) {
        return this.fields.containsKey(Integer.valueOf(i));
    }

    public int hashCode() {
        return this.fields.hashCode();
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return true;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType  reason: collision with other method in class */
    public Builder mo10079newBuilderForType() {
        return newBuilder();
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder  reason: collision with other method in class */
    public Builder mo10081toBuilder() {
        return newBuilder().mergeFrom(this);
    }

    @Override // com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream newInstance = CodedOutputStream.newInstance(bArr);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(newCodedBuilder.getCodedOutput());
            return newCodedBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return TextFormat.printToString(this);
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) throws IOException {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeAsMessageSetExtensionTo(entry.getKey().intValue(), codedOutputStream);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) throws IOException {
        CodedOutputStream newInstance = CodedOutputStream.newInstance(outputStream);
        newInstance.writeRawVarint32(getSerializedSize());
        writeTo(newInstance);
        newInstance.flush();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (Map.Entry<Integer, Field> entry : this.fields.entrySet()) {
            entry.getValue().writeTo(entry.getKey().intValue(), codedOutputStream);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) throws IOException {
        CodedOutputStream newInstance = CodedOutputStream.newInstance(outputStream);
        writeTo(newInstance);
        newInstance.flush();
    }
}
