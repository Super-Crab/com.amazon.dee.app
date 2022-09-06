package com.amazon.alexa.alertsca;

import android.util.Log;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlexaEventSender {
    private static final String TAG = "AlexaEventSender";
    private final AlexaMobileFrameworkApis alexaMobileFrameworkApis;

    @Inject
    public AlexaEventSender(AlexaMobileFrameworkApis alexaMobileFrameworkApis) {
        Preconditions.notNull(alexaMobileFrameworkApis, "alexaMobileFrameworkApis is null");
        this.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
    }

    public void send(AlexaEvent alexaEvent, boolean z) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("send: ");
        outline107.append(alexaEvent.getAlexaHeader().getName());
        Log.i(str, outline107.toString());
        this.alexaMobileFrameworkApis.getEventSender().send(alexaEvent, z);
    }
}
