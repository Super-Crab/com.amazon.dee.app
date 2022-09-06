package com.amazon.alexa.fitness.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.protocols.network.NetworkService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rx.functions.Action1;
/* compiled from: NetworkNotifiableImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/network/NetworkNotifiableImpl;", "Lcom/amazon/alexa/fitness/network/NetworkNotifiable;", "networkService", "Lcom/amazon/alexa/protocols/network/NetworkService;", "(Lcom/amazon/alexa/protocols/network/NetworkService;)V", "_isNetworkConnected", "Landroidx/lifecycle/MutableLiveData;", "", "isNetworkConnected", "Landroidx/lifecycle/LiveData;", "()Landroid/arch/lifecycle/LiveData;", "setNetworkConnected", "(Landroid/arch/lifecycle/LiveData;)V", "getNetworkService", "()Lcom/amazon/alexa/protocols/network/NetworkService;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class NetworkNotifiableImpl implements NetworkNotifiable {
    private MutableLiveData<Boolean> _isNetworkConnected;
    @NotNull
    private LiveData<Boolean> isNetworkConnected;
    @NotNull
    private final NetworkService networkService;

    public NetworkNotifiableImpl(@NotNull NetworkService networkService) {
        Intrinsics.checkParameterIsNotNull(networkService, "networkService");
        this.networkService = networkService;
        this._isNetworkConnected = new MutableLiveData<>();
        MutableLiveData<Boolean> mutableLiveData = this._isNetworkConnected;
        this.isNetworkConnected = mutableLiveData;
        mutableLiveData.postValue(Boolean.valueOf(this.networkService.isConnected()));
        this.networkService.onConnectivityChanged().subscribe(new Action1<Boolean>() { // from class: com.amazon.alexa.fitness.network.NetworkNotifiableImpl.1
            @Override // rx.functions.Action1
            public final void call(Boolean bool) {
                NetworkNotifiableImpl.this._isNetworkConnected.postValue(bool);
            }
        });
    }

    @NotNull
    public final NetworkService getNetworkService() {
        return this.networkService;
    }

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    @NotNull
    public LiveData<Boolean> isNetworkConnected() {
        return this.isNetworkConnected;
    }

    @Override // com.amazon.alexa.fitness.network.NetworkNotifiable
    public void setNetworkConnected(@NotNull LiveData<Boolean> liveData) {
        Intrinsics.checkParameterIsNotNull(liveData, "<set-?>");
        this.isNetworkConnected = liveData;
    }
}
