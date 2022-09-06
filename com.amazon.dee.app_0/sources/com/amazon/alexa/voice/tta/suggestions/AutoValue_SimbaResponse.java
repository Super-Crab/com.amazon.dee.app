package com.amazon.alexa.voice.tta.suggestions;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_SimbaResponse extends C$AutoValue_SimbaResponse {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SimbaResponse> {
        private final Gson gson;
        private volatile TypeAdapter<List<TtaSuggestionItem>> list__ttaSuggestionItem_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<SimbaSuggestionQuery> simbaSuggestionQuery_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("suggestionResults", "suggestionQuery");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SimbaResponse.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SimbaResponse mo8353read(JsonReader jsonReader) throws IOException {
            List<TtaSuggestionItem> list = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            SimbaSuggestionQuery simbaSuggestionQuery = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("suggestionResults").equals(nextName)) {
                        TypeAdapter<List<TtaSuggestionItem>> typeAdapter = this.list__ttaSuggestionItem_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, TtaSuggestionItem.class));
                            this.list__ttaSuggestionItem_adapter = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("suggestionQuery").equals(nextName)) {
                        TypeAdapter<SimbaSuggestionQuery> typeAdapter2 = this.simbaSuggestionQuery_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(SimbaSuggestionQuery.class);
                            this.simbaSuggestionQuery_adapter = typeAdapter2;
                        }
                        simbaSuggestionQuery = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SimbaResponse(list, simbaSuggestionQuery);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SimbaResponse simbaResponse) throws IOException {
            if (simbaResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("suggestionResults"));
            if (simbaResponse.getSuggestionResults() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<TtaSuggestionItem>> typeAdapter = this.list__ttaSuggestionItem_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, TtaSuggestionItem.class));
                    this.list__ttaSuggestionItem_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, simbaResponse.getSuggestionResults());
            }
            jsonWriter.name(this.realFieldNames.get("suggestionQuery"));
            if (simbaResponse.getSuggestionQuery() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SimbaSuggestionQuery> typeAdapter2 = this.simbaSuggestionQuery_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(SimbaSuggestionQuery.class);
                    this.simbaSuggestionQuery_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, simbaResponse.getSuggestionQuery());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SimbaResponse(final List<TtaSuggestionItem> list, final SimbaSuggestionQuery simbaSuggestionQuery) {
        new SimbaResponse(list, simbaSuggestionQuery) { // from class: com.amazon.alexa.voice.tta.suggestions.$AutoValue_SimbaResponse
            private final SimbaSuggestionQuery suggestionQuery;
            private final List<TtaSuggestionItem> suggestionResults;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (list != null) {
                    this.suggestionResults = list;
                    if (simbaSuggestionQuery != null) {
                        this.suggestionQuery = simbaSuggestionQuery;
                        return;
                    }
                    throw new NullPointerException("Null suggestionQuery");
                }
                throw new NullPointerException("Null suggestionResults");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SimbaResponse)) {
                    return false;
                }
                SimbaResponse simbaResponse = (SimbaResponse) obj;
                return this.suggestionResults.equals(simbaResponse.getSuggestionResults()) && this.suggestionQuery.equals(simbaResponse.getSuggestionQuery());
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.SimbaResponse
            public SimbaSuggestionQuery getSuggestionQuery() {
                return this.suggestionQuery;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.SimbaResponse
            public List<TtaSuggestionItem> getSuggestionResults() {
                return this.suggestionResults;
            }

            public int hashCode() {
                return ((this.suggestionResults.hashCode() ^ 1000003) * 1000003) ^ this.suggestionQuery.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SimbaResponse{suggestionResults=");
                outline107.append(this.suggestionResults);
                outline107.append(", suggestionQuery=");
                outline107.append(this.suggestionQuery);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
