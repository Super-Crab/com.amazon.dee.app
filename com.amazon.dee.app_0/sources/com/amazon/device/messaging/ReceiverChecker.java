package com.amazon.device.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes12.dex */
public class ReceiverChecker {
    private static final String TAG = "ReceiverChecker";
    private final String mAction;
    private final String mSenderPermission;
    private final boolean mShouldLogIfMissing;
    private final boolean mShouldThrowIfMissing;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String mAction;
        private String mSenderPermission;
        private Boolean mShouldLogIfMissing;
        private Boolean mShouldThrowIfMissing;

        private void validate() {
            if (this.mAction != null) {
                if (this.mSenderPermission != null) {
                    if (this.mShouldThrowIfMissing != null) {
                        if (this.mShouldLogIfMissing == null) {
                            throw new IllegalStateException("setShouldLogIfMissing() must be called");
                        }
                        return;
                    }
                    throw new IllegalStateException("setShouldThrowIfMissing() must be called");
                }
                throw new IllegalStateException("setSenderPermission() must be called with a non-null value");
            }
            throw new IllegalStateException("setAction() must be called with a non-null value");
        }

        public ReceiverChecker build() {
            validate();
            return new ReceiverChecker(this);
        }

        public Builder setAction(String str) {
            this.mAction = str;
            return this;
        }

        public Builder setSenderPermission(String str) {
            this.mSenderPermission = str;
            return this;
        }

        public Builder setShouldLogIfMissing(boolean z) {
            this.mShouldLogIfMissing = Boolean.valueOf(z);
            return this;
        }

        public Builder setShouldThrowIfMissing(boolean z) {
            this.mShouldThrowIfMissing = Boolean.valueOf(z);
            return this;
        }
    }

    protected ReceiverChecker(Builder builder) {
        this.mAction = builder.mAction;
        this.mSenderPermission = builder.mSenderPermission;
        this.mShouldThrowIfMissing = builder.mShouldThrowIfMissing.booleanValue();
        this.mShouldLogIfMissing = builder.mShouldLogIfMissing.booleanValue();
    }

    public void check(Context context, String str) throws InvalidManifestException {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(this.mAction);
        intent.setPackage(str);
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 32);
        if (queryBroadcastReceivers.isEmpty()) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No receiver found for action: ");
            outline107.append(this.mAction);
            outline107.append(", package: ");
            outline107.append(str);
            Log.i(str2, outline107.toString());
            String str3 = "No receivers for action " + this.mAction;
            if (this.mShouldLogIfMissing) {
                Log.w(TAG, str3);
            }
            if (this.mShouldThrowIfMissing) {
                throw new InvalidManifestException(str3);
            }
            return;
        }
        Log.i(TAG, String.format("# of receiver found for %s, package: %s: %d", this.mAction, str, Integer.valueOf(queryBroadcastReceivers.size())));
        for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
            String str4 = resolveInfo.activityInfo.name;
            Log.i(TAG, String.format("Found Receiver: %s with action: %s", str4, this.mAction));
            if (!this.mSenderPermission.equals(resolveInfo.activityInfo.permission)) {
                String str5 = this.mSenderPermission;
                String format = String.format("Receiver %s does not define permission %s.%nPlease specify <receiver android:name=\"%s\" android:permission=\"%s\"> in your application's manifest file.", str4, str5, str4, str5);
                Log.w(TAG, format);
                throw new InvalidManifestException(format);
            }
        }
    }
}
