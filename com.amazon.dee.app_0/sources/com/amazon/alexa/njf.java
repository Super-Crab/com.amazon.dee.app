package com.amazon.alexa;

import com.amazon.alexa.AbstractC0209ibG;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaWakeWord;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordIndices;
import com.amazon.alexa.client.alexaservice.speechrecognizer.payload.AutoValue_WakeWordInitiatorPayload;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: InitiatorAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class njf {
    public final Map<XWx, JsonObject> BIo = new ConcurrentHashMap();
    public final Gson zZm;

    @Inject
    public njf(Gson gson) {
        this.zZm = gson;
    }

    public JsonObject zZm(AlexaAudioMetadata alexaAudioMetadata) {
        if (AlexaProfile.CLOSE_TALK == alexaAudioMetadata.getAlexaProfile()) {
            return this.zZm.toJsonTree(AbstractC0209ibG.zZm(AbstractC0209ibG.zZm.PRESS_AND_HOLD)).getAsJsonObject();
        }
        AlexaWakeWord alexaWakeword = alexaAudioMetadata.getAlexaWakeword();
        if (alexaWakeword == null) {
            return this.zZm.toJsonTree(AbstractC0209ibG.zZm(AbstractC0209ibG.zZm.TAP)).getAsJsonObject();
        }
        return this.zZm.toJsonTree(AbstractC0209ibG.zZm(AbstractC0209ibG.zZm.WAKEWORD, new AutoValue_WakeWordInitiatorPayload(new AutoValue_WakeWordIndices(alexaWakeword.getStartIndexInSamples(), alexaWakeword.getEndIndexInSamples()), alexaWakeword.getWakeWordName()))).getAsJsonObject();
    }
}
