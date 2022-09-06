package com.amazon.comms.ringservice;

import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public class FrostVideoEffectCommandBuilder {
    public static final String COMMAND_NAME_FROST = "FROST";
    public static final String KEY_NAME_EFFECT_TYPE = "EFFECT";
    public static final String KEY_NAME_STREAMS = "STREAMS";
    public static final String KEY_NAME_STRENGTH = "STRENGTH";
    public static final String KEY_NAME_TRANSITION_TIME = "TIME";
    public static final String KEY_NAME_TRANSITION_TYPE = "TRANSITION";
    public static final String KEY_NAME_WIDTH = "WIDTH";
    private EffectType effectType;
    private Integer streamsCount;
    private Float strength;
    private Float transitionTime;
    private TransitionType transitionType;
    private Integer width;

    /* loaded from: classes12.dex */
    public enum EffectType {
        None,
        Box,
        Gaussian
    }

    /* loaded from: classes12.dex */
    public enum TransitionType {
        Normal,
        Linear,
        Exponential,
        Cosine
    }

    public VideoEffectCommand getCommand() {
        VideoEffectCommand videoEffectCommand = new VideoEffectCommand("FROST");
        Integer num = this.streamsCount;
        if (num != null) {
            videoEffectCommand.add(KEY_NAME_STREAMS, num.intValue());
        }
        EffectType effectType = this.effectType;
        if (effectType != null) {
            videoEffectCommand.add(KEY_NAME_EFFECT_TYPE, effectType.toString());
        }
        Float f = this.strength;
        if (f != null) {
            videoEffectCommand.add(KEY_NAME_STRENGTH, f.floatValue());
        }
        TransitionType transitionType = this.transitionType;
        if (transitionType != null) {
            videoEffectCommand.add(KEY_NAME_TRANSITION_TYPE, transitionType.toString());
        }
        Float f2 = this.transitionTime;
        if (f2 != null) {
            videoEffectCommand.add("TIME", f2.floatValue());
        }
        Integer num2 = this.width;
        if (num2 != null) {
            videoEffectCommand.add(KEY_NAME_WIDTH, num2.intValue());
        }
        return videoEffectCommand;
    }

    public FrostVideoEffectCommandBuilder setEffect(EffectType effectType) {
        this.effectType = effectType;
        return this;
    }

    public FrostVideoEffectCommandBuilder setFrostRadius(int i) {
        this.width = Integer.valueOf(i);
        return this;
    }

    public FrostVideoEffectCommandBuilder setStreamsCount(int i) {
        Preconditions.checkArgument((i >= 0 && i <= 3) || i == -1);
        this.streamsCount = Integer.valueOf(i);
        return this;
    }

    public FrostVideoEffectCommandBuilder setStrength(float f) {
        boolean z = true;
        Preconditions.checkArgument(f >= 0.0f);
        if (f > 1.0f) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.strength = Float.valueOf(f);
        return this;
    }

    public FrostVideoEffectCommandBuilder setTransitionTime(float f) {
        this.transitionTime = Float.valueOf(f);
        return this;
    }

    public FrostVideoEffectCommandBuilder setTransitionType(TransitionType transitionType) {
        this.transitionType = transitionType;
        return this;
    }

    public String toString() {
        return getCommand().toString();
    }
}
