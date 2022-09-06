package amazon.speech.simclient.metrics.deviceeventemitter;

import amazon.speech.simclient.metrics.util.AndroidLogWrapper;
import amazon.speech.simclient.metrics.util.Preconditions;
import amazon.speech.util.LogWrapper;
import com.amazon.deviceevents.emitter.DeviceEventEmitter;
import com.amazon.deviceevents.exceptions.DeviceEventSerializationException;
import com.amazon.deviceevents.model.DeviceEvent;
import com.amazon.deviceevents.serialization.DeviceEventMapperFactory;
import com.amazon.deviceevents.serialization.IDeviceEventMapper;
import java.text.MessageFormat;
/* loaded from: classes.dex */
final class AndroidDeviceEventEmitter extends DeviceEventEmitter {
    private static final String EMIT_ERROR_FORMAT = "Could not log device event of type ''{0}'' because ''{1}''";
    private final IDeviceEventMapper mDeviceEventMapper;
    private final LogWrapper mLogWrapper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidDeviceEventEmitter() {
        this(new AndroidLogWrapper(), DeviceEventMapperFactory.build());
    }

    @Override // com.amazon.deviceevents.emitter.DeviceEventEmitter
    protected void emit(DeviceEvent deviceEvent) {
        Preconditions.checkNotNull(deviceEvent, "event");
        try {
            this.mLogWrapper.i(DeviceEventEmitter.TAG, this.mDeviceEventMapper.serialize(deviceEvent));
        } catch (DeviceEventSerializationException e) {
            this.mLogWrapper.e(DeviceEventEmitter.TAG, MessageFormat.format(EMIT_ERROR_FORMAT, deviceEvent.getEventType(), e.getMessage()));
        }
    }

    AndroidDeviceEventEmitter(LogWrapper logWrapper) {
        this(logWrapper, DeviceEventMapperFactory.build());
    }

    AndroidDeviceEventEmitter(LogWrapper logWrapper, IDeviceEventMapper iDeviceEventMapper) {
        Preconditions.checkNotNull(logWrapper, "logWrapper");
        Preconditions.checkNotNull(iDeviceEventMapper, "deviceEventMapper");
        this.mLogWrapper = logWrapper;
        this.mDeviceEventMapper = iDeviceEventMapper;
    }
}
