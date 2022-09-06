package com.amazon.alexa.delegatedidentity;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.delegatedidentity.api.Metric;
import com.amazon.alexa.delegatedidentity.model.RetrieveDelegationTokenResponse;
import com.amazon.alexa.delegatedidentity.model.TerminateDelegationResponse;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.dee.app.http.CoralService;
import com.dee.app.http.CoralServiceException;
import com.dee.app.metrics.MetricsService;
import com.google.gson.JsonObject;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public class PIMSTokenAccessor implements TokenAccessor {
    private static final String NO_DELEGATION_TOKEN = "";
    private static final String RETRIEVE_DELEGATION_TOKEN_PATH = "/api/person-auth/v1/token/delegation";
    private static final String TAG = "com.amazon.alexa.delegatedidentity.PIMSTokenAccessor";
    private static final String TERMINATE_DELEGATION_PATH = "/api/person-auth/v1/terminate-delegation";
    @NonNull
    private final CoralService coralService;
    private final Provider<MetricsService> metricsService;

    public PIMSTokenAccessor(ComponentRegistry componentRegistry, @NonNull CoralService coralService) {
        this.coralService = coralService;
        this.metricsService = componentRegistry.getLazy(MetricsService.class);
    }

    private static JsonObject retrieveDelegationTokenRequest(String str, Boolean bool) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", str);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("resourceOwnerPersonId", jsonObject);
        jsonObject2.addProperty("startSession", bool);
        return jsonObject2;
    }

    @Override // com.amazon.alexa.delegatedidentity.TokenAccessor
    public String getDelegationTokenForPerson(String str, String str2, Boolean bool) {
        this.metricsService.mo10268get().recordEvent(Metric.Event.PIMS_DELEGATION_TOKEN_START);
        String str3 = "";
        try {
            str3 = ((RetrieveDelegationTokenResponse) pimsPost(RETRIEVE_DELEGATION_TOKEN_PATH, str2, retrieveDelegationTokenRequest(str, bool), RetrieveDelegationTokenResponse.class)).delegationToken;
            this.metricsService.mo10268get().recordEvent(Metric.Event.PIMS_DELEGATION_TOKEN_COMPLETE);
            return str3;
        } catch (CoralServiceException e) {
            Log.e(TAG, "CoralServiceException while retrieveDelegatedTokenFromPIMS", e);
            this.metricsService.mo10268get().recordEvent(Metric.Event.PIMS_DELEGATION_TOKEN_CORAL_FAIL);
            return str3;
        } catch (Exception e2) {
            Log.e(TAG, "Exception while retrieveDelegatedTokenFromPIMS", e2);
            this.metricsService.mo10268get().recordEvent(Metric.Event.PIMS_DELEGATION_TOKEN_FAIL);
            return str3;
        }
    }

    public <T> T pimsPost(String str, String str2, Object obj, Class<T> cls) throws CoralServiceException {
        CoralService.Request withHeader = this.coralService.request(str).withHeader("Content-Type", "application/json");
        return (T) withHeader.withHeader("Authorization", "Bearer " + str2).post(obj).as(cls).execute();
    }

    @Override // com.amazon.alexa.delegatedidentity.TokenAccessor
    public void terminateDelegation(@NonNull String str) throws Exception {
        this.metricsService.mo10268get().startTimer(Metric.Event.PIMS_TERMINATE_DELEGATION_DURATION, PIMSTokenAccessor.class.getSimpleName(), null);
        try {
            try {
                pimsPost(TERMINATE_DELEGATION_PATH, str, null, TerminateDelegationResponse.class);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.PIMS_TERMINATE_DELEGATION_SUCCESS_RATE, TAG, true, null);
            } catch (CoralServiceException e) {
                Log.e(TAG, "CoralServiceException while calling PIMS terminateDelegation", e);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.PIMS_TERMINATE_DELEGATION_SUCCESS_RATE, TAG, false, null);
                throw e;
            } catch (Exception e2) {
                Log.e(TAG, "Exception while calling PIMS terminateDelegation", e2);
                this.metricsService.mo10268get().recordOccurrence(Metric.Event.PIMS_TERMINATE_DELEGATION_SUCCESS_RATE, TAG, false, null);
                throw e2;
            }
        } finally {
            this.metricsService.mo10268get().recordTimer(Metric.Event.PIMS_TERMINATE_DELEGATION_DURATION);
        }
    }
}
