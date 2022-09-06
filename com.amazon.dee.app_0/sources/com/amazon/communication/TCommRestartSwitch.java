package com.amazon.communication;

import amazon.speech.simclient.genericconnection.ConnectionResult;
import amazon.speech.simclient.genericconnection.GenericConnectionClient;
import amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback;
import android.content.Context;
import android.content.Intent;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
/* loaded from: classes12.dex */
public class TCommRestartSwitch implements GenericConnectionStatusCallback {
    private static TCommRestartSwitch INSTANCE;
    private Context mContext;
    private GenericConnectionClient mGenericConnectionClient;
    private ScheduledFuture<?> mTCommRestartFuture;
    private static final DPLogger log = new DPLogger("TComm.TCommRestartSwitch");
    private static int RESTART_DELAY_SECONDS = 15;

    /* renamed from: com.amazon.communication.TCommRestartSwitch$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$communication$TCommRestartSwitch$TCommRestartStatus = new int[TCommRestartStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$communication$TCommRestartSwitch$TCommRestartStatus[TCommRestartStatus.RESTART_IMMEDIATELY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$communication$TCommRestartSwitch$TCommRestartStatus[TCommRestartStatus.RESTART_AFTER_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$communication$TCommRestartSwitch$TCommRestartStatus[TCommRestartStatus.CANCEL_RESTART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$communication$TCommRestartSwitch$TCommRestartStatus[TCommRestartStatus.DO_NOTHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult = new int[ConnectionResult.values().length];
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DISCONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.AVS_DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DISCONNECTED_WITH_RECOVERY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_DOWNCHANNEL_CREATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$amazon$speech$simclient$genericconnection$ConnectionResult[ConnectionResult.TCOMM_CONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private enum TCommRestartStatus {
        DO_NOTHING,
        RESTART_IMMEDIATELY,
        RESTART_AFTER_DELAY,
        CANCEL_RESTART
    }

    private TCommRestartSwitch() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void clearTCommRestartFuture() {
        if (this.mTCommRestartFuture != null) {
            log.info("clearTCommRestartFuture", "Canceled delayed restart of AndroidTCommService", new Object[0]);
            this.mTCommRestartFuture.cancel(false);
            this.mTCommRestartFuture = null;
        }
    }

    public static synchronized TCommRestartSwitch getInstance() {
        TCommRestartSwitch tCommRestartSwitch;
        synchronized (TCommRestartSwitch.class) {
            if (INSTANCE == null) {
                INSTANCE = new TCommRestartSwitch();
            }
            tCommRestartSwitch = INSTANCE;
        }
        return tCommRestartSwitch;
    }

    public synchronized void initialize(Context context) {
        this.mContext = context;
        if (this.mGenericConnectionClient != null) {
            this.mGenericConnectionClient.unregisterConnectionStatusCallback();
            this.mGenericConnectionClient.teardown();
            this.mGenericConnectionClient = null;
        }
        if (GenericConnectionClient.isGenericConnectionServiceAvailable(this.mContext)) {
            this.mGenericConnectionClient = new GenericConnectionClient(this.mContext);
            this.mGenericConnectionClient.registerConnectionStatusCallback(this);
        }
    }

    @Override // amazon.speech.simclient.genericconnection.GenericConnectionStatusCallback
    public synchronized void onResult(final ConnectionResult connectionResult) {
        log.info("onResult", "Received a connectionResult from CSM", "connectionResult", connectionResult);
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.amazon.communication.TCommRestartSwitch.1
            /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
                if (r4 != 5) goto L15;
             */
            /* JADX WARN: Removed duplicated region for block: B:34:0x00c3  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x0131  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 311
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.TCommRestartSwitch.AnonymousClass1.run():void");
            }
        });
    }

    public synchronized void restartTComm() {
        if (this.mContext == null) {
            log.error("restartTComm", "Can't restart AndroidTCommService as the Context is null", new Object[0]);
            return;
        }
        clearTCommRestartFuture();
        log.info("restartTComm", "Restarting AndroidTCommService!", new Object[0]);
        this.mContext.stopService(new Intent(this.mContext.getApplicationContext(), AndroidTCommService.class));
        this.mContext.startService(new Intent(this.mContext.getApplicationContext(), AndroidTCommService.class));
    }
}
