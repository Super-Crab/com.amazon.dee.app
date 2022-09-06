package com.amazon.identity.auth.device.api;

import androidx.annotation.NonNull;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ActorInfo {
    @FireOsSdk
    public static final String ACTOR_TYPE_ADULT = "PERSON.ADULT";
    @FireOsSdk
    public static final String ACTOR_TYPE_CHILD = "PERSON.CHILD";
    @FireOsSdk
    public static final String ACTOR_TYPE_TEEN = "PERSON.TEEN";
    private final String fA;
    private final String fx;
    private final String fy;
    private final String fz;

    @FireOsSdk
    public ActorInfo(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4) {
        this.fx = str;
        this.fy = str2;
        this.fz = str3;
        this.fA = str4;
    }

    @FireOsSdk
    public String getAccountDirectedId() {
        return this.fy;
    }

    @FireOsSdk
    public String getActorDirectedId() {
        return this.fz;
    }

    @FireOsSdk
    public String getProgram() {
        return this.fx;
    }

    @FireOsSdk
    public String getSuggestedActorType() {
        return this.fA;
    }
}
