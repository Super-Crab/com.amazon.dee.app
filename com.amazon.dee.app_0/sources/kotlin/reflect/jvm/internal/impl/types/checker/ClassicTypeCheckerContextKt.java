package kotlin.reflect.jvm.internal.impl.types.checker;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Reflection;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClassicTypeCheckerContext.kt */
/* loaded from: classes4.dex */
public final class ClassicTypeCheckerContextKt {
    public static final /* synthetic */ String access$errorMessage(Object obj) {
        return errorMessage(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String errorMessage(@NotNull Object obj) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassicTypeCheckerContext couldn't handle ");
        outline107.append(Reflection.getOrCreateKotlinClass(obj.getClass()));
        outline107.append(Chars.SPACE);
        outline107.append(obj);
        return outline107.toString();
    }
}
