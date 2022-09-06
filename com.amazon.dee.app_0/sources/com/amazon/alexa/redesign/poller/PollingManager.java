package com.amazon.alexa.redesign.poller;

import android.util.Log;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class PollingManager {
    private static final String TAG = "PollingManager";
    ActionFactory actionFactory;
    NetworkApi networkApi;
    HomeContract.OEInteractor oeInteractor;
    PollerApi poller;
    ViewApi viewApi;

    /* loaded from: classes10.dex */
    public interface NetworkApi {
        Single<JSONObject> getContent(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface PollerApi {
        void startPolling();

        void updatePollingData(List<CardModel> list);
    }

    /* loaded from: classes10.dex */
    public interface ViewApi {
        void updateViewOnTick(List<CardModel> list, int i);
    }

    public void init(PollerApi pollerApi, NetworkApi networkApi, ViewApi viewApi, ActionFactory actionFactory, HomeContract.OEInteractor oEInteractor) {
        this.poller = pollerApi;
        this.networkApi = networkApi;
        this.viewApi = viewApi;
        this.actionFactory = actionFactory;
        this.oeInteractor = oEInteractor;
    }

    public void onGetContentPollResponse(JSONObject jSONObject, int i) {
        List<CardModel> fromRawHomeCards = HomeCardsProducer.fromRawHomeCards(jSONObject, this.actionFactory, this.oeInteractor, true);
        this.viewApi.updateViewOnTick(fromRawHomeCards, i);
        this.poller.updatePollingData(fromRawHomeCards);
    }

    public void onTick(String str, final int i) {
        Disposable disposable = (Disposable) this.networkApi.getContent(str).subscribeWith(new DisposableSingleObserver<JSONObject>() { // from class: com.amazon.alexa.redesign.poller.PollingManager.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                Log.e(PollingManager.TAG, th.toString());
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(JSONObject jSONObject) {
                PollingManager.this.onGetContentPollResponse(jSONObject, i);
            }
        });
    }
}
