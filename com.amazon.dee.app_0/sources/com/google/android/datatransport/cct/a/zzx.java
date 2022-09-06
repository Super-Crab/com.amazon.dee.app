package com.google.android.datatransport.cct.a;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import java.io.IOException;
import java.io.Reader;
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
@AutoValue
/* loaded from: classes2.dex */
public abstract class zzx {
    @Nullable
    public static zzx zza(@NonNull Reader reader) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (jsonReader.nextName().equals("nextRequestWaitMillis")) {
                    if (jsonReader.peek() == JsonToken.STRING) {
                        return new zzl(Long.parseLong(jsonReader.nextString()));
                    }
                    return new zzl(jsonReader.nextLong());
                }
                jsonReader.skipValue();
            }
            throw new IOException("Response is missing nextRequestWaitMillis field.");
        } finally {
            jsonReader.close();
        }
    }

    public abstract long zza();
}
