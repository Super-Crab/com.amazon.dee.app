package org.apache.commons.lang3.reflect;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;
/* loaded from: classes4.dex */
public class TypeUtils {
    public static final WildcardType WILDCARD_ALL = wildcardType().withUpperBounds(Object.class).mo12838build();

    /* loaded from: classes4.dex */
    private static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public boolean equals(Object obj) {
            return obj == this || ((obj instanceof GenericArrayType) && TypeUtils.equals((GenericArrayType) this, (Type) ((GenericArrayType) obj)));
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode() | PhotoshopDirectory.TAG_LAYER_GROUPS_ENABLED_ID;
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        private GenericArrayTypeImpl(Type type) {
            this.componentType = type;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class ParameterizedTypeImpl implements ParameterizedType {
        private final Class<?> raw;
        private final Type[] typeArguments;
        private final Type useOwner;

        public boolean equals(Object obj) {
            return obj == this || ((obj instanceof ParameterizedType) && TypeUtils.equals((ParameterizedType) this, (Type) ((ParameterizedType) obj)));
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.useOwner;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.raw;
        }

        public int hashCode() {
            return ((((this.raw.hashCode() | 1136) << 4) | ObjectUtils.hashCode(this.useOwner)) << 8) | Arrays.hashCode(this.typeArguments);
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        private ParameterizedTypeImpl(Class<?> cls, Type type, Type[] typeArr) {
            this.raw = cls;
            this.useOwner = type;
            this.typeArguments = typeArr;
        }
    }

    /* loaded from: classes4.dex */
    public static class WildcardTypeBuilder implements Builder<WildcardType> {
        private Type[] lowerBounds;
        private Type[] upperBounds;

        public WildcardTypeBuilder withLowerBounds(Type... typeArr) {
            this.lowerBounds = typeArr;
            return this;
        }

        public WildcardTypeBuilder withUpperBounds(Type... typeArr) {
            this.upperBounds = typeArr;
            return this;
        }

        private WildcardTypeBuilder() {
        }

        @Override // org.apache.commons.lang3.builder.Builder
        /* renamed from: build  reason: collision with other method in class */
        public WildcardType mo12838build() {
            return new WildcardTypeImpl(this.upperBounds, this.lowerBounds);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class WildcardTypeImpl implements WildcardType {
        private static final Type[] EMPTY_BOUNDS = new Type[0];
        private final Type[] lowerBounds;
        private final Type[] upperBounds;

        public boolean equals(Object obj) {
            return obj == this || ((obj instanceof WildcardType) && TypeUtils.equals((WildcardType) this, (Type) ((WildcardType) obj)));
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            return (Type[]) this.lowerBounds.clone();
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return (Type[]) this.upperBounds.clone();
        }

        public int hashCode() {
            return ((Arrays.hashCode(this.upperBounds) | 18688) << 8) | Arrays.hashCode(this.lowerBounds);
        }

        public String toString() {
            return TypeUtils.toString(this);
        }

        private WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            this.upperBounds = (Type[]) ObjectUtils.defaultIfNull(typeArr, EMPTY_BOUNDS);
            this.lowerBounds = (Type[]) ObjectUtils.defaultIfNull(typeArr2, EMPTY_BOUNDS);
        }
    }

    private static StringBuilder appendAllTo(StringBuilder sb, String str, Type... typeArr) {
        Validate.notEmpty(Validate.noNullElements(typeArr));
        if (typeArr.length > 0) {
            sb.append(toString(typeArr[0]));
            for (int i = 1; i < typeArr.length; i++) {
                sb.append(str);
                sb.append(toString(typeArr[i]));
            }
        }
        return sb;
    }

    private static String classToString(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        if (cls.getEnclosingClass() != null) {
            sb.append(classToString(cls.getEnclosingClass()));
            sb.append('.');
            sb.append(cls.getSimpleName());
        } else {
            sb.append(cls.getName());
        }
        if (cls.getTypeParameters().length > 0) {
            sb.append(Typography.less);
            appendAllTo(sb, ", ", cls.getTypeParameters());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    public static boolean containsTypeVariables(Type type) {
        if (type instanceof TypeVariable) {
            return true;
        }
        if (type instanceof Class) {
            return ((Class) type).getTypeParameters().length > 0;
        } else if (type instanceof ParameterizedType) {
            for (Type type2 : ((ParameterizedType) type).getActualTypeArguments()) {
                if (containsTypeVariables(type2)) {
                    return true;
                }
            }
            return false;
        } else if (!(type instanceof WildcardType)) {
            return false;
        } else {
            WildcardType wildcardType = (WildcardType) type;
            return containsTypeVariables(getImplicitLowerBounds(wildcardType)[0]) || containsTypeVariables(getImplicitUpperBounds(wildcardType)[0]);
        }
    }

    public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType parameterizedType) {
        Validate.notNull(cls, "cls is null", new Object[0]);
        Validate.notNull(parameterizedType, "superType is null", new Object[0]);
        Class<?> rawType = getRawType(parameterizedType);
        if (!isAssignable((Type) cls, rawType)) {
            return null;
        }
        if (cls.equals(rawType)) {
            return getTypeArguments(parameterizedType, rawType, (Map<TypeVariable<?>, Type>) null);
        }
        Type closestParentType = getClosestParentType(cls, rawType);
        if (closestParentType instanceof Class) {
            return determineTypeArguments((Class) closestParentType, parameterizedType);
        }
        ParameterizedType parameterizedType2 = (ParameterizedType) closestParentType;
        Map<TypeVariable<?>, Type> determineTypeArguments = determineTypeArguments(getRawType(parameterizedType2), parameterizedType);
        mapTypeVariablesToArguments(cls, parameterizedType2, determineTypeArguments);
        return determineTypeArguments;
    }

    public static boolean equals(Type type, Type type2) {
        if (ObjectUtils.equals(type, type2)) {
            return true;
        }
        if (type instanceof ParameterizedType) {
            return equals((ParameterizedType) type, type2);
        }
        if (type instanceof GenericArrayType) {
            return equals((GenericArrayType) type, type2);
        }
        if (!(type instanceof WildcardType)) {
            return false;
        }
        return equals((WildcardType) type, type2);
    }

    private static Type[] extractTypeArgumentsFrom(Map<TypeVariable<?>, Type> map, TypeVariable<?>[] typeVariableArr) {
        Type[] typeArr = new Type[typeVariableArr.length];
        int length = typeVariableArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            TypeVariable<?> typeVariable = typeVariableArr[i];
            Validate.isTrue(map.containsKey(typeVariable), "missing argument mapping for %s", toString(typeVariable));
            typeArr[i2] = map.get(typeVariable);
            i++;
            i2++;
        }
        return typeArr;
    }

    public static GenericArrayType genericArrayType(Type type) {
        return new GenericArrayTypeImpl((Type) Validate.notNull(type, "componentType is null", new Object[0]));
    }

    private static String genericArrayTypeToString(GenericArrayType genericArrayType) {
        return String.format("%s[]", toString(genericArrayType.getGenericComponentType()));
    }

    public static Type getArrayComponentType(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return null;
            }
            return cls.getComponentType();
        } else if (!(type instanceof GenericArrayType)) {
            return null;
        } else {
            return ((GenericArrayType) type).getGenericComponentType();
        }
    }

    private static Type getClosestParentType(Class<?> cls, Class<?> cls2) {
        Type[] genericInterfaces;
        Class<?> cls3;
        if (cls2.isInterface()) {
            Type type = null;
            for (Type type2 : cls.getGenericInterfaces()) {
                if (type2 instanceof ParameterizedType) {
                    cls3 = getRawType((ParameterizedType) type2);
                } else if (type2 instanceof Class) {
                    cls3 = (Class) type2;
                } else {
                    throw new IllegalStateException("Unexpected generic interface type found: " + type2);
                }
                if (isAssignable((Type) cls3, cls2) && isAssignable(type, (Type) cls3)) {
                    type = type2;
                }
            }
            if (type != null) {
                return type;
            }
        }
        return cls.getGenericSuperclass();
    }

    public static Type[] getImplicitBounds(TypeVariable<?> typeVariable) {
        Validate.notNull(typeVariable, "typeVariable is null", new Object[0]);
        Type[] bounds = typeVariable.getBounds();
        return bounds.length == 0 ? new Type[]{Object.class} : normalizeUpperBounds(bounds);
    }

    public static Type[] getImplicitLowerBounds(WildcardType wildcardType) {
        Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
        Type[] lowerBounds = wildcardType.getLowerBounds();
        return lowerBounds.length == 0 ? new Type[]{null} : lowerBounds;
    }

    public static Type[] getImplicitUpperBounds(WildcardType wildcardType) {
        Validate.notNull(wildcardType, "wildcardType is null", new Object[0]);
        Type[] upperBounds = wildcardType.getUpperBounds();
        return upperBounds.length == 0 ? new Type[]{Object.class} : normalizeUpperBounds(upperBounds);
    }

    private static Class<?> getRawType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        if (rawType instanceof Class) {
            return (Class) rawType;
        }
        throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType) {
        return getTypeArguments(parameterizedType, getRawType(parameterizedType), (Map<TypeVariable<?>, Type>) null);
    }

    public static boolean isArrayType(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static boolean isAssignable(Type type, Type type2) {
        return isAssignable(type, type2, (Map<TypeVariable<?>, Type>) null);
    }

    public static boolean isInstance(Object obj, Type type) {
        if (type == null) {
            return false;
        }
        if (obj != null) {
            return isAssignable(obj.getClass(), type, (Map<TypeVariable<?>, Type>) null);
        }
        return !(type instanceof Class) || !((Class) type).isPrimitive();
    }

    private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> map) {
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            mapTypeVariablesToArguments(cls, (ParameterizedType) ownerType, map);
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeVariable<Class<?>>[] typeParameters = getRawType(parameterizedType).getTypeParameters();
        List asList = Arrays.asList(cls.getTypeParameters());
        for (int i = 0; i < actualTypeArguments.length; i++) {
            TypeVariable<Class<?>> typeVariable = typeParameters[i];
            Type type = actualTypeArguments[i];
            if (asList.contains(type) && map.containsKey(typeVariable)) {
                map.put((TypeVariable) type, map.get(typeVariable));
            }
        }
    }

    public static Type[] normalizeUpperBounds(Type[] typeArr) {
        boolean z;
        Validate.notNull(typeArr, "null value specified for bounds array", new Object[0]);
        if (typeArr.length < 2) {
            return typeArr;
        }
        HashSet hashSet = new HashSet(typeArr.length);
        for (Type type : typeArr) {
            int length = typeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                Type type2 = typeArr[i];
                if (type != type2 && isAssignable(type2, type, (Map<TypeVariable<?>, Type>) null)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                hashSet.add(type);
            }
        }
        return (Type[]) hashSet.toArray(new Type[hashSet.size()]);
    }

    public static final ParameterizedType parameterize(Class<?> cls, Type... typeArr) {
        return parameterizeWithOwner((Type) null, cls, typeArr);
    }

    public static final ParameterizedType parameterizeWithOwner(Type type, Class<?> cls, Type... typeArr) {
        Validate.notNull(cls, "raw class is null", new Object[0]);
        if (cls.getEnclosingClass() == null) {
            Validate.isTrue(type == null, "no owner allowed for top-level %s", cls);
            type = null;
        } else if (type == null) {
            type = cls.getEnclosingClass();
        } else {
            Validate.isTrue(isAssignable(type, cls.getEnclosingClass()), "%s is invalid owner type for parameterized %s", type, cls);
        }
        Validate.noNullElements(typeArr, "null type argument at index %s", new Object[0]);
        Validate.isTrue(cls.getTypeParameters().length == typeArr.length, "invalid number of type parameters specified: expected %s, got %s", Integer.valueOf(cls.getTypeParameters().length), Integer.valueOf(typeArr.length));
        return new ParameterizedTypeImpl(cls, type, typeArr);
    }

    private static String parameterizedTypeToString(ParameterizedType parameterizedType) {
        StringBuilder sb = new StringBuilder();
        Type ownerType = parameterizedType.getOwnerType();
        Class cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (ownerType == null) {
            sb.append(cls.getName());
        } else {
            if (ownerType instanceof Class) {
                sb.append(((Class) ownerType).getName());
            } else {
                sb.append(ownerType.toString());
            }
            sb.append('.');
            sb.append(cls.getSimpleName());
        }
        sb.append(Typography.less);
        appendAllTo(sb, ", ", actualTypeArguments).append(Typography.greater);
        return sb.toString();
    }

    private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> map) {
        if (!(type instanceof TypeVariable) || map == null) {
            return type;
        }
        Type type2 = map.get(type);
        if (type2 != null) {
            return type2;
        }
        throw new IllegalArgumentException("missing assignment type for type variable " + type);
    }

    public static String toLongString(TypeVariable<?> typeVariable) {
        Validate.notNull(typeVariable, "var is null", new Object[0]);
        StringBuilder sb = new StringBuilder();
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class<?> cls = (Class) genericDeclaration;
            while (cls.getEnclosingClass() != null) {
                sb.insert(0, cls.getSimpleName()).insert(0, '.');
                cls = cls.getEnclosingClass();
            }
            sb.insert(0, cls.getName());
        } else if (genericDeclaration instanceof Type) {
            sb.append(toString((Type) genericDeclaration));
        } else {
            sb.append(genericDeclaration);
        }
        sb.append(JsonReaderKt.COLON);
        sb.append(typeVariableToString(typeVariable));
        return sb.toString();
    }

    public static String toString(Type type) {
        Validate.notNull(type);
        if (type instanceof Class) {
            return classToString((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return parameterizedTypeToString((ParameterizedType) type);
        }
        if (type instanceof WildcardType) {
            return wildcardTypeToString((WildcardType) type);
        }
        if (type instanceof TypeVariable) {
            return typeVariableToString((TypeVariable) type);
        }
        if (type instanceof GenericArrayType) {
            return genericArrayTypeToString((GenericArrayType) type);
        }
        throw new IllegalArgumentException(ObjectUtils.identityToString(type));
    }

    private static String typeVariableToString(TypeVariable<?> typeVariable) {
        StringBuilder sb = new StringBuilder(typeVariable.getName());
        Type[] bounds = typeVariable.getBounds();
        if (bounds.length > 0 && (bounds.length != 1 || !Object.class.equals(bounds[0]))) {
            sb.append(" extends ");
            appendAllTo(sb, " & ", typeVariable.getBounds());
        }
        return sb.toString();
    }

    public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> map) {
        Validate.notNull(map, "typeVarAssigns is null", new Object[0]);
        for (Map.Entry<TypeVariable<?>, Type> entry : map.entrySet()) {
            Type value = entry.getValue();
            for (Type type : getImplicitBounds(entry.getKey())) {
                if (!isAssignable(value, substituteTypeVariables(type, map), map)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Type[] unrollBounds(Map<TypeVariable<?>, Type> map, Type[] typeArr) {
        int i = 0;
        while (i < typeArr.length) {
            Type unrollVariables = unrollVariables(map, typeArr[i]);
            if (unrollVariables == null) {
                typeArr = (Type[]) ArrayUtils.remove((Object[]) typeArr, i);
                i--;
            } else {
                typeArr[i] = unrollVariables;
            }
            i++;
        }
        return typeArr;
    }

    private static Type unrollVariableAssignments(TypeVariable<?> typeVariable, Map<TypeVariable<?>, Type> map) {
        Type type;
        while (true) {
            type = map.get(typeVariable);
            if (!(type instanceof TypeVariable) || type.equals(typeVariable)) {
                break;
            }
            typeVariable = (TypeVariable) type;
        }
        return type;
    }

    public static Type unrollVariables(Map<TypeVariable<?>, Type> map, Type type) {
        if (map == null) {
            map = Collections.emptyMap();
        }
        if (containsTypeVariables(type)) {
            if (type instanceof TypeVariable) {
                return unrollVariables(map, map.get(type));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getOwnerType() != null) {
                    HashMap hashMap = new HashMap(map);
                    hashMap.putAll(getTypeArguments(parameterizedType));
                    map = hashMap;
                }
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    Type unrollVariables = unrollVariables(map, actualTypeArguments[i]);
                    if (unrollVariables != null) {
                        actualTypeArguments[i] = unrollVariables;
                    }
                }
                return parameterizeWithOwner(parameterizedType.getOwnerType(), (Class) parameterizedType.getRawType(), actualTypeArguments);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType().withUpperBounds(unrollBounds(map, wildcardType.getUpperBounds())).withLowerBounds(unrollBounds(map, wildcardType.getLowerBounds())).mo12838build();
            }
        }
        return type;
    }

    public static WildcardTypeBuilder wildcardType() {
        return new WildcardTypeBuilder();
    }

    private static String wildcardTypeToString(WildcardType wildcardType) {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(Constants.DEFAULT_IMAGE_CHAR);
        Type[] lowerBounds = wildcardType.getLowerBounds();
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (lowerBounds.length <= 1 && (lowerBounds.length != 1 || lowerBounds[0] == null)) {
            if (upperBounds.length > 1 || (upperBounds.length == 1 && !Object.class.equals(upperBounds[0]))) {
                outline104.append(" extends ");
                appendAllTo(outline104, " & ", upperBounds);
            }
        } else {
            outline104.append(" super ");
            appendAllTo(outline104, " & ", lowerBounds);
        }
        return outline104.toString();
    }

    public static <T> Typed<T> wrap(final Type type) {
        return new Typed<T>() { // from class: org.apache.commons.lang3.reflect.TypeUtils.1
            @Override // org.apache.commons.lang3.reflect.Typed
            public Type getType() {
                return type;
            }
        };
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> cls) {
        return getTypeArguments(type, cls, (Map<TypeVariable<?>, Type>) null);
    }

    private static boolean isAssignable(Type type, Type type2, Map<TypeVariable<?>, Type> map) {
        if (type2 != null && !(type2 instanceof Class)) {
            if (type2 instanceof ParameterizedType) {
                return isAssignable(type, (ParameterizedType) type2, map);
            }
            if (type2 instanceof GenericArrayType) {
                return isAssignable(type, (GenericArrayType) type2, map);
            }
            if (type2 instanceof WildcardType) {
                return isAssignable(type, (WildcardType) type2, map);
            }
            if (type2 instanceof TypeVariable) {
                return isAssignable(type, (TypeVariable<?>) type2, map);
            }
            throw new IllegalStateException("found an unhandled type: " + type2);
        }
        return isAssignable(type, (Class<?>) type2);
    }

    public static final ParameterizedType parameterize(Class<?> cls, Map<TypeVariable<?>, Type> map) {
        Validate.notNull(cls, "raw class is null", new Object[0]);
        Validate.notNull(map, "typeArgMappings is null", new Object[0]);
        return parameterizeWithOwner((Type) null, cls, extractTypeArgumentsFrom(map, cls.getTypeParameters()));
    }

    public static <T> Typed<T> wrap(Class<T> cls) {
        return wrap((Type) cls);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> cls, Map<TypeVariable<?>, Type> map) {
        if (type instanceof Class) {
            return getTypeArguments((Class<?>) type, cls, map);
        }
        if (type instanceof ParameterizedType) {
            return getTypeArguments((ParameterizedType) type, cls, map);
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (cls.isArray()) {
                cls = cls.getComponentType();
            }
            return getTypeArguments(genericComponentType, cls, map);
        }
        int i = 0;
        if (type instanceof WildcardType) {
            Type[] implicitUpperBounds = getImplicitUpperBounds((WildcardType) type);
            int length = implicitUpperBounds.length;
            while (i < length) {
                Type type2 = implicitUpperBounds[i];
                if (isAssignable(type2, cls)) {
                    return getTypeArguments(type2, cls, map);
                }
                i++;
            }
            return null;
        } else if (type instanceof TypeVariable) {
            Type[] implicitBounds = getImplicitBounds((TypeVariable) type);
            int length2 = implicitBounds.length;
            while (i < length2) {
                Type type3 = implicitBounds[i];
                if (isAssignable(type3, cls)) {
                    return getTypeArguments(type3, cls, map);
                }
                i++;
            }
            return null;
        } else {
            throw new IllegalStateException("found an unhandled type: " + type);
        }
    }

    public static Class<?> getRawType(Type type, Type type2) {
        Map<TypeVariable<?>, Type> typeArguments;
        Type type3;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawType((ParameterizedType) type);
        }
        if (type instanceof TypeVariable) {
            if (type2 == null) {
                return null;
            }
            GenericDeclaration genericDeclaration = ((TypeVariable) type).getGenericDeclaration();
            if ((genericDeclaration instanceof Class) && (typeArguments = getTypeArguments(type2, (Class) genericDeclaration)) != null && (type3 = typeArguments.get(type)) != null) {
                return getRawType(type3, type2);
            }
            return null;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType(), type2), 0).getClass();
        } else {
            if (type instanceof WildcardType) {
                return null;
            }
            throw new IllegalArgumentException("unknown type: " + type);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equals(ParameterizedType parameterizedType, Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType2 = (ParameterizedType) type;
            if (equals(parameterizedType.getRawType(), parameterizedType2.getRawType()) && equals(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType())) {
                return equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equals(GenericArrayType genericArrayType, Type type) {
        return (type instanceof GenericArrayType) && equals(genericArrayType.getGenericComponentType(), ((GenericArrayType) type).getGenericComponentType());
    }

    public static final ParameterizedType parameterizeWithOwner(Type type, Class<?> cls, Map<TypeVariable<?>, Type> map) {
        Validate.notNull(cls, "raw class is null", new Object[0]);
        Validate.notNull(map, "typeArgMappings is null", new Object[0]);
        return parameterizeWithOwner(type, cls, extractTypeArgumentsFrom(map, cls.getTypeParameters()));
    }

    private static boolean isAssignable(Type type, Class<?> cls) {
        if (type == null) {
            return cls == null || !cls.isPrimitive();
        } else if (cls == null) {
            return false;
        } else {
            if (cls.equals(type)) {
                return true;
            }
            if (type instanceof Class) {
                return ClassUtils.isAssignable((Class) type, cls);
            }
            if (type instanceof ParameterizedType) {
                return isAssignable((Type) getRawType((ParameterizedType) type), cls);
            }
            if (type instanceof TypeVariable) {
                for (Type type2 : ((TypeVariable) type).getBounds()) {
                    if (isAssignable(type2, cls)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof GenericArrayType) {
                if (cls.equals(Object.class)) {
                    return true;
                }
                return cls.isArray() && isAssignable(((GenericArrayType) type).getGenericComponentType(), cls.getComponentType());
            } else if (type instanceof WildcardType) {
                return false;
            } else {
                throw new IllegalStateException("found an unhandled type: " + type);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equals(WildcardType wildcardType, Type type) {
        if (type instanceof WildcardType) {
            WildcardType wildcardType2 = (WildcardType) type;
            return equals(getImplicitLowerBounds(wildcardType), getImplicitLowerBounds(wildcardType2)) && equals(getImplicitUpperBounds(wildcardType), getImplicitUpperBounds(wildcardType2));
        }
        return true;
    }

    private static boolean equals(Type[] typeArr, Type[] typeArr2) {
        if (typeArr.length == typeArr2.length) {
            for (int i = 0; i < typeArr.length; i++) {
                if (!equals(typeArr[i], typeArr2[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> cls, Map<TypeVariable<?>, Type> map) {
        Map<TypeVariable<?>, Type> hashMap;
        Class<?> rawType = getRawType(parameterizedType);
        if (!isAssignable((Type) rawType, cls)) {
            return null;
        }
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            ParameterizedType parameterizedType2 = (ParameterizedType) ownerType;
            hashMap = getTypeArguments(parameterizedType2, getRawType(parameterizedType2), map);
        } else {
            hashMap = map == null ? new HashMap<>() : new HashMap(map);
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeVariable<Class<?>>[] typeParameters = rawType.getTypeParameters();
        for (int i = 0; i < typeParameters.length; i++) {
            Type type = actualTypeArguments[i];
            TypeVariable<Class<?>> typeVariable = typeParameters[i];
            if (hashMap.containsKey(type)) {
                type = hashMap.get(type);
            }
            hashMap.put(typeVariable, type);
        }
        return cls.equals(rawType) ? hashMap : getTypeArguments(getClosestParentType(rawType, cls), cls, hashMap);
    }

    private static boolean isAssignable(Type type, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (parameterizedType == null) {
            return false;
        }
        if (parameterizedType.equals(type)) {
            return true;
        }
        Class<?> rawType = getRawType(parameterizedType);
        Map<TypeVariable<?>, Type> typeArguments = getTypeArguments(type, rawType, (Map<TypeVariable<?>, Type>) null);
        if (typeArguments == null) {
            return false;
        }
        if (typeArguments.isEmpty()) {
            return true;
        }
        Map<TypeVariable<?>, Type> typeArguments2 = getTypeArguments(parameterizedType, rawType, map);
        for (TypeVariable<?> typeVariable : typeArguments2.keySet()) {
            Type unrollVariableAssignments = unrollVariableAssignments(typeVariable, typeArguments2);
            Type unrollVariableAssignments2 = unrollVariableAssignments(typeVariable, typeArguments);
            if (unrollVariableAssignments2 != null && !unrollVariableAssignments.equals(unrollVariableAssignments2) && (!(unrollVariableAssignments instanceof WildcardType) || !isAssignable(unrollVariableAssignments2, unrollVariableAssignments, map))) {
                return false;
            }
        }
        return true;
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> cls2, Map<TypeVariable<?>, Type> map) {
        if (!isAssignable((Type) cls, cls2)) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (cls2.isPrimitive()) {
                return new HashMap();
            }
            cls = ClassUtils.primitiveToWrapper(cls);
        }
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        return cls2.equals(cls) ? hashMap : getTypeArguments(getClosestParentType(cls, cls2), cls2, hashMap);
    }

    private static boolean isAssignable(Type type, GenericArrayType genericArrayType, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (genericArrayType == null) {
            return false;
        }
        if (genericArrayType.equals(type)) {
            return true;
        }
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() && isAssignable(cls.getComponentType(), genericComponentType, map);
        } else if (type instanceof GenericArrayType) {
            return isAssignable(((GenericArrayType) type).getGenericComponentType(), genericComponentType, map);
        } else {
            if (type instanceof WildcardType) {
                for (Type type2 : getImplicitUpperBounds((WildcardType) type)) {
                    if (isAssignable(type2, genericArrayType)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof TypeVariable) {
                for (Type type3 : getImplicitBounds((TypeVariable) type)) {
                    if (isAssignable(type3, genericArrayType)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof ParameterizedType) {
                return false;
            } else {
                throw new IllegalStateException("found an unhandled type: " + type);
            }
        }
    }

    private static boolean isAssignable(Type type, WildcardType wildcardType, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (wildcardType == null) {
            return false;
        }
        if (wildcardType.equals(type)) {
            return true;
        }
        Type[] implicitUpperBounds = getImplicitUpperBounds(wildcardType);
        Type[] implicitLowerBounds = getImplicitLowerBounds(wildcardType);
        if (type instanceof WildcardType) {
            WildcardType wildcardType2 = (WildcardType) type;
            Type[] implicitUpperBounds2 = getImplicitUpperBounds(wildcardType2);
            Type[] implicitLowerBounds2 = getImplicitLowerBounds(wildcardType2);
            for (Type type2 : implicitUpperBounds) {
                Type substituteTypeVariables = substituteTypeVariables(type2, map);
                for (Type type3 : implicitUpperBounds2) {
                    if (!isAssignable(type3, substituteTypeVariables, map)) {
                        return false;
                    }
                }
            }
            for (Type type4 : implicitLowerBounds) {
                Type substituteTypeVariables2 = substituteTypeVariables(type4, map);
                for (Type type5 : implicitLowerBounds2) {
                    if (!isAssignable(substituteTypeVariables2, type5, map)) {
                        return false;
                    }
                }
            }
            return true;
        }
        for (Type type6 : implicitUpperBounds) {
            if (!isAssignable(type, substituteTypeVariables(type6, map), map)) {
                return false;
            }
        }
        for (Type type7 : implicitLowerBounds) {
            if (!isAssignable(substituteTypeVariables(type7, map), type, map)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAssignable(Type type, TypeVariable<?> typeVariable, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (typeVariable == null) {
            return false;
        }
        if (typeVariable.equals(type)) {
            return true;
        }
        if (type instanceof TypeVariable) {
            for (Type type2 : getImplicitBounds((TypeVariable) type)) {
                if (isAssignable(type2, typeVariable, map)) {
                    return true;
                }
            }
        }
        if ((type instanceof Class) || (type instanceof ParameterizedType) || (type instanceof GenericArrayType) || (type instanceof WildcardType)) {
            return false;
        }
        throw new IllegalStateException("found an unhandled type: " + type);
    }
}
