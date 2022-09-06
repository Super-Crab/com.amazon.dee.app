package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
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
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.HandsFreeSetupMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.PageViewMetricMetadata;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class AlexaHandsFreeSettingsManager extends AlexaHandsFreeManager {
    public static final String ALEXA_SETTINGS_REFRESH_ACTION = "com.amazon.alexa.handsfree.ALEXA_SETTINGS_REFRESH_ACTION";
    private static final String TAG = "HandsFreeSettingsMgr";
    private final HandsFreeSettingContract.Listener mHandsFreeListener;
    private final Handler mUIThreadHandler;

    /* renamed from: com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaHandsFreeSettingsManager$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState = new int[HandsFreeSettingsState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.INACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.REMOTELY_DISABLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$remotecontentservice$apigateway$remoteconfig$provider$HandsFreeSettingsState[HandsFreeSettingsState.LOCALLY_DISABLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AlexaHandsFreeSettingsManager(@NonNull Context context, @NonNull HandsFreeSettingContract.Listener listener) {
        super(context);
        this.mUIThreadHandler = new Handler(Looper.getMainLooper());
        this.mHandsFreeListener = listener;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected ClickMetricMetadata.Component getActionComponent() {
        return ClickMetricMetadata.Component.SETTINGS_MENU;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected ClickMetricMetadata.PageType getActionPageType() {
        return ClickMetricMetadata.PageType.ANDROID_SETTINGS;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected HandsFreeSetupMetricMetadata.Component getHandsFreeComponent() {
        return HandsFreeSetupMetricMetadata.Component.SETTINGS_MENU;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected HandsFreeSetupMetricMetadata.PageType getHandsFreePageType() {
        return HandsFreeSetupMetricMetadata.PageType.ANDROID_SETTINGS;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected PageViewMetricMetadata.Component getPageViewComponent() {
        return PageViewMetricMetadata.Component.SETTINGS_MENU;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected PageViewMetricMetadata.PageType getPageViewPageType() {
        return PageViewMetricMetadata.PageType.ANDROID_SETTINGS;
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected void startActivity(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(intent);
    }

    @Override // com.amazon.alexa.handsfree.settings.AlexaHandsFreeManager
    protected void updateOnUIThread(final HandsFreeSettingsState handsFreeSettingsState) {
        this.mUIThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaHandsFreeSettingsManager.1
            @Override // java.lang.Runnable
            public void run() {
                int ordinal = handsFreeSettingsState.ordinal();
                if (ordinal == 0) {
                    AlexaHandsFreeSettingsManager.this.mHandsFreeListener.setInactive();
                } else if (ordinal == 1) {
                    AlexaHandsFreeSettingsManager.this.mHandsFreeListener.setActive();
                } else if (ordinal == 2) {
                    AlexaHandsFreeSettingsManager.this.mHandsFreeListener.setRemotelyDisabled();
                } else if (ordinal == 3) {
                    AlexaHandsFreeSettingsManager.this.mHandsFreeListener.setLocallyDisabled();
                } else {
                    Log.e(AlexaHandsFreeSettingsManager.TAG, "Unknown Hands-Free State");
                }
            }
        });
    }

    public AlexaHandsFreeSettingsManager(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull RemoteConfigManager remoteConfigManager, @NonNull Handler handler, @NonNull HandsFreeSettingsStateProvider handsFreeSettingsStateProvider, @NonNull HandsFreeSettingContract.Listener listener, @NonNull HandsFreeSettingsState handsFreeSettingsState, @NonNull SettingsClientMediator settingsClientMediator, @NonNull SettingsSetupFlowContract settingsSetupFlowContract, @NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull SettingsModule settingsModule) {
        super(context, metricsBuilderProvider, remoteConfigManager, handsFreeSettingsStateProvider, handsFreeSettingsState, settingsClientMediator, lazy, settingsSetupFlowContract, settingsModule);
        this.mUIThreadHandler = handler;
        this.mHandsFreeListener = listener;
    }
}
