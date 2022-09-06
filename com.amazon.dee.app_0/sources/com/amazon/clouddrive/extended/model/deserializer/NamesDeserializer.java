package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.Names;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class NamesDeserializer extends AbstractDeserializer<Names, Names> {
    public static final JsonDeserializer<Names> INSTANCE = new NamesDeserializer(new NamesFieldDeserializer());

    /* loaded from: classes11.dex */
    static class NamesFieldDeserializer implements JsonFieldDeserializer<Names> {
        NamesFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((Names) obj));
        }

        public <U extends Names> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("fullName".equals(str)) {
                u.setFullName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"firstName".equals(str)) {
                return false;
            } else {
                u.setFirstName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private NamesDeserializer(JsonFieldDeserializer<Names> jsonFieldDeserializer) {
        super(jsonFieldDeserializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public Names mo3156createValue() {
        return new Names();
    }
}
