package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.FaceFeatures;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class FaceFeaturesDeserializer extends AbstractDeserializer<FaceFeatures, FaceFeatures> {
    public static final JsonDeserializer<FaceFeatures> INSTANCE = new FaceFeaturesDeserializer();

    /* loaded from: classes11.dex */
    static class FaceFeaturesFieldDeserializer implements JsonFieldDeserializer<FaceFeatures> {
        FaceFeaturesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FaceFeatures) obj));
        }

        public <U extends FaceFeatures> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("xPosition".equals(str)) {
                u.setxPosition(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if ("yPosition".equals(str)) {
                u.setyPosition(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if (!"type".equals(str)) {
                return false;
            } else {
                u.setType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private FaceFeaturesDeserializer() {
        super(new FaceFeaturesFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public FaceFeatures mo3156createValue() {
        return new FaceFeatures();
    }
}
