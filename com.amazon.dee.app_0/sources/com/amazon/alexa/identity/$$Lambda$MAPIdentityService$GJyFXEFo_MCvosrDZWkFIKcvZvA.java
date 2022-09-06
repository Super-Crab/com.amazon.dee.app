package com.amazon.alexa.identity;

import android.util.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA implements Consumer {
    public static final /* synthetic */ $$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA INSTANCE = new $$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA();

    private /* synthetic */ $$Lambda$MAPIdentityService$GJyFXEFo_MCvosrDZWkFIKcvZvA() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        UserIdentity userIdentity = (UserIdentity) obj;
        Log.i(MAPIdentityService.TAG, "IdentityV2 : User updated");
    }
}
