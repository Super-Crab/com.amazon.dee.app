package com.amazon.alexa.handsfree.devices.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
import com.amazon.alexa.handsfree.devices.DeviceConstant;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.constants.Carrier;
import com.amazon.alexa.handsfree.devices.constants.Manufacturer;
import com.amazon.alexa.handsfree.protocols.features.FeatureChecker;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes8.dex */
public enum HandsFreeFeature {
    HANDS_FREE_EXPERIENCE(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE),
    HANDS_FREE_EXPERIENCE_APOLLO_B_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_APOLLO_B_IN, Collections.singletonList(DeviceConstant.APOLLO_B_IN)),
    HANDS_FREE_EXPERIENCE_APOLLO_NARZO_IN("ALEXA_HANDS_FREE_FEATURE_GATING_ROMEO_APOLLO_NARZO_IN", Collections.singletonList(DeviceConstant.APOLLO_NARZO_IN)),
    HANDS_FREE_EXPERIENCE_APOLLO_F_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_APOLLO_F_IN, Collections.singletonList(DeviceConstant.APOLLO_F_IN)),
    HANDS_FREE_EXPERIENCE_OCTO9_VZW("ALEXA_HANDS_FREE_FEATURE_GATING_LAMBDA_CAYMAN_TMO", Collections.singletonList(DeviceConstant.OCTO9_VZW)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L3A_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L3A_EU, Collections.singletonList(DeviceConstant.XIAOMI_L3A_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L11_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L11_EU, Collections.singletonList(DeviceConstant.XIAOMI_L11_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_C3QN_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_C3QN_EU, Collections.singletonList(DeviceConstant.XIAOMI_C3QN_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_C3QB_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_C3QB_IN, Collections.singletonList(DeviceConstant.XIAOMI_C3QB_IN)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L19N_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L19N_EU, Collections.singletonList(DeviceConstant.XIAOMI_L19N_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L19_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L19_IN, Collections.singletonList(DeviceConstant.XIAOMI_L19_IN)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L19P_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L19P_EU, Collections.singletonList(DeviceConstant.XIAOMI_L19P_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L19P_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L19P_IN, Collections.singletonList(DeviceConstant.XIAOMI_L19P_IN)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L11R_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L11R_EU, Collections.singletonList(DeviceConstant.XIAOMI_L11R_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L11R_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L11R_IN, Collections.singletonList(DeviceConstant.XIAOMI_L11R_IN)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L10_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L10_EU, Collections.singletonList(DeviceConstant.XIAOMI_L10_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L9_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L9_EU, Collections.singletonList(DeviceConstant.XIAOMI_L9_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L16_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L16_EU, Collections.singletonList(DeviceConstant.XIAOMI_L16_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L16_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L16_IN, Collections.singletonList(DeviceConstant.XIAOMI_L16_IN)),
    HANDS_FREE_EXPERIENCE_PRODUCT_E_MPCS("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_E_MPCS", Collections.singletonList(DeviceConstant.PRODUCT_E_MPCS)),
    HANDS_FREE_EXPERIENCE_PRODUCT_E_VZW("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_E_VZW", Collections.singletonList(DeviceConstant.PRODUCT_E_VZW)),
    HANDS_FREE_EXPERIENCE_PRODUCT_E_CRK("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_E_CRK", Collections.singletonList(DeviceConstant.PRODUCT_E_CRK)),
    HANDS_FREE_EXPERIENCE_PRODUCT_E_DISH("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_E_DISH", Collections.singletonList(DeviceConstant.PRODUCT_E_DISH)),
    HANDS_FREE_EXPERIENCE_PRODUCT_E_UNLOCKED("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_E_UNLOCKED", Collections.singletonList(DeviceConstant.PRODUCT_E_UNLOCKED)),
    HANDS_FREE_EXPERIENCE_PRODUCT_A_VZW("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_A_VZW", Collections.singletonList(DeviceConstant.PRODUCT_A_VZW)),
    HANDS_FREE_EXPERIENCE_PRODUCT_A_ATT("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_A_ATT", Collections.singletonList(DeviceConstant.PRODUCT_A_ATT)),
    HANDS_FREE_EXPERIENCE_PRODUCT_A_TMO("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_A_TMO", Collections.singletonList(DeviceConstant.PRODUCT_A_TMO)),
    HANDS_FREE_EXPERIENCE_PRODUCT_B_VZW("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_B_VZW", Collections.singletonList(DeviceConstant.PRODUCT_B_VZW)),
    HANDS_FREE_EXPERIENCE_PRODUCT_B_TMO("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_B_TMO", Collections.singletonList(DeviceConstant.PRODUCT_B_TMO)),
    HANDS_FREE_EXPERIENCE_PRODUCT_B_SPR("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_B_SPR", Collections.singletonList(DeviceConstant.PRODUCT_B_SPR)),
    HANDS_FREE_EXPERIENCE_PRODUCT_B_UNLOCKED("ALEXA_HANDS_FREE_FEATURE_GATING_PRODUCT_B_UNLOCKED", Collections.singletonList(DeviceConstant.PRODUCT_B_UNLOCKED)),
    WAKE_WORD_SETTINGS_V2_TRUE_TURN_KEY(NativeFeatureRegistry.WAKE_WORD_SETTINGS_V2_TRUE_TURN_KEY, Collections.singleton(HandsFreeComponent.ALEXA_WW_SETTINGS_V2_TRUE_TURN_KEY)),
    HANDS_FREE_EXPERIENCE_MOTO_KIEV_RETUS(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_MOTO_KIEV_RETUS, Collections.singletonList(DeviceConstant.MOTO_KIEV_RETUS)),
    HANDS_FREE_EXPERIENCE_MOTO_KIEV_VZW_UW("ALEXA_HANDS_FREE_FEATURE_GATING_MOTO_KIEV_VZW_UW)", Collections.singletonList(DeviceConstant.MOTO_KIEV_VZW_UW)),
    HANDS_FREE_EXPERIENCE_MOTO_KIEV_RETUS_UW(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_MOTO_KIEV_RETUS_UW, Collections.singletonList(DeviceConstant.MOTO_KIEV_RETUS_UW)),
    HANDS_FREE_EXPERIENCE_MOTO_KIEV_VZWPRE_UW(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_MOTO_KIEV_VZWPRE_UW, Collections.singletonList(DeviceConstant.MOTO_KIEV_VZWPRE_UW)),
    HANDS_FREE_EXPERIENCE_OPPO_JIN_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_OPPO_JIN_EU, Collections.singletonList(DeviceConstant.OPPO_JIN_EU)),
    HANDS_FREE_EXPERIENCE_OPPO_GAREN_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_OPPO_GAREN_EU, Collections.singletonList(DeviceConstant.OPPO_GAREN_EU)),
    HANDS_FREE_EXPERIENCE_OPPO_GAREN_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_OPPO_GAREN_IN, Collections.singletonList(DeviceConstant.OPPO_GAREN_IN)),
    HANDS_FREE_EXPERIENCE_XRAY_NOTE_SERIES_K16U_EU(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XRAY_NOTE_SERIES_K16U_EU, Collections.singletonList(DeviceConstant.XRAY_NOTE_SERIES_K16U_EU)),
    HANDS_FREE_EXPERIENCE_XRAY_K3S_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XRAY_K3S_IN, Collections.singletonList(DeviceConstant.XRAY_K3S_IN)),
    HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_IN, Collections.singletonList(DeviceConstant.ONEPLUS_10_PRO_IN)),
    HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_EEA(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_EEA, Collections.singletonList(DeviceConstant.ONEPLUS_10_PRO_EEA)),
    HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_US(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_US, Collections.singletonList(DeviceConstant.ONEPLUS_10_PRO_US)),
    HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_TMO(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_ONEPLUS_10_PRO_TMO, Collections.singletonList(DeviceConstant.ONEPLUS_10_PRO_TMO)),
    HANDS_FREE_EXPERIENCE_OPPO_EEA(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_OPPO_EEA, Collections.singletonList(DeviceConstant.OPPO_EEA)),
    HANDS_FREE_EXPERIENCE_OPPO_IN(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_OPPO_IN, Collections.singletonList(DeviceConstant.OPPO_IN)),
    HANDS_FREE_FEATURE_XRAY_K6S_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_XRAY_K6S_IN, Collections.singletonList(DeviceConstant.XRAY_K6S_IN)),
    HANDS_FREE_FEATURE_XRAY_K6S_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_XRAY_K6S_EU, Collections.singletonList(DeviceConstant.XRAY_K6S_EU)),
    HANDS_FREE_FEATURE_XRAY_K6P_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_XRAY_K6P_IN, Collections.singletonList(DeviceConstant.XRAY_K6P_IN)),
    HANDS_FREE_FEATURE_XRAY_K6P_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_XRAY_K6P_EU, Collections.singletonList(DeviceConstant.XRAY_K6P_EU)),
    HANDS_FREE_FEATURE_PHOENIX_229_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_PHOENIX_229_IN, Collections.singletonList(DeviceConstant.PHOENIX_229_IN)),
    HANDS_FREE_FEATURE_OPPO_CHOPIN_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_CHOPIN_EU, Collections.singletonList(DeviceConstant.OPPO_RENO_CHOPIN_EU)),
    HANDS_FREE_FEATURE_OPPO_CHOPIN_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_CHOPIN_IN, Collections.singletonList(DeviceConstant.OPPO_RENO_CHOPIN_IN)),
    HANDS_FREE_FEATURE_OPPO_PICKLE_CPH2411_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_PICKLE_CPH2411_IN, Collections.singletonList(DeviceConstant.OPPO_PICKLE_CPH2411_IN)),
    HANDS_FREE_FEATURE_OPPO_PICKLE_CPH2423_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_PICKLE_CPH2423_IN, Collections.singletonList(DeviceConstant.OPPO_PICKLE_CPH2423_IN)),
    HANDS_FREE_FEATURE_OPPO_OSCAR_O_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_OSCAR_O_IN, Collections.singletonList(DeviceConstant.OPPO_OSCAR_O_IN)),
    HANDS_FREE_FEATURE_OPPO_OSCAR_O_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_OSCAR_O_EU, Collections.singletonList(DeviceConstant.OPPO_OSCAR_O_EU)),
    HANDS_FREE_FEATURE_OPPO_KAREN_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_KAREN_IN, Collections.singletonList(DeviceConstant.OPPO_KAREN_IN)),
    HANDS_FREE_FEATURE_OPPO_KAREN_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_KAREN_EU, Collections.singletonList(DeviceConstant.OPPO_KAREN_EU)),
    HANDS_FREE_FEATURE_OPPO_ALI_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_ALI_IN, Collections.singletonList(DeviceConstant.OPPO_ALI_IN)),
    HANDS_FREE_FEATURE_OPPO_ALI_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_ALI_EU, Collections.singletonList(DeviceConstant.OPPO_ALI_EU)),
    HANDS_FREE_FEATURE_OPPO_TAIBAI_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_TAIBAI_IN, Collections.singletonList(DeviceConstant.OPPO_TAIBAI_IN)),
    HANDS_FREE_FEATURE_OPPO_TAIBAI_EU(NativeFeatureRegistry.HANDS_FREE_FEATURE_OPPO_TAIBAI_EU, Collections.singletonList(DeviceConstant.OPPO_TAIBAI_EU)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L16U_EEA("ALEXA_HANDS_FREE_EXPERIENCE_XIAOMI_L16U_EEA", Collections.singletonList(DeviceConstant.XIAOMI_L16U_EEA)),
    HANDS_FREE_EXPERIENCE_XIAOMI_REDMI_L16U_IN("ALEXA_HANDS_FREE_EXPERIENCE_XIAOMI_REDMI_L16U_IN", Collections.singletonList(DeviceConstant.XIAOMI_REDMI_L16U_IN)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L12_EEA("ALEXA_HANDS_FREE_EXPERIENCE_XIAOMI_L12_EEA", Collections.singletonList(DeviceConstant.XIAOMI_L12_EEA)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L12_U_EEA(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L12_U_EEA, Collections.singletonList(DeviceConstant.XIAOMI_L12U_EEA)),
    HANDS_FREE_EXPERIENCE_XIAOMI_L12_A_EEA(NativeFeatureRegistry.HANDS_FREE_EXPERIENCE_XIAOMI_L12_A_EEA, Collections.singletonList(DeviceConstant.XIAOMI_L12A_EEA)),
    HANDS_FREE_FEATURE_GATING_ONEPLUS_NORD_CE2_IN(NativeFeatureRegistry.HANDS_FREE_FEATURE_GATING_ONEPLUS_NORD_CE2_IN, Collections.singletonList(DeviceConstant.ONEPLUS_NORD_CE2_IN)),
    HANDS_FREE_FEATURE_MOTO_EDGE_PLUS(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS, Collections.singletonList(DeviceConstant.MOTO_EDGE_PLUS_RETAIL)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS_5G(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS_5G, Collections.singletonList(DeviceConstant.MOTO_EDGE_PLUS_5G_RETAIL)),
    ALEXA_HANDSFREE_OPPO_OVALTINE_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_IN, Collections.singletonList(DeviceConstant.OPPO_OVALTINE_IN)),
    ALEXA_HANDSFREE_OPPO_OVALTINE_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_EU, Collections.singletonList(DeviceConstant.OPPO_OVALTINE_EU)),
    ALEXA_HANDSFREE_OPPO_OVALTINE_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_NA, Collections.singletonList(DeviceConstant.OPPO_OVALTINE_NA)),
    ALEXA_HANDSFREE_OPPO_OVALTINE_TMO(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_TMO, Collections.singletonList(DeviceConstant.OPPO_OVALTINE_TMO)),
    ALEXA_HANDSFREE_MOTO_DUBAI_VZW_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_MOTO_DUBAI_VZW_NA, Collections.singletonList(DeviceConstant.MOTO_DUBAI_VZW_NA)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_MILAN_5G_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_MILAN_5G_NA, Collections.singletonList(DeviceConstant.MOTO_MILAN_5G_NA)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_MILAN_5G_VZW_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_MILAN_5G_VZW_NA, Collections.singletonList(DeviceConstant.MOTO_MILAN_5G_VZW_NA)),
    BLOCK_SENSITIVE_REQUEST(NativeFeatureRegistry.ALEXA_HANDS_FREE_BLOCK_SENSITIVE_REQUEST, Collections.singleton(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST)),
    PROFILE_SELECTION(NativeFeatureRegistry.PROFILE_SELECTION, Collections.singleton(HandsFreeComponent.PROFILE_SELECTION)),
    EDGESV_UVR(NativeFeatureRegistry.EDGESV_UVR, Collections.singleton(HandsFreeComponent.EDGESV_UVR)),
    PROFILE_DECOUPLING("ALEXA_PROFILE_OOBE_DECOUPLING_ANDROID", new HashSet(Arrays.asList(HandsFreeComponent.PROFILE_DECOUPLING, HandsFreeComponent.PROFILE_SELECTION))),
    EDGESV_DECOUPLING(NativeFeatureRegistry.EDGESV_DECOUPLING, Collections.singleton(HandsFreeComponent.EDGESV_DECOUPLING)),
    EDGESV_VISUAL_FOCUS(NativeFeatureRegistry.EDGESV_VISUAL_FOCUS, Collections.singleton(HandsFreeComponent.EDGESV_VISUAL_FOCUS)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_NA, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_NA), Collections.singletonList(DeviceConstant.OCTO9_NA)),
    ALEXA_HANDSFREE_EDGE_SV_OP9R_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9R_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9R_IN), Collections.singletonList(DeviceConstant.OCTO9LITE_IN)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_EU, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_EU), Collections.singletonList(DeviceConstant.OCTO9_EU)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_TMO(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_TMO, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_TMO), Collections.singletonList(DeviceConstant.OCTO9_TMO)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_IN), Collections.singletonList(DeviceConstant.OCTO9_IN)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_NA, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_PRO_NA), Collections.singletonList(DeviceConstant.OCTO9PRO_NA)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_EU, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_PRO_EU), Collections.singletonList(DeviceConstant.OCTO9PRO_EU)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_TMO(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_TMO, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_PRO_TMO), Collections.singletonList(DeviceConstant.OCTO9PRO_TMO)),
    ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_OP9_PRO_IN), Collections.singletonList(DeviceConstant.OCTO9PRO_IN)),
    ALEXA_HANDSFREE_EDGE_SV_LG_V60_ATT(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_LG_V60_ATT, Collections.singleton(HandsFreeComponent.EDGE_SV_LG_V60_ATT), Collections.singletonList(DeviceConstant.V60_ATT)),
    ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_XIAOMI_J19C_IN), Collections.singletonList(DeviceConstant.XIAOMI_POCO_J19C)),
    ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_EU, Collections.singleton(HandsFreeComponent.EDGE_SV_XIAOMI_J19C_EU), Collections.singletonList(DeviceConstant.XIAOMI_POCO_K19C)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_PREPAID_VZW(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_PREPAID_VZW, Collections.singleton(HandsFreeComponent.EDGE_SV_MOTO_KIEV_PREPAID_VZW), Collections.singletonList(DeviceConstant.MOTO_KIEV_VZWPRE)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_POSTPAID_VZW(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_POSTPAID_VZW, Collections.singleton(HandsFreeComponent.EDGE_SV_MOTO_KIEV_POSTPAID_VZW), Collections.singletonList(DeviceConstant.MOTO_KIEV_VZW)),
    ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K3S_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K3S_EU, Collections.singleton(HandsFreeComponent.EDGE_SV_XIAOMI_K3S_EU), Collections.singletonList(DeviceConstant.XRAY_MI_T_SERIES_K3S_EU)),
    ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K9D_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K9D_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_XIAOMI_K9D_IN), Collections.singletonList(DeviceConstant.XRAY_MI_11_LITE_K9D_IN)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_BERLIN_VZW(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_BERLIN_VZW, Collections.singleton(HandsFreeComponent.EDGE_SV_MOTO_BERLIN_VZW), Collections.singletonList(DeviceConstant.MOTO_BERLIN_NA)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS, Collections.singleton(HandsFreeComponent.EDGE_SV_MOTO_EDGE_PLUS), Collections.singletonList(DeviceConstant.MOTO_EDGE_PLUS_RETAIL)),
    ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_IN(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_IN, Collections.singleton(HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_IN), Collections.singletonList(DeviceConstant.OPPO_OVALTINE_IN)),
    ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_EU(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_EU, Collections.singleton(HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_EU), Collections.singletonList(DeviceConstant.OPPO_OVALTINE_EU)),
    ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_NA, Collections.singleton(HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_NA), Collections.singletonList(DeviceConstant.OPPO_OVALTINE_NA)),
    ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_TMO(NativeFeatureRegistry.ALEXA_HANDSFREE_OPPO_OVALTINE_TMO, Collections.singleton(HandsFreeComponent.EDGE_SV_OPPO_OVALTINE_TMO), Collections.singletonList(DeviceConstant.OPPO_OVALTINE_TMO)),
    ALEXA_HANDSFREE_EDGE_SV_MOTO_MILAN_5G_RETAIL_NA(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_MILAN_5G_NA, Collections.singleton(HandsFreeComponent.EDGE_SV_MOTO_MILAN_5G_RETAIL_NA), Collections.singletonList(DeviceConstant.MOTO_MILAN_5G_NA)),
    ALEXA_HANDSFREE_EDGE_SV_QC_GLOBAL(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_QC_GLOBAL, Collections.singleton(HandsFreeComponent.EDGE_SV_QC_GLOBAL)),
    ALEXA_HANDSFREE_EDGE_SV_MTK_GLOBAL(NativeFeatureRegistry.ALEXA_HANDSFREE_EDGE_SV_MTK_GLOBAL, Collections.singleton(HandsFreeComponent.EDGE_SV_MTK_GLOBAL)),
    ALEXA_HANDS_FREE_BARGE_IN_SETTING(NativeFeatureRegistry.ALEXA_HANDS_FREE_BARGE_IN_SETTING, Collections.singleton(HandsFreeComponent.ALEXA_HANDS_FREE_BARGE_IN_SETTING)),
    TEST_MODE_HANDS_FREE_EXPERIENCE_ALL(NativeFeatureRegistry.TEST_MODE_HANDS_FREE_EXPERIENCE_ALL),
    ALEXA_VOX_TTA_I18N_LAUNCH(NativeFeatureRegistry.ALEXA_VOX_ANDROID_TTA_I18N_DEV, Collections.singleton(HandsFreeComponent.ALEXA_VOX_TTA)),
    ALEXA_VOX_DLS(NativeFeatureRegistry.ALEXA_VOX_DLS, new HashSet(Arrays.asList(HandsFreeComponent.ALEXA_VOX_DLS, HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING))),
    ALEXA_HANDS_FREE_DATA_SYNC(NativeFeatureRegistry.ALEXA_HANDS_FREE_DATA_SYNC, Collections.singleton(HandsFreeComponent.ALEXA_HANDS_FREE_DATA_SYNC)),
    ALEXA_HANDS_FREE_ENROLLMENT_UTTERANCES_SYNC(NativeFeatureRegistry.ALEXA_HANDS_FREE_ENROLLMENT_UTTERANCES_SYNC, Collections.singleton(HandsFreeComponent.ALEXA_HANDS_FREE_ENROLLMENT_UTTERANCES_SYNC)),
    ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING(NativeFeatureRegistry.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING, Collections.singleton(HandsFreeComponent.ALEXA_HANDS_FREE_DYNAMIC_LANGUAGE_SWITCHING));
    
    public static final List<HandsFreeFeature> LIST_OF_1PSV_DEVICES_SPECIFIC_WEBLABS;
    private final Set<String> mCarrierList;
    private final Set<HandsFreeComponent> mComponentsList;
    private final List<DeviceConstant> mDeviceList;
    private final Set<String> mManufacturerList;
    private final String mName;

    static {
        HandsFreeFeature handsFreeFeature = ALEXA_HANDSFREE_EDGE_SV_OP9_NA;
        HandsFreeFeature handsFreeFeature2 = ALEXA_HANDSFREE_EDGE_SV_OP9R_IN;
        HandsFreeFeature handsFreeFeature3 = ALEXA_HANDSFREE_EDGE_SV_OP9_EU;
        HandsFreeFeature handsFreeFeature4 = ALEXA_HANDSFREE_EDGE_SV_OP9_TMO;
        HandsFreeFeature handsFreeFeature5 = ALEXA_HANDSFREE_EDGE_SV_OP9_IN;
        HandsFreeFeature handsFreeFeature6 = ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_NA;
        HandsFreeFeature handsFreeFeature7 = ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_EU;
        HandsFreeFeature handsFreeFeature8 = ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_TMO;
        HandsFreeFeature handsFreeFeature9 = ALEXA_HANDSFREE_EDGE_SV_OP9_PRO_IN;
        HandsFreeFeature handsFreeFeature10 = ALEXA_HANDSFREE_EDGE_SV_LG_V60_ATT;
        HandsFreeFeature handsFreeFeature11 = ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_IN;
        HandsFreeFeature handsFreeFeature12 = ALEXA_HANDSFREE_EDGE_SV_XIAOMI_J19C_EU;
        HandsFreeFeature handsFreeFeature13 = ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_PREPAID_VZW;
        HandsFreeFeature handsFreeFeature14 = ALEXA_HANDSFREE_EDGE_SV_MOTO_KIEV_POSTPAID_VZW;
        HandsFreeFeature handsFreeFeature15 = ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K3S_EU;
        HandsFreeFeature handsFreeFeature16 = ALEXA_HANDSFREE_EDGE_SV_XIAOMI_K9D_IN;
        HandsFreeFeature handsFreeFeature17 = ALEXA_HANDSFREE_EDGE_SV_MOTO_BERLIN_VZW;
        HandsFreeFeature handsFreeFeature18 = ALEXA_HANDSFREE_EDGE_SV_MOTO_EDGE_PLUS;
        HandsFreeFeature handsFreeFeature19 = ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_IN;
        HandsFreeFeature handsFreeFeature20 = ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_EU;
        HandsFreeFeature handsFreeFeature21 = ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_NA;
        HandsFreeFeature handsFreeFeature22 = ALEXA_HANDSFREE_EDGE_SV_OPPO_OVALTINE_TMO;
        LIST_OF_1PSV_DEVICES_SPECIFIC_WEBLABS = Arrays.asList(handsFreeFeature, handsFreeFeature3, handsFreeFeature4, handsFreeFeature5, handsFreeFeature2, handsFreeFeature6, handsFreeFeature7, handsFreeFeature9, handsFreeFeature8, handsFreeFeature10, handsFreeFeature11, handsFreeFeature12, handsFreeFeature13, handsFreeFeature14, handsFreeFeature15, handsFreeFeature16, handsFreeFeature17, handsFreeFeature18, ALEXA_HANDSFREE_EDGE_SV_MOTO_MILAN_5G_RETAIL_NA, handsFreeFeature21, handsFreeFeature20, handsFreeFeature19, handsFreeFeature22);
    }

    HandsFreeFeature(@NonNull String str) {
        this(str, new HashSet(Arrays.asList(HandsFreeComponent.values())));
    }

    public static List<HandsFreeFeature> getListOf1psvDeviceSpecificWeblabs() {
        return LIST_OF_1PSV_DEVICES_SPECIFIC_WEBLABS;
    }

    public static List<HandsFreeFeature> getListOfHandsFreeFeatures(@NonNull HandsFreeComponent handsFreeComponent) {
        HandsFreeFeature[] values;
        ArrayList arrayList = new ArrayList();
        if (handsFreeComponent == HandsFreeComponent.TEST_MODE_HANDS_FREE_EXPERIENCE) {
            arrayList.add(TEST_MODE_HANDS_FREE_EXPERIENCE_ALL);
            return arrayList;
        }
        for (HandsFreeFeature handsFreeFeature : values()) {
            if (!handsFreeFeature.getFeatureName().equals(TEST_MODE_HANDS_FREE_EXPERIENCE_ALL.getFeatureName()) && handsFreeFeature.mComponentsList.contains(handsFreeComponent)) {
                arrayList.add(handsFreeFeature);
            }
        }
        return arrayList;
    }

    private boolean includesAllDevices() {
        return this.mDeviceList.size() == DeviceConstant.values().length;
    }

    private boolean shouldCheckFeatureStateForCurrentDevice(@NonNull DeviceInformation deviceInformation) {
        return this.mManufacturerList.contains(deviceInformation.getManufacturer()) && this.mCarrierList.contains(deviceInformation.getCarrier()) && containsDeviceType(deviceInformation.getType());
    }

    private boolean shouldCheckFeatureStateForTestMode(@NonNull DeviceInformation deviceInformation) {
        return includesAllDevices() && deviceInformation.isTestModeDevice();
    }

    public boolean containsCarrier(@Nullable String str) {
        return this.mCarrierList.contains(str);
    }

    public boolean containsDeviceType(@NonNull String str) {
        for (DeviceConstant deviceConstant : this.mDeviceList) {
            if (deviceConstant.getDeviceInformation().getType().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOEM(@NonNull String str) {
        return this.mManufacturerList.contains(str);
    }

    public Set<HandsFreeComponent> getComponentsList() {
        return this.mComponentsList;
    }

    public String getFeatureName() {
        return this.mName;
    }

    public boolean isEnabled(@NonNull FeatureChecker featureChecker, @NonNull DeviceInformation deviceInformation, boolean z) {
        if (shouldCheckFeatureStateForTestMode(deviceInformation) || shouldCheckFeatureStateForCurrentDevice(deviceInformation)) {
            return featureChecker.isActive(this.mName, z);
        }
        return true;
    }

    public boolean isEnabledForSDL(@NonNull FeatureChecker featureChecker, boolean z) {
        return featureChecker.isActive(this.mName, z);
    }

    HandsFreeFeature(@NonNull String str, @NonNull Set set) {
        this(str, new HashSet(set), new HashSet(Manufacturer.getAllManufacturers()), new HashSet(Carrier.getAllCarriers()), Arrays.asList(DeviceConstant.values()));
    }

    HandsFreeFeature(@NonNull String str, @NonNull List list) {
        this(str, new HashSet(Arrays.asList(HandsFreeComponent.values())), list);
    }

    HandsFreeFeature(@NonNull String str, @NonNull Set set, @NonNull List list) {
        this.mName = str;
        this.mComponentsList = set;
        this.mManufacturerList = new HashSet();
        this.mCarrierList = new HashSet();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            DeviceConstant deviceConstant = (DeviceConstant) it2.next();
            this.mManufacturerList.add(deviceConstant.getDeviceInformation().getManufacturer());
            this.mCarrierList.add(deviceConstant.getDeviceInformation().getCarrier());
        }
        this.mDeviceList = list;
    }

    HandsFreeFeature(@NonNull String str, @NonNull Set set, @NonNull Set set2, @NonNull Set set3, @NonNull List list) {
        this.mName = str;
        this.mComponentsList = set;
        this.mCarrierList = set3;
        this.mManufacturerList = set2;
        this.mDeviceList = list;
    }
}
