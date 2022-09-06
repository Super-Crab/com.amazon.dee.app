package com.fasterxml.jackson.databind.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes2.dex */
public class TypeFactory implements Serializable {
    private static final long serialVersionUID = 1;
    protected final ClassLoader _classLoader;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LookupCache<Object, JavaType> _typeCache;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_JSON_NODE = JsonNode.class;
    private static final Class<?> CLS_BOOL = Boolean.TYPE;
    private static final Class<?> CLS_INT = Integer.TYPE;
    private static final Class<?> CLS_LONG = Long.TYPE;
    protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
    protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
    protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
    protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
    protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
    protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
    protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
    protected static final SimpleType CORE_TYPE_CLASS = new SimpleType(CLS_CLASS);
    protected static final SimpleType CORE_TYPE_JSON_NODE = new SimpleType(CLS_JSON_NODE);

    private TypeFactory() {
        this((LookupCache<Object, JavaType>) null);
    }

    private TypeBindings _bindingsForSubtype(JavaType javaType, int i, Class<?> cls, boolean z) {
        PlaceholderForType[] placeholderForTypeArr = new PlaceholderForType[i];
        for (int i2 = 0; i2 < i; i2++) {
            placeholderForTypeArr[i2] = new PlaceholderForType(i2);
        }
        JavaType findSuperType = _fromClass(null, cls, TypeBindings.create(cls, placeholderForTypeArr)).findSuperType(javaType.getRawClass());
        if (findSuperType != null) {
            String _resolveTypePlaceholders = _resolveTypePlaceholders(javaType, findSuperType);
            if (_resolveTypePlaceholders != null && !z) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to specialize base type ");
                outline107.append(javaType.toCanonical());
                outline107.append(" as ");
                outline107.append(cls.getName());
                outline107.append(", problem: ");
                outline107.append(_resolveTypePlaceholders);
                throw new IllegalArgumentException(outline107.toString());
            }
            JavaType[] javaTypeArr = new JavaType[i];
            for (int i3 = 0; i3 < i; i3++) {
                JavaType actualType = placeholderForTypeArr[i3].actualType();
                if (actualType == null) {
                    actualType = unknownType();
                }
                javaTypeArr[i3] = actualType;
            }
            return TypeBindings.create(cls, javaTypeArr);
        }
        throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", javaType.getRawClass().getName(), cls.getName()));
    }

    private JavaType _collectionType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType2;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType2 = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType2 = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Strange Collection type "), ": cannot determine type parameters"));
        }
        return CollectionType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2);
    }

    private JavaType _mapType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType _unknownType;
        JavaType javaType2;
        JavaType javaType3;
        if (cls == Properties.class) {
            _unknownType = CORE_TYPE_STRING;
        } else {
            List<JavaType> typeParameters = typeBindings.getTypeParameters();
            int size = typeParameters.size();
            if (size != 0) {
                if (size != 2) {
                    Object[] objArr = new Object[4];
                    objArr[0] = ClassUtil.nameOf(cls);
                    objArr[1] = Integer.valueOf(size);
                    objArr[2] = size == 1 ? "" : "s";
                    objArr[3] = typeBindings;
                    throw new IllegalArgumentException(String.format("Strange Map type %s with %d type parameter%s (%s), can not resolve", objArr));
                }
                javaType2 = typeParameters.get(1);
                javaType3 = typeParameters.get(0);
                return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
            }
            _unknownType = _unknownType();
        }
        javaType3 = _unknownType;
        javaType2 = javaType3;
        return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
    }

    private JavaType _referenceType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType2;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType2 = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType2 = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Strange Reference type "), ": cannot determine type parameters"));
        }
        return ReferenceType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2);
    }

    private String _resolveTypePlaceholders(JavaType javaType, JavaType javaType2) throws IllegalArgumentException {
        List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        int size = typeParameters2.size();
        int size2 = typeParameters.size();
        int i = 0;
        while (i < size2) {
            JavaType javaType3 = typeParameters.get(i);
            JavaType unknownType = i < size ? typeParameters2.get(i) : unknownType();
            if (!_verifyAndResolvePlaceholders(javaType3, unknownType) && !javaType3.hasRawClass(Object.class) && ((i != 0 || !javaType.isMapLikeType() || !unknownType.hasRawClass(Object.class)) && (!javaType3.isInterface() || !javaType3.isTypeOrSuperTypeOf(unknownType.getRawClass())))) {
                return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", Integer.valueOf(i + 1), Integer.valueOf(size2), javaType3.toCanonical(), unknownType.toCanonical());
            }
            i++;
        }
        return null;
    }

    private boolean _verifyAndResolvePlaceholders(JavaType javaType, JavaType javaType2) {
        if (javaType2 instanceof PlaceholderForType) {
            ((PlaceholderForType) javaType2).actualType(javaType);
            return true;
        } else if (javaType.getRawClass() != javaType2.getRawClass()) {
            return false;
        } else {
            List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
            List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
            int size = typeParameters.size();
            for (int i = 0; i < size; i++) {
                if (!_verifyAndResolvePlaceholders(typeParameters.get(i), typeParameters2.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public static Class<?> rawClass(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return defaultInstance().constructType(type).getRawClass();
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    protected JavaType _applyModifiers(Type type, JavaType javaType) {
        if (this._modifiers == null) {
            return javaType;
        }
        TypeBindings bindings = javaType.getBindings();
        if (bindings == null) {
            bindings = EMPTY_BINDINGS;
        }
        TypeModifier[] typeModifierArr = this._modifiers;
        int length = typeModifierArr.length;
        JavaType javaType2 = javaType;
        int i = 0;
        while (i < length) {
            TypeModifier typeModifier = typeModifierArr[i];
            JavaType modifyType = typeModifier.modifyType(javaType2, type, bindings, this);
            if (modifyType == null) {
                throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", typeModifier, TypeModifier.class.getName(), javaType2));
            }
            i++;
            javaType2 = modifyType;
        }
        return javaType2;
    }

    protected JavaType _constructSimple(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType _findWellKnownSimple;
        return (!typeBindings.isEmpty() || (_findWellKnownSimple = _findWellKnownSimple(cls)) == null) ? _newSimpleType(cls, typeBindings, javaType, javaTypeArr) : _findWellKnownSimple;
    }

    protected Class<?> _findPrimitive(String str) {
        if ("int".equals(str)) {
            return Integer.TYPE;
        }
        if ("long".equals(str)) {
            return Long.TYPE;
        }
        if ("float".equals(str)) {
            return Float.TYPE;
        }
        if ("double".equals(str)) {
            return Double.TYPE;
        }
        if ("boolean".equals(str)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(str)) {
            return Byte.TYPE;
        }
        if ("char".equals(str)) {
            return Character.TYPE;
        }
        if ("short".equals(str)) {
            return Short.TYPE;
        }
        if (!"void".equals(str)) {
            return null;
        }
        return Void.TYPE;
    }

    protected JavaType _findWellKnownSimple(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (cls == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (cls != CLS_LONG) {
                return null;
            }
            return CORE_TYPE_LONG;
        } else if (cls == CLS_STRING) {
            return CORE_TYPE_STRING;
        } else {
            if (cls == CLS_OBJECT) {
                return CORE_TYPE_OBJECT;
            }
            if (cls != CLS_JSON_NODE) {
                return null;
            }
            return CORE_TYPE_JSON_NODE;
        }
    }

    protected JavaType _fromAny(ClassStack classStack, Type type, TypeBindings typeBindings) {
        JavaType _fromWildcard;
        if (type instanceof Class) {
            _fromWildcard = _fromClass(classStack, (Class) type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            _fromWildcard = _fromParamType(classStack, (ParameterizedType) type, typeBindings);
        } else if (type instanceof JavaType) {
            return (JavaType) type;
        } else {
            if (type instanceof GenericArrayType) {
                _fromWildcard = _fromArrayType(classStack, (GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                _fromWildcard = _fromVariable(classStack, (TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                _fromWildcard = _fromWildcard(classStack, (WildcardType) type, typeBindings);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unrecognized Type: ");
                outline107.append(type == null ? "[null]" : type.toString());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        return _applyModifiers(type, _fromWildcard);
    }

    protected JavaType _fromArrayType(ClassStack classStack, GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_fromAny(classStack, genericArrayType.getGenericComponentType(), typeBindings), typeBindings);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.fasterxml.jackson.databind.JavaType _fromClass(com.fasterxml.jackson.databind.type.ClassStack r13, java.lang.Class<?> r14, com.fasterxml.jackson.databind.type.TypeBindings r15) {
        /*
            r12 = this;
            com.fasterxml.jackson.databind.JavaType r0 = r12._findWellKnownSimple(r14)
            if (r0 == 0) goto L7
            return r0
        L7:
            if (r15 == 0) goto L15
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L10
            goto L15
        L10:
            java.lang.Object r0 = r15.asKey(r14)
            goto L16
        L15:
            r0 = r14
        L16:
            com.fasterxml.jackson.databind.util.LookupCache<java.lang.Object, com.fasterxml.jackson.databind.JavaType> r1 = r12._typeCache
            java.lang.Object r1 = r1.get(r0)
            com.fasterxml.jackson.databind.JavaType r1 = (com.fasterxml.jackson.databind.JavaType) r1
            if (r1 == 0) goto L21
            return r1
        L21:
            if (r13 != 0) goto L29
            com.fasterxml.jackson.databind.type.ClassStack r13 = new com.fasterxml.jackson.databind.type.ClassStack
            r13.<init>(r14)
            goto L3e
        L29:
            com.fasterxml.jackson.databind.type.ClassStack r2 = r13.find(r14)
            if (r2 == 0) goto L3a
            com.fasterxml.jackson.databind.type.ResolvedRecursiveType r13 = new com.fasterxml.jackson.databind.type.ResolvedRecursiveType
            com.fasterxml.jackson.databind.type.TypeBindings r15 = com.fasterxml.jackson.databind.type.TypeFactory.EMPTY_BINDINGS
            r13.<init>(r14, r15)
            r2.addSelfReference(r13)
            return r13
        L3a:
            com.fasterxml.jackson.databind.type.ClassStack r13 = r13.child(r14)
        L3e:
            boolean r2 = r14.isArray()
            if (r2 == 0) goto L53
            java.lang.Class r14 = r14.getComponentType()
            com.fasterxml.jackson.databind.JavaType r14 = r12._fromAny(r13, r14, r15)
            com.fasterxml.jackson.databind.type.ArrayType r14 = com.fasterxml.jackson.databind.type.ArrayType.construct(r14, r15)
        L50:
            r1 = r14
            goto L9e
        L53:
            boolean r2 = r14.isInterface()
            if (r2 == 0) goto L5f
            r2 = 0
            com.fasterxml.jackson.databind.JavaType[] r3 = r12._resolveSuperInterfaces(r13, r14, r15)
            goto L67
        L5f:
            com.fasterxml.jackson.databind.JavaType r2 = r12._resolveSuperClass(r13, r14, r15)
            com.fasterxml.jackson.databind.JavaType[] r3 = r12._resolveSuperInterfaces(r13, r14, r15)
        L67:
            r10 = r2
            r11 = r3
            java.lang.Class<java.util.Properties> r2 = java.util.Properties.class
            if (r14 != r2) goto L79
            com.fasterxml.jackson.databind.type.SimpleType r9 = com.fasterxml.jackson.databind.type.TypeFactory.CORE_TYPE_STRING
            r4 = r14
            r5 = r15
            r6 = r10
            r7 = r11
            r8 = r9
            com.fasterxml.jackson.databind.type.MapType r1 = com.fasterxml.jackson.databind.type.MapType.construct(r4, r5, r6, r7, r8, r9)
            goto L7f
        L79:
            if (r10 == 0) goto L7f
            com.fasterxml.jackson.databind.JavaType r1 = r10.refine(r14, r15, r10, r11)
        L7f:
            if (r1 != 0) goto L9e
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r10
            r7 = r11
            com.fasterxml.jackson.databind.JavaType r1 = r2._fromWellKnownClass(r3, r4, r5, r6, r7)
            if (r1 != 0) goto L9e
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r10
            r7 = r11
            com.fasterxml.jackson.databind.JavaType r1 = r2._fromWellKnownInterface(r3, r4, r5, r6, r7)
            if (r1 != 0) goto L9e
            com.fasterxml.jackson.databind.JavaType r14 = r12._newSimpleType(r14, r15, r10, r11)
            goto L50
        L9e:
            r13.resolveSelfReferences(r1)
            boolean r13 = r1.hasHandlers()
            if (r13 != 0) goto Lac
            com.fasterxml.jackson.databind.util.LookupCache<java.lang.Object, com.fasterxml.jackson.databind.JavaType> r13 = r12._typeCache
            r13.putIfAbsent(r0, r1)
        Lac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.TypeFactory._fromClass(com.fasterxml.jackson.databind.type.ClassStack, java.lang.Class, com.fasterxml.jackson.databind.type.TypeBindings):com.fasterxml.jackson.databind.JavaType");
    }

    protected JavaType _fromParamType(ClassStack classStack, ParameterizedType parameterizedType, TypeBindings typeBindings) {
        TypeBindings create;
        Class<?> cls = (Class) parameterizedType.getRawType();
        if (cls == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (cls == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        if (cls == CLS_CLASS) {
            return CORE_TYPE_CLASS;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            create = EMPTY_BINDINGS;
        } else {
            JavaType[] javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _fromAny(classStack, actualTypeArguments[i], typeBindings);
            }
            create = TypeBindings.create(cls, javaTypeArr);
        }
        return _fromClass(classStack, cls, create);
    }

    protected JavaType _fromVariable(ClassStack classStack, TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        Type[] bounds;
        String name = typeVariable.getName();
        if (typeBindings != null) {
            JavaType findBoundType = typeBindings.findBoundType(name);
            if (findBoundType != null) {
                return findBoundType;
            }
            if (typeBindings.hasUnbound(name)) {
                return CORE_TYPE_OBJECT;
            }
            TypeBindings withUnboundVariable = typeBindings.withUnboundVariable(name);
            synchronized (typeVariable) {
                bounds = typeVariable.getBounds();
            }
            return _fromAny(classStack, bounds[0], withUnboundVariable);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Null `bindings` passed (type variable \"", name, "\")"));
    }

    protected JavaType _fromWellKnownClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        if (typeBindings == null) {
            typeBindings = EMPTY_BINDINGS;
        }
        if (cls == Map.class) {
            return _mapType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls == Collection.class) {
            return _collectionType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls != AtomicReference.class) {
            return null;
        }
        return _referenceType(cls, typeBindings, javaType, javaTypeArr);
    }

    protected JavaType _fromWellKnownInterface(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        for (JavaType javaType2 : javaTypeArr) {
            JavaType refine = javaType2.refine(cls, typeBindings, javaType, javaTypeArr);
            if (refine != null) {
                return refine;
            }
        }
        return null;
    }

    protected JavaType _fromWildcard(ClassStack classStack, WildcardType wildcardType, TypeBindings typeBindings) {
        return _fromAny(classStack, wildcardType.getUpperBounds()[0], typeBindings);
    }

    protected JavaType _newSimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new SimpleType(cls, typeBindings, javaType, javaTypeArr);
    }

    protected JavaType _resolveSuperClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type genericSuperclass = ClassUtil.getGenericSuperclass(cls);
        if (genericSuperclass == null) {
            return null;
        }
        return _fromAny(classStack, genericSuperclass, typeBindings);
    }

    protected JavaType[] _resolveSuperInterfaces(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type[] genericInterfaces = ClassUtil.getGenericInterfaces(cls);
        if (genericInterfaces != null && genericInterfaces.length != 0) {
            int length = genericInterfaces.length;
            JavaType[] javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _fromAny(classStack, genericInterfaces[i], typeBindings);
            }
            return javaTypeArr;
        }
        return NO_TYPES;
    }

    protected JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    protected Class<?> classForName(String str, boolean z, ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(str, true, classLoader);
    }

    public void clearCache() {
        this._typeCache.clear();
    }

    public ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_fromAny(null, cls, null), null);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return constructCollectionLikeType(cls, _fromClass(null, cls2, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return constructCollectionType(cls, _fromClass(null, cls2, EMPTY_BINDINGS));
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public JavaType constructGeneralizedType(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        JavaType findSuperType = javaType.findSuperType(cls);
        if (findSuperType != null) {
            return findSuperType;
        }
        if (!cls.isAssignableFrom(rawClass)) {
            throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", cls.getName(), javaType));
        }
        throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", cls.getName(), javaType));
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        return constructMapLikeType(cls, _fromClass(null, cls2, EMPTY_BINDINGS), _fromClass(null, cls3, EMPTY_BINDINGS));
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        JavaType _fromClass;
        JavaType _fromClass2;
        if (cls == Properties.class) {
            _fromClass = CORE_TYPE_STRING;
            _fromClass2 = _fromClass;
        } else {
            _fromClass = _fromClass(null, cls2, EMPTY_BINDINGS);
            _fromClass2 = _fromClass(null, cls3, EMPTY_BINDINGS);
        }
        return constructMapType(cls, _fromClass, _fromClass2);
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        int length = clsArr.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromClass(null, clsArr[i], EMPTY_BINDINGS);
        }
        return constructParametricType(cls, javaTypeArr);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> cls, Class<?> cls2, JavaType... javaTypeArr) {
        return constructParametricType(cls, javaTypeArr);
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> cls) {
        return constructCollectionLikeType(cls, unknownType());
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> cls) {
        return constructCollectionType(cls, unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> cls) {
        return constructMapLikeType(cls, unknownType(), unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> cls) {
        return constructMapType(cls, unknownType(), unknownType());
    }

    public JavaType constructReferenceType(Class<?> cls, JavaType javaType) {
        return ReferenceType.construct(cls, TypeBindings.create(cls, javaType), null, null, javaType);
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        return _fromClass(null, cls, TypeBindings.create(cls, javaTypeArr));
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return constructSpecializedType(javaType, cls, false);
    }

    public JavaType constructType(Type type) {
        return _fromAny(null, type, EMPTY_BINDINGS);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> _findPrimitive;
        if (str.indexOf(46) >= 0 || (_findPrimitive = _findPrimitive(str)) == null) {
            Throwable th = null;
            ClassLoader classLoader = getClassLoader();
            if (classLoader == null) {
                classLoader = Thread.currentThread().getContextClassLoader();
            }
            if (classLoader != null) {
                try {
                    return classForName(str, true, classLoader);
                } catch (Exception e) {
                    th = ClassUtil.getRootCause(e);
                }
            }
            try {
                return classForName(str);
            } catch (Exception e2) {
                if (th == null) {
                    th = ClassUtil.getRootCause(e2);
                }
                ClassUtil.throwIfRTE(th);
                throw new ClassNotFoundException(th.getMessage(), th);
            }
        }
        return _findPrimitive;
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        JavaType findSuperType = javaType.findSuperType(cls);
        if (findSuperType == null) {
            return NO_TYPES;
        }
        return findSuperType.getBindings().typeParameterArray();
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    public JavaType moreSpecificType(JavaType javaType, JavaType javaType2) {
        Class<?> rawClass;
        Class<?> rawClass2;
        return javaType == null ? javaType2 : (javaType2 == null || (rawClass = javaType.getRawClass()) == (rawClass2 = javaType2.getRawClass()) || !rawClass.isAssignableFrom(rawClass2)) ? javaType : javaType2;
    }

    public JavaType resolveMemberType(Type type, TypeBindings typeBindings) {
        return _fromAny(null, type, typeBindings);
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return _constructSimple(cls, EMPTY_BINDINGS, null, null);
    }

    @Deprecated
    public TypeFactory withCache(LRUMap<Object, JavaType> lRUMap) {
        return new TypeFactory(lRUMap, this._parser, this._modifiers, this._classLoader);
    }

    public TypeFactory withClassLoader(ClassLoader classLoader) {
        return new TypeFactory(this._typeCache, this._parser, this._modifiers, classLoader);
    }

    public TypeFactory withModifier(TypeModifier typeModifier) {
        TypeModifier[] typeModifierArr;
        LookupCache<Object, JavaType> lookupCache = this._typeCache;
        LookupCache<Object, JavaType> lookupCache2 = null;
        if (typeModifier == null) {
            typeModifierArr = null;
        } else {
            TypeModifier[] typeModifierArr2 = this._modifiers;
            if (typeModifierArr2 == null) {
                typeModifierArr = new TypeModifier[]{typeModifier};
            } else {
                lookupCache2 = lookupCache;
                typeModifierArr = (TypeModifier[]) ArrayBuilders.insertInListNoDup(typeModifierArr2, typeModifier);
            }
        }
        return new TypeFactory(lookupCache2, this._parser, typeModifierArr, this._classLoader);
    }

    @Deprecated
    protected TypeFactory(LRUMap<Object, JavaType> lRUMap) {
        this((LookupCache<Object, JavaType>) lRUMap);
    }

    protected Class<?> classForName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    public ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType, null);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> cls, Class<?> cls2, Class<?>... clsArr) {
        return constructParametricType(cls, clsArr);
    }

    @Deprecated
    public JavaType constructSimpleType(Class<?> cls, Class<?> cls2, JavaType[] javaTypeArr) {
        return constructSimpleType(cls, javaTypeArr);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls, boolean z) throws IllegalArgumentException {
        JavaType _fromClass;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        if (rawClass == Object.class) {
            _fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
        } else if (rawClass.isAssignableFrom(cls)) {
            if (javaType.isContainerType()) {
                if (javaType.isMapLikeType()) {
                    if (cls == HashMap.class || cls == LinkedHashMap.class || cls == EnumMap.class || cls == TreeMap.class) {
                        _fromClass = _fromClass(null, cls, TypeBindings.create(cls, javaType.mo7229getKeyType(), javaType.mo7243getContentType()));
                    }
                } else if (javaType.isCollectionLikeType()) {
                    if (cls != ArrayList.class && cls != LinkedList.class && cls != HashSet.class && cls != TreeSet.class) {
                        if (rawClass == EnumSet.class) {
                            return javaType;
                        }
                    } else {
                        _fromClass = _fromClass(null, cls, TypeBindings.create(cls, javaType.mo7243getContentType()));
                    }
                }
            }
            if (javaType.getBindings().isEmpty()) {
                _fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
            } else {
                int length = cls.getTypeParameters().length;
                if (length == 0) {
                    _fromClass = _fromClass(null, cls, EMPTY_BINDINGS);
                } else {
                    _fromClass = _fromClass(null, cls, _bindingsForSubtype(javaType, length, cls, z));
                }
            }
        } else {
            throw new IllegalArgumentException(String.format("Class %s not subtype of %s", ClassUtil.nameOf(cls), ClassUtil.getTypeDescription(javaType)));
        }
        return _fromClass.withHandlersFrom(javaType);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _fromAny(null, typeReference.getType(), EMPTY_BINDINGS);
    }

    public TypeFactory withCache(LookupCache<Object, JavaType> lookupCache) {
        return new TypeFactory(lookupCache, this._parser, this._modifiers, this._classLoader);
    }

    protected TypeFactory(LookupCache<Object, JavaType> lookupCache) {
        this._typeCache = lookupCache == null ? new LRUMap<>(16, 200) : lookupCache;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    @Deprecated
    public JavaType constructType(Type type, TypeBindings typeBindings) {
        if (type instanceof Class) {
            return _applyModifiers(type, _fromClass(null, (Class) type, typeBindings));
        }
        return _fromAny(null, type, typeBindings);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        JavaType _fromClass = _fromClass(null, cls, TypeBindings.createIfNeeded(cls, javaType));
        if (_fromClass instanceof CollectionLikeType) {
            return (CollectionLikeType) _fromClass;
        }
        return CollectionLikeType.upgradeFrom(_fromClass, javaType);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        TypeBindings createIfNeeded = TypeBindings.createIfNeeded(cls, javaType);
        CollectionType collectionType = (CollectionType) _fromClass(null, cls, createIfNeeded);
        if (createIfNeeded.isEmpty() && javaType != null) {
            JavaType mo7243getContentType = collectionType.findSuperType(Collection.class).mo7243getContentType();
            if (!mo7243getContentType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", ClassUtil.nameOf(cls), javaType, mo7243getContentType));
            }
        }
        return collectionType;
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        return findTypeParameters(constructType(cls, typeBindings), cls2);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        JavaType _fromClass = _fromClass(null, cls, TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2}));
        if (_fromClass instanceof MapLikeType) {
            return (MapLikeType) _fromClass;
        }
        return MapLikeType.upgradeFrom(_fromClass, javaType, javaType2);
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        return constructParametricType(cls, TypeBindings.create(cls, javaTypeArr));
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(constructType(cls), cls2);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        TypeBindings createIfNeeded = TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2});
        MapType mapType = (MapType) _fromClass(null, cls, createIfNeeded);
        if (createIfNeeded.isEmpty()) {
            JavaType findSuperType = mapType.findSuperType(Map.class);
            JavaType mo7229getKeyType = findSuperType.mo7229getKeyType();
            if (mo7229getKeyType.equals(javaType)) {
                JavaType mo7243getContentType = findSuperType.mo7243getContentType();
                if (!mo7243getContentType.equals(javaType2)) {
                    throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", ClassUtil.nameOf(cls), javaType2, mo7243getContentType));
                }
            } else {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", ClassUtil.nameOf(cls), javaType, mo7229getKeyType));
            }
        }
        return mapType;
    }

    public JavaType constructParametricType(Class<?> cls, TypeBindings typeBindings) {
        return _applyModifiers(cls, _fromClass(null, cls, typeBindings));
    }

    @Deprecated
    public JavaType constructType(Type type, Class<?> cls) {
        return constructType(type, cls == null ? null : constructType(cls));
    }

    @Deprecated
    protected TypeFactory(LRUMap<Object, JavaType> lRUMap, TypeParser typeParser, TypeModifier[] typeModifierArr, ClassLoader classLoader) {
        this((LookupCache<Object, JavaType>) lRUMap, typeParser, typeModifierArr, classLoader);
    }

    @Deprecated
    public JavaType constructType(Type type, JavaType javaType) {
        TypeBindings typeBindings;
        if (javaType == null) {
            typeBindings = EMPTY_BINDINGS;
        } else {
            TypeBindings bindings = javaType.getBindings();
            if (type.getClass() != Class.class) {
                JavaType javaType2 = javaType;
                typeBindings = bindings;
                while (typeBindings.isEmpty() && (javaType2 = javaType2.getSuperClass()) != null) {
                    typeBindings = javaType2.getBindings();
                }
            } else {
                typeBindings = bindings;
            }
        }
        return _fromAny(null, type, typeBindings);
    }

    protected TypeFactory(LookupCache<Object, JavaType> lookupCache, TypeParser typeParser, TypeModifier[] typeModifierArr, ClassLoader classLoader) {
        this._typeCache = lookupCache == null ? new LRUMap<>(16, 200) : lookupCache;
        this._parser = typeParser.withFactory(this);
        this._modifiers = typeModifierArr;
        this._classLoader = classLoader;
    }
}
