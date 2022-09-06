package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;
/* loaded from: classes12.dex */
public class EffectsDataStore {
    private static EffectsDataStore effectDataStoreInstance;
    private static EffectsDataStore reactionsDataStoreInstance;
    private EffectData effectData;

    EffectsDataStore() {
    }

    public static EffectsDataStore getEffectsInstance() {
        if (effectDataStoreInstance == null) {
            effectDataStoreInstance = new EffectsDataStore();
        }
        return effectDataStoreInstance;
    }

    public static EffectsDataStore getReactionsInstance() {
        if (reactionsDataStoreInstance == null) {
            reactionsDataStoreInstance = new EffectsDataStore();
        }
        return reactionsDataStoreInstance;
    }

    public void deleteEffectData() {
        this.effectData = null;
    }

    public EffectData getEffectData() {
        return this.effectData;
    }

    public boolean isEffectActive() {
        EffectData effectData = this.effectData;
        if (effectData == null || effectData.getEffectIcons() == null || this.effectData.getEffectIcons().isEmpty()) {
            return false;
        }
        for (EffectIcon effectIcon : this.effectData.getEffectIcons()) {
            if (effectIcon.isActive()) {
                return true;
            }
        }
        return false;
    }

    public void saveEffectData(EffectData effectData) {
        this.effectData = effectData;
    }
}
