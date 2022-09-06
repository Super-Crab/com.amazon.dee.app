package com.amazon.alexa;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaBaseURLs;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.alexa.utils.validation.AlexaUriValidator;
import com.amazon.dee.app.BuildConfig;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.HttpUrl;
/* compiled from: EndpointAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class xUA {
    public static final String zZm = "xUA";
    public final Box JTe;
    public HttpUrl LPk;
    public final AlexaClientEventBus Qle;
    public final Lazy<ClientConfiguration> jiA;
    public static final String[] BIo = {"v1", "devices", "@self", CamerasRouteParameter.CAPABILITIES};
    public static final HttpUrl zQM = new HttpUrl.Builder().scheme("https").host("api.amazonalexa.com").build();
    public static final HttpUrl zyO = new HttpUrl.Builder().scheme("https").host(BuildConfig.VOICE_HOST).build();

    @Inject
    public xUA(AlexaClientEventBus alexaClientEventBus, Box box, Lazy<ClientConfiguration> lazy) {
        this.Qle = alexaClientEventBus;
        this.JTe = box;
        this.jiA = lazy;
    }

    public void zZm(AlexaBaseURLs alexaBaseURLs) {
        if (alexaBaseURLs.getAVSURL() != null) {
            zZm(alexaBaseURLs.getAVSURL());
        }
    }

    public final HttpUrl zZm() {
        if (this.LPk == null) {
            if (this.JTe.yPL()) {
                this.LPk = zZm(this.JTe.zQM(), zyO);
            } else {
                this.LPk = zZm(this.jiA.mo358get().getAvsEndpoint(), zyO);
            }
            StringBuilder zZm2 = C0179Pya.zZm("Initializing endpoint to: ");
            zZm2.append(this.LPk);
            zZm2.toString();
        }
        return this.LPk;
    }

    public void zZm(Uri uri) {
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        HttpUrl zZm2 = zZm();
        boolean z = false;
        if ((zZm2.port() < 0 || port < 0 || zZm2.port() == port) && zZm2.scheme().equalsIgnoreCase(scheme) && zZm2.host().equalsIgnoreCase(host)) {
            z = true;
        }
        if (z) {
            Log.i(zZm, "Dropping endpoint change to the same endpoint: " + uri);
            return;
        }
        C0179Pya.zZm("Switching endpoint to: ", (Object) uri);
        this.JTe.zZm(uri);
        this.LPk = zZm(uri, zyO);
        this.Qle.zyO(new MUO());
    }

    public final HttpUrl zZm(@Nullable Uri uri, HttpUrl httpUrl) {
        if (uri == null) {
            return httpUrl;
        }
        String scheme = uri.getScheme();
        String host = uri.getHost();
        int port = uri.getPort();
        HttpUrl.Builder builder = new HttpUrl.Builder();
        if (!AlexaUriValidator.hasValidScheme(uri)) {
            String zZm2 = C0179Pya.zZm("Attempted to use bad scheme: ", scheme);
            Log.e(zZm, zZm2);
            this.Qle.zyO(TTH.zZm(zZm2));
            return httpUrl;
        }
        builder.scheme(scheme);
        if (!AlexaUriValidator.hasValidHost(uri)) {
            String zZm3 = C0179Pya.zZm("Attempted to use bad host: ", host);
            Log.e(zZm, zZm3);
            this.Qle.zyO(TTH.zZm(zZm3));
            return httpUrl;
        }
        builder.host(host);
        if (port > 0) {
            builder.port(port);
        }
        return builder.build();
    }
}
