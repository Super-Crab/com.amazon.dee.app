package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg INSTANCE = new $$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg();

    private /* synthetic */ $$Lambda$AccessoryMobilyticsUserProvider$H_a9kufOKnzaVLN5jO8MkPTixOg() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s CRITICAL: Unexpected error in activate()", (Throwable) obj, AccessoryMobilyticsUserProvider.TAG);
    }
}
