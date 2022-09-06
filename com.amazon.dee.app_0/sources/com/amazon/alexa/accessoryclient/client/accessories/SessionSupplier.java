package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessoryclient.common.api.AccessoryTransportChange;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000e0\u0003H&J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H&J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H&J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H&J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H&J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H&J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H&J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0016H&¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/SessionSupplier;", "", "createAndConnectSession", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "createAndConnectSessionAwaitConnection", "Lio/reactivex/rxjava3/core/Completable;", "createAndConnectSessionWithOptions", "options", "Lcom/amazon/alexa/accessory/AccessorySessionOptions;", "createAndConnectSessionWithOptionsAwaitConnection", "getActiveAccessories", "", "getActiveSessions", "getSession", "identifier", "", "hasActiveSessions", "", "observeSessionConnected", "Lio/reactivex/rxjava3/core/Observable;", "observeSessionCreated", "observeSessionDisconnected", "observeSessionFailed", "observeSessionReleased", "observeSessionTransportChanged", "Lcom/amazon/alexa/accessoryclient/common/api/AccessoryTransportChange;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface SessionSupplier {
    @NotNull
    Single<AccessorySession> createAndConnectSession(@NotNull Accessory accessory);

    @NotNull
    Completable createAndConnectSessionAwaitConnection(@NotNull Accessory accessory);

    @NotNull
    Single<AccessorySession> createAndConnectSessionWithOptions(@NotNull Accessory accessory, @NotNull AccessorySessionOptions accessorySessionOptions);

    @NotNull
    Single<AccessorySession> createAndConnectSessionWithOptionsAwaitConnection(@NotNull Accessory accessory, @NotNull AccessorySessionOptions accessorySessionOptions);

    @NotNull
    Single<List<Accessory>> getActiveAccessories();

    @NotNull
    Single<List<AccessorySession>> getActiveSessions();

    @NotNull
    AccessorySession getSession(@NotNull String str);

    @NotNull
    Single<Boolean> hasActiveSessions();

    @NotNull
    Observable<Accessory> observeSessionConnected();

    @NotNull
    Observable<Accessory> observeSessionCreated();

    @NotNull
    Observable<Accessory> observeSessionDisconnected();

    @NotNull
    Observable<Accessory> observeSessionFailed();

    @NotNull
    Observable<Accessory> observeSessionReleased();

    @NotNull
    Observable<AccessoryTransportChange> observeSessionTransportChanged();
}
