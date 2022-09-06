package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateIdentityPoolResult implements Serializable {
    private Boolean allowUnauthenticatedIdentities;
    private List<CognitoIdentityProvider> cognitoIdentityProviders;
    private String developerProviderName;
    private String identityPoolId;
    private String identityPoolName;
    private List<String> openIdConnectProviderARNs;
    private List<String> samlProviderARNs;
    private Map<String, String> supportedLoginProviders;

    public UpdateIdentityPoolResult addSupportedLoginProvidersEntry(String str, String str2) {
        if (this.supportedLoginProviders == null) {
            this.supportedLoginProviders = new HashMap();
        }
        if (!this.supportedLoginProviders.containsKey(str)) {
            this.supportedLoginProviders.put(str, str2);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline78(str, GeneratedOutlineSupport1.outline107("Duplicated keys ("), ") are provided."));
    }

    public UpdateIdentityPoolResult clearSupportedLoginProvidersEntries() {
        this.supportedLoginProviders = null;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateIdentityPoolResult)) {
            return false;
        }
        UpdateIdentityPoolResult updateIdentityPoolResult = (UpdateIdentityPoolResult) obj;
        if ((updateIdentityPoolResult.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getIdentityPoolId() != null && !updateIdentityPoolResult.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
        if ((updateIdentityPoolResult.getIdentityPoolName() == null) ^ (getIdentityPoolName() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getIdentityPoolName() != null && !updateIdentityPoolResult.getIdentityPoolName().equals(getIdentityPoolName())) {
            return false;
        }
        if ((updateIdentityPoolResult.getAllowUnauthenticatedIdentities() == null) ^ (getAllowUnauthenticatedIdentities() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getAllowUnauthenticatedIdentities() != null && !updateIdentityPoolResult.getAllowUnauthenticatedIdentities().equals(getAllowUnauthenticatedIdentities())) {
            return false;
        }
        if ((updateIdentityPoolResult.getSupportedLoginProviders() == null) ^ (getSupportedLoginProviders() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getSupportedLoginProviders() != null && !updateIdentityPoolResult.getSupportedLoginProviders().equals(getSupportedLoginProviders())) {
            return false;
        }
        if ((updateIdentityPoolResult.getDeveloperProviderName() == null) ^ (getDeveloperProviderName() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getDeveloperProviderName() != null && !updateIdentityPoolResult.getDeveloperProviderName().equals(getDeveloperProviderName())) {
            return false;
        }
        if ((updateIdentityPoolResult.getOpenIdConnectProviderARNs() == null) ^ (getOpenIdConnectProviderARNs() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getOpenIdConnectProviderARNs() != null && !updateIdentityPoolResult.getOpenIdConnectProviderARNs().equals(getOpenIdConnectProviderARNs())) {
            return false;
        }
        if ((updateIdentityPoolResult.getCognitoIdentityProviders() == null) ^ (getCognitoIdentityProviders() == null)) {
            return false;
        }
        if (updateIdentityPoolResult.getCognitoIdentityProviders() != null && !updateIdentityPoolResult.getCognitoIdentityProviders().equals(getCognitoIdentityProviders())) {
            return false;
        }
        if ((updateIdentityPoolResult.getSamlProviderARNs() == null) ^ (getSamlProviderARNs() == null)) {
            return false;
        }
        return updateIdentityPoolResult.getSamlProviderARNs() == null || updateIdentityPoolResult.getSamlProviderARNs().equals(getSamlProviderARNs());
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

    public String getIdentityPoolId() {
        return this.identityPoolId;
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
        int hashCode = ((((((((((((((getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode()) + 31) * 31) + (getIdentityPoolName() == null ? 0 : getIdentityPoolName().hashCode())) * 31) + (getAllowUnauthenticatedIdentities() == null ? 0 : getAllowUnauthenticatedIdentities().hashCode())) * 31) + (getSupportedLoginProviders() == null ? 0 : getSupportedLoginProviders().hashCode())) * 31) + (getDeveloperProviderName() == null ? 0 : getDeveloperProviderName().hashCode())) * 31) + (getOpenIdConnectProviderARNs() == null ? 0 : getOpenIdConnectProviderARNs().hashCode())) * 31) + (getCognitoIdentityProviders() == null ? 0 : getCognitoIdentityProviders().hashCode())) * 31;
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

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
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
        if (getIdentityPoolId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1072.append(getIdentityPoolId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIdentityPoolName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("IdentityPoolName: ");
            outline1073.append(getIdentityPoolName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getAllowUnauthenticatedIdentities() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("AllowUnauthenticatedIdentities: ");
            outline1074.append(getAllowUnauthenticatedIdentities());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getSupportedLoginProviders() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("SupportedLoginProviders: ");
            outline1075.append(getSupportedLoginProviders());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getDeveloperProviderName() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("DeveloperProviderName: ");
            outline1076.append(getDeveloperProviderName());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getOpenIdConnectProviderARNs() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("OpenIdConnectProviderARNs: ");
            outline1077.append(getOpenIdConnectProviderARNs());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getCognitoIdentityProviders() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("CognitoIdentityProviders: ");
            outline1078.append(getCognitoIdentityProviders());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getSamlProviderARNs() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("SamlProviderARNs: ");
            outline1079.append(getSamlProviderARNs());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateIdentityPoolResult withAllowUnauthenticatedIdentities(Boolean bool) {
        this.allowUnauthenticatedIdentities = bool;
        return this;
    }

    public UpdateIdentityPoolResult withCognitoIdentityProviders(CognitoIdentityProvider... cognitoIdentityProviderArr) {
        if (getCognitoIdentityProviders() == null) {
            this.cognitoIdentityProviders = new ArrayList(cognitoIdentityProviderArr.length);
        }
        for (CognitoIdentityProvider cognitoIdentityProvider : cognitoIdentityProviderArr) {
            this.cognitoIdentityProviders.add(cognitoIdentityProvider);
        }
        return this;
    }

    public UpdateIdentityPoolResult withDeveloperProviderName(String str) {
        this.developerProviderName = str;
        return this;
    }

    public UpdateIdentityPoolResult withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public UpdateIdentityPoolResult withIdentityPoolName(String str) {
        this.identityPoolName = str;
        return this;
    }

    public UpdateIdentityPoolResult withOpenIdConnectProviderARNs(String... strArr) {
        if (getOpenIdConnectProviderARNs() == null) {
            this.openIdConnectProviderARNs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.openIdConnectProviderARNs.add(str);
        }
        return this;
    }

    public UpdateIdentityPoolResult withSamlProviderARNs(String... strArr) {
        if (getSamlProviderARNs() == null) {
            this.samlProviderARNs = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.samlProviderARNs.add(str);
        }
        return this;
    }

    public UpdateIdentityPoolResult withSupportedLoginProviders(Map<String, String> map) {
        this.supportedLoginProviders = map;
        return this;
    }

    public UpdateIdentityPoolResult withCognitoIdentityProviders(Collection<CognitoIdentityProvider> collection) {
        setCognitoIdentityProviders(collection);
        return this;
    }

    public UpdateIdentityPoolResult withOpenIdConnectProviderARNs(Collection<String> collection) {
        setOpenIdConnectProviderARNs(collection);
        return this;
    }

    public UpdateIdentityPoolResult withSamlProviderARNs(Collection<String> collection) {
        setSamlProviderARNs(collection);
        return this;
    }
}
