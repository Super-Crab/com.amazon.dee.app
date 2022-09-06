package io.ktor.client.utils;

import io.ktor.util.KtorExperimentalAPI;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpCacheControl.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0007J\b\u0010\u0011\u001a\u00020\u0004H\u0007J\b\u0010\u0012\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\u0004H\u0007J\b\u0010\u0014\u001a\u00020\u0004H\u0007J\b\u0010\u0015\u001a\u00020\u0004H\u0007J\b\u0010\u0016\u001a\u00020\u0004H\u0007J\b\u0010\u0017\u001a\u00020\u0004H\u0007J\b\u0010\u0018\u001a\u00020\u0004H\u0007J\b\u0010\u0019\u001a\u00020\u0004H\u0007J\b\u0010\u001a\u001a\u00020\u0004H\u0007J\b\u0010\u001b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lio/ktor/client/utils/CacheControl;", "", "()V", "MAX_AGE", "", "MAX_STALE", "MIN_FRESH", "MUST_REVALIDATE", "NO_CACHE", "NO_STORE", "NO_TRANSFORM", "ONLY_IF_CACHED", "PRIVATE", "PROXY_REVALIDATE", "PUBLIC", "S_MAX_AGE", "getMAX_AGE", "getMAX_STALE", "getMIN_FRESH", "getMUST_REVALIDATE", "getNO_CACHE", "getNO_STORE", "getNO_TRANSFORM", "getONLY_IF_CACHED", "getPRIVATE", "getPROXY_REVALIDATE", "getPUBLIC", "getS_MAX_AGE", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CacheControl {
    public static final CacheControl INSTANCE = new CacheControl();
    @NotNull
    public static final String MAX_AGE = "max-age";
    @NotNull
    public static final String MAX_STALE = "max-stale";
    @NotNull
    public static final String MIN_FRESH = "min-fresh";
    @NotNull
    public static final String MUST_REVALIDATE = "must-revalidate";
    @NotNull
    public static final String NO_CACHE = "no-cache";
    @NotNull
    public static final String NO_STORE = "no-store";
    @NotNull
    public static final String NO_TRANSFORM = "no-transform";
    @NotNull
    public static final String ONLY_IF_CACHED = "only-if-cached";
    @NotNull
    public static final String PRIVATE = "private";
    @NotNull
    public static final String PROXY_REVALIDATE = "proxy-revalidate";
    @NotNull
    public static final String PUBLIC = "public";
    @NotNull
    public static final String S_MAX_AGE = "s-maxage";

    private CacheControl() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getMAX_AGE() {
        return MAX_AGE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getMAX_STALE() {
        return MAX_STALE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getMIN_FRESH() {
        return MIN_FRESH;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getMUST_REVALIDATE() {
        return MUST_REVALIDATE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getNO_CACHE() {
        return NO_CACHE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getNO_STORE() {
        return NO_STORE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getNO_TRANSFORM() {
        return NO_TRANSFORM;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getONLY_IF_CACHED() {
        return ONLY_IF_CACHED;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getPRIVATE() {
        return PRIVATE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getPROXY_REVALIDATE() {
        return PROXY_REVALIDATE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getPUBLIC() {
        return PUBLIC;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Compatibility")
    @NotNull
    public final /* synthetic */ String getS_MAX_AGE() {
        return S_MAX_AGE;
    }
}
