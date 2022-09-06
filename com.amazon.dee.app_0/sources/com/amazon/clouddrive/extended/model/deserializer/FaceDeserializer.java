package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.amazon.clouddrive.extended.model.Face;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.clouddrive.model.deserializer.SimpleListDeserializers;
import com.magiear.handsfree.util.Common;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class FaceDeserializer extends AbstractDeserializer<Face, Face> {
    public static final JsonDeserializer<Face> INSTANCE = new FaceDeserializer();

    /* loaded from: classes11.dex */
    static class FaceFieldDeserializer implements JsonFieldDeserializer<Face> {
        FaceFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Face) obj));
        }

        public <U extends Face> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("boundingBox".equals(str)) {
                u.setBoundingBox(FaceBoundingBoxDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (NetworkConstants.FEATURES_KEY.equals(str)) {
                u.setFeatures(new ListDeserializer(FaceFeaturesDeserializer.INSTANCE).mo3229deserialize(jsonParser));
                return true;
            } else if (Common.EXTRA_CONFIDENCE.equals(str)) {
                u.setConfidence(SimpleDeserializers.deserializeDouble(jsonParser));
                return true;
            } else if (!"matches".equals(str)) {
                return false;
            } else {
                u.setMatches(SimpleListDeserializers.STRING_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
        }
    }

    private FaceDeserializer() {
        super(new FaceFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public Face mo3156createValue() {
        return new Face();
    }
}
