package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.amazon.org.codehaus.jackson.map.MapperConfig;
import com.amazon.org.codehaus.jackson.map.PropertyNamingStrategy;
import com.amazon.org.codehaus.jackson.map.util.BeanUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes13.dex */
public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig<?> _config;
    protected final boolean _forSerialization;
    protected Set<String> _ignoredPropertyNames;
    protected Set<String> _ignoredPropertyNamesForDeser;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;
    protected final LinkedHashMap<String, POJOPropertyBuilder> _properties = new LinkedHashMap<>();
    protected LinkedList<POJOPropertyBuilder> _creatorProperties = null;
    protected LinkedList<AnnotatedMethod> _anyGetters = null;
    protected LinkedList<AnnotatedMethod> _anySetters = null;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass) {
        AnnotationIntrospector annotationIntrospector = null;
        this._config = mapperConfig;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        this._annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : annotationIntrospector;
        AnnotationIntrospector annotationIntrospector2 = this._annotationIntrospector;
        if (annotationIntrospector2 == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = annotationIntrospector2.findAutoDetectVisibility(annotatedClass, this._config.getDefaultVisibilityChecker());
        }
    }

    private void _addIgnored(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._forSerialization) {
            return;
        }
        String name = pOJOPropertyBuilder.getName();
        this._ignoredPropertyNames = addToSet(this._ignoredPropertyNames, name);
        if (!pOJOPropertyBuilder.anyDeserializeIgnorals()) {
            return;
        }
        this._ignoredPropertyNamesForDeser = addToSet(this._ignoredPropertyNamesForDeser, name);
    }

    private Set<String> addToSet(Set<String> set, String str) {
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(str);
        return set;
    }

    protected void _addCreators() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return;
        }
        for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList<>();
            }
            int parameterCount = annotatedConstructor.getParameterCount();
            for (int i = 0; i < parameterCount; i++) {
                AnnotatedParameter parameter = annotatedConstructor.getParameter(i);
                String findPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
                if (findPropertyNameForParam != null) {
                    POJOPropertyBuilder _property = _property(findPropertyNameForParam);
                    _property.addCtor(parameter, findPropertyNameForParam, true, false);
                    this._creatorProperties.add(_property);
                }
            }
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.getStaticMethods()) {
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList<>();
            }
            int parameterCount2 = annotatedMethod.getParameterCount();
            for (int i2 = 0; i2 < parameterCount2; i2++) {
                AnnotatedParameter parameter2 = annotatedMethod.getParameter(i2);
                String findPropertyNameForParam2 = annotationIntrospector.findPropertyNameForParam(parameter2);
                if (findPropertyNameForParam2 != null) {
                    POJOPropertyBuilder _property2 = _property(findPropertyNameForParam2);
                    _property2.addCtor(parameter2, findPropertyNameForParam2, true, false);
                    this._creatorProperties.add(_property2);
                }
            }
        }
    }

    protected void _addFields() {
        String findDeserializablePropertyName;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            String name = annotatedField.getName();
            if (annotationIntrospector == null) {
                findDeserializablePropertyName = null;
            } else if (this._forSerialization) {
                findDeserializablePropertyName = annotationIntrospector.findSerializablePropertyName(annotatedField);
            } else {
                findDeserializablePropertyName = annotationIntrospector.findDeserializablePropertyName(annotatedField);
            }
            if ("".equals(findDeserializablePropertyName)) {
                findDeserializablePropertyName = name;
            }
            boolean z = true;
            boolean z2 = findDeserializablePropertyName != null;
            if (!z2) {
                z2 = this._visibilityChecker.isFieldVisible(annotatedField);
            }
            if (annotationIntrospector == null || !annotationIntrospector.hasIgnoreMarker(annotatedField)) {
                z = false;
            }
            _property(name).addField(annotatedField, findDeserializablePropertyName, z2, z);
        }
    }

    protected void _addInjectables() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return;
        }
        for (AnnotatedMember annotatedMember : this._classDef.fields()) {
            _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMember), annotatedMember);
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            if (annotatedMethod.getParameterCount() == 1) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod);
            }
        }
    }

    protected void _addMethods() {
        String okNameForGetter;
        String okNameForSetter;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            boolean z = false;
            String str = null;
            boolean z2 = true;
            if (parameterCount == 0) {
                if (annotationIntrospector != null) {
                    if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
                        if (this._anyGetters == null) {
                            this._anyGetters = new LinkedList<>();
                        }
                        this._anyGetters.add(annotatedMethod);
                    } else if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
                        if (this._jsonValueGetters == null) {
                            this._jsonValueGetters = new LinkedList<>();
                        }
                        this._jsonValueGetters.add(annotatedMethod);
                    }
                }
                if (annotationIntrospector != null) {
                    str = annotationIntrospector.findGettablePropertyName(annotatedMethod);
                }
                if (str == null) {
                    okNameForGetter = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
                    if (okNameForGetter == null) {
                        okNameForGetter = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                        if (okNameForGetter != null) {
                            z2 = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                        }
                    } else {
                        z2 = this._visibilityChecker.isGetterVisible(annotatedMethod);
                    }
                } else {
                    okNameForGetter = BeanUtil.okNameForGetter(annotatedMethod);
                    if (okNameForGetter == null) {
                        okNameForGetter = annotatedMethod.getName();
                    }
                    if (str.length() == 0) {
                        str = okNameForGetter;
                    }
                }
                if (annotationIntrospector != null) {
                    z = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
                }
                _property(okNameForGetter).addGetter(annotatedMethod, str, z2, z);
            } else if (parameterCount == 1) {
                if (annotationIntrospector != null) {
                    str = annotationIntrospector.findSettablePropertyName(annotatedMethod);
                }
                if (str == null) {
                    okNameForSetter = BeanUtil.okNameForSetter(annotatedMethod);
                    if (okNameForSetter != null) {
                        z2 = this._visibilityChecker.isSetterVisible(annotatedMethod);
                    }
                } else {
                    okNameForSetter = BeanUtil.okNameForSetter(annotatedMethod);
                    if (okNameForSetter == null) {
                        okNameForSetter = annotatedMethod.getName();
                    }
                    if (str.length() == 0) {
                        str = okNameForSetter;
                    }
                }
                if (annotationIntrospector != null) {
                    z = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
                }
                _property(okNameForSetter).addSetter(annotatedMethod, str, z2, z);
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    protected void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        if (obj == null) {
            return;
        }
        if (this._injectables == null) {
            this._injectables = new LinkedHashMap<>();
        }
        if (this._injectables.put(obj, annotatedMember) == null) {
            return;
        }
        String name = obj.getClass().getName();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Duplicate injectable value with id '");
        outline107.append(String.valueOf(obj));
        outline107.append("' (of type ");
        outline107.append(name);
        outline107.append(")");
        throw new IllegalArgumentException(outline107.toString());
    }

    protected POJOPropertyBuilder _property(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = this._properties.get(str);
        if (pOJOPropertyBuilder == null) {
            POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(str);
            this._properties.put(str, pOJOPropertyBuilder2);
            return pOJOPropertyBuilder2;
        }
        return pOJOPropertyBuilder;
    }

    protected void _removeUnwantedProperties() {
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it2 = this._properties.entrySet().iterator();
        while (it2.hasNext()) {
            POJOPropertyBuilder value = it2.next().getValue();
            if (!value.anyVisible()) {
                it2.remove();
            } else {
                if (value.anyIgnorals()) {
                    _addIgnored(value);
                    if (!value.anyExplicitNames()) {
                        it2.remove();
                    } else {
                        value.removeIgnored();
                    }
                }
                value.removeNonVisible();
            }
        }
    }

    protected void _renameProperties() {
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it2 = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it2.hasNext()) {
            POJOPropertyBuilder value = it2.next().getValue();
            String findNewName = value.findNewName();
            if (findNewName != null) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(value.withName(findNewName));
                it2.remove();
            }
        }
        if (linkedList != null) {
            Iterator it3 = linkedList.iterator();
            while (it3.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it3.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    this._properties.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
            }
        }
    }

    protected void _renameUsing(PropertyNamingStrategy propertyNamingStrategy) {
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) this._properties.values().toArray(new POJOPropertyBuilder[this._properties.size()]);
        this._properties.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            String name = pOJOPropertyBuilder.getName();
            if (this._forSerialization) {
                if (pOJOPropertyBuilder.hasGetter()) {
                    name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                } else if (pOJOPropertyBuilder.hasField()) {
                    name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
                }
            } else if (pOJOPropertyBuilder.hasSetter()) {
                name = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), name);
            } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                name = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), name);
            } else if (pOJOPropertyBuilder.hasField()) {
                name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
            } else if (pOJOPropertyBuilder.hasGetter()) {
                name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
            }
            if (!name.equals(pOJOPropertyBuilder.getName())) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.withName(name);
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = this._properties.get(name);
            if (pOJOPropertyBuilder2 == null) {
                this._properties.put(name, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
        }
    }

    protected void _sortProperties() {
        boolean booleanValue;
        Map linkedHashMap;
        AnnotationIntrospector annotationIntrospector = this._config.getAnnotationIntrospector();
        Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        if (findSerializationSortAlphabetically == null) {
            booleanValue = this._config.shouldSortPropertiesAlphabetically();
        } else {
            booleanValue = findSerializationSortAlphabetically.booleanValue();
        }
        String[] findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        if (!booleanValue && this._creatorProperties == null && findSerializationPropertyOrder == null) {
            return;
        }
        int size = this._properties.size();
        if (booleanValue) {
            linkedHashMap = new TreeMap();
        } else {
            linkedHashMap = new LinkedHashMap(size + size);
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
            linkedHashMap.put(pOJOPropertyBuilder.getName(), pOJOPropertyBuilder);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(size + size);
        if (findSerializationPropertyOrder != null) {
            for (String str : findSerializationPropertyOrder) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) linkedHashMap.get(str);
                if (pOJOPropertyBuilder2 == null) {
                    Iterator<POJOPropertyBuilder> it2 = this._properties.values().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        POJOPropertyBuilder next = it2.next();
                        if (str.equals(next.getInternalName())) {
                            str = next.getName();
                            pOJOPropertyBuilder2 = next;
                            break;
                        }
                    }
                }
                if (pOJOPropertyBuilder2 != null) {
                    linkedHashMap2.put(str, pOJOPropertyBuilder2);
                }
            }
        }
        LinkedList<POJOPropertyBuilder> linkedList = this._creatorProperties;
        if (linkedList != null) {
            Iterator<POJOPropertyBuilder> it3 = linkedList.iterator();
            while (it3.hasNext()) {
                POJOPropertyBuilder next2 = it3.next();
                linkedHashMap2.put(next2.getName(), next2);
            }
        }
        linkedHashMap2.putAll(linkedHashMap);
        this._properties.clear();
        this._properties.putAll(linkedHashMap2);
    }

    public POJOPropertiesCollector collect() {
        this._properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
        if (propertyNamingStrategy != null) {
            _renameUsing(propertyNamingStrategy);
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
            pOJOPropertyBuilder.trimByVisibility();
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder2 : this._properties.values()) {
            pOJOPropertyBuilder2.mergeAnnotations(this._forSerialization);
        }
        _sortProperties();
        return this;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public AnnotatedMethod getAnyGetterMethod() {
        LinkedList<AnnotatedMethod> linkedList = this._anyGetters;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple 'any-getters' defined (");
                outline107.append(this._anyGetters.get(0));
                outline107.append(" vs ");
                outline107.append(this._anyGetters.get(1));
                outline107.append(")");
                reportProblem(outline107.toString());
            }
            return this._anyGetters.getFirst();
        }
        return null;
    }

    public AnnotatedMethod getAnySetterMethod() {
        LinkedList<AnnotatedMethod> linkedList = this._anySetters;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple 'any-setters' defined (");
                outline107.append(this._anySetters.get(0));
                outline107.append(" vs ");
                outline107.append(this._anySetters.get(1));
                outline107.append(")");
                reportProblem(outline107.toString());
            }
            return this._anySetters.getFirst();
        }
        return null;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public Set<String> getIgnoredPropertyNamesForDeser() {
        return this._ignoredPropertyNamesForDeser;
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        LinkedList<AnnotatedMethod> linkedList = this._jsonValueGetters;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Multiple value properties defined (");
                outline107.append(this._jsonValueGetters.get(0));
                outline107.append(" vs ");
                outline107.append(this._jsonValueGetters.get(1));
                outline107.append(")");
                reportProblem(outline107.toString());
            }
            return this._jsonValueGetters.get(0);
        }
        return null;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(this._properties.values());
    }

    protected Map<String, POJOPropertyBuilder> getPropertyMap() {
        return this._properties;
    }

    public JavaType getType() {
        return this._type;
    }

    protected void reportProblem(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Problem with definition of ");
        outline107.append(this._classDef);
        outline107.append(RealTimeTextConstants.COLON_SPACE);
        outline107.append(str);
        throw new IllegalArgumentException(outline107.toString());
    }
}
