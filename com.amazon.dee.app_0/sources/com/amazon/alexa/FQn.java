package com.amazon.alexa;

import androidx.annotation.VisibleForTesting;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TextAvailabilityProvider.java */
@Singleton
/* loaded from: classes.dex */
public class FQn {
    @VisibleForTesting
    public static final Locale zZm = new Locale("en", "US");
    public final gSO BIo;
    public final MBE zQM;

    @Inject
    public FQn(gSO gso, MBE mbe) {
        this.BIo = gso;
        this.zQM = mbe;
    }
}
