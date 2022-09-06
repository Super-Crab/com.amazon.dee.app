package com.amazon.alexa.voice.tta.statemachine;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine;
import com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions;
import com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineBuilder;
import com.amazon.alexa.voice.tta.statemachine.scxml.SIMBACallContext;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SearchWorkflow.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 *2\u00020\u0001:\u0001*B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\u0006\u0010\u001c\u001a\u00020\u0013J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0016J \u0010#\u001a\u00020\u00132\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8\u0006@BX\u0087.¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/SearchWorkflow;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;", "alexaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;", "simbaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "(Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;)V", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getDisposable$AlexaMobileAndroidVoice_TTA_release", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "<set-?>", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachine;", "inAppSearchStateMachine", "inAppSearchStateMachine$annotations", "()V", "getInAppSearchStateMachine", "()Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachine;", "callSIMBA", "", "callContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "initialize", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "notifyNoSimbaResults", "notifySIMBACallCancelled", "registerObservers", "release", "renderCardData", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "showExistingAvsTextResponse", "textResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "showSIMBAResults", "results", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "showUserUtterance", "text", "", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchWorkflow implements InAppSearchStateMachineActions {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SearchWorkflow";
    private final AlexaMediator alexaMediator;
    @NotNull
    private final CompositeDisposable disposable;
    @NotNull
    private InAppSearchStateMachine inAppSearchStateMachine;
    private final SimbaMediator simbaMediator;

    /* compiled from: SearchWorkflow.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/SearchWorkflow$Companion;", "", "()V", "TAG", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SearchWorkflow(@NotNull AlexaMediator alexaMediator, @NotNull SimbaMediator simbaMediator) {
        Intrinsics.checkParameterIsNotNull(alexaMediator, "alexaMediator");
        Intrinsics.checkParameterIsNotNull(simbaMediator, "simbaMediator");
        this.alexaMediator = alexaMediator;
        this.simbaMediator = simbaMediator;
        this.disposable = new CompositeDisposable();
    }

    public static final /* synthetic */ InAppSearchStateMachine access$getInAppSearchStateMachine$p(SearchWorkflow searchWorkflow) {
        InAppSearchStateMachine inAppSearchStateMachine = searchWorkflow.inAppSearchStateMachine;
        if (inAppSearchStateMachine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inAppSearchStateMachine");
        }
        return inAppSearchStateMachine;
    }

    @VisibleForTesting
    public static /* synthetic */ void inAppSearchStateMachine$annotations() {
    }

    private final void registerObservers() {
        this.disposable.clear();
        this.disposable.add(this.alexaMediator.onTextRequest().subscribe(new Consumer<String>() { // from class: com.amazon.alexa.voice.tta.statemachine.SearchWorkflow$registerObservers$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String queryText) {
                InAppSearchStateMachine inAppSearchStateMachine = SearchWorkflow.this.getInAppSearchStateMachine();
                Intrinsics.checkExpressionValueIsNotNull(queryText, "queryText");
                inAppSearchStateMachine.handleWillSendText(queryText);
            }
        }));
        this.disposable.add(this.alexaMediator.onAlexaCard().subscribe(new Consumer<TtaResponseCard>() { // from class: com.amazon.alexa.voice.tta.statemachine.SearchWorkflow$registerObservers$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(TtaResponseCard ttaResponseCard) {
                SearchWorkflow.this.getInAppSearchStateMachine().handleGotRenderCardDirective(ttaResponseCard);
            }
        }));
        this.disposable.add(this.alexaMediator.onTextResponse().subscribe(new Consumer<TextResponse>() { // from class: com.amazon.alexa.voice.tta.statemachine.SearchWorkflow$registerObservers$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(TextResponse textResponse) {
                InAppSearchStateMachine inAppSearchStateMachine = SearchWorkflow.this.getInAppSearchStateMachine();
                Intrinsics.checkExpressionValueIsNotNull(textResponse, "textResponse");
                inAppSearchStateMachine.handleGotAvsTextResponse(textResponse);
            }
        }));
        this.disposable.add(this.alexaMediator.onExpectText().subscribe(new Consumer<Object>() { // from class: com.amazon.alexa.voice.tta.statemachine.SearchWorkflow$registerObservers$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchWorkflow.this.getInAppSearchStateMachine().handleGotExpectTextDirective();
            }
        }));
        this.disposable.add(this.simbaMediator.onSimbaSearchResults().subscribe(new Consumer<List<? extends SearchResult>>() { // from class: com.amazon.alexa.voice.tta.statemachine.SearchWorkflow$registerObservers$5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(List<? extends SearchResult> list) {
                accept2((List<SearchResult>) list);
            }

            /* renamed from: accept  reason: avoid collision after fix types in other method */
            public final void accept2(List<SearchResult> searchResult) {
                SimbaMediator simbaMediator;
                InAppSearchStateMachine inAppSearchStateMachine = SearchWorkflow.this.getInAppSearchStateMachine();
                Intrinsics.checkExpressionValueIsNotNull(searchResult, "searchResult");
                simbaMediator = SearchWorkflow.this.simbaMediator;
                inAppSearchStateMachine.handleGotSIMBAResults(searchResult, simbaMediator.getCurrentSIMBACallContext());
            }
        }));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void callSIMBA(@NotNull SIMBACallContext callContext) {
        Intrinsics.checkParameterIsNotNull(callContext, "callContext");
        this.simbaMediator.getSearchResults(callContext);
    }

    @NotNull
    public final CompositeDisposable getDisposable$AlexaMobileAndroidVoice_TTA_release() {
        return this.disposable;
    }

    @NotNull
    public final InAppSearchStateMachine getInAppSearchStateMachine() {
        InAppSearchStateMachine inAppSearchStateMachine = this.inAppSearchStateMachine;
        if (inAppSearchStateMachine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inAppSearchStateMachine");
        }
        return inAppSearchStateMachine;
    }

    public final void initialize(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        InAppSearchStateMachine build = InAppSearchStateMachineBuilder.Companion.createBuilder$default(InAppSearchStateMachineBuilder.Companion, activity, null, 2, null).with(this).build();
        if (build == null) {
            Log.e(TAG, "Unable to instantiate app search state machine!");
            return;
        }
        this.simbaMediator.initialize();
        this.inAppSearchStateMachine = build;
        registerObservers();
        InAppSearchStateMachine inAppSearchStateMachine = this.inAppSearchStateMachine;
        if (inAppSearchStateMachine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inAppSearchStateMachine");
        }
        inAppSearchStateMachine.start();
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void notifyNoSimbaResults() {
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void notifySIMBACallCancelled(@Nullable SIMBACallContext sIMBACallContext) {
        this.simbaMediator.ignoreCurrentSimbaCall();
    }

    public final void release() {
        this.simbaMediator.release();
        this.disposable.clear();
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void renderCardData(@NotNull TtaResponseCard card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        this.alexaMediator.publishCardData(card);
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void showExistingAvsTextResponse(@NotNull TextResponse textResponse) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(textResponse, "textResponse");
        isBlank = StringsKt__StringsJVMKt.isBlank(textResponse.getTitle());
        if (!isBlank) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Showing text response of ");
            outline107.append(textResponse.getTitle());
            outline107.toString();
            this.alexaMediator.showResponse(textResponse.getTitle());
        }
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void showSIMBAResults(@NotNull List<SearchResult> results, @Nullable SIMBACallContext sIMBACallContext) {
        Intrinsics.checkParameterIsNotNull(results, "results");
        this.simbaMediator.showSearchResults(results);
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachineActions
    public void showUserUtterance(@NotNull String text) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(text, "text");
        isBlank = StringsKt__StringsJVMKt.isBlank(text);
        if (!isBlank) {
            this.alexaMediator.sendText(text);
        }
    }
}
