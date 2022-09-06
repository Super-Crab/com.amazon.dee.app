package com.amazonaws.auth.policy;

import com.amazon.dee.app.BuildConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes13.dex */
public class Principal {
    private final String id;
    private final String provider;
    public static final Principal AllUsers = new Principal("AWS", "*");
    public static final Principal AllServices = new Principal("Service", "*");
    public static final Principal AllWebProviders = new Principal("Federated", "*");
    public static final Principal All = new Principal("*", "*");

    /* loaded from: classes13.dex */
    public enum Services {
        AWSDataPipeline("datapipeline.amazonaws.com"),
        AmazonElasticTranscoder("elastictranscoder.amazonaws.com"),
        AmazonEC2("ec2.amazonaws.com"),
        AWSOpsWorks("opsworks.amazonaws.com"),
        AWSCloudHSM("cloudhsm.amazonaws.com"),
        AllServices("*");
        
        private String serviceId;

        Services(String str) {
            this.serviceId = str;
        }

        public static Services fromString(String str) {
            Services[] values;
            if (str != null) {
                for (Services services : values()) {
                    if (services.getServiceId().equalsIgnoreCase(str)) {
                        return services;
                    }
                }
                return null;
            }
            return null;
        }

        public String getServiceId() {
            return this.serviceId;
        }
    }

    /* loaded from: classes13.dex */
    public enum WebIdentityProviders {
        Facebook("graph.facebook.com"),
        Google("accounts.google.com"),
        Amazon(BuildConfig.AUTH_HOST),
        AllProviders("*");
        
        private String webIdentityProvider;

        WebIdentityProviders(String str) {
            this.webIdentityProvider = str;
        }

        public static WebIdentityProviders fromString(String str) {
            WebIdentityProviders[] values;
            if (str != null) {
                for (WebIdentityProviders webIdentityProviders : values()) {
                    if (webIdentityProviders.getWebIdentityProvider().equalsIgnoreCase(str)) {
                        return webIdentityProviders;
                    }
                }
                return null;
            }
            return null;
        }

        public String getWebIdentityProvider() {
            return this.webIdentityProvider;
        }
    }

    public Principal(Services services) {
        if (services != null) {
            this.id = services.getServiceId();
            this.provider = "Service";
            return;
        }
        throw new IllegalArgumentException("Null AWS service name specified");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Principal)) {
            return false;
        }
        Principal principal = (Principal) obj;
        return getProvider().equals(principal.getProvider()) && getId().equals(principal.getId());
    }

    public String getId() {
        return this.id;
    }

    public String getProvider() {
        return this.provider;
    }

    public int hashCode() {
        return this.id.hashCode() + GeneratedOutlineSupport1.outline7(this.provider, 31, 31);
    }

    public Principal(String str, String str2) {
        this.provider = str;
        this.id = "AWS".equals(str) ? str2.replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "") : str2;
    }

    public Principal(String str) {
        if (str != null) {
            this.id = str.replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "");
            this.provider = "AWS";
            return;
        }
        throw new IllegalArgumentException("Null AWS account ID specified");
    }

    public Principal(WebIdentityProviders webIdentityProviders) {
        if (webIdentityProviders != null) {
            this.id = webIdentityProviders.getWebIdentityProvider();
            this.provider = "Federated";
            return;
        }
        throw new IllegalArgumentException("Null web identity provider specified");
    }
}
