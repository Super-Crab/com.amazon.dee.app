package com.amazon.org.codehaus.jackson.map.util;

import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public class RootNameLookup {
    protected LRUMap<ClassKey, SerializedString> _rootNames;

    public SerializedString findRootName(JavaType javaType, MapperConfig<?> mapperConfig) {
        return findRootName(javaType.getRawClass(), mapperConfig);
    }

    public synchronized SerializedString findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        ClassKey classKey = new ClassKey(cls);
        if (this._rootNames == null) {
            this._rootNames = new LRUMap<>(20, 200);
        } else {
            SerializedString serializedString = this._rootNames.get(classKey);
            if (serializedString != null) {
                return serializedString;
            }
        }
        String findRootName = mapperConfig.getAnnotationIntrospector().findRootName(((BasicBeanDescription) mapperConfig.introspectClassAnnotations(cls)).getClassInfo());
        if (findRootName == null) {
            findRootName = cls.getSimpleName();
        }
        SerializedString serializedString2 = new SerializedString(findRootName);
        this._rootNames.put(classKey, serializedString2);
        return serializedString2;
    }
}
