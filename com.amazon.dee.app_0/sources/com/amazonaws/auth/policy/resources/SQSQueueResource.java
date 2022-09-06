package com.amazonaws.auth.policy.resources;

import com.amazonaws.auth.policy.Resource;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes13.dex */
public class SQSQueueResource extends Resource {
    public SQSQueueResource(String str, String str2) {
        super(GeneratedOutlineSupport1.outline92(GeneratedOutlineSupport1.outline107("/"), formatAccountId(str), "/", str2));
    }

    private static String formatAccountId(String str) {
        if (str != null) {
            return str.trim().replaceAll(ProcessIdUtil.DEFAULT_PROCESSID, "");
        }
        throw new IllegalArgumentException("Account ID cannot be null");
    }
}
