package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw implements Consumer {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw INSTANCE = new $$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed reporting accessory LocationInformation", (Throwable) obj);
    }
}
