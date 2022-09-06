package com.google.protobuf;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Message;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes3.dex */
public final class MapEntry<K, V> extends AbstractMessage {
    private volatile int cachedSerializedSize;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    /* loaded from: classes3.dex */
    public static class Builder<K, V> extends AbstractMessage.Builder<Builder<K, V>> {
        private boolean hasKey;
        private boolean hasValue;
        private K key;
        private final Metadata<K, V> metadata;
        private V value;

        private Builder(Metadata<K, V> metadata) {
            this(metadata, metadata.defaultKey, metadata.defaultValue, false, false);
        }

        private Builder(Metadata<K, V> metadata, K k, V v, boolean z, boolean z2) {
            this.metadata = metadata;
            this.key = k;
            this.value = v;
            this.hasKey = z;
            this.hasValue = z2;
        }

        private void checkFieldDescriptor(Descriptors.FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() == this.metadata.descriptor) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong FieldDescriptor \"");
            outline107.append(fieldDescriptor.getFullName());
            outline107.append("\" used in message \"");
            outline107.append(this.metadata.descriptor.getFullName());
            throw new RuntimeException(outline107.toString());
        }

        @Override // com.google.protobuf.Message.Builder
        /* renamed from: addRepeatedField */
        public Builder<K, V> mo10083addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: build */
        public MapEntry<K, V> mo10084build() {
            MapEntry<K, V> mo10085buildPartial = mo10085buildPartial();
            if (mo10085buildPartial.isInitialized()) {
                return mo10085buildPartial;
            }
            throw AbstractMessage.Builder.newUninitializedMessageException((Message) mo10085buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: buildPartial */
        public MapEntry<K, V> mo10085buildPartial() {
            return new MapEntry<>(this.metadata, this.key, this.value);
        }

        @Override // com.google.protobuf.Message.Builder
        /* renamed from: clearField */
        public Builder<K, V> mo10088clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 1) {
                clearKey();
            } else {
                clearValue();
            }
            return this;
        }

        public Builder<K, V> clearKey() {
            this.key = this.metadata.defaultKey;
            this.hasKey = false;
            return this;
        }

        public Builder<K, V> clearValue() {
            this.value = this.metadata.defaultValue;
            this.hasValue = false;
            return this;
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /* renamed from: clone */
        public Builder<K, V> mo10093clone() {
            return new Builder<>(this.metadata, this.key, this.value, this.hasKey, this.hasValue);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.MessageOrBuilder
        public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
            TreeMap treeMap = new TreeMap();
            for (Descriptors.FieldDescriptor fieldDescriptor : this.metadata.descriptor.getFields()) {
                if (hasField(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                }
            }
            return Collections.unmodifiableMap(treeMap);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        /* renamed from: getDefaultInstanceForType */
        public MapEntry<K, V> mo10094getDefaultInstanceForType() {
            Metadata<K, V> metadata = this.metadata;
            return new MapEntry<>(metadata, metadata.defaultKey, metadata.defaultValue);
        }

        @Override // com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return this.metadata.descriptor;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            Object key = fieldDescriptor.getNumber() == 1 ? getKey() : getValue();
            return fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM ? fieldDescriptor.mo9296getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue()) : key;
        }

        public K getKey() {
            return this.key;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public UnknownFieldSet getUnknownFields() {
            return UnknownFieldSet.getDefaultInstance();
        }

        public V getValue() {
            return this.value;
        }

        @Override // com.google.protobuf.MessageOrBuilder
        public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            return fieldDescriptor.getNumber() == 1 ? this.hasKey : this.hasValue;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder
        public boolean isInitialized() {
            return MapEntry.isInitialized(this.metadata, this.value);
        }

        @Override // com.google.protobuf.Message.Builder
        /* renamed from: newBuilderForField */
        public Message.Builder mo9371newBuilderForField(Descriptors.FieldDescriptor fieldDescriptor) {
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 2 && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                return ((Message) this.value).mo10079newBuilderForType();
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline107.append(fieldDescriptor.getFullName());
            outline107.append("\" is not a message value field.");
            throw new RuntimeException(outline107.toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.protobuf.Message.Builder
        /* renamed from: setField */
        public Builder<K, V> mo10100setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            checkFieldDescriptor(fieldDescriptor);
            if (fieldDescriptor.getNumber() == 1) {
                setKey(obj);
            } else {
                if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM) {
                    obj = Integer.valueOf(((Descriptors.EnumValueDescriptor) obj).getNumber());
                } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && obj != null && !this.metadata.defaultValue.getClass().isInstance(obj)) {
                    obj = ((Message) this.metadata.defaultValue).mo10081toBuilder().mo10096mergeFrom((Message) obj).mo10084build();
                }
                setValue(obj);
            }
            return this;
        }

        public Builder<K, V> setKey(K k) {
            this.key = k;
            this.hasKey = true;
            return this;
        }

        @Override // com.google.protobuf.Message.Builder
        /* renamed from: setRepeatedField */
        public Builder<K, V> mo10101setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.Message.Builder
        /* renamed from: setUnknownFields */
        public Builder<K, V> mo10102setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return this;
        }

        public Builder<K, V> setValue(V v) {
            this.value = v;
            this.hasValue = true;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Metadata<K, V> extends MapEntryLite.Metadata<K, V> {
        public final Descriptors.Descriptor descriptor;
        public final Parser<MapEntry<K, V>> parser;

        public Metadata(Descriptors.Descriptor descriptor, MapEntry<K, V> mapEntry, WireFormat.FieldType fieldType, WireFormat.FieldType fieldType2) {
            super(fieldType, ((MapEntry) mapEntry).key, fieldType2, ((MapEntry) mapEntry).value);
            this.descriptor = descriptor;
            this.parser = new AbstractParser<MapEntry<K, V>>() { // from class: com.google.protobuf.MapEntry.Metadata.1
                @Override // com.google.protobuf.Parser
                /* renamed from: parsePartialFrom */
                public MapEntry<K, V> mo10082parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new MapEntry<>(Metadata.this, codedInputStream, extensionRegistryLite);
                }
            };
        }
    }

    private MapEntry(Descriptors.Descriptor descriptor, WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.cachedSerializedSize = -1;
        this.key = k;
        this.value = v;
        this.metadata = new Metadata<>(descriptor, this, fieldType, fieldType2);
    }

    private MapEntry(Metadata<K, V> metadata, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.cachedSerializedSize = -1;
        try {
            this.metadata = metadata;
            Map.Entry parseEntry = MapEntryLite.parseEntry(codedInputStream, metadata, extensionRegistryLite);
            this.key = (K) parseEntry.getKey();
            this.value = (V) parseEntry.getValue();
        } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (IOException e2) {
            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
        }
    }

    private MapEntry(Metadata metadata, K k, V v) {
        this.cachedSerializedSize = -1;
        this.key = k;
        this.value = v;
        this.metadata = metadata;
    }

    private void checkFieldDescriptor(Descriptors.FieldDescriptor fieldDescriptor) {
        if (fieldDescriptor.getContainingType() == this.metadata.descriptor) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong FieldDescriptor \"");
        outline107.append(fieldDescriptor.getFullName());
        outline107.append("\" used in message \"");
        outline107.append(this.metadata.descriptor.getFullName());
        throw new RuntimeException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <V> boolean isInitialized(Metadata metadata, V v) {
        if (metadata.valueType.getJavaType() == WireFormat.JavaType.MESSAGE) {
            return ((MessageLite) v).isInitialized();
        }
        return true;
    }

    public static <K, V> MapEntry<K, V> newDefaultInstance(Descriptors.Descriptor descriptor, WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntry<>(descriptor, fieldType, k, fieldType2, v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.MessageOrBuilder
    public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
        TreeMap treeMap = new TreeMap();
        for (Descriptors.FieldDescriptor fieldDescriptor : this.metadata.descriptor.getFields()) {
            if (hasField(fieldDescriptor)) {
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
            }
        }
        return Collections.unmodifiableMap(treeMap);
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    /* renamed from: getDefaultInstanceForType */
    public MapEntry<K, V> mo10094getDefaultInstanceForType() {
        Metadata<K, V> metadata = this.metadata;
        return new MapEntry<>(metadata, metadata.defaultKey, metadata.defaultValue);
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Descriptors.Descriptor getDescriptorForType() {
        return this.metadata.descriptor;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getField(Descriptors.FieldDescriptor fieldDescriptor) {
        checkFieldDescriptor(fieldDescriptor);
        Object key = fieldDescriptor.getNumber() == 1 ? getKey() : getValue();
        return fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.ENUM ? fieldDescriptor.mo9296getEnumType().findValueByNumberCreatingIfUnknown(((Integer) key).intValue()) : key;
    }

    public K getKey() {
        return this.key;
    }

    final Metadata<K, V> getMetadata() {
        return this.metadata;
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: getParserForType */
    public Parser<MapEntry<K, V>> mo9954getParserForType() {
        return this.metadata.parser;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public Object getRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i) {
        throw new RuntimeException("There is no repeated field in a map entry message.");
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public int getRepeatedFieldCount(Descriptors.FieldDescriptor fieldDescriptor) {
        throw new RuntimeException("There is no repeated field in a map entry message.");
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        if (this.cachedSerializedSize != -1) {
            return this.cachedSerializedSize;
        }
        int computeSerializedSize = MapEntryLite.computeSerializedSize(this.metadata, this.key, this.value);
        this.cachedSerializedSize = computeSerializedSize;
        return computeSerializedSize;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public UnknownFieldSet getUnknownFields() {
        return UnknownFieldSet.getDefaultInstance();
    }

    public V getValue() {
        return this.value;
    }

    @Override // com.google.protobuf.MessageOrBuilder
    public boolean hasField(Descriptors.FieldDescriptor fieldDescriptor) {
        checkFieldDescriptor(fieldDescriptor);
        return true;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public boolean isInitialized() {
        return isInitialized(this.metadata, this.value);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: newBuilderForType */
    public Builder<K, V> mo10079newBuilderForType() {
        return new Builder<>(this.metadata);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    /* renamed from: toBuilder */
    public Builder<K, V> mo10081toBuilder() {
        return new Builder<>(this.metadata, this.key, this.value, true, true);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        MapEntryLite.writeTo(codedOutputStream, this.metadata, this.key, this.value);
    }
}
