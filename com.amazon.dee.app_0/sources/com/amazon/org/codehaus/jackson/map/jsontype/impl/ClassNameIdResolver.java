package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumMap;
import java.util.EnumSet;
/* loaded from: classes13.dex */
public class ClassNameIdResolver extends TypeIdResolverBase {
    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    protected final String _idFrom(Object obj, Class<?> cls) {
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        if (!name.startsWith("java.util")) {
            return (name.indexOf(36) < 0 || ClassUtil.getOuterClass(cls) == null || ClassUtil.getOuterClass(this._baseType.getRawClass()) != null) ? name : this._baseType.getRawClass().getName();
        } else if (obj instanceof EnumSet) {
            return TypeFactory.defaultInstance().constructCollectionType(EnumSet.class, ClassUtil.findEnumType((EnumSet) obj)).toCanonical();
        } else if (obj instanceof EnumMap) {
            return TypeFactory.defaultInstance().constructMapType(EnumMap.class, ClassUtil.findEnumType((EnumMap) obj), Object.class).toCanonical();
        } else {
            String substring = name.substring(9);
            return ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) ? "java.util.ArrayList" : name;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CLASS;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValue(Object obj) {
        return _idFrom(obj, obj.getClass());
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls);
    }

    public void registerSubtype(Class<?> cls, String str) {
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JavaType typeFromId(String str) {
        if (str.indexOf(60) > 0) {
            return TypeFactory.fromCanonical(str);
        }
        try {
            return this._typeFactory.constructSpecializedType(this._baseType, ClassUtil.findClass(str));
        } catch (ClassNotFoundException unused) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid type id '", str, "' (for id type 'Id.class'): no such class found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline115("Invalid type id '", str, "' (for id type 'Id.class'): ")), e);
        }
    }
}
