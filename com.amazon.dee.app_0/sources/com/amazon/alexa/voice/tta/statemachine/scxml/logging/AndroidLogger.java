package com.amazon.alexa.voice.tta.statemachine.scxml.logging;

import android.util.Log;
import com.amazon.scxml.logging.Logger;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AndroidLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/logging/AndroidLogger;", "Lcom/amazon/scxml/logging/Logger;", "()V", "d", "", "tag", "", "message", "e", ContextChain.TAG_INFRA, "v", "w", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class AndroidLogger implements Logger {
    @Override // com.amazon.scxml.logging.Logger
    public void d(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.scxml.logging.Logger
    public void e(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.e(str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void i(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.i(str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void v(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.scxml.logging.Logger
    public void w(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.w(str, message);
    }
}
