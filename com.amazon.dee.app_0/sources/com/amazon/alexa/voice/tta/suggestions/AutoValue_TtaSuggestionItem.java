package com.amazon.alexa.voice.tta.suggestions;

import androidx.annotation.Nullable;
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
public final class AutoValue_TtaSuggestionItem extends C$AutoValue_TtaSuggestionItem {

    /* loaded from: classes11.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<TtaSuggestionItem> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("title", "description", "id", "action", "actionData");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_TtaSuggestionItem.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public TtaSuggestionItem mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("title").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("description").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("id").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("action").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("actionData").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_TtaSuggestionItem(str, str2, str3, str4, str5);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, TtaSuggestionItem ttaSuggestionItem) throws IOException {
            if (ttaSuggestionItem == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("title"));
            if (ttaSuggestionItem.getTitle() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ttaSuggestionItem.getTitle());
            }
            jsonWriter.name(this.realFieldNames.get("description"));
            if (ttaSuggestionItem.getDescription() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ttaSuggestionItem.getDescription());
            }
            jsonWriter.name(this.realFieldNames.get("id"));
            if (ttaSuggestionItem.getId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ttaSuggestionItem.getId());
            }
            jsonWriter.name(this.realFieldNames.get("action"));
            if (ttaSuggestionItem.getAction() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ttaSuggestionItem.getAction());
            }
            jsonWriter.name(this.realFieldNames.get("actionData"));
            if (ttaSuggestionItem.getActionData() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, ttaSuggestionItem.getActionData());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TtaSuggestionItem(final String str, @Nullable final String str2, final String str3, final String str4, @Nullable final String str5) {
        new TtaSuggestionItem(str, str2, str3, str4, str5) { // from class: com.amazon.alexa.voice.tta.suggestions.$AutoValue_TtaSuggestionItem
            private final String action;
            private final String actionData;
            private final String description;
            private final String id;
            private final String title;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.title = str;
                    this.description = str2;
                    if (str3 != null) {
                        this.id = str3;
                        if (str4 != null) {
                            this.action = str4;
                            this.actionData = str5;
                            return;
                        }
                        throw new NullPointerException("Null action");
                    }
                    throw new NullPointerException("Null id");
                }
                throw new NullPointerException("Null title");
            }

            public boolean equals(Object obj) {
                String str6;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof TtaSuggestionItem)) {
                    return false;
                }
                TtaSuggestionItem ttaSuggestionItem = (TtaSuggestionItem) obj;
                if (this.title.equals(ttaSuggestionItem.getTitle()) && ((str6 = this.description) != null ? str6.equals(ttaSuggestionItem.getDescription()) : ttaSuggestionItem.getDescription() == null) && this.id.equals(ttaSuggestionItem.getId()) && this.action.equals(ttaSuggestionItem.getAction())) {
                    String str7 = this.actionData;
                    if (str7 == null) {
                        if (ttaSuggestionItem.getActionData() == null) {
                            return true;
                        }
                    } else if (str7.equals(ttaSuggestionItem.getActionData())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem, com.amazon.alexa.voice.ui.suggestions.SuggestionItem
            public String getAction() {
                return this.action;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem, com.amazon.alexa.voice.ui.suggestions.SuggestionItem
            @Nullable
            public String getActionData() {
                return this.actionData;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem, com.amazon.alexa.voice.ui.suggestions.SuggestionItem
            @Nullable
            public String getDescription() {
                return this.description;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem, com.amazon.alexa.voice.ui.suggestions.SuggestionItem
            public String getId() {
                return this.id;
            }

            @Override // com.amazon.alexa.voice.tta.suggestions.TtaSuggestionItem, com.amazon.alexa.voice.ui.suggestions.SuggestionItem
            public String getTitle() {
                return this.title;
            }

            public int hashCode() {
                int hashCode = (this.title.hashCode() ^ 1000003) * 1000003;
                String str6 = this.description;
                int i = 0;
                int hashCode2 = (((((hashCode ^ (str6 == null ? 0 : str6.hashCode())) * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.action.hashCode()) * 1000003;
                String str7 = this.actionData;
                if (str7 != null) {
                    i = str7.hashCode();
                }
                return hashCode2 ^ i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TtaSuggestionItem{title=");
                outline107.append(this.title);
                outline107.append(", description=");
                outline107.append(this.description);
                outline107.append(", id=");
                outline107.append(this.id);
                outline107.append(", action=");
                outline107.append(this.action);
                outline107.append(", actionData=");
                return GeneratedOutlineSupport1.outline91(outline107, this.actionData, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}
