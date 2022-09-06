package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.AbstractTypeResolver;
import com.amazon.org.codehaus.jackson.map.AnnotationIntrospector;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializerFactory;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.Deserializers;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializers;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.map.deser.impl.CreatorCollector;
import com.amazon.org.codehaus.jackson.map.deser.impl.CreatorProperty;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.map.type.CollectionLikeType;
import com.amazon.org.codehaus.jackson.map.type.CollectionType;
import com.amazon.org.codehaus.jackson.map.type.MapLikeType;
import com.amazon.org.codehaus.jackson.map.type.MapType;
import com.amazon.org.codehaus.jackson.map.util.ArrayBuilders;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public class BeanDeserializerFactory extends BasicDeserializerFactory {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(null);
    protected final DeserializerFactory.Config _factoryConfig;

    /* loaded from: classes13.dex */
    public static class ConfigImpl extends DeserializerFactory.Config {
        protected final AbstractTypeResolver[] _abstractTypeResolvers;
        protected final Deserializers[] _additionalDeserializers;
        protected final KeyDeserializers[] _additionalKeyDeserializers;
        protected final BeanDeserializerModifier[] _modifiers;
        protected final ValueInstantiators[] _valueInstantiators;
        protected static final KeyDeserializers[] NO_KEY_DESERIALIZERS = new KeyDeserializers[0];
        protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
        protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
        protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];

        public ConfigImpl() {
            this(null, null, null, null, null);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<AbstractTypeResolver> abstractTypeResolvers() {
            return ArrayBuilders.arrayAsIterable(this._abstractTypeResolvers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<BeanDeserializerModifier> deserializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<Deserializers> deserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalDeserializers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasAbstractTypeResolvers() {
            return this._abstractTypeResolvers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializers() {
            return this._additionalDeserializers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasKeyDeserializers() {
            return this._additionalKeyDeserializers.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasValueInstantiators() {
            return this._valueInstantiators.length > 0;
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<KeyDeserializers> keyDeserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeyDeserializers);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable<ValueInstantiators> valueInstantiators() {
            return ArrayBuilders.arrayAsIterable(this._valueInstantiators);
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
            if (abstractTypeResolver != null) {
                return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, (AbstractTypeResolver[]) ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, abstractTypeResolver), this._valueInstantiators);
            }
            throw new IllegalArgumentException("Can not pass null resolver");
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalDeserializers(Deserializers deserializers) {
            if (deserializers != null) {
                return new ConfigImpl((Deserializers[]) ArrayBuilders.insertInListNoDup(this._additionalDeserializers, deserializers), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
            }
            throw new IllegalArgumentException("Can not pass null Deserializers");
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
            if (keyDeserializers != null) {
                return new ConfigImpl(this._additionalDeserializers, (KeyDeserializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, keyDeserializers), this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
            }
            throw new IllegalArgumentException("Can not pass null KeyDeserializers");
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
            if (beanDeserializerModifier != null) {
                return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, (BeanDeserializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanDeserializerModifier), this._abstractTypeResolvers, this._valueInstantiators);
            }
            throw new IllegalArgumentException("Can not pass null modifier");
        }

        @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withValueInstantiators(ValueInstantiators valueInstantiators) {
            if (valueInstantiators != null) {
                return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, (ValueInstantiators[]) ArrayBuilders.insertInListNoDup(this._valueInstantiators, valueInstantiators));
            }
            throw new IllegalArgumentException("Can not pass null resolver");
        }

        protected ConfigImpl(Deserializers[] deserializersArr, KeyDeserializers[] keyDeserializersArr, BeanDeserializerModifier[] beanDeserializerModifierArr, AbstractTypeResolver[] abstractTypeResolverArr, ValueInstantiators[] valueInstantiatorsArr) {
            this._additionalDeserializers = deserializersArr == null ? DeserializerFactory.NO_DESERIALIZERS : deserializersArr;
            this._additionalKeyDeserializers = keyDeserializersArr == null ? NO_KEY_DESERIALIZERS : keyDeserializersArr;
            this._modifiers = beanDeserializerModifierArr == null ? NO_MODIFIERS : beanDeserializerModifierArr;
            this._abstractTypeResolvers = abstractTypeResolverArr == null ? NO_ABSTRACT_TYPE_RESOLVERS : abstractTypeResolverArr;
            this._valueInstantiators = valueInstantiatorsArr == null ? NO_VALUE_INSTANTIATORS : valueInstantiatorsArr;
        }
    }

    @Deprecated
    public BeanDeserializerFactory() {
        this(null);
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        com.amazon.org.codehaus.jackson.map.util.EnumResolver<?> constructEnumResolver = constructEnumResolver(rawClass, deserializationConfig);
        for (AnnotatedMethod annotatedMethod : ((BasicBeanDescription) deserializationConfig.introspect(javaType)).getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    if (annotatedMethod.getParameterType(0) == String.class) {
                        if (deserializationConfig.canOverrideAccessModifiers()) {
                            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
                        }
                        return com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, annotatedMethod);
                    }
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Unsuitable method (");
                sb.append(annotatedMethod);
                sb.append(") decorated with @JsonCreator (for Enum type ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(rawClass, sb, ")"));
            }
        }
        return com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    protected void _addDeserializerConstructors(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) throws JsonMappingException {
        for (AnnotatedConstructor annotatedConstructor : basicBeanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor.getParameterCount();
            if (parameterCount >= 1) {
                boolean hasCreatorAnnotation = annotationIntrospector.hasCreatorAnnotation(annotatedConstructor);
                boolean isCreatorVisible = visibilityChecker.isCreatorVisible(annotatedConstructor);
                if (parameterCount == 1) {
                    _handleSingleArgumentConstructor(deserializationConfig, basicBeanDescription, visibilityChecker, annotationIntrospector, creatorCollector, annotatedConstructor, hasCreatorAnnotation, isCreatorVisible);
                } else if (hasCreatorAnnotation || isCreatorVisible) {
                    CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                    AnnotatedParameter annotatedParameter = null;
                    int i = 0;
                    int i2 = 0;
                    for (int i3 = 0; i3 < parameterCount; i3++) {
                        AnnotatedParameter parameter = annotatedConstructor.getParameter(i3);
                        String findPropertyNameForParam = parameter == null ? null : annotationIntrospector.findPropertyNameForParam(parameter);
                        Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter);
                        if (findPropertyNameForParam != null && findPropertyNameForParam.length() > 0) {
                            i++;
                            creatorPropertyArr[i3] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i3, parameter, findInjectableValueId);
                        } else if (findInjectableValueId != null) {
                            i2++;
                            creatorPropertyArr[i3] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i3, parameter, findInjectableValueId);
                        } else if (annotatedParameter == null) {
                            annotatedParameter = parameter;
                        }
                    }
                    if (hasCreatorAnnotation || i > 0 || i2 > 0) {
                        if (i + i2 != parameterCount) {
                            if (i == 0 && i2 + 1 == parameterCount) {
                                throw new IllegalArgumentException("Delegated constructor with Injectables not yet supported (see [JACKSON-712]) for " + annotatedConstructor);
                            }
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Argument #");
                            outline107.append(annotatedParameter.getIndex());
                            outline107.append(" of constructor ");
                            outline107.append(annotatedConstructor);
                            outline107.append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
                            throw new IllegalArgumentException(outline107.toString());
                        }
                        creatorCollector.addPropertyCreator(annotatedConstructor, creatorPropertyArr);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void _addDeserializerFactoryMethods(com.amazon.org.codehaus.jackson.map.DeserializationConfig r18, com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription r19, com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker<?> r20, com.amazon.org.codehaus.jackson.map.AnnotationIntrospector r21, com.amazon.org.codehaus.jackson.map.deser.impl.CreatorCollector r22) throws com.amazon.org.codehaus.jackson.map.JsonMappingException {
        /*
            r17 = this;
            r8 = r21
            java.util.List r0 = r19.getFactoryMethods()
            java.util.Iterator r9 = r0.iterator()
        La:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto La8
            java.lang.Object r0 = r9.next()
            r6 = r0
            com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod r6 = (com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMethod) r6
            int r0 = r6.getParameterCount()
            r1 = 1
            if (r0 >= r1) goto L1f
            goto La
        L1f:
            boolean r7 = r8.hasCreatorAnnotation(r6)
            r2 = 0
            if (r0 != r1) goto L4c
            com.amazon.org.codehaus.jackson.map.introspect.AnnotatedParameter r1 = r6.getParameter(r2)
            java.lang.String r3 = r8.findPropertyNameForParam(r1)
            java.lang.Object r1 = r8.findInjectableValueId(r1)
            if (r1 != 0) goto L53
            if (r3 == 0) goto L3c
            int r1 = r3.length()
            if (r1 != 0) goto L53
        L3c:
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = r22
            r0._handleSingleArgumentFactory(r1, r2, r3, r4, r5, r6, r7)
            goto La
        L4c:
            boolean r1 = r8.hasCreatorAnnotation(r6)
            if (r1 != 0) goto L53
            goto La
        L53:
            com.amazon.org.codehaus.jackson.map.deser.impl.CreatorProperty[] r1 = new com.amazon.org.codehaus.jackson.map.deser.impl.CreatorProperty[r0]
        L55:
            if (r2 >= r0) goto La1
            com.amazon.org.codehaus.jackson.map.introspect.AnnotatedParameter r15 = r6.getParameter(r2)
            java.lang.String r13 = r8.findPropertyNameForParam(r15)
            java.lang.Object r16 = r8.findInjectableValueId(r15)
            if (r13 == 0) goto L6b
            int r3 = r13.length()
            if (r3 != 0) goto L6d
        L6b:
            if (r16 == 0) goto L7d
        L6d:
            r10 = r17
            r11 = r18
            r12 = r19
            r14 = r2
            com.amazon.org.codehaus.jackson.map.deser.impl.CreatorProperty r3 = r10.constructCreatorProperty(r11, r12, r13, r14, r15, r16)
            r1[r2] = r3
            int r2 = r2 + 1
            goto L55
        L7d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Argument #"
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = " of factory method "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = " has no property name annotation; must have when multiple-paramater static method annotated as Creator"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La1:
            r0 = r22
            r0.addPropertyCreator(r6, r1)
            goto La
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerFactory._addDeserializerFactoryMethods(com.amazon.org.codehaus.jackson.map.DeserializationConfig, com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription, com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker, com.amazon.org.codehaus.jackson.map.AnnotationIntrospector, com.amazon.org.codehaus.jackson.map.deser.impl.CreatorCollector):void");
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer = deserializers.findArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer != null) {
                return findArrayDeserializer;
            }
        }
        return null;
    }

    protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findBeanDeserializer = deserializers.findBeanDeserializer(javaType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
            if (findBeanDeserializer != null) {
                return findBeanDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer = deserializers.findCollectionDeserializer(collectionType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer != null) {
                return findCollectionDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer = deserializers.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer != null) {
                return findCollectionLikeDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer = deserializers.findEnumDeserializer(cls, deserializationConfig, basicBeanDescription, beanProperty);
            if (findEnumDeserializer != null) {
                return findEnumDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer = deserializers.findMapDeserializer(mapType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer != null) {
                return findMapDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer = deserializers.findMapLikeDeserializer(mapLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer != null) {
                return findMapLikeDeserializer;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) throws JsonMappingException {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer = deserializers.findTreeNodeDeserializer(cls, deserializationConfig, beanProperty);
            if (findTreeNodeDeserializer != null) {
                return findTreeNodeDeserializer;
            }
        }
        return null;
    }

    protected boolean _handleSingleArgumentConstructor(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2) throws JsonMappingException {
        AnnotatedParameter parameter = annotatedConstructor.getParameter(0);
        String findPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
        Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter);
        if (findInjectableValueId == null && (findPropertyNameForParam == null || findPropertyNameForParam.length() <= 0)) {
            Class<?> parameterClass = annotatedConstructor.getParameterClass(0);
            if (parameterClass == String.class) {
                if (z || z2) {
                    creatorCollector.addStringCreator(annotatedConstructor);
                }
                return true;
            } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
                if (z || z2) {
                    creatorCollector.addIntCreator(annotatedConstructor);
                }
                return true;
            } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
                if (z || z2) {
                    creatorCollector.addLongCreator(annotatedConstructor);
                }
                return true;
            } else if (parameterClass == Double.TYPE || parameterClass == Double.class) {
                if (z || z2) {
                    creatorCollector.addDoubleCreator(annotatedConstructor);
                }
                return true;
            } else if (!z) {
                return false;
            } else {
                creatorCollector.addDelegatingCreator(annotatedConstructor);
                return true;
            }
        }
        creatorCollector.addPropertyCreator(annotatedConstructor, new CreatorProperty[]{constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, 0, parameter, findInjectableValueId)});
        return true;
    }

    protected boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) throws JsonMappingException {
        Class<?> parameterClass = annotatedMethod.getParameterClass(0);
        if (parameterClass == String.class) {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addStringCreator(annotatedMethod);
            }
            return true;
        } else if (parameterClass != Integer.TYPE && parameterClass != Integer.class) {
            if (parameterClass != Long.TYPE && parameterClass != Long.class) {
                if (parameterClass != Double.TYPE && parameterClass != Double.class) {
                    if (parameterClass != Boolean.TYPE && parameterClass != Boolean.class) {
                        if (!annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                            return false;
                        }
                        creatorCollector.addDelegatingCreator(annotatedMethod);
                        return true;
                    }
                    if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                        creatorCollector.addBooleanCreator(annotatedMethod);
                    }
                    return true;
                }
                if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                    creatorCollector.addDoubleCreator(annotatedMethod);
                }
                return true;
            }
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addLongCreator(annotatedMethod);
            }
            return true;
        } else {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addIntCreator(annotatedMethod);
            }
            return true;
        }
    }

    protected JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver abstractTypeResolver : this._factoryConfig.abstractTypeResolvers()) {
                JavaType findTypeMapping = abstractTypeResolver.findTypeMapping(deserializationConfig, javaType);
                if (findTypeMapping != null && findTypeMapping.getRawClass() != rawClass) {
                    return findTypeMapping;
                }
            }
            return null;
        }
        return null;
    }

    protected void addBeanProps(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        List<BeanPropertyDefinition> findProperties = basicBeanDescription.findProperties();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Boolean findIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(basicBeanDescription.getClassInfo());
        if (findIgnoreUnknownProperties != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(findIgnoreUnknownProperties.booleanValue());
        }
        HashSet<String> arrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
        for (String str : arrayToSet) {
            beanDeserializerBuilder.addIgnorable(str);
        }
        AnnotatedMethod findAnySetter = basicBeanDescription.findAnySetter();
        Set<String> ignoredPropertyNames = findAnySetter == null ? basicBeanDescription.getIgnoredPropertyNames() : basicBeanDescription.getIgnoredPropertyNamesForDeser();
        if (ignoredPropertyNames != null) {
            for (String str2 : ignoredPropertyNames) {
                beanDeserializerBuilder.addIgnorable(str2);
            }
        }
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : findProperties) {
            String name = beanPropertyDefinition.getName();
            if (!arrayToSet.contains(name)) {
                if (beanPropertyDefinition.hasConstructorParameter()) {
                    beanDeserializerBuilder.addCreatorProperty(beanPropertyDefinition);
                } else if (beanPropertyDefinition.hasSetter()) {
                    AnnotatedMethod setter = beanPropertyDefinition.getSetter();
                    if (isIgnorableType(deserializationConfig, basicBeanDescription, setter.getParameterClass(0), hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    } else {
                        SettableBeanProperty constructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, name, setter);
                        if (constructSettableProperty != null) {
                            beanDeserializerBuilder.addProperty(constructSettableProperty);
                        }
                    }
                } else if (beanPropertyDefinition.hasField()) {
                    AnnotatedField field = beanPropertyDefinition.getField();
                    if (isIgnorableType(deserializationConfig, basicBeanDescription, field.getRawType(), hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    } else {
                        SettableBeanProperty constructSettableProperty2 = constructSettableProperty(deserializationConfig, basicBeanDescription, name, field);
                        if (constructSettableProperty2 != null) {
                            beanDeserializerBuilder.addProperty(constructSettableProperty2);
                        }
                    }
                }
            }
        }
        if (findAnySetter != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationConfig, basicBeanDescription, findAnySetter));
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS)) {
            for (BeanPropertyDefinition beanPropertyDefinition2 : findProperties) {
                if (beanPropertyDefinition2.hasGetter()) {
                    String name2 = beanPropertyDefinition2.getName();
                    if (!beanDeserializerBuilder.hasProperty(name2) && !arrayToSet.contains(name2)) {
                        AnnotatedMethod getter = beanPropertyDefinition2.getGetter();
                        Class<?> rawType = getter.getRawType();
                        if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                            if (!arrayToSet.contains(name2) && !beanDeserializerBuilder.hasProperty(name2)) {
                                beanDeserializerBuilder.addProperty(constructSetterlessProperty(deserializationConfig, basicBeanDescription, name2, getter));
                            }
                        }
                    }
                }
            }
        }
    }

    protected void addInjectables(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map<Object, AnnotatedMember> findInjectables = basicBeanDescription.findInjectables();
        if (findInjectables != null) {
            boolean isEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry<Object, AnnotatedMember> entry : findInjectables.entrySet()) {
                AnnotatedMember value = entry.getValue();
                if (isEnabled) {
                    value.fixAccess();
                }
                beanDeserializerBuilder.addInjectable(value.getName(), basicBeanDescription.resolveType(value.getGenericType()), basicBeanDescription.getClassAnnotations(), value, entry.getKey());
            }
        }
    }

    protected void addReferenceProperties(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map<String, AnnotatedMember> findBackReferenceProperties = basicBeanDescription.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Map.Entry<String, AnnotatedMember> entry : findBackReferenceProperties.entrySet()) {
                String key = entry.getKey();
                AnnotatedMember value = entry.getValue();
                if (value instanceof AnnotatedMethod) {
                    beanDeserializerBuilder.addBackReferenceProperty(key, constructSettableProperty(deserializationConfig, basicBeanDescription, value.getName(), (AnnotatedMethod) value));
                } else {
                    beanDeserializerBuilder.addBackReferenceProperty(key, constructSettableProperty(deserializationConfig, basicBeanDescription, value.getName(), (AnnotatedField) value));
                }
            }
        }
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationConfig, basicBeanDescription);
        if (javaType.isAbstract() && !findValueInstantiator.canInstantiate()) {
            return new AbstractDeserializer(javaType);
        }
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = beanDeserializerModifier.updateBuilder(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build(beanProperty);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier2 : this._factoryConfig.deserializerModifiers()) {
                build = beanDeserializerModifier2.modifyDeserializer(deserializationConfig, basicBeanDescription, build);
            }
        }
        return build;
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) throws JsonMappingException {
        SettableBeanProperty constructSettableProperty;
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationConfig, basicBeanDescription));
        addBeanProps(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = basicBeanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null && (constructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, "cause", findMethod)) != null) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("message");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = beanDeserializerModifier.updateBuilder(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build(beanProperty);
        if (build instanceof BeanDeserializer) {
            build = new com.amazon.org.codehaus.jackson.map.deser.std.ThrowableDeserializer((BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier2 : this._factoryConfig.deserializerModifiers()) {
                build = beanDeserializerModifier2.modifyDeserializer(deserializationConfig, basicBeanDescription, build);
            }
        }
        return build;
    }

    protected SettableAnyProperty constructAnySetter(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(1));
        BeanProperty.Std std = new BeanProperty.Std(annotatedMethod.getName(), resolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedMethod, std);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        if (findDeserializerFromAnnotation != null) {
            return new SettableAnyProperty(std, annotatedMethod, resolveType2, findDeserializerFromAnnotation);
        }
        return new SettableAnyProperty(std, annotatedMethod, modifyTypeByAnnotation(deserializationConfig, annotatedMethod, resolveType2, std.getName()), (JsonDeserializer<Object>) null);
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanDeserializerBuilder(basicBeanDescription);
    }

    protected CreatorProperty constructCreatorProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, int i, AnnotatedParameter annotatedParameter, Object obj) throws JsonMappingException {
        JavaType constructType = deserializationConfig.getTypeFactory().constructType(annotatedParameter.getParameterType(), basicBeanDescription.bindingsForBeanType());
        BeanProperty.Std std = new BeanProperty.Std(str, constructType, basicBeanDescription.getClassAnnotations(), annotatedParameter);
        JavaType resolveType = resolveType(deserializationConfig, basicBeanDescription, constructType, annotatedParameter, std);
        if (resolveType != constructType) {
            std = std.withType(resolveType);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedParameter, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedParameter, resolveType, str);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(deserializationConfig, modifyTypeByAnnotation, std);
        }
        CreatorProperty creatorProperty = new CreatorProperty(str, modifyTypeByAnnotation, typeDeserializer, basicBeanDescription.getClassAnnotations(), annotatedParameter, i, obj);
        return findDeserializerFromAnnotation != null ? creatorProperty.mo4140withValueDeserializer(findDeserializerFromAnnotation) : creatorProperty;
    }

    protected ValueInstantiator constructDefaultValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException {
        AnnotatedConstructor findDefaultConstructor;
        boolean isEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        CreatorCollector creatorCollector = new CreatorCollector(basicBeanDescription, isEnabled);
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        if (basicBeanDescription.getType().isConcrete() && (findDefaultConstructor = basicBeanDescription.findDefaultConstructor()) != null) {
            if (isEnabled) {
                ClassUtil.checkAndFixAccess(findDefaultConstructor.mo4213getAnnotated());
            }
            creatorCollector.setDefaultConstructor(findDefaultConstructor);
        }
        VisibilityChecker<?> findAutoDetectVisibility = deserializationConfig.getAnnotationIntrospector().findAutoDetectVisibility(basicBeanDescription.getClassInfo(), deserializationConfig.getDefaultVisibilityChecker());
        _addDeserializerFactoryMethods(deserializationConfig, basicBeanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        _addDeserializerConstructors(deserializationConfig, basicBeanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        return creatorCollector.constructValueInstantiator(deserializationConfig);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(0));
        BeanProperty.Std std = new BeanProperty.Std(str, resolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedMethod, std);
        if (resolveType2 != resolveType) {
            std = std.withType(resolveType2);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, resolveType2, str);
        SettableBeanProperty methodProperty = new SettableBeanProperty.MethodProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        if (findDeserializerFromAnnotation != null) {
            methodProperty = methodProperty.mo4140withValueDeserializer(findDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedMethod);
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            methodProperty.setManagedReferenceName(findReferenceType.getName());
        }
        return methodProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType type = annotatedMethod.getType(basicBeanDescription.bindingsForBeanType());
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, new BeanProperty.Std(str, type, basicBeanDescription.getClassAnnotations(), annotatedMethod));
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, type, str);
        SettableBeanProperty.SetterlessProperty setterlessProperty = new SettableBeanProperty.SetterlessProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        return findDeserializerFromAnnotation != null ? setterlessProperty.mo4140withValueDeserializer((JsonDeserializer) findDeserializerFromAnnotation) : setterlessProperty;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JavaType materializeAbstractType;
        if (javaType.isAbstract()) {
            javaType = mapAbstractType(deserializationConfig, javaType);
        }
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(javaType);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), javaType, null);
        if (modifyTypeByAnnotation.getRawClass() != javaType.getRawClass()) {
            basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(modifyTypeByAnnotation);
            javaType = modifyTypeByAnnotation;
        }
        JsonDeserializer<Object> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationConfig, javaType, basicBeanDescription, beanProperty);
        }
        if (javaType.isAbstract() && (materializeAbstractType = materializeAbstractType(deserializationConfig, basicBeanDescription)) != null) {
            return buildBeanDeserializer(deserializationConfig, materializeAbstractType, (BasicBeanDescription) deserializationConfig.introspect(materializeAbstractType), beanProperty);
        }
        JsonDeserializer<Object> findStdBeanDeserializer = findStdBeanDeserializer(deserializationConfig, deserializerProvider, javaType, beanProperty);
        if (findStdBeanDeserializer != null) {
            return findStdBeanDeserializer;
        }
        if (isPotentialBeanType(javaType.getRawClass())) {
            return buildBeanDeserializer(deserializationConfig, javaType, basicBeanDescription, beanProperty);
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        if (this._factoryConfig.hasKeyDeserializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass());
            for (KeyDeserializers keyDeserializers : this._factoryConfig.keyDeserializers()) {
                KeyDeserializer findKeyDeserializer = keyDeserializers.findKeyDeserializer(javaType, deserializationConfig, basicBeanDescription, beanProperty);
                if (findKeyDeserializer != null) {
                    return findKeyDeserializer;
                }
            }
        }
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass != String.class && rawClass != Object.class) {
            KeyDeserializer keyDeserializer = BasicDeserializerFactory._keyDeserializers.get(javaType);
            if (keyDeserializer != null) {
                return keyDeserializer;
            }
            if (javaType.isEnumType()) {
                return _createEnumKeyDeserializer(deserializationConfig, javaType, beanProperty);
            }
            return com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.findStringBasedKeyDeserializer(deserializationConfig, javaType);
        }
        return com.amazon.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructStringKeyDeserializer(deserializationConfig, javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException {
        ValueInstantiator constructDefaultValueInstantiator;
        AnnotatedClass classInfo = basicBeanDescription.getClassInfo();
        Object findValueInstantiator = deserializationConfig.getAnnotationIntrospector().findValueInstantiator(classInfo);
        if (findValueInstantiator != null) {
            if (findValueInstantiator instanceof ValueInstantiator) {
                constructDefaultValueInstantiator = (ValueInstantiator) findValueInstantiator;
            } else if (findValueInstantiator instanceof Class) {
                Class<? extends ValueInstantiator> cls = (Class) findValueInstantiator;
                if (ValueInstantiator.class.isAssignableFrom(cls)) {
                    constructDefaultValueInstantiator = deserializationConfig.valueInstantiatorInstance(classInfo, cls);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid instantiator Class<?> returned for type ");
                    sb.append(basicBeanDescription);
                    sb.append(RealTimeTextConstants.COLON_SPACE);
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline40(cls, sb, " not a ValueInstantiator"));
                }
            } else {
                throw new IllegalStateException("Invalid value instantiator returned for type " + basicBeanDescription + ": neither a Class nor ValueInstantiator");
            }
        } else {
            constructDefaultValueInstantiator = constructDefaultValueInstantiator(deserializationConfig, basicBeanDescription);
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators valueInstantiators : this._factoryConfig.valueInstantiators()) {
                constructDefaultValueInstantiator = valueInstantiators.findValueInstantiator(deserializationConfig, basicBeanDescription, constructDefaultValueInstantiator);
                if (constructDefaultValueInstantiator == null) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Broken registered ValueInstantiators (of type ");
                    outline107.append(valueInstantiators.getClass().getName());
                    outline107.append("): returned null ValueInstantiator");
                    throw new JsonMappingException(outline107.toString());
                }
            }
        }
        return constructDefaultValueInstantiator;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public final DeserializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool = map.get(cls);
        if (bool == null && (bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(cls)).getClassInfo())) == null) {
            bool = Boolean.FALSE;
        }
        return bool.booleanValue();
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType == null) {
            if (!ClassUtil.isProxyType(cls)) {
                String isLocalType = ClassUtil.isLocalType(cls, true);
                if (isLocalType == null) {
                    return true;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not deserialize Class ");
                outline107.append(cls.getName());
                outline107.append(" (of type ");
                outline107.append(isLocalType);
                outline107.append(") as a Bean");
                throw new IllegalArgumentException(outline107.toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not deserialize Proxy class "), " as a Bean"));
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Can not deserialize Class ");
        outline1072.append(cls.getName());
        outline1072.append(" (of type ");
        outline1072.append(canBeABeanType);
        outline1072.append(") as a Bean");
        throw new IllegalArgumentException(outline1072.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType _mapAbstractType2;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (_mapAbstractType2 == null) {
                return javaType;
            }
            Class<?> rawClass = javaType.getRawClass();
            Class<?> rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                break;
            }
            javaType = _mapAbstractType2;
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + _mapAbstractType2 + ": latter is not a subtype of former");
    }

    protected JavaType materializeAbstractType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) throws JsonMappingException {
        JavaType type = basicBeanDescription.getType();
        for (AbstractTypeResolver abstractTypeResolver : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType = abstractTypeResolver.resolveAbstractType(deserializationConfig, type);
            if (resolveAbstractType != null) {
                return resolveAbstractType;
            }
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.amazon.org.codehaus.jackson.map.DeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactory.Config config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() == BeanDeserializerFactory.class) {
            return new BeanDeserializerFactory(config);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Subtype of BeanDeserializerFactory (");
        outline107.append(getClass().getName());
        outline107.append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ");
        outline107.append("additional deserializer definitions");
        throw new IllegalStateException(outline107.toString());
    }

    public BeanDeserializerFactory(DeserializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedField annotatedField) throws JsonMappingException {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedField.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedField.getGenericType());
        BeanProperty.Std std = new BeanProperty.Std(str, resolveType, basicBeanDescription.getClassAnnotations(), annotatedField);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedField, std);
        if (resolveType2 != resolveType) {
            std = std.withType(resolveType2);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedField, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedField, resolveType2, str);
        SettableBeanProperty fieldProperty = new SettableBeanProperty.FieldProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedField);
        if (findDeserializerFromAnnotation != null) {
            fieldProperty = fieldProperty.mo4140withValueDeserializer(findDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedField);
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            fieldProperty.setManagedReferenceName(findReferenceType.getName());
        }
        return fieldProperty;
    }
}
