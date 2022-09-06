package com.amazon.deecomms.common.network.acmsrecipes;

import android.text.TextUtils;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.oobe.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class GetAccounts {
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OOBE_DEPROVISIONED_GET_ACCOUNTS);

    private String getAccountsInternal() throws ServiceException, IOException {
        IHttpClient.Response mo3640execute = this.mClient.request(AppUrl.OOBE_ACCOUNTS).authenticated().get().mo3640execute();
        try {
            String body = mo3640execute.getBody();
            mo3640execute.close();
            return body;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (mo3640execute != null) {
                    try {
                        mo3640execute.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public List<Person> execute() throws ServiceException, IOException {
        String accountsInternal = getAccountsInternal();
        if (!TextUtils.isEmpty(accountsInternal)) {
            return (List) new JacksonJSONConverter().fromJson(accountsInternal, new TypeReference<ArrayList<Person>>() { // from class: com.amazon.deecomms.common.network.acmsrecipes.GetAccounts.1
            });
        }
        return null;
    }
}
