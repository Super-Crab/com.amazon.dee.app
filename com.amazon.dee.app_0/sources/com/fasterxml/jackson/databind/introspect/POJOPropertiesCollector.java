package com.fasterxml.jackson.databind.introspect;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes2.dex */
public class POJOPropertiesCollector {
    protected final AccessorNamingStrategy _accessorNaming;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetterField;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMember> _anySetterField;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected boolean _collected;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected Map<PropertyName, PropertyName> _fieldRenameMappings;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMember> _jsonKeyAccessors;
    protected LinkedList<AnnotatedMember> _jsonValueAccessors;
    @Deprecated
    protected String _mutatorPrefix;
    protected LinkedHashMap<String, POJOPropertyBuilder> _properties;
    @Deprecated
    protected final boolean _stdBeanNaming;
    protected final JavaType _type;
    protected final boolean _useAnnotations;
    protected final VisibilityChecker<?> _visibilityChecker;

    /* JADX INFO: Access modifiers changed from: protected */
    public POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, AccessorNamingStrategy accessorNamingStrategy) {
        this._mutatorPrefix = "set";
        this._config = mapperConfig;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        if (mapperConfig.isAnnotationProcessingEnabled()) {
            this._useAnnotations = true;
            this._annotationIntrospector = this._config.getAnnotationIntrospector();
        } else {
            this._useAnnotations = false;
            this._annotationIntrospector = AnnotationIntrospector.nopInstance();
        }
        this._visibilityChecker = this._config.getDefaultVisibilityChecker(javaType.getRawClass(), annotatedClass);
        this._accessorNaming = accessorNamingStrategy;
        this._stdBeanNaming = mapperConfig.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
    }

    private static AccessorNamingStrategy _accessorNaming(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, String str) {
        if (str == null) {
            str = "set";
        }
        return new DefaultAccessorNamingStrategy.Provider().withSetterPrefix(str).forPOJO(mapperConfig, annotatedClass);
    }

    private boolean _anyIndexed(Collection<POJOPropertyBuilder> collection) {
        for (POJOPropertyBuilder pOJOPropertyBuilder : collection) {
            if (pOJOPropertyBuilder.getMetadata().hasIndex()) {
                return true;
            }
        }
        return false;
    }

    private String _checkRenameByField(String str) {
        PropertyName propertyName;
        Map<PropertyName, PropertyName> map = this._fieldRenameMappings;
        return (map == null || (propertyName = map.get(_propNameFromSimple(str))) == null) ? str : propertyName.getSimpleName();
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        PropertyNamingStrategy namingStrategyInstance;
        Object findNamingStrategy = this._annotationIntrospector.findNamingStrategy(this._classDef);
        if (findNamingStrategy == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (findNamingStrategy instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) findNamingStrategy;
        }
        if (findNamingStrategy instanceof Class) {
            Class<?> cls = (Class) findNamingStrategy;
            if (cls == PropertyNamingStrategy.class) {
                return null;
            }
            if (PropertyNamingStrategy.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                return (handlerInstantiator == null || (namingStrategyInstance = handlerInstantiator.namingStrategyInstance(this._config, this._classDef, cls)) == null) ? (PropertyNamingStrategy) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : namingStrategyInstance;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned Class "), "; expected Class<PropertyNamingStrategy>"));
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline46(findNamingStrategy, GeneratedOutlineSupport1.outline107("AnnotationIntrospector returned PropertyNamingStrategy definition of type "), "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead"));
    }

    private PropertyName _propNameFromSimple(String str) {
        return PropertyName.construct(str, null);
    }

    protected void _addCreatorParam(Map<String, POJOPropertyBuilder> map, AnnotatedParameter annotatedParameter) {
        JsonCreator.Mode findCreatorAnnotation;
        String findImplicitPropertyName = this._annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null) {
            findImplicitPropertyName = "";
        }
        PropertyName findNameForDeserialization = this._annotationIntrospector.findNameForDeserialization(annotatedParameter);
        boolean z = findNameForDeserialization != null && !findNameForDeserialization.isEmpty();
        if (!z) {
            if (findImplicitPropertyName.isEmpty() || (findCreatorAnnotation = this._annotationIntrospector.findCreatorAnnotation(this._config, annotatedParameter.getOwner())) == null || findCreatorAnnotation == JsonCreator.Mode.DISABLED) {
                return;
            }
            findNameForDeserialization = PropertyName.construct(findImplicitPropertyName);
        }
        PropertyName propertyName = findNameForDeserialization;
        String _checkRenameByField = _checkRenameByField(findImplicitPropertyName);
        POJOPropertyBuilder _property = (!z || !_checkRenameByField.isEmpty()) ? _property(map, _checkRenameByField) : _property(map, propertyName);
        _property.addCtor(annotatedParameter, propertyName, z, true, false);
        this._creatorProperties.add(_property);
    }

    protected void _addCreators(Map<String, POJOPropertyBuilder> map) {
        if (!this._useAnnotations) {
            return;
        }
        Iterator<AnnotatedConstructor> it2 = this._classDef.getConstructors().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            AnnotatedConstructor next = it2.next();
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList<>();
            }
            int parameterCount = next.getParameterCount();
            for (int i = 0; i < parameterCount; i++) {
                _addCreatorParam(map, next.getParameter(i));
            }
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.getFactoryMethods()) {
            if (this._creatorProperties == null) {
                this._creatorProperties = new LinkedList<>();
            }
            int parameterCount2 = annotatedMethod.getParameterCount();
            for (int i2 = 0; i2 < parameterCount2; i2++) {
                _addCreatorParam(map, annotatedMethod.getParameter(i2));
            }
        }
    }

    protected void _addFields(Map<String, POJOPropertyBuilder> map) {
        PropertyName findNameForDeserialization;
        PropertyName propertyName;
        boolean z;
        boolean z2;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        boolean z3 = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        boolean isEnabled = this._config.isEnabled(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            if (Boolean.TRUE.equals(annotationIntrospector.hasAsKey(this._config, annotatedField))) {
                if (this._jsonKeyAccessors == null) {
                    this._jsonKeyAccessors = new LinkedList<>();
                }
                this._jsonKeyAccessors.add(annotatedField);
            }
            if (Boolean.TRUE.equals(annotationIntrospector.hasAsValue(annotatedField))) {
                if (this._jsonValueAccessors == null) {
                    this._jsonValueAccessors = new LinkedList<>();
                }
                this._jsonValueAccessors.add(annotatedField);
            } else {
                boolean equals = Boolean.TRUE.equals(annotationIntrospector.hasAnyGetter(annotatedField));
                boolean equals2 = Boolean.TRUE.equals(annotationIntrospector.hasAnySetter(annotatedField));
                if (equals || equals2) {
                    if (equals) {
                        if (this._anyGetterField == null) {
                            this._anyGetterField = new LinkedList<>();
                        }
                        this._anyGetterField.add(annotatedField);
                    }
                    if (equals2) {
                        if (this._anySetterField == null) {
                            this._anySetterField = new LinkedList<>();
                        }
                        this._anySetterField.add(annotatedField);
                    }
                } else {
                    String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedField);
                    if (findImplicitPropertyName == null) {
                        findImplicitPropertyName = annotatedField.getName();
                    }
                    String modifyFieldName = this._accessorNaming.modifyFieldName(annotatedField, findImplicitPropertyName);
                    if (modifyFieldName != null) {
                        PropertyName _propNameFromSimple = _propNameFromSimple(modifyFieldName);
                        PropertyName findRenameByField = annotationIntrospector.findRenameByField(this._config, annotatedField, _propNameFromSimple);
                        if (findRenameByField != null && !findRenameByField.equals(_propNameFromSimple)) {
                            if (this._fieldRenameMappings == null) {
                                this._fieldRenameMappings = new HashMap();
                            }
                            this._fieldRenameMappings.put(findRenameByField, _propNameFromSimple);
                        }
                        if (this._forSerialization) {
                            findNameForDeserialization = annotationIntrospector.findNameForSerialization(annotatedField);
                        } else {
                            findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedField);
                        }
                        boolean z4 = findNameForDeserialization != null;
                        if (!z4 || !findNameForDeserialization.isEmpty()) {
                            propertyName = findNameForDeserialization;
                            z = z4;
                        } else {
                            z = false;
                            propertyName = _propNameFromSimple(modifyFieldName);
                        }
                        boolean z5 = propertyName != null;
                        if (!z5) {
                            z5 = this._visibilityChecker.isFieldVisible(annotatedField);
                        }
                        boolean hasIgnoreMarker = annotationIntrospector.hasIgnoreMarker(annotatedField);
                        if (!annotatedField.isTransient() || z4) {
                            z2 = z5;
                        } else {
                            if (isEnabled) {
                                hasIgnoreMarker = true;
                            }
                            z2 = false;
                        }
                        if (!z3 || propertyName != null || hasIgnoreMarker || !Modifier.isFinal(annotatedField.getModifiers())) {
                            _property(map, modifyFieldName).addField(annotatedField, propertyName, z, z2, hasIgnoreMarker);
                        }
                    }
                }
            }
        }
    }

    protected void _addGetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z;
        boolean z2;
        String str;
        PropertyName propertyName;
        boolean isGetterVisible;
        Class<?> rawReturnType = annotatedMethod.getRawReturnType();
        if (rawReturnType != Void.TYPE) {
            if (rawReturnType == Void.class && !this._config.isEnabled(MapperFeature.ALLOW_VOID_VALUED_PROPERTIES)) {
                return;
            }
            if (Boolean.TRUE.equals(annotationIntrospector.hasAnyGetter(annotatedMethod))) {
                if (this._anyGetters == null) {
                    this._anyGetters = new LinkedList<>();
                }
                this._anyGetters.add(annotatedMethod);
            } else if (Boolean.TRUE.equals(annotationIntrospector.hasAsKey(this._config, annotatedMethod))) {
                if (this._jsonKeyAccessors == null) {
                    this._jsonKeyAccessors = new LinkedList<>();
                }
                this._jsonKeyAccessors.add(annotatedMethod);
            } else if (Boolean.TRUE.equals(annotationIntrospector.hasAsValue(annotatedMethod))) {
                if (this._jsonValueAccessors == null) {
                    this._jsonValueAccessors = new LinkedList<>();
                }
                this._jsonValueAccessors.add(annotatedMethod);
            } else {
                PropertyName findNameForSerialization = annotationIntrospector.findNameForSerialization(annotatedMethod);
                boolean z3 = findNameForSerialization != null;
                if (!z3) {
                    str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                    if (str == null) {
                        str = this._accessorNaming.findNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
                    }
                    if (str == null) {
                        str = this._accessorNaming.findNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                        if (str == null) {
                            return;
                        }
                        isGetterVisible = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                    } else {
                        isGetterVisible = this._visibilityChecker.isGetterVisible(annotatedMethod);
                    }
                    propertyName = findNameForSerialization;
                    z = isGetterVisible;
                    z2 = z3;
                } else {
                    String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                    if (findImplicitPropertyName == null && (findImplicitPropertyName = this._accessorNaming.findNameForRegularGetter(annotatedMethod, annotatedMethod.getName())) == null) {
                        findImplicitPropertyName = this._accessorNaming.findNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                    }
                    if (findImplicitPropertyName == null) {
                        findImplicitPropertyName = annotatedMethod.getName();
                    }
                    if (findNameForSerialization.isEmpty()) {
                        findNameForSerialization = _propNameFromSimple(findImplicitPropertyName);
                        z3 = false;
                    }
                    z = true;
                    z2 = z3;
                    str = findImplicitPropertyName;
                    propertyName = findNameForSerialization;
                }
                _property(map, _checkRenameByField(str)).addGetter(annotatedMethod, propertyName, z2, z, annotationIntrospector.hasIgnoreMarker(annotatedMethod));
            }
        }
    }

    protected void _addInjectables(Map<String, POJOPropertyBuilder> map) {
        for (AnnotatedMember annotatedMember : this._classDef.fields()) {
            _doAddInjectable(this._annotationIntrospector.findInjectableValue(annotatedMember), annotatedMember);
        }
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            if (annotatedMethod.getParameterCount() == 1) {
                _doAddInjectable(this._annotationIntrospector.findInjectableValue(annotatedMethod), annotatedMethod);
            }
        }
    }

    protected void _addMethods(Map<String, POJOPropertyBuilder> map) {
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(map, annotatedMethod, this._annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(map, annotatedMethod, this._annotationIntrospector);
            } else if (parameterCount == 2 && Boolean.TRUE.equals(this._annotationIntrospector.hasAnySetter(annotatedMethod))) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    protected void _addSetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z;
        boolean z2;
        String str;
        PropertyName propertyName;
        PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedMethod);
        boolean z3 = findNameForDeserialization != null;
        if (!z3) {
            str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            if (str == null) {
                str = this._accessorNaming.findNameForMutator(annotatedMethod, annotatedMethod.getName());
            }
            if (str == null) {
                return;
            }
            propertyName = findNameForDeserialization;
            z = this._visibilityChecker.isSetterVisible(annotatedMethod);
            z2 = z3;
        } else {
            String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            if (findImplicitPropertyName == null) {
                findImplicitPropertyName = this._accessorNaming.findNameForMutator(annotatedMethod, annotatedMethod.getName());
            }
            if (findImplicitPropertyName == null) {
                findImplicitPropertyName = annotatedMethod.getName();
            }
            if (findNameForDeserialization.isEmpty()) {
                findNameForDeserialization = _propNameFromSimple(findImplicitPropertyName);
                z3 = false;
            }
            z = true;
            z2 = z3;
            str = findImplicitPropertyName;
            propertyName = findNameForDeserialization;
        }
        _property(map, _checkRenameByField(str)).addSetter(annotatedMethod, propertyName, z2, z, annotationIntrospector.hasIgnoreMarker(annotatedMethod));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _collectIgnorals(String str) {
        if (this._forSerialization || str == null) {
            return;
        }
        if (this._ignoredPropertyNames == null) {
            this._ignoredPropertyNames = new HashSet<>();
        }
        this._ignoredPropertyNames.add(str);
    }

    protected void _doAddInjectable(JacksonInject.Value value, AnnotatedMember annotatedMember) {
        if (value == null) {
            return;
        }
        Object id = value.getId();
        if (this._injectables == null) {
            this._injectables = new LinkedHashMap<>();
        }
        AnnotatedMember put = this._injectables.put(id, annotatedMember);
        if (put == null || put.getClass() != annotatedMember.getClass()) {
            return;
        }
        String name = id.getClass().getName();
        throw new IllegalArgumentException("Duplicate injectable value with id '" + id + "' (of type " + name + ")");
    }

    protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, PropertyName propertyName) {
        String simpleName = propertyName.getSimpleName();
        POJOPropertyBuilder pOJOPropertyBuilder = map.get(simpleName);
        if (pOJOPropertyBuilder == null) {
            POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, propertyName);
            map.put(simpleName, pOJOPropertyBuilder2);
            return pOJOPropertyBuilder2;
        }
        return pOJOPropertyBuilder;
    }

    protected void _removeUnwantedAccessor(Map<String, POJOPropertyBuilder> map) {
        boolean isEnabled = this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        for (POJOPropertyBuilder pOJOPropertyBuilder : map.values()) {
            pOJOPropertyBuilder.removeNonVisible(isEnabled, this._forSerialization ? null : this);
        }
    }

    protected void _removeUnwantedProperties(Map<String, POJOPropertyBuilder> map) {
        Iterator<POJOPropertyBuilder> it2 = map.values().iterator();
        while (it2.hasNext()) {
            POJOPropertyBuilder next = it2.next();
            if (!next.anyVisible()) {
                it2.remove();
            } else if (next.anyIgnorals()) {
                if (!next.isExplicitlyIncluded()) {
                    it2.remove();
                    _collectIgnorals(next.getName());
                } else {
                    next.removeIgnored();
                    if (!next.couldDeserialize()) {
                        _collectIgnorals(next.getName());
                    }
                }
            }
        }
    }

    protected void _renameProperties(Map<String, POJOPropertyBuilder> map) {
        HashSet<String> hashSet;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it2 = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it2.hasNext()) {
            POJOPropertyBuilder value = it2.next().getValue();
            Set<PropertyName> findExplicitNames = value.findExplicitNames();
            if (!findExplicitNames.isEmpty()) {
                it2.remove();
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                if (findExplicitNames.size() == 1) {
                    linkedList.add(value.mo7128withName(findExplicitNames.iterator().next()));
                } else {
                    linkedList.addAll(value.explode(findExplicitNames));
                }
            }
        }
        if (linkedList != null) {
            Iterator it3 = linkedList.iterator();
            while (it3.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it3.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = map.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    map.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
                if (_replaceCreatorProperty(pOJOPropertyBuilder, this._creatorProperties) && (hashSet = this._ignoredPropertyNames) != null) {
                    hashSet.remove(name);
                }
            }
        }
    }

    protected void _renameUsing(Map<String, POJOPropertyBuilder> map, PropertyNamingStrategy propertyNamingStrategy) {
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) map.values().toArray(new POJOPropertyBuilder[map.size()]);
        map.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            PropertyName fullName = pOJOPropertyBuilder.getFullName();
            String str = null;
            if (!pOJOPropertyBuilder.isExplicitlyNamed() || this._config.isEnabled(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING)) {
                if (this._forSerialization) {
                    if (pOJOPropertyBuilder.hasGetter()) {
                        str = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), fullName.getSimpleName());
                    } else if (pOJOPropertyBuilder.hasField()) {
                        str = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), fullName.getSimpleName());
                    }
                } else if (pOJOPropertyBuilder.hasSetter()) {
                    str = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                    str = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasField()) {
                    str = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), fullName.getSimpleName());
                } else if (pOJOPropertyBuilder.hasGetter()) {
                    str = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), fullName.getSimpleName());
                }
            }
            if (str != null && !fullName.hasSimpleName(str)) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.mo7129withSimpleName(str);
            } else {
                str = fullName.getSimpleName();
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = map.get(str);
            if (pOJOPropertyBuilder2 == null) {
                map.put(str, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
            _replaceCreatorProperty(pOJOPropertyBuilder, this._creatorProperties);
        }
    }

    protected void _renameWithWrappers(Map<String, POJOPropertyBuilder> map) {
        PropertyName findWrapperName;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it2 = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it2.hasNext()) {
            POJOPropertyBuilder value = it2.next().getValue();
            AnnotatedMember primaryMember = value.getPrimaryMember();
            if (primaryMember != null && (findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember)) != null && findWrapperName.hasSimpleName() && !findWrapperName.equals(value.getFullName())) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(value.mo7128withName(findWrapperName));
                it2.remove();
            }
        }
        if (linkedList != null) {
            Iterator it3 = linkedList.iterator();
            while (it3.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it3.next();
                String name = pOJOPropertyBuilder.getName();
                POJOPropertyBuilder pOJOPropertyBuilder2 = map.get(name);
                if (pOJOPropertyBuilder2 == null) {
                    map.put(name, pOJOPropertyBuilder);
                } else {
                    pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
                }
            }
        }
    }

    protected boolean _replaceCreatorProperty(POJOPropertyBuilder pOJOPropertyBuilder, List<POJOPropertyBuilder> list) {
        if (list != null) {
            String internalName = pOJOPropertyBuilder.getInternalName();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).getInternalName().equals(internalName)) {
                    list.set(i, pOJOPropertyBuilder);
                    return true;
                }
            }
        }
        return false;
    }

    protected void _sortProperties(Map<String, POJOPropertyBuilder> map) {
        boolean booleanValue;
        Map<? extends Object, ? extends Object> linkedHashMap;
        Collection<POJOPropertyBuilder> collection;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        if (findSerializationSortAlphabetically == null) {
            booleanValue = this._config.shouldSortPropertiesAlphabetically();
        } else {
            booleanValue = findSerializationSortAlphabetically.booleanValue();
        }
        boolean _anyIndexed = _anyIndexed(map.values());
        String[] findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        if (booleanValue || _anyIndexed || this._creatorProperties != null || findSerializationPropertyOrder != null) {
            int size = map.size();
            if (booleanValue) {
                linkedHashMap = new TreeMap<>();
            } else {
                linkedHashMap = new LinkedHashMap<>(size + size);
            }
            for (POJOPropertyBuilder pOJOPropertyBuilder : map.values()) {
                linkedHashMap.put(pOJOPropertyBuilder.getName(), pOJOPropertyBuilder);
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(size + size);
            if (findSerializationPropertyOrder != null) {
                for (String str : findSerializationPropertyOrder) {
                    POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) linkedHashMap.remove(str);
                    if (pOJOPropertyBuilder2 == null) {
                        Iterator<POJOPropertyBuilder> it2 = map.values().iterator();
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
            if (_anyIndexed) {
                TreeMap treeMap = new TreeMap();
                Iterator<Map.Entry<? extends Object, ? extends Object>> it3 = linkedHashMap.entrySet().iterator();
                while (it3.hasNext()) {
                    POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) it3.next().getValue();
                    Integer index = pOJOPropertyBuilder3.getMetadata().getIndex();
                    if (index != null) {
                        treeMap.put(index, pOJOPropertyBuilder3);
                        it3.remove();
                    }
                }
                for (POJOPropertyBuilder pOJOPropertyBuilder4 : treeMap.values()) {
                    linkedHashMap2.put(pOJOPropertyBuilder4.getName(), pOJOPropertyBuilder4);
                }
            }
            if (this._creatorProperties != null && (!booleanValue || this._config.isEnabled(MapperFeature.SORT_CREATOR_PROPERTIES_FIRST))) {
                if (booleanValue) {
                    TreeMap treeMap2 = new TreeMap();
                    Iterator<POJOPropertyBuilder> it4 = this._creatorProperties.iterator();
                    while (it4.hasNext()) {
                        POJOPropertyBuilder next2 = it4.next();
                        treeMap2.put(next2.getName(), next2);
                    }
                    collection = treeMap2.values();
                } else {
                    collection = this._creatorProperties;
                }
                for (POJOPropertyBuilder pOJOPropertyBuilder5 : collection) {
                    String name = pOJOPropertyBuilder5.getName();
                    if (linkedHashMap.containsKey(name)) {
                        linkedHashMap2.put(name, pOJOPropertyBuilder5);
                    }
                }
            }
            linkedHashMap2.putAll(linkedHashMap);
            map.clear();
            map.putAll(linkedHashMap2);
        }
    }

    @Deprecated
    protected void _updateCreatorProperty(POJOPropertyBuilder pOJOPropertyBuilder, List<POJOPropertyBuilder> list) {
        _replaceCreatorProperty(pOJOPropertyBuilder, list);
    }

    protected void collectAll() {
        LinkedHashMap<String, POJOPropertyBuilder> linkedHashMap = new LinkedHashMap<>();
        _addFields(linkedHashMap);
        _addMethods(linkedHashMap);
        if (!this._classDef.isNonStaticInnerClass()) {
            _addCreators(linkedHashMap);
        }
        _removeUnwantedProperties(linkedHashMap);
        _removeUnwantedAccessor(linkedHashMap);
        _renameProperties(linkedHashMap);
        _addInjectables(linkedHashMap);
        for (POJOPropertyBuilder pOJOPropertyBuilder : linkedHashMap.values()) {
            pOJOPropertyBuilder.mergeAnnotations(this._forSerialization);
        }
        for (POJOPropertyBuilder pOJOPropertyBuilder2 : linkedHashMap.values()) {
            pOJOPropertyBuilder2.trimByVisibility();
        }
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(linkedHashMap, _findNamingStrategy);
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers(linkedHashMap);
        }
        _sortProperties(linkedHashMap);
        this._properties = linkedHashMap;
        this._collected = true;
    }

    @Deprecated
    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    @Deprecated
    public AnnotatedMember getAnyGetter() {
        return getAnyGetterMethod();
    }

    public AnnotatedMember getAnyGetterField() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anyGetterField;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'any-getter' fields defined (%s vs %s)", this._anyGetterField.get(0), this._anyGetterField.get(1));
            }
            return this._anyGetterField.getFirst();
        }
        return null;
    }

    public AnnotatedMember getAnyGetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anyGetters;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'any-getter' methods defined (%s vs %s)", this._anyGetters.get(0), this._anyGetters.get(1));
            }
            return this._anyGetters.getFirst();
        }
        return null;
    }

    public AnnotatedMember getAnySetterField() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anySetterField;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'any-setter' fields defined (%s vs %s)", this._anySetterField.get(0), this._anySetterField.get(1));
            }
            return this._anySetterField.getFirst();
        }
        return null;
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMethod> linkedList = this._anySetters;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'any-setter' methods defined (%s vs %s)", this._anySetters.get(0), this._anySetters.get(1));
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

    public Map<Object, AnnotatedMember> getInjectables() {
        if (!this._collected) {
            collectAll();
        }
        return this._injectables;
    }

    public AnnotatedMember getJsonKeyAccessor() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._jsonKeyAccessors;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'as-key' properties defined (%s vs %s)", this._jsonKeyAccessors.get(0), this._jsonKeyAccessors.get(1));
            }
            return this._jsonKeyAccessors.get(0);
        }
        return null;
    }

    public AnnotatedMember getJsonValueAccessor() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._jsonValueAccessors;
        if (linkedList != null) {
            if (linkedList.size() > 1) {
                reportProblem("Multiple 'as-value' properties defined (%s vs %s)", this._jsonValueAccessors.get(0), this._jsonValueAccessors.get(1));
            }
            return this._jsonValueAccessors.get(0);
        }
        return null;
    }

    @Deprecated
    public AnnotatedMethod getJsonValueMethod() {
        AnnotatedMember jsonValueAccessor = getJsonValueAccessor();
        if (jsonValueAccessor instanceof AnnotatedMethod) {
            return (AnnotatedMethod) jsonValueAccessor;
        }
        return null;
    }

    public ObjectIdInfo getObjectIdInfo() {
        ObjectIdInfo findObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        return findObjectIdInfo != null ? this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo) : findObjectIdInfo;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(getPropertyMap().values());
    }

    protected Map<String, POJOPropertyBuilder> getPropertyMap() {
        if (!this._collected) {
            collectAll();
        }
        return this._properties;
    }

    public JavaType getType() {
        return this._type;
    }

    protected void reportProblem(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Problem with definition of ");
        outline107.append(this._classDef);
        outline107.append(RealTimeTextConstants.COLON_SPACE);
        outline107.append(str);
        throw new IllegalArgumentException(outline107.toString());
    }

    protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = map.get(str);
        if (pOJOPropertyBuilder == null) {
            POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, PropertyName.construct(str));
            map.put(str, pOJOPropertyBuilder2);
            return pOJOPropertyBuilder2;
        }
        return pOJOPropertyBuilder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        this(mapperConfig, z, javaType, annotatedClass, _accessorNaming(mapperConfig, annotatedClass, str));
        this._mutatorPrefix = str;
    }
}
