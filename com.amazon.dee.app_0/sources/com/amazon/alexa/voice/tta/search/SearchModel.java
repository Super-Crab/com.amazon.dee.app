package com.amazon.alexa.voice.tta.search;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.permissions.Permission;
import com.amazon.alexa.voice.tta.permissions.PermissionType;
import com.amazon.alexa.voice.tta.permissions.PermissionsUtil;
import com.amazon.alexa.voice.tta.statemachine.SimbaMediator;
import com.amazon.alexa.voice.ui.onedesign.tta.ItemRouteImpl;
import com.amazon.alexa.voice.ui.onedesign.tta.SearchResultItem;
import com.amazon.alexa.voice.ui.tta.search.ActionType;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.PillResultItem;
import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
/* compiled from: SearchModel.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\"\u001a\u00020#H\u0002J\u0006\u0010%\u001a\u00020&J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0(J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110(J\u000e\u0010*\u001a\u00020&2\u0006\u0010\"\u001a\u00020#J\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000f0(J\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00140(J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000f0(J\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140(J\b\u0010.\u001a\u00020&H\u0002J\u0006\u0010/\u001a\u00020&J\b\u00100\u001a\u00020&H\u0002J\u0010\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00140\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/SearchModel;", "", "context", "Landroid/content/Context;", "simbaMediator", "Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;", "permissionsUtil", "Lcom/amazon/alexa/voice/tta/permissions/PermissionsUtil;", "(Landroid/content/Context;Lcom/amazon/alexa/voice/tta/statemachine/SimbaMediator;Lcom/amazon/alexa/voice/tta/permissions/PermissionsUtil;)V", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getDisposable$AlexaMobileAndroidVoice_TTA_release", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "externalRouteRequest", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "permissionObservable", "Lcom/amazon/alexa/voice/tta/permissions/Permission;", "pillResults", "Lio/reactivex/rxjava3/subjects/ReplaySubject;", "", "Lcom/amazon/alexa/voice/ui/tta/search/PillResultItem;", "routeRequest", "searchResultList", "Ljava/util/ArrayList;", "Lcom/amazon/alexa/voice/ui/tta/search/TtaInChatResultCard;", "Lkotlin/collections/ArrayList;", "searchResults", "textRequest", "getPillHints", "count", "", "getRoute", "Lcom/amazon/alexa/voice/ui/tta/search/ItemRoute;", "resultItem", "Lcom/amazon/alexa/voice/ui/tta/search/ResultItem;", "getText", "initialize", "", "onExternalRouteRequest", "Lio/reactivex/rxjava3/core/Observable;", "onPermissionRequired", "onResultSelected", "onRouteRequest", "onSearchResults", "onTextRequest", "processSearchResult", "release", "showSearchResult", "updateInteractionResponse", "resultId", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchModel {
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_HINTS = 5;
    public static final int SEARCH_RESULT_THRESHOLD = 8;
    private static final String TAG = "SearchModel";
    private final Context context;
    @NotNull
    private final CompositeDisposable disposable;
    private final BehaviorSubject<String> externalRouteRequest;
    private final BehaviorSubject<Permission> permissionObservable;
    private final PermissionsUtil permissionsUtil;
    private final ReplaySubject<List<PillResultItem>> pillResults;
    private final BehaviorSubject<String> routeRequest;
    private final ArrayList<TtaInChatResultCard> searchResultList;
    private final BehaviorSubject<List<TtaInChatResultCard>> searchResults;
    private final SimbaMediator simbaMediator;
    private final BehaviorSubject<String> textRequest;

    /* compiled from: SearchModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/SearchModel$Companion;", "", "()V", "DEFAULT_HINTS", "", "DEFAULT_HINTS$annotations", "SEARCH_RESULT_THRESHOLD", "TAG", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void DEFAULT_HINTS$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActionType.values().length];

        static {
            $EnumSwitchMapping$0[ActionType.IN_APP.ordinal()] = 1;
            $EnumSwitchMapping$0[ActionType.WEB.ordinal()] = 2;
        }
    }

    @Inject
    public SearchModel(@NotNull Context context, @NotNull SimbaMediator simbaMediator, @NotNull PermissionsUtil permissionsUtil) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(simbaMediator, "simbaMediator");
        Intrinsics.checkParameterIsNotNull(permissionsUtil, "permissionsUtil");
        this.context = context;
        this.simbaMediator = simbaMediator;
        this.permissionsUtil = permissionsUtil;
        ReplaySubject<List<PillResultItem>> create = ReplaySubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "ReplaySubject.create()");
        this.pillResults = create;
        BehaviorSubject<List<TtaInChatResultCard>> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.searchResults = create2;
        BehaviorSubject<String> create3 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "BehaviorSubject.create()");
        this.routeRequest = create3;
        BehaviorSubject<String> create4 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "BehaviorSubject.create()");
        this.externalRouteRequest = create4;
        BehaviorSubject<String> create5 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create5, "BehaviorSubject.create()");
        this.textRequest = create5;
        BehaviorSubject<Permission> create6 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create6, "BehaviorSubject.create()");
        this.permissionObservable = create6;
        this.disposable = new CompositeDisposable();
        this.searchResultList = new ArrayList<>();
    }

    private final List<PillResultItem> getPillHints(int i) {
        List list;
        List shuffled;
        List<String> slice;
        int collectionSizeOrDefault;
        String[] stringArray = this.context.getResources().getStringArray(R.array.default_pill_hints);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "context.resources.getStr…array.default_pill_hints)");
        list = ArraysKt___ArraysKt.toList(stringArray);
        shuffled = CollectionsKt__MutableCollectionsJVMKt.shuffled(list);
        slice = CollectionsKt___CollectionsKt.slice((List) shuffled, new IntRange(0, Math.min(list.size(), i) - 1));
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(slice, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String pillText : slice) {
            Intrinsics.checkExpressionValueIsNotNull(pillText, "pillText");
            arrayList.add(new TextPillResultItem(pillText, 2, null, null, false, 12, null));
        }
        return arrayList;
    }

    private final ItemRoute getRoute(ResultItem resultItem) {
        if (resultItem instanceof PillResultItem) {
            return ItemRouteImpl.create(((PillResultItem) resultItem).getItemRoute());
        }
        if (resultItem instanceof SearchResultItem) {
            return ((SearchResultItem) resultItem).getItemRoute();
        }
        Log.e(TAG, "Unsupported result item type selected");
        return null;
    }

    private final String getText(ResultItem resultItem) {
        if (resultItem instanceof PillResultItem) {
            return ((PillResultItem) resultItem).getItemText();
        }
        if (resultItem instanceof SearchResultItem) {
            return ((SearchResultItem) resultItem).getItemText();
        }
        Log.e(TAG, "Unsupported result item type selected");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processSearchResult() {
        if (this.searchResultList.size() > 0) {
            Permission requiredPermission = this.permissionsUtil.getRequiredPermission(this.searchResultList.get(0));
            Intrinsics.checkExpressionValueIsNotNull(requiredPermission, "requiredPermission");
            if (requiredPermission.getType() != PermissionType.NONE) {
                this.permissionObservable.onNext(requiredPermission);
            }
            showSearchResult();
        }
    }

    private final void showSearchResult() {
        List<PillResultItem> listOf;
        if (this.searchResultList.size() <= 8) {
            this.searchResults.onNext(this.searchResultList);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.searchResultList.subList(0, 8));
        this.searchResults.onNext(arrayList);
        this.searchResultList.removeAll(arrayList);
        ReplaySubject<List<PillResultItem>> replaySubject = this.pillResults;
        String string = this.context.getResources().getString(R.string.tta_show_more);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…g(R.string.tta_show_more)");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new TextPillResultItem(string, 0, null, null, null, 28, null));
        replaySubject.onNext(listOf);
    }

    private final void updateInteractionResponse(String str) {
        this.simbaMediator.updateInteraction(this.context, str, SearchModel$updateInteractionResponse$1.INSTANCE);
    }

    @NotNull
    public final CompositeDisposable getDisposable$AlexaMobileAndroidVoice_TTA_release() {
        return this.disposable;
    }

    public final void initialize() {
        this.pillResults.onNext(getPillHints(5));
        this.disposable.clear();
        this.disposable.add(this.simbaMediator.onPillResult().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<? extends PillResultItem>>() { // from class: com.amazon.alexa.voice.tta.search.SearchModel$initialize$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(List<? extends PillResultItem> list) {
                ReplaySubject replaySubject;
                replaySubject = SearchModel.this.pillResults;
                replaySubject.onNext(list);
            }
        }));
        this.disposable.add(this.simbaMediator.onSearchResult().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<? extends TtaInChatResultCard>>() { // from class: com.amazon.alexa.voice.tta.search.SearchModel$initialize$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(List<? extends TtaInChatResultCard> list) {
                ArrayList arrayList;
                ArrayList arrayList2;
                arrayList = SearchModel.this.searchResultList;
                arrayList.clear();
                arrayList2 = SearchModel.this.searchResultList;
                arrayList2.addAll(list);
                SearchModel.this.processSearchResult();
            }
        }));
    }

    @NotNull
    public final Observable<String> onExternalRouteRequest() {
        return this.externalRouteRequest;
    }

    @NotNull
    public final Observable<Permission> onPermissionRequired() {
        return this.permissionObservable;
    }

    public final void onResultSelected(@NotNull ResultItem resultItem) {
        ActionType routeType;
        Intrinsics.checkParameterIsNotNull(resultItem, "resultItem");
        int type = resultItem.getType();
        if (type == 0) {
            showSearchResult();
        } else if (type != 1) {
            if (type != 2) {
                Log.e(TAG, "Unsupported result item type selected");
                return;
            }
            String text = getText(resultItem);
            if (text != null) {
                this.textRequest.onNext(text);
            }
            String id = resultItem.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "resultItem.id");
            updateInteractionResponse(id);
        } else {
            ItemRoute route = getRoute(resultItem);
            if (route != null && (routeType = route.getRouteType()) != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[routeType.ordinal()];
                if (i == 1) {
                    this.routeRequest.onNext(route.getRouteLink());
                } else if (i == 2) {
                    this.externalRouteRequest.onNext(route.getRouteLink());
                }
            }
            String id2 = resultItem.getId();
            Intrinsics.checkExpressionValueIsNotNull(id2, "resultItem.id");
            updateInteractionResponse(id2);
        }
    }

    @NotNull
    public final Observable<String> onRouteRequest() {
        return this.routeRequest;
    }

    @NotNull
    public final Observable<List<TtaInChatResultCard>> onSearchResults() {
        return this.searchResults;
    }

    @NotNull
    public final Observable<String> onTextRequest() {
        return this.textRequest;
    }

    @NotNull
    public final Observable<List<PillResultItem>> pillResults() {
        return this.pillResults;
    }

    public final void release() {
        this.disposable.clear();
    }
}
