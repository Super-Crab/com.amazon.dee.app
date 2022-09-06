package com.amazon.alexa.identity;

import android.util.Log;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.dee.app.metrics.MetricsService;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.JsonObject;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ApesCaller implements ApesCallerInterface {
    private static final String APES_PROFILES_ENDPOINT = "/api/profile/graphql";
    static final String TAG = Utils.tag(ApesCaller.class);
    private final CoralService coralService;
    private final MetricsService metricsService;

    public ApesCaller(CoralService coralService, MetricsService metricsService) {
        this.coralService = coralService;
        this.metricsService = metricsService;
    }

    private String findMatchingPersonId(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONObject(str2).getJSONObject("data").getJSONObject("account").getJSONArray("profiles");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                if (jSONObject.has("alexaInfo") && !jSONObject.isNull("alexaInfo")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("alexaInfo");
                    if (jSONObject2.has("commsInfo") && !jSONObject2.isNull("commsInfo")) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("commsInfo");
                        if (jSONObject3.has("commsPersonId") && str.equals(jSONObject3.getString("commsPersonId"))) {
                            return jSONObject.getString(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID);
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Exception while parsing GraphQL response to match PersonIdV1 to PersonIdV2", e);
            this.metricsService.recordEvent(Metric.Event.CALL_APES_PARSE_EXCEPTION, TAG, Utils.CUSTOM_ENTRIES);
            return null;
        }
    }

    private boolean isValidApesResponse(String str, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.isNull(MAPWebViewEventHelper.KEY_ERRORS) && i >= 200 && i <= 299) {
                return true;
            }
            String obj = jSONObject.isNull(MAPWebViewEventHelper.KEY_ERRORS) ? "" : jSONObject.get(MAPWebViewEventHelper.KEY_ERRORS).toString();
            throw new Exception("Invalid APES response with status code: " + i + ", and errors: " + obj);
        } catch (Exception e) {
            Log.e(TAG, "Exception while checking validity of APES response", e);
            return false;
        }
    }

    @Override // com.amazon.alexa.identity.ApesCallerInterface
    public String getPersonId(String str) {
        Response execute;
        String string;
        try {
            try {
                try {
                    this.metricsService.startTimer(Metric.Event.GET_PERSONID_DURATION, ApesCaller.class.getSimpleName(), Utils.CUSTOM_ENTRIES);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(SearchIntents.EXTRA_QUERY, "query account { account { profiles { personId alexaInfo { commsInfo { commsPersonId } } } } }");
                    execute = this.coralService.request(APES_PROFILES_ENDPOINT).withHeader("Content-Type", "application/json").post(jsonObject.toString()).asRaw().execute();
                    string = execute.body().string();
                } catch (CoralServiceException e) {
                    Log.e(TAG, "CoralServiceException while calling APES", e);
                    this.metricsService.recordOccurrence(Metric.Event.CALL_APES_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
                }
            } catch (Exception e2) {
                Log.e(TAG, "Exception while calling APES", e2);
                this.metricsService.recordOccurrence(Metric.Event.CALL_APES_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
            }
            if (!isValidApesResponse(string, execute.code())) {
                this.metricsService.recordOccurrence(Metric.Event.CALL_APES_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
                return null;
            }
            this.metricsService.recordOccurrence(Metric.Event.CALL_APES_SUCCESS_RATE, TAG, true, Utils.CUSTOM_ENTRIES);
            String findMatchingPersonId = findMatchingPersonId(str, string);
            if (findMatchingPersonId == null) {
                this.metricsService.recordOccurrence(Metric.Event.MATCH_PERSONID_SUCCESS_RATE, TAG, false, Utils.CUSTOM_ENTRIES);
            } else {
                this.metricsService.recordOccurrence(Metric.Event.MATCH_PERSONID_SUCCESS_RATE, TAG, true, Utils.CUSTOM_ENTRIES);
            }
            return findMatchingPersonId;
        } finally {
            this.metricsService.recordTimer(Metric.Event.GET_PERSONID_DURATION, Utils.CUSTOM_ENTRIES);
        }
    }
}
