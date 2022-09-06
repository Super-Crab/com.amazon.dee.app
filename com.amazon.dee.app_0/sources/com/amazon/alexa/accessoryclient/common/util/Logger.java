package com.amazon.alexa.accessoryclient.common.util;

import com.amazon.alexa.accessory.internal.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/util/Logger;", "", "()V", "d", "", "stringFun", "Lkotlin/Function0;", "", "e", "v", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Logger {
    public static final Logger INSTANCE = new Logger();

    private Logger() {
    }

    public final void d(@NotNull Function0<String> stringFun) {
        Intrinsics.checkParameterIsNotNull(stringFun, "stringFun");
        if (com.amazon.alexa.accessory.internal.util.Logger.shouldLog(Logger.Level.DEBUG)) {
            com.amazon.alexa.accessory.internal.util.Logger.d(stringFun.mo12560invoke());
        }
    }

    public final void e(@NotNull Function0<String> stringFun) {
        Intrinsics.checkParameterIsNotNull(stringFun, "stringFun");
        com.amazon.alexa.accessory.internal.util.Logger.e(stringFun.mo12560invoke());
    }

    public final void v(@NotNull Function0<String> stringFun) {
        Intrinsics.checkParameterIsNotNull(stringFun, "stringFun");
        if (com.amazon.alexa.accessory.internal.util.Logger.shouldLog(Logger.Level.VERBOSE)) {
            com.amazon.alexa.accessory.internal.util.Logger.v(stringFun.mo12560invoke());
        }
    }
}
