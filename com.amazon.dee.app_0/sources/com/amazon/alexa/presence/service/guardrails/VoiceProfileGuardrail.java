package com.amazon.alexa.presence.service.guardrails;

import android.util.Log;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
import com.amazon.devicesetup.common.v1.RegistrationState;
import com.dee.app.http.CoralService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class VoiceProfileGuardrail implements PresenceFeatureGuardrailInterface {
    private static final String GET_SPEAKER_BY_PERSON_ID_PARAMETERIZED_URL = "/api/speakers/person/id/<PERSON_ID>";
    private final CoralService coralService;
    private final DeviceInformation deviceInformation;
    private final IdentityService identityService;
    private final Locale locale;
    private MetricsRecorder metrics;
    private static final Logger LOG = LoggerFactory.getLogger(VoiceProfileGuardrail.class);
    private static final Set<String> COMPLETED_VOICE_STATUSES = new HashSet(Arrays.asList(RegistrationState.COMPLETE, "START"));
    private static final String TAG = VoiceProfileGuardrail.class.getName();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class GetSpeakerByPersonIdResponse {
        public Speaker speaker;

        private GetSpeakerByPersonIdResponse() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class Speaker {
        public String firstName;
        public String personId;
        public String speakerCID;
        public String speakerUUID;
        public String voiceTrainingStatus;

        private Speaker() {
        }
    }

    public VoiceProfileGuardrail(CoralService coralService, IdentityService identityService, DeviceInformation deviceInformation, Locale locale) {
        this.coralService = coralService;
        this.identityService = identityService;
        this.deviceInformation = deviceInformation;
        this.locale = locale;
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    private String getPersonId() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null || user.getUserProfile() == null) {
            return null;
        }
        return user.getUserProfile().getDirectedId();
    }

    private String getSpeakerByPersonIdUrl(String str) {
        return GET_SPEAKER_BY_PERSON_ID_PARAMETERIZED_URL.replaceAll("<PERSON_ID>", str);
    }

    private Boolean hasVoiceProfile(String str) throws Exception {
        Speaker speaker;
        boolean z = false;
        if (str == null) {
            LOG.info("No personId passed, skipping voice profile check");
            return false;
        }
        GetSpeakerByPersonIdResponse getSpeakerByPersonIdResponse = (GetSpeakerByPersonIdResponse) this.coralService.request(getSpeakerByPersonIdUrl(str)).get().as(GetSpeakerByPersonIdResponse.class).execute();
        if (getSpeakerByPersonIdResponse != null && (speaker = getSpeakerByPersonIdResponse.speaker) != null && speaker.voiceTrainingStatus != null) {
            z = true;
        }
        if (!Boolean.valueOf(z).booleanValue()) {
            LOG.info("No voice training status set");
            return false;
        }
        String str2 = getSpeakerByPersonIdResponse.speaker.voiceTrainingStatus;
        LOG.info("Voice training status: {}", str2);
        return Boolean.valueOf(COMPLETED_VOICE_STATUSES.contains(str2.toUpperCase()));
    }

    @Override // com.amazon.alexa.presence.service.guardrails.PresenceFeatureGuardrailInterface
    public boolean checkGuardrail() {
        try {
            boolean booleanValue = hasVoiceProfile(getPersonId()).booleanValue();
            if (booleanValue) {
                this.metrics.recordCount(MetricsId.PASSED_GUARDRAIL, MetricsMethod.VOICE_PROFILE_GUARDRAIL);
            } else {
                this.metrics.recordCount(MetricsId.FAILED_GUARDRAIL, MetricsMethod.VOICE_PROFILE_GUARDRAIL);
            }
            return booleanValue;
        } catch (Exception e) {
            Log.w(TAG, e);
            return false;
        }
    }
}
