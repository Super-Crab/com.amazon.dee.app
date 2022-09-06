package com.amazon.alexa.client.alexaservice.networking.adapters;

import android.content.ComponentName;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.C0179Pya;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public class ComponentNameAdapter extends TypeAdapter<ComponentName> {
    @VisibleForTesting
    public static final ComponentName BIo = new ComponentName("unknown", "unknown");
    public static final String zZm = "ComponentNameAdapter";

    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, ComponentName componentName) throws IOException {
        if (componentName != null) {
            jsonWriter.beginObject();
            jsonWriter.name("class").value(componentName.getClassName());
            jsonWriter.name("package").value(componentName.getPackageName());
            jsonWriter.endObject();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public ComponentName mo8353read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != -807062458) {
                if (hashCode == 94742904 && nextName.equals("class")) {
                    c = 0;
                }
            } else if (nextName.equals("package")) {
                c = 1;
            }
            if (c == 0) {
                str2 = jsonReader.nextString();
            } else if (c != 1) {
                String str3 = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("Invalid entry");
                zZm2.append(jsonReader.nextString());
                zZm2.append("for fieldName: ");
                zZm2.append(nextName);
                Log.w(str3, zZm2.toString());
            } else {
                str = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        if (str != null && str2 != null) {
            return new ComponentName(str, str2);
        }
        return BIo;
    }
}
