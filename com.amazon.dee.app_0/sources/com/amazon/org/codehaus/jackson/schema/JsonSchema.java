package com.amazon.org.codehaus.jackson.schema;

import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.annotate.JsonCreator;
import com.amazon.org.codehaus.jackson.annotate.JsonValue;
import com.amazon.org.codehaus.jackson.node.JsonNodeFactory;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import org.apache.commons.codec.language.bm.Languages;
/* loaded from: classes13.dex */
public class JsonSchema {
    private final ObjectNode schema;

    @JsonCreator
    public JsonSchema(ObjectNode objectNode) {
        this.schema = objectNode;
    }

    public static JsonNode getDefaultSchemaNode() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("type", Languages.ANY);
        return objectNode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonSchema)) {
            return false;
        }
        JsonSchema jsonSchema = (JsonSchema) obj;
        ObjectNode objectNode = this.schema;
        if (objectNode != null) {
            return objectNode.equals(jsonSchema.schema);
        }
        return jsonSchema.schema == null;
    }

    @JsonValue
    public ObjectNode getSchemaNode() {
        return this.schema;
    }

    public String toString() {
        return this.schema.toString();
    }
}
