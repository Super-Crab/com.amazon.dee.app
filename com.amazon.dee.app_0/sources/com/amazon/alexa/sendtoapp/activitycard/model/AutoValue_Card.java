package com.amazon.alexa.sendtoapp.activitycard.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.sendtoapp.activitycard.model.Card;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.CustomData;
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
public final class AutoValue_Card extends C$AutoValue_Card {

    /* loaded from: classes10.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Card> {
        private volatile TypeAdapter<Boolean> boolean__adapter;
        private volatile TypeAdapter<CustomData> customData_adapter;
        private final Gson gson;
        private volatile TypeAdapter<P13NMetadata> p13NMetadata_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("contentId", "contentProvider", "contentType", "contentSource", "templateType");
            outline129.add("clickToDismiss");
            outline129.add("customData");
            outline129.add("customTemplateRoute");
            outline129.add("p13nMetadata");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Card.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Card mo8353read(JsonReader jsonReader) throws IOException {
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
            Boolean bool = null;
            CustomData customData = null;
            String str6 = null;
            P13NMetadata p13NMetadata = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("contentId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("contentProvider").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("contentType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("contentSource").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("templateType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("clickToDismiss").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter6 = this.boolean__adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(Boolean.class);
                            this.boolean__adapter = typeAdapter6;
                        }
                        bool = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("customData").equals(nextName)) {
                        TypeAdapter<CustomData> typeAdapter7 = this.customData_adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(CustomData.class);
                            this.customData_adapter = typeAdapter7;
                        }
                        customData = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("customTemplateRoute").equals(nextName)) {
                        TypeAdapter<String> typeAdapter8 = this.string_adapter;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter8;
                        }
                        str6 = typeAdapter8.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("p13nMetadata").equals(nextName)) {
                        TypeAdapter<P13NMetadata> typeAdapter9 = this.p13NMetadata_adapter;
                        if (typeAdapter9 == null) {
                            typeAdapter9 = this.gson.getAdapter(P13NMetadata.class);
                            this.p13NMetadata_adapter = typeAdapter9;
                        }
                        p13NMetadata = typeAdapter9.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Card(str, str2, str3, str4, str5, bool, customData, str6, p13NMetadata);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Card card) throws IOException {
            if (card == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("contentId"));
            if (card.getContentId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, card.getContentId());
            }
            jsonWriter.name(this.realFieldNames.get("contentProvider"));
            if (card.getContentProvider() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, card.getContentProvider());
            }
            jsonWriter.name(this.realFieldNames.get("contentType"));
            if (card.getContentType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, card.getContentType());
            }
            jsonWriter.name(this.realFieldNames.get("contentSource"));
            if (card.getContentSource() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, card.getContentSource());
            }
            jsonWriter.name(this.realFieldNames.get("templateType"));
            if (card.getTemplateType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, card.getTemplateType());
            }
            jsonWriter.name(this.realFieldNames.get("clickToDismiss"));
            if (card.getClickToDismiss() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter6 = this.boolean__adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(Boolean.class);
                    this.boolean__adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, card.getClickToDismiss());
            }
            jsonWriter.name(this.realFieldNames.get("customData"));
            if (card.getCustomData() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<CustomData> typeAdapter7 = this.customData_adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(CustomData.class);
                    this.customData_adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, card.getCustomData());
            }
            jsonWriter.name(this.realFieldNames.get("customTemplateRoute"));
            if (card.getCustomTemplateRoute() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.string_adapter;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, card.getCustomTemplateRoute());
            }
            jsonWriter.name(this.realFieldNames.get("p13nMetadata"));
            if (card.getP13nMetadata() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<P13NMetadata> typeAdapter9 = this.p13NMetadata_adapter;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.gson.getAdapter(P13NMetadata.class);
                    this.p13NMetadata_adapter = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, card.getP13nMetadata());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Card(final String str, final String str2, final String str3, final String str4, final String str5, @Nullable final Boolean bool, final CustomData customData, final String str6, final P13NMetadata p13NMetadata) {
        new Card(str, str2, str3, str4, str5, bool, customData, str6, p13NMetadata) { // from class: com.amazon.alexa.sendtoapp.activitycard.model.$AutoValue_Card
            private final Boolean clickToDismiss;
            private final String contentId;
            private final String contentProvider;
            private final String contentSource;
            private final String contentType;
            private final CustomData customData;
            private final String customTemplateRoute;
            private final P13NMetadata p13nMetadata;
            private final String templateType;

            /* renamed from: com.amazon.alexa.sendtoapp.activitycard.model.$AutoValue_Card$Builder */
            /* loaded from: classes10.dex */
            static final class Builder extends Card.Builder {
                private Boolean clickToDismiss;
                private String contentId;
                private String contentProvider;
                private String contentSource;
                private String contentType;
                private CustomData customData;
                private String customTemplateRoute;
                private P13NMetadata p13nMetadata;
                private String templateType;

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card build() {
                    String str = "";
                    if (this.contentId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " contentId");
                    }
                    if (this.contentProvider == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " contentProvider");
                    }
                    if (this.contentType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " contentType");
                    }
                    if (this.contentSource == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " contentSource");
                    }
                    if (this.templateType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " templateType");
                    }
                    if (this.customData == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " customData");
                    }
                    if (this.customTemplateRoute == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " customTemplateRoute");
                    }
                    if (this.p13nMetadata == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " p13nMetadata");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Card(this.contentId, this.contentProvider, this.contentType, this.contentSource, this.templateType, this.clickToDismiss, this.customData, this.customTemplateRoute, this.p13nMetadata);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setClickToDismiss(@Nullable Boolean bool) {
                    this.clickToDismiss = bool;
                    return this;
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setContentId(String str) {
                    if (str != null) {
                        this.contentId = str;
                        return this;
                    }
                    throw new NullPointerException("Null contentId");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setContentProvider(String str) {
                    if (str != null) {
                        this.contentProvider = str;
                        return this;
                    }
                    throw new NullPointerException("Null contentProvider");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setContentSource(String str) {
                    if (str != null) {
                        this.contentSource = str;
                        return this;
                    }
                    throw new NullPointerException("Null contentSource");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setContentType(String str) {
                    if (str != null) {
                        this.contentType = str;
                        return this;
                    }
                    throw new NullPointerException("Null contentType");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setCustomData(CustomData customData) {
                    if (customData != null) {
                        this.customData = customData;
                        return this;
                    }
                    throw new NullPointerException("Null customData");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setCustomTemplateRoute(String str) {
                    if (str != null) {
                        this.customTemplateRoute = str;
                        return this;
                    }
                    throw new NullPointerException("Null customTemplateRoute");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setP13nMetadata(P13NMetadata p13NMetadata) {
                    if (p13NMetadata != null) {
                        this.p13nMetadata = p13NMetadata;
                        return this;
                    }
                    throw new NullPointerException("Null p13nMetadata");
                }

                @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card.Builder
                public Card.Builder setTemplateType(String str) {
                    if (str != null) {
                        this.templateType = str;
                        return this;
                    }
                    throw new NullPointerException("Null templateType");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.contentId = str;
                    if (str2 != null) {
                        this.contentProvider = str2;
                        if (str3 != null) {
                            this.contentType = str3;
                            if (str4 != null) {
                                this.contentSource = str4;
                                if (str5 != null) {
                                    this.templateType = str5;
                                    this.clickToDismiss = bool;
                                    if (customData != null) {
                                        this.customData = customData;
                                        if (str6 != null) {
                                            this.customTemplateRoute = str6;
                                            if (p13NMetadata != null) {
                                                this.p13nMetadata = p13NMetadata;
                                                return;
                                            }
                                            throw new NullPointerException("Null p13nMetadata");
                                        }
                                        throw new NullPointerException("Null customTemplateRoute");
                                    }
                                    throw new NullPointerException("Null customData");
                                }
                                throw new NullPointerException("Null templateType");
                            }
                            throw new NullPointerException("Null contentSource");
                        }
                        throw new NullPointerException("Null contentType");
                    }
                    throw new NullPointerException("Null contentProvider");
                }
                throw new NullPointerException("Null contentId");
            }

            public boolean equals(Object obj) {
                Boolean bool2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Card)) {
                    return false;
                }
                Card card = (Card) obj;
                return this.contentId.equals(card.getContentId()) && this.contentProvider.equals(card.getContentProvider()) && this.contentType.equals(card.getContentType()) && this.contentSource.equals(card.getContentSource()) && this.templateType.equals(card.getTemplateType()) && ((bool2 = this.clickToDismiss) != null ? bool2.equals(card.getClickToDismiss()) : card.getClickToDismiss() == null) && this.customData.equals(card.getCustomData()) && this.customTemplateRoute.equals(card.getCustomTemplateRoute()) && this.p13nMetadata.equals(card.getP13nMetadata());
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            @Nullable
            public Boolean getClickToDismiss() {
                return this.clickToDismiss;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getContentId() {
                return this.contentId;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getContentProvider() {
                return this.contentProvider;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getContentSource() {
                return this.contentSource;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getContentType() {
                return this.contentType;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public CustomData getCustomData() {
                return this.customData;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getCustomTemplateRoute() {
                return this.customTemplateRoute;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public P13NMetadata getP13nMetadata() {
                return this.p13nMetadata;
            }

            @Override // com.amazon.alexa.sendtoapp.activitycard.model.Card
            public String getTemplateType() {
                return this.templateType;
            }

            public int hashCode() {
                int hashCode = (((((((((this.contentId.hashCode() ^ 1000003) * 1000003) ^ this.contentProvider.hashCode()) * 1000003) ^ this.contentType.hashCode()) * 1000003) ^ this.contentSource.hashCode()) * 1000003) ^ this.templateType.hashCode()) * 1000003;
                Boolean bool2 = this.clickToDismiss;
                return ((((((hashCode ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003) ^ this.customData.hashCode()) * 1000003) ^ this.customTemplateRoute.hashCode()) * 1000003) ^ this.p13nMetadata.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Card{contentId=");
                outline107.append(this.contentId);
                outline107.append(", contentProvider=");
                outline107.append(this.contentProvider);
                outline107.append(", contentType=");
                outline107.append(this.contentType);
                outline107.append(", contentSource=");
                outline107.append(this.contentSource);
                outline107.append(", templateType=");
                outline107.append(this.templateType);
                outline107.append(", clickToDismiss=");
                outline107.append(this.clickToDismiss);
                outline107.append(", customData=");
                outline107.append(this.customData);
                outline107.append(", customTemplateRoute=");
                outline107.append(this.customTemplateRoute);
                outline107.append(", p13nMetadata=");
                outline107.append(this.p13nMetadata);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
