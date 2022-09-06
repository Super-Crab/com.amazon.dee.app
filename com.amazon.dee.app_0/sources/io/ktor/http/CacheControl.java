package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CacheControl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lio/ktor/http/CacheControl;", "", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "getVisibility", "()Lio/ktor/http/CacheControl$Visibility;", "MaxAge", "NoCache", "NoStore", "Visibility", "Lio/ktor/http/CacheControl$NoCache;", "Lio/ktor/http/CacheControl$NoStore;", "Lio/ktor/http/CacheControl$MaxAge;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class CacheControl {
    @Nullable
    private final Visibility visibility;

    /* compiled from: CacheControl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0015"}, d2 = {"Lio/ktor/http/CacheControl$MaxAge;", "Lio/ktor/http/CacheControl;", "maxAgeSeconds", "", "proxyMaxAgeSeconds", "mustRevalidate", "", "proxyRevalidate", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(ILjava/lang/Integer;ZZLio/ktor/http/CacheControl$Visibility;)V", "getMaxAgeSeconds", "()I", "getMustRevalidate", "()Z", "getProxyMaxAgeSeconds", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProxyRevalidate", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class MaxAge extends CacheControl {
        private final int maxAgeSeconds;
        private final boolean mustRevalidate;
        @Nullable
        private final Integer proxyMaxAgeSeconds;
        private final boolean proxyRevalidate;

        public /* synthetic */ MaxAge(int i, Integer num, boolean z, boolean z2, Visibility visibility, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2, (i2 & 16) != 0 ? null : visibility);
        }

        public final int getMaxAgeSeconds() {
            return this.maxAgeSeconds;
        }

        public final boolean getMustRevalidate() {
            return this.mustRevalidate;
        }

        @Nullable
        public final Integer getProxyMaxAgeSeconds() {
            return this.proxyMaxAgeSeconds;
        }

        public final boolean getProxyRevalidate() {
            return this.proxyRevalidate;
        }

        @NotNull
        public String toString() {
            String joinToString$default;
            ArrayList arrayList = new ArrayList(5);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("max-age=");
            outline107.append(this.maxAgeSeconds);
            arrayList.add(outline107.toString());
            if (this.proxyMaxAgeSeconds != null) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("s-maxage=");
                outline1072.append(this.proxyMaxAgeSeconds);
                arrayList.add(outline1072.toString());
            }
            if (this.mustRevalidate) {
                arrayList.add(io.ktor.client.utils.CacheControl.MUST_REVALIDATE);
            }
            if (this.proxyRevalidate) {
                arrayList.add(io.ktor.client.utils.CacheControl.PROXY_REVALIDATE);
            }
            if (getVisibility() != null) {
                String name = getVisibility().name();
                if (name == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String lowerCase = name.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                arrayList.add(lowerCase);
            }
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null);
            return joinToString$default;
        }

        public MaxAge(int i, @Nullable Integer num, boolean z, boolean z2, @Nullable Visibility visibility) {
            super(visibility, null);
            this.maxAgeSeconds = i;
            this.proxyMaxAgeSeconds = num;
            this.mustRevalidate = z;
            this.proxyRevalidate = z2;
        }
    }

    /* compiled from: CacheControl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/http/CacheControl$NoCache;", "Lio/ktor/http/CacheControl;", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class NoCache extends CacheControl {
        public NoCache(@Nullable Visibility visibility) {
            super(visibility, null);
        }

        @NotNull
        public String toString() {
            if (getVisibility() == null) {
                return io.ktor.client.utils.CacheControl.NO_CACHE;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("no-cache, ");
            String name = getVisibility().name();
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            outline107.append(lowerCase);
            return outline107.toString();
        }
    }

    /* compiled from: CacheControl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lio/ktor/http/CacheControl$NoStore;", "Lio/ktor/http/CacheControl;", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "toString", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class NoStore extends CacheControl {
        public NoStore(@Nullable Visibility visibility) {
            super(visibility, null);
        }

        @NotNull
        public String toString() {
            if (getVisibility() == null) {
                return io.ktor.client.utils.CacheControl.NO_STORE;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("no-store, ");
            String name = getVisibility().name();
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            outline107.append(lowerCase);
            return outline107.toString();
        }
    }

    /* compiled from: CacheControl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/http/CacheControl$Visibility;", "", "(Ljava/lang/String;I)V", "Public", "Private", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public enum Visibility {
        Public,
        Private
    }

    private CacheControl(Visibility visibility) {
        this.visibility = visibility;
    }

    @Nullable
    public final Visibility getVisibility() {
        return this.visibility;
    }

    public /* synthetic */ CacheControl(Visibility visibility, DefaultConstructorMarker defaultConstructorMarker) {
        this(visibility);
    }
}
