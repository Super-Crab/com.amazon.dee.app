package com.amazon.org.codehaus.jackson.map;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public abstract class InjectableValues {

    /* loaded from: classes13.dex */
    public static class Std extends InjectableValues {
        protected final Map<String, Object> _values;

        public Std() {
            this(new HashMap());
        }

        public Std addValue(String str, Object obj) {
            this._values.put(str, obj);
            return this;
        }

        @Override // com.amazon.org.codehaus.jackson.map.InjectableValues
        public Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) {
            if (!(obj instanceof String)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Unrecognized inject value id type (", obj == null ? "[null]" : obj.getClass().getName(), "), expecting String"));
            }
            String str = (String) obj;
            Object obj3 = this._values.get(str);
            if (obj3 != null || this._values.containsKey(str)) {
                return obj3;
            }
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("No injectable id with value '", str, "' found (for property '");
            outline115.append(beanProperty.getName());
            outline115.append("')");
            throw new IllegalArgumentException(outline115.toString());
        }

        public Std(Map<String, Object> map) {
            this._values = map;
        }

        public Std addValue(Class<?> cls, Object obj) {
            this._values.put(cls.getName(), obj);
            return this;
        }
    }

    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2);
}
