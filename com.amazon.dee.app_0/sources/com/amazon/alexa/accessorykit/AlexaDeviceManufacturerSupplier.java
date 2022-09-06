package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.DeviceManufacturerSupplier;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes6.dex */
public final class AlexaDeviceManufacturerSupplier implements DeviceManufacturerSupplier {
    public static final String AH_CASE = "A21YKVRGQV9TET";
    public static final String AH_CLUSTER = "ADXK6Q246T9EA";
    public static final String AH_LEFT = "A168KS6Z8QG6RV";
    public static final String AH_RIGHT = "A3KF60B9RDMWLY";
    public static final String ARMSTRONG_CASE = "A3HVREY4JWAZ6K";
    public static final String ARMSTRONG_CLUSTER = "A15QWUTQ6FSMYX";
    public static final String ARMSTRONG_LEFT = "A2QDHDQIWC2LTG";
    public static final String ARMSTRONG_RIGHT = "A31PMVIWCRNTX2";
    public static final String ECHO_AUTO = "A303PJF6ISQ7IC";
    public static final String G = "A13W6HQIHKEN3Z";
    public static final String HK = "A3HND3J60V1OXX";
    public static final String LENOVO_SMART_DOCK_NAME_PREFIX = "Lenovo Smart Dock";
    public static final String M = "A1388YOZ88W373";
    public static final String P_CASE = "AE9FIEPOC6D9B";
    public static final String P_CLUSTER = "A16MZVIFVHX6P6";
    public static final String P_LEFT = "AS8OPU4NNXJI8";
    public static final String P_RIGHT = "ALWUIN0P635PT";
    public static final String WEAR_APP = "AJQACSCJ0A68Q";
    public static final String Z = "A2OUFCM11BT9RB";
    public static final String ZION = "A3IYPH06PH1HRA";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isFirstPartyDevice(String str) {
        char c;
        Preconditions.notNull(str, "deviceType");
        switch (str.hashCode()) {
            case -1915399919:
                if (str.equals("A2QDHDQIWC2LTG")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1586932183:
                if (str.equals("A3HVREY4JWAZ6K")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1137784950:
                if (str.equals("A168KS6Z8QG6RV")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -1059701810:
                if (str.equals(WEAR_APP)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -964666442:
                if (str.equals("A16MZVIFVHX6P6")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -800938255:
                if (str.equals("A13W6HQIHKEN3Z")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -286864690:
                if (str.equals("AE9FIEPOC6D9B")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -218356722:
                if (str.equals("ALWUIN0P635PT")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 203551326:
                if (str.equals("A3IYPH06PH1HRA")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 217455752:
                if (str.equals("A21YKVRGQV9TET")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 249118782:
                if (str.equals(Z)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 503787559:
                if (str.equals("AS8OPU4NNXJI8")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 551901593:
                if (str.equals(M)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 655951664:
                if (str.equals("A15QWUTQ6FSMYX")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 806903076:
                if (str.equals("ADXK6Q246T9EA")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 929575927:
                if (str.equals("A31PMVIWCRNTX2")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1013997001:
                if (str.equals("A303PJF6ISQ7IC")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1215443527:
                if (str.equals("A3KF60B9RDMWLY")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1486296343:
                if (str.equals(HK)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return true;
            default:
                return false;
        }
    }

    @Override // com.amazon.alexa.accessory.DeviceManufacturerSupplier
    public boolean isFirstParty(String str) {
        return isFirstPartyDevice(str);
    }

    @Override // com.amazon.alexa.accessory.DeviceManufacturerSupplier
    public boolean isFwUpdateSupported(String str) {
        Preconditions.notNull(str, "deviceType");
        return !WEAR_APP.equals(str);
    }
}
