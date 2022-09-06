package com.amazon.alexa.biloba.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class LiveDataUtils {
    private static final String TAG = "LiveDataUtils";

    private LiveDataUtils() {
    }

    private static <T> T getLiveDataEqual(T t, T t2, LiveData<T>... liveDataArr) {
        for (LiveData<T> liveData : liveDataArr) {
            if (liveData != null && Objects.equals(liveData.getValue(), t)) {
                return t;
            }
        }
        return t2;
    }

    private static <T> T getLiveDataNotEqual(T t, @NonNull LiveData<T>... liveDataArr) {
        for (LiveData<T> liveData : liveDataArr) {
            if (liveData != null && !Objects.equals(liveData.getValue(), t)) {
                return liveData.getValue();
            }
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void mergeLiveDataEqual(final T t, final T t2, @NonNull final MediatorLiveData<T> mediatorLiveData, @NonNull final LiveData<T>... liveDataArr) {
        mediatorLiveData.setValue(t2);
        for (QueueBlockerLiveDataProvider.MultiQueueLiveData multiQueueLiveData : liveDataArr) {
            mediatorLiveData.addSource(multiQueueLiveData, new Observer() { // from class: com.amazon.alexa.biloba.utils.-$$Lambda$LiveDataUtils$_MPmca3DGSOgeQatCv_klkrCw8w
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    MediatorLiveData.this.setValue(LiveDataUtils.getLiveDataEqual(t, t2, liveDataArr));
                }
            });
        }
    }

    public static <T> void mergeLiveDataEqualTrue(@NonNull MediatorLiveData<Boolean> mediatorLiveData, @NonNull LiveData<Boolean>... liveDataArr) {
        mergeLiveDataEqual(Boolean.TRUE, Boolean.FALSE, mediatorLiveData, liveDataArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void mergeLiveDataNotEqual(final T t, @NonNull final MediatorLiveData<T> mediatorLiveData, @NonNull final LiveData<T>... liveDataArr) {
        mediatorLiveData.setValue(t);
        for (QueueBlockerLiveDataProvider.MultiQueueLiveData multiQueueLiveData : liveDataArr) {
            mediatorLiveData.addSource(multiQueueLiveData, new Observer() { // from class: com.amazon.alexa.biloba.utils.-$$Lambda$LiveDataUtils$_TBMkmoXbzbaVbVX9MDO17cac-4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    MediatorLiveData.this.setValue(LiveDataUtils.getLiveDataNotEqual(t, liveDataArr));
                }
            });
        }
    }

    public static <T> void mergeLiveDataNotNull(@NonNull MediatorLiveData<T> mediatorLiveData, @NonNull LiveData<T>... liveDataArr) {
        mergeLiveDataNotEqual(null, mediatorLiveData, liveDataArr);
    }
}
