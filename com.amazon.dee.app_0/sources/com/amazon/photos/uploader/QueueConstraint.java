package com.amazon.photos.uploader;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: QueueConstraint.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/QueueConstraint;", "", "()V", "BlockInLowBattery", "BlockInPowerSavingMode", "RequiresChargingConstraint", "RequiresUnmeteredConnection", "Lcom/amazon/photos/uploader/QueueConstraint$RequiresUnmeteredConnection;", "Lcom/amazon/photos/uploader/QueueConstraint$RequiresChargingConstraint;", "Lcom/amazon/photos/uploader/QueueConstraint$BlockInPowerSavingMode;", "Lcom/amazon/photos/uploader/QueueConstraint$BlockInLowBattery;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class QueueConstraint {

    /* compiled from: QueueConstraint.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/uploader/QueueConstraint$BlockInLowBattery;", "Lcom/amazon/photos/uploader/QueueConstraint;", "()V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class BlockInLowBattery extends QueueConstraint {
        public static final BlockInLowBattery INSTANCE = new BlockInLowBattery();

        private BlockInLowBattery() {
            super(null);
        }
    }

    /* compiled from: QueueConstraint.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/uploader/QueueConstraint$BlockInPowerSavingMode;", "Lcom/amazon/photos/uploader/QueueConstraint;", "()V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class BlockInPowerSavingMode extends QueueConstraint {
        public static final BlockInPowerSavingMode INSTANCE = new BlockInPowerSavingMode();

        private BlockInPowerSavingMode() {
            super(null);
        }
    }

    /* compiled from: QueueConstraint.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/uploader/QueueConstraint$RequiresChargingConstraint;", "Lcom/amazon/photos/uploader/QueueConstraint;", "()V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class RequiresChargingConstraint extends QueueConstraint {
        public static final RequiresChargingConstraint INSTANCE = new RequiresChargingConstraint();

        private RequiresChargingConstraint() {
            super(null);
        }
    }

    /* compiled from: QueueConstraint.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/photos/uploader/QueueConstraint$RequiresUnmeteredConnection;", "Lcom/amazon/photos/uploader/QueueConstraint;", "()V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class RequiresUnmeteredConnection extends QueueConstraint {
        public static final RequiresUnmeteredConnection INSTANCE = new RequiresUnmeteredConnection();

        private RequiresUnmeteredConnection() {
            super(null);
        }
    }

    private QueueConstraint() {
    }

    public /* synthetic */ QueueConstraint(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
