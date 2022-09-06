package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
/* loaded from: classes12.dex */
public class VoxCallInfo {
    private String callID = "";
    private String calleeCommsID;
    private String calleeNumberType;
    private String displayName;
    private EnhancedProcessingState enhancedProcessingState;
    private Boolean isDropIn;
    private Boolean isVideo;
    private String name;
    private String provider;
    private String sipUri;
    private String srtpKey;

    public String getCallID() {
        return this.callID;
    }

    public String getCalleeCommsID() {
        return this.calleeCommsID;
    }

    public String getCalleeNumberType() {
        return this.calleeNumberType;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Boolean getDropIn() {
        return this.isDropIn;
    }

    public EnhancedProcessingState getEnhancedProcessingState() {
        return this.enhancedProcessingState;
    }

    public String getName() {
        return this.name;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getSipUri() {
        return this.sipUri;
    }

    public String getSrtpKey() {
        return this.srtpKey;
    }

    public Boolean getVideo() {
        return this.isVideo;
    }

    public void setCallID(String str) {
        this.callID = str;
    }

    public void setCalleeCommsID(String str) {
        this.calleeCommsID = str;
    }

    public void setCalleeNumberType(String str) {
        this.calleeNumberType = str;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setDropIn(Boolean bool) {
        this.isDropIn = bool;
    }

    public void setEnhancedProcessingState(@NonNull EnhancedProcessingState enhancedProcessingState) {
        this.enhancedProcessingState = enhancedProcessingState;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setSipUri(String str) {
        this.sipUri = str;
    }

    public void setSrtpKey(String str) {
        this.srtpKey = str;
    }

    public void setVideo(Boolean bool) {
        this.isVideo = bool;
    }
}
