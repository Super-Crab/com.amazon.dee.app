package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializer;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.EnumResolver;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class StdKeyDeserializers {
    protected final HashMap<JavaType, KeyDeserializer> _keyDeserializers = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public StdKeyDeserializers() {
        add(new StdKeyDeserializer.BoolKD());
        add(new StdKeyDeserializer.ByteKD());
        add(new StdKeyDeserializer.CharKD());
        add(new StdKeyDeserializer.ShortKD());
        add(new StdKeyDeserializer.IntKD());
        add(new StdKeyDeserializer.LongKD());
        add(new StdKeyDeserializer.FloatKD());
        add(new StdKeyDeserializer.DoubleKD());
        add(new StdKeyDeserializer.DateKD());
        add(new StdKeyDeserializer.CalendarKD());
        add(new StdKeyDeserializer.UuidKD());
    }

    private void add(StdKeyDeserializer stdKeyDeserializer) {
        this._keyDeserializers.put(TypeFactory.defaultInstance().uncheckedSimpleType(stdKeyDeserializer.getKeyClass()), stdKeyDeserializer);
    }

    public static HashMap<JavaType, KeyDeserializer> constructAll() {
        return new StdKeyDeserializers()._keyDeserializers;
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver) {
        return new StdKeyDeserializer.EnumKD(enumResolver, null);
    }

    public static KeyDeserializer constructStringKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        return StdKeyDeserializer.StringKD.forType(javaType.getClass());
    }

    public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(javaType);
        Constructor<?> findSingleArgConstructor = basicBeanDescription.findSingleArgConstructor(String.class);
        if (findSingleArgConstructor != null) {
            if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                ClassUtil.checkAndFixAccess(findSingleArgConstructor);
            }
            return new StdKeyDeserializer.StringCtorKeyDeserializer(findSingleArgConstructor);
        }
        Method findFactoryMethod = basicBeanDescription.findFactoryMethod(String.class);
        if (findFactoryMethod == null) {
            return null;
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            ClassUtil.checkAndFixAccess(findFactoryMethod);
        }
        return new StdKeyDeserializer.StringFactoryKeyDeserializer(findFactoryMethod);
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
        return new StdKeyDeserializer.EnumKD(enumResolver, annotatedMethod);
    }
}
