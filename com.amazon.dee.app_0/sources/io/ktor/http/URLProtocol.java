package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.CharsetKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: URLProtocol.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lio/ktor/http/URLProtocol;", "", "name", "", "defaultPort", "", "(Ljava/lang/String;I)V", "getDefaultPort", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class URLProtocol {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final URLProtocol HTTP = new URLProtocol("http", 80);
    @NotNull
    private static final URLProtocol HTTPS = new URLProtocol("https", 443);
    @NotNull
    private static final URLProtocol WS = new URLProtocol("ws", 80);
    @NotNull
    private static final URLProtocol WSS = new URLProtocol("wss", 443);
    @NotNull
    private static final Map<String, URLProtocol> byName;
    private final int defaultPort;
    @NotNull
    private final String name;

    /* compiled from: URLProtocol.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lio/ktor/http/URLProtocol$Companion;", "", "()V", "HTTP", "Lio/ktor/http/URLProtocol;", "getHTTP", "()Lio/ktor/http/URLProtocol;", "HTTPS", "getHTTPS", "WS", "getWS", "WSS", "getWSS", "byName", "", "", "getByName", "()Ljava/util/Map;", "createOrDefault", "name", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final URLProtocol createOrDefault(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            URLProtocol uRLProtocol = URLProtocol.Companion.getByName().get(lowerCase);
            return uRLProtocol != null ? uRLProtocol : new URLProtocol(lowerCase, 0);
        }

        @NotNull
        public final Map<String, URLProtocol> getByName() {
            return URLProtocol.byName;
        }

        @NotNull
        public final URLProtocol getHTTP() {
            return URLProtocol.HTTP;
        }

        @NotNull
        public final URLProtocol getHTTPS() {
            return URLProtocol.HTTPS;
        }

        @NotNull
        public final URLProtocol getWS() {
            return URLProtocol.WS;
        }

        @NotNull
        public final URLProtocol getWSS() {
            return URLProtocol.WSS;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List listOf;
        int collectionSizeOrDefault;
        int mapCapacity;
        int coerceAtLeast;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new URLProtocol[]{HTTP, HTTPS, WS, WSS});
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10);
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(collectionSizeOrDefault);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (Object obj : listOf) {
            linkedHashMap.put(((URLProtocol) obj).name, obj);
        }
        byName = linkedHashMap;
    }

    public URLProtocol(@NotNull String name, int i) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.defaultPort = i;
        String str = this.name;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= str.length()) {
                z = true;
                break;
            } else if (!CharsetKt.isLowerCase(str.charAt(i2))) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException("All characters should be lower case".toString());
    }

    @NotNull
    public static /* synthetic */ URLProtocol copy$default(URLProtocol uRLProtocol, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = uRLProtocol.name;
        }
        if ((i2 & 2) != 0) {
            i = uRLProtocol.defaultPort;
        }
        return uRLProtocol.copy(str, i);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.defaultPort;
    }

    @NotNull
    public final URLProtocol copy(@NotNull String name, int i) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new URLProtocol(name, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof URLProtocol) {
                URLProtocol uRLProtocol = (URLProtocol) obj;
                if (Intrinsics.areEqual(this.name, uRLProtocol.name)) {
                    if (this.defaultPort == uRLProtocol.defaultPort) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int getDefaultPort() {
        return this.defaultPort;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        String str = this.name;
        return ((str != null ? str.hashCode() : 0) * 31) + this.defaultPort;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URLProtocol(name=");
        outline107.append(this.name);
        outline107.append(", defaultPort=");
        return GeneratedOutlineSupport1.outline86(outline107, this.defaultPort, ")");
    }
}
