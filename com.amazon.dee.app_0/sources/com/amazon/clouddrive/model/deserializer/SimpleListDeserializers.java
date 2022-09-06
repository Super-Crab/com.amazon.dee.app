package com.amazon.clouddrive.model.deserializer;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SimpleListDeserializers {
    public static final JsonDeserializer<List<String>> STRING_LIST_DESERIALIZER = new ListDeserializer(new StringDeserializer());

    /* loaded from: classes11.dex */
    private static class StringDeserializer implements JsonDeserializer<String> {
        StringDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
        /* renamed from: deserialize  reason: collision with other method in class */
        public String mo3229deserialize(JsonParser jsonParser) throws IOException {
            return SimpleDeserializers.deserializeString(jsonParser);
        }
    }

    private SimpleListDeserializers() {
    }
}
