package com.amazon.identity.auth.device.actor;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.az;
import com.amazon.identity.auth.device.ba;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.dn;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.mq;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ActorManagerCommunication implements ba {
    private final dn ba;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class EnrollActorWithUIAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_POLICY_HANDLE = "policy_handle";

        public static Bundle parametersToBundle(String str, String str2, String str3, Bundle bundle) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("account_id", str, "actor_id", str2);
            outline12.putString("policy_handle", str3);
            outline12.putBundle("options_key", bundle);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("policy_handle");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "MAPActorManager:enrollActorWithUI");
            az.b(edVar).a(edVar, string, string2, string3, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class GetActorForMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2) {
            return GeneratedOutlineSupport1.outline12("account", str, "actor_mapping", str2);
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return az.b(edVar).getActorForMapping(bundle.getString("account"), bundle.getString("actor_mapping"));
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class RemoveActorMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2) {
            return GeneratedOutlineSupport1.outline12("account", str, "actor_mapping", str2);
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return az.b(edVar).removeActorMapping(bundle.getString("account"), bundle.getString("actor_mapping"));
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SetActorMappingAction implements IPCCommand {
        public static final String KEY_ACCOUNT = "account";
        public static final String KEY_ACTOR = "actor";
        public static final String KEY_ACTOR_MAPPING = "actor_mapping";

        public static Bundle parametersToBundle(String str, String str2, String str3) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("account", str, KEY_ACTOR, str2);
            outline12.putString("actor_mapping", str3);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            return az.b(edVar).setActorMapping(bundle.getString("account"), bundle.getString(KEY_ACTOR), bundle.getString("actor_mapping"));
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SignUpAndEnrollActorWithUIAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_POLICY_HANDLE = "policy_handle";

        public static Bundle parametersToBundle(String str, String str2, Bundle bundle) {
            Bundle outline12 = GeneratedOutlineSupport1.outline12("account_id", str, "policy_handle", str2);
            outline12.putBundle("options_key", bundle);
            return outline12;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("policy_handle");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "MAPActorManager:signUpAndEnrollActorWithUI");
            az.b(edVar).a(edVar, string, string2, bundle3, mq.a(d, callback), d);
            return null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class SwitchActorAction implements IPCCommand {
        public static final String KEY_ACCOUNT_ID = "account_id";
        public static final String KEY_ACTOR_ID = "actor_id";
        public static final String KEY_ACTOR_SWITCH_MODE = "actor_switch_mode";
        public static final String KEY_ACTOR_TYPE = "actor_type";
        public static final String KEY_OPTIONS = "options_key";
        public static final String KEY_PACAKGE_NAME = "package_name";
        public static final String KEY_PROGRAM = "program";

        public static Bundle parametersToBundle(MAPActorManager.ActorSwitchMode actorSwitchMode, ActorInfo actorInfo, String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(KEY_ACTOR_SWITCH_MODE, actorSwitchMode.name());
            bundle2.putString("account_id", actorInfo.getAccountDirectedId());
            bundle2.putString("actor_id", actorInfo.getActorDirectedId());
            bundle2.putString("actor_type", actorInfo.getSuggestedActorType());
            bundle2.putString("program", actorInfo.getProgram());
            bundle2.putString("package_name", str);
            bundle2.putBundle("options_key", bundle);
            return bundle2;
        }

        public Bundle performIPCAction(ed edVar, Bundle bundle, Callback callback) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            MAPActorManager.ActorSwitchMode valueOf = MAPActorManager.ActorSwitchMode.valueOf(bundle.getString(KEY_ACTOR_SWITCH_MODE));
            String string = bundle.getString("account_id");
            String string2 = bundle.getString("actor_id");
            String string3 = bundle.getString("program");
            String string4 = bundle.getString("actor_type");
            String string5 = bundle.getString("package_name");
            Bundle bundle2 = bundle.getBundle("options_key");
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putInt("callingUid", bundle.getInt("callingUid", -1));
            ej d = ej.d(bundle, "MAPActorManager:switchActor");
            Callback a = mq.a(d, callback);
            az.b(edVar).a(valueOf, new ActorInfo(string3, string, string2, string4), string5, bundle2, a, d);
            return null;
        }
    }

    ActorManagerCommunication(ed edVar, dn dnVar) {
        this.o = edVar;
        this.ba = dnVar;
    }

    private boolean aC(String str) {
        return TextUtils.equals(str, "Error parsing IPC request") || TextUtils.equals(str, "The given IPC information was not of a valid form");
    }

    private void q(Bundle bundle) {
        if (bundle.getInt("result_code") == 4) {
            bundle.putBoolean(MAPActorManager.KEY_RETRYABLE, !aC(bundle.getString(MAPActorManager.KEY_ERROR_MESSAGE)));
        }
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(SignUpAndEnrollActorWithUIAction.class, SignUpAndEnrollActorWithUIAction.parametersToBundle(str, str2, bundle), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getActor(String str, Bundle bundle) {
        return MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.UNSUPPORTED_OPERATION, "This API can only be called on 3P devices", false);
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getActorForMapping(String str, String str2) {
        Bundle a = this.ba.a(GetActorForMappingAction.class, GetActorForMappingAction.parametersToBundle(str, str2));
        q(a);
        return a;
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle getCurrentActor(String str) {
        return getActorForMapping(str, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle removeActorMapping(String str, String str2) {
        Bundle a = this.ba.a(RemoveActorMappingAction.class, RemoveActorMappingAction.parametersToBundle(str, str2));
        q(a);
        return a;
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle removeCurrentActor(String str) {
        return removeActorMapping(str, "com.amazon.identity.auth.device.current.actor");
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle setActorMapping(String str, String str2, String str3) {
        Bundle a = this.ba.a(SetActorMappingAction.class, SetActorMappingAction.parametersToBundle(str, str2, str3));
        q(a);
        return a;
    }

    @Override // com.amazon.identity.auth.device.ba
    public Bundle setCurrentActor(String str, String str2) {
        return setActorMapping(str, str2, "com.amazon.identity.auth.device.current.actor");
    }

    public ActorManagerCommunication(ed edVar) {
        this(edVar, new dn(edVar, "result_code", MAPActorManager.KEY_ERROR_MESSAGE, 4));
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(EnrollActorWithUIAction.class, EnrollActorWithUIAction.parametersToBundle(str, str2, str3, bundle), blVar);
        return blVar;
    }

    @Override // com.amazon.identity.auth.device.ba
    public MAPFuture<Bundle> a(@NonNull MAPActorManager.ActorSwitchMode actorSwitchMode, @NonNull ActorInfo actorInfo, String str, Bundle bundle, Callback callback, ej ejVar) {
        bl blVar = new bl(callback);
        this.ba.a(SwitchActorAction.class, SwitchActorAction.parametersToBundle(actorSwitchMode, actorInfo, str, bundle), blVar);
        return blVar;
    }
}
