package com.amazon.alexa.accessory.avsclient.media;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.Media;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
/* loaded from: classes.dex */
class MediaControlActionHandler {
    private static final String TAG = "MediaControlActionHandler";

    /* JADX INFO: Access modifiers changed from: package-private */
    public Single<AccessorySession> sendMediaControl(List<AccessorySession> list, final Media.MediaControl mediaControl) {
        return Observable.fromIterable(list).concatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.media.-$$Lambda$MediaControlActionHandler$KzQqqOJJQIFwzs75jcufC-NKdIM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                ObservableSource onErrorResumeNext;
                onErrorResumeNext = r2.getMediaRepository().issueMediaControl(r0).andThen(Observable.just(r2)).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.avsclient.media.-$$Lambda$MediaControlActionHandler$A2q16pwhB_0HUp1TcYSBMhU5J7U
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        return Logger.e("%s: Failed to negotiate MediaControl %s with session %s", (Throwable) obj2, MediaControlActionHandler.TAG, Media.MediaControl.this, r2.getAddress());
                    }
                });
                return onErrorResumeNext;
            }
        }).firstOrError();
    }
}
