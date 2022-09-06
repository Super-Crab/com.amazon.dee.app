package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.extended.model.SearchMetadata;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class SearchMetadataDeserializer extends AbstractDeserializer<SearchMetadata, SearchMetadata> {
    public static final JsonDeserializer<SearchMetadata> INSTANCE = new SearchMetadataDeserializer();

    /* loaded from: classes11.dex */
    static class SearchMetadataFieldDeserializer implements JsonFieldDeserializer<SearchMetadata> {
        SearchMetadataFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((SearchMetadata) obj));
        }

        public <U extends SearchMetadata> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (AlexaDeviceBackgroundImageBridge.KEY_NODE_ID.equals(str)) {
                u.setNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                u.setNodeOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("locationId".equals(str)) {
                u.setLocationId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("clusterName".equals(str)) {
                u.setClusterName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (PhotoSearchCategory.CLUSTER_ID.equals(str)) {
                u.setClusterId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"thingId".equals(str)) {
                return false;
            } else {
                u.setThingId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private SearchMetadataDeserializer() {
        super(new SearchMetadataFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public SearchMetadata mo3156createValue() {
        return new SearchMetadata();
    }
}
