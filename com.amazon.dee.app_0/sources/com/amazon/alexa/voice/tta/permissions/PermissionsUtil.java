package com.amazon.alexa.voice.tta.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.typing.PersistentStorage;
import com.amazon.alexa.voice.tta.typing.TextTtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.TtaInChatResultCard;
/* loaded from: classes11.dex */
public class PermissionsUtil {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 321;
    public static final String LOCATION_PERMISSION_URI = "twa-prompt://location";

    private boolean isPermissionGranted(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public TtaResponseMessage getLocationPermissionMessage(Activity activity, PersistentStorage persistentStorage) {
        String string;
        if (activity != null && !activity.isDestroyed()) {
            if (getPermissionStatus(activity, "android.permission.ACCESS_FINE_LOCATION", persistentStorage) == PermissionStatus.DONT_ASK_AGAIN) {
                string = activity.getString(R.string.tta_location_permission_message_when_disabled);
            } else {
                string = activity.getString(R.string.tta_location_permission_message);
            }
            return new TextTtaResponseMessage(string);
        }
        return new TextTtaResponseMessage("");
    }

    public PermissionStatus getPermissionStatus(Activity activity, String str, PersistentStorage persistentStorage) {
        if (isPermissionGranted(activity, str)) {
            return PermissionStatus.GRANTED;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
            return PermissionStatus.DENIED;
        }
        if (!persistentStorage.isPermissionRequested(str)) {
            return PermissionStatus.NOT_REQUESTED;
        }
        return PermissionStatus.DONT_ASK_AGAIN;
    }

    public Permission getRequiredPermission(TtaInChatResultCard ttaInChatResultCard) {
        ItemRoute itemRoute = ttaInChatResultCard.getItemRoute();
        if (itemRoute != null && LOCATION_PERMISSION_URI.equals(itemRoute.getRouteLink())) {
            return Permission.builder().message(ttaInChatResultCard.getMessage()).type(PermissionType.LOCATION).build();
        }
        return Permission.builder().message("").type(PermissionType.NONE).build();
    }

    public void openAppInfo(Activity activity) {
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setFlags(268435456);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }

    public void requestLocationPermissions(Activity activity, PersistentStorage persistentStorage) {
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        if (getPermissionStatus(activity, "android.permission.ACCESS_FINE_LOCATION", persistentStorage) == PermissionStatus.DONT_ASK_AGAIN) {
            openAppInfo(activity);
            return;
        }
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, LOCATION_PERMISSION_REQUEST_CODE);
        persistentStorage.setPermissionRequested("android.permission.ACCESS_FINE_LOCATION", true);
    }
}
