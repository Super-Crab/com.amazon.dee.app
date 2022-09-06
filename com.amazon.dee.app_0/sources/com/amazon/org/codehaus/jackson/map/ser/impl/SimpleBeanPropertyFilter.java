package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter {

    /* loaded from: classes13.dex */
    public static class FilterExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set<String> _propertiesToInclude;

        public FilterExceptFilter(Set<String> set) {
            this._propertiesToInclude = set;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyFilter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws Exception {
            if (this._propertiesToInclude.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class SerializeExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set<String> _propertiesToExclude;

        public SerializeExceptFilter(Set<String> set) {
            this._propertiesToExclude = set;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyFilter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws Exception {
            if (!this._propertiesToExclude.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    protected SimpleBeanPropertyFilter() {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set<String> set) {
        return new FilterExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set<String> set) {
        return new SerializeExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(String... strArr) {
        HashSet hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new FilterExceptFilter(hashSet);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(String... strArr) {
        HashSet hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new SerializeExceptFilter(hashSet);
    }
}
