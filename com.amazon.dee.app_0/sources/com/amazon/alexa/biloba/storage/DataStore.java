package com.amazon.alexa.biloba.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
/* loaded from: classes6.dex */
public abstract class DataStore {
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Throwable> error = new MutableLiveData<>();

    public void clear() {
        this.loading = new MutableLiveData<>();
        this.error = new MutableLiveData<>();
    }

    public LiveData<Throwable> getError() {
        return this.error;
    }

    public LiveData<Boolean> getIsLoading() {
        return this.loading;
    }

    public void postError(Throwable th) {
        this.error.setValue(th);
    }

    public void setIsLoading(Boolean bool) {
        this.loading.setValue(bool);
    }
}
