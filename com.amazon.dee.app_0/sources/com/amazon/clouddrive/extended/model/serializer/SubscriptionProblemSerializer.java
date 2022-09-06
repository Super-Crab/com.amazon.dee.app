package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.SubscriptionProblem;
import com.amazon.clouddrive.model.serializer.JsonFieldSerializer;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SubscriptionProblemSerializer implements JsonSerializer<SubscriptionProblem> {
    public static final JsonSerializer<SubscriptionProblem> INSTANCE = new SubscriptionProblemSerializer();
    private final SubscriptionProblemFieldSerializer mFieldSerializer = new SubscriptionProblemFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class SubscriptionProblemFieldSerializer implements JsonFieldSerializer<SubscriptionProblem> {
        SubscriptionProblemFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((SubscriptionProblemFieldSerializer) ((SubscriptionProblem) obj), jsonGenerator);
        }

        public <U extends SubscriptionProblem> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("effectiveDate");
            SimpleSerializers.serializeString(u.getEffectiveDate(), jsonGenerator);
            jsonGenerator.writeFieldName("billingPeriodNumber");
            SimpleSerializers.serializePrimitiveLong(u.getBillingPeriodNumber(), jsonGenerator);
            jsonGenerator.writeFieldName("problemID");
            SimpleSerializers.serializeString(u.getProblemID(), jsonGenerator);
            jsonGenerator.writeFieldName("problemCode");
            SimpleSerializers.serializeString(u.getProblemCode(), jsonGenerator);
            jsonGenerator.writeFieldName("problemStatus");
            SimpleSerializers.serializeString(u.getProblemStatus(), jsonGenerator);
        }
    }

    private SubscriptionProblemSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(SubscriptionProblem subscriptionProblem, JsonGenerator jsonGenerator) throws IOException {
        if (subscriptionProblem == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((SubscriptionProblemFieldSerializer) subscriptionProblem, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
