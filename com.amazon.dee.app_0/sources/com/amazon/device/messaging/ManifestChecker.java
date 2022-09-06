package com.amazon.device.messaging;

import android.content.Context;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class ManifestChecker {
    private static final String TAG = "ManifestChecker";
    private final List<String> mExtraPermissions;
    private final String mPackageName;
    private List<ReceiverChecker> mReceiverCheckers;
    private final String mUseMessagingPermission;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String mPackageName;
        private String mUseMessagingPermission;
        private List<String> mExtraPermissions = new ArrayList();
        private List<ReceiverChecker> mReceiverCheckers = new ArrayList();

        private void validate() {
            if (this.mPackageName != null) {
                if (this.mUseMessagingPermission == null) {
                    throw new IllegalStateException("setUseMessagingPermission() must be called with a non-null value.");
                }
                return;
            }
            throw new IllegalStateException("setPackageName() must be called with a non-null value.");
        }

        public Builder addExtraPermission(String str) {
            this.mExtraPermissions.add(str);
            return this;
        }

        public Builder addReceiverChecker(ReceiverChecker receiverChecker) {
            this.mReceiverCheckers.add(receiverChecker);
            return this;
        }

        public ManifestChecker build() {
            validate();
            return new ManifestChecker(this);
        }

        public Builder setPackageName(String str) {
            this.mPackageName = str;
            return this;
        }

        public Builder setUseMessagingPermission(String str) {
            this.mUseMessagingPermission = str;
            return this;
        }
    }

    protected ManifestChecker(Builder builder) {
        this.mReceiverCheckers = new ArrayList();
        this.mPackageName = builder.mPackageName;
        this.mUseMessagingPermission = builder.mUseMessagingPermission;
        this.mExtraPermissions = Collections.unmodifiableList(builder.mExtraPermissions);
        this.mReceiverCheckers = Collections.unmodifiableList(builder.mReceiverCheckers);
    }

    private void verifyPermissionGranted(Context context, String str) throws InvalidManifestException {
        if (context.getPackageManager().checkPermission(str, this.mPackageName) == 0) {
            Log.i(TAG, String.format("%s was granted permission %s.", this.mPackageName, str));
            return;
        }
        throw new InvalidManifestException(String.format("%s does not use permission %s.%nPlease specify <uses-permission android:name=\"%s\" /> in your application's manifest file.", this.mPackageName, str, str));
    }

    public void check(Context context) throws InvalidManifestException {
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Performing Manifest checks for package: "), this.mPackageName, TAG);
        verifyPermissionGranted(context, this.mUseMessagingPermission);
        for (String str : this.mExtraPermissions) {
            verifyPermissionGranted(context, str);
        }
        for (ReceiverChecker receiverChecker : this.mReceiverCheckers) {
            receiverChecker.check(context, this.mPackageName);
        }
    }
}
