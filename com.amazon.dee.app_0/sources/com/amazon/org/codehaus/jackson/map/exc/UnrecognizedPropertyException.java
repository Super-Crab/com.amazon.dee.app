package com.amazon.org.codehaus.jackson.map.exc;

import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class UnrecognizedPropertyException extends JsonMappingException {
    private static final long serialVersionUID = 1;
    protected final Class<?> _referringClass;
    protected final String _unrecognizedPropertyName;

    public UnrecognizedPropertyException(String str, JsonLocation jsonLocation, Class<?> cls, String str2) {
        super(str, jsonLocation);
        this._referringClass = cls;
        this._unrecognizedPropertyName = str2;
    }

    public static UnrecognizedPropertyException from(JsonParser jsonParser, Object obj, String str) {
        Class<?> cls;
        if (obj != null) {
            if (obj instanceof Class) {
                cls = (Class) obj;
            } else {
                cls = obj.getClass();
            }
            UnrecognizedPropertyException unrecognizedPropertyException = new UnrecognizedPropertyException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline115("Unrecognized field \"", str, "\" (Class "), "), not marked as ignorable"), jsonParser.getCurrentLocation(), cls, str);
            unrecognizedPropertyException.prependPath(obj, str);
            return unrecognizedPropertyException;
        }
        throw new IllegalArgumentException();
    }

    public Class<?> getReferringClass() {
        return this._referringClass;
    }

    public String getUnrecognizedPropertyName() {
        return this._unrecognizedPropertyName;
    }
}
