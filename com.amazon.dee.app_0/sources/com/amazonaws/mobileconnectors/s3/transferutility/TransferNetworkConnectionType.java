package com.amazonaws.mobileconnectors.s3.transferutility;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum TransferNetworkConnectionType {
    ANY { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.1
        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected();
        }
    },
    WIFI { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.2
        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
        }
    },
    MOBILE { // from class: com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType.3
        @Override // com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkConnectionType
        protected boolean verify(NetworkInfo networkInfo) {
            return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 0;
        }
    };
    
    private static final Log LOGGER;
    private static final Map<String, TransferNetworkConnectionType> MAP = new HashMap();

    static {
        TransferNetworkConnectionType[] values;
        for (TransferNetworkConnectionType transferNetworkConnectionType : values()) {
            MAP.put(transferNetworkConnectionType.toString(), transferNetworkConnectionType);
        }
        LOGGER = LogFactory.getLog(TransferNetworkConnectionType.class);
    }

    public static TransferNetworkConnectionType getConnectionType(String str) {
        if (MAP.containsKey(str)) {
            return MAP.get(str);
        }
        Log log = LOGGER;
        log.error("Unknown connection type " + str + " transfer will have connection type set to ANY.");
        return ANY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnected(ConnectivityManager connectivityManager) {
        return verify(connectivityManager.getActiveNetworkInfo());
    }

    protected abstract boolean verify(NetworkInfo networkInfo);
}
