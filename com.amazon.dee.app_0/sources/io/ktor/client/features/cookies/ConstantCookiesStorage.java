package io.ktor.client.features.cookies;

import androidx.core.app.FrameMetricsAggregator;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.dee.app.metrics.MetricsConstants;
import io.ktor.http.Cookie;
import io.ktor.http.URLBuilder;
import io.ktor.http.Url;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConstantCookiesStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005J!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/client/features/cookies/ConstantCookiesStorage;", "Lio/ktor/client/features/cookies/CookiesStorage;", AccountManagerConstants.GetCookiesParams.COOKIES, "", "Lio/ktor/http/Cookie;", "([Lio/ktor/http/Cookie;)V", "storage", "", "addCookie", "", "requestUrl", "Lio/ktor/http/Url;", "cookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", MetricsConstants.Method.CACHE_GET, "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ConstantCookiesStorage implements CookiesStorage {
    private final List<Cookie> storage;

    public ConstantCookiesStorage(@NotNull Cookie... cookies) {
        List<Cookie> list;
        Intrinsics.checkParameterIsNotNull(cookies, "cookies");
        ArrayList arrayList = new ArrayList(cookies.length);
        for (Cookie cookie : cookies) {
            arrayList.add(CookiesStorageKt.fillDefaults(cookie, new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null).build()));
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        this.storage = list;
    }

    @Override // io.ktor.client.features.cookies.CookiesStorage
    @Nullable
    public Object addCookie(@NotNull Url url, @NotNull Cookie cookie, @NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // io.ktor.client.features.cookies.CookiesStorage
    @Nullable
    public Object get(@NotNull Url url, @NotNull Continuation<? super List<Cookie>> continuation) {
        List<Cookie> list = this.storage;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (Boxing.boxBoolean(CookiesStorageKt.matches((Cookie) obj, url)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
