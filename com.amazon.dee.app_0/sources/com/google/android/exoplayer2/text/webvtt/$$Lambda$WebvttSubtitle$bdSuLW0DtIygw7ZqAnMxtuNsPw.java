package com.google.android.exoplayer2.text.webvtt;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.text.webvtt.-$$Lambda$WebvttSubtitle$bdSuLW0DtIygw7Z-qAnMxtuNsPw  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$WebvttSubtitle$bdSuLW0DtIygw7ZqAnMxtuNsPw implements Comparator {
    public static final /* synthetic */ $$Lambda$WebvttSubtitle$bdSuLW0DtIygw7ZqAnMxtuNsPw INSTANCE = new $$Lambda$WebvttSubtitle$bdSuLW0DtIygw7ZqAnMxtuNsPw();

    private /* synthetic */ $$Lambda$WebvttSubtitle$bdSuLW0DtIygw7ZqAnMxtuNsPw() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Long.compare(((WebvttCueInfo) obj).startTimeUs, ((WebvttCueInfo) obj2).startTimeUs);
        return compare;
    }
}
