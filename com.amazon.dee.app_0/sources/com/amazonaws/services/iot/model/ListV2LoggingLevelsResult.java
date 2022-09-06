package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListV2LoggingLevelsResult implements Serializable {
    private List<LogTargetConfiguration> logTargetConfigurations;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListV2LoggingLevelsResult)) {
            return false;
        }
        ListV2LoggingLevelsResult listV2LoggingLevelsResult = (ListV2LoggingLevelsResult) obj;
        if ((listV2LoggingLevelsResult.getLogTargetConfigurations() == null) ^ (getLogTargetConfigurations() == null)) {
            return false;
        }
        if (listV2LoggingLevelsResult.getLogTargetConfigurations() != null && !listV2LoggingLevelsResult.getLogTargetConfigurations().equals(getLogTargetConfigurations())) {
            return false;
        }
        if ((listV2LoggingLevelsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listV2LoggingLevelsResult.getNextToken() == null || listV2LoggingLevelsResult.getNextToken().equals(getNextToken());
    }

    public List<LogTargetConfiguration> getLogTargetConfigurations() {
        return this.logTargetConfigurations;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogTargetConfigurations() == null ? 0 : getLogTargetConfigurations().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setLogTargetConfigurations(Collection<LogTargetConfiguration> collection) {
        if (collection == null) {
            this.logTargetConfigurations = null;
        } else {
            this.logTargetConfigurations = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogTargetConfigurations() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logTargetConfigurations: ");
            outline1072.append(getLogTargetConfigurations());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListV2LoggingLevelsResult withLogTargetConfigurations(LogTargetConfiguration... logTargetConfigurationArr) {
        if (getLogTargetConfigurations() == null) {
            this.logTargetConfigurations = new ArrayList(logTargetConfigurationArr.length);
        }
        for (LogTargetConfiguration logTargetConfiguration : logTargetConfigurationArr) {
            this.logTargetConfigurations.add(logTargetConfiguration);
        }
        return this;
    }

    public ListV2LoggingLevelsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListV2LoggingLevelsResult withLogTargetConfigurations(Collection<LogTargetConfiguration> collection) {
        setLogTargetConfigurations(collection);
        return this;
    }
}
