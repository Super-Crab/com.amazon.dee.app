package com.amazon.whisperjoin.common.sharedtypes.error;

import com.amazon.whisperjoin.protobuf.ErrorCodes;
/* loaded from: classes13.dex */
public class WJErrorFactory {

    /* loaded from: classes13.dex */
    public static class BLECommand {
        public static WJError completeProvisioning(int i) {
            return create(ErrorCodes.BLECommandErrorType.COMPLETE_PROVISIONING, i);
        }

        private static WJError create(ErrorCodes.BLECommandErrorType bLECommandErrorType, int i) {
            return new WJError(ErrorCodes.Domain.BLE_COMMAND_FAILURE.getNumber(), bLECommandErrorType.getNumber(), i, ErrorCodes.Resolution.RETRY_SETUP.getNumber());
        }

        public static WJError exchangeAuthEcdheKey(int i) {
            return create(ErrorCodes.BLECommandErrorType.EXCHANGE_AUTH_ECDHE_KEY, i);
        }

        public static WJError exchangeEcdheKey(int i) {
            return create(ErrorCodes.BLECommandErrorType.EXCHANGE_ECDHE_KEY, i);
        }

        public static WJError getConnectionStatus(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_CONNECTION_STATUS, i);
        }

        public static WJError getDeviceDetails(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_DEVICE_DETAILS, i);
        }

        public static WJError getNotificationEventData(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_NOTIFICATION_EVENT_DATA, i);
        }

        public static WJError getRegistrationStatus(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_REGISTRATION_STATUS, i);
        }

        public static WJError getSupportedNotifications(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_SUPPORTED_NOTIFICATIONS, i);
        }

        public static WJError getVisibleNetworks(int i) {
            return create(ErrorCodes.BLECommandErrorType.GET_VISIBLE_NETWORKS, i);
        }

        public static WJError jpakeRound1(int i) {
            return create(ErrorCodes.BLECommandErrorType.JPAKE_ROUND_1, i);
        }

        public static WJError jpakeRound2(int i) {
            return create(ErrorCodes.BLECommandErrorType.JPAKE_ROUND_2, i);
        }

        public static WJError jpakeRound3(int i) {
            return create(ErrorCodes.BLECommandErrorType.JPAKE_ROUND_3, i);
        }

        public static WJError jpakeRoundCertValidation(int i) {
            return create(ErrorCodes.BLECommandErrorType.JPAKE_CERT_VALIDATION, i);
        }

        public static WJError saveNetwork(int i) {
            return create(ErrorCodes.BLECommandErrorType.SAVE_NETWORK, i);
        }

        public static WJError setConfiguration(int i) {
            return create(ErrorCodes.BLECommandErrorType.SET_CONFIGURATION, i);
        }

        public static WJError setRegistrationToken(int i) {
            return create(ErrorCodes.BLECommandErrorType.SET_REGISTRATION_TOKEN, i);
        }

        public static WJError unknown(int i) {
            return create(ErrorCodes.BLECommandErrorType.UNKNOWN, i);
        }
    }

    /* loaded from: classes13.dex */
    public static class Connection {
        private static WJError create(ErrorCodes.ConnectionErrorType connectionErrorType) {
            return create(connectionErrorType, CommonErrorDetails.NONE);
        }

        public static WJError otherError() {
            return create(ErrorCodes.ConnectionErrorType.UNKNOWN_CONNECTION_ERROR);
        }

        public static WJError startProvisioningRequestFailed() {
            return create(ErrorCodes.ConnectionErrorType.START_PROVISIONING_REQUEST_FAILED);
        }

        public static WJError unableToEstablishConnection(int i) {
            return create(ErrorCodes.ConnectionErrorType.UNABLE_TO_ESTABLISH_CONNECTION, i);
        }

        public static WJError unableToEstablishSecureChannel() {
            return create(ErrorCodes.ConnectionErrorType.UNABLE_TO_ESTABLISH_SECURE_CHANNEL);
        }

        public static WJError unexpectedConnectionDrop(int i) {
            return create(ErrorCodes.ConnectionErrorType.UNEXPECTED_CONNECTION_DROP, i);
        }

        private static WJError create(ErrorCodes.ConnectionErrorType connectionErrorType, int i) {
            return new WJError(ErrorCodes.Domain.CONNECTION_FAILURE.getNumber(), connectionErrorType.getNumber(), i, ErrorCodes.Resolution.RETRY_SETUP.getNumber());
        }
    }

    /* loaded from: classes13.dex */
    public static class Other {
        private static WJError create(ErrorCodes.OtherErrorType otherErrorType, int i) {
            return new WJError(ErrorCodes.Domain.OTHER_FAILURE.getNumber(), otherErrorType.getNumber(), i, ErrorCodes.Resolution.RETRY_SETUP.getNumber());
        }

        public static WJError unknown(int i) {
            return create(ErrorCodes.OtherErrorType.UNKNOWN_ERROR, i);
        }
    }

    /* loaded from: classes13.dex */
    public static class Precondition {
        public static WJError bluetoothLowEnergyNotSupported() {
            return create(ErrorCodes.PreconditionErrorType.BLUETOOTH_LOW_ENERGY_NOT_SUPPORTED, ErrorCodes.Resolution.UNRESOLVABLE);
        }

        public static WJError bluetoothNotEnabled() {
            return create(ErrorCodes.PreconditionErrorType.BLUETOOTH_NOT_ENABLED, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }

        private static WJError create(ErrorCodes.PreconditionErrorType preconditionErrorType, ErrorCodes.Resolution resolution) {
            return new WJError(ErrorCodes.Domain.PRECONDITION_FAILURE.getNumber(), preconditionErrorType.getNumber(), resolution.getNumber());
        }

        public static WJError noUserLoggedIn() {
            return create(ErrorCodes.PreconditionErrorType.NO_USER_LOGGED_IN, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }

        public static WJError otherError() {
            return create(ErrorCodes.PreconditionErrorType.UNKNOWN_PRECONDITION_ERROR, ErrorCodes.Resolution.UNRESOLVABLE);
        }

        public static WJError requiredPermissionsNotGranted() {
            return create(ErrorCodes.PreconditionErrorType.REQUIRED_PERMISSIONS_NOT_GRANTED, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }
    }

    /* loaded from: classes13.dex */
    public static class Provisioning {
        private static WJError create(ErrorCodes.ProvisioningErrorType provisioningErrorType, ErrorCodes.Resolution resolution) {
            return new WJError(ErrorCodes.Domain.PROVISIONING_FAILURE.getNumber(), provisioningErrorType.getNumber(), resolution.getNumber());
        }

        public static WJError noAssociatedDeviceCredentials() {
            return create(ErrorCodes.ProvisioningErrorType.NO_ASSOCIATED_DEVICE_CREDENTIALS, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }

        public static WJError noConfiguredNetworks() {
            return create(ErrorCodes.ProvisioningErrorType.NO_CONFIGURED_NETWORKS, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError otherProvisioningError() {
            return create(ErrorCodes.ProvisioningErrorType.UNKNOWN_PROVISIONING_ERROR, ErrorCodes.Resolution.UNRESOLVABLE);
        }

        public static WJError provisioningDoneFailure(int i) {
            return create(ErrorCodes.ProvisioningErrorType.PROVISIONING_DONE_FAILURE, i, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError provisioningVerificationTimeout() {
            return create(ErrorCodes.ProvisioningErrorType.PROVISIONING_VERIFICATION_TIMEOUT, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorFailedOther(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_FAILED_OTHER, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorFailedToGetTokenFromMAP() {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_FAILED_TO_GET_TOKEN_FROM_MAP, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorServerError(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_SERVER_ERROR, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorServerNotReachable(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_SERVER_NOT_REACHABLE, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorTokenExpired(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_TOKEN_EXPIRED, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationErrorTokenInvalid(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_TOKEN_INVALID, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError registrationUnknownError(Integer num) {
            return create(ErrorCodes.ProvisioningErrorType.REG_ERROR_UNKNOWN_ERROR, num, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError wifiConnectionErrorApNotFound() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_AP_NOT_FOUND, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError wifiConnectionErrorBadPsk() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_BAD_PSK, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError wifiConnectionErrorCaptivePortal() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_CAPTIVE_PORTAL, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError wifiConnectionErrorInternalError() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_INTERNAL_ERROR, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError wifiConnectionErrorLimitedConnectivity() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_LIMITED_CONNECTIVITY, ErrorCodes.Resolution.CUSTOMER);
        }

        public static WJError wifiConnectionUnknownError() {
            return create(ErrorCodes.ProvisioningErrorType.WIFI_CONN_UNKNOWN_ERROR, ErrorCodes.Resolution.RETRY_SETUP);
        }

        public static WJError wifiSyncAuthTokenFailedValidation() {
            return create(ErrorCodes.ProvisioningErrorType.PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_INVALID, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }

        public static WJError wifiSyncAuthTokenNotFound() {
            return create(ErrorCodes.ProvisioningErrorType.PROVISIONING_FAILURE_NETWORK_SYNC_TOKEN_NOT_FOUND, ErrorCodes.Resolution.CLIENT_APPLICATION);
        }

        private static WJError create(ErrorCodes.ProvisioningErrorType provisioningErrorType, Integer num, ErrorCodes.Resolution resolution) {
            return new WJError(ErrorCodes.Domain.PROVISIONING_FAILURE.getNumber(), provisioningErrorType.getNumber(), num != null ? num.intValue() : 0, resolution.getNumber());
        }

        private static WJError create(ErrorCodes.ProvisioningErrorType provisioningErrorType, int i, ErrorCodes.Resolution resolution) {
            return new WJError(ErrorCodes.Domain.PROVISIONING_FAILURE.getNumber(), provisioningErrorType.getNumber(), i, resolution.getNumber());
        }
    }

    /* loaded from: classes13.dex */
    public static class WebService {
        public static WJError computeConfigurationData(int i) {
            return create(ErrorCodes.WebServiceErrorType.COMPUTE_CONFIGURATION_DATA, i);
        }

        private static WJError create(ErrorCodes.WebServiceErrorType webServiceErrorType, int i) {
            return new WJError(ErrorCodes.Domain.WEB_SERVICE_CALL_FAILUE.getNumber(), webServiceErrorType.getNumber(), i, ErrorCodes.Resolution.RETRY_SETUP.getNumber());
        }

        public static WJError discoveredProvisionableDevice(int i) {
            return create(ErrorCodes.WebServiceErrorType.DISCOVERED_PROVISIONABLE_DEVICE, i);
        }

        public static WJError discoveredProvisioneeDevice(int i) {
            return create(ErrorCodes.WebServiceErrorType.DISCOVERED_PROVISIONEE_DEVICE, i);
        }

        public static WJError finalizeEcdheAuthenticationSession(int i) {
            return create(ErrorCodes.WebServiceErrorType.FINALIZE_ECDHE_AUTHENTICATION_SESSION, i);
        }

        public static WJError getCustomerProvisioneesSetupStatus(int i) {
            return create(ErrorCodes.WebServiceErrorType.GET_CUSTOMER_PROVISIONEES_SETUP_STATUS, i);
        }

        public static WJError getDeviceRegistrationStatus(int i) {
            return create(ErrorCodes.WebServiceErrorType.GET_DEVICE_REGISTRATION_STATUS, i);
        }

        public static WJError getProvisionableStatus(int i) {
            return create(ErrorCodes.WebServiceErrorType.GET_PROVISIONABLE_STATUS, i);
        }

        public static WJError getWhiteListPolicy(int i) {
            return create(ErrorCodes.WebServiceErrorType.GET_WHITELIST_POLICY, i);
        }

        public static WJError reportEvent(int i) {
            return create(ErrorCodes.WebServiceErrorType.REPORT_EVENT, i);
        }

        public static WJError startEcdheAuthenticationSession(int i) {
            return create(ErrorCodes.WebServiceErrorType.START_ECDHE_AUTHENTICATION_SESSION, i);
        }

        public static WJError unknown(int i) {
            return create(ErrorCodes.WebServiceErrorType.UNKNOWN_WEB_SERVICE_ERROR, i);
        }

        public static WJError validateWifiSyncAuthToken(int i) {
            return create(ErrorCodes.WebServiceErrorType.VALIDATE_WIFI_SYNC_AUTH_TOKEN, i);
        }
    }

    /* loaded from: classes13.dex */
    public static class Workflow {
        private static WJError create(ErrorCodes.WorkflowErrorType workflowErrorType) {
            return new WJError(ErrorCodes.Domain.WORKFLOW_FAILURE.getNumber(), workflowErrorType.getNumber(), CommonErrorDetails.NONE, ErrorCodes.Resolution.CLIENT_APPLICATION.getNumber());
        }

        public static WJError deviceRecentlyProvisioned() {
            return create(ErrorCodes.WorkflowErrorType.DEVICE_RECENTLY_PROVISIONED);
        }

        public static WJError unknown() {
            return create(ErrorCodes.WorkflowErrorType.UNKNOWN_WORKFLOW_ERROR);
        }
    }
}
