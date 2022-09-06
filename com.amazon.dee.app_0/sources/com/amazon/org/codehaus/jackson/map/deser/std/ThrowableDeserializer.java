package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.deser.BeanDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.SettableAnyProperty;
import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.HashSet;
/* loaded from: classes13.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BeanDeserializer
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object createUsingDefault;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(jsonDeserializer.mo4206deserialize(jsonParser, deserializationContext));
        }
        if (!this._beanType.isAbstract()) {
            boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
            boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
            if (!canCreateFromString && !canCreateUsingDefault) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not deserialize Throwable of type ");
                outline107.append(this._beanType);
                outline107.append(" without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
                throw new JsonMappingException(outline107.toString());
            }
            int i = 0;
            Object obj = null;
            Object[] objArr = null;
            while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                jsonParser.nextToken();
                if (find != null) {
                    if (obj != null) {
                        find.deserializeAndSet(jsonParser, deserializationContext, obj);
                    } else {
                        if (objArr == null) {
                            int size = this._beanProperties.size();
                            objArr = new Object[size + size];
                        }
                        int i2 = i + 1;
                        objArr[i] = find;
                        i = i2 + 1;
                        objArr[i2] = find.deserialize(jsonParser, deserializationContext);
                    }
                } else if ("message".equals(currentName) && canCreateFromString) {
                    obj = this._valueInstantiator.createFromString(jsonParser.getText());
                    if (objArr != null) {
                        for (int i3 = 0; i3 < i; i3 += 2) {
                            ((SettableBeanProperty) objArr[i3]).set(obj, objArr[i3 + 1]);
                        }
                        objArr = null;
                    }
                } else {
                    HashSet<String> hashSet = this._ignorableProps;
                    if (hashSet != null && hashSet.contains(currentName)) {
                        jsonParser.skipChildren();
                    } else {
                        SettableAnyProperty settableAnyProperty = this._anySetter;
                        if (settableAnyProperty != null) {
                            settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                        } else {
                            handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                        }
                    }
                }
                jsonParser.nextToken();
            }
            if (obj == null) {
                if (canCreateFromString) {
                    createUsingDefault = this._valueInstantiator.createFromString(null);
                } else {
                    createUsingDefault = this._valueInstantiator.createUsingDefault();
                }
                obj = createUsingDefault;
                if (objArr != null) {
                    for (int i4 = 0; i4 < i; i4 += 2) {
                        ((SettableBeanProperty) objArr[i4]).set(obj, objArr[i4 + 1]);
                    }
                }
            }
            return obj;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Can not instantiate abstract type ");
        outline1072.append(this._beanType);
        outline1072.append(" (need to add/enable type information?)");
        throw JsonMappingException.from(jsonParser, outline1072.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.deser.BeanDeserializer, com.amazon.org.codehaus.jackson.map.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer() {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, true);
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, boolean z) {
        super(beanDeserializer, z);
    }
}
