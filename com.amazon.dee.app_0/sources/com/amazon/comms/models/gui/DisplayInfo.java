package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class DisplayInfo implements Serializable {
    private PartyInfo calleePartyInfo;
    private PartyInfo callerPartyInfo;
    private CspInfo cspInfo;
    private DropInInfo dropInInfo;
    private MediaSettingsInfo mediaSettingsInfo;
    private SystemTrayInfo systemTrayInfo;

    /* loaded from: classes11.dex */
    public static class DisplayInfoBuilder {
        private PartyInfo calleePartyInfo;
        private PartyInfo callerPartyInfo;
        private CspInfo cspInfo;
        private DropInInfo dropInInfo;
        private MediaSettingsInfo mediaSettingsInfo;
        private SystemTrayInfo systemTrayInfo;

        DisplayInfoBuilder() {
        }

        public DisplayInfo build() {
            return new DisplayInfo(this.callerPartyInfo, this.calleePartyInfo, this.dropInInfo, this.systemTrayInfo, this.mediaSettingsInfo, this.cspInfo);
        }

        public DisplayInfoBuilder calleePartyInfo(PartyInfo partyInfo) {
            this.calleePartyInfo = partyInfo;
            return this;
        }

        public DisplayInfoBuilder callerPartyInfo(PartyInfo partyInfo) {
            this.callerPartyInfo = partyInfo;
            return this;
        }

        public DisplayInfoBuilder cspInfo(CspInfo cspInfo) {
            this.cspInfo = cspInfo;
            return this;
        }

        public DisplayInfoBuilder dropInInfo(DropInInfo dropInInfo) {
            this.dropInInfo = dropInInfo;
            return this;
        }

        public DisplayInfoBuilder mediaSettingsInfo(MediaSettingsInfo mediaSettingsInfo) {
            this.mediaSettingsInfo = mediaSettingsInfo;
            return this;
        }

        public DisplayInfoBuilder systemTrayInfo(SystemTrayInfo systemTrayInfo) {
            this.systemTrayInfo = systemTrayInfo;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DisplayInfo.DisplayInfoBuilder(callerPartyInfo=");
            outline107.append(this.callerPartyInfo);
            outline107.append(", calleePartyInfo=");
            outline107.append(this.calleePartyInfo);
            outline107.append(", dropInInfo=");
            outline107.append(this.dropInInfo);
            outline107.append(", systemTrayInfo=");
            outline107.append(this.systemTrayInfo);
            outline107.append(", mediaSettingsInfo=");
            outline107.append(this.mediaSettingsInfo);
            outline107.append(", cspInfo=");
            outline107.append(this.cspInfo);
            outline107.append(")");
            return outline107.toString();
        }
    }

    DisplayInfo(PartyInfo partyInfo, PartyInfo partyInfo2, DropInInfo dropInInfo, SystemTrayInfo systemTrayInfo, MediaSettingsInfo mediaSettingsInfo, CspInfo cspInfo) {
        this.callerPartyInfo = partyInfo;
        this.calleePartyInfo = partyInfo2;
        this.dropInInfo = dropInInfo;
        this.systemTrayInfo = systemTrayInfo;
        this.mediaSettingsInfo = mediaSettingsInfo;
        this.cspInfo = cspInfo;
    }

    public static DisplayInfoBuilder builder() {
        return new DisplayInfoBuilder();
    }

    @JsonIgnore
    public String getCalleeCredit() {
        PartyInfo partyInfo = this.calleePartyInfo;
        if (partyInfo != null) {
            return partyInfo.getCredit();
        }
        return null;
    }

    @JsonIgnore
    public boolean getCalleeDropInPermission() {
        PartyInfo partyInfo = this.calleePartyInfo;
        if (partyInfo != null) {
            return partyInfo.getDropInPermission();
        }
        return false;
    }

    @JsonIgnore
    public String getCalleeEndpointDescription() {
        if (this.callerPartyInfo != null) {
            return this.calleePartyInfo.getEndpointDescription();
        }
        return null;
    }

    @JsonIgnore
    public String getCalleeName() {
        PartyInfo partyInfo = this.calleePartyInfo;
        if (partyInfo != null) {
            return partyInfo.getName();
        }
        return null;
    }

    public PartyInfo getCalleePartyInfo() {
        return this.calleePartyInfo;
    }

    @JsonIgnore
    public String getCallerCredit() {
        PartyInfo partyInfo = this.callerPartyInfo;
        if (partyInfo != null) {
            return partyInfo.getCredit();
        }
        return null;
    }

    @JsonIgnore
    public boolean getCallerDropInPermission() {
        PartyInfo partyInfo = this.callerPartyInfo;
        if (partyInfo != null) {
            return partyInfo.getDropInPermission();
        }
        return false;
    }

    @JsonIgnore
    public String getCallerEndpointDescription() {
        PartyInfo partyInfo = this.callerPartyInfo;
        if (partyInfo != null) {
            return partyInfo.getEndpointDescription();
        }
        return null;
    }

    @JsonIgnore
    public String getCallerName() {
        PartyInfo partyInfo = this.callerPartyInfo;
        if (partyInfo != null) {
            return partyInfo.getName();
        }
        return null;
    }

    public PartyInfo getCallerPartyInfo() {
        return this.callerPartyInfo;
    }

    public CspInfo getCspInfo() {
        return this.cspInfo;
    }

    public DropInInfo getDropInInfo() {
        return this.dropInInfo;
    }

    @JsonIgnore
    public String getEnhancedProcessing() {
        MediaSettingsInfo mediaSettingsInfo = this.mediaSettingsInfo;
        return mediaSettingsInfo != null ? mediaSettingsInfo.getEnhancedProcessing() : MediaSettingsInfo.ENHANCED_PROCESSING_NOT_APPLICABLE;
    }

    public MediaSettingsInfo getMediaSettingsInfo() {
        return this.mediaSettingsInfo;
    }

    public SystemTrayInfo getSystemTrayInfo() {
        return this.systemTrayInfo;
    }

    @JsonIgnore
    public String getSystemTrayInfoDescription() {
        SystemTrayInfo systemTrayInfo = this.systemTrayInfo;
        if (systemTrayInfo != null) {
            return systemTrayInfo.getCallDescription();
        }
        return null;
    }
}
