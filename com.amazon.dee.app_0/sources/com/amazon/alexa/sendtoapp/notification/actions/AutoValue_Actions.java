package com.amazon.alexa.sendtoapp.notification.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.notification.actions.Actions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class AutoValue_Actions extends C$AutoValue_Actions {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Actions> {
        private volatile TypeAdapter<Action> action_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("primary", "fallback");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Actions.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Actions mo8353read(JsonReader jsonReader) throws IOException {
            Action action = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Action action2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    char c = 65535;
                    int hashCode = nextName.hashCode();
                    if (hashCode != -314765822) {
                        if (hashCode == 761243362 && nextName.equals("fallback")) {
                            c = 1;
                        }
                    } else if (nextName.equals("primary")) {
                        c = 0;
                    }
                    if (c == 0) {
                        TypeAdapter<Action> typeAdapter = this.action_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Action.class);
                            this.action_adapter = typeAdapter;
                        }
                        action = typeAdapter.mo8353read(jsonReader);
                    } else if (c != 1) {
                        jsonReader.skipValue();
                    } else {
                        TypeAdapter<Action> typeAdapter2 = this.action_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Action.class);
                            this.action_adapter = typeAdapter2;
                        }
                        action2 = typeAdapter2.mo8353read(jsonReader);
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Actions(action, action2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Actions actions) throws IOException {
            if (actions == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("primary");
            if (actions.primary() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Action> typeAdapter = this.action_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Action.class);
                    this.action_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, actions.primary());
            }
            jsonWriter.name("fallback");
            if (actions.fallback() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Action> typeAdapter2 = this.action_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Action.class);
                    this.action_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, actions.fallback());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Actions(final Action action, @Nullable final Action action2) {
        new Actions(action, action2) { // from class: com.amazon.alexa.sendtoapp.notification.actions.$AutoValue_Actions
            private final Action fallback;
            private final Action primary;

            /* renamed from: com.amazon.alexa.sendtoapp.notification.actions.$AutoValue_Actions$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Actions.Builder {
                private Action fallback;
                private Action primary;

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Actions.Builder
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

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Actions.Builder
                public Actions.Builder setFallback(@Nullable Action action) {
                    this.fallback = action;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.notification.actions.Actions.Builder
                public Actions.Builder setPrimary(Action action) {
                    if (action != null) {
                        this.primary = action;
                        return this;
                    }
                    throw new NullPointerException("Null primary");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (action != null) {
                    this.primary = action;
                    this.fallback = action2;
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
                if (this.primary.equals(actions.primary())) {
                    Action action3 = this.fallback;
                    if (action3 == null) {
                        if (actions.fallback() == null) {
                            return true;
                        }
                    } else if (action3.equals(actions.fallback())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Actions
            @Nullable
            @SerializedName("fallback")
            public Action fallback() {
                return this.fallback;
            }

            public int hashCode() {
                int hashCode = (this.primary.hashCode() ^ 1000003) * 1000003;
                Action action3 = this.fallback;
                return hashCode ^ (action3 == null ? 0 : action3.hashCode());
            }

            @Override // com.amazon.alexa.sendtoapp.notification.actions.Actions
            @NonNull
            @SerializedName("primary")
            public Action primary() {
                return this.primary;
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
