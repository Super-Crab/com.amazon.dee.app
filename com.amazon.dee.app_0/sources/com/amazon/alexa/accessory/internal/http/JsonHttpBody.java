package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import java.io.IOException;
import org.json.JSONException;
/* loaded from: classes.dex */
public final class JsonHttpBody implements HttpBody {
    private final JsonObjectSerializable jsonSerializable;

    public JsonHttpBody(JsonObjectSerializable jsonObjectSerializable) {
        Preconditions.notNull(jsonObjectSerializable, "jsonSerializable");
        this.jsonSerializable = jsonObjectSerializable;
    }

    @Override // com.amazon.alexa.accessory.internal.http.HttpBody
    public String getContentType() {
        return LocationPublisher.CONTENT_TYPE_JSON;
    }

    @Override // com.amazon.alexa.accessory.internal.http.HttpBody
    public void writeTo(Sink sink) throws IOException {
        try {
            byte[] bytes = this.jsonSerializable.toJsonObject().toString().getBytes();
            sink.write(bytes, 0, bytes.length);
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }
}
