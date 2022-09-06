package com.amazon.alexa.voice.ui.onedesign.standard;

import android.text.Html;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardCardFactory;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.standard.-$$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0 implements StandardCardFactory.Formatter {
    public static final /* synthetic */ $$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0 INSTANCE = new $$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0();

    private /* synthetic */ $$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0() {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardCardFactory.Formatter
    public final CharSequence fromHtml(String str) {
        return Html.fromHtml(str);
    }
}
