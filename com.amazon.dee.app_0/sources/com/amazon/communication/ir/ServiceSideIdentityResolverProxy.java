package com.amazon.communication.ir;

import amazon.communication.identity.EndpointIdentityFactory;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import com.amazon.communication.ir.IIdentityResolver;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class ServiceSideIdentityResolverProxy extends IIdentityResolver.Stub {
    private static final DPLogger log = new DPLogger("TComm.ServiceSideIdentityResolverProxy");
    private final IdentityResolver mIdentityResolver;

    public ServiceSideIdentityResolverProxy(IdentityResolver identityResolver) {
        this.mIdentityResolver = identityResolver;
    }

    @Override // com.amazon.communication.ir.IIdentityResolver
    public ParcelableIRServiceEndpoint getEndpointForServiceName(String str) {
        try {
            IRServiceEndpoint endpointForServiceName = this.mIdentityResolver.getEndpointForServiceName(str);
            if (endpointForServiceName != null) {
                return new ParcelableIRServiceEndpoint(endpointForServiceName);
            }
            return null;
        } catch (RuntimeException e) {
            log.warn("getEndpointForServiceName", "Exception occurred!", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.ir.IIdentityResolver
    public ParcelableIRServiceEndpoint resolveServiceEndpoint(String str, String str2, String str3) {
        try {
            IRServiceEndpoint resolveServiceEndpoint = this.mIdentityResolver.resolveServiceEndpoint(EndpointIdentityFactory.createServiceIdentity(str, str2, str3));
            if (resolveServiceEndpoint != null) {
                return new ParcelableIRServiceEndpoint(resolveServiceEndpoint);
            }
            return null;
        } catch (RuntimeException e) {
            log.warn("resolveServiceEndpoint", "Exception occurred!", e);
            throw e;
        }
    }
}
