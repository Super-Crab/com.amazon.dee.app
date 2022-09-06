package chip.devicecontroller;

import java.util.List;
/* loaded from: classes.dex */
public class ChipClusters {

    /* loaded from: classes.dex */
    public static abstract class BaseChipCluster {
        protected long chipClusterPtr;

        public BaseChipCluster(long j, int i) {
            this.chipClusterPtr = initWithDevice(j, i);
        }

        public native void deleteCluster(long j);

        protected void finalize() throws Throwable {
            super.finalize();
            long j = this.chipClusterPtr;
            if (j != 0) {
                deleteCluster(j);
                this.chipClusterPtr = 0L;
            }
        }

        public abstract long initWithDevice(long j, int i);
    }

    /* loaded from: classes.dex */
    public interface BooleanAttributeCallback {
        void onError(Exception exc);

        void onSuccess(boolean z);
    }

    /* loaded from: classes.dex */
    public interface CharStringAttributeCallback {
        void onError(Exception exc);

        void onSuccess(String str);
    }

    /* loaded from: classes.dex */
    public interface DefaultClusterCallback {
        void onError(Exception exc);

        void onSuccess();
    }

    /* loaded from: classes.dex */
    public static class GeneralCommissioningCluster extends BaseChipCluster {

        /* loaded from: classes.dex */
        public interface ArmFailSafeResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public static class BasicCommissioningInfoListAttribute {
            public long failSafeExpiryLengthMs;

            public BasicCommissioningInfoListAttribute(long j) {
                this.failSafeExpiryLengthMs = j;
            }
        }

        /* loaded from: classes.dex */
        public interface BasicCommissioningInfoListAttributeCallback {
            void onError(Exception exc);

            void onSuccess(List<BasicCommissioningInfoListAttribute> list);
        }

        /* loaded from: classes.dex */
        public interface CommissioningCompleteResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface SetRegulatoryConfigResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        public GeneralCommissioningCluster(long j, int i) {
            super(j, i);
        }

        private native void armFailSafe(long j, ArmFailSafeResponseCallback armFailSafeResponseCallback, int i, long j2, long j3);

        private native void commissioningComplete(long j, CommissioningCompleteResponseCallback commissioningCompleteResponseCallback);

        private native void readBasicCommissioningInfoListAttribute(long j, BasicCommissioningInfoListAttributeCallback basicCommissioningInfoListAttributeCallback);

        private native void readBreadcrumbAttribute(long j, LongAttributeCallback longAttributeCallback);

        private native void readClusterRevisionAttribute(long j, IntegerAttributeCallback integerAttributeCallback);

        private native void setRegulatoryConfig(long j, SetRegulatoryConfigResponseCallback setRegulatoryConfigResponseCallback, int i, String str, long j2, long j3);

        private native void writeBreadcrumbAttribute(long j, DefaultClusterCallback defaultClusterCallback, long j2);

        public void armFailSafe(ArmFailSafeResponseCallback armFailSafeResponseCallback, int i, long j, long j2) {
            armFailSafe(this.chipClusterPtr, armFailSafeResponseCallback, i, j, j2);
        }

        public void commissioningComplete(CommissioningCompleteResponseCallback commissioningCompleteResponseCallback) {
            commissioningComplete(this.chipClusterPtr, commissioningCompleteResponseCallback);
        }

        @Override // chip.devicecontroller.ChipClusters.BaseChipCluster
        public native long initWithDevice(long j, int i);

        public void readBasicCommissioningInfoListAttribute(BasicCommissioningInfoListAttributeCallback basicCommissioningInfoListAttributeCallback) {
            readBasicCommissioningInfoListAttribute(this.chipClusterPtr, basicCommissioningInfoListAttributeCallback);
        }

        public void readBreadcrumbAttribute(LongAttributeCallback longAttributeCallback) {
            readBreadcrumbAttribute(this.chipClusterPtr, longAttributeCallback);
        }

        public void readClusterRevisionAttribute(IntegerAttributeCallback integerAttributeCallback) {
            readClusterRevisionAttribute(this.chipClusterPtr, integerAttributeCallback);
        }

        public void setRegulatoryConfig(SetRegulatoryConfigResponseCallback setRegulatoryConfigResponseCallback, int i, String str, long j, long j2) {
            setRegulatoryConfig(this.chipClusterPtr, setRegulatoryConfigResponseCallback, i, str, j, j2);
        }

        public void writeBreadcrumbAttribute(DefaultClusterCallback defaultClusterCallback, long j) {
            writeBreadcrumbAttribute(this.chipClusterPtr, defaultClusterCallback, j);
        }
    }

    /* loaded from: classes.dex */
    public interface IntegerAttributeCallback {
        void onError(Exception exc);

        void onSuccess(int i);
    }

    /* loaded from: classes.dex */
    public interface LongAttributeCallback {
        void onError(Exception exc);

        void onSuccess(long j);
    }

    /* loaded from: classes.dex */
    public static class NetworkCommissioningCluster extends BaseChipCluster {

        /* loaded from: classes.dex */
        public interface AddThreadNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface AddWiFiNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface DisableNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface EnableNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface RemoveNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface ScanNetworksResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface UpdateThreadNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        /* loaded from: classes.dex */
        public interface UpdateWiFiNetworkResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, String str);
        }

        public NetworkCommissioningCluster(long j, int i) {
            super(j, i);
        }

        private native void addThreadNetwork(long j, AddThreadNetworkResponseCallback addThreadNetworkResponseCallback, byte[] bArr, long j2, long j3);

        private native void addWiFiNetwork(long j, AddWiFiNetworkResponseCallback addWiFiNetworkResponseCallback, byte[] bArr, byte[] bArr2, long j2, long j3);

        private native void disableNetwork(long j, DisableNetworkResponseCallback disableNetworkResponseCallback, byte[] bArr, long j2, long j3);

        private native void enableNetwork(long j, EnableNetworkResponseCallback enableNetworkResponseCallback, byte[] bArr, long j2, long j3);

        private native void getLastNetworkCommissioningResult(long j, DefaultClusterCallback defaultClusterCallback, long j2);

        private native void readClusterRevisionAttribute(long j, IntegerAttributeCallback integerAttributeCallback);

        private native void readFeatureMapAttribute(long j, LongAttributeCallback longAttributeCallback);

        private native void removeNetwork(long j, RemoveNetworkResponseCallback removeNetworkResponseCallback, byte[] bArr, long j2, long j3);

        private native void scanNetworks(long j, ScanNetworksResponseCallback scanNetworksResponseCallback, byte[] bArr, long j2, long j3);

        private native void updateThreadNetwork(long j, UpdateThreadNetworkResponseCallback updateThreadNetworkResponseCallback, byte[] bArr, long j2, long j3);

        private native void updateWiFiNetwork(long j, UpdateWiFiNetworkResponseCallback updateWiFiNetworkResponseCallback, byte[] bArr, byte[] bArr2, long j2, long j3);

        public void addThreadNetwork(AddThreadNetworkResponseCallback addThreadNetworkResponseCallback, byte[] bArr, long j, long j2) {
            addThreadNetwork(this.chipClusterPtr, addThreadNetworkResponseCallback, bArr, j, j2);
        }

        public void addWiFiNetwork(AddWiFiNetworkResponseCallback addWiFiNetworkResponseCallback, byte[] bArr, byte[] bArr2, long j, long j2) {
            addWiFiNetwork(this.chipClusterPtr, addWiFiNetworkResponseCallback, bArr, bArr2, j, j2);
        }

        public void disableNetwork(DisableNetworkResponseCallback disableNetworkResponseCallback, byte[] bArr, long j, long j2) {
            disableNetwork(this.chipClusterPtr, disableNetworkResponseCallback, bArr, j, j2);
        }

        public void enableNetwork(EnableNetworkResponseCallback enableNetworkResponseCallback, byte[] bArr, long j, long j2) {
            enableNetwork(this.chipClusterPtr, enableNetworkResponseCallback, bArr, j, j2);
        }

        public void getLastNetworkCommissioningResult(DefaultClusterCallback defaultClusterCallback, long j) {
            getLastNetworkCommissioningResult(this.chipClusterPtr, defaultClusterCallback, j);
        }

        @Override // chip.devicecontroller.ChipClusters.BaseChipCluster
        public native long initWithDevice(long j, int i);

        public void readClusterRevisionAttribute(IntegerAttributeCallback integerAttributeCallback) {
            readClusterRevisionAttribute(this.chipClusterPtr, integerAttributeCallback);
        }

        public void readFeatureMapAttribute(LongAttributeCallback longAttributeCallback) {
            readFeatureMapAttribute(this.chipClusterPtr, longAttributeCallback);
        }

        public void removeNetwork(RemoveNetworkResponseCallback removeNetworkResponseCallback, byte[] bArr, long j, long j2) {
            removeNetwork(this.chipClusterPtr, removeNetworkResponseCallback, bArr, j, j2);
        }

        public void scanNetworks(ScanNetworksResponseCallback scanNetworksResponseCallback, byte[] bArr, long j, long j2) {
            scanNetworks(this.chipClusterPtr, scanNetworksResponseCallback, bArr, j, j2);
        }

        public void updateThreadNetwork(UpdateThreadNetworkResponseCallback updateThreadNetworkResponseCallback, byte[] bArr, long j, long j2) {
            updateThreadNetwork(this.chipClusterPtr, updateThreadNetworkResponseCallback, bArr, j, j2);
        }

        public void updateWiFiNetwork(UpdateWiFiNetworkResponseCallback updateWiFiNetworkResponseCallback, byte[] bArr, byte[] bArr2, long j, long j2) {
            updateWiFiNetwork(this.chipClusterPtr, updateWiFiNetworkResponseCallback, bArr, bArr2, j, j2);
        }
    }

    /* loaded from: classes.dex */
    public interface OctetStringAttributeCallback {
        void onError(Exception exc);

        void onSuccess(byte[] bArr);
    }

    /* loaded from: classes.dex */
    public static class OperationalCredentialsCluster extends BaseChipCluster {

        /* loaded from: classes.dex */
        public static class FabricsListAttribute {
            public long fabricId;
            public int fabricIndex;
            public byte[] label;
            public long nodeId;
            public byte[] rootPublicKey;
            public int vendorId;

            public FabricsListAttribute(int i, byte[] bArr, int i2, long j, long j2, byte[] bArr2) {
                this.fabricIndex = i;
                this.rootPublicKey = bArr;
                this.vendorId = i2;
                this.fabricId = j;
                this.nodeId = j2;
                this.label = bArr2;
            }
        }

        /* loaded from: classes.dex */
        public interface FabricsListAttributeCallback {
            void onError(Exception exc);

            void onSuccess(List<FabricsListAttribute> list);
        }

        /* loaded from: classes.dex */
        public interface NOCResponseCallback {
            void onError(Exception exc);

            void onSuccess(int i, int i2, byte[] bArr);
        }

        /* loaded from: classes.dex */
        public interface OpCSRResponseCallback {
            void onError(Exception exc);

            void onSuccess(byte[] bArr, byte[] bArr2);
        }

        public OperationalCredentialsCluster(long j, int i) {
            super(j, i);
        }

        private native void addNOC(long j, NOCResponseCallback nOCResponseCallback, byte[] bArr, byte[] bArr2, byte[] bArr3, long j2, int i);

        private native void addTrustedRootCertificate(long j, DefaultClusterCallback defaultClusterCallback, byte[] bArr);

        private native void opCSRRequest(long j, OpCSRResponseCallback opCSRResponseCallback, byte[] bArr);

        private native void readClusterRevisionAttribute(long j, IntegerAttributeCallback integerAttributeCallback);

        private native void readCommissionedFabricsAttribute(long j, IntegerAttributeCallback integerAttributeCallback);

        private native void readFabricsListAttribute(long j, FabricsListAttributeCallback fabricsListAttributeCallback);

        private native void readSupportedFabricsAttribute(long j, IntegerAttributeCallback integerAttributeCallback);

        private native void removeFabric(long j, NOCResponseCallback nOCResponseCallback, int i);

        private native void removeTrustedRootCertificate(long j, DefaultClusterCallback defaultClusterCallback, byte[] bArr);

        private native void updateFabricLabel(long j, NOCResponseCallback nOCResponseCallback, String str);

        private native void updateNOC(long j, NOCResponseCallback nOCResponseCallback, byte[] bArr, byte[] bArr2);

        public void addNOC(NOCResponseCallback nOCResponseCallback, byte[] bArr, byte[] bArr2, byte[] bArr3, long j, int i) {
            addNOC(this.chipClusterPtr, nOCResponseCallback, bArr, bArr2, bArr3, j, i);
        }

        public void addTrustedRootCertificate(DefaultClusterCallback defaultClusterCallback, byte[] bArr) {
            addTrustedRootCertificate(this.chipClusterPtr, defaultClusterCallback, bArr);
        }

        @Override // chip.devicecontroller.ChipClusters.BaseChipCluster
        public native long initWithDevice(long j, int i);

        public void opCSRRequest(OpCSRResponseCallback opCSRResponseCallback, byte[] bArr) {
            opCSRRequest(this.chipClusterPtr, opCSRResponseCallback, bArr);
        }

        public void readClusterRevisionAttribute(IntegerAttributeCallback integerAttributeCallback) {
            readClusterRevisionAttribute(this.chipClusterPtr, integerAttributeCallback);
        }

        public void readCommissionedFabricsAttribute(IntegerAttributeCallback integerAttributeCallback) {
            readCommissionedFabricsAttribute(this.chipClusterPtr, integerAttributeCallback);
        }

        public void readFabricsListAttribute(FabricsListAttributeCallback fabricsListAttributeCallback) {
            readFabricsListAttribute(this.chipClusterPtr, fabricsListAttributeCallback);
        }

        public void readSupportedFabricsAttribute(IntegerAttributeCallback integerAttributeCallback) {
            readSupportedFabricsAttribute(this.chipClusterPtr, integerAttributeCallback);
        }

        public void removeFabric(NOCResponseCallback nOCResponseCallback, int i) {
            removeFabric(this.chipClusterPtr, nOCResponseCallback, i);
        }

        public void removeTrustedRootCertificate(DefaultClusterCallback defaultClusterCallback, byte[] bArr) {
            removeTrustedRootCertificate(this.chipClusterPtr, defaultClusterCallback, bArr);
        }

        public void updateFabricLabel(NOCResponseCallback nOCResponseCallback, String str) {
            updateFabricLabel(this.chipClusterPtr, nOCResponseCallback, str);
        }

        public void updateNOC(NOCResponseCallback nOCResponseCallback, byte[] bArr, byte[] bArr2) {
            updateNOC(this.chipClusterPtr, nOCResponseCallback, bArr, bArr2);
        }
    }
}
