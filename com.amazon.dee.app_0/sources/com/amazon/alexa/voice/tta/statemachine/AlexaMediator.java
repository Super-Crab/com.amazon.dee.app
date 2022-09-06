package com.amazon.alexa.voice.tta.statemachine;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.compat.AlexaExpectTextListener;
import com.amazon.alexa.api.compat.AlexaTextResponseListener;
import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.sdk.AlexaPlayerInfoCardTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaRenderedCardReceiver;
import com.amazon.alexa.voice.tta.sdk.AlexaStateTracker;
import com.amazon.alexa.voice.tta.sdk.AlexaTextResponseTracker;
import com.amazon.alexa.voice.tta.sdk.TextVisualTask;
import com.amazon.alexa.voice.tta.typing.TextTtaResponseMessage;
import com.amazon.alexa.voice.tta.typing.TtaMessageSanitizer;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaMediator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020'2\u0006\u0010\u001e\u001a\u00020\u001fJ\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0*J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00170*J\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0*J\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001b0*J\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00150*J\b\u00100\u001a\u00020'H\u0016J\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001f0*J\u0018\u00102\u001a\u00020'2\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u000104H\u0016J\f\u00105\u001a\b\u0012\u0004\u0012\u00020#0*J\u0010\u00106\u001a\u00020'2\u0006\u0010\"\u001a\u00020\u0019H\u0016J\f\u00107\u001a\b\u0012\u0004\u0012\u00020!0*J\u000e\u00108\u001a\u00020'2\u0006\u00109\u001a\u00020!J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00190*J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001f0*J\u000e\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020\u001bJ\u0010\u0010>\u001a\u00020'2\u0006\u0010\"\u001a\u00020\u0019H\u0002J\u0006\u0010?\u001a\u00020'J\u000e\u0010@\u001a\u00020'2\u0006\u00109\u001a\u00020!J\u000e\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020#J\u000e\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u00020!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;", "Lcom/amazon/alexa/api/compat/AlexaTextResponseListener;", "Lcom/amazon/alexa/api/AlexaLocalesListener;", "Lcom/amazon/alexa/api/compat/AlexaExpectTextListener;", "alexaClientSdkApis", "Lcom/amazon/alexa/voice/tta/sdk/AlexaClientSdkApis;", "alexaStateTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaStateTracker;", "alexaTextResponseTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaTextResponseTracker;", "alexaRenderedCardReceiver", "Lcom/amazon/alexa/voice/tta/sdk/AlexaRenderedCardReceiver;", "alexaPlayerInfoCardTracker", "Lcom/amazon/alexa/voice/tta/sdk/AlexaPlayerInfoCardTracker;", "messageSanitizer", "Lcom/amazon/alexa/voice/tta/typing/TtaMessageSanitizer;", "ttaEventSender", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEventSender;", "(Lcom/amazon/alexa/voice/tta/sdk/AlexaClientSdkApis;Lcom/amazon/alexa/voice/tta/sdk/AlexaStateTracker;Lcom/amazon/alexa/voice/tta/sdk/AlexaTextResponseTracker;Lcom/amazon/alexa/voice/tta/sdk/AlexaRenderedCardReceiver;Lcom/amazon/alexa/voice/tta/sdk/AlexaPlayerInfoCardTracker;Lcom/amazon/alexa/voice/tta/typing/TtaMessageSanitizer;Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEventSender;)V", "alexaExpectText", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "alexaLocale", "Ljava/util/Locale;", "alexaTextResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "cardResponse", "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "isAppSearchEnabled", "", "textRequest", "", "textResponse", "Lcom/amazon/alexa/voice/ui/tta/TtaResponseMessage;", "textVisualTask", "Lcom/amazon/alexa/voice/tta/sdk/TextVisualTask;", "destroy", "", "initialize", "onAlexaCard", "Lio/reactivex/rxjava3/core/Observable;", "onAlexaLocale", "onAlexaPlayerInfoState", "Lcom/amazon/alexa/api/AlexaPlayerInfoState;", "onCardResponse", "onExpectText", "onExpectTextReceived", "onListening", "onLocales", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "onResponse", "onTextReceived", "onTextRequest", "onTextRequestReceived", "text", "onTextResponse", "onThinking", "publishCardData", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, "recordTextResponseMetrics", "release", "sendText", "showResponse", "response", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class AlexaMediator implements AlexaTextResponseListener, AlexaLocalesListener, AlexaExpectTextListener {
    private final AlexaClientSdkApis alexaClientSdkApis;
    private final BehaviorSubject<Object> alexaExpectText;
    private final BehaviorSubject<Locale> alexaLocale;
    private final AlexaPlayerInfoCardTracker alexaPlayerInfoCardTracker;
    private final AlexaRenderedCardReceiver alexaRenderedCardReceiver;
    private final AlexaStateTracker alexaStateTracker;
    private final BehaviorSubject<TextResponse> alexaTextResponse;
    private final AlexaTextResponseTracker alexaTextResponseTracker;
    private final BehaviorSubject<TtaResponseCard> cardResponse;
    private final CompositeDisposable disposable;
    private boolean isAppSearchEnabled;
    private final TtaMessageSanitizer messageSanitizer;
    private final BehaviorSubject<String> textRequest;
    private final BehaviorSubject<TtaResponseMessage> textResponse;
    private final TextVisualTask textVisualTask;
    private final TtaEventSender ttaEventSender;

    public AlexaMediator(@NotNull AlexaClientSdkApis alexaClientSdkApis, @NotNull AlexaStateTracker alexaStateTracker, @NotNull AlexaTextResponseTracker alexaTextResponseTracker, @NotNull AlexaRenderedCardReceiver alexaRenderedCardReceiver, @NotNull AlexaPlayerInfoCardTracker alexaPlayerInfoCardTracker, @NotNull TtaMessageSanitizer messageSanitizer, @NotNull TtaEventSender ttaEventSender) {
        Intrinsics.checkParameterIsNotNull(alexaClientSdkApis, "alexaClientSdkApis");
        Intrinsics.checkParameterIsNotNull(alexaStateTracker, "alexaStateTracker");
        Intrinsics.checkParameterIsNotNull(alexaTextResponseTracker, "alexaTextResponseTracker");
        Intrinsics.checkParameterIsNotNull(alexaRenderedCardReceiver, "alexaRenderedCardReceiver");
        Intrinsics.checkParameterIsNotNull(alexaPlayerInfoCardTracker, "alexaPlayerInfoCardTracker");
        Intrinsics.checkParameterIsNotNull(messageSanitizer, "messageSanitizer");
        Intrinsics.checkParameterIsNotNull(ttaEventSender, "ttaEventSender");
        this.alexaClientSdkApis = alexaClientSdkApis;
        this.alexaStateTracker = alexaStateTracker;
        this.alexaTextResponseTracker = alexaTextResponseTracker;
        this.alexaRenderedCardReceiver = alexaRenderedCardReceiver;
        this.alexaPlayerInfoCardTracker = alexaPlayerInfoCardTracker;
        this.messageSanitizer = messageSanitizer;
        this.ttaEventSender = ttaEventSender;
        this.disposable = new CompositeDisposable();
        this.textVisualTask = new TextVisualTask();
        BehaviorSubject<String> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.textRequest = create;
        BehaviorSubject<TextResponse> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.alexaTextResponse = create2;
        BehaviorSubject<Object> create3 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "BehaviorSubject.create()");
        this.alexaExpectText = create3;
        BehaviorSubject<Locale> create4 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "BehaviorSubject.create()");
        this.alexaLocale = create4;
        BehaviorSubject<TtaResponseMessage> create5 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create5, "BehaviorSubject.create()");
        this.textResponse = create5;
        BehaviorSubject<TtaResponseCard> create6 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create6, "BehaviorSubject.create()");
        this.cardResponse = create6;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
        if (r3 != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void recordTextResponseMetrics(com.amazon.alexa.api.compat.TextResponse r3) {
        /*
            r2 = this;
            com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender r0 = r2.ttaEventSender
            com.amazon.alexa.voice.tta.metrics.AppSearchEvent r1 = com.amazon.alexa.voice.tta.metrics.AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED
            r0.sendEvent(r1)
            java.lang.String r0 = r3.getTitle()
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L18
            com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender r0 = r2.ttaEventSender
            com.amazon.alexa.voice.tta.metrics.AppSearchEvent r1 = com.amazon.alexa.voice.tta.metrics.AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_EMPTY
            r0.sendEvent(r1)
        L18:
            com.amazon.alexa.api.TextResponseMetadata r0 = r3.getMetadata()
            r1 = 0
            if (r0 == 0) goto L24
            java.lang.String r0 = r0.getPromptId()
            goto L25
        L24:
            r0 = r1
        L25:
            com.amazon.alexa.api.TextResponseMetadata r3 = r3.getMetadata()
            if (r3 == 0) goto L2f
            java.lang.String r1 = r3.getNamespace()
        L2f:
            if (r0 == 0) goto L37
            boolean r3 = kotlin.text.StringsKt.isBlank(r0)
            if (r3 == 0) goto L3e
        L37:
            com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender r3 = r2.ttaEventSender
            com.amazon.alexa.voice.tta.metrics.AppSearchEvent r0 = com.amazon.alexa.voice.tta.metrics.AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_NO_PROMPT_ID
            r3.sendEvent(r0)
        L3e:
            if (r1 == 0) goto L46
            boolean r3 = kotlin.text.StringsKt.isBlank(r1)
            if (r3 == 0) goto L4d
        L46:
            com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender r3 = r2.ttaEventSender
            com.amazon.alexa.voice.tta.metrics.AppSearchEvent r0 = com.amazon.alexa.voice.tta.metrics.AppSearchEvent.VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED_NO_NAMESPACE
            r3.sendEvent(r0)
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.statemachine.AlexaMediator.recordTextResponseMetrics(com.amazon.alexa.api.compat.TextResponse):void");
    }

    public final void destroy() {
        this.alexaClientSdkApis.destroy();
    }

    public final void initialize(boolean z) {
        this.alexaClientSdkApis.start();
        this.alexaClientSdkApis.scheduleVisualTask(this.textVisualTask);
        this.alexaClientSdkApis.registerStateListener(this.alexaStateTracker);
        this.alexaClientSdkApis.registerCardListener(this.alexaRenderedCardReceiver);
        this.alexaClientSdkApis.registerPlayerInfoCardListener(this.alexaPlayerInfoCardTracker);
        this.alexaClientSdkApis.registerLocalesListener(this);
        this.isAppSearchEnabled = z;
        if (z) {
            this.alexaClientSdkApis.registerTextResponseListener(this);
            this.alexaClientSdkApis.registerExpectTextListener(this);
        } else {
            this.alexaClientSdkApis.registerTextResponseListener(this.alexaTextResponseTracker);
        }
        this.disposable.add(this.alexaTextResponseTracker.onResponse().subscribe(new Consumer<TtaResponseMessage>() { // from class: com.amazon.alexa.voice.tta.statemachine.AlexaMediator$initialize$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(TtaResponseMessage ttaResponseMessage) {
                BehaviorSubject behaviorSubject;
                behaviorSubject = AlexaMediator.this.textResponse;
                behaviorSubject.onNext(ttaResponseMessage);
            }
        }));
    }

    @NotNull
    public final Observable<TtaResponseCard> onAlexaCard() {
        Observable<TtaResponseCard> onAlexaCard = this.alexaRenderedCardReceiver.onAlexaCard();
        Intrinsics.checkExpressionValueIsNotNull(onAlexaCard, "alexaRenderedCardReceiver.onAlexaCard()");
        return onAlexaCard;
    }

    @NotNull
    public final Observable<Locale> onAlexaLocale() {
        return this.alexaLocale;
    }

    @NotNull
    public final Observable<AlexaPlayerInfoState> onAlexaPlayerInfoState() {
        Observable<AlexaPlayerInfoState> onAlexaPlayerInfoState = this.alexaPlayerInfoCardTracker.onAlexaPlayerInfoState();
        Intrinsics.checkExpressionValueIsNotNull(onAlexaPlayerInfoState, "alexaPlayerInfoCardTrack….onAlexaPlayerInfoState()");
        return onAlexaPlayerInfoState;
    }

    @NotNull
    public final Observable<TtaResponseCard> onCardResponse() {
        return this.cardResponse;
    }

    @NotNull
    public final Observable<Object> onExpectText() {
        return this.alexaExpectText;
    }

    @Override // com.amazon.alexa.api.compat.AlexaExpectTextListener
    public void onExpectTextReceived() {
        this.alexaExpectText.onNext(new Object());
    }

    @NotNull
    public final Observable<Boolean> onListening() {
        Observable<Boolean> onListening = this.alexaStateTracker.onListening();
        Intrinsics.checkExpressionValueIsNotNull(onListening, "alexaStateTracker.onListening()");
        return onListening;
    }

    @Override // com.amazon.alexa.api.AlexaLocalesListener
    public void onLocales(@Nullable List<Locale> list) {
        this.alexaLocale.onNext(list != null ? list.get(0) : null);
    }

    @NotNull
    public final Observable<TtaResponseMessage> onResponse() {
        return this.textResponse;
    }

    @Override // com.amazon.alexa.api.compat.AlexaTextResponseListener
    public void onTextReceived(@NotNull TextResponse textResponse) {
        Intrinsics.checkParameterIsNotNull(textResponse, "textResponse");
        recordTextResponseMetrics(textResponse);
        this.alexaTextResponse.onNext(textResponse);
    }

    @NotNull
    public final Observable<String> onTextRequest() {
        return this.textRequest;
    }

    public final void onTextRequestReceived(@NotNull String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        if (this.isAppSearchEnabled) {
            this.textRequest.onNext(text);
        } else {
            this.alexaClientSdkApis.sendText(text);
        }
    }

    @NotNull
    public final Observable<TextResponse> onTextResponse() {
        return this.alexaTextResponse;
    }

    @NotNull
    public final Observable<Boolean> onThinking() {
        Observable<Boolean> onThinking = this.alexaStateTracker.onThinking();
        Intrinsics.checkExpressionValueIsNotNull(onThinking, "alexaStateTracker.onThinking()");
        return onThinking;
    }

    public final void publishCardData(@NotNull TtaResponseCard card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        this.cardResponse.onNext(card);
    }

    public final void release() {
        this.alexaClientSdkApis.deregisterStateListener(this.alexaStateTracker);
        this.alexaClientSdkApis.deregisterCardListener(this.alexaRenderedCardReceiver);
        this.alexaClientSdkApis.deregisterPlayerInfoCardListener(this.alexaPlayerInfoCardTracker);
        this.alexaClientSdkApis.deregisterLocalesListener(this);
        if (this.isAppSearchEnabled) {
            this.alexaClientSdkApis.deregisterTextResponseListener(this);
            this.alexaClientSdkApis.deregisterExpectTextListener(this);
        } else {
            this.alexaClientSdkApis.deregisterTextResponseListener(this.alexaTextResponseTracker);
        }
        this.alexaClientSdkApis.unscheduleVisualTask(this.textVisualTask);
        this.alexaClientSdkApis.stop();
        this.disposable.clear();
    }

    public final void sendText(@NotNull String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.alexaClientSdkApis.sendText(text);
    }

    public final void showResponse(@NotNull String response) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(response, "response");
        String santizedResponse = this.messageSanitizer.sanitizeResponse(response);
        Intrinsics.checkExpressionValueIsNotNull(santizedResponse, "santizedResponse");
        isBlank = StringsKt__StringsJVMKt.isBlank(santizedResponse);
        if (!isBlank) {
            this.textResponse.onNext(new TextTtaResponseMessage(santizedResponse));
        }
    }

    public final void showResponse(@NotNull TtaResponseMessage response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.textResponse.onNext(response);
    }
}
