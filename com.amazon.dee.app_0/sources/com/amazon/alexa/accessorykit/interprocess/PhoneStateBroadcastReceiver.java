package com.amazon.alexa.accessorykit.interprocess;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessorykit.interprocess.PersistedState;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class PhoneStateBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "PhoneStateBroadcastReceiver:";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompletableSource lambda$onReceive$0(boolean z, PersistedState persistedState, RxMapStore rxMapStore, Set set) throws Throwable {
        if (z && !set.isEmpty() && ((PersistedState) set.iterator().next()).state.equals(persistedState.state)) {
            return Completable.complete();
        }
        return rxMapStore.put(Integer.toString(persistedState.state.getFeature()), persistedState).ignoreElement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onReceive$1(BroadcastReceiver.PendingResult pendingResult, Throwable th) throws Throwable {
        Logger.e("%s failed to put state in the store", th, TAG);
        pendingResult.finish();
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"CheckResult"})
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("state_update")) {
            return;
        }
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            goAsync.finish();
            return;
        }
        String string = extras.getString("STATE");
        final boolean z = extras.getBoolean("IS_BASELINE");
        try {
            final PersistedState mo1239create = PersistedState.Factory.INSTANCE.mo1239create(new JSONObject(string));
            final RxMapStore<String, PersistedState> remoteStateCache = RemoteStateCacheHolder.getRemoteStateCache(context, StateFeature.from(mo1239create.state.getFeature()));
            Completable flatMapCompletable = remoteStateCache.get(Integer.toString(mo1239create.state.getFeature())).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$PhoneStateBroadcastReceiver$FOhM-CzVpj04mwHx78I6Dm0tTLA
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return PhoneStateBroadcastReceiver.lambda$onReceive$0(z, mo1239create, remoteStateCache, (Set) obj);
                }
            });
            goAsync.getClass();
            flatMapCompletable.subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$cbgfWMFe0yt2yfP--VEbeMUFC88
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    goAsync.finish();
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$PhoneStateBroadcastReceiver$nLZsWX8wM1kva1x2nhRHt30xt2g
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    PhoneStateBroadcastReceiver.lambda$onReceive$1(goAsync, (Throwable) obj);
                }
            });
        } catch (JSONException e) {
            Logger.e("%s Unable to create PersistedState from json %s", e, TAG, string);
            goAsync.finish();
        }
    }
}
