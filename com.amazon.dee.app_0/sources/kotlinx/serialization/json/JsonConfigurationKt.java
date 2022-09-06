package kotlinx.serialization.json;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.internal.LowPriorityInOverloadResolution;
/* compiled from: JsonConfiguration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¨\u0006\u0005"}, d2 = {"JsonConfiguration", "", "strictMode", "", "unquoted", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonConfigurationKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "This named parameters were deprecated.Intstead of 'unquoted' please use 'unquotedPrint'. 'strictMode' was splitted into'ignoreUnknownKeys', 'isLenient' and 'serializeSpecialFloatingPointValues'")
    @LowPriorityInOverloadResolution
    public static final void JsonConfiguration(boolean z, boolean z2) {
        throw new IllegalStateException("Should not be called".toString());
    }

    public static /* synthetic */ void JsonConfiguration$default(boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        JsonConfiguration(z, z2);
    }
}
