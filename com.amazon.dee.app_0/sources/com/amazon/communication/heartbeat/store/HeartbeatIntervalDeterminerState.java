package com.amazon.communication.heartbeat.store;

import amazon.communication.serialize.ObjectMapper;
import amazon.communication.serialize.ObjectMapperFactory;
import com.amazon.communication.NetworkType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
/* loaded from: classes12.dex */
public class HeartbeatIntervalDeterminerState {
    private static final ObjectMapper JSON_MAPPER = ObjectMapperFactory.newObjectMapper(ObjectMapperFactory.ContentType.JSON);
    private static final String UTF8 = "UTF-8";
    private int mConsecutiveFailureCount;
    private long mCurrentLowerBoundMillis;
    private long mCurrentUpperBoundMillis;
    private long mHeartbeatIntervalMillis;
    private long mIntervalExpiryTimestamp;
    private boolean mIsLearningMode;
    private int mLearningAttemptCount;
    private long mLearntTimestamp;
    private NetworkType mNetworkType;

    private HeartbeatIntervalDeterminerState() {
    }

    public static HeartbeatIntervalDeterminerState deserialize(String str) throws IOException {
        return (HeartbeatIntervalDeterminerState) JSON_MAPPER.deserialize(new ByteArrayInputStream(str.getBytes("UTF-8")), HeartbeatIntervalDeterminerState.class);
    }

    public int getConsecutiveFailureCount() {
        return this.mConsecutiveFailureCount;
    }

    public long getCurrentLowerBoundMillis() {
        return this.mCurrentLowerBoundMillis;
    }

    public long getCurrentUpperBoundMillis() {
        return this.mCurrentUpperBoundMillis;
    }

    public long getHeartbeatIntervalMillis() {
        return this.mHeartbeatIntervalMillis;
    }

    public long getIntervalExpiryTimestamp() {
        return this.mIntervalExpiryTimestamp;
    }

    public int getLearningAttemptCount() {
        return this.mLearningAttemptCount;
    }

    public long getLearntTimestamp() {
        return this.mLearntTimestamp;
    }

    public NetworkType getNetworkType() {
        return this.mNetworkType;
    }

    public boolean isLearningMode() {
        return this.mIsLearningMode;
    }

    public String serialize() {
        try {
            return new String(JSON_MAPPER.serialize(this).array(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public void setConsecutiveFailureCount(int i) {
        this.mConsecutiveFailureCount = i;
    }

    public void setCurrentLowerBoundMillis(long j) {
        this.mCurrentLowerBoundMillis = j;
    }

    public void setCurrentUpperBoundMillis(long j) {
        this.mCurrentUpperBoundMillis = j;
    }

    public void setHeartbeatIntervalMillis(long j) {
        this.mHeartbeatIntervalMillis = j;
    }

    public void setIntervalExpiryTimestamp(long j) {
        this.mIntervalExpiryTimestamp = j;
    }

    public void setLearningAttemptCount(int i) {
        this.mLearningAttemptCount = i;
    }

    public void setLearningMode(boolean z) {
        this.mIsLearningMode = z;
    }

    public void setLearntTimestamp(long j) {
        this.mLearntTimestamp = j;
    }

    public void setNetworkType(NetworkType networkType) {
        this.mNetworkType = networkType;
    }

    public String toString() {
        return serialize();
    }

    public HeartbeatIntervalDeterminerState(NetworkType networkType, long j, long j2, long j3, boolean z, long j4, long j5, int i, int i2) {
        this.mNetworkType = networkType;
        this.mCurrentLowerBoundMillis = j;
        this.mCurrentUpperBoundMillis = j2;
        this.mHeartbeatIntervalMillis = j3;
        this.mIsLearningMode = z;
        this.mLearntTimestamp = j4;
        this.mIntervalExpiryTimestamp = j5;
        this.mLearningAttemptCount = i;
        this.mConsecutiveFailureCount = i2;
    }
}
