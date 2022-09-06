package com.amazon.alexa.voice.tta.suggestions;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis;
import com.amazon.alexa.voice.tta.simba.Suggestion;
import com.amazon.alexa.voice.tta.simba.SuggestionsSimbaResponse;
import com.amazon.alexa.voice.tta.statemachine.SimbaMediator;
import com.amazon.alexa.voice.ui.suggestions.SuggestionAction;
import com.amazon.alexa.voice.ui.suggestions.SuggestionItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TtaSuggestionModel.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 +2\u00020\u0001:\u0002+,B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001bJ\u0018\u0010\u001c\u001a\u00020\u00152\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0012H\u0007J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001bJ \u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000eJ\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001bJ\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0012H\u0002J\u0006\u0010&\u001a\u00020\u0015J\u0016\u0010'\u001a\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u001bJ\u0010\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u000eH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n \u000b*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionModel;", "", "simbaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "context", "Landroid/content/Context;", "sdkApis", "Lcom/amazon/alexa/voice/tta/sdk/AlexaClientSdkApis;", "(Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;Landroid/content/Context;Lcom/amazon/alexa/voice/tta/sdk/AlexaClientSdkApis;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "kotlin.jvm.PlatformType", "externalRoute", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "inAppRoute", "suggestionUtterance", "suggestionsList", "", "Lcom/amazon/alexa/voice/ui/suggestions/SuggestionItem;", "getSuggestions", "", "queryText", "initialize", "isAppSearchEnabled", "", "onExternalRoute", "Lio/reactivex/rxjava3/core/Observable;", "onGetSuggestionsResponse", "suggestions", "Lcom/amazon/alexa/voice/tta/simba/Suggestion;", "onInAppRoute", "onSuggestionSelected", JsonFields.ACTION_TYPE, "action", "id", "onUtterance", "prepareSuggestionsForUi", "release", "showSuggestions", "suggestionItems", "updateInteractionResponse", "suggestionId", "Companion", "SuggestionResultReceiver", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class TtaSuggestionModel {
    public static final int MAX_SUGGESTIONS = 5;
    private final CompositeDisposable compositeDisposable;
    private final Context context;
    private final BehaviorSubject<String> externalRoute;
    private final BehaviorSubject<String> inAppRoute;
    private final SimbaMediator simbaMediator;
    private final BehaviorSubject<String> suggestionUtterance;
    private final BehaviorSubject<List<SuggestionItem>> suggestionsList;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = TtaSuggestionModel.class.getSimpleName();

    /* compiled from: TtaSuggestionModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionModel$Companion;", "", "()V", "MAX_SUGGESTIONS", "", "MAX_SUGGESTIONS$annotations", "TAG", "", "kotlin.jvm.PlatformType", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void MAX_SUGGESTIONS$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: TtaSuggestionModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionModel$SuggestionResultReceiver;", "Landroid/os/ResultReceiver;", "queryText", "", "suggestionModel", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionModel;", "(Ljava/lang/String;Ljava/lang/ref/WeakReference;)V", "getQueryText", "()Ljava/lang/String;", "getSuggestionModel", "()Ljava/lang/ref/WeakReference;", "onReceiveResult", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class SuggestionResultReceiver extends ResultReceiver {
        @Nullable
        private final String queryText;
        @NotNull
        private final WeakReference<TtaSuggestionModel> suggestionModel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SuggestionResultReceiver(@Nullable String str, @NotNull WeakReference<TtaSuggestionModel> suggestionModel) {
            super(null);
            Intrinsics.checkParameterIsNotNull(suggestionModel, "suggestionModel");
            this.queryText = str;
            this.suggestionModel = suggestionModel;
        }

        @Nullable
        public final String getQueryText() {
            return this.queryText;
        }

        @NotNull
        public final WeakReference<TtaSuggestionModel> getSuggestionModel() {
            return this.suggestionModel;
        }

        @Override // android.os.ResultReceiver
        protected void onReceiveResult(int i, @Nullable Bundle bundle) {
            List<Suggestion> suggestions;
            String str = TtaSuggestionModel.TAG;
            Log.e(str, "onReceiveResult: " + bundle);
            if (bundle != null) {
                bundle.setClassLoader(Suggestion.class.getClassLoader());
            }
            Integer num = null;
            SuggestionsSimbaResponse suggestionsSimbaResponse = bundle != null ? (SuggestionsSimbaResponse) bundle.getParcelable("suggestionResponse") : null;
            String str2 = TtaSuggestionModel.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onReceiveResult: received: ");
            if (suggestionsSimbaResponse != null && (suggestions = suggestionsSimbaResponse.getSuggestions()) != null) {
                num = Integer.valueOf(suggestions.size());
            }
            outline107.append(num);
            Log.e(str2, outline107.toString());
            if (suggestionsSimbaResponse == null) {
                String str3 = TtaSuggestionModel.TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onReceiveResult: No response for query ");
                outline1072.append(this.queryText);
                Log.w(str3, outline1072.toString());
                return;
            }
            TtaSuggestionModel ttaSuggestionModel = this.suggestionModel.get();
            if (ttaSuggestionModel == null) {
                return;
            }
            ttaSuggestionModel.onGetSuggestionsResponse(suggestionsSimbaResponse.getSuggestions());
        }
    }

    @Inject
    public TtaSuggestionModel(@NotNull SimbaMediator simbaMediator, @NotNull Context context, @NotNull AlexaClientSdkApis sdkApis) {
        Intrinsics.checkParameterIsNotNull(simbaMediator, "simbaMediator");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(sdkApis, "sdkApis");
        BehaviorSubject<List<SuggestionItem>> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.suggestionsList = create;
        BehaviorSubject<String> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.suggestionUtterance = create2;
        BehaviorSubject<String> create3 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "BehaviorSubject.create()");
        this.inAppRoute = create3;
        BehaviorSubject<String> create4 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "BehaviorSubject.create()");
        this.externalRoute = create4;
        this.compositeDisposable = new CompositeDisposable();
        this.simbaMediator = simbaMediator;
        this.context = context.getApplicationContext();
    }

    private final List<SuggestionItem> prepareSuggestionsForUi(List<Suggestion> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Suggestion suggestion : list) {
                TtaSuggestionItem create = TtaSuggestionItem.create(suggestion);
                Intrinsics.checkExpressionValueIsNotNull(create, "TtaSuggestionItem.create(suggestion)");
                arrayList.add(create);
            }
        }
        return arrayList;
    }

    private final void showSuggestions(List<? extends SuggestionItem> list) {
        this.suggestionsList.onNext(list);
    }

    private final void updateInteractionResponse(String str) {
        SimbaMediator simbaMediator = this.simbaMediator;
        Context context = this.context;
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        simbaMediator.updateInteraction(context, str, TtaSuggestionModel$updateInteractionResponse$1.INSTANCE);
    }

    public final void getSuggestions(@Nullable String str) {
        GeneratedOutlineSupport1.outline158("getSuggestions called with query: ", str);
        if (str != null) {
            this.simbaMediator.fetchSuggestions(this.context, str);
        }
    }

    public final void initialize(boolean z) {
        if (z) {
            CompositeDisposable compositeDisposable = this.compositeDisposable;
            Observable<List<Suggestion>> observeOn = this.simbaMediator.onSimbaSuggestions().observeOn(AndroidSchedulers.mainThread());
            final TtaSuggestionModel$initialize$1 ttaSuggestionModel$initialize$1 = new TtaSuggestionModel$initialize$1(this);
            compositeDisposable.add(observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.tta.suggestions.TtaSuggestionModel$sam$io_reactivex_rxjava3_functions_Consumer$0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final /* synthetic */ void accept(Object obj) {
                    Intrinsics.checkExpressionValueIsNotNull(Function1.this.mo12165invoke(obj), "invoke(...)");
                }
            }));
        }
    }

    @NotNull
    public final Observable<String> onExternalRoute() {
        return this.externalRoute;
    }

    @VisibleForTesting
    public final void onGetSuggestionsResponse(@Nullable List<Suggestion> list) {
        showSuggestions(prepareSuggestionsForUi(list != null ? list.subList(0, Math.min(5, list != null ? list.size() : 0)) : null));
    }

    @NotNull
    public final Observable<String> onInAppRoute() {
        return this.inAppRoute;
    }

    public final void onSuggestionSelected(@Nullable String str, @NotNull String action, @NotNull String id) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(id, "id");
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1038134325) {
                if (hashCode != 646424913) {
                    if (hashCode == 1979887117 && str.equals(SuggestionAction.APP_PAGE)) {
                        this.inAppRoute.onNext(action);
                        updateInteractionResponse(id);
                        return;
                    }
                } else if (str.equals(SuggestionAction.UTTERANCE)) {
                    this.suggestionUtterance.onNext(action);
                    updateInteractionResponse(id);
                    return;
                }
            } else if (str.equals(SuggestionAction.EXTERNAL)) {
                this.externalRoute.onNext(action);
                updateInteractionResponse(id);
                return;
            }
        }
        Log.e(TAG, "Unsupported suggestion item clicked");
    }

    @NotNull
    public final Observable<String> onUtterance() {
        return this.suggestionUtterance;
    }

    public final void release() {
        this.compositeDisposable.clear();
    }

    @NotNull
    public final Observable<List<SuggestionItem>> suggestionsList() {
        return this.suggestionsList;
    }
}
