package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.amazon.clouddrive.extended.model.ReUpdateNode;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class ReUpdateNodeDeserializer extends AbstractDeserializer<ReUpdateNode, ReUpdateNode> {

    /* loaded from: classes11.dex */
    static class ReUpdateNodeFieldDeserializer implements JsonFieldDeserializer<ReUpdateNode> {
        ReUpdateNodeFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ReUpdateNode) obj));
        }

        public <U extends ReUpdateNode> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("id".equals(str)) {
                u.setId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (SierraContentProviderContract.MD5_VALUE.equals(str)) {
                u.setMd5(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("requestedTimestamp".equals(str)) {
                u.setRequestedTimestamp(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"type".equals(str)) {
                return false;
            } else {
                u.setType(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    public ReUpdateNodeDeserializer() {
        super(new ReUpdateNodeFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public ReUpdateNode mo3156createValue() {
        return new ReUpdateNode();
    }
}
