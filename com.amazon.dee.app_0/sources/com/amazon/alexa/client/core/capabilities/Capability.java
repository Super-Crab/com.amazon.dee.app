package com.amazon.alexa.client.core.capabilities;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.JsonObject;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Capability {
    public static Capability create(CapabilityInterface capabilityInterface, String str) {
        return create(CapabilityType.createAlexaInterface(), capabilityInterface, str);
    }

    @Nullable
    public abstract JsonObject getConfigurations();

    public abstract CapabilityInterface getInterface();

    public abstract CapabilityType getType();

    public abstract String getVersion();

    public static Capability create(CapabilityType capabilityType, CapabilityInterface capabilityInterface, String str) {
        return create(capabilityType, capabilityInterface, str, null);
    }

    public static Capability create(CapabilityInterface capabilityInterface, String str, @Nullable JsonObject jsonObject) {
        return create(CapabilityType.createAlexaInterface(), capabilityInterface, str, jsonObject);
    }

    public static Capability create(CapabilityType capabilityType, CapabilityInterface capabilityInterface, String str, @Nullable JsonObject jsonObject) {
        return new AutoValue_Capability(capabilityType, capabilityInterface, str, jsonObject);
    }
}
