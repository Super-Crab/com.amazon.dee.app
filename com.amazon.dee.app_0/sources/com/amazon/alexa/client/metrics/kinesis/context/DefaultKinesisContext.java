package com.amazon.alexa.client.metrics.kinesis.context;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.metrics.kinesis.system.AndroidAppDetails;
import com.amazon.alexa.client.metrics.kinesis.system.AndroidDeviceDetails;
import com.amazon.alexa.client.metrics.kinesis.system.AppDetails;
import com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails;
import com.amazon.alexa.client.metrics.kinesis.util.KinesisContextIdUtil;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.UUID;
import javax.inject.Named;
/* loaded from: classes6.dex */
public class DefaultKinesisContext implements KinesisContext {
    private static final String TAG = "DefaultKinesisContext";
    private static final String UNIQUE_ID_KEY = "UniqueId";
    private final Lazy<AWSCredentialsProvider> awsCredentialsProvider;
    private final Lazy<ClientConfiguration> clientConfigurationProvider;
    private final Context context;
    private final Lazy<PersistentStorage> persistentStorage;
    private KinesisContextIdUtil uniqueId;

    public DefaultKinesisContext(Context context, Lazy<ClientConfiguration> lazy, @Named("service.metrics.alexaservice") Lazy<PersistentStorage> lazy2, Lazy<AWSCredentialsProvider> lazy3) {
        this.uniqueId = KinesisContextIdUtil.getEmptyId();
        this.context = context;
        this.clientConfigurationProvider = lazy;
        this.persistentStorage = lazy2;
        this.uniqueId = getUniqueId();
        this.awsCredentialsProvider = lazy3;
    }

    private String getCarrier(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getNetworkOperatorName() != null && !telephonyManager.getNetworkOperatorName().equals("")) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Exception unused) {
        }
        return "Unknown";
    }

    private void storeUniqueId(KinesisContextIdUtil kinesisContextIdUtil) {
        try {
            this.persistentStorage.mo358get().edit().set(UNIQUE_ID_KEY, kinesisContextIdUtil.getValue()).commitAsynchronously();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("There was an exception when trying to store, the unique id into the Preferences "), TAG);
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public AWSCredentialsProvider getAWSCredentialsProvider() {
        return this.awsCredentialsProvider.mo358get();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public AppDetails getAppDetails() {
        Context context = this.context;
        return new AndroidAppDetails(context, context.getPackageName());
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public String getAppID() {
        return this.clientConfigurationProvider.mo358get().getMobilyticsKinesisCognitoIdentityPoolId();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public DeviceDetails getDeviceDetails() {
        return new AndroidDeviceDetails(getCarrier(this.context));
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public Regions getKinesisAwsRegion() {
        return Regions.fromName(this.clientConfigurationProvider.mo358get().getMobilyticsKinesisRegion());
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public String getKinesisStreamName() {
        return this.clientConfigurationProvider.mo358get().getMobilyticsKinesisStreamName();
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.context.KinesisContext
    public KinesisContextIdUtil getUniqueId() {
        String string = this.persistentStorage.mo358get().getString(UNIQUE_ID_KEY);
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
