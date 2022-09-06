package com.amazon.alexa.featureservice.recordTrigger;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.featureservice.util.JsonUtils;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class RecordTriggerServiceHelper {
    private static final String TAG = "RecordTriggerServiceHelper";
    private Lazy<Mobilytics> mobilyticsLazy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecordTriggerServiceHelper(Lazy<Mobilytics> lazy) {
        this.mobilyticsLazy = lazy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List<ResponseTreatment> fromResponseJson(@NonNull String str) {
        Preconditions.checkNotNull(str, "Json body can not null");
        String.format("Record Trigger response payload: %s", str);
        ArrayList arrayList = new ArrayList();
        try {
            JsonObject jsonObject = JsonUtils.toJsonObject(str);
            if (jsonObject.has(FeatureConstants.Network.TREATMENTS)) {
                JsonObject asJsonObject = jsonObject.getAsJsonObject(FeatureConstants.Network.TREATMENTS);
                for (String str2 : asJsonObject.keySet()) {
                    JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(str2);
                    String string = JsonUtils.getString(asJsonObject2, FeatureConstants.Network.ALLOCATION_ID);
                    String string2 = JsonUtils.getString(asJsonObject2, FeatureConstants.Network.TREATMENT);
                    boolean z = JsonUtils.getBoolean(asJsonObject2, FeatureConstants.Network.TRIGGER_ON_USE);
                    String string3 = JsonUtils.getString(asJsonObject2, FeatureConstants.Network.RECORD_STATUS);
                    if (string2 != null) {
                        arrayList.add(new ResponseTreatment(str2, string2, string, z, string3));
                    } else {
                        Log.w(TAG, String.format("Payload entry for %s was missing treatment; ignoring", str2));
                        recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.MISSING_TREATMENT, null);
                    }
                }
            } else {
                Log.w(TAG, "Network payload is missing treatments; invalid payload");
            }
        } catch (JsonSyntaxException e) {
            Log.w(TAG, "Network payload was not the right syntax", e);
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "Network payload was not a json object", e2);
        }
        return arrayList;
    }

    @VisibleForTesting
    void recordMetricsEvent(@NonNull String str, @NonNull String str2, @Nullable Exception exc) {
        this.mobilyticsLazy.mo358get().recordCriticalEvent(GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), str2, FeatureServiceMetrics.Subcomponent.RECORD_TRIGGER, exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String toRequestJson(@NonNull List<RequestTreatment> list) {
        Preconditions.checkNotNull(list, "List can't be null");
        JsonObject jsonObject = new JsonObject();
        for (RequestTreatment requestTreatment : list) {
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty(FeatureConstants.Network.ALLOCATION_ID, requestTreatment.getAllocationId());
            jsonObject2.addProperty(FeatureConstants.Network.TIME_USED, Long.valueOf(requestTreatment.getTimeUsed().getTime()));
            jsonObject2.addProperty(FeatureConstants.Network.TREATMENT_USED, requestTreatment.getTreatmentUsed());
            jsonObject.add(requestTreatment.getFeatureName(), jsonObject2);
        }
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add(FeatureConstants.Network.TREATMENTS, jsonObject);
        return jsonObject3.toString();
    }
}
