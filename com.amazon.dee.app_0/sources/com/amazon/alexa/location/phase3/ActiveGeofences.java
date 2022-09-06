package com.amazon.alexa.location.phase3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes9.dex */
public class ActiveGeofences extends HashMap<String, ALSGeofence> {
    private static final String TAG = "ActiveGeofences";
    @NonNull
    private final Context context;
    private final LazyComponent<CrashReporter> crashReporter;
    private final Subject<List<ALSGeofence>> updateSubject = BehaviorSubject.create();
    private Disposable updaterDisposable;

    public ActiveGeofences(@NonNull LazyComponent<CrashReporter> lazyComponent, @NonNull Context context) {
        this.crashReporter = lazyComponent;
        this.context = context;
    }

    public Observable<List<ALSGeofence>> getUpdateSubject() {
        return this.updateSubject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Disposable getUpdaterDisposable() {
        return this.updaterDisposable;
    }

    public /* synthetic */ void lambda$subscribeToUpstream$0$ActiveGeofences(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "upstream", th, this.crashReporter);
    }

    public void subscribeToUpstream(@NonNull Observable<List<ALSGeofence>> observable) {
        this.updaterDisposable = observable.subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$PNOsA_DYWqa7SNoPlxw-qXCwwww
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ActiveGeofences.this.updateFences((List) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$ActiveGeofences$GL0KYo8evIUSMB9H4zNjM9neDGg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ActiveGeofences.this.lambda$subscribeToUpstream$0$ActiveGeofences((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateFences(@NonNull List<ALSGeofence> list) {
        clear();
        WriteToFile.dumpAsJson(this.context, "setGeofences", list);
        for (ALSGeofence aLSGeofence : list) {
            put(aLSGeofence.getId(), aLSGeofence);
        }
        this.updateSubject.onNext(list);
    }
}
