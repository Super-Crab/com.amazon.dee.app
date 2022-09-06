package com.amazon.alexa.client.alexaservice.networking.adapters;

import android.net.Uri;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class UriAdapter extends TypeAdapter<Uri> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, Uri uri) throws IOException {
        jsonWriter.value(uri.toString());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public Uri mo8353read(JsonReader jsonReader) throws IOException {
        return Uri.parse(jsonReader.nextString());
    }
}
