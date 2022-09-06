package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: RegistrationSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH&¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/RegistrationSupplier;", "", "deregister", "Lio/reactivex/rxjava3/core/Completable;", "identifier", "", "getDeviceRegistration", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/registration/DeviceRegistration;", "getOrCreateDeviceRegistration", "queryRegistrations", "Lio/reactivex/rxjava3/core/Observable;", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface RegistrationSupplier {
    @NotNull
    Completable deregister(@NotNull String str);

    @NotNull
    Single<DeviceRegistration> getDeviceRegistration(@NotNull String str);

    @NotNull
    Single<DeviceRegistration> getOrCreateDeviceRegistration(@NotNull String str);

    @NotNull
    Observable<Set<DeviceRegistration>> queryRegistrations();
}
