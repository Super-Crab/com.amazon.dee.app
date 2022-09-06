package com.amazon.dee.app.services.metrics.kinesis.context;

import android.content.Context;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.kinesis.system.AndroidAppDetails;
import com.amazon.dee.app.services.metrics.kinesis.system.AndroidDeviceDetails;
import com.amazon.dee.app.services.metrics.kinesis.system.AppDetails;
import com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails;
import com.amazon.dee.app.util.KinesisContextIdUtil;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes12.dex */
public class DefaultKinesisContext implements KinesisContext {
    private static final String TAG = Log.tag(DefaultKinesisContext.class);
    private static final String UNIQUE_ID_KEY = "UniqueId";
    private final String appId;
    private final AWSCredentialsProvider awsCredentialsProvider;
    private final Context context;
    private final Regions kinesisAwsRegion;
    private final String kinesisStreamName;
    private final PersistentStorage persistentStorage;
    private KinesisContextIdUtil uniqueId;

    public DefaultKinesisContext(Context context, String str, String str2, Regions regions, PersistentStorage.Factory factory, AWSCredentialsProvider aWSCredentialsProvider) {
        this.uniqueId = KinesisContextIdUtil.getEmptyId();
        this.context = context;
        this.appId = str;
        this.kinesisStreamName = str2;
        this.kinesisAwsRegion = regions;
        this.persistentStorage = factory.create(str);
        this.uniqueId = getUniqueId();
        this.awsCredentialsProvider = aWSCredentialsProvider;
    }

    private void storeUniqueId(KinesisContextIdUtil kinesisContextIdUtil) {
        try {
            this.persistentStorage.edit().set(UNIQUE_ID_KEY, kinesisContextIdUtil.getValue()).commit();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("There was an exception when trying to store, the unique id into the Preferences ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public AWSCredentialsProvider getAWSCredentialsProvider() {
        return this.awsCredentialsProvider;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public AppDetails getAppDetails() {
        Context context = this.context;
        return new AndroidAppDetails(context, context.getPackageName());
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public String getAppID() {
        return this.appId;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public DeviceDetails getDeviceDetails() {
        return new AndroidDeviceDetails(this.context);
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public Regions getKinesisAwsRegion() {
        return this.kinesisAwsRegion;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public String getKinesisStreamName() {
        return this.kinesisStreamName;
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext
    public KinesisContextIdUtil getUniqueId() {
        String string = this.persistentStorage.getString(UNIQUE_ID_KEY);
        if (string != null) {
            this.uniqueId = new KinesisContextIdUtil(string);
        }
        if (this.uniqueId == KinesisContextIdUtil.getEmptyId()) {
            this.uniqueId = new KinesisContextIdUtil(UUID.randomUUID().toString());
            storeUniqueId(this.uniqueId);
        }
        return this.uniqueId;
    }
}
