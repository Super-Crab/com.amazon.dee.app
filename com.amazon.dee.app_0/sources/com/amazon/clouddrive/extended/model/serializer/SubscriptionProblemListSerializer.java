package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SubscriptionProblem;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SubscriptionProblemListSerializer implements JsonSerializer<List<SubscriptionProblem>> {
    public static final JsonSerializer<List<SubscriptionProblem>> INSTANCE = new SubscriptionProblemListSerializer();

    private SubscriptionProblemListSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(List<SubscriptionProblem> list, JsonGenerator jsonGenerator) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        for (SubscriptionProblem subscriptionProblem : list) {
            SubscriptionProblemSerializer.INSTANCE.serialize(subscriptionProblem, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }
}
