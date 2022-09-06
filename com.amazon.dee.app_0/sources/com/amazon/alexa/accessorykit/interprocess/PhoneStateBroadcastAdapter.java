package com.amazon.alexa.accessorykit.interprocess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
/* loaded from: classes6.dex */
public class PhoneStateBroadcastAdapter {
    static final String ACTION = "state_update";
    private static final String APP_NAME = "com.amazon.dee.app";
    static final String IS_BASELINE_KEY = "IS_BASELINE";
    static final String STATE_KEY = "STATE";
    private static final String TAG = "PhoneStateBroadcastAdapter:";
    private final Context context;
    private final PhoneStatePlugin phoneStatePlugin;

    public PhoneStateBroadcastAdapter(Context context, PhoneStatePlugin phoneStatePlugin) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(phoneStatePlugin, "phoneStatePlugin");
        this.context = context;
        this.phoneStatePlugin = phoneStatePlugin;
        emitStates();
    }

    private Intent createBroadcastIntent(StateOuterClass.State state, boolean z) throws JSONException {
        return new Intent(this.context, PhoneStateBroadcastReceiver.class).setAction(ACTION).setPackage("com.amazon.dee.app").putExtra(STATE_KEY, new PersistedState(state).toJsonObject().toString()).putExtra(IS_BASELINE_KEY, z);
    }

    @SuppressLint({"CheckResult"})
    private void emitStates() {
        Flowable delaySubscription = this.phoneStatePlugin.getState().toFlowable().subscribeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$PhoneStateBroadcastAdapter$REy04sGZGiec4zzm2PO-gwUVN10
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return PhoneStateBroadcastAdapter.this.lambda$emitStates$0$PhoneStateBroadcastAdapter((StateOuterClass.State) obj);
            }
        }).concatWith(this.phoneStatePlugin.queryState().subscribeOn(AndroidSchedulers.mainThread()).map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$PhoneStateBroadcastAdapter$YXOtLPfCTmUeUL6Dw9j-zk-Yngw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return PhoneStateBroadcastAdapter.this.lambda$emitStates$1$PhoneStateBroadcastAdapter((StateOuterClass.State) obj);
            }
        })).delaySubscription(1L, TimeUnit.SECONDS);
        final Context context = this.context;
        context.getClass();
        delaySubscription.subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$AKEhPEoaT3as5H09up_xudluDiQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                context.sendBroadcast((Intent) obj);
            }
        }, $$Lambda$PhoneStateBroadcastAdapter$Czr5uSVcBceZz4EafY644Lx7U40.INSTANCE);
    }

    public /* synthetic */ Intent lambda$emitStates$0$PhoneStateBroadcastAdapter(StateOuterClass.State state) throws Throwable {
        return createBroadcastIntent(state, true);
    }

    public /* synthetic */ Intent lambda$emitStates$1$PhoneStateBroadcastAdapter(StateOuterClass.State state) throws Throwable {
        return createBroadcastIntent(state, false);
    }
}
