package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.factories.ReflectionSerializerFactory;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes2.dex */
final class FieldSerializerAnnotationsUtil {
    public FieldSerializerAnnotationsUtil(FieldSerializer fieldSerializer) {
    }

    public void processAnnotatedFields(FieldSerializer fieldSerializer) {
        FieldSerializer.CachedField[] fields = fieldSerializer.getFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            Field field = fields[i].getField();
            if (field.isAnnotationPresent(FieldSerializer.Bind.class)) {
                fields[i].setSerializer(ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), ((FieldSerializer.Bind) field.getAnnotation(FieldSerializer.Bind.class)).value(), field.getClass()));
            }
            if (field.isAnnotationPresent(CollectionSerializer.BindCollection.class)) {
                field.isAnnotationPresent(MapSerializer.BindMap.class);
            }
            if (field.isAnnotationPresent(CollectionSerializer.BindCollection.class)) {
                if (fields[i].serializer == null) {
                    CollectionSerializer.BindCollection bindCollection = (CollectionSerializer.BindCollection) field.getAnnotation(CollectionSerializer.BindCollection.class);
                    if (Collection.class.isAssignableFrom(fields[i].field.getType())) {
                        Class<? extends Serializer> elementSerializer = bindCollection.elementSerializer();
                        if (elementSerializer == Serializer.class) {
                            elementSerializer = null;
                        }
                        Serializer makeSerializer = elementSerializer == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), elementSerializer, field.getClass());
                        boolean elementsCanBeNull = bindCollection.elementsCanBeNull();
                        Class<?> elementClass = bindCollection.elementClass();
                        if (elementClass == Object.class) {
                            elementClass = null;
                        }
                        CollectionSerializer collectionSerializer = new CollectionSerializer();
                        collectionSerializer.setElementsCanBeNull(elementsCanBeNull);
                        collectionSerializer.setElementClass(elementClass, makeSerializer);
                        fields[i].setSerializer(collectionSerializer);
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CollectionSerialier.Bind should be used only with fields implementing java.util.Collection, but field ");
                        outline107.append(fields[i].getField().getDeclaringClass().getName());
                        outline107.append(".");
                        outline107.append(fields[i].getField().getName());
                        outline107.append(" does not implement it.");
                        throw new RuntimeException(outline107.toString());
                    }
                } else {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("CollectionSerialier.Bind cannot be used with field ");
                    outline1072.append(fields[i].getField().getDeclaringClass().getName());
                    outline1072.append(".");
                    outline1072.append(fields[i].getField().getName());
                    outline1072.append(", because it has a serializer already.");
                    throw new RuntimeException(outline1072.toString());
                }
            }
            if (field.isAnnotationPresent(MapSerializer.BindMap.class)) {
                if (fields[i].serializer == null) {
                    MapSerializer.BindMap bindMap = (MapSerializer.BindMap) field.getAnnotation(MapSerializer.BindMap.class);
                    if (Map.class.isAssignableFrom(fields[i].field.getType())) {
                        Class<? extends Serializer> valueSerializer = bindMap.valueSerializer();
                        Class<? extends Serializer> keySerializer = bindMap.keySerializer();
                        if (valueSerializer == Serializer.class) {
                            valueSerializer = null;
                        }
                        if (keySerializer == Serializer.class) {
                            keySerializer = null;
                        }
                        Serializer makeSerializer2 = valueSerializer == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), valueSerializer, field.getClass());
                        Serializer makeSerializer3 = keySerializer == null ? null : ReflectionSerializerFactory.makeSerializer(fieldSerializer.getKryo(), keySerializer, field.getClass());
                        boolean valuesCanBeNull = bindMap.valuesCanBeNull();
                        boolean keysCanBeNull = bindMap.keysCanBeNull();
                        Class<?> keyClass = bindMap.keyClass();
                        Class<?> valueClass = bindMap.valueClass();
                        if (keyClass == Object.class) {
                            keyClass = null;
                        }
                        if (valueClass == Object.class) {
                            valueClass = null;
                        }
                        MapSerializer mapSerializer = new MapSerializer();
                        mapSerializer.setKeysCanBeNull(keysCanBeNull);
                        mapSerializer.setValuesCanBeNull(valuesCanBeNull);
                        mapSerializer.setKeyClass(keyClass, makeSerializer3);
                        mapSerializer.setValueClass(valueClass, makeSerializer2);
                        fields[i].setSerializer(mapSerializer);
                    } else {
                        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("MapSerialier.Bind should be used only with fields implementing java.util.Map, but field ");
                        outline1073.append(fields[i].getField().getDeclaringClass().getName());
                        outline1073.append(".");
                        outline1073.append(fields[i].getField().getName());
                        outline1073.append(" does not implement it.");
                        throw new RuntimeException(outline1073.toString());
                    }
                } else {
                    StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("MapSerialier.Bind cannot be used with field ");
                    outline1074.append(fields[i].getField().getDeclaringClass().getName());
                    outline1074.append(".");
                    outline1074.append(fields[i].getField().getName());
                    outline1074.append(", because it has a serializer already.");
                    throw new RuntimeException(outline1074.toString());
                }
            }
        }
    }
}
