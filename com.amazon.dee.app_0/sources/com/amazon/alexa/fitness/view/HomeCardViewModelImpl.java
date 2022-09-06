package com.amazon.alexa.fitness.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HomeCardViewModelImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0017J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0017R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/view/HomeCardViewModelImpl;", "Landroidx/lifecycle/ViewModel;", "Lcom/amazon/alexa/fitness/api/afx/HomeCardViewModel;", "()V", "_subTitle", "Landroidx/lifecycle/MutableLiveData;", "", "_title", "mainHandler", "Landroid/os/Handler;", "subTitle", "Landroidx/lifecycle/LiveData;", "getSubTitle", "()Landroidx/lifecycle/LiveData;", "title", "getTitle", "setSubTitle", "", "value", "setTitle", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HomeCardViewModelImpl extends ViewModel implements HomeCardViewModel {
    private final MutableLiveData<String> _subTitle;
    private final MutableLiveData<String> _title;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public HomeCardViewModelImpl() {
        ComponentRegistry.getInstance().bindConcreteFactory(HomeCardViewModel.class, new ComponentRegistry.ConcreteComponentFactory<HomeCardViewModel>() { // from class: com.amazon.alexa.fitness.view.HomeCardViewModelImpl.1
            @Override // com.amazon.alexa.protocols.service.api.ComponentRegistry.ConcreteComponentFactory
            @NotNull
            /* renamed from: create */
            public final HomeCardViewModel create2(Context context) {
                return HomeCardViewModelImpl.this;
            }
        });
        this._title = new MutableLiveData<>();
        this._subTitle = new MutableLiveData<>();
    }

    @Override // com.amazon.alexa.fitness.api.afx.HomeCardViewModel
    @NotNull
    public LiveData<String> getSubTitle() {
        return this._subTitle;
    }

    @Override // com.amazon.alexa.fitness.api.afx.HomeCardViewModel
    @NotNull
    public LiveData<String> getTitle() {
        return this._title;
    }

    @Override // com.amazon.alexa.fitness.api.afx.HomeCardViewModel
    @VisibleForTesting
    public void setSubTitle(@NotNull final String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.view.HomeCardViewModelImpl$setSubTitle$1
            @Override // java.lang.Runnable
            public final void run() {
                MutableLiveData mutableLiveData;
                mutableLiveData = HomeCardViewModelImpl.this._subTitle;
                mutableLiveData.setValue(value);
            }
        });
    }

    @Override // com.amazon.alexa.fitness.api.afx.HomeCardViewModel
    @VisibleForTesting
    public void setTitle(@NotNull final String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.view.HomeCardViewModelImpl$setTitle$1
            @Override // java.lang.Runnable
            public final void run() {
                MutableLiveData mutableLiveData;
                mutableLiveData = HomeCardViewModelImpl.this._title;
                mutableLiveData.setValue(value);
            }
        });
    }
}
