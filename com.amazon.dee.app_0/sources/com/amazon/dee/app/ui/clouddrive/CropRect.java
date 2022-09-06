package com.amazon.dee.app.ui.clouddrive;
/* loaded from: classes12.dex */
public enum CropRect {
    TOP,
    LEFT,
    BOTTOM,
    RIGHT;
    
    private float mPosition;

    public static float getHeight() {
        return BOTTOM.getPosition() - TOP.getPosition();
    }

    public static float getWidth() {
        return RIGHT.getPosition() - LEFT.getPosition();
    }

    public float getPosition() {
        return this.mPosition;
    }

    public void setPosition(float f) {
        this.mPosition = f;
    }
}
