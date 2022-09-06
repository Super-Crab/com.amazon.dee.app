package com.google.android.exoplayer2.text.cea;

import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.text.cea.-$$Lambda$Cea708Decoder$Cea708CueInfo$8f1il_zEcouq-_x8remACEaVBv8  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$Cea708Decoder$Cea708CueInfo$8f1il_zEcouq_x8remACEaVBv8 implements Comparator {
    public static final /* synthetic */ $$Lambda$Cea708Decoder$Cea708CueInfo$8f1il_zEcouq_x8remACEaVBv8 INSTANCE = new $$Lambda$Cea708Decoder$Cea708CueInfo$8f1il_zEcouq_x8remACEaVBv8();

    private /* synthetic */ $$Lambda$Cea708Decoder$Cea708CueInfo$8f1il_zEcouq_x8remACEaVBv8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((Cea708Decoder.Cea708CueInfo) obj2).priority, ((Cea708Decoder.Cea708CueInfo) obj).priority);
        return compare;
    }
}
