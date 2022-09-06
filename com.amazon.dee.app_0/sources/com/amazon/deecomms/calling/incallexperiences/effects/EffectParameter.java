package com.amazon.deecomms.calling.incallexperiences.effects;
/* loaded from: classes12.dex */
public class EffectParameter {
    private String type;
    private Object value;

    public EffectParameter(String str, Object obj) {
        this.type = str;
        this.value = obj;
    }

    public String getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }
}
