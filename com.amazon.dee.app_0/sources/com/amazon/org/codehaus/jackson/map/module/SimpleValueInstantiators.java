package com.amazon.org.codehaus.jackson.map.module;

import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class SimpleValueInstantiators extends ValueInstantiators.Base {
    protected HashMap<ClassKey, ValueInstantiator> _classMappings = new HashMap<>();

    public SimpleValueInstantiators addValueInstantiator(Class<?> cls, ValueInstantiator valueInstantiator) {
        this._classMappings.put(new ClassKey(cls), valueInstantiator);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators.Base, com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators
    public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
        ValueInstantiator valueInstantiator2 = this._classMappings.get(new ClassKey(beanDescription.getBeanClass()));
        return valueInstantiator2 == null ? valueInstantiator : valueInstantiator2;
    }
}
