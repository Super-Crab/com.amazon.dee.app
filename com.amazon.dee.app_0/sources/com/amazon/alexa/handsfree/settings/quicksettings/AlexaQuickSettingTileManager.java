package com.amazon.alexa.handsfree.settings.quicksettings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.service.quicksettings.TileService;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsState;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider.HandsFreeSettingsStateProvider;
import com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager;
import com.amazon.alexa.handsfree.settings.SettingsClientMediator;
import com.amazon.alexa.handsfree.settings.SettingsModule;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.contract.quicksettings.QSTileContract;
import com.amazon.alexa.handsfree.settings.contract.quicksettings.exceptions.TileNotPresentException;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.HandsFreeSetupMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import com.amazon.alexa.handsfree.settings.metrics.PageViewMetricMetadata;
import dagger.Lazy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class AlexaQuickSettingTileManager extends AlexaHandsFreeManager {
    private static final String ALEXA_QUICK_SETTING_ACTIVITY = "com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaQuickSettingsLauncherActivity";
    private static final String ERROR_TILE_NOT_PRESENT = "Quick Settings tile is no longer present, no need to update the tile";
    private static final long HANDLER_DELAY_IN_MILLIS = 200;
    private static final String TAG = "AlexaQuickSettingTileManager";
    private final Context mContext;
    private final QSTileContract.Listener mQsListener;
    private final Handler mUIThreadHandler;

    /* renamed from: com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileManager$3  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState = new int[HandsFreeSettingsState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaQuickSettingTileManager(@NonNull Context context, @NonNull QSTileContract.Listener listener) {
        super(context);
        this.mContext = context;
        this.mQsListener = listener;
        this.mUIThreadHandler = new Handler(Looper.getMainLooper());
    }

    private void enableSettingsActivity(boolean z) {
        Context applicationContext = this.mContext.getApplicationContext();
        applicationContext.getPackageManager().setComponentEnabledSetting(new ComponentName(applicationContext.getPackageName(), ALEXA_QUICK_SETTING_ACTIVITY), z ? 1 : 2, 1);
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected ClickMetricMetadata.Component getActionComponent() {
        return ClickMetricMetadata.Component.SETTINGS_TILE;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected ClickMetricMetadata.PageType getActionPageType() {
        return ClickMetricMetadata.PageType.QUICK_SETTINGS;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected HandsFreeSetupMetricMetadata.Component getHandsFreeComponent() {
        return HandsFreeSetupMetricMetadata.Component.SETTINGS_TILE;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected HandsFreeSetupMetricMetadata.PageType getHandsFreePageType() {
        return HandsFreeSetupMetricMetadata.PageType.QUICK_SETTINGS;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected PageViewMetricMetadata.Component getPageViewComponent() {
        return PageViewMetricMetadata.Component.SETTINGS_TILE;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected PageViewMetricMetadata.PageType getPageViewPageType() {
        return PageViewMetricMetadata.PageType.QUICK_SETTINGS;
    }

    void recordOperationForQuickSettingTileUpdateSyncAlarm(boolean z) {
        recordOperationForAlarm(MetricType.HANDSFREE_MANAGER_QUICK_SETTINGS_TILE_UPDATE_SUCCESS.getValue(), z);
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected void startActivity(@NonNull Context context, @NonNull Intent intent) {
        if (context instanceof TileService) {
            ((TileService) context).startActivityAndCollapse(intent);
        } else {
            context.startActivity(intent);
        }
        if (canEnableWakeWord()) {
            try {
                this.mQsListener.setTileDescription(2);
                recordOperationForQuickSettingTileUpdateSyncAlarm(true);
            } catch (TileNotPresentException unused) {
                Log.d(TAG, ERROR_TILE_NOT_PRESENT);
                recordOperationForQuickSettingTileUpdateSyncAlarm(false);
            }
        }
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected void updateOnUIThread(HandsFreeSettingsState handsFreeSettingsState) {
        final int i;
        try {
            int ordinal = handsFreeSettingsState.ordinal();
            if (ordinal == 0) {
                this.mQsListener.setTileActive(hasPendingSteps());
                i = 1;
            } else if (ordinal != 1) {
                i = 0;
            } else {
                i = 2;
                this.mQsListener.setTileActive(true);
            }
            if (handsFreeSettingsState.isEnabled()) {
                this.mQsListener.setTileDescription(i);
                enableSettingsActivity(true);
            } else {
                enableSettingsActivity(false);
            }
            this.mUIThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileManager.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AlexaQuickSettingTileManager.this.mQsListener.setState(i);
                        AlexaQuickSettingTileManager.this.mQsListener.updateTile();
                        AlexaQuickSettingTileManager.this.recordOperationForQuickSettingTileUpdateSyncAlarm(true);
                    } catch (TileNotPresentException unused) {
                        Log.d(AlexaQuickSettingTileManager.TAG, AlexaQuickSettingTileManager.ERROR_TILE_NOT_PRESENT);
                        AlexaQuickSettingTileManager.this.recordOperationForQuickSettingTileUpdateSyncAlarm(false);
                    }
                }
            });
            if (Build.VERSION.SDK_INT > 26) {
                return;
            }
            this.mUIThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.quicksettings.AlexaQuickSettingTileManager.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AlexaQuickSettingTileManager.this.mQsListener.updateTile();
                    } catch (TileNotPresentException unused) {
                        Log.d(AlexaQuickSettingTileManager.TAG, AlexaQuickSettingTileManager.ERROR_TILE_NOT_PRESENT);
                    }
                }
            }, HANDLER_DELAY_IN_MILLIS);
        } catch (TileNotPresentException unused) {
            Log.d(TAG, ERROR_TILE_NOT_PRESENT);
            recordOperationForQuickSettingTileUpdateSyncAlarm(false);
        }
    }

    @VisibleForTesting
    AlexaQuickSettingTileManager(@NonNull Context context, @NonNull QSTileContract.Listener listener, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull RemoteConfigManager remoteConfigManager, @NonNull Handler handler, @NonNull HandsFreeSettingsStateProvider handsFreeSettingsStateProvider, @NonNull HandsFreeSettingsState handsFreeSettingsState, @NonNull SettingsClientMediator settingsClientMediator, @NonNull SettingsSetupFlowContract settingsSetupFlowContract, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull SettingsModule settingsModule) {
        super(context, metricsBuilderProvider, remoteConfigManager, handsFreeSettingsStateProvider, handsFreeSettingsState, settingsClientMediator, lazy, settingsSetupFlowContract, settingsModule);
        this.mContext = context;
        this.mQsListener = listener;
        this.mUIThreadHandler = handler;
    }
}
