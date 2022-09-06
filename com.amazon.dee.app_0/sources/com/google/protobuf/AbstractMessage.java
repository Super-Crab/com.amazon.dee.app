package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    protected int memoizedSize = -1;

    /* loaded from: classes3.dex */
    public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends AbstractMessageLite.Builder implements Message.Builder {
        /* JADX INFO: Access modifiers changed from: protected */
        public static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(MessageReflection.findMissingFields(message));
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clear */
        public BuilderType mo10087clear() {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
                mo10088clearField(entry.getKey());
            }
            return this;
        }

        /* renamed from: clearOneof */
        public BuilderType mo10090clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("clearOneof() is not implemented.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public BuilderType mo10093clone() {
            throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void dispose() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public List<String> findInitializationErrors() {
            return MessageReflection.findMissingFields(this);
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public String getInitializationErrorString() {
            return MessageReflection.delimitWithCommas(findInitializationErrors());
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        @Override // com.google.protobuf.Message.Builder
        public Message.Builder getRepeatedFieldBuilder(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on an unsupported message type.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: internalMergeFrom */
        public BuilderType mo8359internalMergeFrom(AbstractMessageLite abstractMessageLite) {
            return mo10096mergeFrom((Message) abstractMessageLite);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void markClean() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
            return super.mergeDelimitedFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9962mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9962mergeFrom(byteString);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9963mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9963mergeFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9964mergeFrom(CodedInputStream codedInputStream) throws IOException {
            return mo10097mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo10097mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readTag;
            UnknownFieldSet.Builder newBuilder = getDescriptorForType().getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 ? codedInputStream.shouldDiscardUnknownFieldsProto3() : codedInputStream.shouldDiscardUnknownFields() ? null : UnknownFieldSet.newBuilder(getUnknownFields());
            do {
                readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                }
            } while (MessageReflection.mergeFieldFrom(codedInputStream, newBuilder, extensionRegistryLite, getDescriptorForType(), new MessageReflection.BuilderAdapter(this), readTag));
            if (newBuilder != null) {
                mo10102setUnknownFields(newBuilder.mo10084build());
            }
            return this;
        }

        /* renamed from: mergeFrom */
        public BuilderType mo10096mergeFrom(Message message) {
            return mergeFrom(message, message.getAllFields());
        }

        BuilderType mergeFrom(Message message, Map<Descriptors.FieldDescriptor, Object> map) {
            Object value;
            if (message.getDescriptorForType() == getDescriptorForType()) {
                for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
                    Descriptors.FieldDescriptor key = entry.getKey();
                    if (key.isRepeated()) {
                        for (Object obj : (List) entry.getValue()) {
                            mo10083addRepeatedField(key, obj);
                        }
                    } else {
                        if (key.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                            Message message2 = (Message) getField(key);
                            if (message2 != message2.mo10094getDefaultInstanceForType()) {
                                value = message2.mo10079newBuilderForType().mo10096mergeFrom(message2).mo10096mergeFrom((Message) entry.getValue()).mo10084build();
                                mo10100setField(key, value);
                            }
                        }
                        value = entry.getValue();
                        mo10100setField(key, value);
                    }
                }
                mo10099mergeUnknownFields(message.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9967mergeFrom(InputStream inputStream) throws IOException {
            return (BuilderType) super.mo9967mergeFrom(inputStream);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9968mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (BuilderType) super.mo9968mergeFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9969mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9969mergeFrom(bArr);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9970mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9970mergeFrom(bArr, i, i2);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9971mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9971mergeFrom(bArr, i, i2, extensionRegistryLite);
        }

        @Override // com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: mergeFrom */
        public BuilderType mo9972mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (BuilderType) super.mo9972mergeFrom(bArr, extensionRegistryLite);
        }

        /* renamed from: mergeUnknownFields */
        public BuilderType mo10099mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            mo10102setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).mo10084build());
            return this;
        }

        public String toString() {
            return TextFormat.printToString(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public interface BuilderParent {
        void markDirty();
    }

    private static boolean compareBytes(Object obj, Object obj2) {
        return (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) ? toByteString(obj).equals(toByteString(obj2)) : Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    static boolean compareFields(Map<Descriptors.FieldDescriptor, Object> map, Map<Descriptors.FieldDescriptor, Object> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (Descriptors.FieldDescriptor fieldDescriptor : map.keySet()) {
            if (!map2.containsKey(fieldDescriptor)) {
                return false;
            }
            Object obj = map.get(fieldDescriptor);
            Object obj2 = map2.get(fieldDescriptor);
            if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) obj;
                    List list2 = (List) obj2;
                    if (list.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (!compareBytes(list.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                    continue;
                } else if (!compareBytes(obj, obj2)) {
                    return false;
                }
            } else if (fieldDescriptor.isMapField()) {
                if (!compareMapField(obj, obj2)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareMapField(Object obj, Object obj2) {
        return MapFieldLite.equals(convertMapEntryListToMap((List) obj), convertMapEntryListToMap((List) obj2));
    }

    private static Map convertMapEntryListToMap(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        Message message = (Message) it2.next();
        Descriptors.Descriptor descriptorForType = message.getDescriptorForType();
        Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName("key");
        Descriptors.FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
        Object field = message.getField(findFieldByName2);
        if (field instanceof Descriptors.EnumValueDescriptor) {
            field = Integer.valueOf(((Descriptors.EnumValueDescriptor) field).getNumber());
        }
        while (true) {
            hashMap.put(message.getField(findFieldByName), field);
            if (!it2.hasNext()) {
                return hashMap;
            }
            message = (Message) it2.next();
            field = message.getField(findFieldByName2);
            if (field instanceof Descriptors.EnumValueDescriptor) {
                field = Integer.valueOf(((Descriptors.EnumValueDescriptor) field).getNumber());
            }
        }
    }

    @Deprecated
    protected static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    @Deprecated
    protected static int hashEnum(Internal.EnumLite enumLite) {
        return enumLite.getNumber();
    }

    @Deprecated
    protected static int hashEnumList(List<? extends Internal.EnumLite> list) {
        int i = 1;
        for (Internal.EnumLite enumLite : list) {
            i = (i * 31) + hashEnum(enumLite);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int hashFields(int i, Map<Descriptors.FieldDescriptor, Object> map) {
        int i2;
        int hashEnum;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
            Descriptors.FieldDescriptor key = entry.getKey();
            Object value = entry.getValue();
            int number = key.getNumber() + (i * 37);
            if (key.isMapField()) {
                i = (number * 53) + hashMapField(value);
            } else {
                if (key.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
                    i2 = number * 53;
                    hashEnum = value.hashCode();
                } else if (key.isRepeated()) {
                    i2 = number * 53;
                    hashEnum = Internal.hashEnumList((List) value);
                } else {
                    i2 = number * 53;
                    hashEnum = Internal.hashEnum((Internal.EnumLite) value);
                }
                i = hashEnum + i2;
            }
        }
        return i;
    }

    @Deprecated
    protected static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    private static int hashMapField(Object obj) {
        return MapFieldLite.calculateHashCodeForMap(convertMapEntryListToMap((List) obj));
    }

    private static ByteString toByteString(Object obj) {
        return obj instanceof byte[] ? ByteString.copyFrom((byte[]) obj) : (ByteString) obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if (getDescriptorForType() != message.getDescriptorForType()) {
            return false;
        }
        return compareFields(getAllFields(), message.getAllFields()) && getUnknownFields().equals(message.getUnknownFields());
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public List<String> findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.FieldDescriptor getOneofFieldDescriptor(Descriptors.OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    @Override // com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = MessageReflection.getSerializedSize(this, getAllFields());
        return this.memoizedSize;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasOneof(Descriptors.OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i == 0) {
            int hashFields = (hashFields(getDescriptorForType().hashCode() + 779, getAllFields()) * 29) + getUnknownFields().hashCode();
            this.memoizedHashCode = hashFields;
            return hashFields;
        }
        return i;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return MessageReflection.isInitialized(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Message.Builder newBuilderForType(BuilderParent builderParent) {
        throw new UnsupportedOperationException("Nested builder is not supported for this type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.protobuf.AbstractMessageLite
    public UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException((Message) this);
    }

    @Override // com.google.protobuf.Message
    public final String toString() {
        return TextFormat.printToString(this);
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        MessageReflection.writeMessageTo(this, getAllFields(), codedOutputStream, false);
    }
}
