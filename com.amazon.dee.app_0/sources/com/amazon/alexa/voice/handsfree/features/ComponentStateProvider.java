package com.amazon.alexa.voice.handsfree.features;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.EnumMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ComponentStateProvider {
    @VisibleForTesting
    static final String SHARED_PREFERENCE_FILE = "FEATURE_GATE_COMPONENT_STATE";
    private static final String TEST_MODE_HANDS_FREE_EXPERIENCE_KEY = "FEATURE_GATE_ALEXA_HANDS_FREE";
    private final EnumMap<HandsFreeComponent, String> mHandsFreeComponentMap = new EnumMap<>(HandsFreeComponent.class);
    private final SharedPreferences mSharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ComponentStateProvider(@NonNull Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, 0);
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.HANDS_FREE_NOTIFICATIONS, (HandsFreeComponent) "FEATURE_GATE_NOTIFICATION_STATE");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.HANDS_FREE_SETTINGS, (HandsFreeComponent) "FEATURE_GATE_SETTINGS_STATE");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.VOX_APP_HANDS_FREE_SETTING, (HandsFreeComponent) "FEATURE_GATE_IN_APP_SETTINGS_STATE");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.HANDS_FREE_SETUP_FLOW, (HandsFreeComponent) "FEATURE_GATE_SETUP_FLOW_STATE");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST, (HandsFreeComponent) "FEATURE_GATE_BLOCK_SENSITIVE_REQUEST");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_WW_SETTINGS_V2_TRUE_TURN_KEY, (HandsFreeComponent) "FEATURE_GATE_WW_SETTINGS_V2_TRUE_TURN_KEY");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.PROFILE_SELECTION, (HandsFreeComponent) "FEATURE_GATE_PROFILE_SELECTION");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGESV_UVR, (HandsFreeComponent) "FEATURE_GATE_EDGESV_UVR");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGESV_DECOUPLING, (HandsFreeComponent) "FEATURE_GATE_EDGESV_DECOUPLING");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9R_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9R_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_NA, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_NA");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_EU, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_EU");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_TMO, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_TMO");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_PRO_NA, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_PRO_NA");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_PRO_EU, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_PRO_EU");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_PRO_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_PRO_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OP9_PRO_TMO, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OP9_PRO_TMO");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_LG_V60_ATT, (HandsFreeComponent) "FEATURE_GATE_EDGESV_LG_V60_ATT");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_XIAOMI_J19C_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_XIAOMI_J19C_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_XIAOMI_J19C_EU, (HandsFreeComponent) "FEATURE_GATE_EDGESV_XIAOMI_J19C_EU");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MOTO_KIEV_PREPAID_VZW, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MOTO_KIEV_PREPAID_VZW");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MOTO_KIEV_POSTPAID_VZW, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MOTO_KIEV_POSTPAID_VZW");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_XIAOMI_K3S_EU, (HandsFreeComponent) "FEATURE_GATE_EDGESV_XIAOMI_K3S_EU");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_XIAOMI_K9D_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_XIAOMI_K9D_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MOTO_BERLIN_VZW, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MOTO_BERLIN_VZW");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_QC_GLOBAL, (HandsFreeComponent) "FEATURE_GATE_EDGESV_QC_GLOBAL");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MTK_GLOBAL, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MTK_GLOBAL");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MOTO_EDGE_PLUS, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MOTO_PLUS");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_MOTO_MILAN_5G_RETAIL_NA, (HandsFreeComponent) "FEATURE_GATE_EDGESV_MOTO_MILAN_5G_NA");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_IN, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OPPO_OVALTINE_IN");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_EU, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OPPO_OVALTINE_EU");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_NA, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OPPO_OVALTINE_NA");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_TMO, (HandsFreeComponent) "FEATURE_GATE_EDGESV_OPPO_OVALTINE_TMO");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.TEST_MODE_HANDS_FREE_EXPERIENCE, (HandsFreeComponent) TEST_MODE_HANDS_FREE_EXPERIENCE_KEY);
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_VOX_TTA, (HandsFreeComponent) "FEATURE_GATE_ALEXA_VOX_TTA");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.PROFILE_DECOUPLING, (HandsFreeComponent) "FEATURE_GATE_PROFILE_DECOUPLING");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_HANDS_FREE_BARGE_IN_SETTING, (HandsFreeComponent) "FEATURE_GATE_ALEXA_HANDS_FREE_BARGE_IN_SETTING");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.EDGESV_VISUAL_FOCUS, (HandsFreeComponent) "FEATURE_GATE_EDGESV_VISUAL_FOCUS");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_VOX_DLS, (HandsFreeComponent) "FEATURE_GATE_ALEXA_VOX_DLS");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING, (HandsFreeComponent) "FEATURE_GATE_DYNAMIC_LANGUAGE_SWITCHING");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_HANDS_FREE_DATA_SYNC, (HandsFreeComponent) "FEATURE_GATE_ALEXA_HANDS_FREE_DATA_SYNC");
        this.mHandsFreeComponentMap.put((EnumMap<HandsFreeComponent, String>) HandsFreeComponent.ALEXA_HANDS_FREE_ENROLLMENT_UTTERANCES_SYNC, (HandsFreeComponent) "FEATURE_GATE_ALEXA_HANDS_FREE_ENROLLMENT_UTTERANCES_SYNC");
    }

    @VisibleForTesting
    String getKeyForComponent(@NonNull HandsFreeComponent handsFreeComponent) {
        return this.mHandsFreeComponentMap.get(handsFreeComponent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasRemainedAvailable(@NonNull HandsFreeComponent handsFreeComponent) {
        String keyForComponent = getKeyForComponent(handsFreeComponent);
        if (keyForComponent.equals(TEST_MODE_HANDS_FREE_EXPERIENCE_KEY)) {
            return this.mSharedPreferences.getBoolean(keyForComponent, false);
        }
        return this.mSharedPreferences.getBoolean(keyForComponent, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void storeCurrentState(@NonNull HandsFreeComponent handsFreeComponent, boolean z) {
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, getKeyForComponent(handsFreeComponent), z);
    }
}
