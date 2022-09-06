package com.amazon.alexa.presence;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.bleconn.identity.clients.RoamingClient;
import com.amazon.alexa.presence.library.ContextHelper;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.service.AlexaBeaconDetectorService;
import com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceController {
    private static final String TAG = "com.amazon.alexa.presence.PresenceController";
    private final MetricsServiceV2 metricsService;
    private final PersistentLocalStorage.PresenceDataStore presenceDataStore;
    private final PresenceSubComponents presenceSubComponents;
    private final RoamingClient roamingClient;
    private final PresenceForegroundServiceStateAdviser serviceStateAdviser;

    @Inject
    public PresenceController(RoamingClient roamingClient, PresenceSubComponents presenceSubComponents, PersistentLocalStorage.PresenceDataStore presenceDataStore, PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser, MetricsServiceV2 metricsServiceV2) {
        this.roamingClient = roamingClient;
        this.presenceSubComponents = presenceSubComponents;
        this.presenceDataStore = presenceDataStore;
        this.serviceStateAdviser = presenceForegroundServiceStateAdviser;
        this.metricsService = metricsServiceV2;
    }

    private String getPersonId() {
        PersonIdProvider personIdProvider = this.presenceSubComponents.getPersonIdProvider();
        if (personIdProvider == null) {
            return null;
        }
        return personIdProvider.getPersonId();
    }

    private void resolveForegroundServiceState(Context context) {
        PresenceForegroundServiceStateAdviser presenceForegroundServiceStateAdviser = new PresenceForegroundServiceStateAdviser(context);
        PresenceJobService.Helper helper = new PresenceJobService.Helper(context);
        if (presenceForegroundServiceStateAdviser.serviceShouldBeRunning()) {
            MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V2_START);
            helper.scheduleForegroundServiceStart();
            return;
        }
        MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V2_STOP);
        helper.scheduleForegroundServiceStop();
    }

    boolean addPresenceRequestToLocalStorage(Context context, String str) {
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.ADD_PRESENCE_DOMAIN, MetricsUtil.Method.PERSISTENT_LOCAL_STORAGE);
        return this.presenceDataStore.addDomain(str.toLowerCase(), context);
    }

    public void clearPresenceDomains(Context context) {
        this.presenceDataStore.clearStorage(context);
        disablePresence(context, false);
    }

    public void disablePresence(Context context, boolean z) {
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.STOP_SCANNING_EVENT_BUS, MetricsUtil.Method.STOP_SCANNING_WORKFLOW);
        MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PHONE_ID_STOP);
        if (this.serviceStateAdviser.isBleConnV2Enabled()) {
            resolveForegroundServiceState(context);
        } else {
            new PresenceJobService.Helper(context).scheduleForegroundServiceStop();
        }
        if (!z && this.serviceStateAdviser.isBleConnV1Enabled()) {
            AlexaBeaconDetectorService.stopScanning(context);
            MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V1_STOP);
            return;
        }
        AlexaBeaconDetectorService.forceStopScanning(context);
        MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V1_STOP);
    }

    public void disablePresenceDomain(Context context, String str) {
        GeneratedOutlineSupport1.outline163("Disabling domain ", str, TAG);
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.PRESENCE_DISABLE_MESSAGE, MetricsUtil.Method.PRESENCE_SUBSCRIBER_MESSAGE_RECEIVED);
        if (removePresenceRequestFromLocalStorage(context, str)) {
            this.presenceSubComponents.destroyPresenceComponentsOnNoDomainRequest();
        }
        if (this.serviceStateAdviser.isBleConnV2Enabled()) {
            new PresenceJobService.Helper(context).scheduleIdentityRefresh(true);
        }
        disablePresence(context, false);
    }

    public void enablePresence(Context context) {
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.START_SCANNING_EVENT_BUS, MetricsUtil.Method.START_SCANNING_WORKFLOW);
        MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PHONE_ID_START);
        if (this.serviceStateAdviser.isBleConnV2Enabled()) {
            resolveForegroundServiceState(context);
            MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V2_START);
        }
        if (this.serviceStateAdviser.isBleConnV1Enabled()) {
            AlexaBeaconDetectorService.executeActionToStartScanning(context);
            MetricsUtil.recordCount(this.metricsService, "presence", MetricsUtil.Method.PRESENCE_V1_START);
        }
    }

    public void enablePresenceDomain(Context context, String str) {
        if (isPresenceDomainEnabled(context, str)) {
            String str2 = TAG;
            Log.i(str2, "Requested to enable domain " + str + ".  Already enabled.");
            return;
        }
        GeneratedOutlineSupport1.outline163("Enabling domain ", str, TAG);
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.PRESENCE_ENABLE_MESSAGE, MetricsUtil.Method.PRESENCE_SUBSCRIBER_MESSAGE_RECEIVED);
        if (addPresenceRequestToLocalStorage(context, str)) {
            this.presenceSubComponents.initializePresenceComponentsAfterDomainRequest();
        }
        if (this.serviceStateAdviser.isBleConnV2Enabled()) {
            Log.i(TAG, "Domain added, refreshing identity");
            new PresenceJobService.Helper(context).scheduleIdentityRefresh(true);
        }
        enablePresence(context);
    }

    boolean isPresenceDomainEnabled(Context context, String str) {
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.ADD_PRESENCE_DOMAIN, MetricsUtil.Method.PERSISTENT_LOCAL_STORAGE);
        return this.presenceDataStore.getDomains(context).contains(str.toLowerCase());
    }

    boolean removePresenceRequestFromLocalStorage(Context context, String str) {
        MetricsUtil.recordCount(this.metricsService, MetricsUtil.MetricsId.REMOVE_PRESENCE_DOMAIN, MetricsUtil.Method.PERSISTENT_LOCAL_STORAGE);
        return this.presenceDataStore.removeDomain(str.toLowerCase(), context);
    }

    public void restorePresenceState(Context context) {
        Log.i(TAG, "Attempting to restore presence state.");
        String personId = getPersonId();
        if (personId != null && !personId.isEmpty()) {
            if (!this.serviceStateAdviser.isBleConnV2Enabled() || !this.roamingClient.isRoamingEnabled(personId)) {
                return;
            }
            MetricsUtil.recordCount(this.metricsService, "presence", "presence_state_restored");
            enablePresenceDomain(context, ContextHelper.ROAMING_GUEST_DOMAIN);
            return;
        }
        Log.w(TAG, "Unable to restore roaming state, id for current profile could not be located.");
    }
}
