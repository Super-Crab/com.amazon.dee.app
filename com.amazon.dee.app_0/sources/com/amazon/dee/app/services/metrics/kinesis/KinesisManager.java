package com.amazon.dee.app.services.metrics.kinesis;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisDefaultEventClient;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisEventClient;
import com.amazon.dee.app.services.metrics.kinesis.client.KinesisInternalEventClient;
import com.amazon.dee.app.services.metrics.kinesis.context.DefaultKinesisContext;
import com.amazon.dee.app.services.metrics.kinesis.session.AppFileSessionStore;
import com.amazon.dee.app.services.metrics.kinesis.session.AppSessionClient;
import com.amazon.dee.app.services.metrics.kinesis.session.SessionObserver;
import com.amazon.dee.app.services.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class KinesisManager {
    private static final String TAG = Log.tag(KinesisManager.class);
    private static final HashMap<String, KinesisManager> instanceMap = new HashMap<>();
    private final AppSessionClient appSessionClient;
    private final KinesisInternalEventClient kinesisEventClient;

    public KinesisManager(Context context, String str, String str2, Regions regions, PersistentStorage.Factory factory, AWSCredentialsProvider aWSCredentialsProvider, IdentityService identityService) {
        DefaultKinesisContext defaultKinesisContext = new DefaultKinesisContext(context, str, str2, regions, factory, aWSCredentialsProvider);
        this.kinesisEventClient = new KinesisDefaultEventClient(context, defaultKinesisContext);
        UserIdentity user = identityService.getUser(TAG);
        if (user != null) {
            this.kinesisEventClient.setDirectedId(user.getDirectedId());
        }
        this.appSessionClient = new AppDefaultSessionClient(defaultKinesisContext, new AppFileSessionStore(context, defaultKinesisContext));
        this.appSessionClient.registerSessionObserver((SessionObserver) this.kinesisEventClient);
        this.appSessionClient.startSession();
        Log.i(TAG, "Kinesis Manager initialization successfuly completed");
    }

    public static KinesisManager getInstance(String str) {
        KinesisManager kinesisManager;
        synchronized (instanceMap) {
            kinesisManager = instanceMap.get(str);
        }
        return kinesisManager;
    }

    public static KinesisManager getOrCreateInstance(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull Regions regions, @NonNull PersistentStorage.Factory factory, @NonNull AWSCredentialsProvider aWSCredentialsProvider, @NonNull IdentityService identityService) {
        KinesisManager kinesisManager;
        synchronized (instanceMap) {
            if (!instanceMap.containsKey(str)) {
                instanceMap.put(str, new KinesisManager(context, str, str2, regions, factory, aWSCredentialsProvider, identityService));
            }
            kinesisManager = instanceMap.get(str);
        }
        return kinesisManager;
    }

    public AppSessionClient getAppSessionClient() {
        return this.appSessionClient;
    }

    public KinesisEventClient getEventClient() {
        return this.kinesisEventClient;
    }
}
