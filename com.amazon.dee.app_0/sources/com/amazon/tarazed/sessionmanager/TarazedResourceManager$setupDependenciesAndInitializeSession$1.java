package com.amazon.tarazed.sessionmanager;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazonaws.services.s3.internal.Constants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"setupDependenciesAndInitializeSession", "", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager", f = "TarazedResourceManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {351}, m = "setupDependenciesAndInitializeSession", n = {"this", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "sessionClient", EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH, "screenHeight", "mediaProjectionPermissionTimeoutMs", "webRTCManagerHelper", "sessionInfoSender", Constants.REQUESTER_PAYS}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "J$0", "L$3", "L$4", "L$5"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$setupDependenciesAndInitializeSession$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$setupDependenciesAndInitializeSession$1(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedResourceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.setupDependenciesAndInitializeSession(null, this);
    }
}
