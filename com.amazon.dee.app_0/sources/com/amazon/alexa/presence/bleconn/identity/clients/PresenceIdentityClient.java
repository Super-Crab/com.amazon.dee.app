package com.amazon.alexa.presence.bleconn.identity.clients;

import android.net.Uri;
import android.os.Build;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.bleconn.identity.IdentityClient;
import com.amazon.alexa.presence.bleconn.identity.model.BleIdentityCore;
import com.amazon.alexa.presence.service.AuthTokenProvider;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.amazonaws.services.s3.Headers;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class PresenceIdentityClient implements IdentityClient {
    private static final String DEFAULT_ENDPOINT = "https://api.amazonalexa.com";
    private static final Logger LOG = LoggerFactory.getLogger(PresenceIdentityClient.class);
    private final OkHttpClient httpClient;
    private final AuthTokenProvider identityProvider;
    private final String identityTokenUri;
    private final PersonIdProvider personIdProvider;

    public PresenceIdentityClient(String str, AuthTokenProvider authTokenProvider) {
        this(new OkHttpClient(), str, authTokenProvider, null);
    }

    private boolean attachAuthorization(Request.Builder builder) {
        String authToken = this.identityProvider.getAuthToken();
        if (authToken == null) {
            return false;
        }
        builder.header("x-amz-access-token", authToken);
        return true;
    }

    private String buildQueryParamsString() {
        StringBuilder sb = new StringBuilder();
        PersonIdProvider personIdProvider = this.personIdProvider;
        String personId = personIdProvider != null ? personIdProvider.getPersonId() : null;
        ArrayList arrayList = new ArrayList();
        if (personId != null) {
            arrayList.add(encodeParam(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, personId));
        }
        String deviceMake = deviceMake();
        if (deviceMake != null) {
            arrayList.add(encodeParam("make", deviceMake));
        }
        String deviceModel = deviceModel();
        if (deviceModel != null) {
            arrayList.add(encodeParam("model", deviceModel));
        }
        sb.append(join(WebConstants.UriConstants.AMPERSAND_KEY, arrayList));
        return WebConstants.UriConstants.QUESTIONMARK_KEY + ((Object) sb);
    }

    private String deviceMake() {
        try {
            return Build.MANUFACTURER.toLowerCase(Locale.ROOT).trim();
        } catch (Throwable th) {
            LOG.warn("Unable to look up device make", th);
            return null;
        }
    }

    private String deviceModel() {
        try {
            return Build.MODEL.toLowerCase(Locale.ROOT).replace(deviceMake(), "").trim();
        } catch (Throwable th) {
            LOG.warn("Unable to look up device model", th);
            return null;
        }
    }

    private String encodeParam(String str, String str2) {
        try {
            return String.format("%s=%s", URLEncoder.encode(str, StandardCharsets.UTF_8.toString()), URLEncoder.encode(str2, StandardCharsets.UTF_8.toString()));
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public static String getDefaultEndpoint() {
        return Uri.parse("https://api.amazonalexa.com").buildUpon().path("/v1/presence/rollingProximityIdentifierTokens").build().toString();
    }

    public static String getTokenIdentityEndpoint(String str) {
        try {
            Uri parse = Uri.parse(str);
            if (parse.isAbsolute()) {
                return parse.buildUpon().path("/v1/presence/rollingProximityIdentifierTokens").build().toString();
            }
            LOG.warn(String.format("Error building token endpoint uri (given %s), using default.", str));
            return getDefaultEndpoint();
        } catch (Throwable th) {
            LOG.warn(String.format("Error building token endpoint uri (given %s), using default.", str), th);
            return getDefaultEndpoint();
        }
    }

    private String getV2BeaconIdentityJson() {
        Response response;
        String buildQueryParamsString = buildQueryParamsString();
        Request.Builder header = new Request.Builder().get().url(this.identityTokenUri + buildQueryParamsString).header("Accept", "application/json").header("Content-Encoding", "gzip").header(Headers.REQUEST_ID, String.valueOf(UUID.randomUUID()));
        LOG.debug("getV2BeaconIdentityJson called, retrieving identity packet from APRS.");
        if (!attachAuthorization(header)) {
            this.identityProvider.refreshAuthToken();
            LOG.warn("No identity could be located. Skipping identity token call.  Attempting to refresh identity.");
            return null;
        }
        try {
            response = this.httpClient.newCall(header.build()).execute();
            try {
                int code = response.code();
                if (code == 401) {
                    LOG.warn("Could not authenticate with server.  Attempting to refresh identity.");
                    this.identityProvider.refreshAuthToken();
                }
                if (code >= 500) {
                    LOG.warn("Request failed due to service error, with status code " + code);
                    LOG.debug("Request finished. Closing response stream.");
                    if (response.body() != null) {
                        response.body().close();
                    }
                    return null;
                } else if (code >= 300) {
                    LOG.warn("Request failed with status code " + code);
                    LOG.debug("Request finished. Closing response stream.");
                    if (response.body() != null) {
                        response.body().close();
                    }
                    return null;
                } else {
                    LOG.info("Identity retrieved");
                    LOG.debug("Request:");
                    LOG.debug(response.request().toString());
                    LOG.debug("Request:");
                    LOG.debug(response.request().headers().toString());
                    LOG.debug("Response:");
                    LOG.debug(response.toString());
                    LOG.debug("Response Headers:");
                    LOG.debug(response.headers().toString());
                    String string = response.body().string();
                    LOG.debug("Response Body:");
                    LOG.debug(String.format("Response Body: %s", string));
                    LOG.debug("Request finished. Closing response stream.");
                    if (response.body() != null) {
                        response.body().close();
                    }
                    return string;
                }
            } catch (Throwable th) {
                th = th;
                try {
                    throw new RuntimeException(String.format("Exception calling endpoint %s", this.identityTokenUri), th);
                } catch (Throwable th2) {
                    LOG.debug("Request finished. Closing response stream.");
                    if (response != null && response.body() != null) {
                        response.body().close();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            response = null;
        }
    }

    private String join(String str, List<String> list) {
        int size = list.size() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= size; i++) {
            sb.append(list.get(i));
            if (i != size) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.IdentityClient
    public BleIdentityCore getV2BeaconIdentityPacket() {
        LOG.info("Retrieving Identity packet");
        String v2BeaconIdentityJson = getV2BeaconIdentityJson();
        if (v2BeaconIdentityJson == null) {
            return null;
        }
        return BleIdentityCore.deserialize(v2BeaconIdentityJson);
    }

    public PresenceIdentityClient(String str, AuthTokenProvider authTokenProvider, PersonIdProvider personIdProvider) {
        this(new OkHttpClient(), str, authTokenProvider, personIdProvider);
    }

    public PresenceIdentityClient(OkHttpClient okHttpClient, String str, AuthTokenProvider authTokenProvider) {
        this(okHttpClient, str, authTokenProvider, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Inject
    public PresenceIdentityClient(OkHttpClient okHttpClient, String str, AuthTokenProvider authTokenProvider, PersonIdProvider personIdProvider) {
        this.httpClient = (OkHttpClient) Objects.requireNonNull(okHttpClient);
        this.identityProvider = (AuthTokenProvider) Objects.requireNonNull(authTokenProvider);
        this.personIdProvider = personIdProvider;
        this.identityTokenUri = getTokenIdentityEndpoint(str);
    }
}
