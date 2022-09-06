package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class CharacterValue implements Value {
    private char mValue;

    public CharacterValue(char c) {
        this.mValue = c;
    }

    public char getValue() {
        return this.mValue;
    }

    public void setValue(char c) {
        this.mValue = c;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Character.valueOf(getValue());
    }

    public CharacterValue() {
        this((char) 0);
    }
}
