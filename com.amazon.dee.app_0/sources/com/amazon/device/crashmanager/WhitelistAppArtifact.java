package com.amazon.device.crashmanager;

import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.device.crashmanager.source.WhitelistAppArtifactSource;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.InputStream;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class WhitelistAppArtifact extends Artifact {
    private static final DPLogger log = new DPLogger("WhitelistAppArtifact");

    public WhitelistAppArtifact(String str, InputStream inputStream, long j) {
        super(str, inputStream, j);
    }

    @Override // com.amazon.device.crashmanager.Artifact
    public boolean shouldUploadArtifact() {
        log.info("shouldUploadArtifact", "Verifying that the artifact can be uploaded", new Object[0]);
        Pattern whiteListedAppPatternsForTag = WhitelistAppArtifactSource.getWhiteListedAppPatternsForTag(getTag());
        String str = getMetadataMap().get(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY);
        log.info("shouldUploadArtifact", GeneratedOutlineSupport1.outline72("mAppName: ", str), new Object[0]);
        if (whiteListedAppPatternsForTag.matcher(str).matches()) {
            return true;
        }
        log.info("shouldUploadArtifact", "Artifact is not whitelisted to be uploaded", new Object[0]);
        return false;
    }
}
