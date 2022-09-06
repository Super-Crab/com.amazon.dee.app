package com.amazon.alexa.voice.tta.statemachine;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.TextResponseMetadata;
import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.metrics.AppSearchEvent;
import com.amazon.alexa.voice.tta.search.FrictionHelper;
import com.amazon.alexa.voice.tta.search.TextPillResultItem;
import com.amazon.alexa.voice.tta.simba.FrictivePromptsResponse;
import com.amazon.alexa.voice.tta.simba.FrictivePromptsSimbaResponse;
import com.amazon.alexa.voice.tta.simba.PromptData;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.tta.simba.SearchSimbaResponse;
import com.amazon.alexa.voice.tta.simba.SimbaServiceClient;
import com.amazon.alexa.voice.tta.simba.SimbaServiceClientKt;
import com.amazon.alexa.voice.tta.simba.Suggestion;
import com.amazon.alexa.voice.tta.simba.SuggestionsSimbaResponse;
import com.amazon.alexa.voice.tta.simba.UpdateInteractionSimbaResponse;
import com.amazon.alexa.voice.tta.statemachine.scxml.SIMBACallContext;
import com.amazon.alexa.voice.tta.typing.TextTtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaMediator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 D2\u00020\u0001:\u0002DEB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010$\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0018\u0010&\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010'\u001a\u00020(J\b\u0010)\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u001c\u001a\u00020%2\u0006\u0010*\u001a\u00020\fJ\u0016\u0010+\u001a\u00020%2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00190\u0015H\u0002J\u0006\u0010-\u001a\u00020%J\u0006\u0010.\u001a\u00020%J\u0012\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001500J\u0012\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u001500J\u0012\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u001500J\u0012\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001500J\u0014\u00104\u001a\u00020%2\f\u00105\u001a\b\u0012\u0004\u0012\u00020!0\u0015J\u0006\u00106\u001a\u00020%J\u0015\u00107\u001a\u00020%2\u0006\u00108\u001a\u000209H\u0000¢\u0006\u0002\b:J\u0014\u0010;\u001a\u00020%2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020!0\u0015J;\u0010=\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010>\u001a\u00020(2#\u0010?\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010(¢\u0006\f\bA\u0012\b\bB\u0012\u0004\b\b(C\u0012\u0004\u0012\u00020%0@R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00150\u00188\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "", "context", "Landroid/content/Context;", "alexaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;", "frictionHelper", "Lcom/amazon/alexa/voice/tta/search/FrictionHelper;", "ttaEventSender", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEventSender;", "(Landroid/content/Context;Lcom/amazon/alexa/voice/tta/statemachine/AlexaMediator;Lcom/amazon/alexa/voice/tta/search/FrictionHelper;Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEventSender;)V", "currentSimbaCallContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "isCurrentCallIgnored", "", "locale", "Ljava/util/Locale;", "pillResults", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "Lcom/amazon/alexa/voice/ui/tta/search/PillResultItem;", "searchResults", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/alexa/voice/ui/tta/search/TtaInChatResultCard;", "searchResults$annotations", "()V", "getSearchResults", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "simbaResultReceiver", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator$SimbaResultReceiver;", "simbaSearchResults", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "simbaSuggestions", "Lcom/amazon/alexa/voice/tta/simba/Suggestion;", "fetchFrictivePrompts", "", "fetchSuggestions", "queryText", "", "getCurrentSIMBACallContext", "callContext", "handleSearchResultItems", "searchResultItems", "ignoreCurrentSimbaCall", "initialize", "onPillResult", "Lio/reactivex/rxjava3/core/Observable;", "onSearchResult", "onSimbaSearchResults", "onSimbaSuggestions", "publishSimbaResults", "simbaResults", "release", "sendMetrics", "ttaEvent", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "sendMetrics$AlexaMobileAndroidVoice_TTA_release", "showSearchResults", "results", "updateInteraction", "resultId", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "response", "Companion", "SimbaResultReceiver", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SimbaMediator {
    public static final Companion Companion = new Companion(null);
    private static final String INCHAT_LINK_RESULT_CATEGORY = "inchat_link_result";
    private static final String PILL_RESULT_CATEGORY = "pill_result";
    private static final String SEARCH_RESULT_CATEGORY = "search_result";
    private static final String TAG = "SimbaMediator";
    private final AlexaMediator alexaMediator;
    private final Context context;
    private SIMBACallContext currentSimbaCallContext;
    private final CompositeDisposable disposable;
    private final FrictionHelper frictionHelper;
    private boolean isCurrentCallIgnored;
    private Locale locale;
    private final BehaviorSubject<List<PillResultItem>> pillResults;
    @NotNull
    private final PublishSubject<List<TtaInChatResultCard>> searchResults;
    private final SimbaResultReceiver simbaResultReceiver;
    private final BehaviorSubject<List<SearchResult>> simbaSearchResults;
    private final BehaviorSubject<List<Suggestion>> simbaSuggestions;
    private final TtaEventSender ttaEventSender;

    /* compiled from: SimbaMediator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator$Companion;", "", "()V", "INCHAT_LINK_RESULT_CATEGORY", "", "PILL_RESULT_CATEGORY", "SEARCH_RESULT_CATEGORY", "TAG", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: SimbaMediator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ\u001a\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0017\u0010\u0011\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0012J\u0017\u0010\u0013\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0014J\u0017\u0010\u0015\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0016J\u0017\u0010\u0017\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0018R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator$SimbaResultReceiver;", "Landroid/os/ResultReceiver;", "simbaMediator", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "(Ljava/lang/ref/WeakReference;)V", "getSimbaMediator", "()Ljava/lang/ref/WeakReference;", "handleApiError", "", "errorCode", "", "handleApiError$AlexaMobileAndroidVoice_TTA_release", "onReceiveResult", "resultCode", "resultData", "Landroid/os/Bundle;", "processFrictiveResponse", "processFrictiveResponse$AlexaMobileAndroidVoice_TTA_release", "processSearchResponse", "processSearchResponse$AlexaMobileAndroidVoice_TTA_release", "processSuggestionResponse", "processSuggestionResponse$AlexaMobileAndroidVoice_TTA_release", "processUpdateInteractionResponse", "processUpdateInteractionResponse$AlexaMobileAndroidVoice_TTA_release", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class SimbaResultReceiver extends ResultReceiver {
        @NotNull
        private final WeakReference<SimbaMediator> simbaMediator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SimbaResultReceiver(@NotNull WeakReference<SimbaMediator> simbaMediator) {
            super(null);
            Intrinsics.checkParameterIsNotNull(simbaMediator, "simbaMediator");
            this.simbaMediator = simbaMediator;
        }

        @NotNull
        public final WeakReference<SimbaMediator> getSimbaMediator() {
            return this.simbaMediator;
        }

        public final void handleApiError$AlexaMobileAndroidVoice_TTA_release(int i) {
            SimbaMediator simbaMediator = this.simbaMediator.get();
            if (simbaMediator != null) {
                Intrinsics.checkExpressionValueIsNotNull(simbaMediator, "simbaMediator.get() ?: return");
                Log.e(SimbaMediator.TAG, "API error occurred, error code is " + i);
                switch (i) {
                    case 1000:
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_ERROR);
                        return;
                    case 1001:
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_FAIL_COUNT);
                        return;
                    case 1002:
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_ERROR);
                        return;
                    case 1003:
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_TIMEOUT);
                        return;
                    default:
                        return;
                }
            }
        }

        @Override // android.os.ResultReceiver
        protected void onReceiveResult(int i, @Nullable Bundle bundle) {
            if (i == 1) {
                processSuggestionResponse$AlexaMobileAndroidVoice_TTA_release(bundle);
            } else if (i == 2) {
                processSearchResponse$AlexaMobileAndroidVoice_TTA_release(bundle);
            } else if (i == 3) {
                processFrictiveResponse$AlexaMobileAndroidVoice_TTA_release(bundle);
            } else if (i != 4) {
                if (bundle == null || bundle.getString(SimbaServiceClientKt.SIMBA_ERROR) == null) {
                    return;
                }
                handleApiError$AlexaMobileAndroidVoice_TTA_release(i);
            } else {
                processUpdateInteractionResponse$AlexaMobileAndroidVoice_TTA_release(bundle);
            }
        }

        public final void processFrictiveResponse$AlexaMobileAndroidVoice_TTA_release(@Nullable Bundle bundle) {
            FrictivePromptsResponse response;
            List<PromptData> frictivePrompts;
            FrictivePromptsResponse response2;
            SimbaMediator simbaMediator = this.simbaMediator.get();
            if (simbaMediator != null) {
                Intrinsics.checkExpressionValueIsNotNull(simbaMediator, "simbaMediator.get() ?: return");
                String str = "processFrictiveResponse: " + bundle;
                if (bundle != null) {
                    bundle.setClassLoader(FrictivePromptsSimbaResponse.class.getClassLoader());
                }
                Integer num = null;
                FrictivePromptsSimbaResponse frictivePromptsSimbaResponse = bundle != null ? (FrictivePromptsSimbaResponse) bundle.getParcelable(SimbaServiceClientKt.SIMBA_FRICATIVE_RESPONSE) : null;
                List<PromptData> frictivePrompts2 = (frictivePromptsSimbaResponse == null || (response2 = frictivePromptsSimbaResponse.getResponse()) == null) ? null : response2.getFrictivePrompts();
                if (frictivePrompts2 != null) {
                    if (frictivePrompts2.isEmpty()) {
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_NO_RESULTS);
                        return;
                    } else {
                        simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_HAS_RESULTS);
                        simbaMediator.frictionHelper.setFrictivePrompts(frictivePrompts2);
                    }
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processFrictiveResponse: size");
                if (frictivePromptsSimbaResponse != null && (response = frictivePromptsSimbaResponse.getResponse()) != null && (frictivePrompts = response.getFrictivePrompts()) != null) {
                    num = Integer.valueOf(frictivePrompts.size());
                }
                outline107.append(num);
                outline107.toString();
            }
        }

        public final void processSearchResponse$AlexaMobileAndroidVoice_TTA_release(@Nullable Bundle bundle) {
            List<SearchResult> searchResults;
            List<SearchResult> searchResults2;
            SimbaMediator simbaMediator = this.simbaMediator.get();
            if (simbaMediator != null) {
                Intrinsics.checkExpressionValueIsNotNull(simbaMediator, "simbaMediator.get() ?: return");
                String str = "processSearchResponse: " + bundle;
                if (simbaMediator.isCurrentCallIgnored) {
                    simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_CANCELLED);
                    return;
                }
                if (bundle != null) {
                    bundle.setClassLoader(SearchSimbaResponse.class.getClassLoader());
                }
                Integer num = null;
                SearchSimbaResponse searchSimbaResponse = bundle != null ? (SearchSimbaResponse) bundle.getParcelable(SimbaServiceClientKt.SIMBA_SEARCH_RESPONSE) : null;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processSearchResponse: results");
                if (searchSimbaResponse != null && (searchResults2 = searchSimbaResponse.getSearchResults()) != null) {
                    num = Integer.valueOf(searchResults2.size());
                }
                outline107.append(num);
                outline107.toString();
                if (searchSimbaResponse == null || (searchResults = searchSimbaResponse.getSearchResults()) == null) {
                    return;
                }
                simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_SUCCESS_COUNT);
                if (searchResults.isEmpty()) {
                    simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_NO_RESULTS);
                    return;
                }
                simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SEARCH_API_HAS_RESULTS);
                simbaMediator.publishSimbaResults(searchResults);
            }
        }

        public final void processSuggestionResponse$AlexaMobileAndroidVoice_TTA_release(@Nullable Bundle bundle) {
            List<Suggestion> suggestions;
            SimbaMediator simbaMediator = this.simbaMediator.get();
            if (simbaMediator != null) {
                Intrinsics.checkExpressionValueIsNotNull(simbaMediator, "simbaMediator.get() ?: return");
                String str = "processSuggestionResponse: " + bundle;
                if (bundle != null) {
                    bundle.setClassLoader(SuggestionsSimbaResponse.class.getClassLoader());
                }
                Integer num = null;
                SuggestionsSimbaResponse suggestionsSimbaResponse = bundle != null ? (SuggestionsSimbaResponse) bundle.getParcelable(SimbaServiceClientKt.SIMBA_SUGGESTION_RESPONSE) : null;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processSuggestionResponse: received: ");
                if (suggestionsSimbaResponse != null && (suggestions = suggestionsSimbaResponse.getSuggestions()) != null) {
                    num = Integer.valueOf(suggestions.size());
                }
                outline107.append(num);
                outline107.toString();
                if (suggestionsSimbaResponse != null) {
                    simbaMediator.sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT);
                    simbaMediator.simbaSuggestions.onNext(suggestionsSimbaResponse.getSuggestions());
                    return;
                }
                Log.w(SimbaMediator.TAG, "processSuggestionResponse: No response for query");
            }
        }

        public final void processUpdateInteractionResponse$AlexaMobileAndroidVoice_TTA_release(@Nullable Bundle bundle) {
            String str = "processUpdateInteractionResponse: " + bundle;
            if (bundle != null) {
                bundle.setClassLoader(UpdateInteractionSimbaResponse.class.getClassLoader());
            }
            String str2 = "processUpdateInteractionResponse: " + (bundle != null ? (UpdateInteractionSimbaResponse) bundle.getParcelable(SimbaServiceClientKt.SIMBA_INTERACTION_RESPONSE) : null);
        }
    }

    public SimbaMediator(@NotNull Context context, @NotNull AlexaMediator alexaMediator, @NotNull FrictionHelper frictionHelper, @NotNull TtaEventSender ttaEventSender) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(alexaMediator, "alexaMediator");
        Intrinsics.checkParameterIsNotNull(frictionHelper, "frictionHelper");
        Intrinsics.checkParameterIsNotNull(ttaEventSender, "ttaEventSender");
        this.context = context;
        this.alexaMediator = alexaMediator;
        this.frictionHelper = frictionHelper;
        this.ttaEventSender = ttaEventSender;
        BehaviorSubject<List<Suggestion>> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.simbaSuggestions = create;
        BehaviorSubject<List<SearchResult>> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.simbaSearchResults = create2;
        BehaviorSubject<List<PillResultItem>> create3 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "BehaviorSubject.create()");
        this.pillResults = create3;
        PublishSubject<List<TtaInChatResultCard>> create4 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "PublishSubject.create()");
        this.searchResults = create4;
        this.disposable = new CompositeDisposable();
        this.simbaResultReceiver = new SimbaResultReceiver(new WeakReference(this));
    }

    private final void handleSearchResultItems(List<? extends TtaInChatResultCard> list) {
        SIMBACallContext currentSIMBACallContext = getCurrentSIMBACallContext();
        TextResponse avsTextResponse = currentSIMBACallContext != null ? currentSIMBACallContext.getAvsTextResponse() : null;
        if (avsTextResponse != null && this.frictionHelper.isFrictive(avsTextResponse)) {
            sendMetrics$AlexaMobileAndroidVoice_TTA_release(AppSearchEvent.VOX_SIMBA_FRICTIVE_UTTERANCE);
            String string = this.context.getResources().getString(R.string.friction_supportive_message);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…ction_supportive_message)");
            this.alexaMediator.showResponse(new TextTtaResponseMessage(string));
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Showing search results for ");
        outline107.append(list.size());
        outline107.append(" items");
        outline107.toString();
        this.searchResults.onNext(list);
    }

    @VisibleForTesting
    public static /* synthetic */ void searchResults$annotations() {
    }

    public final void fetchFrictivePrompts(@Nullable Context context) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("fetchFrictivePrompts with locale: ");
        Object obj = this.locale;
        if (obj == null) {
            obj = "Unknown";
        }
        outline107.append(obj);
        outline107.toString();
        Locale locale = this.locale;
        if (locale == null || context == null) {
            return;
        }
        this.ttaEventSender.sendEvent(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_STARTED);
        SimbaServiceClient.Companion.fetchFricativePrompts(context, this.simbaResultReceiver, locale);
        this.ttaEventSender.sendEvent(AppSearchEvent.VOX_SIMBA_FRICTIVE_PROMPTS_API_CALLED);
    }

    public final void fetchSuggestions(@Nullable Context context, @NotNull String queryText) {
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        StringBuilder sb = new StringBuilder();
        sb.append("fetchSuggestions for ");
        sb.append(queryText);
        sb.append(" with");
        sb.append("text response with locale: ");
        Object obj = this.locale;
        if (obj == null) {
            obj = "Unknown";
        }
        sb.append(obj);
        sb.toString();
        Locale locale = this.locale;
        if (locale == null || context == null) {
            return;
        }
        this.ttaEventSender.sendEvent(AppSearchEvent.VOX_SIMBA_SUGGESTIONS_CALL_COUNT);
        SimbaServiceClient.Companion.fetchSuggestions(context, this.simbaResultReceiver, queryText, locale);
    }

    @Nullable
    public final SIMBACallContext getCurrentSIMBACallContext() {
        return this.currentSimbaCallContext;
    }

    @NotNull
    public final PublishSubject<List<TtaInChatResultCard>> getSearchResults() {
        return this.searchResults;
    }

    public final void ignoreCurrentSimbaCall() {
        this.isCurrentCallIgnored = true;
    }

    public final void initialize() {
        this.disposable.add(this.alexaMediator.onAlexaLocale().subscribe(new Consumer<Locale>() { // from class: com.amazon.alexa.voice.tta.statemachine.SimbaMediator$initialize$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Locale locale) {
                Context context;
                SimbaMediator.this.locale = locale;
                SimbaMediator simbaMediator = SimbaMediator.this;
                context = simbaMediator.context;
                simbaMediator.fetchFrictivePrompts(context);
            }
        }));
    }

    @NotNull
    public final Observable<List<PillResultItem>> onPillResult() {
        return this.pillResults;
    }

    @NotNull
    public final Observable<List<TtaInChatResultCard>> onSearchResult() {
        return this.searchResults;
    }

    @NotNull
    public final Observable<List<SearchResult>> onSimbaSearchResults() {
        return this.simbaSearchResults;
    }

    @NotNull
    public final Observable<List<Suggestion>> onSimbaSuggestions() {
        return this.simbaSuggestions;
    }

    public final void publishSimbaResults(@NotNull List<SearchResult> simbaResults) {
        Intrinsics.checkParameterIsNotNull(simbaResults, "simbaResults");
        this.simbaSearchResults.onNext(simbaResults);
    }

    public final void release() {
        this.disposable.dispose();
    }

    public final void sendMetrics$AlexaMobileAndroidVoice_TTA_release(@NotNull TtaEvent ttaEvent) {
        Intrinsics.checkParameterIsNotNull(ttaEvent, "ttaEvent");
        this.ttaEventSender.sendEvent(ttaEvent);
    }

    public final void showSearchResults(@NotNull List<SearchResult> results) {
        Intrinsics.checkParameterIsNotNull(results, "results");
        if (this.isCurrentCallIgnored) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Showing search results with ");
        outline107.append(results.size());
        outline107.append(" items");
        outline107.toString();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (Object obj : results) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            SearchResult searchResult = (SearchResult) obj;
            String category = searchResult.getCategory();
            Locale locale = Locale.ROOT;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
            if (category == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = category.toLowerCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            int hashCode = lowerCase.hashCode();
            if (hashCode == 361879744) {
                if (lowerCase.equals(INCHAT_LINK_RESULT_CATEGORY)) {
                    arrayList2.add(TtaInChatResultCardParser.Companion.constructTtaInChatResultRenderedCard(searchResult, i, results.size()));
                }
                Log.e(TAG, searchResult.getCategory() + " category is not supported");
            } else if (hashCode != 533275107) {
                if (hashCode == 1425879700 && lowerCase.equals(SEARCH_RESULT_CATEGORY)) {
                    arrayList2.add(TtaInChatResultCardParser.Companion.constructTtaInChatResultRenderedCard(searchResult, i, results.size()));
                }
                Log.e(TAG, searchResult.getCategory() + " category is not supported");
            } else {
                if (lowerCase.equals(PILL_RESULT_CATEGORY)) {
                    arrayList.add(new TextPillResultItem(searchResult.getTitle(), TextPillResultItem.Companion.getResultType(searchResult.getActionType()), searchResult.getActionData(), searchResult.getResultId(), null, 16, null));
                }
                Log.e(TAG, searchResult.getCategory() + " category is not supported");
            }
            i = i2;
        }
        if (!arrayList2.isEmpty()) {
            handleSearchResultItems(arrayList2);
        }
        if (!(!arrayList.isEmpty())) {
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Showing pill results for ");
        outline1072.append(results.size());
        outline1072.append(" items");
        outline1072.toString();
        this.pillResults.onNext(arrayList);
    }

    public final void updateInteraction(@NotNull Context context, @NotNull String resultId, @NotNull Function1<? super String, Unit> callback) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(resultId, "resultId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        StringBuilder sb = new StringBuilder();
        sb.append("updateInteraction with result id: ");
        sb.append(resultId);
        sb.append(" and with locale: ");
        Object obj = this.locale;
        if (obj == null) {
            obj = "Unknown";
        }
        sb.append(obj);
        sb.toString();
        Locale locale = this.locale;
        if (locale != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(resultId);
            if (!(!isBlank)) {
                return;
            }
            SimbaServiceClient.Companion.updateInteraction(context, this.simbaResultReceiver, resultId, locale);
        }
    }

    public final void getSearchResults(@NotNull SIMBACallContext callContext) {
        TextResponseMetadata metadata;
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(callContext, "callContext");
        this.isCurrentCallIgnored = false;
        this.currentSimbaCallContext = callContext;
        TextResponse avsTextResponse = callContext.getAvsTextResponse();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Getting search results for ");
        outline107.append(avsTextResponse.getTitle());
        outline107.append(" with ");
        outline107.append("text response with locale: ");
        Object obj = this.locale;
        if (obj == null) {
            obj = "Unknown";
        }
        outline107.append(obj);
        outline107.toString();
        Locale locale = this.locale;
        if (locale == null || (metadata = avsTextResponse.getMetadata()) == null) {
            return;
        }
        this.ttaEventSender.sendEvent(AppSearchEvent.VOX_SIMBA_SEARCH_API_CALL_COUNT);
        SimbaServiceClient.Companion companion = SimbaServiceClient.Companion;
        Context context = this.context;
        SimbaResultReceiver simbaResultReceiver = new SimbaResultReceiver(new WeakReference(this));
        String utterance = callContext.getUtterance();
        isBlank = StringsKt__StringsJVMKt.isBlank(avsTextResponse.getTitle());
        String token = metadata.getToken();
        Intrinsics.checkExpressionValueIsNotNull(token, "it.token");
        companion.fetchSearchResults(context, simbaResultReceiver, utterance, locale, isBlank, token, metadata.getPromptId(), metadata.getVariant(), metadata.getNamespace());
        this.ttaEventSender.sendEvent(AppSearchEvent.VOX_SIMBA_SEARCH_API_CALLED);
    }
}
