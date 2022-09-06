package com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.Eqg;
import com.amazon.alexa.client.core.messages.Payload;
import com.amazon.alexa.vQe;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class AlexaMediaPayload implements Payload {
    public static AlexaMediaPayload create(vQe vqe) {
        return new Eqg(vqe);
    }

    @Nullable
    public abstract vQe getPlayerId();
}
