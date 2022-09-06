package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected AnnotatedMethod _anyGetterMethod;
    protected AnnotatedMethod _anySetterMethod;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;
    protected Set<String> _ignoredPropertyNames;
    protected Set<String> _ignoredPropertyNamesForDeser;
    protected Map<Object, AnnotatedMember> _injectables;
    protected AnnotatedMethod _jsonValueMethod;
    protected final List<BeanPropertyDefinition> _properties;

    @Deprecated
    public BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        this(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector.getConfig(), pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef(), pOJOPropertiesCollector.getProperties());
        basicBeanDescription._anySetterMethod = pOJOPropertiesCollector.getAnySetterMethod();
        basicBeanDescription._ignoredPropertyNames = pOJOPropertiesCollector.getIgnoredPropertyNames();
        basicBeanDescription._ignoredPropertyNamesForDeser = pOJOPropertiesCollector.getIgnoredPropertyNamesForDeser();
        basicBeanDescription._injectables = pOJOPropertiesCollector.getInjectables();
        return basicBeanDescription;
    }

    public static BasicBeanDescription forOtherUse(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        return new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector.getConfig(), pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef(), pOJOPropertiesCollector.getProperties());
        basicBeanDescription._jsonValueMethod = pOJOPropertiesCollector.getJsonValueMethod();
        basicBeanDescription._anyGetterMethod = pOJOPropertiesCollector.getAnyGetterMethod();
        return basicBeanDescription;
    }

    public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> collection, boolean z) {
        LinkedHashMap<String, AnnotatedField> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedField field = beanPropertyDefinition.getField();
            if (field != null) {
                String name = beanPropertyDefinition.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, field);
                }
            }
        }
        return linkedHashMap;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public TypeBindings bindingsForBeanType() {
        if (this._bindings == null) {
            this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
        }
        return this._bindings;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findAnyGetter() throws IllegalArgumentException {
        AnnotatedMethod annotatedMethod = this._anyGetterMethod;
        if (annotatedMethod != null && !Map.class.isAssignableFrom(annotatedMethod.getRawType())) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid 'any-getter' annotation on method ");
            outline107.append(this._anyGetterMethod.getName());
            outline107.append("(): return type is not instance of java.util.Map");
            throw new IllegalArgumentException(outline107.toString());
        }
        return this._anyGetterMethod;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findAnySetter() throws IllegalArgumentException {
        Class<?> parameterClass;
        AnnotatedMethod annotatedMethod = this._anySetterMethod;
        if (annotatedMethod != null && (parameterClass = annotatedMethod.getParameterClass(0)) != String.class && parameterClass != Object.class) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid 'any-setter' annotation on method ");
            outline107.append(this._anySetterMethod.getName());
            outline107.append("(): first argument not of type String or Object, but ");
            outline107.append(parameterClass.getName());
            throw new IllegalArgumentException(outline107.toString());
        }
        return this._anySetterMethod;
    }

    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        AnnotationIntrospector.ReferenceProperty findReferenceType;
        HashMap hashMap = null;
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMember mutator = beanPropertyDefinition.getMutator();
            if (mutator != null && (findReferenceType = this._annotationIntrospector.findReferenceType(mutator)) != null && findReferenceType.isBackReference()) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String name = findReferenceType.getName();
                if (hashMap.put(name, mutator) != null) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Multiple back-reference properties with name '", name, "'"));
                }
            }
        }
        return hashMap;
    }

    public List<String> findCreatorPropertyNames() {
        String findPropertyNameForParam;
        ArrayList arrayList = null;
        int i = 0;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1 && (findPropertyNameForParam = this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(0))) != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(findPropertyNameForParam);
                    for (int i2 = 1; i2 < parameterCount; i2++) {
                        arrayList.add(this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(i2)));
                    }
                }
            }
            i++;
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap<String, AnnotatedField> findDeserializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        return _findPropertyFields(collection, false);
    }

    public Method findFactoryMethod(Class<?>... clsArr) {
        for (AnnotatedMethod annotatedMethod : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(annotatedMethod)) {
                Class<?> parameterClass = annotatedMethod.getParameterClass(0);
                for (Class<?> cls : clsArr) {
                    if (parameterClass.isAssignableFrom(cls)) {
                        return annotatedMethod.mo4213getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        LinkedHashMap<String, AnnotatedMethod> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMethod getter = beanPropertyDefinition.getGetter();
            if (getter != null) {
                String name = beanPropertyDefinition.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, getter);
                }
            }
        }
        return linkedHashMap;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public Map<Object, AnnotatedMember> findInjectables() {
        return this._injectables;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findJsonValueMethod() {
        return this._jsonValueMethod;
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public List<BeanPropertyDefinition> findProperties() {
        return this._properties;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    /* renamed from: findSerializableFields  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Map mo4216findSerializableFields(VisibilityChecker visibilityChecker, Collection collection) {
        return mo4216findSerializableFields((VisibilityChecker<?>) visibilityChecker, (Collection<String>) collection);
    }

    public JsonSerialize.Inclusion findSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        return annotationIntrospector == null ? inclusion : annotationIntrospector.findSerializationInclusion(this._classInfo, inclusion);
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> visibilityChecker) {
        LinkedHashMap<String, AnnotatedMethod> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMethod setter = beanPropertyDefinition.getSetter();
            if (setter != null) {
                linkedHashMap.put(beanPropertyDefinition.getName(), setter);
            }
        }
        return linkedHashMap;
    }

    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        for (AnnotatedConstructor annotatedConstructor : this._classInfo.getConstructors()) {
            if (annotatedConstructor.getParameterCount() == 1) {
                Class<?> parameterClass = annotatedConstructor.getParameterClass(0);
                for (Class<?> cls : clsArr) {
                    if (cls == parameterClass) {
                        return annotatedConstructor.mo4213getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedMethod annotatedMethod : staticMethods) {
            if (isFactoryMethod(annotatedMethod)) {
                arrayList.add(annotatedMethod);
            }
        }
        return arrayList;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public Set<String> getIgnoredPropertyNames() {
        Set<String> set = this._ignoredPropertyNames;
        return set == null ? Collections.emptySet() : set;
    }

    public Set<String> getIgnoredPropertyNamesForDeser() {
        return this._ignoredPropertyNamesForDeser;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Object instantiateBean(boolean z) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z) {
            defaultConstructor.fixAccess();
        }
        try {
            return defaultConstructor.mo4213getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if (!(e instanceof Error)) {
                if (e instanceof RuntimeException) {
                    throw ((RuntimeException) e);
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to instantiate bean of type ");
                outline107.append(this._classInfo.mo4213getAnnotated().getName());
                outline107.append(": (");
                outline107.append(e.getClass().getName());
                outline107.append(") ");
                outline107.append(e.getMessage());
                throw new IllegalArgumentException(outline107.toString(), e);
            }
            throw ((Error) e);
        }
    }

    protected boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        if (!getBeanClass().isAssignableFrom(annotatedMethod.getRawType())) {
            return false;
        }
        return this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod) || "valueOf".equals(annotatedMethod.getName());
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    public JavaType resolveType(Type type) {
        if (type == null) {
            return null;
        }
        return bindingsForBeanType().resolveType(type);
    }

    protected BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List<BeanPropertyDefinition> list) {
        super(javaType);
        this._config = mapperConfig;
        this._annotationIntrospector = mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector();
        this._classInfo = annotatedClass;
        this._properties = list;
    }

    @Override // com.amazon.org.codehaus.jackson.map.BeanDescription
    /* renamed from: findSerializableFields */
    public LinkedHashMap<String, AnnotatedField> mo4216findSerializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        return _findPropertyFields(collection, true);
    }
}
