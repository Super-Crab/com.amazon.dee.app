package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Aggregation;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class AggregationDeserializer extends AbstractDeserializer<Aggregation, Aggregation> {
    public static final JsonDeserializer<Aggregation> INSTANCE = new AggregationDeserializer();

    /* loaded from: classes11.dex */
    static class AggregationFieldDeserializer implements JsonFieldDeserializer<Aggregation> {
        AggregationFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Aggregation) obj));
        }

        public <U extends Aggregation> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("count".equals(str)) {
                u.setCount(SimpleDeserializers.deserializeInteger(jsonParser));
                return true;
            } else if ("value".equals(str)) {
                u.setValue(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"searchData".equals(str)) {
                return false;
            } else {
                u.setMetadata(SearchMetadataDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private AggregationDeserializer() {
        super(new AggregationFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public Aggregation mo3156createValue() {
        return new Aggregation();
    }
}
