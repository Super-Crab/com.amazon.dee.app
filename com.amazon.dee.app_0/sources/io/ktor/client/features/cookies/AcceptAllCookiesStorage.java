package io.ktor.client.features.cookies;

import com.dee.app.metrics.MetricsConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.ktor.http.Cookie;
import io.ktor.http.Url;
import io.ktor.util.Lock;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AcceptAllCookiesStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/client/features/cookies/AcceptAllCookiesStorage;", "Lio/ktor/client/features/cookies/CookiesStorage;", "()V", TtmlNode.RUBY_CONTAINER, "", "Lio/ktor/http/Cookie;", "mutex", "Lio/ktor/util/Lock;", "oldestCookie", "Lkotlinx/atomicfu/AtomicLong;", "addCookie", "", "requestUrl", "Lio/ktor/http/Url;", "cookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanup", "timestamp", "", MetricsConstants.Method.CACHE_GET, "", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class AcceptAllCookiesStorage implements CookiesStorage {
    private static final AtomicLongFieldUpdater oldestCookie$FU = AtomicLongFieldUpdater.newUpdater(AcceptAllCookiesStorage.class, "oldestCookie");
    private final List<Cookie> container = new ArrayList();
    private volatile long oldestCookie = 0;
    private final Lock mutex = new Lock();

    private final void cleanup(long j) {
        CollectionsKt__MutableCollectionsKt.removeAll((List) this.container, (Function1) new AcceptAllCookiesStorage$cleanup$1(j));
        long j2 = Long.MAX_VALUE;
        for (Cookie cookie : this.container) {
            GMTDate expires = cookie.getExpires();
            if (expires != null) {
                j2 = Math.min(j2, expires.getTimestamp());
            }
        }
        this.oldestCookie = j2;
    }

    @Override // io.ktor.client.features.cookies.CookiesStorage
    @Nullable
    public Object addCookie(@NotNull Url url, @NotNull Cookie cookie, @NotNull Continuation<? super Unit> continuation) {
        boolean isBlank;
        Lock lock = this.mutex;
        try {
            lock.lock();
            isBlank = StringsKt__StringsJVMKt.isBlank(cookie.getName());
            if (!isBlank) {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.container, (Function1) new AcceptAllCookiesStorage$addCookie$$inlined$use$lambda$1(this, cookie, url));
                this.container.add(CookiesStorageKt.fillDefaults(cookie, url));
            }
            return Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    @Override // io.ktor.client.features.cookies.CookiesStorage
    @Nullable
    public Object get(@NotNull Url url, @NotNull Continuation<? super List<Cookie>> continuation) {
        Lock lock = this.mutex;
        try {
            lock.lock();
            GMTDate GMTDate$default = DateJvmKt.GMTDate$default(null, 1, null);
            if (GMTDate$default.getTimestamp() < this.oldestCookie) {
                cleanup(GMTDate$default.getTimestamp());
            }
            List<Cookie> list = this.container;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (Boxing.boxBoolean(CookiesStorageKt.matches((Cookie) obj, url)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        } finally {
            lock.unlock();
        }
    }
}
