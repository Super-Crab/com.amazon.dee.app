package org.bouncycastle.jsse.provider;

import java.security.Principal;
import java.util.List;
import org.bouncycastle.jsse.provider.NamedGroupInfo;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class JsseSecurityParameters {
    List<SignatureSchemeInfo> localSigSchemes;
    List<SignatureSchemeInfo> localSigSchemesCert;
    NamedGroupInfo.PerConnection namedGroups;
    List<SignatureSchemeInfo> peerSigSchemes;
    List<SignatureSchemeInfo> peerSigSchemesCert;
    List<byte[]> statusResponses;
    Principal[] trustedIssuers;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.namedGroups = null;
        this.localSigSchemes = null;
        this.localSigSchemesCert = null;
        this.peerSigSchemes = null;
        this.peerSigSchemesCert = null;
        this.statusResponses = null;
        this.trustedIssuers = null;
    }
}
