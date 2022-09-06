package com.amazon.clouddrive.extended.utils;

import com.amazon.clouddrive.extended.model.JobType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class JobKeyUtils {
    public static String generateJobKey(String str, JobType jobType) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ":");
        outline113.append(jobType.name());
        return outline113.toString();
    }
}
