package com.amazon.alexa.voice.handsfree.features;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.SettingsClientMediator;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.wakewordsettings.WakeWordSettingsMediator;
/* loaded from: classes11.dex */
public class WakeWordSettingVersionResolver implements HandsFreeUserIdentity.Listener {
    private static final String TAG = "WakeWordSettingVersionResolver";
    private static WakeWordSettingVersionResolver sInstance;
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private final WakeWordSettingsManager mWakeWordSettingsManagerV1;
    private final WakeWordSettingsManager mWakeWordSettingsManagerV2;

    private WakeWordSettingVersionResolver(@NonNull Context context) {
        this(((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider(), context, AMPDInformationProvider.getInstance(context));
    }

    public static synchronized WakeWordSettingVersionResolver getInstance(@NonNull Context context) {
        WakeWordSettingVersionResolver wakeWordSettingVersionResolver;
        synchronized (WakeWordSettingVersionResolver.class) {
            if (sInstance == null) {
                sInstance = new WakeWordSettingVersionResolver(context.getApplicationContext());
            }
            wakeWordSettingVersionResolver = sInstance;
        }
        return wakeWordSettingVersionResolver;
    }

    private void initializeWakeWordSettingsManager() {
        if (shouldInvokeWakeWordSettingsV2()) {
            Log.d(TAG, "initializeWakeWordSettingsManager with WakeWordSettingsManager version 2");
            WakeWordSettingsManagerProvider.getInstance().initialize(this.mWakeWordSettingsManagerV2);
            return;
        }
        Log.d(TAG, "initializeWakeWordSettingsManager with WakeWordSettingsManager version 1");
        WakeWordSettingsManagerProvider.getInstance().initialize(this.mWakeWordSettingsManagerV1);
    }

    private void startListeningForIdentityChanges() {
        this.mHandsFreeUserIdentityProvider.addListener(this);
    }

    public void initialize() {
        initializeWakeWordSettingsManager();
        startListeningForIdentityChanges();
    }

    @VisibleForTesting
    boolean isComponentEnabled(@NonNull HandsFreeComponent handsFreeComponent) {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity();
        boolean z = handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(handsFreeComponent);
        Log.d(TAG, String.format("User %s has %s weblab enabled? : %s", handsFreeUserIdentity, handsFreeComponent.name(), Boolean.valueOf(z)));
        return z;
    }

    @Override // com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity.Listener
    public void onHandsFreeUserIdentityChanged(@Nullable HandsFreeUserIdentity handsFreeUserIdentity) {
        Log.d(TAG, "onHandsFreeUserIdentityChanged");
        initializeWakeWordSettingsManager();
    }

    @VisibleForTesting
    boolean shouldInvokeWakeWordSettingsV2() {
        return isComponentEnabled(HandsFreeComponent.ALEXA_WW_SETTINGS_V2_TRUE_TURN_KEY) && this.mAMPDInformationProvider.isTrueTurnKey();
    }

    @VisibleForTesting
    WakeWordSettingVersionResolver(@NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this(handsFreeUserIdentityProvider, context, aMPDInformationProvider, new SettingsClientMediator(context, ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(context, FalcoSettingsComponent.class)).remoteConfigManager(), MetricsBuilderProvider.getInstance(context)), new WakeWordSettingsMediator(context));
    }

    @VisibleForTesting
    WakeWordSettingVersionResolver(@NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull WakeWordSettingsManager wakeWordSettingsManager2) {
        this.mContext = context;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mWakeWordSettingsManagerV1 = wakeWordSettingsManager;
        this.mWakeWordSettingsManagerV2 = wakeWordSettingsManager2;
    }
}
