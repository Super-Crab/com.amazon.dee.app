package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ay;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.mq;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPActorManager {
    @FireOsSdk
    public static final int ERROR_ACCOUNT_NOT_EXIST = 2;
    @FireOsSdk
    public static final int ERROR_INVALID_INPUT = 5;
    @FireOsSdk
    public static final int ERROR_UNKNOWN_DB_FAILURE = 3;
    @FireOsSdk
    public static final int ERROR_UNKNOWN_IPC_ERROR = 4;
    @FireOsSdk
    public static final String KEY_ACCOUNT_ID = "account_id";
    @FireOsSdk
    public static final String KEY_ACTOR_ID = "actor_id";
    @FireOsSdk
    public static final String KEY_ACTOR_TYPE = "actor_type";
    @FireOsSdk
    public static final String KEY_DO_NOT_PASS_PACKAGE_NAME_TO_APS = "do_not_pass_package_name_to_aps";
    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "error_message";
    @FireOsSdk
    public static final String KEY_PROGRAM = "program";
    @FireOsSdk
    public static final String KEY_RESULT_ACTOR_TYPE = "result_actor_type";
    @FireOsSdk
    public static final String KEY_RESULT_CODE = "result_code";
    @FireOsSdk
    public static final String KEY_RETRYABLE = "retryable";
    @FireOsSdk
    public static final int SUCCESS = 1;
    private ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum ActorSwitchMode {
        Normal,
        Force
    }

    @FireOsSdk
    public MAPActorManager(Context context) {
        MAPInit.getInstance(context).initialize();
        this.o = ed.M(context);
    }

    @FireOsSdk
    public MAPFuture<Bundle> enrollActorWithUI(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback) {
        ej by = ej.by("EnrollActorWithUI");
        return ay.a(this.o).a(context, str, str2, str3, bundle, mq.a(by, by.ea(), callback), by);
    }

    public Bundle getActor(String str, Bundle bundle) {
        return ay.a(this.o).getActor(str, bundle);
    }

    @FireOsSdk
    public Bundle getActorForMapping(String str, String str2) {
        return ay.a(this.o).getActorForMapping(str, str2);
    }

    @FireOsSdk
    public Bundle getCurrentActor(String str) {
        return ay.a(this.o).getCurrentActor(str);
    }

    @FireOsSdk
    public Bundle removeActorMapping(String str, String str2) {
        return ay.a(this.o).removeActorMapping(str, str2);
    }

    @FireOsSdk
    public Bundle removeCurrentActor(String str) {
        return ay.a(this.o).removeCurrentActor(str);
    }

    @FireOsSdk
    public Bundle setActorMapping(String str, String str2, String str3) {
        return ay.a(this.o).setActorMapping(str, str2, str3);
    }

    @FireOsSdk
    public Bundle setCurrentActor(String str, String str2) {
        return ay.a(this.o).setCurrentActor(str, str2);
    }

    @FireOsSdk
    public MAPFuture<Bundle> signUpAndEnrollActorWithUI(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback) {
        ej by = ej.by("SignUpAndEnrollActorWithUI");
        return ay.a(this.o).a(context, str, str2, bundle, mq.a(by, by.ea(), callback), by);
    }

    @FireOsSdk
    public MAPFuture<Bundle> switchActor(@NonNull ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, Bundle bundle, Callback callback) {
        ej by = ej.by("switchActor");
        return ay.a(this.o).a(actorSwitchMode, actorInfo, this.o.getPackageName(), bundle, mq.a(by, by.ea(), callback), by);
    }
}
