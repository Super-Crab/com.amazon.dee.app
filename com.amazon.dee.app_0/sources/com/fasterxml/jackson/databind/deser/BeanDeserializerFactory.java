package com.fasterxml.jackson.databind.deser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.impl.UnsupportedTypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    private boolean _isSetterlessType(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    protected JsonDeserializer<Object> _findUnsupportedTypeDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        String checkUnsupportedType = BeanUtil.checkUnsupportedType(javaType);
        if (checkUnsupportedType == null || deserializationContext.mo7051getConfig().findMixInClassFor(javaType.getRawClass()) != null) {
            return null;
        }
        return new UnsupportedTypeDeserializer(javaType, checkUnsupportedType);
    }

    protected void _validateSubType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SubTypeValidator.instance().validateSubType(deserializationContext, javaType, beanDescription);
    }

    protected void addBackReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        List<BeanPropertyDefinition> findBackReferences = beanDescription.findBackReferences();
        if (findBackReferences != null) {
            for (BeanPropertyDefinition beanPropertyDefinition : findBackReferences) {
                beanDeserializerBuilder.addBackReferenceProperty(beanPropertyDefinition.findReferenceName(), constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getPrimaryType()));
            }
        }
    }

    protected void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Set<String> emptySet;
        Set<String> set;
        SettableBeanProperty settableBeanProperty;
        CreatorProperty creatorProperty;
        SettableBeanProperty[] fromObjectArguments = beanDescription.getType().isAbstract() ^ true ? beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.mo7051getConfig()) : null;
        boolean z = fromObjectArguments != null;
        JsonIgnoreProperties.Value defaultPropertyIgnorals = deserializationContext.mo7051getConfig().getDefaultPropertyIgnorals(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (defaultPropertyIgnorals != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(defaultPropertyIgnorals.getIgnoreUnknown());
            emptySet = defaultPropertyIgnorals.findIgnoredForDeserialization();
            for (String str : emptySet) {
                beanDeserializerBuilder.addIgnorable(str);
            }
        } else {
            emptySet = Collections.emptySet();
        }
        Set<String> set2 = emptySet;
        JsonIncludeProperties.Value defaultPropertyInclusions = deserializationContext.mo7051getConfig().getDefaultPropertyInclusions(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (defaultPropertyInclusions != null) {
            Set<String> included = defaultPropertyInclusions.getIncluded();
            if (included != null) {
                for (String str2 : included) {
                    beanDeserializerBuilder.addIncludable(str2);
                }
            }
            set = included;
        } else {
            set = null;
        }
        AnnotatedMember findAnySetterAccessor = beanDescription.findAnySetterAccessor();
        if (findAnySetterAccessor != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationContext, beanDescription, findAnySetterAccessor));
        } else {
            Set<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (ignoredPropertyNames != null) {
                for (String str3 : ignoredPropertyNames) {
                    beanDeserializerBuilder.addIgnorable(str3);
                }
            }
        }
        boolean z2 = deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> filterBeanProps = filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), set2, set);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                filterBeanProps = beanDeserializerModifier.updateProperties(deserializationContext.mo7051getConfig(), beanDescription, filterBeanProps);
            }
        }
        for (BeanPropertyDefinition beanPropertyDefinition : filterBeanProps) {
            if (beanPropertyDefinition.hasSetter()) {
                settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getParameterType(0));
            } else if (beanPropertyDefinition.hasField()) {
                settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getType());
            } else {
                AnnotatedMethod getter = beanPropertyDefinition.getGetter();
                if (getter != null) {
                    if (z2 && _isSetterlessType(getter.getRawType())) {
                        if (!beanDeserializerBuilder.hasIgnorable(beanPropertyDefinition.getName())) {
                            settableBeanProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                        }
                    } else if (!beanPropertyDefinition.hasConstructorParameter() && beanPropertyDefinition.getMetadata().getMergeInfo() != null) {
                        settableBeanProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                }
                settableBeanProperty = null;
            }
            if (z && beanPropertyDefinition.hasConstructorParameter()) {
                String name = beanPropertyDefinition.getName();
                int length = fromObjectArguments.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        creatorProperty = null;
                        break;
                    }
                    SettableBeanProperty settableBeanProperty2 = fromObjectArguments[i];
                    if (name.equals(settableBeanProperty2.getName()) && (settableBeanProperty2 instanceof CreatorProperty)) {
                        creatorProperty = (CreatorProperty) settableBeanProperty2;
                        break;
                    }
                    i++;
                }
                if (creatorProperty == null) {
                    ArrayList arrayList = new ArrayList();
                    for (SettableBeanProperty settableBeanProperty3 : fromObjectArguments) {
                        arrayList.add(settableBeanProperty3.getName());
                    }
                    deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "Could not find creator property with name %s (known Creator properties: %s)", ClassUtil.name(name), arrayList);
                } else {
                    if (settableBeanProperty != null) {
                        creatorProperty.setFallbackSetter(settableBeanProperty);
                    }
                    Class<?>[] findViews = beanPropertyDefinition.findViews();
                    if (findViews == null) {
                        findViews = beanDescription.findDefaultViews();
                    }
                    creatorProperty.setViews(findViews);
                    beanDeserializerBuilder.addCreatorProperty(creatorProperty);
                }
            } else if (settableBeanProperty != null) {
                Class<?>[] findViews2 = beanPropertyDefinition.findViews();
                if (findViews2 == null) {
                    findViews2 = beanDescription.findDefaultViews();
                }
                settableBeanProperty.setViews(findViews2);
                beanDeserializerBuilder.addProperty(settableBeanProperty);
            }
        }
    }

    protected void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map<Object, AnnotatedMember> findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            for (Map.Entry<Object, AnnotatedMember> entry : findInjectables.entrySet()) {
                AnnotatedMember value = entry.getValue();
                beanDeserializerBuilder.addInjectable(PropertyName.construct(value.getName()), value.getType(), beanDescription.getClassAnnotations(), value, entry.getKey());
            }
        }
    }

    protected void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        SettableBeanProperty settableBeanProperty;
        PropertyBasedObjectIdGenerator objectIdGeneratorInstance;
        JavaType javaType;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo == null) {
            return;
        }
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
        if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
            PropertyName propertyName = objectIdInfo.getPropertyName();
            settableBeanProperty = beanDeserializerBuilder.findProperty(propertyName);
            if (settableBeanProperty != null) {
                javaType = settableBeanProperty.getType();
                objectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", ClassUtil.getTypeDescription(beanDescription.getType()), ClassUtil.name(propertyName)));
            }
        } else {
            JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType((Class<?>) generatorType), ObjectIdGenerator.class)[0];
            settableBeanProperty = null;
            objectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
            javaType = javaType2;
        }
        beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty, objectIdResolverInstance));
    }

    @Deprecated
    protected void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilder);
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> build;
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            DeserializationConfig mo7051getConfig = deserializationContext.mo7051getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                    constructBeanDeserializerBuilder = beanDeserializerModifier.updateBuilder(mo7051getConfig, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            if (javaType.isAbstract() && !findValueInstantiator.canInstantiate()) {
                build = constructBeanDeserializerBuilder.buildAbstract();
            } else {
                build = constructBeanDeserializerBuilder.build();
            }
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier beanDeserializerModifier2 : this._factoryConfig.deserializerModifiers()) {
                    build = beanDeserializerModifier2.modifyDeserializer(mo7051getConfig, beanDescription, build);
                }
            }
            return build;
        } catch (IllegalArgumentException e) {
            InvalidDefinitionException from = InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e), beanDescription, (BeanPropertyDefinition) null);
            from.initCause(e);
            throw from;
        } catch (NoClassDefFoundError e2) {
            return new ErrorThrowingDeserializer(e2);
        }
    }

    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            DeserializationConfig mo7051getConfig = deserializationContext.mo7051getConfig();
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            JsonPOJOBuilder.Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
            String str = findPOJOBuilderConfig == null ? JsonPOJOBuilder.DEFAULT_BUILD_METHOD : findPOJOBuilderConfig.buildMethodName;
            AnnotatedMethod findMethod = beanDescription.findMethod(str, null);
            if (findMethod != null && mo7051getConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(findMethod.mo7118getMember(), mo7051getConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                    constructBeanDeserializerBuilder = beanDeserializerModifier.updateBuilder(mo7051getConfig, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier beanDeserializerModifier2 : this._factoryConfig.deserializerModifiers()) {
                    buildBuilderBased = beanDeserializerModifier2.modifyDeserializer(mo7051getConfig, beanDescription, buildBuilderBased);
                }
            }
            return buildBuilderBased;
        } catch (IllegalArgumentException e) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e), beanDescription, (BeanPropertyDefinition) null);
        } catch (NoClassDefFoundError e2) {
            return new ErrorThrowingDeserializer(e2);
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SettableBeanProperty constructSettableProperty;
        DeserializationConfig mo7051getConfig = deserializationContext.mo7051getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null && (constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.mo7051getConfig(), findMethod, new PropertyName("cause")), findMethod.getParameterType(0))) != null) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = beanDeserializerModifier.updateBuilder(mo7051getConfig, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = new ThrowableDeserializer((BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier2 : this._factoryConfig.deserializerModifiers()) {
                build = beanDeserializerModifier2.modifyDeserializer(mo7051getConfig, beanDescription, build);
            }
        }
        return build;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.fasterxml.jackson.databind.deser.SettableAnyProperty constructAnySetter(com.fasterxml.jackson.databind.DeserializationContext r9, com.fasterxml.jackson.databind.BeanDescription r10, com.fasterxml.jackson.databind.introspect.AnnotatedMember r11) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r8 = this;
            boolean r0 = r11 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L2a
            r10 = r11
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r10 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r10
            com.fasterxml.jackson.databind.JavaType r0 = r10.getParameterType(r2)
            com.fasterxml.jackson.databind.JavaType r10 = r10.getParameterType(r1)
            com.fasterxml.jackson.databind.JavaType r10 = r8.resolveMemberAndTypeAnnotations(r9, r11, r10)
            com.fasterxml.jackson.databind.BeanProperty$Std r7 = new com.fasterxml.jackson.databind.BeanProperty$Std
            java.lang.String r1 = r11.getName()
            com.fasterxml.jackson.databind.PropertyName r2 = com.fasterxml.jackson.databind.PropertyName.construct(r1)
            r4 = 0
            com.fasterxml.jackson.databind.PropertyMetadata r6 = com.fasterxml.jackson.databind.PropertyMetadata.STD_OPTIONAL
            r1 = r7
            r3 = r10
            r5 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r10 = r0
            goto L55
        L2a:
            boolean r0 = r11 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedField
            if (r0 == 0) goto L99
            r10 = r11
            com.fasterxml.jackson.databind.introspect.AnnotatedField r10 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r10
            com.fasterxml.jackson.databind.JavaType r10 = r10.getType()
            com.fasterxml.jackson.databind.JavaType r2 = r8.resolveMemberAndTypeAnnotations(r9, r11, r10)
            com.fasterxml.jackson.databind.JavaType r10 = r2.mo7229getKeyType()
            com.fasterxml.jackson.databind.JavaType r6 = r2.mo7243getContentType()
            com.fasterxml.jackson.databind.BeanProperty$Std r7 = new com.fasterxml.jackson.databind.BeanProperty$Std
            java.lang.String r0 = r11.getName()
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.construct(r0)
            r3 = 0
            com.fasterxml.jackson.databind.PropertyMetadata r5 = com.fasterxml.jackson.databind.PropertyMetadata.STD_OPTIONAL
            r0 = r7
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r3 = r6
            r1 = r7
        L55:
            com.fasterxml.jackson.databind.KeyDeserializer r0 = r8.findKeyDeserializerFromAnnotation(r9, r11)
            if (r0 != 0) goto L61
            java.lang.Object r0 = r10.getValueHandler()
            com.fasterxml.jackson.databind.KeyDeserializer r0 = (com.fasterxml.jackson.databind.KeyDeserializer) r0
        L61:
            if (r0 != 0) goto L69
            com.fasterxml.jackson.databind.KeyDeserializer r10 = r9.findKeyDeserializer(r10, r1)
        L67:
            r4 = r10
            goto L75
        L69:
            boolean r10 = r0 instanceof com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer
            if (r10 == 0) goto L74
            com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer r0 = (com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer) r0
            com.fasterxml.jackson.databind.KeyDeserializer r10 = r0.createContextual(r9, r1)
            goto L67
        L74:
            r4 = r0
        L75:
            com.fasterxml.jackson.databind.JsonDeserializer r10 = r8.findContentDeserializerFromAnnotation(r9, r11)
            if (r10 != 0) goto L81
            java.lang.Object r10 = r3.getValueHandler()
            com.fasterxml.jackson.databind.JsonDeserializer r10 = (com.fasterxml.jackson.databind.JsonDeserializer) r10
        L81:
            if (r10 == 0) goto L89
            com.fasterxml.jackson.databind.JsonDeserializer r9 = r9.handlePrimaryContextualization(r10, r1, r3)
            r5 = r9
            goto L8a
        L89:
            r5 = r10
        L8a:
            java.lang.Object r9 = r3.getTypeHandler()
            r6 = r9
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r6 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r6
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r9 = new com.fasterxml.jackson.databind.deser.SettableAnyProperty
            r0 = r9
            r2 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r9
        L99:
            com.fasterxml.jackson.databind.JavaType r10 = r10.getType()
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.Class r11 = r11.getClass()
            r0[r2] = r11
            java.lang.String r11 = "Unrecognized mutator type for any setter: %s"
            java.lang.String r11 = java.lang.String.format(r11, r0)
            java.lang.Object r9 = r9.reportBadDefinition(r10, r11)
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r9 = (com.fasterxml.jackson.databind.deser.SettableAnyProperty) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.constructAnySetter(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.introspect.AnnotatedMember):com.fasterxml.jackson.databind.deser.SettableAnyProperty");
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType) throws JsonMappingException {
        SettableBeanProperty fieldProperty;
        AnnotatedMember nonConstructorMutator = beanPropertyDefinition.getNonConstructorMutator();
        if (nonConstructorMutator == null) {
            deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "No non-constructor mutator available", new Object[0]);
        }
        JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, nonConstructorMutator, javaType);
        TypeDeserializer typeDeserializer = (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler();
        if (nonConstructorMutator instanceof AnnotatedMethod) {
            fieldProperty = new MethodProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedMethod) nonConstructorMutator);
        } else {
            fieldProperty = new FieldProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedField) nonConstructorMutator);
        }
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, nonConstructorMutator);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
        }
        if (findDeserializerFromAnnotation != null) {
            fieldProperty = fieldProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, fieldProperty, resolveMemberAndTypeAnnotations));
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = beanPropertyDefinition.findReferenceType();
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            fieldProperty.setManagedReferenceName(findReferenceType.getName());
        }
        ObjectIdInfo findObjectIdInfo = beanPropertyDefinition.findObjectIdInfo();
        if (findObjectIdInfo != null) {
            fieldProperty.setObjectIdInfo(findObjectIdInfo);
        }
        return fieldProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, getter, getter.getType());
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
        }
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, setterlessProperty, resolveMemberAndTypeAnnotations)) : setterlessProperty;
    }

    @Override // com.fasterxml.jackson.databind.deser.DeserializerFactory
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType materializeAbstractType;
        DeserializationConfig mo7051getConfig = deserializationContext.mo7051getConfig();
        JsonDeserializer<?> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, mo7051getConfig, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                    _findCustomBeanDeserializer = beanDeserializerModifier.modifyDeserializer(deserializationContext.mo7051getConfig(), beanDescription, _findCustomBeanDeserializer);
                }
            }
            return _findCustomBeanDeserializer;
        } else if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        } else {
            if (javaType.isAbstract() && !javaType.isPrimitive() && !javaType.isEnumType() && (materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription)) != null) {
                return buildBeanDeserializer(deserializationContext, materializeAbstractType, mo7051getConfig.introspect(materializeAbstractType));
            }
            JsonDeserializer<?> findStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
            if (findStdDeserializer != null) {
                return findStdDeserializer;
            }
            if (!isPotentialBeanType(javaType.getRawClass())) {
                return null;
            }
            _validateSubType(deserializationContext, javaType, beanDescription);
            JsonDeserializer<Object> _findUnsupportedTypeDeserializer = _findUnsupportedTypeDeserializer(deserializationContext, javaType, beanDescription);
            return _findUnsupportedTypeDeserializer != null ? _findUnsupportedTypeDeserializer : buildBeanDeserializer(deserializationContext, javaType, beanDescription);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.DeserializerFactory
    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        JavaType constructType;
        if (deserializationContext.isEnabled(MapperFeature.INFER_BUILDER_TYPE_BINDINGS)) {
            constructType = deserializationContext.getTypeFactory().constructParametricType(cls, javaType.getBindings());
        } else {
            constructType = deserializationContext.constructType(cls);
        }
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.mo7051getConfig().introspectForBuilder(constructType, beanDescription));
    }

    @Deprecated
    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        return filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, list, set, null);
    }

    protected JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier beanDeserializerModifier : this._factoryConfig.deserializerModifiers()) {
                findDefaultDeserializer = beanDeserializerModifier.modifyDeserializer(deserializationContext.mo7051getConfig(), beanDescription, findDefaultDeserializer);
            }
        }
        return findDefaultDeserializer;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanPropertyDefinition beanPropertyDefinition, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool;
        Boolean bool2 = map.get(cls);
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (cls != String.class && !cls.isPrimitive()) {
            bool = deserializationConfig.getConfigOverride(cls).getIsIgnoredType();
            if (bool == null) {
                bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
                if (bool == null) {
                    bool = Boolean.FALSE;
                }
            }
        } else {
            bool = Boolean.FALSE;
        }
        map.put(cls, bool);
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
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot deserialize Class ");
                outline107.append(cls.getName());
                outline107.append(" (of type ");
                outline107.append(isLocalType);
                outline107.append(") as a Bean");
                throw new IllegalArgumentException(outline107.toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Cannot deserialize Proxy class "), " as a Bean"));
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Cannot deserialize Class ");
        outline1072.append(cls.getName());
        outline1072.append(" (of type ");
        outline1072.append(canBeABeanType);
        outline1072.append(") as a Bean");
        throw new IllegalArgumentException(outline1072.toString());
    }

    protected JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        for (AbstractTypeResolver abstractTypeResolver : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType = abstractTypeResolver.resolveAbstractType(deserializationContext.mo7051getConfig(), beanDescription);
            if (resolveAbstractType != null) {
                return resolveAbstractType;
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.deser.BasicDeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        if (this._factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
        return new BeanDeserializerFactory(deserializerFactoryConfig);
    }

    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set, Set<String> set2) {
        Class<?> rawPrimaryType;
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : list) {
            String name = beanPropertyDefinition.getName();
            if (!IgnorePropertiesUtil.shouldIgnore(name, set, set2)) {
                if (!beanPropertyDefinition.hasConstructorParameter() && (rawPrimaryType = beanPropertyDefinition.getRawPrimaryType()) != null && isIgnorableType(deserializationContext.mo7051getConfig(), beanPropertyDefinition, rawPrimaryType, hashMap)) {
                    beanDeserializerBuilder.addIgnorable(name);
                } else {
                    arrayList.add(beanPropertyDefinition);
                }
            }
        }
        return arrayList;
    }
}
