package okhttp3.internal.platform.android;

import android.util.Log;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: util.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"MAX_LOG_LENGTH", "", "androidLog", "", ModelTransformer.KEY_BATTERY_LEVEL, "message", "", "t", "", "okhttp"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UtilKt {
    private static final int MAX_LOG_LENGTH = 4000;

    public static final void androidLog(int i, @NotNull String message, @Nullable Throwable th) {
        int indexOf$default;
        int min;
        Intrinsics.checkParameterIsNotNull(message, "message");
        int i2 = 5;
        if (i != 5) {
            i2 = 3;
        }
        if (th != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(message, "\n");
            outline113.append(Log.getStackTraceString(th));
            message = outline113.toString();
        }
        int i3 = 0;
        int length = message.length();
        while (i3 < length) {
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) message, '\n', i3, false, 4, (Object) null);
            if (indexOf$default == -1) {
                indexOf$default = length;
            }
            while (true) {
                min = Math.min(indexOf$default, i3 + 4000);
                String substring = message.substring(i3, min);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Log.println(i2, "OkHttp", substring);
                if (min >= indexOf$default) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }
}
