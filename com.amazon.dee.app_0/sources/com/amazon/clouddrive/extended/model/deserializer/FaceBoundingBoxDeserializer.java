package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.FaceBoundingBox;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class FaceBoundingBoxDeserializer extends AbstractDeserializer<FaceBoundingBox, FaceBoundingBox> {
    public static final JsonDeserializer<FaceBoundingBox> INSTANCE = new FaceBoundingBoxDeserializer();

    /* loaded from: classes11.dex */
    static class FaceBoundingBoxFieldDeserializer implements JsonFieldDeserializer<FaceBoundingBox> {
        FaceBoundingBoxFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FaceBoundingBox) obj));
        }

        public <U extends FaceBoundingBox> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (!"TopLeftCoordY".equals(str) && !"topLeftCoordY".equals(str)) {
                if (!"TopLeftCoordX".equals(str) && !"topLeftCoordX".equals(str)) {
                    if ("width".equals(str)) {
                        u.setWidth(SimpleDeserializers.deserializeDouble(jsonParser));
                        return true;
                    } else if (!"height".equals(str)) {
                        return false;
                    } else {
                        u.setHeight(SimpleDeserializers.deserializeDouble(jsonParser));
                        return true;
                    }
                }
                u.setTopLeftCoordX(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            }
            u.setTopLeftCoordY(SimpleDeserializers.deserializeDouble(jsonParser));
            return true;
        }
    }

    private FaceBoundingBoxDeserializer() {
        super(new FaceBoundingBoxFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public FaceBoundingBox mo3156createValue() {
        return new FaceBoundingBox();
    }
}
