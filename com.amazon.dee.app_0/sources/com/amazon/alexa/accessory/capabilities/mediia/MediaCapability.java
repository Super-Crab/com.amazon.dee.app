package com.amazon.alexa.accessory.capabilities.mediia;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.mediia.MediaCapability;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Media;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.media.MediaProducer;
import com.amazon.alexa.accessory.repositories.media.MediaProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
/* loaded from: classes.dex */
public class MediaCapability extends AccessoryCapability {
    private final MediaActionHandler actionHandler;
    private Disposable playbackEventDisposable;
    private final MediaProducer producer;
    private final MediaProvider provider;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class MediaActionHandler implements MediaProducer.ActionHandler {
        private final ActionQueue mediaQueue = new ActionQueue();
        private final ActionQueue playbackEventQueue = new ActionQueue();

        public MediaActionHandler() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleIssueMediaControl$0(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unexpected error code ")));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handlePlaybackStatus$1(Media.PlaybackStatus playbackStatus, Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            Logger.e("Failed to send playback status '%s' to accessory", playbackStatus.getStatus());
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Unexpected error code ")));
        }

        void cancelAllActions() {
            this.mediaQueue.cancelAll();
            this.playbackEventQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.media.MediaProducer.ActionHandler
        public void handleIssueMediaControl(Media.MediaControl mediaControl, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(mediaControl, "control");
            Preconditions.notNull(result, "result");
            this.mediaQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.ISSUE_MEDIA_CONTROL).setIssueMediaControl(Media.IssueMediaControl.newBuilder().setControl(mediaControl)).mo10084build()), MediaCapability.this.stream, result, $$Lambda$MediaCapability$MediaActionHandler$kye6gi3g4Q_rawxPCmi_skW0z1k.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.media.MediaProducer.ActionHandler
        public void handlePlaybackStatus(final Media.PlaybackStatus playbackStatus, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(playbackStatus, "status");
            Preconditions.notNull(result, "result");
            this.playbackEventQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.MEDIA_EVENT_OCCURRED).setMediaEventOccurred(Media.MediaEventOccurred.newBuilder().setEvent(Media.MediaEvent.MEDIA_EVENT_PLAYBACK_STATUS_CHANGED).setPlaybackStatus(playbackStatus).mo10084build()).mo10084build()), MediaCapability.this.stream, result, new MappedResponseAction.Mapper() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$MediaActionHandler$NDlH3AiGExi1ZAie_NMrGW5MFnw
                @Override // com.amazon.alexa.accessory.internal.MappedResponseAction.Mapper
                public final Object map(Accessories.Response response) {
                    return MediaCapability.MediaActionHandler.lambda$handlePlaybackStatus$1(Media.PlaybackStatus.this, response);
                }
            }));
        }
    }

    public MediaCapability(MediaProducer mediaProducer, MediaProvider mediaProvider) {
        Preconditions.notNull(mediaProducer, "producer");
        Preconditions.notNull(mediaProvider, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
        this.producer = mediaProducer;
        this.provider = mediaProvider;
        this.actionHandler = new MediaActionHandler();
    }

    @SuppressLint({"CheckResult"})
    private ControlMessageHandler<Media.GetPlaybackStatus> getGetPlaybackStatusEventsHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$cBfImRuoQwQDArvOAJdb0ZL62i8
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                MediaCapability.this.lambda$getGetPlaybackStatusEventsHandler$10$MediaCapability(controlStream, command, (Media.GetPlaybackStatus) obj);
            }
        };
    }

    private ControlMessageHandler<Media.IssueMediaControl> getIssueMediaControlHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$NuLHdBa0cE8pkleWL7wBMGuCgJM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                MediaCapability.this.lambda$getIssueMediaControlHandler$0$MediaCapability(controlStream, command, (Media.IssueMediaControl) obj);
            }
        };
    }

    private ControlMessageHandler<Media.RegisterForMediaEvents> getRegisterForMediaEventsHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$WbS9KS8sqtRqMdfE0XOhnIH2CRk
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                MediaCapability.this.lambda$getRegisterForMediaEventsHandler$7$MediaCapability(controlStream, command, (Media.RegisterForMediaEvents) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$1(Media.PlaybackStatus playbackStatus, Media.PlaybackStatus playbackStatus2) throws Throwable {
        return playbackStatus.getStatus() == playbackStatus2.getStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$5(Media.PlaybackStatus playbackStatus) throws Throwable {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updated playback status sent to accessory: ");
        outline107.append(playbackStatus.getStatus());
        Logger.d(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$9(ControlStream controlStream, Throwable th) throws Throwable {
        Logger.e("Couldn't get playback status; telling accessory there was an internal error.", th);
        controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_PLAYBACK_STATUS).setResponse(Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.INTERNAL).mo10084build()).mo10084build()));
    }

    public /* synthetic */ void lambda$getGetPlaybackStatusEventsHandler$10$MediaCapability(final ControlStream controlStream, Accessories.Command command, Media.GetPlaybackStatus getPlaybackStatus) throws Exception {
        this.provider.queryPlaybackStatus().firstOrError().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$FzE0Qzo4C4WWy1ukco8bwOWjWjI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ControlStream.this.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_PLAYBACK_STATUS).setResponse(Accessories.Response.newBuilder().setPlaybackStatus((Media.PlaybackStatus) obj).setErrorCode(Common.ErrorCode.SUCCESS).mo10084build()).mo10084build()));
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$cR0sEiGG1BGUzJVD3KtB7iQ-Mt0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MediaCapability.lambda$null$9(ControlStream.this, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getIssueMediaControlHandler$0$MediaCapability(ControlStream controlStream, Accessories.Command command, Media.IssueMediaControl issueMediaControl) throws Exception {
        this.provider.provideMediaCommand(issueMediaControl.getControl());
        controlStream.respond(Accessories.Command.ISSUE_MEDIA_CONTROL, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ void lambda$getRegisterForMediaEventsHandler$7$MediaCapability(ControlStream controlStream, Accessories.Command command, Media.RegisterForMediaEvents registerForMediaEvents) throws Exception {
        try {
            if (registerForMediaEvents.getPlaybackStatusChanged()) {
                if (ObservableUtils.isDisposed(this.playbackEventDisposable)) {
                    this.playbackEventDisposable = this.provider.queryPlaybackStatus().observeOn(AndroidSchedulers.mainThread()).distinctUntilChanged($$Lambda$MediaCapability$8zv8oOxPppuiw1UJIKpohME0xVw.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$XX2ve_mgs1JB0APcpMwYb8DJies
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return MediaCapability.this.lambda$null$4$MediaCapability((Media.PlaybackStatus) obj);
                        }
                    }).subscribe($$Lambda$MediaCapability$br9E2QWNSKiM_cumcAH9qmNn_Y4.INSTANCE, $$Lambda$MediaCapability$wvl6QmGYZodPAas9JCi7xnW1j94.INSTANCE);
                }
            } else {
                ObservableUtils.dispose(this.playbackEventDisposable);
                this.playbackEventDisposable = null;
                Logger.i("Deregistered from playback status changed events.");
            }
            this.provider.provideRegisterForMediaEvents(registerForMediaEvents);
            controlStream.respond(Accessories.Command.REGISTER_FOR_MEDIA_EVENTS, Common.ErrorCode.SUCCESS);
        } catch (Exception e) {
            Logger.e("Exception trying to update media events registration information", e);
            controlStream.respond(Accessories.Command.REGISTER_FOR_MEDIA_EVENTS, Common.ErrorCode.INTERNAL);
        }
    }

    public /* synthetic */ void lambda$null$2$MediaCapability(Media.PlaybackStatus playbackStatus, Producer.Result result) {
        this.actionHandler.handlePlaybackStatus(playbackStatus, result);
    }

    public /* synthetic */ ObservableSource lambda$null$4$MediaCapability(final Media.PlaybackStatus playbackStatus) throws Throwable {
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.capabilities.mediia.-$$Lambda$MediaCapability$3WJ5D1_-DOBABqcjfLWpAbrupQM
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MediaCapability.this.lambda$null$2$MediaCapability(playbackStatus, result);
            }
        }).doOnError($$Lambda$MediaCapability$DoXHpqZIQYuREmZjhQEx3OqFgK4.INSTANCE).onErrorComplete().andThen(Observable.just(playbackStatus));
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        Preconditions.mainThread();
        this.producer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
        ObservableUtils.dispose(this.playbackEventDisposable);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        Preconditions.mainThread();
        this.producer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAgnosticDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.ISSUE_MEDIA_CONTROL, getIssueMediaControlHandler());
        this.stream.addMessageHandler(Accessories.Command.REGISTER_FOR_MEDIA_EVENTS, getRegisterForMediaEventsHandler());
        this.stream.addMessageHandler(Accessories.Command.GET_PLAYBACK_STATUS, getGetPlaybackStatusEventsHandler());
        accessoryDescriptor.add(this.stream);
    }
}
