package io.ktor.util;

import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
/* compiled from: Logging.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"error", "", "Lorg/slf4j/Logger;", ADMRegistrationConstants.CALL_EXCEPTION, "", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LoggingKt {
    public static final void error(@NotNull Logger receiver$0, @NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        String message = exception.getMessage();
        if (message == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception of type ");
            outline107.append(exception.getClass());
            message = outline107.toString();
        }
        receiver$0.error(message, exception);
    }
}
