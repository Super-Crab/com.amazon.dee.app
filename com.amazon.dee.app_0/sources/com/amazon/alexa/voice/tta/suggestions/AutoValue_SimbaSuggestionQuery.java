package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
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
public final class AutoValue_SimbaSuggestionQuery extends C$AutoValue_SimbaSuggestionQuery {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SimbaSuggestionQuery> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("queryId", "queryText");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SimbaSuggestionQuery.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SimbaSuggestionQuery mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("queryId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("queryText").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SimbaSuggestionQuery(str, str2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SimbaSuggestionQuery simbaSuggestionQuery) throws IOException {
            if (simbaSuggestionQuery == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("queryId"));
            if (simbaSuggestionQuery.getQueryId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, simbaSuggestionQuery.getQueryId());
            }
            jsonWriter.name(this.realFieldNames.get("queryText"));
            if (simbaSuggestionQuery.getQueryText() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, simbaSuggestionQuery.getQueryText());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SimbaSuggestionQuery(final String str, final String str2) {
        new SimbaSuggestionQuery(str, str2) { // from class: com.amazon.alexa.voice.tta.suggestions.$AutoValue_SimbaSuggestionQuery
            private final String queryId;
            private final String queryText;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.queryId = str;
                    if (str2 != null) {
                        this.queryText = str2;
                        return;
                    }
                    throw new NullPointerException("Null queryText");
                }
                throw new NullPointerException("Null queryId");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SimbaSuggestionQuery)) {
                    return false;
                }
                SimbaSuggestionQuery simbaSuggestionQuery = (SimbaSuggestionQuery) obj;
                return this.queryId.equals(simbaSuggestionQuery.getQueryId()) && this.queryText.equals(simbaSuggestionQuery.getQueryText());
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.SimbaSuggestionQuery
            public String getQueryId() {
                return this.queryId;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.SimbaSuggestionQuery
            public String getQueryText() {
                return this.queryText;
            }

            public int hashCode() {
                return ((this.queryId.hashCode() ^ 1000003) * 1000003) ^ this.queryText.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SimbaSuggestionQuery{queryId=");
                outline107.append(this.queryId);
                outline107.append(", queryText=");
                return GeneratedOutlineSupport1.outline91(outline107, this.queryText, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
