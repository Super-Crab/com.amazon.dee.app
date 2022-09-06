package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.ContextWrapper;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.storage.LocalDataStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executors;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ed extends ContextWrapper {
    private final Map<String, Object> lm;

    private ed(Context context) {
        super(context);
        this.lm = GeneratedOutlineSupport1.outline136();
    }

    public static ed M(Context context) {
        if (context instanceof ed) {
            return (ed) context;
        }
        return new ed(context);
    }

    public gg dV() {
        return ((gh) getSystemService("dcp_data_storage_factory")).dV();
    }

    public synchronized co dW() {
        return cp.a(new cq(this));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getFilesDir() {
        return super.getFilesDir();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        Object systemService = getBaseContext().getSystemService(str);
        if (systemService != null) {
            return systemService;
        }
        Object obj = this.lm.get(str);
        if (obj == null) {
            if ("dcp_system".equals(str)) {
                obj = new eh();
            } else if ("dcp_device_info".equals(str)) {
                obj = dl.C(this);
            } else if ("dcp_account_manager".equals(str)) {
                obj = hq.ah(this);
            } else if ("dcp_single_threaded_executor".equals(str)) {
                obj = Executors.newSingleThreadExecutor(ji.dG("MAP-ServiceWrappingContextThread"));
            } else if ("dcp_amazon_account_man".equals(str)) {
                obj = new AmazonAccountManager(this);
            } else if ("dcp_authenticated_url_connection_factory".equals(str)) {
                obj = new dd();
            } else if ("dcp_token_cache_holder".equals(str)) {
                obj = gw.ac(this);
            } else if ("dcp_data_storage_factory".equals(str)) {
                obj = gi.T(this);
            } else if ("sso_map_account_manager_communicator".equals(str)) {
                obj = new CentralAccountManagerCommunication(this);
            } else if ("dcp_token_mangement".equals(str)) {
                obj = new TokenManagement(this);
            } else if ("sso_local_datastorage".equals(str)) {
                obj = LocalDataStorage.W(this);
            } else if ("sso_alarm_maanger".equals(str)) {
                obj = new cw(this);
            } else if ("sso_platform".equals(str)) {
                obj = new ds(this);
            } else if ("sso_webservice_caller_creator".equals(str)) {
                obj = new cz(this);
            } else if ("dcp_wifi".equals(str)) {
                obj = new eo(this);
            } else if ("sso_telephony_service".equals(str)) {
                obj = new ei(this);
            } else {
                obj = "sso_window_manager".equals(str) ? new ep(this) : null;
            }
            this.lm.put(str, obj);
        }
        return obj;
    }
}
