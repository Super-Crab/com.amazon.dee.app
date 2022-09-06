package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class ValueInstantiator {
    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public boolean canCreateFromString() {
        return false;
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public boolean canCreateUsingDelegate() {
        return getDelegateType() != null;
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public Object createFromBoolean(boolean z) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" from JSON boolean value");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createFromDouble(double d) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" from JSON floating-point number");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createFromInt(int i) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" from JSON int number");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createFromLong(long j) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" from JSON long number");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createFromObjectWith(Object[] objArr) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" with arguments");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createFromString(String str) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" from JSON String");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createUsingDefault() throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append("; no default creator found");
        throw new JsonMappingException(outline107.toString());
    }

    public Object createUsingDelegate(Object obj) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not instantiate value of type ");
        outline107.append(getValueTypeDesc());
        outline107.append(" using delegate");
        throw new JsonMappingException(outline107.toString());
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public JavaType getDelegateType() {
        return null;
    }

    public SettableBeanProperty[] getFromObjectArguments() {
        return null;
    }

    public abstract String getValueTypeDesc();

    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }
}
