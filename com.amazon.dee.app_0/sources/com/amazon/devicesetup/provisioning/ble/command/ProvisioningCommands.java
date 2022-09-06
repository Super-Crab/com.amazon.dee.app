package com.amazon.devicesetup.provisioning.ble.command;

import com.amazon.devicesetup.constants.BleConstants;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ProvisioningCommands {
    public static final int CONNECT_TO_CONFIGURED_NETWORK_COMMAND_FAILED_NETWORK_NOT_CONFIGURED = -10;
    public static final int CONNECT_TO_CONFIGURED_NETWORK_COMMAND_FAILED_NETWORK_UNAVAILABLE = -11;
    public static final int DELETE_CONFIGURED_NETWORK_COMMAND_FAILED_NETWORK_NOT_CONFIGURED = -10;
    public static final int GET_PROVISIONING_FAILURE_CAUSE_NO_FAILURE = -10;
    public static final int INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_FAILED_UNABLE_TO_START_SCAN = -10;
    public static final int PROVISIONER_PUBLIC_KEY_INVALID = -10;
    public static final int PROVISIONING_COMMAND_FAILED_INTERNAL_ERROR = 1;
    public static final int PROVISIONING_COMMAND_FAILED_PACKET_VERIFICATION_ERROR = 4;
    public static final int PROVISIONING_COMMAND_FAILED_SERIALIZATION_ERROR = 3;
    public static final int PROVISIONING_COMMAND_FAILED_UNAUTHORIZED_PROVISIONER = 5;
    public static final int PROVISIONING_COMMAND_FAILED_UNKNOWN_COMMAND = 2;
    public static final int PROVISIONING_COMMAND_SUCCESS = 0;
    public static final int REGISTER_WITH_CODE_BASED_LINKING_FAILED_INTERNAL_ERROR = -10;
    public static final int REGISTER_WITH_CODE_BASED_LINKING_FAILED_SERVER_ERROR = -14;
    public static final int REGISTER_WITH_CODE_BASED_LINKING_FAILED_SERVER_NOT_REACHABLE = -13;
    public static final int REGISTER_WITH_CODE_BASED_LINKING_FAILED_TOKEN_EXPIRED = -12;
    public static final int REGISTER_WITH_CODE_BASED_LINKING_FAILED_TOKEN_INVALID = -11;
    public static final int RETRIEVE_EVENT_OBJECT_FAILED_EXPIRED_EVENT_KEY = -11;
    public static final int RETRIEVE_EVENT_OBJECT_FAILED_UNKNOWN_EVENT_KEY = -10;
    public static final int SAVE_CONFIGURED_NETWORK_COMMAND_FAILED_INVALID_NETWORK = -10;
    public static final String SUCCESS = "SUCCESS";
    static final Map<Integer, String> PROVISIONING_COMMAND_DEFAULT_STATUS_MAP = ImmutableMap.builder().mo7828put(0, "SUCCESS").mo7828put(1, "Failed due to internal error.").mo7828put(2, "Failed due to unknown command.").mo7828put(3, "Failed due to serialization error.").mo7828put(4, "Failed due to packet verification error.").mo7828put(5, "Failed due to unauthorized provisioner.").mo7826build();
    public static final UUID RETRIEVE_SUPPORTED_COMMANDS_UUID = UUID.fromString("22947d92-c4bb-47dc-9c00-23679575b5a5");
    static final Map<Integer, String> RETRIEVE_SUPPORTED_COMMANDS_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID RETRIEVE_SUPPORTED_EVENTS_UUID = UUID.fromString("22947d93-c4bb-47dc-9c00-23679575b5a5");
    static final Map<Integer, String> RETRIEVE_SUPPORTED_EVENTS_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID RETRIEVE_EVENT_OBJECT_UUID = UUID.fromString("22947d94-c4bb-47dc-9c00-23679575b5a5");
    static final Map<Integer, String> RETRIEVE_EVENT_OBJECT_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to unknown event key.").mo7828put(-11, "Failed due to expired event key.").mo7826build();
    public static final UUID STOP_PROVISIONING_COMMAND_UUID = UUID.fromString("22947d95-c4bb-47dc-9c00-23679575b5a5");
    static final Map<Integer, String> STOP_PROVISIONING_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID PROVISIONING_COMPLETE_UUID = UUID.fromString("6cd1d7e1-c37b-46d7-b268-635186f9aca1");
    static final Map<Integer, String> PROVISIONING_COMPLETE_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_UUID = UUID.fromString("c95ec710-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to being unable to start scan.").mo7826build();
    public static final UUID GET_VISIBLE_NETWORKS_COMMAND_UUID = UUID.fromString("c95ec711-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> GET_VISIBLE_NETWORKS_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID SAVE_CONFIGURED_NETWORK_COMMAND_UUID = UUID.fromString("c95ec713-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> SAVE_CONFIGURED_NETWORK_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to invalid network.").mo7826build();
    public static final UUID UNAUTHENTICATED_ECDHE_COMMAND_UUID = UUID.fromString("cc6ad206-b8a2-4710-bf23-e9d7ae3e2d0b");
    static final Map<Integer, String> UNAUTHENTICATED_ECDHE_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Provisioner public key invalid.").mo7826build();
    public static final UUID AUTHENTICATED_ECDHE_COMMAND_UUID = UUID.fromString("2caff8db-7767-448c-b12b-eac29589e12b");
    static final Map<Integer, String> AUTHENTICATED_ECDHE_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID CONNECT_TO_CONFIGURED_NETWORK_COMMAND_UUID = UUID.fromString("c95ec714-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> CONNECT_TO_CONFIGURED_NETWORK_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to network not being presently configured.").mo7828put(-11, "Failed due to network not being available for connection.").mo7826build();
    public static final UUID DELETE_CONFIGURED_NETWORK_COMMAND_UUID = UUID.fromString("c95ec715-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> DELETE_CONFIGURED_NETWORK_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to network not being presently configured.").mo7826build();
    public static final UUID DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_UUID = UUID.fromString("c95ec716-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID REFRESH_WIFI_CONNECTION_DETAILS_UUID = UUID.fromString("c95ec717-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> REFRESH_WIFI_CONNECTION_DETAILS_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID REGISTER_WITH_CODE_BASED_LINKING = UUID.fromString("c95ec718-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> REGISTER_WITH_CODE_BASED_LINKING_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "Failed due to internal error.").mo7828put(-11, "Failed due to invalid token.").mo7828put(-12, "Failed due to expired token.").mo7828put(-13, "Failed due to server being unreachable.").mo7828put(-14, "Failed due to server error.").mo7826build();
    public static final UUID REFRESH_CBL_REGISTRATION_DETAILS_UUID = UUID.fromString("c95ec719-f36f-4cd9-9879-1fa22f7eb110");
    static final Map<Integer, String> REFRESH_CBL_REGISTRATION_DETAILS_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID SET_CONFIGURATION_UUID = UUID.fromString("ac796df3-dd27-4459-a753-44efb4a9f195");
    static final Map<Integer, String> SET_CONFIGURATION_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID GET_CONFIGURATION_UUID = UUID.fromString("dd7ad30e-4a09-4252-98c5-bc159f6bd2f2");
    static final Map<Integer, String> GET_CONFIGURATION_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID DELETE_CONFIGURATION_UUID = UUID.fromString("f8747782-c7e8-4314-a086-aee78ad8b0e7");
    static final Map<Integer, String> DELETE_CONFIGURATION_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID DELETE_CONFIGURATION_SET_UUID = UUID.fromString("93fd9191-8382-4c10-aedf-6092c6154651");
    static final Map<Integer, String> DELETE_CONFIGURATION_SET_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID GET_DEVICE_DETAILS_UUID = UUID.fromString("bfae2d87-3937-42ec-8016-7ea13542c397");
    static final Map<Integer, String> GET_DEVICE_DETAILS_COMMAND_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7826build();
    public static final UUID GET_PROVISIONING_FAILURE_CAUSE_UUID = UUID.fromString("3a74a1c9-62b4-4cf3-8720-81144fa59f80");
    static final Map<Integer, String> GET_PROVISIONING_FAILURE_CAUSE_STATUS_MAP = ImmutableMap.builder().mo7831putAll(PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(-10, "No provisioning failure cause").mo7826build();
    public static final Set<UUID> PROVISIONING_COMMANDS = ImmutableSet.builder().mo7849add((ImmutableSet.Builder) RETRIEVE_SUPPORTED_COMMANDS_UUID).mo7849add((ImmutableSet.Builder) RETRIEVE_SUPPORTED_EVENTS_UUID).mo7849add((ImmutableSet.Builder) RETRIEVE_EVENT_OBJECT_UUID).mo7849add((ImmutableSet.Builder) STOP_PROVISIONING_COMMAND_UUID).mo7849add((ImmutableSet.Builder) PROVISIONING_COMPLETE_UUID).mo7849add((ImmutableSet.Builder) INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_UUID).mo7849add((ImmutableSet.Builder) GET_VISIBLE_NETWORKS_COMMAND_UUID).mo7849add((ImmutableSet.Builder) SAVE_CONFIGURED_NETWORK_COMMAND_UUID).mo7849add((ImmutableSet.Builder) CONNECT_TO_CONFIGURED_NETWORK_COMMAND_UUID).mo7849add((ImmutableSet.Builder) DELETE_CONFIGURED_NETWORK_COMMAND_UUID).mo7849add((ImmutableSet.Builder) DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_UUID).mo7849add((ImmutableSet.Builder) REFRESH_WIFI_CONNECTION_DETAILS_UUID).mo7849add((ImmutableSet.Builder) REGISTER_WITH_CODE_BASED_LINKING).mo7849add((ImmutableSet.Builder) REFRESH_CBL_REGISTRATION_DETAILS_UUID).mo7849add((ImmutableSet.Builder) UNAUTHENTICATED_ECDHE_COMMAND_UUID).mo7849add((ImmutableSet.Builder) AUTHENTICATED_ECDHE_COMMAND_UUID).mo7849add((ImmutableSet.Builder) SET_CONFIGURATION_UUID).mo7849add((ImmutableSet.Builder) GET_CONFIGURATION_UUID).mo7849add((ImmutableSet.Builder) DELETE_CONFIGURATION_UUID).mo7849add((ImmutableSet.Builder) DELETE_CONFIGURATION_SET_UUID).mo7849add((ImmutableSet.Builder) GET_DEVICE_DETAILS_UUID).mo7849add((ImmutableSet.Builder) GET_PROVISIONING_FAILURE_CAUSE_UUID).mo7852build();
    static final Map<UUID, Map<Integer, String>> PROVISIONING_COMMANDS_STATUS_MAPS = ImmutableMap.builder().mo7828put(BleConstants.PROVISIONING_COMMAND_CHARACTERISTIC_UUID, PROVISIONING_COMMAND_DEFAULT_STATUS_MAP).mo7828put(INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_UUID, INITIATE_VISIBLE_NETWORK_SCAN_COMMAND_STATUS_MAP).mo7828put(GET_VISIBLE_NETWORKS_COMMAND_UUID, GET_VISIBLE_NETWORKS_COMMAND_STATUS_MAP).mo7828put(SAVE_CONFIGURED_NETWORK_COMMAND_UUID, SAVE_CONFIGURED_NETWORK_COMMAND_STATUS_MAP).mo7828put(CONNECT_TO_CONFIGURED_NETWORK_COMMAND_UUID, CONNECT_TO_CONFIGURED_NETWORK_COMMAND_STATUS_MAP).mo7828put(DELETE_CONFIGURED_NETWORK_COMMAND_UUID, DELETE_CONFIGURED_NETWORK_COMMAND_STATUS_MAP).mo7828put(DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_UUID, DELETE_ALL_CONFIGURED_NETWORKS_COMMAND_STATUS_MAP).mo7828put(REFRESH_WIFI_CONNECTION_DETAILS_UUID, REFRESH_WIFI_CONNECTION_DETAILS_STATUS_MAP).mo7828put(REGISTER_WITH_CODE_BASED_LINKING, REGISTER_WITH_CODE_BASED_LINKING_STATUS_MAP).mo7828put(REFRESH_CBL_REGISTRATION_DETAILS_UUID, REFRESH_CBL_REGISTRATION_DETAILS_STATUS_MAP).mo7828put(STOP_PROVISIONING_COMMAND_UUID, STOP_PROVISIONING_COMMAND_STATUS_MAP).mo7828put(PROVISIONING_COMPLETE_UUID, PROVISIONING_COMPLETE_STATUS_MAP).mo7828put(UNAUTHENTICATED_ECDHE_COMMAND_UUID, UNAUTHENTICATED_ECDHE_COMMAND_STATUS_MAP).mo7828put(AUTHENTICATED_ECDHE_COMMAND_UUID, AUTHENTICATED_ECDHE_COMMAND_STATUS_MAP).mo7828put(SET_CONFIGURATION_UUID, SET_CONFIGURATION_COMMAND_STATUS_MAP).mo7828put(GET_CONFIGURATION_UUID, GET_CONFIGURATION_COMMAND_STATUS_MAP).mo7828put(DELETE_CONFIGURATION_UUID, DELETE_CONFIGURATION_COMMAND_STATUS_MAP).mo7828put(DELETE_CONFIGURATION_SET_UUID, DELETE_CONFIGURATION_SET_COMMAND_STATUS_MAP).mo7828put(GET_DEVICE_DETAILS_UUID, GET_DEVICE_DETAILS_COMMAND_STATUS_MAP).mo7828put(GET_PROVISIONING_FAILURE_CAUSE_UUID, GET_PROVISIONING_FAILURE_CAUSE_STATUS_MAP).mo7826build();
}