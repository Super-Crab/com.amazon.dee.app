package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.common.Constants;
import com.google.common.base.Optional;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class BeginCallMapper {
    public Optional<InitiateOutboundCallRequest> map(@NonNull BeginCallPayload beginCallPayload) {
        String str = "";
        for (Map<String, Object> map : beginCallPayload.sipCommand.payload.headers) {
            if (map.containsKey(Constants.GROUPID_HEADER)) {
                str = Objects.requireNonNull(map.get(Constants.GROUPID_HEADER)).toString();
            }
        }
        if (StringUtils.isEmpty(str)) {
            return Optional.absent();
        }
        return Optional.of(new InitiateOutboundCallRequestBuilder(Arrays.asList("AUDIO", "VIDEO")).withGroupTarget(Constants.GROUP_CONTACT, str, false).build());
    }
}
