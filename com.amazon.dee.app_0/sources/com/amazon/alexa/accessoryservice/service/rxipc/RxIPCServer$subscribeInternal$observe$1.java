package com.amazon.alexa.accessoryservice.service.rxipc;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.accessoryclient.common.query.Query;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxIPCServer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "response", "apply", "(Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;)Landroid/os/Bundle;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class RxIPCServer$subscribeInternal$observe$1<T, R> implements Function<T, R> {
    public static final RxIPCServer$subscribeInternal$observe$1 INSTANCE = new RxIPCServer$subscribeInternal$observe$1();

    RxIPCServer$subscribeInternal$observe$1() {
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Landroid/os/Bundle; */
    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final Bundle mo10358apply(@NotNull Query.Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        return response.getBundleTransformer().toBundle(response);
    }
}
