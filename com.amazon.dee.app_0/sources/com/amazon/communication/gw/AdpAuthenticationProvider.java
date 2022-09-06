package com.amazon.communication.gw;

import amazon.communication.authentication.SigningException;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import com.amazon.communication.gw.SignatureProvider;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import java.util.concurrent.ExecutionException;
/* loaded from: classes12.dex */
public class AdpAuthenticationProvider implements SignatureProvider {
    protected static final String ALGORITHM = "SHA256withRSA";
    private static final DPLogger log = new DPLogger("TComm.AdpAuthenticationProvider");
    private final Context mContext;

    public AdpAuthenticationProvider(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.communication.gw.SignatureProvider
    public String getAlgorithm() {
        return ALGORITHM;
    }

    @Override // com.amazon.communication.gw.SignatureProvider
    public SignatureProvider.SigningResult sign(byte[] bArr, String str) throws SigningException {
        log.debug("sign", "signing bytes for customer", "directedCustomerId", str);
        try {
            Bundle bundle = ((ADPCorpusSigningAuthenticationMethod) new AuthenticationMethodFactory(this.mContext, str).newAuthenticationMethod(AuthenticationType.ADPAuthenticator)).signCorpus(bArr, new Bundle(), null).get();
            return new SignatureProvider.SigningResult(Base64.decode(bundle.getString(ADPCorpusSigningAuthenticationMethod.KEY_ADP_AUTH_SIGNATURE), 0), bundle.getString("adp_token"));
        } catch (MAPCallbackErrorException e) {
            throw new SigningException(e);
        } catch (InterruptedException e2) {
            throw new SigningException(e2);
        } catch (ExecutionException e3) {
            throw new SigningException(e3);
        }
    }
}
