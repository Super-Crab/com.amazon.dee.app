package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager;
import java.io.File;
import java.io.IOException;
@Deprecated
/* loaded from: classes13.dex */
public class FileManagerValidator {
    public void validate(AnalyticsContext analyticsContext) {
        FileManager fileManager = analyticsContext.getSystem().getFileManager();
        try {
            File createFile = fileManager.createFile(new File(fileManager.getDirectory(""), "validatorFile"));
            if (createFile != null) {
                if (!fileManager.deleteFile(createFile)) {
                    throw new RuntimeException("Encountered an error accessing the file system, could not delete files");
                }
                return;
            }
            throw new RuntimeException("Encountered an error accessing the file system, could not create files");
        } catch (IOException e) {
            throw new RuntimeException("Encountered an error accessing the file system", e);
        }
    }
}
