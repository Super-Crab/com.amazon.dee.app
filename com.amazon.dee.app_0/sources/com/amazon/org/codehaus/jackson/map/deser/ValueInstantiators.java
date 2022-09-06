package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
/* loaded from: classes13.dex */
public interface ValueInstantiators {

    /* loaded from: classes13.dex */
    public static class Base implements ValueInstantiators {
        @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators
        public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
            return valueInstantiator;
        }
    }

    ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator);
}
