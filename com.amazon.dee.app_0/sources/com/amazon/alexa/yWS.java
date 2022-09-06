package com.amazon.alexa;

import com.amazon.alexa.YJe;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ExternalMediaPlayerRegistrationPersister.java */
@Singleton
/* loaded from: classes.dex */
public class yWS extends YJe<FHI, yfS> {

    /* compiled from: ExternalMediaPlayerRegistrationPersister.java */
    /* loaded from: classes.dex */
    private static class zZm implements YJe.zyO<FHI> {
        public /* synthetic */ zZm(asO aso) {
        }

        @Override // com.amazon.alexa.YJe.zyO
        public FHI create(String str) {
            return FHI.zZm(str);
        }
    }

    @Inject
    public yWS(@Named("player_registration_loader") Lazy<uTP> lazy) {
        super(yfS.class, new zZm(null), lazy);
    }
}
