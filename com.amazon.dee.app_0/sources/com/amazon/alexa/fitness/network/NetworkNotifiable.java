package com.amazon.alexa.fitness.network;

import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: NetworkNotifiable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/network/NetworkNotifiable;", "", "isNetworkConnected", "Landroidx/lifecycle/LiveData;", "", "()Landroid/arch/lifecycle/LiveData;", "setNetworkConnected", "(Landroid/arch/lifecycle/LiveData;)V", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface NetworkNotifiable {
    @NotNull
    LiveData<Boolean> isNetworkConnected();

    void setNetworkConnected(@NotNull LiveData<Boolean> liveData);
}
