package com.amazon.alexa.drivemode.api;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.routing.api.RoutingAdapter;
/* loaded from: classes7.dex */
public interface DriveModeMainActivityCompanion {

    /* loaded from: classes7.dex */
    public interface ViewModel {
        boolean applyContentMode(int i);

        void initialize(@Nullable Bundle bundle, @NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2);

        @Deprecated
        void initialize(@Nullable Bundle bundle, @NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2, @NonNull ViewGroup viewGroup3);

        void restoreState(@Nullable Bundle bundle);

        @Nullable
        Bundle saveState();
    }

    RoutingAdapter getDriveModeIngressRoutingAdapter();

    ViewModel getMainActivityCompanionViewModel();
}
