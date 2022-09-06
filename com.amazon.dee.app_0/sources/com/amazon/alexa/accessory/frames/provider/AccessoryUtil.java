package com.amazon.alexa.accessory.frames.provider;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.calling.ATCommand;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class AccessoryUtil {
    private static final Integer STATE_FEATURE_TOP_CONTACT = 869;
    private static final String TAG = "AccessoryUtil";

    private AccessoryUtil() {
    }

    public static void forwardATCommand(@NonNull final String str, @NonNull Context context) {
        Log.i(TAG, "Attempting to forward AT Command");
        Accessories.Shared.INSTANCE.get(context).getSessionSupplier().getActiveSessions().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.frames.provider.-$$Lambda$AccessoryUtil$-UM4OUtsmcxcdgIP04V5ME2yjq0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryUtil.lambda$forwardATCommand$2(str, (List) obj);
            }
        }, $$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$forwardATCommand$2(final String str, List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            final AccessorySession accessorySession = (AccessorySession) it2.next();
            accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_HFP_CONNECTED).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.frames.provider.-$$Lambda$AccessoryUtil$otMt6FOsriYBqEvqDbW7pK-jd6U
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryUtil.lambda$null$0(AccessorySession.this, str, (StateOuterClass.State) obj);
                }
            }, $$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(AccessorySession accessorySession, final String str, StateOuterClass.State state) throws Throwable {
        CallingRepository callingRepository;
        if (state == null || !state.getBoolean() || (callingRepository = accessorySession.getCallingRepository()) == null) {
            return;
        }
        String str2 = TAG;
        Log.d(str2, "Found a HFP compliant PCC Accessory..forwarding AT Command: " + str);
        callingRepository.forwardAtCommand(ATCommand.from(str)).subscribe(new SingleObserver<Common.ErrorCode>() { // from class: com.amazon.alexa.accessory.frames.provider.AccessoryUtil.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                String str3 = AccessoryUtil.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error forwarding AT cmd ");
                outline107.append(str);
                Log.e(str3, outline107.toString());
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSubscribe(Disposable disposable) {
                Log.i(AccessoryUtil.TAG, "Subscribed to pass AT Command");
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(Common.ErrorCode errorCode) {
                String str3 = AccessoryUtil.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sent AT Command ");
                outline107.append(str);
                Log.d(str3, outline107.toString());
            }
        });
    }

    public static void subscribeToAccessoryStateChanges(final Accessory accessory) {
        DependencyProvider.getMainHandler().post(new Runnable() { // from class: com.amazon.alexa.accessory.frames.provider.AccessoryUtil.5
            @Override // java.lang.Runnable
            public void run() {
                Log.i(AccessoryUtil.TAG, "subscribeToAccessoryStateChanges - Top contact feature state");
                AccessoryUtil.subscribeToTouchpadCustomizationState(Accessory.this);
            }
        });
    }

    public static void subscribeToTouchpadCustomizationState(Accessory accessory) {
        Log.d(TAG, "AccessoryClient: subscribeToTouchpadCustomizationState");
        if (accessory == null) {
            Log.i(TAG, "AccessoryClient: subscribeToTouchpadCustomizationState - Accessory session is null");
        } else {
            DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getStateRepository().query(StateFeature.from(STATE_FEATURE_TOP_CONTACT.intValue())).subscribeOn(Schedulers.computation()).subscribe(new Consumer<StateOuterClass.State>() { // from class: com.amazon.alexa.accessory.frames.provider.AccessoryUtil.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(StateOuterClass.State state) {
                    int integer = state.getInteger();
                    if (integer == 0) {
                        Log.d(AccessoryUtil.TAG, "OS Assistant");
                        TopContactManager.getInstance().setTouchpadCustomizationState(TopContactManager.TouchpadCustomizationState.OS_ASSISTANT);
                    } else if (integer != 1) {
                        Log.d(AccessoryUtil.TAG, "default");
                        TopContactManager.getInstance().setTouchpadCustomizationState(TopContactManager.TouchpadCustomizationState.NOT_SUPPORTED);
                    } else {
                        Log.d(AccessoryUtil.TAG, "Calling");
                        TopContactManager.getInstance().setTouchpadCustomizationState(TopContactManager.TouchpadCustomizationState.CALLING);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessory.frames.provider.AccessoryUtil.3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) {
                    String str = AccessoryUtil.TAG;
                    Log.d(str, "AccessoryClient: subscribeToTouchpadCustomizationState - onError - " + th);
                    TopContactManager.getInstance().setTouchpadCustomizationState(TopContactManager.TouchpadCustomizationState.NOT_SUPPORTED);
                }
            }, new Action() { // from class: com.amazon.alexa.accessory.frames.provider.AccessoryUtil.4
                @Override // io.reactivex.rxjava3.functions.Action
                public void run() {
                }
            });
        }
    }
}
