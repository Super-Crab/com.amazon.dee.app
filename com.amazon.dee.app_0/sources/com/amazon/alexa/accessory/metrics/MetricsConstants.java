package com.amazon.alexa.accessory.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.registration.DeviceRegistrationRequestIdentifier;
import com.amazon.alexa.accessory.registration.FirstPartyClusterDevice;
import com.amazon.alexa.accessory.registration.FirstPartyDevice;
import com.amazon.alexa.accessory.registration.ThirdPartyDevice;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public interface MetricsConstants {
    public static final String UNKNOWN_DEVICE_TYPE = "unknown";

    /* loaded from: classes.dex */
    public interface AccessoriesMetaDataKey {
        public static final String FIRMWARE_VERSION = "firmware_accessory";
        public static final String FIRMWARE_VERSION_0 = "firmware_accessory_0";
        public static final String FIRMWARE_VERSION_1 = "firmware_accessory_1";
    }

    /* loaded from: classes.dex */
    public interface Bootup {
        public static final String ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_NON_ZERO_TIME = "TimeFromPoweredOnToFullyConnectedNonZero";
        public static final String ACCESSORY_BOOTUP_TO_FULLY_CONNECTED_TIME = "TimeFromPoweredOnToFullyConnected";
    }

    /* loaded from: classes.dex */
    public interface BulkData {
        public static final String INCOMING_MANIFEST_SUCCESS = "IncomingManifest";
        public static final String INCOMING_MANIFEST_TIME = "IncomingManifestTimeMillis";
        public static final String INCOMING_TRANSFER_SUCCESS = "IncomingTransfer";
        public static final String INCOMING_TRANSFER_TIME = "IncomingTransferTimeMillis";
    }

    /* loaded from: classes.dex */
    public interface Capability {
        public static final String ACCESSORY_SPEECH_CAPABILITY = "AccessorySpeechCapability";
    }

    /* loaded from: classes.dex */
    public interface CompanionDevice {
        public static final String COMPANION_DEVICE_ASSOCIATION_REQUESTED = "AccessoryCompanionDeviceAssociationRequested";
        public static final String COMPANION_DEVICE_NOTIFICATION_CREATED = "AccessoryCompanionDeviceNotificationCreated";
    }

    /* loaded from: classes.dex */
    public interface Connection {
        public static final String ACTUAL_TRANSPORT_FLAGS = "actualTransportFlags";
        public static final String ACTUAL_TRANSPORT_VERSION = "actualTransportVersion";
        public static final String CONNECTION_CLOSED_EVENT = "AccessoryConnectionClosed";
        public static final String CONNECTION_ERROR_EVENT = "AccessoryConnectionError";
        public static final String CONNECTION_OPENED_EVENT = "AccessoryConnectionOpened";
        public static final String CONTROL_PACKET_LENGTH = "controlPacketLength";
        public static final String EXPECTED_TRANSPORT_FLAGS = "expectedTransportFlags";
        public static final String INTERRUPTED_EXCEPTION = "interruptedException";
        public static final String INTERRUPTED_IO_EXCEPTION = "interruptedIOException";
        public static final String INVALID_CONTROL_PACKET_LENGTH = "invalidControlPacketLength";
        public static final String INVALID_TRANSACTION_TYPE = "invalidTransactionType";
        public static final String INVALID_TRANSPORT_FLAGS = "invalidTransportFlags";
        public static final String INVALID_TRANSPORT_MAGIC_WORD = "invalidTransportMagicWord";
        public static final String INVALID_TRANSPORT_VERSION = "invalidTransportVersion";
        public static final String PACKET_DROPPED = "PacketDropped";
        public static final String TIME_OPEN = "timeOpen";
        public static final String TIME_TO_OPEN = "timeToOpen";
        public static final String TRANSACTION_COLLISION = "transactionCollision";
        public static final String TRANSACTION_STREAM = "transactionStream";
        public static final String TRANSACTION_TYPE = "transactionType";
        public static final String TRANSPORT_MAGIC_WORD = "transportMagicWord";
        public static final String TRANSPORT_VERSION = "transportVersion";
        public static final String UNEXPECTED_TRANSACTION = "unexpectedTransactionStream";
        public static final String UNKNOWN_EXCEPTION = "unknownException";
        public static final String UNSUPPORTED_TRANSPORT_VERSION = "unsupportedTransportVersion";
    }

    /* loaded from: classes.dex */
    public interface DAVS {
        public static final String ARTIFACT_DOWNLOAD_ERROR_CODE = "ArtifactDownloadErrorCode";
        public static final String ARTIFACT_DOWNLOAD_RESPONSE_EMPTY = "ArtifactDownloadResponseEmpty";
        public static final String ARTIFACT_DOWNLOAD_SUCCESS = "ArtifactDownloadSuccess";
        public static final String ARTIFACT_FILTER_ABSENT = "AccessoryDAVSFilterAbsent";
        public static final String DAVS_PREPARE_ARTIFACTS_ERROR = "AccessoryDavsPrepareArtifactsError";
        public static final String DOWNLOAD_ARTIFACT_FAILURE_PREFIX = "DownloadArtifactFailure";
        public static final String DOWNLOAD_I18N_FAILURE_PREFIX = "DownloadI18nFailure";
        public static final String ERROR_ARTIFACT_SIGNATURE_MISSING = "AccessoryDavsMissingArtifactSignature";
        public static final String ERROR_VERIFY_ARTIFACT_SIGNATURE = "AccessoryDavsVerifyArtifactSignatureError";
        public static final String FIRMWARE_UPDATE_HAS_COMPONENT_AND_ARTIFACTS = "FirmwareUpdateHasComponentAndArtifacts";
        public static final String FIRMWARE_UPDATE_HAS_COMPONENT_NO_ARTIFACTS = "FirmwareUpdateHasComponentNoArtifacts";
        public static final String GET_ARTIFACT_FILTER = "GetArtifactFilter";
        public static final String GET_ARTIFACT_FILTER_PARSING_ERROR = "GetArtifactFilterParsingError";
        public static final String GET_ARTIFACT_FROM_PACKAGE_EXCEPTION = "AccessoryDAVSArtifactParsingException";
        public static final String GET_ARTIFACT_UPDATE_PREFERENCE_RESPONSE = "GetArtifactUpdatePreferenceResponse";
        public static final String GET_DEVICE_ARTIFACTS_ACCESSORY_RESPONSE = "GetDeviceArtifactsAccessoryResponse";
        public static final String GET_DEVICE_ARTIFACTS_RESPONSE = "GetDeviceArtifactsResponse";
        public static final String GET_DEVICE_ARTIFACT_API_DEVICE_ACCOUNT_ERROR = "GetDeviceArtifactApiGetDeviceAccountError";
        public static final String GET_DEVICE_ARTIFACT_API_ERROR_CODE = "GetDeviceArtifactApiErrorCode";
        public static final String GET_DEVICE_ARTIFACT_API_FAILURE_PREFIX = "GetDeviceArtifactApiFailure";
        public static final String GET_DEVICE_ARTIFACT_API_MISSING_TOKEN = "GetDeviceArtifactApiMissingToken";
        public static final String GET_DEVICE_ARTIFACT_API_PARSING_ERROR = "GetDeviceArtifactApiParsingError";
        public static final String GET_DEVICE_ARTIFACT_API_SUCCESS = "GetDeviceArtifactApiSuccess";
        public static final String GET_I18N_CONFIG_DOWNLOAD_ERROR_CODE = "GetI18nConfigDownloadErrorCode";
        public static final String GET_I18N_CONFIG_DOWNLOAD_PARSING_ERROR = "GetI18nConfigDownloadParsingError";
        public static final String GET_I18N_CONFIG_DOWNLOAD_SUCCESS = "GetI18nConfigDownloadSuccess";
        public static final String I18N_CONFIG = "I18nConfig";
        public static final String I18N_CONFIG_ACCESSORY_SUCCESS = "AccessoryReceivedI18nConfig";
        public static final String NO_MORE_RETRIES = "AccessoryDavsOnUpdateSegment:NoMoreRetries";
        public static final String ON_VERIFY_ARTIFACT_RESPONSE = "AccessoryDavsVerifyArtifactResponse";
        public static final String START_FIRMWARE_UPDATE = "StartFirmwareUpdate";
    }

    /* loaded from: classes.dex */
    public interface DeviceAccount {
        public static final String DEVICE_ACCOUNT_HAS_DATA = "AccessoryDeviceAccountHasData";
        public static final String DEVICE_ACCOUNT_PARSE_SUCCESS = "AccessoryDeviceAccountParseData";
        public static final String DEVICE_ACCOUNT_PREPARED_REQUEST = "AccessoryDeviceAccountPreparedRequest";
        public static final String DEVICE_ACCOUNT_STATUS_CODE = "AccessoryDeviceAccountStatusCode";
        public static final String DEVICE_ACCOUNT_SUCCESS = "AccessoryDeviceAccount";
    }

    /* loaded from: classes.dex */
    public interface Dfu {
        public static final String APPLY_FIRMWARE_ERROR_CODE = "AccessoryDfuOnApplyFirmwareErrorCode";
        public static final String DFU_FAILURE_PREFIX = "AccessoryDfuOverallFailure";
        public static final String ERROR_ON_APPLY_FIRMWARE = "AccessoryDfuOnApplyFirmwareError";
        public static final String ERROR_ON_GET_CACHED_COMPONENT = "AccessoryDfuOnGetCachedComponentError";
        public static final String ERROR_ON_GET_CACHED_COMPONENT_DAVS = "AccessoryDfuOnGetCachedComponentErrorDAVS";
        public static final String ERROR_ON_UPDATE_SEGMENT = "AccessoryDfuOnUpdateSegmentError";
        public static final String ERROR_ON_UPDATE_SEGMENT_DAVS = "AccessoryDfuOnUpdateSegmentErrorDAVS";
        public static final String FILE_OPEN_PREFIX = "AccessoryDfuFileOpen";
        public static final String GET_FIRMWARE_INFORMATION_RESPONSE = "AccessoryDfuGetFirmwareInformationResponse";
        public static final String GET_FIRMWARE_UPDATE_PREFERENCE_RESPONSE = "AccessoryDfuGetFirmwareUpdatePreference";
        public static final String NO_MORE_RETRIES = "AccessoryDfuOnUpdateSegment:NoMoreRetries";
        public static final String ON_GET_CACHED_COMPONENT_DAVS_RESPONSE = "AccessoryDfuOnGetCachedComponentDAVS";
        public static final String ON_GET_CACHED_COMPONENT_RESPONSE = "AccessoryDfuOnGetCachedComponent";
        public static final String RESET_CACHED_COMPONENT_RESPONSE_PREFIX = "AccessoryDfuResetCachedComponent:";
        public static final String SIGNATURE_MISMATCH = "AccessoryDfuSignatureMismatch";
        public static final String SIGNATURE_VERIFICATION_IN_RUN_PREFIX = "AccessoryDfuSignatureVerificationInRun";
        public static final String SIGNATURE_VERIFICATION_PREFIX = "AccessoryDfuSignatureVerification";
        public static final String SUCCESS = "AccessoryDfuSuccess";
        public static final String TRANSMIT_DATA_PREFIX = "AccessoryDfuTransmitData";
        public static final String UPDATE_SEGMENT_RESPONSE_PREFIX = "AccessoryDfuOnUpdateSegment";
        public static final String VALIDATE_CACHED_COMPONENT_EXCEPTION_PREFIX = "AccessoryDfuValidateCachedComponent";
    }

    /* loaded from: classes.dex */
    public interface DisplayContent {
        public static final String SET_DISPLAY_CONTENT = "SetDisplayContent";
    }

    /* loaded from: classes.dex */
    public interface Dms {
        public static final String DEREGISTRATION_HAS_DATA = "AccessoryDmsDeregistrationHasData";
        public static final String DEREGISTRATION_PREPARED_REQUEST = "AccessoryDmsDeregistrationPreparedRequest";
        public static final String DEREGISTRATION_STATUS_CODE = "AccessoryDmsDeregistrationStatusCode";
        public static final String DEREGISTRATION_SUCCESS = "AccessoryDmsDeregistration";
        public static final String DIRECTED_ID_IS_EQUAL = "AccessoryDmsDirectedIdsEqual";
        public static final String DIRECTED_ID_RETRIEVED_FOR_GET_OR_CREATE = "AccessoryDirectedIdRetrievedForGetOrCreateRegistration";
        public static final String FRO_REGISTRATION_SUCCESS = "AccessoryDmsRegistrationFRO";
        public static final String REGISTER_CLOUD_AND_PERSIST_ERROR = "AccessoryDmsRegistrationRegisterCloudAndPersist";
        public static final String REGISTRATION_FAILURE_PREFIX = "AccessoryDmsRegistrationFailed";
        public static final String REGISTRATION_HAS_DATA = "AccessoryDmsRegistrationHasData";
        public static final String REGISTRATION_PREPARED_REQUEST = "AccessoryDmsRegistrationPreparedRequest";
        public static final String REGISTRATION_STATUS_CODE = "AccessoryDmsRegistrationStatusCode";
        public static final String REGISTRATION_SUCCESS = "AccessoryDmsRegistration";

        static Single<String> getDeviceType(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, DeviceSupplierV2 deviceSupplierV2) {
            FirstPartyDevice firstPartyDevice = deviceRegistrationRequestIdentifier.getFirstPartyDevice();
            ThirdPartyDevice thirdPartyDevice = deviceRegistrationRequestIdentifier.getThirdPartyDevice();
            FirstPartyClusterDevice firstPartyClusterDevice = deviceRegistrationRequestIdentifier.getFirstPartyClusterDevice();
            if (firstPartyDevice != null) {
                return Single.just(firstPartyDevice.getDeviceType());
            }
            if (thirdPartyDevice != null) {
                return Single.just(thirdPartyDevice.getDeviceType());
            }
            if (firstPartyClusterDevice != null) {
                return getDeviceTypeOfHighestDeviceIdFromConstituentDevices(firstPartyClusterDevice.getConstituentDevices(), deviceSupplierV2);
            }
            return Single.error(new IllegalArgumentException("DeviceRegistrationRequestIdentifier has no recognized Device component"));
        }

        @VisibleForTesting
        static Single<String> getDeviceTypeOfHighestDeviceIdFromConstituentDevices(Collection<FirstPartyClusterDevice.ConstituentDevice> collection, DeviceSupplierV2 deviceSupplierV2) {
            final HashSet hashSet = new HashSet();
            for (FirstPartyClusterDevice.ConstituentDevice constituentDevice : collection) {
                hashSet.add(constituentDevice.getDeviceSerialNumber());
            }
            return deviceSupplierV2.queryDeviceGroups().firstOrError().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessory.metrics.-$$Lambda$MetricsConstants$Dms$eSEWKyoWxb9qzUyr6Fy8wOAU8oU
                @Override // io.reactivex.rxjava3.functions.Predicate
                public final boolean test(Object obj) {
                    return MetricsConstants.Dms.lambda$getDeviceTypeOfHighestDeviceIdFromConstituentDevices$0(hashSet, (DeviceGroup) obj);
                }
            }).firstOrError().map($$Lambda$V2XR79r_72Az_IcM_2ChHt3jmVY.INSTANCE).onErrorReturn($$Lambda$MetricsConstants$Dms$k7WxHFpsIh641ZPLRtEwiOjHRg.INSTANCE);
        }

        static /* synthetic */ boolean lambda$getDeviceTypeOfHighestDeviceIdFromConstituentDevices$0(Set set, DeviceGroup deviceGroup) throws Throwable {
            HashSet hashSet = new HashSet();
            for (Device device : deviceGroup.getDevices()) {
                hashSet.add(device.getSerialNumber());
            }
            return set.equals(hashSet);
        }

        static /* synthetic */ String lambda$getDeviceTypeOfHighestDeviceIdFromConstituentDevices$1(Throwable th) throws Throwable {
            return "unknown";
        }
    }

    /* loaded from: classes.dex */
    public interface Environment {
        public static final String ACCESSORY_LIBRARY_VERSION = "accessoryLibraryVersion";
    }

    /* loaded from: classes.dex */
    public interface FileJsonStore {
        public static final String CACHE_LOADED = "AccessoryFileStoreLoaded";
        public static final String DID_PERSIST = "AccessoryFileStoreDidPersist";
        public static final String DIRECTORY_CREATED = "AccessoryFileStoreDirectoryCreated";
        public static final String DIRECTORY_IS_VALID = "AccessoryFileStoreDirectoryValid";
        public static final String FILE_EXISTS = "AccessoryFileStoreFileExists";
    }

    /* loaded from: classes.dex */
    public interface Firmware {
        public static final String CAUSE_APPLY_FIRMWARE_ERROR = "applyFirmwareError";
        public static final String CAUSE_DISPOSED = "disposed";
        public static final String CAUSE_OF_FAILURE = "causeOfFailure";
        public static final String CAUSE_RAN_OUT_OF_DAVS_TRANSMISSION_RETRIES = "ranOutOfTransmissionRetriesForDavs";
        public static final String CAUSE_RAN_OUT_OF_TRANSMISSION_RETRIES = "ranOutOfTransmissionRetries";
        public static final String COMPONENT_CACHE_INVALIDATED_COUNT = "componentCacheInvalidatedCount";
        public static final String DEVICE_ID = "deviceId";
        public static final String FIRMWARE_TASK_ERROR_HAS_KOTA_HAS_DAVS = "FirmwareTaskErrorHasKotaHasDavs";
        public static final String FIRMWARE_TASK_ERROR_HAS_KOTA_NO_DAVS = "FirmwareTaskErrorHasKotaNoDavs";
        public static final String FIRMWARE_TASK_ERROR_NO_KOTA_HAS_DAVS = "FirmwareTaskErrorNoKotaHasDavs";
        public static final String FIRMWARE_TASK_ERROR_NO_KOTA_NO_DAVS = "FirmwareTaskErrorNoKotaNoDavs";
        public static final String FIRMWARE_TASK_START_HAS_KOTA_HAS_DAVS = "FirmwareTaskStartHasKotaHasDavs";
        public static final String FIRMWARE_TASK_START_HAS_KOTA_NO_DAVS = "FirmwareTaskStartHasKotaNoDavs";
        public static final String FIRMWARE_TASK_START_NO_KOTA_HAS_DAVS = "FirmwareTaskStartNoKotaHasDavs";
        public static final String FIRMWARE_TASK_START_NO_KOTA_NO_DAVS = "FirmwareTaskStartNoKotaNoDavs";
        public static final String FIRMWARE_TASK_SUCCESS_HAS_KOTA_HAS_DAVS = "FirmwareTaskSuccessHasKotaHasDavs";
        public static final String FIRMWARE_TASK_SUCCESS_HAS_KOTA_NO_DAVS = "FirmwareTaskSuccessHasKotaNoDavs";
        public static final String FIRMWARE_TASK_SUCCESS_NO_KOTA_HAS_DAVS = "FirmwareTaskSuccessNoKotaHasDavs";
        public static final String FIRMWARE_TASK_SUCCESS_NO_KOTA_NO_DAVS = "FirmwareTaskSuccessNoKotaNoDavs";
        public static final String FIRMWARE_UPDATE_APPLIED_EVENT = "firmwareUpdateApplied";
        public static final String FIRMWARE_UPDATE_FAILED_EVENT = "firmwareUpdateFailed";
        public static final String GET_CACHED_COMPONENT_ERROR_COUNT = "getCachedComponentErrorCount";
        public static final String SUSPENSION_COUNT = "suspensionCount";
        public static final String TARGET_NAME = "targetName";
        public static final String TARGET_REFERENCE = "targetReference";
        public static final String TARGET_VERSION = "targetVersion";
        public static final String TOTAL_COMPONENTS_COUNT = "totalComponentsCount";
        public static final String TOTAL_ELAPSED_TIME_MILLIS = "totalElapsedTimeMillis";
        public static final String TOTAL_SUSPENDED_TIME_MILLIS = "totalSuspendedTimeMillis";
        public static final String TRANSMITTED_COMPONENTS_COUNT = "transmittedComponentsCount";
        public static final String TRANSMIT_COMPONENT_ERROR_COUNT = "transmitComponentErrorCount";
    }

    /* loaded from: classes.dex */
    public interface KeyExchange {
        public static final String KEY_EXCHANGE_COMPLETE_HANDSHAKE_KEY_FETCH_LATENCY = "CompleteHandshake.KeyFetchingLatency";
        public static final String KEY_EXCHANGE_COMPLETE_HANDSHAKE_PROCESSING_LATENCY = "CompleteHandshake.ProcessingLatency";
        public static final String KEY_EXCHANGE_INITIALIZATION_LATENCY = "KeyExchangeInitializationLatency";
        public static final String KEY_EXCHANGE_INITIALIZED_NO_KEYS_RATE = "KeyExchangeInitialized.NoKeys";
        public static final String KEY_EXCHANGE_INITIALIZED_NO_KEYS_REQUIRED_RATE = "KeyExchangeInitialized.NoKeysRequired";
        public static final String KEY_EXCHANGE_INITIALIZED_UNEXPECTED_NEXT_STATE_RATE = "KeyExchangeInitialized.UnexpectedState";
        public static final String KEY_EXCHANGE_INITIALIZED_WITH_KEYS_RATE = "KeyExchangeInitialized.HasKeys";
        public static final String KEY_EXCHANGE_INIT_HANDSHAKE_ACKNOWLEDGEMENT_SUCCESS_RATE = "InitiateHandshakeAcknowledged";
        public static final String KEY_EXCHANGE_INIT_HANDSHAKE_KEY_GENERATION_LATENCY = "InitiateHandshake.KeyGenerationLatency";
        public static final String KEY_EXCHANGE_INIT_HANDSHAKE_PROCESSING_LATENCY = "InitiateHandshake.ProcessingLatency";
        public static final String KEY_EXCHANGE_NEGOTIATION_LATENCY = "KeyExchangeNegotiationLatency";
        public static final String KEY_EXCHANGE_NEGOTIATION_SUCCESS_RATE = "KeyExchangeNegotiated";
        public static final String KEY_EXCHANGE_ROTATION_LATENCY = "KeyExchangeRotationLatency";
        public static final String KEY_EXCHANGE_ROTATION_SUCCESS_RATE = "KeyExchangeRotated";
        public static final String KEY_ROTATION_ACKNOWLEDGEMENT_SUCCESS_RATE = "ResetKeysAcknowledged";
        public static final String METRICS_PREFIX = "KeyExchange";
        public static final String METRIC_NAME_SEPARATOR = ".";
        public static final String STATE_TRANSITION_IRRECOVERABLE_ERROR_COUNT = "IrrecoverableError";
        public static final String STATE_TRANSITION_TIMEOUT = "Timeout";

        /* loaded from: classes.dex */
        public enum IrrecoverableErrorCause {
            TIMEOUT(KeyExchange.STATE_TRANSITION_TIMEOUT),
            RESET_ROOT_KEY("ResetRootKey"),
            UNEXPECTED_MESSAGE("UnexpectedMessage"),
            UNEXPECTED_EVENT("UnexpectedEvent"),
            CRYPTO_KEY_DATA_STORE_FAILURE("CryptoKeyDataStore.Error"),
            INVALID_MESSAGE("InvalidMessage"),
            HMAC_COMPUTATION_FAILURE("HmacComputationFailed"),
            HMAC_VALIDATION_FAILURE("HmacValidationFailed"),
            DECRYPTION_FAILURE("DecryptionFailed"),
            DECRYPTION_DATA_MISMATCH("DecryptionDataMismatch"),
            INVALID_ACCESSORY("InvalidAccessory"),
            MISSING_KEYS("MissingKeys"),
            ACCESSORY_FAILURE("AccessoryFailure"),
            INCOMPLETE_TEST_BLOB("IncompleteTestBlob"),
            UNKNOWN("Unknown");
            
            public final String metricName;

            IrrecoverableErrorCause(String str) {
                this.metricName = str;
            }
        }
    }

    /* loaded from: classes.dex */
    public interface Kota {
        public static final String DOWNLOAD_JOB_SCHEDULED_SUCCESS = "AccessoryDownloadScheduledKOTA";
        public static final String FIRMWARE_DOWNLOAD_SUCCESS = "AccessoryDownloadKOTA";
        public static final String JOB_ACTIVATED_SUCCESS = "AccessoryJobActivatedKOTA";
        public static final String PREPARE_DFU_CHECK_UPDATE = "AccessoryPreparedCheckForUpdateRequest";
        public static final String UPDATE_CHECK_ERROR = "AccessoryUpdateCheckKOTAError";
        public static final String UPDATE_CHECK_SUCCESS = "AccessoryUpdateCheckKOTA";
        public static final String USER_RETRIEVED_FOR_DFU_CHECK_UPDATE = "AccessoryUserRetrievedForCheckForUpdate";
    }

    /* loaded from: classes.dex */
    public interface Session {
        public static final String BONDING_CANCELLED = "bondingCancelled";
        public static final String CREATE_AND_CONNECT_SOCKET_ADAPTER_UNAVAILABLE_ERROR = "createSocketFailedAdapterUnavailable";
        public static final String CREATE_AND_CONNECT_SOCKET_BLUETOOTH_UNSUPPORTED_ERROR = "createSocketFailedBluetoothUnsupported";
        public static final String DEVICE_RESET_CONNECTION_FORCE_DISCONNECT_HAS_TIMEOUT = "forceDisconnectHasTimeout";
        public static final String DEVICE_RESET_CONNECTION_FORCE_DISCONNECT_NO_TIMEOUT = "forceDisconnectNoTimeout";
        public static final String DEVICE_RESET_CONNECTION_NOT_FORCE_DISCONNECT_HAS_TIMEOUT = "notForceDisconnectHasTimeout";
        public static final String DEVICE_RESET_CONNECTION_NOT_FORCE_DISCONNECT_NO_TIMEOUT = "notForceDisconnectNoTimeout";
        public static final String DEVICE_RESET_CONNECTION_RECEIVED = "deviceResetConnectionReceived";
        public static final String DEVICE_SERIAL_NUMBER_PREFIX = "deviceSerialNumber_";
        public static final String DEVICE_TYPE_PREFIX = "deviceType_";
        public static final String EXHAUSTED_RFCOMM_CONNECTION_ATTEMPT = "exhaustedRfcommConnectionAttempt";
        public static final String FAILED_RFCOMM_CONNECTION_SECURITY_EXCEPTION = "connectionFailedSecurityException";
        public static final String FIRMWARE_LOCALE = "firmwareLocale";
        public static final String FIRMWARE_NAME = "firmwareName";
        public static final String FIRMWARE_VERSION_NAME = "firmwareVersionName";
        public static final String GATT_AMA_SERVICE_NOT_SUPPORTED = "gattAmaServiceNotSupported_";
        public static final String GATT_BLUETOOTH_BOND_FAILED = "bluetoothBondFailed";
        public static final String GATT_CHANGE_CONNECTION_STATE_FAILED = "gattChangeConnectionStateFailed";
        public static final String GATT_CHARACTERISTIC_NOTIFICATION_UNSUPPORTED = "gattCharacteristicNotificationUnsupported";
        public static final String GATT_CHARACTERISTIC_RX_NOT_AVAILABLE = "gattCharacteristicRxNotAvailable";
        public static final String GATT_CHARACTERISTIC_TX_NOT_AVAILABLE = "gattCharacteristicTxNotAvailable";
        public static final String GATT_CONNECTION_CHANGED_TO_DISCONNECTED = "gattConnectionChangedToDisconnected";
        public static final String GATT_CONNECTION_FAILED = "gattConnectionFailed";
        public static final String GATT_DESCRIPTOR_RX_NOT_AVAILABLE = "gattDescriptorRxNotAvailable";
        public static final String GATT_DISCOVER_SERVICES_FAILED = "gattDiscoverServicesFailed";
        public static final String GATT_DISCOVER_SERVICES_INITIATION_FAILED = "gattDiscoverServicesInitiationFailed";
        public static final String GATT_MTU_ATTEMPTS_EXCEEDED = "gattMtuAttemptsExceeded";
        public static final String GATT_READ_CHARACTERISTIC_FAILED = "gattReadCharacteristicFailed";
        public static final String GATT_READ_CHARACTERISTIC_INITIATION_FAILED = "gattReadCharacteristicInitiationFailed";
        public static final String GATT_WRITE_CHARACTERISTIC_FAILED = "gattWriteCharacteristicFailed";
        public static final String GATT_WRITE_DESCRIPTOR_FAILED = "gattWriteDescriptorFailed";
        public static final String GATT_WRITE_DESCRIPTOR_INITIATION_FAILED = "gattWriteDescriptorInitiationFailed";
        public static final String RECONNECT_SESSION_AFTER_DELAY = "reconnectSessionAfterDelay";
        public static final String RECONNECT_SESSION_AFTER_FAILURE = "reconnectAfterSessionFailure";
        public static final String RECONNECT_SESSION_ON_KNOWN_ACCESSORY = "reconnectOnKnownAccessoryFound";
        public static final String RECONNECT_SESSION_STANDBY_EXPIRED = "reconnectSessionStandbyExpired";
        public static final String RETRY_FAILED_RFCOMM_CONNECTION = "retryFailedRfcommConnection";
        public static final String SESSION_CONNECTION = "AccessorySessionConnection";
        public static final String SESSION_RELEASED = "AccessorySessionReleased";
        public static final String SESSION_RELEASED_REASON_ACCESSORIES_UNBIND = "accessoriesUnbind";
        public static final String SESSION_RELEASED_REASON_CONNECTION_CLOSED = "connectionClosed";
        public static final String SESSION_RELEASED_REASON_CONNECTION_ERROR = "connectionError";
        public static final String SESSION_RELEASED_REASON_CONNECTIVITY_INTERACTOR_RELEASE_ALL = "connectivityInteractorReleaseAll";
        public static final String SESSION_RELEASED_REASON_INVALID_DEVICE_INFO = "invalidDeviceInformation";
        public static final String SESSION_RELEASED_REASON_RESET_CONNECTION_ACCESSORY_INITIATED = "accessoryInitiatedResetConnection";
        public static final String SESSION_RELEASED_REASON_UNLINK = "unlink";
        public static final String SESSION_RELEASED_REASON_USER_INITIATED = "userInitiated";
        public static final String SESSION_RETRY_DELAY = "sessionRetryDelay";
        public static final String TRANSPORT_TYPE = "transportType";
    }

    /* loaded from: classes.dex */
    public interface Speech {
        public static final String SPEECH_PROCESSING_DELAY_METRIC = "AccessorySpeechProcessingDelay";
        public static final String SPEECH_PROCESSING_FAILED_FOR_DEVICE_METRIC = "AccessorySpeechProcessingFailedForDevice";
        public static final String SPEECH_PROCESSING_FAILED_METRIC = "AccessorySpeechProcessingFailed";
        public static final String SPEECH_RECOGNITION_ENABLED_IN_BUFFER_TIME = "SpeechRecognitionEnabledInBufferTime";
        public static final String SPEECH_RECOGNITION_ENABLED_TIME = "SpeechRecognitionEnabledTime";
        public static final String SPEECH_RECOGNITION_ENABLED_TIME_AT_FEATURE_CHECK = "SpeechRecognitionEnabledTimeAtFeatureCheck";
    }
}
