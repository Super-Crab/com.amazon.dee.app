package com.google.firebase.heartbeatinfo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes3.dex */
public class DefaultHeartBeatInfo implements HeartBeatInfo {
    private HeartBeatInfoStorage storage;

    private DefaultHeartBeatInfo(Context context) {
        this.storage = HeartBeatInfoStorage.getInstance(context);
    }

    @NonNull
    public static Component<HeartBeatInfo> component() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(HeartBeatInfo.class).add(Dependency.required(Context.class));
        componentFactory = DefaultHeartBeatInfo$$Lambda$1.instance;
        return add.factory(componentFactory).build();
    }

    public static /* synthetic */ HeartBeatInfo lambda$component$0(ComponentContainer componentContainer) {
        return new DefaultHeartBeatInfo((Context) componentContainer.get(Context.class));
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    @NonNull
    public HeartBeatInfo.HeartBeat getHeartBeatCode(@NonNull String str) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean shouldSendSdkHeartBeat = this.storage.shouldSendSdkHeartBeat(str, currentTimeMillis);
        boolean shouldSendGlobalHeartBeat = this.storage.shouldSendGlobalHeartBeat(currentTimeMillis);
        if (!shouldSendSdkHeartBeat || !shouldSendGlobalHeartBeat) {
            if (shouldSendGlobalHeartBeat) {
                return HeartBeatInfo.HeartBeat.GLOBAL;
            }
            if (shouldSendSdkHeartBeat) {
                return HeartBeatInfo.HeartBeat.SDK;
            }
            return HeartBeatInfo.HeartBeat.NONE;
        }
        return HeartBeatInfo.HeartBeat.COMBINED;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    DefaultHeartBeatInfo(HeartBeatInfoStorage heartBeatInfoStorage) {
        this.storage = heartBeatInfoStorage;
    }
}
