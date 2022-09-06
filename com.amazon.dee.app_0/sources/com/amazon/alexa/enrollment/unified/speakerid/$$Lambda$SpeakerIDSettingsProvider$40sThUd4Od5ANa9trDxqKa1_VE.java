package com.amazon.alexa.enrollment.unified.speakerid;

import com.amazon.alexa.enrollment.unified.model.GetSpeakerByPersonIdResponse;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.enrollment.unified.speakerid.-$$Lambda$SpeakerIDSettingsProvider$40sT-hUd4Od5ANa9trDxqKa1_VE  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$SpeakerIDSettingsProvider$40sThUd4Od5ANa9trDxqKa1_VE implements Function {
    public static final /* synthetic */ $$Lambda$SpeakerIDSettingsProvider$40sThUd4Od5ANa9trDxqKa1_VE INSTANCE = new $$Lambda$SpeakerIDSettingsProvider$40sThUd4Od5ANa9trDxqKa1_VE();

    private /* synthetic */ $$Lambda$SpeakerIDSettingsProvider$40sThUd4Od5ANa9trDxqKa1_VE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return SpeakerIDSettingsProvider.lambda$isSpeakerEnrolled$3((GetSpeakerByPersonIdResponse) obj);
    }
}
