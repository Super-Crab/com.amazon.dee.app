package kotlin.text.jdk8;

import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RegexExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0002¨\u0006\u0005"}, d2 = {MetricsConstants.Method.CACHE_GET, "Lkotlin/text/MatchGroup;", "Lkotlin/text/MatchGroupCollection;", "name", "", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 1, 16}, pn = "kotlin.text")
@JvmName(name = "RegexExtensionsJDK8Kt")
/* loaded from: classes4.dex */
public final class RegexExtensionsJDK8Kt {
    @SinceKotlin(version = "1.2")
    @Nullable
    public static final MatchGroup get(@NotNull MatchGroupCollection get, @NotNull String name) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (!(get instanceof MatchNamedGroupCollection)) {
            get = null;
        }
        MatchNamedGroupCollection matchNamedGroupCollection = (MatchNamedGroupCollection) get;
        if (matchNamedGroupCollection != null) {
            return matchNamedGroupCollection.get(name);
        }
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }
}
