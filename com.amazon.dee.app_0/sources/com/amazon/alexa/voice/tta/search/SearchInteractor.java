package com.amazon.alexa.voice.tta.search;

import android.app.Activity;
import com.amazon.alexa.voice.tta.permissions.Permission;
import com.amazon.alexa.voice.tta.permissions.PermissionType;
import com.amazon.alexa.voice.tta.permissions.PermissionsUtil;
import com.amazon.alexa.voice.tta.statemachine.SearchWorkflow;
import com.amazon.alexa.voice.tta.typing.PersistentStorage;
import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
import com.amazon.alexa.voice.ui.tta.search.TtaResultListener;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SearchInteractor.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0#J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0#J\u0006\u0010(\u001a\u00020\u0018J\u000e\u0010)\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/SearchInteractor;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "searchModel", "Lcom/amazon/alexa/voice/tta/search/SearchModel;", "searchWorkflow", "Lcom/amazon/alexa/voice/tta/statemachine/SearchWorkflow;", "ttaResultHandler", "Lcom/amazon/alexa/voice/tta/search/DefaultTtaResultHandler;", "ttaNavigator", "Lcom/amazon/alexa/voice/ui/tta/TtaNavigator;", "persistentStorage", "Lcom/amazon/alexa/voice/tta/typing/PersistentStorage;", "permissionsUtil", "Lcom/amazon/alexa/voice/tta/permissions/PermissionsUtil;", "(Landroid/app/Activity;Lcom/amazon/alexa/voice/tta/search/SearchModel;Lcom/amazon/alexa/voice/tta/statemachine/SearchWorkflow;Lcom/amazon/alexa/voice/tta/search/DefaultTtaResultHandler;Lcom/amazon/alexa/voice/ui/tta/TtaNavigator;Lcom/amazon/alexa/voice/tta/typing/PersistentStorage;Lcom/amazon/alexa/voice/tta/permissions/PermissionsUtil;)V", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getDisposable$AlexaMobileAndroidVoice_TTA_release", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "ttaResponseListener", "Lcom/amazon/alexa/voice/ui/tta/TtaMessageResponseListener;", "initializeData", "", "initializeHandler", "onPermissionRequired", "permission", "Lcom/amazon/alexa/voice/tta/permissions/Permission;", "onResultSelected", "itemRoute", "Lcom/amazon/alexa/voice/ui/tta/search/ItemRoute;", "resultItem", "Lcom/amazon/alexa/voice/ui/tta/search/ResultItem;", "onSearchResults", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/amazon/alexa/voice/ui/tta/search/TtaInChatResultCard;", "onTextRequest", "", "releaseSubscription", "setResponseListener", "setResultListener", "ttaResultListener", "Lcom/amazon/alexa/voice/ui/tta/search/TtaResultListener;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchInteractor {
    private final Activity activity;
    @NotNull
    private final CompositeDisposable disposable;
    private final PermissionsUtil permissionsUtil;
    private final PersistentStorage persistentStorage;
    private final SearchModel searchModel;
    private final SearchWorkflow searchWorkflow;
    private final TtaNavigator ttaNavigator;
    private TtaMessageResponseListener ttaResponseListener;
    private final DefaultTtaResultHandler ttaResultHandler;

    @Inject
    public SearchInteractor(@NotNull Activity activity, @NotNull SearchModel searchModel, @NotNull SearchWorkflow searchWorkflow, @NotNull DefaultTtaResultHandler ttaResultHandler, @NotNull TtaNavigator ttaNavigator, @NotNull PersistentStorage persistentStorage, @NotNull PermissionsUtil permissionsUtil) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(searchModel, "searchModel");
        Intrinsics.checkParameterIsNotNull(searchWorkflow, "searchWorkflow");
        Intrinsics.checkParameterIsNotNull(ttaResultHandler, "ttaResultHandler");
        Intrinsics.checkParameterIsNotNull(ttaNavigator, "ttaNavigator");
        Intrinsics.checkParameterIsNotNull(persistentStorage, "persistentStorage");
        Intrinsics.checkParameterIsNotNull(permissionsUtil, "permissionsUtil");
        this.activity = activity;
        this.searchModel = searchModel;
        this.searchWorkflow = searchWorkflow;
        this.ttaResultHandler = ttaResultHandler;
        this.ttaNavigator = ttaNavigator;
        this.persistentStorage = persistentStorage;
        this.permissionsUtil = permissionsUtil;
        this.disposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPermissionRequired(Permission permission) {
        boolean isBlank;
        if (permission.getType() == PermissionType.LOCATION) {
            TtaResponseMessage message = this.permissionsUtil.getLocationPermissionMessage(this.activity, this.persistentStorage);
            Intrinsics.checkExpressionValueIsNotNull(message, "message");
            String message2 = message.getMessage();
            Intrinsics.checkExpressionValueIsNotNull(message2, "message.message");
            isBlank = StringsKt__StringsJVMKt.isBlank(message2);
            if (!(!isBlank)) {
                return;
            }
            TtaMessageResponseListener ttaMessageResponseListener = this.ttaResponseListener;
            if (ttaMessageResponseListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttaResponseListener");
            }
            ttaMessageResponseListener.onMessage(this.permissionsUtil.getLocationPermissionMessage(this.activity, this.persistentStorage));
        }
    }

    @NotNull
    public final CompositeDisposable getDisposable$AlexaMobileAndroidVoice_TTA_release() {
        return this.disposable;
    }

    public final void initializeData() {
        this.searchModel.initialize();
        this.searchWorkflow.initialize(this.activity);
    }

    public final void initializeHandler() {
        this.ttaResultHandler.initialize(this);
        this.disposable.clear();
        this.disposable.add(this.searchModel.onRouteRequest().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.amazon.alexa.voice.tta.search.SearchInteractor$initializeHandler$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String str) {
                TtaNavigator ttaNavigator;
                Activity activity;
                ttaNavigator = SearchInteractor.this.ttaNavigator;
                activity = SearchInteractor.this.activity;
                ttaNavigator.navigateInApp(activity, str, null);
            }
        }));
        this.disposable.add(this.searchModel.onExternalRouteRequest().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.amazon.alexa.voice.tta.search.SearchInteractor$initializeHandler$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String str) {
                TtaNavigator ttaNavigator;
                Activity activity;
                ttaNavigator = SearchInteractor.this.ttaNavigator;
                activity = SearchInteractor.this.activity;
                ttaNavigator.navigateToExternalUrl(activity, str);
            }
        }));
    }

    public final void onResultSelected(@NotNull ResultItem resultItem) {
        Intrinsics.checkParameterIsNotNull(resultItem, "resultItem");
        this.searchModel.onResultSelected(resultItem);
    }

    @NotNull
    public final Observable<List<TtaInChatResultCard>> onSearchResults() {
        return this.searchModel.onSearchResults();
    }

    @NotNull
    public final Observable<String> onTextRequest() {
        return this.searchModel.onTextRequest();
    }

    public final void releaseSubscription() {
        this.disposable.clear();
        this.searchModel.release();
        this.searchWorkflow.release();
    }

    public final void setResponseListener(@NotNull TtaMessageResponseListener ttaResponseListener) {
        Intrinsics.checkParameterIsNotNull(ttaResponseListener, "ttaResponseListener");
        this.ttaResponseListener = ttaResponseListener;
        this.disposable.add(this.searchModel.onSearchResults().subscribe(new SearchInteractor$sam$io_reactivex_rxjava3_functions_Consumer$0(new SearchInteractor$setResponseListener$1(ttaResponseListener))));
        this.disposable.add(this.searchModel.onPermissionRequired().subscribe(new SearchInteractor$sam$io_reactivex_rxjava3_functions_Consumer$0(new SearchInteractor$setResponseListener$2(this))));
    }

    public final void setResultListener(@NotNull TtaResultListener ttaResultListener) {
        Intrinsics.checkParameterIsNotNull(ttaResultListener, "ttaResultListener");
        this.disposable.add(this.searchModel.pillResults().subscribe(new SearchInteractor$sam$io_reactivex_rxjava3_functions_Consumer$0(new SearchInteractor$setResultListener$1(ttaResultListener))));
    }

    public final void onResultSelected(@NotNull ItemRoute itemRoute) {
        Intrinsics.checkParameterIsNotNull(itemRoute, "itemRoute");
        if (Intrinsics.areEqual(itemRoute.getRouteLink(), PermissionsUtil.LOCATION_PERMISSION_URI)) {
            this.permissionsUtil.requestLocationPermissions(this.activity, this.persistentStorage);
        } else {
            this.ttaNavigator.navigateInApp(this.activity, itemRoute.getRouteLink(), null);
        }
    }
}
