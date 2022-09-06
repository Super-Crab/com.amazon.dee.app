package com.amazon.alexa.accessorykit.interprocess.feature;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.feature.-$$Lambda$InterprocessFeatureChecker$04S-yfFg9AqwX2dLWhY9BXP7HyQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterprocessFeatureChecker$04SyfFg9AqwX2dLWhY9BXP7HyQ implements Consumer {
    public static final /* synthetic */ $$Lambda$InterprocessFeatureChecker$04SyfFg9AqwX2dLWhY9BXP7HyQ INSTANCE = new $$Lambda$InterprocessFeatureChecker$04SyfFg9AqwX2dLWhY9BXP7HyQ();

    private /* synthetic */ $$Lambda$InterprocessFeatureChecker$04SyfFg9AqwX2dLWhY9BXP7HyQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Error loading FeatureFlag", (Throwable) obj);
    }
}
