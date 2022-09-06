package com.amazon.alexa.biloba.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
/* loaded from: classes6.dex */
public interface BilobaViewModel {
    void create(@Nullable Bundle bundle);

    void destroy();

    LiveData<Throwable> getError();

    String getErrorViewMetricName();

    LiveData<Boolean> getIsLoading();

    void sendRequest();
}
