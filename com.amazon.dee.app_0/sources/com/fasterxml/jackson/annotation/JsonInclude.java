package com.fasterxml.jackson.annotation;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface JsonInclude {

    /* loaded from: classes2.dex */
    public enum Include {
        ALWAYS,
        NON_NULL,
        NON_ABSENT,
        NON_EMPTY,
        NON_DEFAULT,
        CUSTOM,
        USE_DEFAULTS
    }

    Include content() default Include.ALWAYS;

    Class<?> contentFilter() default Void.class;

    Include value() default Include.ALWAYS;

    Class<?> valueFilter() default Void.class;

    /* loaded from: classes2.dex */
    public static class Value implements JacksonAnnotationValue<JsonInclude>, Serializable {
        protected static final Value EMPTY;
        private static final long serialVersionUID = 1;
        protected final Class<?> _contentFilter;
        protected final Include _contentInclusion;
        protected final Class<?> _valueFilter;
        protected final Include _valueInclusion;

        static {
            Include include = Include.USE_DEFAULTS;
            EMPTY = new Value(include, include, null, null);
        }

        public Value(JsonInclude jsonInclude) {
            this(jsonInclude.value(), jsonInclude.content(), jsonInclude.valueFilter(), jsonInclude.contentFilter());
        }

        public static Value construct(Include include, Include include2) {
            if ((include != Include.USE_DEFAULTS && include != null) || (include2 != Include.USE_DEFAULTS && include2 != null)) {
                return new Value(include, include2, null, null);
            }
            return EMPTY;
        }

        public static Value empty() {
            return EMPTY;
        }

        public static Value from(JsonInclude jsonInclude) {
            if (jsonInclude == null) {
                return EMPTY;
            }
            Include value = jsonInclude.value();
            Include content = jsonInclude.content();
            Include include = Include.USE_DEFAULTS;
            if (value == include && content == include) {
                return EMPTY;
            }
            Class<?> valueFilter = jsonInclude.valueFilter();
            if (valueFilter == Void.class) {
                valueFilter = null;
            }
            Class<?> contentFilter = jsonInclude.contentFilter();
            if (contentFilter == Void.class) {
                contentFilter = null;
            }
            return new Value(value, content, valueFilter, contentFilter);
        }

        public static Value merge(Value value, Value value2) {
            return value == null ? value2 : value.withOverrides(value2);
        }

        public static Value mergeAll(Value... valueArr) {
            Value value = null;
            for (Value value2 : valueArr) {
                if (value2 != null) {
                    if (value != null) {
                        value2 = value.withOverrides(value2);
                    }
                    value = value2;
                }
            }
            return value;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != Value.class) {
                return false;
            }
            Value value = (Value) obj;
            return value._valueInclusion == this._valueInclusion && value._contentInclusion == this._contentInclusion && value._valueFilter == this._valueFilter && value._contentFilter == this._contentFilter;
        }

        public Class<?> getContentFilter() {
            return this._contentFilter;
        }

        public Include getContentInclusion() {
            return this._contentInclusion;
        }

        public Class<?> getValueFilter() {
            return this._valueFilter;
        }

        public Include getValueInclusion() {
            return this._valueInclusion;
        }

        public int hashCode() {
            return this._contentInclusion.hashCode() + (this._valueInclusion.hashCode() << 2);
        }

        protected Object readResolve() {
            Include include = this._valueInclusion;
            Include include2 = Include.USE_DEFAULTS;
            return (include == include2 && this._contentInclusion == include2 && this._valueFilter == null && this._contentFilter == null) ? EMPTY : this;
        }

        public String toString() {
            StringBuilder outline105 = GeneratedOutlineSupport1.outline105(80, "JsonInclude.Value(value=");
            outline105.append(this._valueInclusion);
            outline105.append(",content=");
            outline105.append(this._contentInclusion);
            if (this._valueFilter != null) {
                outline105.append(",valueFilter=");
                GeneratedOutlineSupport1.outline146(this._valueFilter, outline105, ".class");
            }
            if (this._contentFilter != null) {
                outline105.append(",contentFilter=");
                GeneratedOutlineSupport1.outline146(this._contentFilter, outline105, ".class");
            }
            outline105.append(')');
            return outline105.toString();
        }

        @Override // com.fasterxml.jackson.annotation.JacksonAnnotationValue
        public Class<JsonInclude> valueFor() {
            return JsonInclude.class;
        }

        public Value withContentFilter(Class<?> cls) {
            Include include;
            if (cls != null && cls != Void.class) {
                include = Include.CUSTOM;
            } else {
                include = Include.USE_DEFAULTS;
                cls = null;
            }
            return construct(this._valueInclusion, include, this._valueFilter, cls);
        }

        public Value withContentInclusion(Include include) {
            return include == this._contentInclusion ? this : new Value(this._valueInclusion, include, this._valueFilter, this._contentFilter);
        }

        public Value withOverrides(Value value) {
            if (value != null && value != EMPTY) {
                Include include = value._valueInclusion;
                Include include2 = value._contentInclusion;
                Class<?> cls = value._valueFilter;
                Class<?> cls2 = value._contentFilter;
                boolean z = true;
                boolean z2 = (include == this._valueInclusion || include == Include.USE_DEFAULTS) ? false : true;
                boolean z3 = (include2 == this._contentInclusion || include2 == Include.USE_DEFAULTS) ? false : true;
                Class<?> cls3 = this._valueFilter;
                if (cls == cls3 && cls2 == cls3) {
                    z = false;
                }
                if (z2) {
                    if (z3) {
                        return new Value(include, include2, cls, cls2);
                    }
                    return new Value(include, this._contentInclusion, cls, cls2);
                } else if (z3) {
                    return new Value(this._valueInclusion, include2, cls, cls2);
                } else {
                    if (z) {
                        return new Value(this._valueInclusion, this._contentInclusion, cls, cls2);
                    }
                }
            }
            return this;
        }

        public Value withValueFilter(Class<?> cls) {
            Include include;
            if (cls != null && cls != Void.class) {
                include = Include.CUSTOM;
            } else {
                include = Include.USE_DEFAULTS;
                cls = null;
            }
            return construct(include, this._contentInclusion, cls, this._contentFilter);
        }

        public Value withValueInclusion(Include include) {
            return include == this._valueInclusion ? this : new Value(include, this._contentInclusion, this._valueFilter, this._contentFilter);
        }

        protected Value(Include include, Include include2, Class<?> cls, Class<?> cls2) {
            this._valueInclusion = include == null ? Include.USE_DEFAULTS : include;
            this._contentInclusion = include2 == null ? Include.USE_DEFAULTS : include2;
            Class<?> cls3 = null;
            this._valueFilter = cls == Void.class ? null : cls;
            this._contentFilter = cls2 != Void.class ? cls2 : cls3;
        }

        public static Value construct(Include include, Include include2, Class<?> cls, Class<?> cls2) {
            if (cls == Void.class) {
                cls = null;
            }
            if (cls2 == Void.class) {
                cls2 = null;
            }
            if ((include == Include.USE_DEFAULTS || include == null) && ((include2 == Include.USE_DEFAULTS || include2 == null) && cls == null && cls2 == null)) {
                return EMPTY;
            }
            return new Value(include, include2, cls, cls2);
        }
    }
}
