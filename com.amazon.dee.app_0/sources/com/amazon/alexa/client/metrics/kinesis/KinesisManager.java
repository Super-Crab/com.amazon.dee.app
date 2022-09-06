package com.amazon.alexa.client.metrics.kinesis;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import com.amazon.alexa.client.metrics.kinesis.client.KinesisDefaultEventClient;
import com.amazon.alexa.client.metrics.kinesis.client.KinesisEventClient;
import com.amazon.alexa.client.metrics.kinesis.client.KinesisInternalEventClient;
import com.amazon.alexa.client.metrics.kinesis.context.DefaultKinesisContext;
import com.amazon.alexa.client.metrics.kinesis.session.AppFileSessionStore;
import com.amazon.alexa.client.metrics.kinesis.session.AppInternalSessionClient;
import com.amazon.alexa.client.metrics.kinesis.session.AppSessionClient;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazonaws.auth.AWSCredentialsProvider;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class KinesisManager {
    private static final String TAG = "KinesisManager";
    private static final HashMap<String, KinesisManager> instanceMap = new HashMap<>();
    private final AppInternalSessionClient appSessionClient;
    private final KinesisInternalEventClient kinesisEventClient;

    @Inject
    public KinesisManager(Context context, Lazy<ClientConfiguration> lazy, @Named("service.metrics.alexaservice") Lazy<PersistentStorage> lazy2, Lazy<AWSCredentialsProvider> lazy3, Lazy<DirectedIDProvider> lazy4, CrashReporter crashReporter) {
        DefaultKinesisContext defaultKinesisContext = new DefaultKinesisContext(context, lazy, lazy2, lazy3);
        this.kinesisEventClient = new KinesisDefaultEventClient(context, defaultKinesisContext);
        this.kinesisEventClient.setDirectedId(lazy4.mo358get().getDirectedID());
        this.appSessionClient = new AppDefaultSessionClient(defaultKinesisContext, this.kinesisEventClient, new AppFileSessionStore(context, defaultKinesisContext), crashReporter);
        this.appSessionClient.startSession();
        Log.i(TAG, "Kinesis Manager initialization successfully completed");
    }

    public AppSessionClient getAppSessionClient() {
        return this.appSessionClient;
    }

    public KinesisEventClient getEventClient() {
        return this.kinesisEventClient;
    }
}
