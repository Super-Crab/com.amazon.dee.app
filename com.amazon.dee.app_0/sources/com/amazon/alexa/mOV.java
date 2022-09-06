package com.amazon.alexa;

import android.net.Uri;
import android.util.Log;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.configuration.Loader;
import com.amazon.alexa.client.core.configuration.Stage;
import com.amazon.alexa.client.metrics.core.DefaultMetricsConstants;
import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: DefaultConfigurationLoader.java */
@Singleton
/* loaded from: classes.dex */
public class mOV implements Loader<ClientConfiguration.Builder> {
    public final jZN zZm;

    @Inject
    public mOV(jZN jzn) {
        this.zZm = jzn;
    }

    @Override // com.amazon.alexa.client.core.configuration.Loader
    /* renamed from: zZm */
    public void load(ClientConfiguration.Builder builder) {
        ClientConfiguration.Builder experimentalLocales = builder.setEnabledFeatures(new HashSet(Arrays.asList(Feature.WAKEWORD_ACTIVATION_SOUND, Feature.TOUCH_ACTIVATION_SOUND))).setStage(Stage.PROD).setAvsEndpoint(Uri.parse(xUA.zyO.toString())).setCapabilitiesEndpoint(Uri.parse(xUA.zQM.toString())).setSupportedLocales(new HashSet(Arrays.asList(new Locale("de", "DE"), new Locale("de", "AT"), new Locale("en", "US"), new Locale("en", "GB"), new Locale("en", "AU"), new Locale("en", "IN"), new Locale("en", "IE"), new Locale("en", "NZ"), new Locale("en", "CA"), new Locale("es", "ES"), new Locale("es", "MX"), new Locale("es", "US"), new Locale("fr", "CA"), new Locale("fr", "FR"), new Locale("it", "IT"), new Locale("ja", "JP"), new Locale("pt", "BR"), new Locale("hi", "IN"), new Locale("ar", "SA"), new Locale("nl", "NL"), new Locale("nl", "BE")))).setActualSupportedLocales(new HashSet(Arrays.asList(new Locale("de", "DE"), new Locale("en", "US"), new Locale("en", "GB"), new Locale("en", "AU"), new Locale("en", "CA"), new Locale("en", "IN"), new Locale("es", "ES"), new Locale("es", "MX"), new Locale("es", "US"), new Locale("fr", "CA"), new Locale("fr", "FR"), new Locale("it", "IT"), new Locale("ja", "JP"), new Locale("pt", "BR"), new Locale("hi", "IN"), new Locale("ar", "SA")))).setSupportedLocaleCombinations(new HashSet(Arrays.asList(new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("es", "US"))), new ArrayList(Arrays.asList(new Locale("es", "US"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "IN"), new Locale("hi", "IN"))), new ArrayList(Arrays.asList(new Locale("hi", "IN"), new Locale("en", "IN"))), new ArrayList(Arrays.asList(new Locale("fr", "CA"), new Locale("en", "CA"))), new ArrayList(Arrays.asList(new Locale("en", "CA"), new Locale("fr", "CA"))), new ArrayList(Arrays.asList(new Locale("it", "IT"), new Locale("en", "GB"))), new ArrayList(Arrays.asList(new Locale("en", "GB"), new Locale("it", "IT"))), new ArrayList(Arrays.asList(new Locale("es", "ES"), new Locale("en", "GB"))), new ArrayList(Arrays.asList(new Locale("en", "GB"), new Locale("es", "ES"))), new ArrayList(Arrays.asList(new Locale("de", "DE"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("de", "DE"))), new ArrayList(Arrays.asList(new Locale("de", "DE"), new Locale("en", "GB"))), new ArrayList(Arrays.asList(new Locale("en", "GB"), new Locale("de", "DE"))), new ArrayList(Arrays.asList(new Locale("ja", "JP"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("ja", "JP"))), new ArrayList(Arrays.asList(new Locale("fr", "FR"), new Locale("en", "GB"))), new ArrayList(Arrays.asList(new Locale("en", "GB"), new Locale("fr", "FR"))), new ArrayList(Arrays.asList(new Locale("fr", "FR"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("fr", "FR"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("it", "IT"))), new ArrayList(Arrays.asList(new Locale("it", "IT"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("es", "ES"))), new ArrayList(Arrays.asList(new Locale("es", "ES"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("pt", "BR"))), new ArrayList(Arrays.asList(new Locale("pt", "BR"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("es", "MX"))), new ArrayList(Arrays.asList(new Locale("es", "MX"), new Locale("en", "US"))), new ArrayList(Arrays.asList(new Locale("en", "US"), new Locale("hi", "IN"))), new ArrayList(Arrays.asList(new Locale("hi", "IN"), new Locale("en", "US")))))).setExperimentalLocales(new HashSet());
        Object obj = this.zZm.BIo.get("MAPDeviceType");
        if (obj != null && !(obj instanceof String)) {
            throw new RuntimeException("MAP device type is not a string in the manifest");
        }
        if (obj == null) {
            Log.i(jZN.zZm, "MAP device type is not in the manifest");
        }
        experimentalLocales.setMetricsDeviceType((String) obj).setNetworkTotalWriteTimeout(50L).setFirstTurnNetworkTotalWriteTimeout(10L).setNetworkWriteBytesTimeout(Long.valueOf((long) DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS)).setMaxUtteranceDuration(45L).setMetricsServiceName(DefaultMetricsConstants.DEFAULT_METRICS_SERVICE_NAME).setAwsMobileAnalyticsApplicationId("901ad8be11e4424f875dc792db51f34d").setAwsMobileAnalyticsIdentityPoolId("us-east-1:dbcce1fd-ea8a-4a85-a2ee-80a7b04ff098").setMobilyticsKinesisStreamName("alexa-mobile-analytics").setMobilyticsKinesisCognitoIdentityPoolId("us-east-1:5de045a1-113d-437d-be67-68496504240c").setMobilyticsKinesisRegion("us-east-1").setPlaybackResumingTimeout(60L).setECAV2(false).setRemoveNotificationOnTeardown(false).setLocationPermissionsAllowed(true);
    }
}
