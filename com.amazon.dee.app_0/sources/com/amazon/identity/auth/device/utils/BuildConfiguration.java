package com.amazon.identity.auth.device.utils;

import com.amazon.identity.auth.device.framework.InvalidEnumValueException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum BuildConfiguration {
    Eng(KeyType.Debug),
    UserDebug(KeyType.ReleaseDebug),
    User(KeyType.Release),
    ReleaseDebug(KeyType.ReleaseDebug);
    
    private final KeyType mSignedWith;

    BuildConfiguration(KeyType keyType) {
        this.mSignedWith = keyType;
    }

    public static BuildConfiguration fromString(String str) throws InvalidEnumValueException {
        BuildConfiguration[] values;
        if (str != null) {
            for (BuildConfiguration buildConfiguration : values()) {
                if (buildConfiguration.toString().equalsIgnoreCase(str)) {
                    return buildConfiguration;
                }
            }
            throw new InvalidEnumValueException("Unknown build type: ".concat(str));
        }
        throw new InvalidEnumValueException("Null is not a valid BuildType");
    }

    public boolean isSignedWith(KeyType keyType) {
        return this.mSignedWith == keyType;
    }
}
