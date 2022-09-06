package com.amazon.deecomms.accessories;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.calling.CallingRepository;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
/* loaded from: classes12.dex */
public final class AccessoryUtilities {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AccessoryUtilities.class);

    private AccessoryUtilities() {
    }

    public static void forwardATCommand(@NonNull final String str, @NonNull final CounterMetric counterMetric, @NonNull Context context) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Attempting to forward AT Command ");
        outline1.append(str.toString());
        commsLogger.i(outline1.toString());
        CommsDaggerWrapper.getComponent().getCommsDisposableManager().add(Accessories.Shared.INSTANCE.get(context).getSessionSupplier().getActiveSessions().subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$AccessoryUtilities$7BLVQGdlbegMJWlNOZNPfXXNAhc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryUtilities.forwardAtCommands(str, counterMetric, (List) obj);
            }
        }, $$Lambda$AccessoryUtilities$272xYPebW5OzNAMn6_8JPq_xzjo.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void forwardAtCommands(@NonNull final String str, @NonNull final CounterMetric counterMetric, @NonNull List<AccessorySession> list) {
        for (final AccessorySession accessorySession : list) {
            CommsDaggerWrapper.getComponent().getCommsDisposableManager().add(accessorySession.getStateRepository().query(StateFeature.BLUETOOTH_HFP_CONNECTED).subscribe(new Consumer() { // from class: com.amazon.deecomms.accessories.-$$Lambda$AccessoryUtilities$Dr-xQb8m3yAaU6uaWqMJ4V-mze0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryUtilities.lambda$forwardAtCommands$2(AccessorySession.this, str, counterMetric, (StateOuterClass.State) obj);
                }
            }, $$Lambda$AccessoryUtilities$z8ZeuErXB172IvC0TgG2yQq56IA.INSTANCE));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$forwardAtCommands$2(AccessorySession accessorySession, final String str, final CounterMetric counterMetric, StateOuterClass.State state) throws Throwable {
        CallingRepository callingRepository;
        if (state == null || !state.getBoolean() || (callingRepository = accessorySession.getCallingRepository()) == null) {
            return;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.i("Found a HFP compliant PCC Accessory..forwarding AT Command: " + str);
        callingRepository.forwardAtCommand(com.amazon.alexa.accessory.repositories.calling.ATCommand.from(str)).subscribe(new SingleObserver<Common.ErrorCode>() { // from class: com.amazon.deecomms.accessories.AccessoryUtilities.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(Throwable th) {
                GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Error forwarding AT cmd "), str, AccessoryUtilities.LOG);
                counterMetric.getMetadata().put("errorSource", MetricKeys.VALUE_AT_COMMAND_FAILURE);
                PCCDirectiveHandler.recordPCCMetric(counterMetric, false);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSubscribe(Disposable disposable) {
                AccessoryUtilities.LOG.d("Subscribed to pass AT Command");
                CommsDaggerWrapper.getComponent().getCommsDisposableManager().add(disposable);
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(Common.ErrorCode errorCode) {
                GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport.outline1("Sent AT Command "), str, AccessoryUtilities.LOG);
                PCCDirectiveHandler.recordPCCMetric(counterMetric, true);
            }
        });
    }
}
