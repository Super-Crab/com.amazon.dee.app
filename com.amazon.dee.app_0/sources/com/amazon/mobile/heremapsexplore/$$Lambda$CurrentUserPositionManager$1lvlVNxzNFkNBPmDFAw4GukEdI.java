package com.amazon.mobile.heremapsexplore;

import android.util.Log;
import com.amazon.mobile.heremapsexplore.Constants.Constants;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.mobile.heremapsexplore.-$$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFA-w4GukEdI  reason: invalid class name */
/* loaded from: classes13.dex */
public final /* synthetic */ class $$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFAw4GukEdI implements Consumer {
    public static final /* synthetic */ $$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFAw4GukEdI INSTANCE = new $$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFAw4GukEdI();

    private /* synthetic */ $$Lambda$CurrentUserPositionManager$1lvlVNxzNFkNBPmDFAw4GukEdI() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Log.e(Constants.LOG_TAG, "Failed to get most recent recent user location for update, using previous values instead");
    }
}
