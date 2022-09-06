package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
/* loaded from: classes13.dex */
public final class PropertyBasedCreator {
    protected Object[] _defaultValues;
    protected final HashMap<String, SettableBeanProperty> _properties = new HashMap<>();
    protected final SettableBeanProperty[] _propertiesWithInjectables;
    protected final int _propertyCount;
    protected final ValueInstantiator _valueInstantiator;

    public PropertyBasedCreator(ValueInstantiator valueInstantiator) {
        this._valueInstantiator = valueInstantiator;
        SettableBeanProperty[] fromObjectArguments = valueInstantiator.getFromObjectArguments();
        int length = fromObjectArguments.length;
        this._propertyCount = length;
        Object[] objArr = null;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = fromObjectArguments[i];
            this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
            if (settableBeanProperty.getType().isPrimitive()) {
                objArr = objArr == null ? new Object[length] : objArr;
                objArr[i] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
            }
            if (settableBeanProperty.getInjectableValueId() != null) {
                settableBeanPropertyArr = settableBeanPropertyArr == null ? new SettableBeanProperty[length] : settableBeanPropertyArr;
                settableBeanPropertyArr[i] = settableBeanProperty;
            }
        }
        this._defaultValues = objArr;
        this._propertiesWithInjectables = settableBeanPropertyArr;
    }

    public void assignDeserializer(SettableBeanProperty settableBeanProperty, JsonDeserializer<Object> jsonDeserializer) {
        SettableBeanProperty mo4140withValueDeserializer = settableBeanProperty.mo4140withValueDeserializer(jsonDeserializer);
        this._properties.put(mo4140withValueDeserializer.getName(), mo4140withValueDeserializer);
        Object nullValue = jsonDeserializer.getNullValue();
        if (nullValue != null) {
            if (this._defaultValues == null) {
                this._defaultValues = new Object[this._properties.size()];
            }
            this._defaultValues[mo4140withValueDeserializer.getPropertyIndex()] = nullValue;
        }
    }

    public Object build(PropertyValueBuffer propertyValueBuffer) throws IOException {
        Object createFromObjectWith = this._valueInstantiator.createFromObjectWith(propertyValueBuffer.getParameters(this._defaultValues));
        for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
            buffered.assign(createFromObjectWith);
        }
        return createFromObjectWith;
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return this._properties.get(str);
    }

    public Collection<SettableBeanProperty> getCreatorProperties() {
        return this._properties.values();
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyValueBuffer propertyValueBuffer = new PropertyValueBuffer(jsonParser, deserializationContext, this._propertyCount);
        SettableBeanProperty[] settableBeanPropertyArr = this._propertiesWithInjectables;
        if (settableBeanPropertyArr != null) {
            propertyValueBuffer.inject(settableBeanPropertyArr);
        }
        return propertyValueBuffer;
    }
}
