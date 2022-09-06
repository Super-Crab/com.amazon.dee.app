package com.amazon.alexa;

import android.content.Context;
import android.content.pm.PackageManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ExternalCapabilityAgentManifestExtractor.java */
@Singleton
/* loaded from: classes.dex */
public class rOO {
    public static final String zZm = "rOO";
    public final PackageManager BIo;
    public final AlexaClientEventBus jiA;
    public final Gson zQM;
    public final Context zyO;

    @Inject
    public rOO(PackageManager packageManager, Gson gson, Context context, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = packageManager;
        this.zQM = gson;
        this.zyO = context;
        this.jiA = alexaClientEventBus;
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0201 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.Set<com.amazon.alexa.KHc> zZm() {
        /*
            Method dump skipped, instructions count: 559
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.rOO.zZm():java.util.Set");
    }
}
