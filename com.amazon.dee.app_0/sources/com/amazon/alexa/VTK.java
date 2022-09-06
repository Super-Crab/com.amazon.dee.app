package com.amazon.alexa;
/* compiled from: AttachmentContentType.java */
/* loaded from: classes.dex */
public enum VTK {
    METADATA("metadata"),
    AUDIO("audio"),
    WAKEWORD_ENGINE_METADATA("wakewordEngineMetadata");
    
    public final String name;

    VTK(String str) {
        this.name = str;
    }

    public String zZm() {
        return this.name;
    }
}
