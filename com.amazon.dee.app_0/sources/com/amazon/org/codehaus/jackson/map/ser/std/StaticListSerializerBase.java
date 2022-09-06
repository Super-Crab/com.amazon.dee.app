package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;
import java.util.Collection;
/* loaded from: classes13.dex */
public abstract class StaticListSerializerBase<T extends Collection<?>> extends SerializerBase<T> {
    protected final BeanProperty _property;

    /* JADX INFO: Access modifiers changed from: protected */
    public StaticListSerializerBase(Class<?> cls, BeanProperty beanProperty) {
        super(cls, false);
        this._property = beanProperty;
    }

    protected abstract JsonNode contentSchema();

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, contentSchema());
        return createSchemaNode;
    }
}
