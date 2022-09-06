package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.deser.impl.CreatorProperty;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public class StdValueInstantiator extends ValueInstantiator {
    protected final boolean _cfgEmptyStringsAsObjects;
    protected CreatorProperty[] _constructorArguments;
    protected AnnotatedWithParams _defaultCreator;
    protected AnnotatedWithParams _delegateCreator;
    protected JavaType _delegateType;
    protected AnnotatedWithParams _fromBooleanCreator;
    protected AnnotatedWithParams _fromDoubleCreator;
    protected AnnotatedWithParams _fromIntCreator;
    protected AnnotatedWithParams _fromLongCreator;
    protected AnnotatedWithParams _fromStringCreator;
    protected final String _valueTypeDesc;
    protected AnnotatedWithParams _withArgsCreator;

    public StdValueInstantiator(DeserializationConfig deserializationConfig, Class<?> cls) {
        this._cfgEmptyStringsAsObjects = deserializationConfig == null ? false : deserializationConfig.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        this._valueTypeDesc = cls == null ? "UNKNOWN TYPE" : cls.getName();
    }

    protected Object _createFromStringFallbacks(String str) throws IOException, JsonProcessingException {
        if (this._fromBooleanCreator != null) {
            String trim = str.trim();
            if ("true".equals(trim)) {
                return createFromBoolean(true);
            }
            if (PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equals(trim)) {
                return createFromBoolean(false);
            }
        }
        if (!this._cfgEmptyStringsAsObjects || str.length() != 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
            outline107.append(getValueTypeDesc());
            outline107.append(" from JSON String; no single-String constructor/factory method");
            throw new JsonMappingException(outline107.toString());
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromBoolean() {
        return this._fromBooleanCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromDouble() {
        return this._fromDoubleCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromInt() {
        return this._fromIntCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromLong() {
        return this._fromLongCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromObjectWith() {
        return this._withArgsCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateFromString() {
        return this._fromStringCreator != null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public boolean canCreateUsingDefault() {
        return this._defaultCreator != null;
    }

    public void configureFromBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromBooleanCreator = annotatedWithParams;
    }

    public void configureFromDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromDoubleCreator = annotatedWithParams;
    }

    public void configureFromIntCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromIntCreator = annotatedWithParams;
    }

    public void configureFromLongCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromLongCreator = annotatedWithParams;
    }

    public void configureFromObjectSettings(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, JavaType javaType, AnnotatedWithParams annotatedWithParams3, CreatorProperty[] creatorPropertyArr) {
        this._defaultCreator = annotatedWithParams;
        this._delegateCreator = annotatedWithParams2;
        this._delegateType = javaType;
        this._withArgsCreator = annotatedWithParams3;
        this._constructorArguments = creatorPropertyArr;
    }

    public void configureFromStringCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromStringCreator = annotatedWithParams;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromBoolean(boolean z) throws IOException, JsonProcessingException {
        try {
            if (this._fromBooleanCreator != null) {
                return this._fromBooleanCreator.call1(Boolean.valueOf(z));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
            outline107.append(getValueTypeDesc());
            outline107.append(" from JSON boolean value; no single-boolean/Boolean-arg constructor/factory method");
            throw new JsonMappingException(outline107.toString());
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromDouble(double d) throws IOException, JsonProcessingException {
        try {
            if (this._fromDoubleCreator != null) {
                return this._fromDoubleCreator.call1(Double.valueOf(d));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
            outline107.append(getValueTypeDesc());
            outline107.append(" from JSON floating-point number; no one-double/Double-arg constructor/factory method");
            throw new JsonMappingException(outline107.toString());
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromInt(int i) throws IOException, JsonProcessingException {
        try {
            if (this._fromIntCreator != null) {
                return this._fromIntCreator.call1(Integer.valueOf(i));
            }
            if (this._fromLongCreator != null) {
                return this._fromLongCreator.call1(Long.valueOf(i));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
            outline107.append(getValueTypeDesc());
            outline107.append(" from JSON integral number; no single-int-arg constructor/factory method");
            throw new JsonMappingException(outline107.toString());
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromLong(long j) throws IOException, JsonProcessingException {
        try {
            if (this._fromLongCreator != null) {
                return this._fromLongCreator.call1(Long.valueOf(j));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
            outline107.append(getValueTypeDesc());
            outline107.append(" from JSON long integral number; no single-long-arg constructor/factory method");
            throw new JsonMappingException(outline107.toString());
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromObjectWith(Object[] objArr) throws IOException, JsonProcessingException {
        AnnotatedWithParams annotatedWithParams = this._withArgsCreator;
        if (annotatedWithParams != null) {
            try {
                return annotatedWithParams.call(objArr);
            } catch (Exception e) {
                throw wrapException(e);
            } catch (ExceptionInInitializerError e2) {
                throw wrapException(e2);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No with-args constructor for ");
        outline107.append(getValueTypeDesc());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createFromString(String str) throws IOException, JsonProcessingException {
        AnnotatedWithParams annotatedWithParams = this._fromStringCreator;
        if (annotatedWithParams != null) {
            try {
                return annotatedWithParams.call1(str);
            } catch (Exception e) {
                throw wrapException(e);
            }
        }
        return _createFromStringFallbacks(str);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createUsingDefault() throws IOException, JsonProcessingException {
        AnnotatedWithParams annotatedWithParams = this._defaultCreator;
        if (annotatedWithParams != null) {
            try {
                return annotatedWithParams.call();
            } catch (Exception e) {
                throw wrapException(e);
            } catch (ExceptionInInitializerError e2) {
                throw wrapException(e2);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No default constructor for ");
        outline107.append(getValueTypeDesc());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public Object createUsingDelegate(Object obj) throws IOException, JsonProcessingException {
        AnnotatedWithParams annotatedWithParams = this._delegateCreator;
        if (annotatedWithParams != null) {
            try {
                return annotatedWithParams.call1(obj);
            } catch (Exception e) {
                throw wrapException(e);
            } catch (ExceptionInInitializerError e2) {
                throw wrapException(e2);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No delegate constructor for ");
        outline107.append(getValueTypeDesc());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public AnnotatedWithParams getDefaultCreator() {
        return this._defaultCreator;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public AnnotatedWithParams getDelegateCreator() {
        return this._delegateCreator;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public JavaType getDelegateType() {
        return this._delegateType;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public SettableBeanProperty[] getFromObjectArguments() {
        return this._constructorArguments;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public String getValueTypeDesc() {
        return this._valueTypeDesc;
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator
    public AnnotatedWithParams getWithArgsCreator() {
        return this._withArgsCreator;
    }

    protected JsonMappingException wrapException(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Instantiation of ");
        outline107.append(getValueTypeDesc());
        outline107.append(" value failed: ");
        outline107.append(th.getMessage());
        return new JsonMappingException(outline107.toString(), th);
    }

    public StdValueInstantiator(DeserializationConfig deserializationConfig, JavaType javaType) {
        this._cfgEmptyStringsAsObjects = deserializationConfig == null ? false : deserializationConfig.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        this._valueTypeDesc = javaType == null ? "UNKNOWN TYPE" : javaType.toString();
    }

    protected StdValueInstantiator(StdValueInstantiator stdValueInstantiator) {
        this._cfgEmptyStringsAsObjects = stdValueInstantiator._cfgEmptyStringsAsObjects;
        this._valueTypeDesc = stdValueInstantiator._valueTypeDesc;
        this._defaultCreator = stdValueInstantiator._defaultCreator;
        this._constructorArguments = stdValueInstantiator._constructorArguments;
        this._withArgsCreator = stdValueInstantiator._withArgsCreator;
        this._delegateType = stdValueInstantiator._delegateType;
        this._delegateCreator = stdValueInstantiator._delegateCreator;
        this._fromStringCreator = stdValueInstantiator._fromStringCreator;
        this._fromIntCreator = stdValueInstantiator._fromIntCreator;
        this._fromLongCreator = stdValueInstantiator._fromLongCreator;
        this._fromDoubleCreator = stdValueInstantiator._fromDoubleCreator;
        this._fromBooleanCreator = stdValueInstantiator._fromBooleanCreator;
    }
}
