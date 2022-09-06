package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.amazon.clouddrive.model.Settings;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class SettingsSerializer implements JsonSerializer<Settings> {
    public static final JsonSerializer<Settings> INSTANCE = new SettingsSerializer();

    private SettingsSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(Settings settings, JsonGenerator jsonGenerator) throws IOException {
        if (settings == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(PhotoSearchCategory.FAVORITE);
        SimpleSerializers.serializeBoolean(settings.isFavorite(), jsonGenerator);
        jsonGenerator.writeFieldName("hidden");
        SimpleSerializers.serializeBoolean(settings.isHidden(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
