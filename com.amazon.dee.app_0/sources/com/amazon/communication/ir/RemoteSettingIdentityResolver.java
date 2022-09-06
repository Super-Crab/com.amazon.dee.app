package com.amazon.communication.ir;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.ExplicitServiceIdentity;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.communication.directorservice.DirectorServiceClient;
import com.amazon.communication.directorservice.GetEndpointFailedException;
import com.amazon.communication.directorservice.GetEndpointResponse;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.utils.StringUtils;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.ValueGauge;
import com.dp.utils.FailFast;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class RemoteSettingIdentityResolver implements IdentityResolver {
    protected static final String DOMAIN_DEFAULT_VALUE = "prod";
    public static final String DOMAIN_KEY = "ir-master.domain";
    private static final String GATEWAY_SERVICE_NAME = "DPGatewayService";
    protected static final String REALM_DEFAULT_VALUE = "USAmazon";
    public static final String REALM_KEY = "ir-master.realm";
    private static final String SDCS_SERVICE_NAME = "SynchronousDeviceCallingService";
    private static final String SERVICE_ENDPOINT_KEY_DELIMITER = ".";
    protected static final String WILDCARD = "*";
    private static final DPLogger log = new DPLogger("TComm.RemoteSettingIdentityResolver");
    private final DirectorServiceClient mDirectorServiceClient;
    private final IdentityResolverSetting mIdentityResolverSetting;
    protected volatile IrMaster mIrMaster;
    protected final AtomicBoolean mIsInitialized;

    /* renamed from: com.amazon.communication.ir.RemoteSettingIdentityResolver$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$communication$identity$IRServiceEndpoint$Scheme = new int[IRServiceEndpoint.Scheme.values().length];

        static {
            try {
                $SwitchMap$amazon$communication$identity$IRServiceEndpoint$Scheme[IRServiceEndpoint.Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$communication$identity$IRServiceEndpoint$Scheme[IRServiceEndpoint.Scheme.WS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$communication$identity$IRServiceEndpoint$Scheme[IRServiceEndpoint.Scheme.HTTPS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$communication$identity$IRServiceEndpoint$Scheme[IRServiceEndpoint.Scheme.WSS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class IdentityResolverSetting {
        IdentityResolverSetting() {
        }

        private static String buildServiceEndpointKey(String str, String str2, String str3) {
            return GeneratedOutlineSupport1.outline77(str, ".", str2, ".", str3);
        }

        public String getDeviceGlobalDomain() {
            return RemoteSettingManager.getOptStringValue(RemoteSettingIdentityResolver.DOMAIN_KEY, "prod");
        }

        public String getDeviceGlobalRealm() {
            return RemoteSettingManager.getOptStringValue(RemoteSettingIdentityResolver.REALM_KEY, "USAmazon");
        }

        public String retrieveServiceEndpointJson(String str, String str2, String str3) {
            RemoteSettingIdentityResolver.log.info("retrieveServiceEndpointJson", "retrieving service endpoint JSON", "serviceName", str, "domain", str2, HttpAuthHeader.Parameters.Realm, str3);
            String buildServiceEndpointKey = buildServiceEndpointKey(str, str2, str3);
            String jsonValue = RemoteSettingManager.getJsonValue("IdentityResolver." + buildServiceEndpointKey);
            if (jsonValue == null) {
                String buildServiceEndpointKey2 = buildServiceEndpointKey(str, str2, "*");
                return RemoteSettingManager.getJsonValue("IdentityResolver." + buildServiceEndpointKey2);
            }
            return jsonValue;
        }
    }

    public RemoteSettingIdentityResolver(DirectorServiceClient directorServiceClient) {
        this(directorServiceClient, new IdentityResolverSetting());
    }

    private IRServiceEndpoint getEndpointForServiceNameDomainAndRealm(String str, String str2, String str3) {
        IRServiceEndpoint retrieveAndResolveServiceEndpoint;
        log.debug("getEndpointForServiceNameDomainAndRealm", "looking up in cache", "serviceName", str, "domain", str2, HttpAuthHeader.Parameters.Realm, str3);
        if (!StringUtils.isNullOrEmpty(str)) {
            if (this.mIsInitialized.get()) {
                if (str2 == null) {
                    str2 = this.mIrMaster.getDomain();
                }
                if (str3 == null) {
                    str3 = this.mIrMaster.getRealm();
                }
                FailFast.expectFalse(StringUtils.isNullOrEmpty(str2), "domainToUse");
                FailFast.expectFalse(StringUtils.isNullOrEmpty(str3), "realmToUse");
                IRServiceEndpoint retrieveAndResolveServiceEndpoint2 = retrieveAndResolveServiceEndpoint(str, str2, str3);
                if (retrieveAndResolveServiceEndpoint2 != null) {
                    return retrieveAndResolveServiceEndpoint2;
                }
                if ("master".equals(this.mIrMaster.getDomain()) && (retrieveAndResolveServiceEndpoint = retrieveAndResolveServiceEndpoint(str, "prod", str3)) != null) {
                    return retrieveAndResolveServiceEndpoint;
                }
                IRServiceEndpoint retrieveAndResolveServiceEndpoint3 = retrieveAndResolveServiceEndpoint(str, "*", str3);
                if (retrieveAndResolveServiceEndpoint3 != null) {
                    return retrieveAndResolveServiceEndpoint3;
                }
                log.warn("getEndpointForServiceName", "failed to resolve service endpoint", "serviceName", str);
                return null;
            }
            log.warn("getEndpointForServiceName", "not initialized", new Object[0]);
            throw new IllegalStateException("initialize() must be called before using this method");
        }
        throw new IllegalArgumentException("serviceName is null or empty");
    }

    private IRServiceEndpoint getServiceEndpointFromDirectedEndpoint(GetEndpointResponse getEndpointResponse, IRServiceEndpoint iRServiceEndpoint) {
        Integer valueOf;
        Integer num;
        try {
            URI uri = new URI(getEndpointResponse.getEndpointUrl());
            if (uri.getPort() == -1) {
                log.error("getServiceEndpointFromDirectedEndpoint", "There's no port in the directed endpoint uri", "directorResponse", getEndpointResponse);
                return null;
            }
            int ordinal = IRServiceEndpoint.Scheme.parse(uri.getScheme()).ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            valueOf = null;
                            num = null;
                            return new IRServiceEndpointImpl(uri.getHost(), iRServiceEndpoint.getDomain(), iRServiceEndpoint.getRealm(), iRServiceEndpoint.getDirectConnection(), iRServiceEndpoint.getDataCompression(), iRServiceEndpoint.getClearTextConnection(), iRServiceEndpoint.getTimeout(), valueOf, num);
                        }
                    }
                }
                num = Integer.valueOf(uri.getPort());
                valueOf = null;
                return new IRServiceEndpointImpl(uri.getHost(), iRServiceEndpoint.getDomain(), iRServiceEndpoint.getRealm(), iRServiceEndpoint.getDirectConnection(), iRServiceEndpoint.getDataCompression(), iRServiceEndpoint.getClearTextConnection(), iRServiceEndpoint.getTimeout(), valueOf, num);
            }
            valueOf = Integer.valueOf(uri.getPort());
            num = null;
            return new IRServiceEndpointImpl(uri.getHost(), iRServiceEndpoint.getDomain(), iRServiceEndpoint.getRealm(), iRServiceEndpoint.getDirectConnection(), iRServiceEndpoint.getDataCompression(), iRServiceEndpoint.getClearTextConnection(), iRServiceEndpoint.getTimeout(), valueOf, num);
        } catch (URISyntaxException e) {
            log.error("getServiceEndpointFromDirectedEndpoint", "failed to parse directed endpoint url", "URISyntaxException:", e);
            return null;
        }
    }

    private IRServiceEndpoint retrieveAndResolveServiceEndpoint(String str, String str2, String str3) {
        IRServiceEndpoint cachedEndpoint;
        log.verbose("retrieveAndResolveServiceEndpoint", "resolving service endpoint and caching it", "serviceName", str, "domain", str2, HttpAuthHeader.Parameters.Realm, str3);
        if ((str.equals(GATEWAY_SERVICE_NAME) || str.equals(SDCS_SERVICE_NAME)) && str3.equals("USAmazon") && (cachedEndpoint = RemoteSettingManager.getCachedEndpoint(str, str2)) != null) {
            return cachedEndpoint;
        }
        String retrieveServiceEndpointJson = this.mIdentityResolverSetting.retrieveServiceEndpointJson(str, str2, str3);
        if (retrieveServiceEndpointJson != null) {
            try {
                return IRServiceEndpointImpl.parse(str2, str3, retrieveServiceEndpointJson);
            } catch (InvalidIRFileFormatException e) {
                log.error("retrieveAndResolveServiceEndpoint", "error parsing service endpoint", "domain", str2, "json", retrieveServiceEndpointJson, e);
                return null;
            } catch (IOException e2) {
                log.error("retrieveAndResolveServiceEndpoint", "error parsing service endpoint", "domain", str2, "json", retrieveServiceEndpointJson, e2);
                return null;
            }
        }
        log.debug("retrieveAndResolveServiceEndpoint", "failed to retrieve service endpoint JSON", "serviceName", str, "domain", str2, HttpAuthHeader.Parameters.Realm, str3);
        return null;
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint getEndpointForServiceName(String str) {
        return getEndpointForServiceNameDomainAndRealm(str, null, null);
    }

    public synchronized void initialize() {
        if (!this.mIsInitialized.get()) {
            String deviceGlobalDomain = this.mIdentityResolverSetting.getDeviceGlobalDomain();
            String deviceGlobalRealm = this.mIdentityResolverSetting.getDeviceGlobalRealm();
            try {
                MetricRegistry orCreate = SharedMetricRegistries.getOrCreate(RouteName.MAIN);
                ((ValueGauge) orCreate.metric("com.amazon.tcomm.domain", ValueGauge.STRING)).setValue(deviceGlobalDomain);
                ((ValueGauge) orCreate.metric("com.amazon.tcomm.realm", ValueGauge.STRING)).setValue(deviceGlobalRealm);
            } catch (Exception e) {
                log.error("initialize", "Exception occurred when updating domain and realm values in MetricRegistry", e);
            }
            log.verbose("initialize", "constructing IR master", "domain", deviceGlobalDomain, HttpAuthHeader.Parameters.Realm, deviceGlobalRealm);
            try {
                this.mIrMaster = new IrMasterImpl(deviceGlobalDomain, deviceGlobalRealm);
                this.mIsInitialized.set(true);
            } catch (IllegalArgumentException e2) {
                log.error("initialize", "error constructing IR master", "domain", deviceGlobalDomain, HttpAuthHeader.Parameters.Realm, deviceGlobalRealm, e2);
            }
            if (!this.mIsInitialized.get()) {
                log.debug("initialize", "initialize failed", new Object[0]);
            }
        } else {
            log.debug("initialize", "already initialized", new Object[0]);
        }
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity) {
        return resolveServiceEndpoint(serviceIdentity, Purpose.REGULAR);
    }

    RemoteSettingIdentityResolver(DirectorServiceClient directorServiceClient, IdentityResolverSetting identityResolverSetting) {
        this.mDirectorServiceClient = directorServiceClient;
        this.mIdentityResolverSetting = identityResolverSetting;
        this.mIsInitialized = new AtomicBoolean(false);
    }

    public IRServiceEndpoint resolveServiceEndpoint(ExplicitServiceIdentity explicitServiceIdentity) {
        return new IRServiceEndpointImpl(explicitServiceIdentity.getHostname(), explicitServiceIdentity.getDomain(), explicitServiceIdentity.getRealm(), IRServiceEndpoint.DirectConnection.parse(explicitServiceIdentity.getDirectConnection()), IRServiceEndpoint.DataCompression.parse(explicitServiceIdentity.getDataCompression()), IRServiceEndpoint.ClearTextConnection.parse(explicitServiceIdentity.getClearTextConnection()), explicitServiceIdentity.getTimeout().intValue(), explicitServiceIdentity.getPort(), explicitServiceIdentity.getSecurePort());
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity, Purpose purpose) {
        if (serviceIdentity instanceof ExplicitServiceIdentity) {
            return resolveServiceEndpoint((ExplicitServiceIdentity) serviceIdentity);
        }
        IRServiceEndpoint endpointForServiceNameDomainAndRealm = getEndpointForServiceNameDomainAndRealm(serviceIdentity.getServiceName(), serviceIdentity.getDomain(), serviceIdentity.getRealm());
        if (endpointForServiceNameDomainAndRealm == null) {
            log.warn("resolveServiceEndpoint", "Failed to resolve endpoint. Return null", "endpoint", serviceIdentity, "purpose", purpose);
            return null;
        } else if (endpointForServiceNameDomainAndRealm.getDirectorServiceName() == null) {
            log.verbose("resolveServiceEndpoint", "No need to use director service", "serviceEndpoint", endpointForServiceNameDomainAndRealm);
            return endpointForServiceNameDomainAndRealm;
        } else {
            log.verbose("resolveServiceEndpoint", "Need to use director service", "serviceEndpoint", endpointForServiceNameDomainAndRealm, "purpose", purpose);
            IRServiceEndpoint endpointForServiceName = getEndpointForServiceName(endpointForServiceNameDomainAndRealm.getDirectorServiceName());
            try {
                GetEndpointResponse endpoint = this.mDirectorServiceClient.getEndpoint(endpointForServiceName.toResolvedUri(IRServiceEndpoint.Scheme.HTTPS), serviceIdentity.getServiceName(), purpose.getPurpose());
                log.verbose("resolveServiceEndpoint", "Got directed endpoint", "response", endpoint);
                IRServiceEndpoint serviceEndpointFromDirectedEndpoint = getServiceEndpointFromDirectedEndpoint(endpoint, endpointForServiceNameDomainAndRealm);
                log.verbose("resolveServiceEndpoint", "Returning resolved directed endpoint", "IREndpoint", serviceEndpointFromDirectedEndpoint);
                return serviceEndpointFromDirectedEndpoint;
            } catch (GetEndpointFailedException e) {
                log.warn("resolveServiceEndpoint", "Got exception while getting the endpoint", "directorEndpoint", endpointForServiceName, ADMRegistrationConstants.CALL_EXCEPTION, e);
                return null;
            }
        }
    }
}
