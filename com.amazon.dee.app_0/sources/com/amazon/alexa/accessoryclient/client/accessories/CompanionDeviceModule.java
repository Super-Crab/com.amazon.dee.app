package com.amazon.alexa.accessoryclient.client.accessories;

import androidx.annotation.RequiresApi;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CompanionDeviceModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/CompanionDeviceModule;", "", "isCompanionDevice", "Lio/reactivex/rxjava3/core/Single;", "", "address", "", "queryNewCompanionDevices", "Lio/reactivex/rxjava3/core/Observable;", "queryRemovedCompanionDevices", "removeCompanionDevice", "Lio/reactivex/rxjava3/core/Completable;", "requestCompanionDevice", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface CompanionDeviceModule {
    @RequiresApi(api = 26)
    @NotNull
    Single<Boolean> isCompanionDevice(@NotNull String str);

    @RequiresApi(api = 26)
    @NotNull
    Observable<String> queryNewCompanionDevices();

    @RequiresApi(api = 26)
    @NotNull
    Observable<String> queryRemovedCompanionDevices();

    @RequiresApi(api = 26)
    @NotNull
    Completable removeCompanionDevice(@NotNull String str);

    @RequiresApi(api = 26)
    @NotNull
    Single<Boolean> requestCompanionDevice(@NotNull String str);
}
