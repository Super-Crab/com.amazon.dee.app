package com.amazon.alexa.handsfree.audio;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.leaderselection.LeaderSelector;
/* loaded from: classes8.dex */
public class AlexaServicesConnectionFactory {
    @NonNull
    public AlexaServicesConnection getAlexaServicesConnection(@NonNull Context context) {
        LeaderSelector.enable(context, true);
        return new AlexaServicesConnection(context);
    }
}
