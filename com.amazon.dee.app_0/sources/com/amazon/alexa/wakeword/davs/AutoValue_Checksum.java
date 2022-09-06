package com.amazon.alexa.wakeword.davs;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_Checksum extends C$AutoValue_Checksum {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Checksum> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126(SierraContentProviderContract.MD5_VALUE);
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Checksum.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Checksum mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get(SierraContentProviderContract.MD5_VALUE).equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Checksum(str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Checksum checksum) throws IOException {
            if (checksum == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get(SierraContentProviderContract.MD5_VALUE));
            if (checksum.getMd5() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, checksum.getMd5());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Checksum(final String str) {
        new Checksum(str) { // from class: com.amazon.alexa.wakeword.davs.$AutoValue_Checksum
            private final String md5;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.md5 = str;
                    return;
                }
                throw new NullPointerException("Null md5");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Checksum)) {
                    return false;
                }
                return this.md5.equals(((Checksum) obj).getMd5());
            }

            @Override // com.amazon.alexa.wakeword.davs.Checksum
            public String getMd5() {
                return this.md5;
            }

            public int hashCode() {
                return this.md5.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Checksum{md5="), this.md5, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
