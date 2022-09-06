package com.amazon.alexa.sendtoapp.activitycard.model.v1;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions;
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
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_Actions extends C$AutoValue_Actions {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Actions> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Target> target_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("primary", "fallback");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Actions.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Actions mo8353read(JsonReader jsonReader) throws IOException {
            Target target = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Target target2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("primary").equals(nextName)) {
                        TypeAdapter<Target> typeAdapter = this.target_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Target.class);
                            this.target_adapter = typeAdapter;
                        }
                        target = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("fallback").equals(nextName)) {
                        TypeAdapter<Target> typeAdapter2 = this.target_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Target.class);
                            this.target_adapter = typeAdapter2;
                        }
                        target2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Actions(target, target2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Actions actions) throws IOException {
            if (actions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("primary"));
            if (actions.getPrimary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Target> typeAdapter = this.target_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Target.class);
                    this.target_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, actions.getPrimary());
            }
            jsonWriter.name(this.realFieldNames.get("fallback"));
            if (actions.getFallback() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Target> typeAdapter2 = this.target_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Target.class);
                    this.target_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, actions.getFallback());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Actions(final Target target, @Nullable final Target target2) {
        new Actions(target, target2) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Actions
            private final Target fallback;
            private final Target primary;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.v1.$AutoValue_Actions$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Actions.Builder {
                private Target fallback;
                private Target primary;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions.Builder
                public Actions build() {
                    String str = "";
                    if (this.primary == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " primary");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Actions(this.primary, this.fallback);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions.Builder
                public Actions.Builder setFallback(@Nullable Target target) {
                    this.fallback = target;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions.Builder
                public Actions.Builder setPrimary(Target target) {
                    if (target != null) {
                        this.primary = target;
                        return this;
                    }
                    throw new NullPointerException("Null primary");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (target != null) {
                    this.primary = target;
                    this.fallback = target2;
                    return;
                }
                throw new NullPointerException("Null primary");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Actions)) {
                    return false;
                }
                Actions actions = (Actions) obj;
                if (this.primary.equals(actions.getPrimary())) {
                    Target target3 = this.fallback;
                    if (target3 == null) {
                        if (actions.getFallback() == null) {
                            return true;
                        }
                    } else if (target3.equals(actions.getFallback())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions
            @Nullable
            public Target getFallback() {
                return this.fallback;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.v1.Actions
            public Target getPrimary() {
                return this.primary;
            }

            public int hashCode() {
                int hashCode = (this.primary.hashCode() ^ 1000003) * 1000003;
                Target target3 = this.fallback;
                return hashCode ^ (target3 == null ? 0 : target3.hashCode());
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Actions{primary=");
                outline107.append(this.primary);
                outline107.append(", fallback=");
                outline107.append(this.fallback);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
