package com.amazon.alexa.accessory.notificationpublisher;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class AccessoryNotifier {
    public static final String TAG = "AccessoryNotifier";

    private AccessoryNotifier() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMessageNotification$0(Accessory accessory, boolean z) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Accessory:");
        outline107.append(accessory.getName());
        outline107.append(" Updated Message Notification State to: ");
        outline107.append(z);
        Log.i(str, outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMessageNotification$1(Accessory accessory, Throwable th) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Accessory:");
        outline107.append(accessory.getName());
        outline107.append(" unable to update Message Notification State");
        Log.w(str, outline107.toString());
    }

    public static void onMessageNotification(final boolean z) {
        Iterator<Accessory> it2 = AccessoryProvider.getConnectedAccessoryList().iterator();
        if (it2.hasNext()) {
            final Accessory next = it2.next();
            DependencyProvider.getClientAccessories().getAccessorySession(next.getAddress()).getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(StateFeature.MESSAGE_NOTIFICATION.toInteger()).setInteger(z ? 1 : 0).mo10084build()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$AccessoryNotifier$INkkiPWJkq-ROlTxflfNp_2epqY
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    AccessoryNotifier.lambda$onMessageNotification$0(Accessory.this, z);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$AccessoryNotifier$4dSRZLeVc2-SZVnP3BDGnHlGO6Q
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryNotifier.lambda$onMessageNotification$1(Accessory.this, (Throwable) obj);
                }
            });
        }
    }
}
