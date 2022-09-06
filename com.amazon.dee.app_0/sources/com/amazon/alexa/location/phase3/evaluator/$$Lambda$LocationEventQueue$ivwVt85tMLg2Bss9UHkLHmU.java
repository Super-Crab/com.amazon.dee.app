package com.amazon.alexa.location.phase3.evaluator;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.phase3.evaluator.-$$Lambda$LocationEventQueue$ivw-Vt85tMLg-2B-s-s9UHkLHmU  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$LocationEventQueue$ivwVt85tMLg2Bss9UHkLHmU implements Comparator {
    public static final /* synthetic */ $$Lambda$LocationEventQueue$ivwVt85tMLg2Bss9UHkLHmU INSTANCE = new $$Lambda$LocationEventQueue$ivwVt85tMLg2Bss9UHkLHmU();

    private /* synthetic */ $$Lambda$LocationEventQueue$ivwVt85tMLg2Bss9UHkLHmU() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int signum;
        signum = Long.signum(((LocationEvent) obj).timestamp - ((LocationEvent) obj2).timestamp);
        return signum;
    }
}
