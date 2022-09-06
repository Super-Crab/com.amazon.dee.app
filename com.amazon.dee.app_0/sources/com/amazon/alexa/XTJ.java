package com.amazon.alexa;

import com.amazon.alexa.YJe;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: PlayerInFocusPersister.java */
@Singleton
/* loaded from: classes.dex */
public class XTJ extends YJe<XjE, vQe> {

    /* compiled from: PlayerInFocusPersister.java */
    /* loaded from: classes.dex */
    private static class zZm implements YJe.zyO<XjE> {
        public /* synthetic */ zZm(JWc jWc) {
        }

        @Override // com.amazon.alexa.YJe.zyO
        public XjE create(String str) {
            return XjE.valueOf(str);
        }
    }

    @Inject
    public XTJ(@Named("player_in_focus_loader") Lazy<uTP> lazy) {
        super(vQe.class, new zZm(null), lazy);
    }
}
