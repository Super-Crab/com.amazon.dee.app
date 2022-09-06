package com.amazon.device.crashmanager.ndk;
/* loaded from: classes12.dex */
public class SystemProps {
    private String bootLoader;
    private String buildFingerPrint;
    private String hardware;
    private String osBuildNumber;
    private String revision;
    private String version;

    public String getBootLoader() {
        return this.bootLoader;
    }

    public String getBuildFingerPrint() {
        return this.buildFingerPrint;
    }

    public String getHardware() {
        return this.hardware;
    }

    public String getOsBuildNumber() {
        return this.osBuildNumber;
    }

    public String getRevision() {
        return this.revision;
    }

    public String getVersion() {
        return this.version;
    }

    public void setBootLoader(String str) {
        this.bootLoader = str;
    }

    public void setBuildFingerPrint(String str) {
        this.buildFingerPrint = str;
    }

    public void setHardware(String str) {
        this.hardware = str;
    }

    public void setOsBuildNumber(String str) {
        this.osBuildNumber = str;
    }

    public void setRevision(String str) {
        this.revision = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
