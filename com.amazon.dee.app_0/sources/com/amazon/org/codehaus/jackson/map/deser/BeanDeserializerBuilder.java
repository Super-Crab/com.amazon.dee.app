package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import com.amazon.org.codehaus.jackson.map.deser.impl.ValueInjector;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.amazon.org.codehaus.jackson.map.util.Annotations;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes13.dex */
public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BasicBeanDescription _beanDesc;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected List<ValueInjector> _injectables;
    protected final HashMap<String, SettableBeanProperty> _properties = new LinkedHashMap();
    protected ValueInstantiator _valueInstantiator;

    public BeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        this._beanDesc = basicBeanDescription;
    }

    private static HashMap<String, SettableBeanProperty> _copy(HashMap<String, SettableBeanProperty> hashMap) {
        if (hashMap == null) {
            return null;
        }
        return new HashMap<>(hashMap);
    }

    public void addBackReferenceProperty(String str, SettableBeanProperty settableBeanProperty) {
        if (this._backRefProperties == null) {
            this._backRefProperties = new HashMap<>(4);
        }
        this._backRefProperties.put(str, settableBeanProperty);
        HashMap<String, SettableBeanProperty> hashMap = this._properties;
        if (hashMap != null) {
            hashMap.remove(settableBeanProperty.getName());
        }
    }

    public void addCreatorProperty(BeanPropertyDefinition beanPropertyDefinition) {
    }

    public void addIgnorable(String str) {
        if (this._ignorableProps == null) {
            this._ignorableProps = new HashSet<>();
        }
        this._ignorableProps.add(str);
    }

    public void addInjectable(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        if (this._injectables == null) {
            this._injectables = new ArrayList();
        }
        this._injectables.add(new ValueInjector(str, javaType, annotations, annotatedMember, obj));
    }

    public void addOrReplaceProperty(SettableBeanProperty settableBeanProperty, boolean z) {
        this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty put = this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
        if (put == null || put == settableBeanProperty) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Duplicate property '");
        outline107.append(settableBeanProperty.getName());
        outline107.append("' for ");
        outline107.append(this._beanDesc.getType());
        throw new IllegalArgumentException(outline107.toString());
    }

    public JsonDeserializer<?> build(BeanProperty beanProperty) {
        BeanPropertyMap beanPropertyMap = new BeanPropertyMap(this._properties.values());
        beanPropertyMap.assignIndexes();
        return new BeanDeserializer(this._beanDesc, beanProperty, this._valueInstantiator, beanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, this._anySetter, this._injectables);
    }

    public Iterator<SettableBeanProperty> getProperties() {
        return this._properties.values().iterator();
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public boolean hasProperty(String str) {
        return this._properties.containsKey(str);
    }

    public SettableBeanProperty removeProperty(String str) {
        return this._properties.remove(str);
    }

    public void setAnySetter(SettableAnyProperty settableAnyProperty) {
        if (this._anySetter != null && settableAnyProperty != null) {
            throw new IllegalStateException("_anySetter already set to non-null");
        }
        this._anySetter = settableAnyProperty;
    }

    public void setIgnoreUnknownProperties(boolean z) {
        this._ignoreAllUnknown = z;
    }

    public void setValueInstantiator(ValueInstantiator valueInstantiator) {
        this._valueInstantiator = valueInstantiator;
    }

    protected BeanDeserializerBuilder(BeanDeserializerBuilder beanDeserializerBuilder) {
        this._beanDesc = beanDeserializerBuilder._beanDesc;
        this._anySetter = beanDeserializerBuilder._anySetter;
        this._ignoreAllUnknown = beanDeserializerBuilder._ignoreAllUnknown;
        this._properties.putAll(beanDeserializerBuilder._properties);
        this._backRefProperties = _copy(beanDeserializerBuilder._backRefProperties);
        this._ignorableProps = beanDeserializerBuilder._ignorableProps;
        this._valueInstantiator = beanDeserializerBuilder._valueInstantiator;
    }
}
