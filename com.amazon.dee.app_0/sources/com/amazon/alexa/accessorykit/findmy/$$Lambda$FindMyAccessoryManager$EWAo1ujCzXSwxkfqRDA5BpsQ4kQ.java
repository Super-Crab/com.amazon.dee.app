package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsResponse;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ implements Consumer {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ INSTANCE = new $$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        FindMyAccessoryManager.lambda$reportLocationInformation$2((ReportLocationsResponse) obj);
    }
}
