package com.amazon.deecomms.api;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.calling.model.CurrentVoxCallInfo;
import com.amazon.deecomms.calling.model.VoxCallInfo;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import java.util.Map;
/* loaded from: classes12.dex */
public interface CommsManager {
    public static final String CONV_TAB_PRESSED = "CONVERSATION_TAB_PRESSED";
    public static final int OOBE_REQUEST_CODE = 120;

    void acceptCall(@NonNull String str);

    void beginCall(@NonNull VoxCallInfo voxCallInfo);

    void clearCommsUIDelegate();

    @Deprecated
    void deregisterForPush();

    void deregisterForPush(boolean z);

    void enableVideoStreamInVideoCall(boolean z);

    void endActiveCallIfAny(@NonNull String str);

    @Nullable
    String getAor();

    @Nullable
    String getCommsId();

    @NonNull
    @Deprecated
    CommsIdentity getCommsIdentity();

    @Nullable
    String getDirectedId();

    @Nullable
    String getEmail();

    @Nullable
    String getFirstName();

    String getGcmSenderId();

    @Nullable
    String getHashedCommsId();

    @Nullable
    String getHomeGroupId();

    @Nullable
    String getLastName();

    @Nullable
    String getPhoneNumber();

    @Nullable
    String getProfileName();

    CommsProvisionStatus getUserProvisionStatus();

    void handleInterruptedAudioMessageRecording();

    void initializeAccessoryComponents();

    boolean isUserAutoProvisioned();

    boolean isUserProvisioned();

    boolean isValidEntryPoint(@NonNull CommsView commsView);

    void logoutComms();

    boolean onPush(Bundle bundle);

    void passDirectivePayload(String str, String str2, String str3);

    boolean performCommsBlocking(@NonNull Activity activity);

    void registerForPush();

    void registerForPush(String str, String str2);

    @Deprecated
    void registerForPush(String str, String str2, String str3);

    void requestFragment(CommsView commsView, Bundle bundle, boolean z);

    void requestFragment(String str);

    void resetDynamicFeatures(@NonNull Map<String, Boolean> map);

    @Deprecated
    void setCallInfo(CurrentVoxCallInfo currentVoxCallInfo);

    void setCommsLocale(@NonNull String str);

    void setCommsUIDelegate(CommsUIDelegateBase commsUIDelegateBase);

    void setDeviceNameForMAPRegistration(@NonNull String str);

    void setDynamicFeatures(@NonNull Map<String, Boolean> map);

    void setupCommsUser();

    boolean shouldRouteToReactNativeContactList();

    void signOut();

    void startComms();

    void stopComms();
}
