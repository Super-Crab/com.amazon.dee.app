package com.facebook.react.animated;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class SpringAnimation extends AnimationDriver {
    private static final double MAX_DELTA_TIME_SEC = 0.064d;
    private static final double SOLVER_TIMESTEP_SEC = 0.001d;
    private int mCurrentLoop;
    private final PhysicsState mCurrentState = new PhysicsState();
    private double mDisplacementFromRestThreshold;
    private double mEndValue;
    private double mInitialVelocity;
    private int mIterations;
    private long mLastTime;
    private double mOriginalValue;
    private boolean mOvershootClampingEnabled;
    private double mRestSpeedThreshold;
    private double mSpringDamping;
    private double mSpringMass;
    private boolean mSpringStarted;
    private double mSpringStiffness;
    private double mStartValue;
    private double mTimeAccumulator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PhysicsState {
        double position;
        double velocity;

        private PhysicsState() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpringAnimation(ReadableMap readableMap) {
        this.mCurrentState.velocity = readableMap.getDouble("initialVelocity");
        resetConfig(readableMap);
    }

    private void advance(double d) {
        double d2;
        double d3;
        if (isAtRest()) {
            return;
        }
        double d4 = MAX_DELTA_TIME_SEC;
        if (d <= MAX_DELTA_TIME_SEC) {
            d4 = d;
        }
        this.mTimeAccumulator += d4;
        double d5 = this.mSpringDamping;
        double d6 = this.mSpringMass;
        double d7 = this.mSpringStiffness;
        double d8 = -this.mInitialVelocity;
        double sqrt = d5 / (Math.sqrt(d7 * d6) * 2.0d);
        double sqrt2 = Math.sqrt(d7 / d6);
        double sqrt3 = Math.sqrt(1.0d - (sqrt * sqrt)) * sqrt2;
        double d9 = this.mEndValue - this.mStartValue;
        double d10 = this.mTimeAccumulator;
        if (sqrt < 1.0d) {
            double exp = Math.exp((-sqrt) * sqrt2 * d10);
            double d11 = sqrt * sqrt2;
            double d12 = (d11 * d9) + d8;
            double d13 = d10 * sqrt3;
            double cos = this.mEndValue - (((Math.cos(d13) * d9) + (Math.sin(d13) * (d12 / sqrt3))) * exp);
            d3 = (((Math.cos(d13) * d9) + ((Math.sin(d13) * d12) / sqrt3)) * (d11 * exp)) - (((Math.cos(d13) * d12) - (Math.sin(d13) * (sqrt3 * d9))) * exp);
            d2 = cos;
        } else {
            double exp2 = Math.exp((-sqrt2) * d10);
            d2 = this.mEndValue - (((((sqrt2 * d9) + d8) * d10) + d9) * exp2);
            d3 = ((sqrt2 * sqrt2 * d10 * d9) + (((d10 * sqrt2) - 1.0d) * d8)) * exp2;
        }
        PhysicsState physicsState = this.mCurrentState;
        physicsState.position = d2;
        physicsState.velocity = d3;
        if (!isAtRest() && (!this.mOvershootClampingEnabled || !isOvershooting())) {
            return;
        }
        if (this.mSpringStiffness > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            double d14 = this.mEndValue;
            this.mStartValue = d14;
            this.mCurrentState.position = d14;
        } else {
            this.mEndValue = this.mCurrentState.position;
            this.mStartValue = this.mEndValue;
        }
        this.mCurrentState.velocity = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    private double getDisplacementDistanceForState(PhysicsState physicsState) {
        return Math.abs(this.mEndValue - physicsState.position);
    }

    private boolean isAtRest() {
        return Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold && (getDisplacementDistanceForState(this.mCurrentState) <= this.mDisplacementFromRestThreshold || this.mSpringStiffness == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    private boolean isOvershooting() {
        if (this.mSpringStiffness > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            double d = this.mStartValue;
            double d2 = this.mEndValue;
            if (d >= d2 || this.mCurrentState.position <= d2) {
                double d3 = this.mStartValue;
                double d4 = this.mEndValue;
                if (d3 <= d4 || this.mCurrentState.position >= d4) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        this.mSpringStiffness = readableMap.getDouble("stiffness");
        this.mSpringDamping = readableMap.getDouble("damping");
        this.mSpringMass = readableMap.getDouble("mass");
        this.mInitialVelocity = this.mCurrentState.velocity;
        this.mEndValue = readableMap.getDouble("toValue");
        this.mRestSpeedThreshold = readableMap.getDouble("restSpeedThreshold");
        this.mDisplacementFromRestThreshold = readableMap.getDouble("restDisplacementThreshold");
        this.mOvershootClampingEnabled = readableMap.getBoolean("overshootClamping");
        boolean z = true;
        this.mIterations = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        if (this.mIterations != 0) {
            z = false;
        }
        this.mHasFinished = z;
        this.mCurrentLoop = 0;
        this.mTimeAccumulator = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.mSpringStarted = false;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j) {
        long j2 = j / 1000000;
        if (!this.mSpringStarted) {
            if (this.mCurrentLoop == 0) {
                this.mOriginalValue = this.mAnimatedValue.mValue;
                this.mCurrentLoop = 1;
            }
            PhysicsState physicsState = this.mCurrentState;
            double d = this.mAnimatedValue.mValue;
            physicsState.position = d;
            this.mStartValue = d;
            this.mLastTime = j2;
            this.mTimeAccumulator = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            this.mSpringStarted = true;
        }
        advance((j2 - this.mLastTime) / 1000.0d);
        this.mLastTime = j2;
        this.mAnimatedValue.mValue = this.mCurrentState.position;
        if (isAtRest()) {
            int i = this.mIterations;
            if (i != -1 && this.mCurrentLoop >= i) {
                this.mHasFinished = true;
                return;
            }
            this.mSpringStarted = false;
            this.mAnimatedValue.mValue = this.mOriginalValue;
            this.mCurrentLoop++;
        }
    }
}
