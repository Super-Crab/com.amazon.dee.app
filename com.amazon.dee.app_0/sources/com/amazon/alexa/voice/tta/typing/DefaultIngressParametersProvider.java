package com.amazon.alexa.voice.tta.typing;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.alexa.voice.tta.metrics.EventClock;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.amazon.alexa.voice.tta.typing.IngressParameters;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class DefaultIngressParametersProvider implements IngressParameters.Provider {
    private static final String TAG = "DefaultIngressParametersProvider";
    private static final long UNKNOWN_TIME = Long.MIN_VALUE;
    private final EventClock eventClock;
    private IngressParameters ingressParameters = null;

    public DefaultIngressParametersProvider(@NonNull EventClock eventClock) {
        this.eventClock = eventClock;
    }

    @Override // com.amazon.alexa.voice.tta.typing.IngressParameters.Provider
    @NonNull
    public IngressParameters getIngressParameters() {
        IngressParameters ingressParameters = this.ingressParameters;
        if (ingressParameters != null) {
            return ingressParameters;
        }
        throw new IllegalStateException("IngressParameters.Provider.update() must be called before getIngressParameters()");
    }

    @Override // com.amazon.alexa.voice.tta.typing.IngressParameters.Provider
    public void update(@Nullable Intent intent) {
        long longExtra;
        String str;
        EventTime fromSystemTimeMilliseconds;
        String str2 = "update: " + intent;
        if (intent == null) {
            str = null;
            longExtra = Long.MIN_VALUE;
        } else {
            String stringExtra = intent.getStringExtra("referer");
            longExtra = intent.getLongExtra(Constants.IntentParameters.START_TIMESTAMP, Long.MIN_VALUE);
            str = stringExtra;
        }
        if (longExtra == Long.MIN_VALUE) {
            fromSystemTimeMilliseconds = this.eventClock.now();
        } else {
            fromSystemTimeMilliseconds = this.eventClock.fromSystemTimeMilliseconds(longExtra);
        }
        if (str == null) {
            str = "UNKNOWN";
        }
        this.ingressParameters = IngressParameters.create(str, fromSystemTimeMilliseconds);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updated ingress parameters: ");
        outline107.append(this.ingressParameters);
        outline107.toString();
    }
}
