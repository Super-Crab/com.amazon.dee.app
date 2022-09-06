package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPFuture;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface ba {
    MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback, ej ejVar);

    MAPFuture<Bundle> a(@NonNull MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, String str, Bundle bundle, Callback callback, ej ejVar);

    Bundle getActor(String str, Bundle bundle);

    Bundle getActorForMapping(String str, String str2);

    Bundle getCurrentActor(String str);

    Bundle removeActorMapping(String str, String str2);

    Bundle removeCurrentActor(String str);

    Bundle setActorMapping(String str, String str2, String str3);

    Bundle setCurrentActor(String str, String str2);
}
