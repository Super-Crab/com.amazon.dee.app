package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateIdentityPoolRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean allowUnauthenticatedIdentities;
    private List<CognitoIdentityProvider> cognitoIdentityProviders;
    private String developerProviderName;
    private String identityPoolName;
    private List<String> openIdConnectProviderARNs;
    private List<String> samlProviderARNs;
    private Map<String, String> supportedLoginProviders;

    public CreateIdentityPoolRequest addSupportedLoginProvidersEntry(String str, String str2) {
        if (this.supportedLoginProviders == null) {
            this.supportedLoginProviders = new HashMap();
        }
        if (!this.supportedLoginProviders.containsKey(str)) {
            this.supportedLoginProviders.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public CreateIdentityPoolRequest clearSupportedLoginProvidersEntries() {
        this.supportedLoginProviders = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateIdentityPoolRequest)) {
            return false;
        }
        CreateIdentityPoolRequest createIdentityPoolRequest = (CreateIdentityPoolRequest) obj;
        if ((createIdentityPoolRequest.getIdentityPoolName() == null) ^ (getIdentityPoolName() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getIdentityPoolName() != null && !createIdentityPoolRequest.getIdentityPoolName().equals(getIdentityPoolName())) {
            return false;
        }
        if ((createIdentityPoolRequest.getAllowUnauthenticatedIdentities() == null) ^ (getAllowUnauthenticatedIdentities() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getAllowUnauthenticatedIdentities() != null && !createIdentityPoolRequest.getAllowUnauthenticatedIdentities().equals(getAllowUnauthenticatedIdentities())) {
            return false;
        }
        if ((createIdentityPoolRequest.getSupportedLoginProviders() == null) ^ (getSupportedLoginProviders() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getSupportedLoginProviders() != null && !createIdentityPoolRequest.getSupportedLoginProviders().equals(getSupportedLoginProviders())) {
            return false;
        }
        if ((createIdentityPoolRequest.getDeveloperProviderName() == null) ^ (getDeveloperProviderName() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getDeveloperProviderName() != null && !createIdentityPoolRequest.getDeveloperProviderName().equals(getDeveloperProviderName())) {
            return false;
        }
        if ((createIdentityPoolRequest.getOpenIdConnectProviderARNs() == null) ^ (getOpenIdConnectProviderARNs() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getOpenIdConnectProviderARNs() != null && !createIdentityPoolRequest.getOpenIdConnectProviderARNs().equals(getOpenIdConnectProviderARNs())) {
            return false;
        }
        if ((createIdentityPoolRequest.getCognitoIdentityProviders() == null) ^ (getCognitoIdentityProviders() == null)) {
            return false;
        }
        if (createIdentityPoolRequest.getCognitoIdentityProviders() != null && !createIdentityPoolRequest.getCognitoIdentityProviders().equals(getCognitoIdentityProviders())) {
            return false;
        }
        if ((createIdentityPoolRequest.getSamlProviderARNs() == null) ^ (getSamlProviderARNs() == null)) {
            return false;
        }
        return createIdentityPoolRequest.getSamlProviderARNs() == null || createIdentityPoolRequest.getSamlProviderARNs().equals(getSamlProviderARNs());
    }

    public Boolean getAllowUnauthenticatedIdentities() {
        return this.allowUnauthenticatedIdentities;
    }

    public List<CognitoIdentityProvider> getCognitoIdentityProviders() {
        return this.cognitoIdentityProviders;
    }

    public String getDeveloperProviderName() {
        return this.developerProviderName;
    }

    public String getIdentityPoolName() {
        return this.identityPoolName;
    }

    public List<String> getOpenIdConnectProviderARNs() {
        return this.openIdConnectProviderARNs;
    }

    public List<String> getSamlProviderARNs() {
        return this.samlProviderARNs;
    }

    public Map<String, String> getSupportedLoginProviders() {
        return this.supportedLoginProviders;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getIdentityPoolName() == null ? 0 : getIdentityPoolName().hashCode()) + 31) * 31) + (getAllowUnauthenticatedIdentities() == null ? 0 : getAllowUnauthenticatedIdentities().hashCode())) * 31) + (getSupportedLoginProviders() == null ? 0 : getSupportedLoginProviders().hashCode())) * 31) + (getDeveloperProviderName() == null ? 0 : getDeveloperProviderName().hashCode())) * 31) + (getOpenIdConnectProviderARNs() == null ? 0 : getOpenIdConnectProviderARNs().hashCode())) * 31) + (getCognitoIdentityProviders() == null ? 0 : getCognitoIdentityProviders().hashCode())) * 31;
        if (getSamlProviderARNs() != null) {
            i = getSamlProviderARNs().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isAllowUnauthenticatedIdentities() {
        return this.allowUnauthenticatedIdentities;
    }

    public void setAllowUnauthenticatedIdentities(Boolean bool) {
        this.allowUnauthenticatedIdentities = bool;
    }

    public void setCognitoIdentityProviders(Collection<CognitoIdentityProvider> collection) {
        if (collection == null) {
            this.cognitoIdentityProviders = null;
        } else {
            this.cognitoIdentityProviders = new ArrayList(collection);
        }
    }

    public void setDeveloperProviderName(String str) {
        this.developerProviderName = str;
    }

    public void setIdentityPoolName(String str) {
        this.identityPoolName = str;
    }

    public void setOpenIdConnectProviderARNs(Collection<String> collection) {
        if (collection == null) {
            this.openIdConnectProviderARNs = null;
        } else {
            this.openIdConnectProviderARNs = new ArrayList(collection);
        }
    }

    public void setSamlProviderARNs(Collection<String> collection) {
        if (collection == null) {
            this.samlProviderARNs = null;
        } else {
            this.samlProviderARNs = new ArrayList(collection);
        }
    }

    public void setSupportedLoginProviders(Map<String, String> map) {
        this.supportedLoginProviders = map;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityPoolName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityPoolName: ");
            outline1072.append(getIdentityPoolName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getAllowUnauthenticatedIdentities() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("AllowUnauthenticatedIdentities: ");
            outline1073.append(getAllowUnauthenticatedIdentities());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getSupportedLoginProviders() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("SupportedLoginProviders: ");
            outline1074.append(getSupportedLoginProviders());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDeveloperProviderName() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("DeveloperProviderName: ");
            outline1075.append(getDeveloperProviderName());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getOpenIdConnectProviderARNs() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("OpenIdConnectProviderARNs: ");
            outline1076.append(getOpenIdConnectProviderARNs());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCognitoIdentityProviders() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("CognitoIdentityProviders: ");
            outline1077.append(getCognitoIdentityProviders());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getSamlProviderARNs() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("SamlProviderARNs: ");
            outline1078.append(getSamlProviderARNs());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateIdentityPoolRequest withAllowUnauthenticatedIdentities(Boolean bool) {
        this.allowUnauthenticatedIdentities = bool;
        return this;
    }

    public CreateIdentityPoolRequest withCognitoIdentityProviders(CognitoIdentityProvider... cognitoIdentityProviderArr) {
        if (getCognitoIdentityProviders() == null) {
            this.cognitoIdentityProviders = new ArrayList(cognitoIdentityProviderArr.length);
        }
        for (CognitoIdentityProvider cognitoIdentityProvider : cognitoIdentityProviderArr) {
            this.cognitoIdentityProviders.add(cognitoIdentityProvider);
        }
        return this;
    }

    public CreateIdentityPoolRequest withDeveloperProviderName(String str) {
        this.developerProviderName = str;
        return this;
    }

    public CreateIdentityPoolRequest withIdentityPoolName(String str) {
        this.identityPoolName = str;
        return this;
    }

    public CreateIdentityPoolRequest withOpenIdConnectProviderARNs(String... strArr) {
        if (getOpenIdConnectProviderARNs() == null) {
            this.openIdConnectProviderARNs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.openIdConnectProviderARNs.add(str);
        }
        return this;
    }

    public CreateIdentityPoolRequest withSamlProviderARNs(String... strArr) {
        if (getSamlProviderARNs() == null) {
            this.samlProviderARNs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.samlProviderARNs.add(str);
        }
        return this;
    }

    public CreateIdentityPoolRequest withSupportedLoginProviders(Map<String, String> map) {
        this.supportedLoginProviders = map;
        return this;
    }

    public CreateIdentityPoolRequest withCognitoIdentityProviders(Collection<CognitoIdentityProvider> collection) {
        setCognitoIdentityProviders(collection);
        return this;
    }

    public CreateIdentityPoolRequest withOpenIdConnectProviderARNs(Collection<String> collection) {
        setOpenIdConnectProviderARNs(collection);
        return this;
    }

    public CreateIdentityPoolRequest withSamlProviderARNs(Collection<String> collection) {
        setSamlProviderARNs(collection);
        return this;
    }
}
