package com.amazon.alexa.photos;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.photos.events.PhotosUploaderEvent;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class MessagePublisher {
    private static final long MESSAGE_DELAY_TIME_IN_SECONDS = 1;
    private Disposable disposable;
    private final LazyComponent<EventBus> eventBus;
    private final PublishSubject<PhotosUploaderEventMessage> publishSubject = PublishSubject.create();

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessagePublisher(@NonNull LazyComponent<EventBus> lazyComponent) {
        this.eventBus = lazyComponent;
        initializeMessageFilter();
    }

    private void initializeMessageFilter() {
        this.disposable = Observable.merge(this.publishSubject.filter($$Lambda$MessagePublisher$7VhEOvUKDJyjq2gVJwWJSCnoL8.INSTANCE).debounce(1L, TimeUnit.SECONDS), this.publishSubject.filter($$Lambda$MessagePublisher$fbOTnGohyi1ZbQOiDzN7v89HDDw.INSTANCE).throttleWithTimeout(1L, TimeUnit.SECONDS), this.publishSubject.filter($$Lambda$MessagePublisher$4GWTF7h3Znw2jSrM60v31sW8Js.INSTANCE)).subscribe(new Consumer() { // from class: com.amazon.alexa.photos.-$$Lambda$MessagePublisher$3Bt0wsAcGLgGyk6Y3vXl2o0kylk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MessagePublisher.this.lambda$initializeMessageFilter$3$MessagePublisher((PhotosUploaderEventMessage) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initializeMessageFilter$0(PhotosUploaderEventMessage photosUploaderEventMessage) throws Throwable {
        return photosUploaderEventMessage.eventType == PhotosUploaderEventType.UPLOAD_PROCESSING_COMPLETE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$initializeMessageFilter$1(PhotosUploaderEventMessage photosUploaderEventMessage) throws Throwable {
        return photosUploaderEventMessage.eventType == PhotosUploaderEventType.UPLOAD_PROGRESS_STATUS;
    }

    private void publishMessageToEventBus(PhotosUploaderEventType photosUploaderEventType, PhotosUploaderEvent photosUploaderEvent) {
        this.eventBus.mo10268get().publish(new Message.Builder().setEventType(photosUploaderEventType.getValue()).setPayload(photosUploaderEvent.getPayloadData()).build());
    }

    public /* synthetic */ void lambda$initializeMessageFilter$3$MessagePublisher(PhotosUploaderEventMessage photosUploaderEventMessage) throws Throwable {
        publishMessageToEventBus(photosUploaderEventMessage.eventType, photosUploaderEventMessage.event);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void publishMessage(PhotosUploaderEventType photosUploaderEventType, PhotosUploaderEvent photosUploaderEvent) {
        this.publishSubject.onNext(new PhotosUploaderEventMessage(photosUploaderEventType, photosUploaderEvent));
    }
}
