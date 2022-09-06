package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.DeserializationProblemHandler;
import com.amazon.org.codehaus.jackson.map.DeserializerProvider;
import com.amazon.org.codehaus.jackson.map.InjectableValues;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import com.amazon.org.codehaus.jackson.map.util.ArrayBuilders;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.map.util.LinkedNode;
import com.amazon.org.codehaus.jackson.map.util.ObjectBuffer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes13.dex */
public class StdDeserializationContext extends DeserializationContext {
    static final int MAX_ERROR_STR_LEN = 500;
    protected ArrayBuilders _arrayBuilders;
    protected DateFormat _dateFormat;
    protected final DeserializerProvider _deserProvider;
    protected final InjectableValues _injectableValues;
    protected ObjectBuffer _objectBuffer;
    protected JsonParser _parser;

    public StdDeserializationContext(DeserializationConfig deserializationConfig, JsonParser jsonParser, DeserializerProvider deserializerProvider, InjectableValues injectableValues) {
        super(deserializationConfig);
        this._parser = jsonParser;
        this._deserProvider = deserializerProvider;
        this._injectableValues = injectableValues;
    }

    protected String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return GeneratedOutlineSupport1.outline91(new StringBuilder(), _calcName(cls.getComponentType()), "[]");
        }
        return cls.getName();
    }

    protected String _desc(String str) {
        if (str.length() > 500) {
            return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
        }
        return str;
    }

    protected String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception unused) {
            return "[N/A]";
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    protected String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        InjectableValues injectableValues = this._injectableValues;
        if (injectableValues != null) {
            return injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
        }
        throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    protected DateFormat getDateFormat() {
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        return this._dateFormat;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public DeserializerProvider getDeserializerProvider() {
        return this._deserProvider;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonParser getParser() {
        return this._parser;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            JsonParser jsonParser2 = this._parser;
            this._parser = jsonParser;
            while (problemHandlers != null) {
                try {
                    if (problemHandlers.value().handleUnknownProperty(this, jsonDeserializer, obj, str)) {
                        return true;
                    }
                    problemHandlers = problemHandlers.next();
                } finally {
                    this._parser = jsonParser2;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        JsonParser jsonParser = this._parser;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not construct instance of ");
        outline107.append(cls.getName());
        outline107.append(", problem: ");
        outline107.append(th.getMessage());
        return JsonMappingException.from(jsonParser, outline107.toString(), th);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownFieldException(Object obj, String str) {
        return UnrecognizedPropertyException.from(this._parser, obj, str);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Could not resolve type id '" + str + "' into a subtype of " + javaType);
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        JsonParser jsonParser = this._parser;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not construct Map key of type ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " from String \"");
        outline107.append(_desc(str));
        outline107.append("\": ");
        outline107.append(str2);
        return JsonMappingException.from(jsonParser, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdNumberException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not construct instance of ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " from number value (");
        outline107.append(_valueDesc());
        outline107.append("): ");
        outline107.append(str);
        return JsonMappingException.from(jsonParser, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdStringException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not construct instance of ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " from String value '");
        outline107.append(_valueDesc());
        outline107.append("': ");
        outline107.append(str);
        return JsonMappingException.from(jsonParser, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected token (");
        outline107.append(jsonParser.getCurrentToken());
        outline107.append("), expected ");
        outline107.append(jsonToken);
        outline107.append(RealTimeTextConstants.COLON_SPACE);
        outline107.append(str);
        return JsonMappingException.from(jsonParser, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not construct instance of ");
        outline107.append(cls.getName());
        outline107.append(", problem: ");
        outline107.append(str);
        return JsonMappingException.from(jsonParser, outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        String _calcName = _calcName(cls);
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not deserialize instance of " + _calcName + " out of " + jsonToken + " token");
    }
}
