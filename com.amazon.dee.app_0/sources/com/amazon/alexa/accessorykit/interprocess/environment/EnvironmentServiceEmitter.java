package com.amazon.alexa.accessorykit.interprocess.environment;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class EnvironmentServiceEmitter {
    private static final String EU_WEST_1 = "eu-west-1";
    private static final Map<String, String> REGION_MAP = new HashMap<String, String>() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.EnvironmentServiceEmitter.1
        {
            put("us-east-1", "us-east-1");
            put(EnvironmentServiceEmitter.US_WEST_2, EnvironmentServiceEmitter.US_WEST_2);
            put(EnvironmentServiceEmitter.EU_WEST_1, EnvironmentServiceEmitter.EU_WEST_1);
        }
    };
    private static final String TAG = "EnvironmentServiceEmitter:";
    private static final String US_EAST_1 = "us-east-1";
    private static final String US_WEST_2 = "us-west-2";
    private final Context context;
    private final EnvironmentService environmentService;
    private final Object lock;
    private final UserSupplier userSupplier;
    private User previousUser = null;
    private boolean active = false;

    public EnvironmentServiceEmitter(@NonNull Context context, @NonNull EnvironmentService environmentService, @NonNull UserSupplier userSupplier) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(environmentService, "environmentService");
        Preconditions.notNull(userSupplier, "userSupplier");
        this.context = context;
        this.environmentService = environmentService;
        this.userSupplier = userSupplier;
        this.lock = new Object();
    }

    private static Intent createEnvironmentServiceIntent(@NonNull EnvironmentService environmentService, @NonNull Context context) {
        return new Intent(EnvironmentServiceReceiver.ENVIRONMENT_SERVICE_BASE_INTENT).setClass(context, EnvironmentServiceReceiver.class).putExtra("webEndpoint", environmentService.getWebEndpoint()).putExtra("alexaApiHost", environmentService.getApiGatewayHost()).putExtra("alexaApiEndpoint", environmentService.getApiGatewayEndpoint()).putExtra("awsRegion", (String) environmentService.getMobilyticsEndpoint(REGION_MAP));
    }

    public EnvironmentServiceEmitter activate() {
        synchronized (this.lock) {
            if (this.active) {
                return this;
            }
            this.active = true;
            Logger.d("%s activate()", TAG);
            this.userSupplier.queryUser().distinctUntilChanged().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.-$$Lambda$EnvironmentServiceEmitter$5sAPp8anZ8j1iFiXXBCwCqsi5R8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnvironmentServiceEmitter.this.lambda$activate$0$EnvironmentServiceEmitter((User) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.environment.-$$Lambda$EnvironmentServiceEmitter$AujSyKexLq2pIocy3V5_dWMTBO4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    EnvironmentServiceEmitter.this.lambda$activate$1$EnvironmentServiceEmitter((Throwable) obj);
                }
            });
            return this;
        }
    }

    public /* synthetic */ void lambda$activate$0$EnvironmentServiceEmitter(User user) throws Throwable {
        Logger.d("%s user changed", TAG);
        if (!user.equals(this.previousUser)) {
            this.previousUser = user;
            Context context = this.context;
            context.sendBroadcast(createEnvironmentServiceIntent(this.environmentService, context), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
        }
    }

    public /* synthetic */ void lambda$activate$1$EnvironmentServiceEmitter(Throwable th) throws Throwable {
        Logger.e("%s user changed error", th, TAG);
        Context context = this.context;
        context.sendBroadcast(createEnvironmentServiceIntent(this.environmentService, context), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }
}
