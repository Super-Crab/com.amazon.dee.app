package com.amazon.identity.auth.device;

import android.net.Uri;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import java.util.Arrays;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class gq {
    public static final String[] oL = {"value"};
    public static final String[] oM = {"key", "value", "namespace"};
    public static final List<String> oN = Arrays.asList("map_major_version", "map_minor_version", "current_device_type", "dsn_override", "map_sw_version", "map_init_version", "map_brazil_version");
    public static final List<String> oO = Arrays.asList("directedId", "namespace", "userdata_account", "token_account", "display_name", "userdata_key", "userdata_value", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_value", "device_data_key", "device_data_value", "timestamp_key", "deleted_key", "dirty_key");

    private static Uri V(String str, String str2) {
        return Uri.parse(String.format("content://%s%s", str, str2));
    }

    public static Uri cA(String str) {
        return V(str, "/device_data");
    }

    public static Uri cB(String str) {
        return V(str, "/map_info");
    }

    public static Uri cC(String str) {
        return V(str, "/all_data");
    }

    public static Uri cD(String str) {
        return V(str, "/all_deleted_data");
    }

    public static Uri cE(String str) {
        return V(str, "/bulk_data");
    }

    public static Uri cF(String str) {
        return V(str, "/generate_common_info");
    }

    public static Uri cx(String str) {
        return V(str, AppUrl.OOBE_ACCOUNTS);
    }

    public static Uri cy(String str) {
        return V(str, "/userdata");
    }

    public static Uri cz(String str) {
        return V(str, "/tokens");
    }
}
