package com.amazon.tarazed.utility;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BrowserPresenceDetectorToResumeSuspendedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0082@"}, d2 = {"getCachedCredentials", "", "context", "Landroid/content/Context;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession", f = "BrowserPresenceDetectorToResumeSuspendedSession.kt", i = {0, 0}, l = {96}, m = "getCachedCredentials", n = {"this", "context"}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BrowserPresenceDetectorToResumeSuspendedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrowserPresenceDetectorToResumeSuspendedSession$getCachedCredentials$1(BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetectorToResumeSuspendedSession, Continuation continuation) {
        super(continuation);
        this.this$0 = browserPresenceDetectorToResumeSuspendedSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getCachedCredentials(null, this);
    }
}
