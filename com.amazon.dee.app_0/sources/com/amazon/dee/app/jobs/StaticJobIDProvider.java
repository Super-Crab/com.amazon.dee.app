package com.amazon.dee.app.jobs;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.jobs.JobIDProvider;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class StaticJobIDProvider implements JobIDProvider {
    private static final String TAG = Log.tag(StaticJobIDProvider.class);
    private static final Map<String, Integer> jobIdMap = new HashMap();

    static {
        jobIdMap.put("com.amazon.dee.app.services.conversation.FireOSDirectiveHandlerService", 53259213);
        jobIdMap.put("com.amazon.dee.app.services.messaging.AmazonMessageReceiver", 53259214);
        jobIdMap.put("com.amazon.alexa.system.UserInactivityAuthority.UserInactivityReportingJob", 234613);
        jobIdMap.put("com.amazon.whisperjoin.deviceprovisioningservice.service.whitelist.FFSWhiteListJobService", 8557036);
    }

    public StaticJobIDProvider(@NonNull Context context) {
    }

    public static int getJobIdentification(@NonNull Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (jobIdMap.containsKey(canonicalName)) {
            int intValue = jobIdMap.get(canonicalName).intValue();
            String str = "Returning job id " + intValue + " for class " + canonicalName;
            return intValue;
        }
        GeneratedOutlineSupport1.outline158("Did not find a job id for class ", canonicalName);
        return -1;
    }

    @Override // com.amazon.alexa.protocols.jobs.JobIDProvider
    public int getJobId(@NonNull Class cls) {
        return getJobIdentification(cls);
    }
}
