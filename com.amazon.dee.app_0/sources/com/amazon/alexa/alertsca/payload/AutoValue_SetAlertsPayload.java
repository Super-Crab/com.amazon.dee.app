package com.amazon.alexa.alertsca.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.alertsca.AlertLabel;
import com.amazon.alexa.alertsca.AlertsContract;
import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.Asset;
import com.amazon.alexa.alertsca.AssetIdentifier;
import com.amazon.alexa.alertsca.payload.SetAlertsPayload;
import com.amazon.alexa.api.compat.alerts.AlertType;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_SetAlertsPayload extends C$AutoValue_SetAlertsPayload {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SetAlertsPayload> {
        private volatile TypeAdapter<AlertLabel> alertLabel_adapter;
        private volatile TypeAdapter<AlertType> alertType_adapter;
        private volatile TypeAdapter<AlertsToken> alertsToken_adapter;
        private volatile TypeAdapter<AssetIdentifier> assetIdentifier_adapter;
        private volatile TypeAdapter<Date> date_adapter;
        private final Gson gson;
        private volatile TypeAdapter<List<AssetIdentifier>> list__assetIdentifier_adapter;
        private volatile TypeAdapter<Long> long__adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Set<Asset>> set__asset_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("token", "type", AlertsContract.AlertRecordEntry.COLUMN_NAME_SCHEDULED_TIME, "assets", "assetPlayOrder");
            outline129.add(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_BACKGROUND_ALERT_ASSET);
            outline129.add(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_LOOP_COUNT);
            outline129.add("label");
            outline129.add("loopPauseInMilliSeconds");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SetAlertsPayload.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SetAlertsPayload mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            AlertsToken alertsToken = null;
            AlertType alertType = null;
            Date date = null;
            Set<Asset> set = null;
            List<AssetIdentifier> list = null;
            AssetIdentifier assetIdentifier = null;
            Long l = null;
            AlertLabel alertLabel = null;
            Long l2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("token").equals(nextName)) {
                        TypeAdapter<AlertsToken> typeAdapter = this.alertsToken_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(AlertsToken.class);
                            this.alertsToken_adapter = typeAdapter;
                        }
                        alertsToken = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("type").equals(nextName)) {
                        TypeAdapter<AlertType> typeAdapter2 = this.alertType_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(AlertType.class);
                            this.alertType_adapter = typeAdapter2;
                        }
                        alertType = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(AlertsContract.AlertRecordEntry.COLUMN_NAME_SCHEDULED_TIME).equals(nextName)) {
                        TypeAdapter<Date> typeAdapter3 = this.date_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(Date.class);
                            this.date_adapter = typeAdapter3;
                        }
                        date = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("assets").equals(nextName)) {
                        TypeAdapter<Set<Asset>> typeAdapter4 = this.set__asset_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(TypeToken.getParameterized(Set.class, Asset.class));
                            this.set__asset_adapter = typeAdapter4;
                        }
                        set = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("assetPlayOrder").equals(nextName)) {
                        TypeAdapter<List<AssetIdentifier>> typeAdapter5 = this.list__assetIdentifier_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(TypeToken.getParameterized(List.class, AssetIdentifier.class));
                            this.list__assetIdentifier_adapter = typeAdapter5;
                        }
                        list = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_BACKGROUND_ALERT_ASSET).equals(nextName)) {
                        TypeAdapter<AssetIdentifier> typeAdapter6 = this.assetIdentifier_adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(AssetIdentifier.class);
                            this.assetIdentifier_adapter = typeAdapter6;
                        }
                        assetIdentifier = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_LOOP_COUNT).equals(nextName)) {
                        TypeAdapter<Long> typeAdapter7 = this.long__adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(Long.class);
                            this.long__adapter = typeAdapter7;
                        }
                        l = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("label").equals(nextName)) {
                        TypeAdapter<AlertLabel> typeAdapter8 = this.alertLabel_adapter;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.gson.getAdapter(AlertLabel.class);
                            this.alertLabel_adapter = typeAdapter8;
                        }
                        alertLabel = typeAdapter8.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("loopPauseInMilliSeconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter9 = this.long__adapter;
                        if (typeAdapter9 == null) {
                            typeAdapter9 = this.gson.getAdapter(Long.class);
                            this.long__adapter = typeAdapter9;
                        }
                        l2 = typeAdapter9.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetAlertsPayload(alertsToken, alertType, date, set, list, assetIdentifier, l, alertLabel, l2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SetAlertsPayload setAlertsPayload) throws IOException {
            if (setAlertsPayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("token"));
            if (setAlertsPayload.getToken() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AlertsToken> typeAdapter = this.alertsToken_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(AlertsToken.class);
                    this.alertsToken_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, setAlertsPayload.getToken());
            }
            jsonWriter.name(this.realFieldNames.get("type"));
            if (setAlertsPayload.getType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AlertType> typeAdapter2 = this.alertType_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(AlertType.class);
                    this.alertType_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, setAlertsPayload.getType());
            }
            jsonWriter.name(this.realFieldNames.get(AlertsContract.AlertRecordEntry.COLUMN_NAME_SCHEDULED_TIME));
            if (setAlertsPayload.getScheduledTime() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Date> typeAdapter3 = this.date_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(Date.class);
                    this.date_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, setAlertsPayload.getScheduledTime());
            }
            jsonWriter.name(this.realFieldNames.get("assets"));
            if (setAlertsPayload.getAssets() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<Asset>> typeAdapter4 = this.set__asset_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(TypeToken.getParameterized(Set.class, Asset.class));
                    this.set__asset_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, setAlertsPayload.getAssets());
            }
            jsonWriter.name(this.realFieldNames.get("assetPlayOrder"));
            if (setAlertsPayload.getAssetPlayOrder() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<AssetIdentifier>> typeAdapter5 = this.list__assetIdentifier_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(TypeToken.getParameterized(List.class, AssetIdentifier.class));
                    this.list__assetIdentifier_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, setAlertsPayload.getAssetPlayOrder());
            }
            jsonWriter.name(this.realFieldNames.get(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_BACKGROUND_ALERT_ASSET));
            if (setAlertsPayload.getBackgroundAlertAsset() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AssetIdentifier> typeAdapter6 = this.assetIdentifier_adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(AssetIdentifier.class);
                    this.assetIdentifier_adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, setAlertsPayload.getBackgroundAlertAsset());
            }
            jsonWriter.name(this.realFieldNames.get(AlertsContract.AlertAssetInfoEntry.COLUMN_NAME_LOOP_COUNT));
            if (setAlertsPayload.getLoopCount() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter7 = this.long__adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(Long.class);
                    this.long__adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, setAlertsPayload.getLoopCount());
            }
            jsonWriter.name(this.realFieldNames.get("label"));
            if (setAlertsPayload.getLabel() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AlertLabel> typeAdapter8 = this.alertLabel_adapter;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.gson.getAdapter(AlertLabel.class);
                    this.alertLabel_adapter = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, setAlertsPayload.getLabel());
            }
            jsonWriter.name(this.realFieldNames.get("loopPauseInMilliSeconds"));
            if (setAlertsPayload.getLoopPauseInMilliSeconds() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter9 = this.long__adapter;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.gson.getAdapter(Long.class);
                    this.long__adapter = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, setAlertsPayload.getLoopPauseInMilliSeconds());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SetAlertsPayload(final AlertsToken alertsToken, final AlertType alertType, final Date date, @Nullable final Set<Asset> set, @Nullable final List<AssetIdentifier> list, @Nullable final AssetIdentifier assetIdentifier, @Nullable final Long l, @Nullable final AlertLabel alertLabel, @Nullable final Long l2) {
        new SetAlertsPayload(alertsToken, alertType, date, set, list, assetIdentifier, l, alertLabel, l2) { // from class: com.amazon.alexa.alertsca.payload.$AutoValue_SetAlertsPayload
            private final List<AssetIdentifier> assetPlayOrder;
            private final Set<Asset> assets;
            private final AssetIdentifier backgroundAlertAsset;
            private final AlertLabel label;
            private final Long loopCount;
            private final Long loopPauseInMilliSeconds;
            private final Date scheduledTime;
            private final AlertsToken token;
            private final AlertType type;

            /* renamed from: com.amazon.alexa.alertsca.payload.$AutoValue_SetAlertsPayload$Builder */
            /* loaded from: classes6.dex */
            static final class Builder extends SetAlertsPayload.Builder {
                private List<AssetIdentifier> assetPlayOrder;
                private Set<Asset> assets;
                private AssetIdentifier backgroundAlertAsset;
                private AlertLabel label;
                private Long loopCount;
                private Long loopPauseInMilliSeconds;
                private Date scheduledTime;
                private AlertsToken token;
                private AlertType type;

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload build() {
                    String str = "";
                    if (this.token == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " token");
                    }
                    if (this.type == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " type");
                    }
                    if (this.scheduledTime == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " scheduledTime");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_SetAlertsPayload(this.token, this.type, this.scheduledTime, this.assets, this.assetPlayOrder, this.backgroundAlertAsset, this.loopCount, this.label, this.loopPauseInMilliSeconds);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setAssetPlayOrder(@Nullable List<AssetIdentifier> list) {
                    this.assetPlayOrder = list;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setAssets(@Nullable Set<Asset> set) {
                    this.assets = set;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setBackgroundAlertAsset(@Nullable AssetIdentifier assetIdentifier) {
                    this.backgroundAlertAsset = assetIdentifier;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setLabel(@Nullable AlertLabel alertLabel) {
                    this.label = alertLabel;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setLoopCount(@Nullable Long l) {
                    this.loopCount = l;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setLoopPauseInMilliSeconds(@Nullable Long l) {
                    this.loopPauseInMilliSeconds = l;
                    return this;
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setScheduledTime(Date date) {
                    if (date != null) {
                        this.scheduledTime = date;
                        return this;
                    }
                    throw new NullPointerException("Null scheduledTime");
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setToken(AlertsToken alertsToken) {
                    if (alertsToken != null) {
                        this.token = alertsToken;
                        return this;
                    }
                    throw new NullPointerException("Null token");
                }

                @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload.Builder
                public SetAlertsPayload.Builder setType(AlertType alertType) {
                    if (alertType != null) {
                        this.type = alertType;
                        return this;
                    }
                    throw new NullPointerException("Null type");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (alertsToken != null) {
                    this.token = alertsToken;
                    if (alertType != null) {
                        this.type = alertType;
                        if (date != null) {
                            this.scheduledTime = date;
                            this.assets = set;
                            this.assetPlayOrder = list;
                            this.backgroundAlertAsset = assetIdentifier;
                            this.loopCount = l;
                            this.label = alertLabel;
                            this.loopPauseInMilliSeconds = l2;
                            return;
                        }
                        throw new NullPointerException("Null scheduledTime");
                    }
                    throw new NullPointerException("Null type");
                }
                throw new NullPointerException("Null token");
            }

            public boolean equals(Object obj) {
                Set<Asset> set2;
                List<AssetIdentifier> list2;
                AssetIdentifier assetIdentifier2;
                Long l3;
                AlertLabel alertLabel2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SetAlertsPayload)) {
                    return false;
                }
                SetAlertsPayload setAlertsPayload = (SetAlertsPayload) obj;
                if (this.token.equals(setAlertsPayload.getToken()) && this.type.equals(setAlertsPayload.getType()) && this.scheduledTime.equals(setAlertsPayload.getScheduledTime()) && ((set2 = this.assets) != null ? set2.equals(setAlertsPayload.getAssets()) : setAlertsPayload.getAssets() == null) && ((list2 = this.assetPlayOrder) != null ? list2.equals(setAlertsPayload.getAssetPlayOrder()) : setAlertsPayload.getAssetPlayOrder() == null) && ((assetIdentifier2 = this.backgroundAlertAsset) != null ? assetIdentifier2.equals(setAlertsPayload.getBackgroundAlertAsset()) : setAlertsPayload.getBackgroundAlertAsset() == null) && ((l3 = this.loopCount) != null ? l3.equals(setAlertsPayload.getLoopCount()) : setAlertsPayload.getLoopCount() == null) && ((alertLabel2 = this.label) != null ? alertLabel2.equals(setAlertsPayload.getLabel()) : setAlertsPayload.getLabel() == null)) {
                    Long l4 = this.loopPauseInMilliSeconds;
                    if (l4 == null) {
                        if (setAlertsPayload.getLoopPauseInMilliSeconds() == null) {
                            return true;
                        }
                    } else if (l4.equals(setAlertsPayload.getLoopPauseInMilliSeconds())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public List<AssetIdentifier> getAssetPlayOrder() {
                return this.assetPlayOrder;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public Set<Asset> getAssets() {
                return this.assets;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public AssetIdentifier getBackgroundAlertAsset() {
                return this.backgroundAlertAsset;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public AlertLabel getLabel() {
                return this.label;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public Long getLoopCount() {
                return this.loopCount;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            @Nullable
            public Long getLoopPauseInMilliSeconds() {
                return this.loopPauseInMilliSeconds;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            public Date getScheduledTime() {
                return this.scheduledTime;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            public AlertsToken getToken() {
                return this.token;
            }

            @Override // com.amazon.alexa.alertsca.payload.SetAlertsPayload
            public AlertType getType() {
                return this.type;
            }

            public int hashCode() {
                int hashCode = (((((this.token.hashCode() ^ 1000003) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.scheduledTime.hashCode()) * 1000003;
                Set<Asset> set2 = this.assets;
                int i = 0;
                int hashCode2 = (hashCode ^ (set2 == null ? 0 : set2.hashCode())) * 1000003;
                List<AssetIdentifier> list2 = this.assetPlayOrder;
                int hashCode3 = (hashCode2 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
                AssetIdentifier assetIdentifier2 = this.backgroundAlertAsset;
                int hashCode4 = (hashCode3 ^ (assetIdentifier2 == null ? 0 : assetIdentifier2.hashCode())) * 1000003;
                Long l3 = this.loopCount;
                int hashCode5 = (hashCode4 ^ (l3 == null ? 0 : l3.hashCode())) * 1000003;
                AlertLabel alertLabel2 = this.label;
                int hashCode6 = (hashCode5 ^ (alertLabel2 == null ? 0 : alertLabel2.hashCode())) * 1000003;
                Long l4 = this.loopPauseInMilliSeconds;
                if (l4 != null) {
                    i = l4.hashCode();
                }
                return hashCode6 ^ i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetAlertsPayload{token=");
                outline107.append(this.token);
                outline107.append(", type=");
                outline107.append(this.type);
                outline107.append(", scheduledTime=");
                outline107.append(this.scheduledTime);
                outline107.append(", assets=");
                outline107.append(this.assets);
                outline107.append(", assetPlayOrder=");
                outline107.append(this.assetPlayOrder);
                outline107.append(", backgroundAlertAsset=");
                outline107.append(this.backgroundAlertAsset);
                outline107.append(", loopCount=");
                outline107.append(this.loopCount);
                outline107.append(", label=");
                outline107.append(this.label);
                outline107.append(", loopPauseInMilliSeconds=");
                outline107.append(this.loopPauseInMilliSeconds);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}
