package com.amazon.deecomms.platform.identity;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public enum Entity {
    BOT("bot"),
    GROUP("group"),
    HOMEGROUP("hg"),
    PERSON(ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR),
    ROOM("room");
    
    private String mCommsIDValue;

    Entity(String str) {
        this.mCommsIDValue = str;
    }

    public static Entity getValueOf(String str) {
        Entity[] values;
        if (str != null) {
            for (Entity entity : values()) {
                if (str.equals(entity.toString())) {
                    return entity;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("No Entity found with value: [", str, "]"));
        }
        throw new IllegalArgumentException("A value must be specified");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mCommsIDValue;
    }
}
