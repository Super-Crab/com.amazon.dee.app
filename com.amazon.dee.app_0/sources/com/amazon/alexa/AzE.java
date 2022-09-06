package com.amazon.alexa;

import com.amazon.alexa.YJe;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ChannelStatePersister.java */
@Singleton
/* loaded from: classes.dex */
public class AzE extends YJe<aVo, aNh> {

    /* compiled from: ChannelStatePersister.java */
    /* loaded from: classes.dex */
    private static class zZm implements YJe.zyO<aVo> {
        public /* synthetic */ zZm(dUd dud) {
        }

        @Override // com.amazon.alexa.YJe.zyO
        public aVo create(String str) {
            return aVo.valueOf(str);
        }
    }

    @Inject
    public AzE(@Named("channels_data_loader") Lazy<uTP> lazy) {
        super(aNh.class, new zZm(null), lazy);
    }
}
