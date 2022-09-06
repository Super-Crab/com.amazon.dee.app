package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GetAggregationsResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetAggregationsResponseDeserializer extends AbstractDeserializer<GetAggregationsResponse, GetAggregationsResponse> {
    public static final JsonDeserializer<GetAggregationsResponse> INSTANCE = new GetAggregationsResponseDeserializer();

    /* loaded from: classes11.dex */
    static class GetAggregationsResponseFieldDeserializer implements JsonFieldDeserializer<GetAggregationsResponse> {
        GetAggregationsResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GetAggregationsResponse) obj));
        }

        public <U extends GetAggregationsResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("category".equals(str)) {
                u.setCategory(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"aggregations".equals(str)) {
                return false;
            } else {
                u.setAggregation(new ListDeserializer(AggregationDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private GetAggregationsResponseDeserializer() {
        super(new GetAggregationsResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public GetAggregationsResponse mo3156createValue() {
        return new GetAggregationsResponse();
    }
}
