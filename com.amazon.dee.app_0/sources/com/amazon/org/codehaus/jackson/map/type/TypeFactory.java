package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.map.util.ArrayBuilders;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.amazon.org.codehaus.jackson.type.TypeReference;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public final class TypeFactory {
    protected HierarchicType _cachedArrayListType;
    protected HierarchicType _cachedHashMapType;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    @Deprecated
    public static final TypeFactory instance = new TypeFactory();
    private static final JavaType[] NO_TYPES = new JavaType[0];

    private TypeFactory() {
        this._parser = new TypeParser(this);
        this._modifiers = null;
    }

    private JavaType _collectionType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters(cls, Collection.class);
        if (findTypeParameters == null) {
            return CollectionType.construct(cls, _unknownType());
        }
        if (findTypeParameters.length == 1) {
            return CollectionType.construct(cls, findTypeParameters[0]);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Strange Collection type "), ": can not determine type parameters"));
    }

    private JavaType _mapType(Class<?> cls) {
        JavaType[] findTypeParameters = findTypeParameters(cls, Map.class);
        if (findTypeParameters == null) {
            return MapType.construct(cls, _unknownType(), _unknownType());
        }
        if (findTypeParameters.length == 2) {
            return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Strange Map type "), ": can not determine type parameters"));
    }

    @Deprecated
    public static JavaType arrayType(Class<?> cls) {
        TypeFactory typeFactory = instance;
        return typeFactory.constructArrayType(typeFactory.constructType(cls));
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, Class<?> cls2) {
        TypeFactory typeFactory = instance;
        return typeFactory.constructCollectionType(cls, typeFactory.constructType(cls2));
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    @Deprecated
    public static JavaType fastSimpleType(Class<?> cls) {
        return instance.uncheckedSimpleType(cls);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2) {
        return instance.findTypeParameters(cls, cls2);
    }

    public static JavaType fromCanonical(String str) throws IllegalArgumentException {
        return instance.constructFromCanonical(str);
    }

    @Deprecated
    public static JavaType fromClass(Class<?> cls) {
        return instance._fromClass(cls, null);
    }

    @Deprecated
    public static JavaType fromType(Type type) {
        return instance._constructType(type, null);
    }

    @Deprecated
    public static JavaType fromTypeReference(TypeReference<?> typeReference) {
        return type(typeReference.getType());
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return instance.constructMapType(cls, type(cls2), instance.constructType(cls3));
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, Class<?>... clsArr) {
        return instance.constructParametricType(cls, clsArr);
    }

    public static Class<?> rawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return defaultInstance().constructType(type).getRawClass();
    }

    @Deprecated
    public static JavaType specialize(JavaType javaType, Class<?> cls) {
        return instance.constructSpecializedType(javaType, cls);
    }

    @Deprecated
    public static JavaType type(Type type) {
        return instance._constructType(type, null);
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    protected synchronized HierarchicType _arrayListSuperInterfaceChain(HierarchicType hierarchicType) {
        if (this._cachedArrayListType == null) {
            HierarchicType deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, List.class);
            this._cachedArrayListType = deepCloneWithoutSubtype.getSuperType();
        }
        HierarchicType deepCloneWithoutSubtype2 = this._cachedArrayListType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype2);
        deepCloneWithoutSubtype2.setSubType(hierarchicType);
        return hierarchicType;
    }

    public JavaType _constructType(Type type, TypeBindings typeBindings) {
        JavaType _fromWildcard;
        if (type instanceof Class) {
            Class<?> cls = (Class) type;
            if (typeBindings == null) {
                typeBindings = new TypeBindings(this, cls);
            }
            _fromWildcard = _fromClass(cls, typeBindings);
        } else if (type instanceof ParameterizedType) {
            _fromWildcard = _fromParamType((ParameterizedType) type, typeBindings);
        } else if (type instanceof GenericArrayType) {
            _fromWildcard = _fromArrayType((GenericArrayType) type, typeBindings);
        } else if (type instanceof TypeVariable) {
            _fromWildcard = _fromVariable((TypeVariable) type, typeBindings);
        } else if (type instanceof WildcardType) {
            _fromWildcard = _fromWildcard((WildcardType) type, typeBindings);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized Type: ");
            outline107.append(type.toString());
            throw new IllegalArgumentException(outline107.toString());
        }
        if (this._modifiers != null && !_fromWildcard.isContainerType()) {
            for (TypeModifier typeModifier : this._modifiers) {
                _fromWildcard = typeModifier.modifyType(_fromWildcard, type, typeBindings, this);
            }
        }
        return _fromWildcard;
    }

    protected HierarchicType _doFindSuperInterfaceChain(HierarchicType hierarchicType, Class<?> cls) {
        HierarchicType _findSuperInterfaceChain;
        Class<?> rawClass = hierarchicType.getRawClass();
        Type[] genericInterfaces = rawClass.getGenericInterfaces();
        if (genericInterfaces != null) {
            for (Type type : genericInterfaces) {
                HierarchicType _findSuperInterfaceChain2 = _findSuperInterfaceChain(type, cls);
                if (_findSuperInterfaceChain2 != null) {
                    _findSuperInterfaceChain2.setSubType(hierarchicType);
                    hierarchicType.setSuperType(_findSuperInterfaceChain2);
                    return hierarchicType;
                }
            }
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperInterfaceChain = _findSuperInterfaceChain(genericSuperclass, cls)) == null) {
            return null;
        }
        _findSuperInterfaceChain.setSubType(hierarchicType);
        hierarchicType.setSuperType(_findSuperInterfaceChain);
        return hierarchicType;
    }

    protected HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType _findSuperClassChain;
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return hierarchicType;
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperClassChain = _findSuperClassChain(genericSuperclass, cls)) == null) {
            return null;
        }
        _findSuperClassChain.setSubType(hierarchicType);
        hierarchicType.setSuperType(_findSuperClassChain);
        return hierarchicType;
    }

    protected HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType = new HierarchicType(type);
        Class<?> rawClass = hierarchicType.getRawClass();
        if (rawClass == cls) {
            return new HierarchicType(type);
        }
        if (rawClass == HashMap.class && cls == Map.class) {
            return _hashMapSuperInterfaceChain(hierarchicType);
        }
        if (rawClass == ArrayList.class && cls == List.class) {
            return _arrayListSuperInterfaceChain(hierarchicType);
        }
        return _doFindSuperInterfaceChain(hierarchicType, cls);
    }

    protected HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        if (cls2.isInterface()) {
            return _findSuperInterfaceChain(cls, cls2);
        }
        return _findSuperClassChain(cls, cls2);
    }

    protected JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings), null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return _mapType(cls);
        }
        if (Collection.class.isAssignableFrom(cls)) {
            return _collectionType(cls);
        }
        return new SimpleType(cls);
    }

    protected JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        JavaType[] javaTypeArr;
        Class<?> cls = (Class) parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            JavaType[] javaTypeArr2 = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr2[i] = _constructType(actualTypeArguments[i], typeBindings);
            }
            javaTypeArr = javaTypeArr2;
        }
        if (Map.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters = findTypeParameters(constructSimpleType(cls, javaTypeArr), Map.class);
            if (findTypeParameters.length == 2) {
                return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not find 2 type parameters for Map class ");
            GeneratedOutlineSupport1.outline146(cls, outline107, " (found ");
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(outline107, findTypeParameters.length, ")"));
        } else if (Collection.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters2 = findTypeParameters(constructSimpleType(cls, javaTypeArr), Collection.class);
            if (findTypeParameters2.length == 1) {
                return CollectionType.construct(cls, findTypeParameters2[0]);
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Could not find 1 type parameter for Collection class ");
            GeneratedOutlineSupport1.outline146(cls, outline1072, " (found ");
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(outline1072, findTypeParameters2.length, ")"));
        } else {
            if (length == 0) {
                return new SimpleType(cls);
            }
            return constructSimpleType(cls, javaTypeArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        if (cls.isArray()) {
            return ArrayType.construct(_constructType(cls.getComponentType(), null), null, null);
        }
        if (cls.isEnum()) {
            return new SimpleType(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            if (list.size() > 0) {
                return MapType.construct(cls, list.get(0), list.size() >= 2 ? list.get(1) : _unknownType());
            }
            return _mapType(cls);
        } else if (Collection.class.isAssignableFrom(cls)) {
            if (list.size() >= 1) {
                return CollectionType.construct(cls, list.get(0));
            }
            return _collectionType(cls);
        } else if (list.size() == 0) {
            return new SimpleType(cls);
        } else {
            return constructSimpleType(cls, (JavaType[]) list.toArray(new JavaType[list.size()]));
        }
    }

    protected JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        if (typeBindings == null) {
            return _unknownType();
        }
        String name = typeVariable.getName();
        JavaType findType = typeBindings.findType(name);
        if (findType != null) {
            return findType;
        }
        Type[] bounds = typeVariable.getBounds();
        typeBindings._addPlaceholder(name);
        return _constructType(bounds[0], typeBindings);
    }

    protected JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    protected synchronized HierarchicType _hashMapSuperInterfaceChain(HierarchicType hierarchicType) {
        if (this._cachedHashMapType == null) {
            HierarchicType deepCloneWithoutSubtype = hierarchicType.deepCloneWithoutSubtype();
            _doFindSuperInterfaceChain(deepCloneWithoutSubtype, Map.class);
            this._cachedHashMapType = deepCloneWithoutSubtype.getSuperType();
        }
        HierarchicType deepCloneWithoutSubtype2 = this._cachedHashMapType.deepCloneWithoutSubtype();
        hierarchicType.setSuperType(deepCloneWithoutSubtype2);
        deepCloneWithoutSubtype2.setSubType(hierarchicType);
        return hierarchicType;
    }

    protected JavaType _resolveVariableViaSubTypes(HierarchicType hierarchicType, String str, TypeBindings typeBindings) {
        if (hierarchicType != null && hierarchicType.isGeneric()) {
            TypeVariable<Class<?>>[] typeParameters = hierarchicType.getRawClass().getTypeParameters();
            int length = typeParameters.length;
            for (int i = 0; i < length; i++) {
                if (str.equals(typeParameters[i].getName())) {
                    Type type = hierarchicType.asGeneric().getActualTypeArguments()[i];
                    if (type instanceof TypeVariable) {
                        return _resolveVariableViaSubTypes(hierarchicType.getSubType(), ((TypeVariable) type).getName(), typeBindings);
                    }
                    return _constructType(type, typeBindings);
                }
            }
        }
        return _unknownType();
    }

    protected JavaType _unknownType() {
        return new SimpleType(Object.class);
    }

    public ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_constructType(cls, null), null, null);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return CollectionLikeType.construct(cls, constructType(cls2));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return CollectionType.construct((Class<?>) cls, constructType(cls2));
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return MapLikeType.construct(cls, javaType, javaType2);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return MapType.construct((Class<?>) cls, javaType, javaType2);
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        int length = clsArr.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromClass(clsArr[i], null);
        }
        return constructParametricType(cls, javaTypeArr);
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> cls) {
        return CollectionLikeType.construct(cls, unknownType());
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> cls) {
        return CollectionType.construct((Class<?>) cls, unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> cls) {
        return MapLikeType.construct(cls, unknownType(), unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> cls) {
        return MapType.construct((Class<?>) cls, unknownType(), unknownType());
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length == javaTypeArr.length) {
            String[] strArr = new String[typeParameters.length];
            int length = typeParameters.length;
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
            return new SimpleType(cls, strArr, javaTypeArr, null, null);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parameter type mismatch for ");
        GeneratedOutlineSupport1.outline146(cls, outline107, ": expected ");
        outline107.append(typeParameters.length);
        outline107.append(" parameters, was given ");
        outline107.append(javaTypeArr.length);
        throw new IllegalArgumentException(outline107.toString());
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        if ((javaType instanceof SimpleType) && (cls.isArray() || Map.class.isAssignableFrom(cls) || Collection.class.isAssignableFrom(cls))) {
            if (javaType.getRawClass().isAssignableFrom(cls)) {
                JavaType _fromClass = _fromClass(cls, new TypeBindings(this, javaType.getRawClass()));
                Object valueHandler = javaType.getValueHandler();
                if (valueHandler != null) {
                    _fromClass = _fromClass.mo4250withValueHandler(valueHandler);
                }
                Object typeHandler = javaType.getTypeHandler();
                return typeHandler != null ? _fromClass.mo4249withTypeHandler(typeHandler) : _fromClass;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
            outline107.append(cls.getClass().getName());
            outline107.append(" not subtype of ");
            outline107.append(javaType);
            throw new IllegalArgumentException(outline107.toString());
        }
        return javaType.narrowBy(cls);
    }

    public JavaType constructType(Type type) {
        return _constructType(type, null);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            int containedTypeCount = javaType.containedTypeCount();
            if (containedTypeCount == 0) {
                return null;
            }
            JavaType[] javaTypeArr = new JavaType[containedTypeCount];
            for (int i = 0; i < containedTypeCount; i++) {
                javaTypeArr[i] = javaType.containedType(i);
            }
            return javaTypeArr;
        }
        return findTypeParameters(rawClass, cls, new TypeBindings(this, javaType));
    }

    public JavaType uncheckedSimpleType(Class<?> cls) {
        return new SimpleType(cls);
    }

    public TypeFactory withModifier(TypeModifier typeModifier) {
        TypeModifier[] typeModifierArr = this._modifiers;
        if (typeModifierArr == null) {
            return new TypeFactory(this._parser, new TypeModifier[]{typeModifier});
        }
        return new TypeFactory(this._parser, (TypeModifier[]) ArrayBuilders.insertInListNoDup(typeModifierArr, typeModifier));
    }

    @Deprecated
    public static JavaType arrayType(JavaType javaType) {
        return instance.constructArrayType(javaType);
    }

    @Deprecated
    public static JavaType collectionType(Class<? extends Collection> cls, JavaType javaType) {
        return instance.constructCollectionType(cls, javaType);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        return instance.findTypeParameters(cls, cls2, typeBindings);
    }

    @Deprecated
    public static JavaType mapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return instance.constructMapType(cls, javaType, javaType2);
    }

    @Deprecated
    public static JavaType parametricType(Class<?> cls, JavaType... javaTypeArr) {
        return instance.constructParametricType(cls, javaTypeArr);
    }

    @Deprecated
    public static JavaType type(Type type, Class<?> cls) {
        return instance.constructType(type, cls);
    }

    public ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType, null, null);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        return CollectionLikeType.construct(cls, javaType);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        return CollectionType.construct((Class<?>) cls, javaType);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType(cls2), constructType(cls3));
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct((Class<?>) cls, constructType(cls2), constructType(cls3));
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    @Deprecated
    public static JavaType[] findParameterTypes(JavaType javaType, Class<?> cls) {
        return instance.findTypeParameters(javaType, cls);
    }

    @Deprecated
    public static JavaType type(Type type, JavaType javaType) {
        return instance.constructType(type, javaType);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _constructType(typeReference.getType(), null);
    }

    protected TypeFactory(TypeParser typeParser, TypeModifier[] typeModifierArr) {
        this._parser = typeParser;
        this._modifiers = typeModifierArr;
    }

    @Deprecated
    public static JavaType type(Type type, TypeBindings typeBindings) {
        return instance._constructType(type, typeBindings);
    }

    public JavaType constructType(Type type, Class<?> cls) {
        return _constructType(type, cls == null ? null : new TypeBindings(this, cls));
    }

    @Deprecated
    public static JavaType type(TypeReference<?> typeReference) {
        return instance.constructType(typeReference.getType());
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        if (cls.isArray()) {
            if (javaTypeArr.length == 1) {
                return constructArrayType(javaTypeArr[0]);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Need exactly 1 parameter type for arrays ("), ")"));
        } else if (Map.class.isAssignableFrom(cls)) {
            if (javaTypeArr.length == 2) {
                return constructMapType((Class<? extends Map>) cls, javaTypeArr[0], javaTypeArr[1]);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Need exactly 2 parameter types for Map types ("), ")"));
        } else if (Collection.class.isAssignableFrom(cls)) {
            if (javaTypeArr.length == 1) {
                return constructCollectionType((Class<? extends Collection>) cls, javaTypeArr[0]);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Need exactly 1 parameter type for Collection types ("), ")"));
        } else {
            return constructSimpleType(cls, javaTypeArr);
        }
    }

    public JavaType constructType(Type type, JavaType javaType) {
        return _constructType(type, javaType == null ? null : new TypeBindings(this, javaType));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(cls, cls2, new TypeBindings(this, cls));
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        HierarchicType _findSuperTypeChain = _findSuperTypeChain(cls, cls2);
        if (_findSuperTypeChain != null) {
            while (_findSuperTypeChain.getSuperType() != null) {
                _findSuperTypeChain = _findSuperTypeChain.getSuperType();
                Class<?> rawClass = _findSuperTypeChain.getRawClass();
                TypeBindings typeBindings2 = new TypeBindings(this, rawClass);
                if (_findSuperTypeChain.isGeneric()) {
                    Type[] actualTypeArguments = _findSuperTypeChain.asGeneric().getActualTypeArguments();
                    TypeVariable<Class<?>>[] typeParameters = rawClass.getTypeParameters();
                    int length = actualTypeArguments.length;
                    for (int i = 0; i < length; i++) {
                        typeBindings2.addBinding(typeParameters[i].getName(), instance._constructType(actualTypeArguments[i], typeBindings));
                    }
                }
                typeBindings = typeBindings2;
            }
            if (_findSuperTypeChain.isGeneric()) {
                return typeBindings.typesAsArray();
            }
            return null;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
        outline107.append(cls.getName());
        outline107.append(" is not a subtype of ");
        outline107.append(cls2.getName());
        throw new IllegalArgumentException(outline107.toString());
    }
}
