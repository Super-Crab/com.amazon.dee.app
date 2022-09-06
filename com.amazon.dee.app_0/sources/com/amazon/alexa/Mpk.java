package com.amazon.alexa;

import com.amazon.alexa.YJe;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: VisualChannelStatePersister.java */
@Singleton
/* loaded from: classes.dex */
public class Mpk extends YJe<JiQ, aNh> {

    /* compiled from: VisualChannelStatePersister.java */
    /* loaded from: classes.dex */
    private static class zZm implements YJe.zyO<JiQ> {
        public /* synthetic */ zZm(UPZ upz) {
        }

        @Override // com.amazon.alexa.YJe.zyO
        public JiQ create(String str) {
            return JiQ.valueOf(str);
        }
    }

    @Inject
    public Mpk(@Named("channels_data_loader") Lazy<uTP> lazy) {
        super(aNh.class, new zZm(null), lazy);
    }
}
