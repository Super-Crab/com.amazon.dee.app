package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerFactory;
import com.amazon.org.codehaus.jackson.map.Serializers;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.amazon.org.codehaus.jackson.map.type.TypeBindings;
import com.amazon.org.codehaus.jackson.map.util.ArrayBuilders;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes13.dex */
public class BeanSerializerFactory extends BasicSerializerFactory {
    public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
    protected final SerializerFactory.Config _factoryConfig;

    /* loaded from: classes13.dex */
    public static class ConfigImpl extends SerializerFactory.Config {
        protected final Serializers[] _additionalKeySerializers;
        protected final Serializers[] _additionalSerializers;
        protected final BeanSerializerModifier[] _modifiers;
        protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
        protected static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];

        public ConfigImpl() {
            this(null, null, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasKeySerializers() {
            return this._additionalKeySerializers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializers() {
            return this._additionalSerializers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<Serializers> keySerializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeySerializers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<BeanSerializerModifier> serializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable<Serializers> serializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalSerializers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalKeySerializers(Serializers serializers) {
            if (serializers != null) {
                return new ConfigImpl(this._additionalSerializers, (Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeySerializers, serializers), this._modifiers);
            }
            throw new IllegalArgumentException("Can not pass null Serializers");
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalSerializers(Serializers serializers) {
            if (serializers != null) {
                return new ConfigImpl((Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalSerializers, serializers), this._additionalKeySerializers, this._modifiers);
            }
            throw new IllegalArgumentException("Can not pass null Serializers");
        }

        @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
            if (beanSerializerModifier != null) {
                return new ConfigImpl(this._additionalSerializers, this._additionalKeySerializers, (BeanSerializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanSerializerModifier));
            }
            throw new IllegalArgumentException("Can not pass null modifier");
        }

        protected ConfigImpl(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
            this._additionalSerializers = serializersArr == null ? NO_SERIALIZERS : serializersArr;
            this._additionalKeySerializers = serializersArr2 == null ? NO_SERIALIZERS : serializersArr2;
            this._modifiers = beanSerializerModifierArr == null ? NO_MODIFIERS : beanSerializerModifierArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerFactory(SerializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    protected BeanPropertyWriter _constructWriter(SerializationConfig serializationConfig, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, String str, AnnotatedMember annotatedMember) throws JsonMappingException {
        if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMember.fixAccess();
        }
        JavaType type = annotatedMember.getType(typeBindings);
        BeanProperty.Std std = new BeanProperty.Std(str, type, propertyBuilder.getClassAnnotations(), annotatedMember);
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, annotatedMember, std);
        TypeSerializer typeSerializer = null;
        if (ClassUtil.isCollectionMapOrArray(type.getRawClass())) {
            typeSerializer = findPropertyContentTypeSerializer(type, serializationConfig, annotatedMember, std);
        }
        BeanPropertyWriter buildWriter = propertyBuilder.buildWriter(str, type, findSerializerFromAnnotation, findPropertyTypeSerializer(type, serializationConfig, annotatedMember, std), typeSerializer, annotatedMember, z);
        buildWriter.setViews(serializationConfig.getAnnotationIntrospector().findSerializationViews(annotatedMember));
        return buildWriter;
    }

    protected JsonSerializer<Object> constructBeanSerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        if (basicBeanDescription.getBeanClass() != Object.class) {
            BeanSerializerBuilder constructBeanSerializerBuilder = constructBeanSerializerBuilder(basicBeanDescription);
            List<BeanPropertyWriter> findBeanProperties = findBeanProperties(serializationConfig, basicBeanDescription);
            if (findBeanProperties == null) {
                findBeanProperties = new ArrayList<>();
            }
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier beanSerializerModifier : this._factoryConfig.serializerModifiers()) {
                    findBeanProperties = beanSerializerModifier.changeProperties(serializationConfig, basicBeanDescription, findBeanProperties);
                }
            }
            List<BeanPropertyWriter> sortBeanProperties = sortBeanProperties(serializationConfig, basicBeanDescription, filterBeanProperties(serializationConfig, basicBeanDescription, findBeanProperties));
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier beanSerializerModifier2 : this._factoryConfig.serializerModifiers()) {
                    sortBeanProperties = beanSerializerModifier2.orderProperties(serializationConfig, basicBeanDescription, sortBeanProperties);
                }
            }
            constructBeanSerializerBuilder.setProperties(sortBeanProperties);
            constructBeanSerializerBuilder.setFilterId(findFilterId(serializationConfig, basicBeanDescription));
            AnnotatedMethod findAnyGetter = basicBeanDescription.findAnyGetter();
            if (findAnyGetter != null) {
                if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                    findAnyGetter.fixAccess();
                }
                JavaType type = findAnyGetter.getType(basicBeanDescription.bindingsForBeanType());
                constructBeanSerializerBuilder.setAnyGetter(new AnyGetterWriter(findAnyGetter, com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer.construct(null, type, serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING), createTypeSerializer(serializationConfig, type.getContentType(), beanProperty), beanProperty, null, null)));
            }
            processViews(serializationConfig, constructBeanSerializerBuilder);
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier beanSerializerModifier3 : this._factoryConfig.serializerModifiers()) {
                    constructBeanSerializerBuilder = beanSerializerModifier3.updateBuilder(serializationConfig, basicBeanDescription, constructBeanSerializerBuilder);
                }
            }
            JsonSerializer<?> build = constructBeanSerializerBuilder.build();
            return (build != null || !basicBeanDescription.hasKnownClassAnnotations()) ? build : constructBeanSerializerBuilder.createDummy();
        }
        throw new IllegalArgumentException("Can not create bean serializer for Object.class");
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanSerializerBuilder(basicBeanDescription);
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return new PropertyBuilder(serializationConfig, basicBeanDescription);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<?> jsonSerializer = null;
        if (!this._factoryConfig.hasKeySerializers()) {
            return null;
        }
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass());
        Iterator<Serializers> it2 = this._factoryConfig.keySerializers().iterator();
        while (it2.hasNext() && (jsonSerializer = it2.next().findSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty)) == null) {
        }
        return jsonSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BasicSerializerFactory, com.amazon.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspect(javaType);
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), javaType);
        boolean z = modifyTypeByAnnotation != javaType;
        if (modifyTypeByAnnotation != javaType && modifyTypeByAnnotation.getRawClass() != javaType.getRawClass()) {
            basicBeanDescription = (BasicBeanDescription) serializationConfig.introspect(modifyTypeByAnnotation);
        }
        if (javaType.isContainerType()) {
            return buildContainerSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z);
        }
        for (Serializers serializers : this._factoryConfig.serializers()) {
            JsonSerializer<?> findSerializer = serializers.findSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
            if (findSerializer != null) {
                return findSerializer;
            }
        }
        JsonSerializer<?> findSerializerByLookup = findSerializerByLookup(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
        if (findSerializerByLookup != null) {
            return findSerializerByLookup;
        }
        JsonSerializer<?> findSerializerByPrimaryType = findSerializerByPrimaryType(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
        if (findSerializerByPrimaryType != null) {
            return findSerializerByPrimaryType;
        }
        JsonSerializer<Object> findBeanSerializer = findBeanSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
        return findBeanSerializer == null ? findSerializerByAddonType(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z) : findBeanSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BasicSerializerFactory
    protected Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        String[] findPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo());
        if (findPropertiesToIgnore != null && findPropertiesToIgnore.length > 0) {
            HashSet arrayToSet = ArrayBuilders.arrayToSet(findPropertiesToIgnore);
            Iterator<BeanPropertyWriter> it2 = list.iterator();
            while (it2.hasNext()) {
                if (arrayToSet.contains(it2.next().getName())) {
                    it2.remove();
                }
            }
        }
        return list;
    }

    protected List<BeanPropertyWriter> findBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException {
        List<BeanPropertyDefinition> findProperties = basicBeanDescription.findProperties();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        removeIgnorableTypes(serializationConfig, basicBeanDescription, findProperties);
        if (serializationConfig.isEnabled(SerializationConfig.Feature.REQUIRE_SETTERS_FOR_GETTERS)) {
            removeSetterlessGetters(serializationConfig, basicBeanDescription, findProperties);
        }
        if (findProperties.isEmpty()) {
            return null;
        }
        boolean usesStaticTyping = usesStaticTyping(serializationConfig, basicBeanDescription, null, null);
        PropertyBuilder constructPropertyBuilder = constructPropertyBuilder(serializationConfig, basicBeanDescription);
        ArrayList arrayList = new ArrayList(findProperties.size());
        TypeBindings bindingsForBeanType = basicBeanDescription.bindingsForBeanType();
        for (BeanPropertyDefinition beanPropertyDefinition : findProperties) {
            AnnotatedMember accessor = beanPropertyDefinition.getAccessor();
            AnnotationIntrospector.ReferenceProperty findReferenceType = annotationIntrospector.findReferenceType(accessor);
            if (findReferenceType == null || !findReferenceType.isBackReference()) {
                String name = beanPropertyDefinition.getName();
                if (accessor instanceof AnnotatedMethod) {
                    arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, name, (AnnotatedMethod) accessor));
                } else {
                    arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, name, (AnnotatedField) accessor));
                }
            }
        }
        return arrayList;
    }

    public JsonSerializer<Object> findBeanSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        JsonSerializer<?> constructBeanSerializer = constructBeanSerializer(serializationConfig, basicBeanDescription, beanProperty);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier beanSerializerModifier : this._factoryConfig.serializerModifiers()) {
                constructBeanSerializer = beanSerializerModifier.modifySerializer(serializationConfig, basicBeanDescription, constructBeanSerializer);
            }
        }
        return constructBeanSerializer;
    }

    protected Object findFilterId(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(basicBeanDescription.getClassInfo());
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        JavaType contentType = javaType.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig, annotatedMember, javaType);
        if (findPropertyContentTypeResolver == null) {
            return createTypeSerializer(serializationConfig, contentType, beanProperty);
        }
        return findPropertyContentTypeResolver.buildTypeSerializer(serializationConfig, contentType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder<?> findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return createTypeSerializer(serializationConfig, javaType, beanProperty);
        }
        return findPropertyTypeResolver.buildTypeSerializer(serializationConfig, javaType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        return ClassUtil.canBeABeanType(cls) == null && !ClassUtil.isProxyType(cls);
    }

    protected void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        List<BeanPropertyWriter> properties = beanSerializerBuilder.getProperties();
        boolean isEnabled = serializationConfig.isEnabled(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            BeanPropertyWriter beanPropertyWriter = properties.get(i2);
            Class<?>[] views = beanPropertyWriter.getViews();
            if (views != null) {
                i++;
                beanPropertyWriterArr[i2] = constructFilteredBeanWriter(beanPropertyWriter, views);
            } else if (isEnabled) {
                beanPropertyWriterArr[i2] = beanPropertyWriter;
            }
        }
        if (!isEnabled || i != 0) {
            beanSerializerBuilder.setFilteredProperties(beanPropertyWriterArr);
        }
    }

    protected void removeIgnorableTypes(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyDefinition> list) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        HashMap hashMap = new HashMap();
        Iterator<BeanPropertyDefinition> it2 = list.iterator();
        while (it2.hasNext()) {
            AnnotatedMember accessor = it2.next().getAccessor();
            if (accessor == null) {
                it2.remove();
            } else {
                Class<?> rawType = accessor.getRawType();
                Boolean bool = (Boolean) hashMap.get(rawType);
                if (bool == null) {
                    bool = annotationIntrospector.isIgnorableType(((BasicBeanDescription) serializationConfig.introspectClassAnnotations(rawType)).getClassInfo());
                    if (bool == null) {
                        bool = Boolean.FALSE;
                    }
                    hashMap.put(rawType, bool);
                }
                if (bool.booleanValue()) {
                    it2.remove();
                }
            }
        }
    }

    protected void removeSetterlessGetters(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyDefinition> list) {
        Iterator<BeanPropertyDefinition> it2 = list.iterator();
        while (it2.hasNext()) {
            BeanPropertyDefinition next = it2.next();
            if (!next.couldDeserialize() && !next.isExplicitlyIncluded()) {
                it2.remove();
            }
        }
    }

    @Deprecated
    protected List<BeanPropertyWriter> sortBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    @Override // com.amazon.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() == BeanSerializerFactory.class) {
            return new BeanSerializerFactory(config);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subtype of BeanSerializerFactory (");
        outline107.append(getClass().getName());
        outline107.append(") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with ");
        outline107.append("additional serializer definitions");
        throw new IllegalStateException(outline107.toString());
    }
}
