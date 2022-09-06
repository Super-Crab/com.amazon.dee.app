package org.bouncycastle.jsse;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* loaded from: classes4.dex */
public final class BCSSLParameters {
    private BCAlgorithmConstraints algorithmConstraints;
    private String[] applicationProtocols;
    private String[] cipherSuites;
    private String endpointIdentificationAlgorithm;
    private boolean needClientAuth;
    private String[] protocols;
    private List<BCSNIServerName> serverNames;
    private List<BCSNIMatcher> sniMatchers;
    private boolean useCipherSuitesOrder;
    private boolean wantClientAuth;

    public BCSSLParameters() {
        this.applicationProtocols = new String[0];
    }

    public BCSSLParameters(String[] strArr) {
        this.applicationProtocols = new String[0];
        setCipherSuites(strArr);
    }

    public BCSSLParameters(String[] strArr, String[] strArr2) {
        this.applicationProtocols = new String[0];
        setCipherSuites(strArr);
        setProtocols(strArr2);
    }

    private static String[] clone(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    private static <T> List<T> copyList(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        return collection.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(collection));
    }

    public BCAlgorithmConstraints getAlgorithmConstraints() {
        return this.algorithmConstraints;
    }

    public String[] getApplicationProtocols() {
        return (String[]) this.applicationProtocols.clone();
    }

    public String[] getCipherSuites() {
        return clone(this.cipherSuites);
    }

    public String getEndpointIdentificationAlgorithm() {
        return this.endpointIdentificationAlgorithm;
    }

    public boolean getNeedClientAuth() {
        return this.needClientAuth;
    }

    public String[] getProtocols() {
        return clone(this.protocols);
    }

    public Collection<BCSNIMatcher> getSNIMatchers() {
        return copyList(this.sniMatchers);
    }

    public List<BCSNIServerName> getServerNames() {
        return copyList(this.serverNames);
    }

    public boolean getUseCipherSuitesOrder() {
        return this.useCipherSuitesOrder;
    }

    public boolean getWantClientAuth() {
        return this.wantClientAuth;
    }

    public void setAlgorithmConstraints(BCAlgorithmConstraints bCAlgorithmConstraints) {
        this.algorithmConstraints = bCAlgorithmConstraints;
    }

    public void setApplicationProtocols(String[] strArr) {
        if (strArr != null) {
            String[] strArr2 = (String[]) strArr.clone();
            for (String str : strArr2) {
                if (str == null || str.length() < 1) {
                    throw new IllegalArgumentException("'applicationProtocols' entries cannot be null or empty strings");
                }
            }
            this.applicationProtocols = strArr2;
            return;
        }
        throw new NullPointerException("'applicationProtocols' cannot be null");
    }

    public void setCipherSuites(String[] strArr) {
        this.cipherSuites = clone(strArr);
    }

    public void setEndpointIdentificationAlgorithm(String str) {
        this.endpointIdentificationAlgorithm = str;
    }

    public void setNeedClientAuth(boolean z) {
        this.needClientAuth = z;
        this.wantClientAuth = false;
    }

    public void setProtocols(String[] strArr) {
        this.protocols = clone(strArr);
    }

    public void setSNIMatchers(Collection<BCSNIMatcher> collection) {
        List<BCSNIMatcher> copyList;
        if (collection == null) {
            copyList = null;
        } else {
            copyList = copyList(collection);
            HashSet hashSet = new HashSet();
            for (BCSNIMatcher bCSNIMatcher : copyList) {
                int type = bCSNIMatcher.getType();
                if (!hashSet.add(Integer.valueOf(type))) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Found duplicate SNI matcher entry of type ", type));
                }
            }
        }
        this.sniMatchers = copyList;
    }

    public void setServerNames(List<BCSNIServerName> list) {
        List<BCSNIServerName> copyList;
        if (list == null) {
            copyList = null;
        } else {
            copyList = copyList(list);
            HashSet hashSet = new HashSet();
            for (BCSNIServerName bCSNIServerName : copyList) {
                int type = bCSNIServerName.getType();
                if (!hashSet.add(Integer.valueOf(type))) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Found duplicate SNI server name entry of type ", type));
                }
            }
        }
        this.serverNames = copyList;
    }

    public void setUseCipherSuitesOrder(boolean z) {
        this.useCipherSuitesOrder = z;
    }

    public void setWantClientAuth(boolean z) {
        this.wantClientAuth = z;
        this.needClientAuth = false;
    }
}
