package com.amazon.device.setup.thrift;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.thrift.orig.EncodingUtils;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TBaseHelper;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.TFieldIdEnum;
import org.apache.thrift.orig.meta_data.EnumMetaData;
import org.apache.thrift.orig.meta_data.FieldMetaData;
import org.apache.thrift.orig.meta_data.FieldValueMetaData;
import org.apache.thrift.orig.protocol.TCompactProtocol;
import org.apache.thrift.orig.protocol.TField;
import org.apache.thrift.orig.protocol.TProtocol;
import org.apache.thrift.orig.protocol.TProtocolUtil;
import org.apache.thrift.orig.protocol.TStruct;
import org.apache.thrift.orig.protocol.TTupleProtocol;
import org.apache.thrift.orig.scheme.IScheme;
import org.apache.thrift.orig.scheme.SchemeFactory;
import org.apache.thrift.orig.scheme.StandardScheme;
import org.apache.thrift.orig.scheme.TupleScheme;
import org.apache.thrift.orig.transport.TIOStreamTransport;
/* loaded from: classes12.dex */
public class Update implements TBase<Update, _Fields>, Serializable, Cloneable {
    private static final int __DOWNLOADPROGRESS_ISSET_ID = 2;
    private static final int __PACKAGEBUILDVERSIONCODE_ISSET_ID = 0;
    private static final int __REMOTEFILESIZE_ISSET_ID = 1;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private byte __isset_bitfield;
    public double downloadProgress;
    public long packageBuildVersionCode;
    public String packageName;
    public String packageVersionName;
    public long remoteFileSize;
    public UpdateState updateState;
    public String updateType;
    private static final TStruct STRUCT_DESC = new TStruct("Update");
    private static final TField UPDATE_TYPE_FIELD_DESC = new TField("updateType", (byte) 11, 1);
    private static final TField PACKAGE_BUILD_VERSION_CODE_FIELD_DESC = new TField("packageBuildVersionCode", (byte) 10, 2);
    private static final TField PACKAGE_NAME_FIELD_DESC = new TField(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, (byte) 11, 3);
    private static final TField PACKAGE_VERSION_NAME_FIELD_DESC = new TField("packageVersionName", (byte) 11, 4);
    private static final TField UPDATE_STATE_FIELD_DESC = new TField("updateState", (byte) 8, 5);
    private static final TField REMOTE_FILE_SIZE_FIELD_DESC = new TField("remoteFileSize", (byte) 10, 6);
    private static final TField DOWNLOAD_PROGRESS_FIELD_DESC = new TField("downloadProgress", (byte) 4, 7);
    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.device.setup.thrift.Update$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields = new int[_Fields.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.UPDATE_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.PACKAGE_BUILD_VERSION_CODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.PACKAGE_NAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.PACKAGE_VERSION_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.UPDATE_STATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.REMOTE_FILE_SIZE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$setup$thrift$Update$_Fields[_Fields.DOWNLOAD_PROGRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class UpdateStandardScheme extends StandardScheme<Update> {
        private UpdateStandardScheme() {
        }

        /* synthetic */ UpdateStandardScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, Update update) throws TException {
            tProtocol.readStructBegin();
            while (true) {
                TField readFieldBegin = tProtocol.readFieldBegin();
                byte b = readFieldBegin.type;
                if (b == 0) {
                    tProtocol.readStructEnd();
                    update.validate();
                    return;
                }
                switch (readFieldBegin.id) {
                    case 1:
                        if (b == 11) {
                            update.updateType = tProtocol.readString();
                            update.setUpdateTypeIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 2:
                        if (b == 10) {
                            update.packageBuildVersionCode = tProtocol.readI64();
                            update.setPackageBuildVersionCodeIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 3:
                        if (b == 11) {
                            update.packageName = tProtocol.readString();
                            update.setPackageNameIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 4:
                        if (b == 11) {
                            update.packageVersionName = tProtocol.readString();
                            update.setPackageVersionNameIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 5:
                        if (b == 8) {
                            update.updateState = UpdateState.findByValue(tProtocol.readI32());
                            update.setUpdateStateIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 6:
                        if (b == 10) {
                            update.remoteFileSize = tProtocol.readI64();
                            update.setRemoteFileSizeIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                    case 7:
                        if (b == 4) {
                            update.downloadProgress = tProtocol.readDouble();
                            update.setDownloadProgressIsSet(true);
                            continue;
                            tProtocol.readFieldEnd();
                        }
                        break;
                }
                TProtocolUtil.skip(tProtocol, b);
                tProtocol.readFieldEnd();
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, Update update) throws TException {
            update.validate();
            tProtocol.writeStructBegin(Update.STRUCT_DESC);
            if (update.updateType != null) {
                tProtocol.writeFieldBegin(Update.UPDATE_TYPE_FIELD_DESC);
                tProtocol.writeString(update.updateType);
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(Update.PACKAGE_BUILD_VERSION_CODE_FIELD_DESC);
            tProtocol.writeI64(update.packageBuildVersionCode);
            tProtocol.writeFieldEnd();
            if (update.packageName != null) {
                tProtocol.writeFieldBegin(Update.PACKAGE_NAME_FIELD_DESC);
                tProtocol.writeString(update.packageName);
                tProtocol.writeFieldEnd();
            }
            if (update.packageVersionName != null) {
                tProtocol.writeFieldBegin(Update.PACKAGE_VERSION_NAME_FIELD_DESC);
                tProtocol.writeString(update.packageVersionName);
                tProtocol.writeFieldEnd();
            }
            if (update.updateState != null) {
                tProtocol.writeFieldBegin(Update.UPDATE_STATE_FIELD_DESC);
                tProtocol.writeI32(update.updateState.getValue());
                tProtocol.writeFieldEnd();
            }
            tProtocol.writeFieldBegin(Update.REMOTE_FILE_SIZE_FIELD_DESC);
            tProtocol.writeI64(update.remoteFileSize);
            tProtocol.writeFieldEnd();
            tProtocol.writeFieldBegin(Update.DOWNLOAD_PROGRESS_FIELD_DESC);
            tProtocol.writeDouble(update.downloadProgress);
            tProtocol.writeFieldEnd();
            tProtocol.writeFieldStop();
            tProtocol.writeStructEnd();
        }
    }

    /* loaded from: classes12.dex */
    private static class UpdateStandardSchemeFactory implements SchemeFactory {
        private UpdateStandardSchemeFactory() {
        }

        /* synthetic */ UpdateStandardSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public UpdateStandardScheme mo12846getScheme() {
            return new UpdateStandardScheme(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class UpdateTupleScheme extends TupleScheme<Update> {
        private UpdateTupleScheme() {
        }

        /* synthetic */ UpdateTupleScheme(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void read(TProtocol tProtocol, Update update) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet readBitSet = tTupleProtocol.readBitSet(7);
            if (readBitSet.get(0)) {
                update.updateType = tTupleProtocol.readString();
                update.setUpdateTypeIsSet(true);
            }
            if (readBitSet.get(1)) {
                update.packageBuildVersionCode = tTupleProtocol.readI64();
                update.setPackageBuildVersionCodeIsSet(true);
            }
            if (readBitSet.get(2)) {
                update.packageName = tTupleProtocol.readString();
                update.setPackageNameIsSet(true);
            }
            if (readBitSet.get(3)) {
                update.packageVersionName = tTupleProtocol.readString();
                update.setPackageVersionNameIsSet(true);
            }
            if (readBitSet.get(4)) {
                update.updateState = UpdateState.findByValue(tTupleProtocol.readI32());
                update.setUpdateStateIsSet(true);
            }
            if (readBitSet.get(5)) {
                update.remoteFileSize = tTupleProtocol.readI64();
                update.setRemoteFileSizeIsSet(true);
            }
            if (readBitSet.get(6)) {
                update.downloadProgress = tTupleProtocol.readDouble();
                update.setDownloadProgressIsSet(true);
            }
        }

        @Override // org.apache.thrift.orig.scheme.IScheme
        public void write(TProtocol tProtocol, Update update) throws TException {
            TTupleProtocol tTupleProtocol = (TTupleProtocol) tProtocol;
            BitSet bitSet = new BitSet();
            if (update.isSetUpdateType()) {
                bitSet.set(0);
            }
            if (update.isSetPackageBuildVersionCode()) {
                bitSet.set(1);
            }
            if (update.isSetPackageName()) {
                bitSet.set(2);
            }
            if (update.isSetPackageVersionName()) {
                bitSet.set(3);
            }
            if (update.isSetUpdateState()) {
                bitSet.set(4);
            }
            if (update.isSetRemoteFileSize()) {
                bitSet.set(5);
            }
            if (update.isSetDownloadProgress()) {
                bitSet.set(6);
            }
            tTupleProtocol.writeBitSet(bitSet, 7);
            if (update.isSetUpdateType()) {
                tTupleProtocol.writeString(update.updateType);
            }
            if (update.isSetPackageBuildVersionCode()) {
                tTupleProtocol.writeI64(update.packageBuildVersionCode);
            }
            if (update.isSetPackageName()) {
                tTupleProtocol.writeString(update.packageName);
            }
            if (update.isSetPackageVersionName()) {
                tTupleProtocol.writeString(update.packageVersionName);
            }
            if (update.isSetUpdateState()) {
                tTupleProtocol.writeI32(update.updateState.getValue());
            }
            if (update.isSetRemoteFileSize()) {
                tTupleProtocol.writeI64(update.remoteFileSize);
            }
            if (update.isSetDownloadProgress()) {
                tTupleProtocol.writeDouble(update.downloadProgress);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class UpdateTupleSchemeFactory implements SchemeFactory {
        private UpdateTupleSchemeFactory() {
        }

        /* synthetic */ UpdateTupleSchemeFactory(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // org.apache.thrift.orig.scheme.SchemeFactory
        /* renamed from: getScheme */
        public UpdateTupleScheme mo12846getScheme() {
            return new UpdateTupleScheme(null);
        }
    }

    /* loaded from: classes12.dex */
    public enum _Fields implements TFieldIdEnum {
        UPDATE_TYPE(1, "updateType"),
        PACKAGE_BUILD_VERSION_CODE(2, "packageBuildVersionCode"),
        PACKAGE_NAME(3, CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME),
        PACKAGE_VERSION_NAME(4, "packageVersionName"),
        UPDATE_STATE(5, "updateState"),
        REMOTE_FILE_SIZE(6, "remoteFileSize"),
        DOWNLOAD_PROGRESS(7, "downloadProgress");
        
        private static final Map<String, _Fields> byName = new HashMap();
        private final String _fieldName;
        private final short _thriftId;

        static {
            Iterator it2 = EnumSet.allOf(_Fields.class).iterator();
            while (it2.hasNext()) {
                _Fields _fields = (_Fields) it2.next();
                byName.put(_fields.getFieldName(), _fields);
            }
        }

        _Fields(short s, String str) {
            this._thriftId = s;
            this._fieldName = str;
        }

        public static _Fields findByName(String str) {
            return byName.get(str);
        }

        public static _Fields findByThriftId(int i) {
            switch (i) {
                case 1:
                    return UPDATE_TYPE;
                case 2:
                    return PACKAGE_BUILD_VERSION_CODE;
                case 3:
                    return PACKAGE_NAME;
                case 4:
                    return PACKAGE_VERSION_NAME;
                case 5:
                    return UPDATE_STATE;
                case 6:
                    return REMOTE_FILE_SIZE;
                case 7:
                    return DOWNLOAD_PROGRESS;
                default:
                    return null;
            }
        }

        public static _Fields findByThriftIdOrThrow(int i) {
            _Fields findByThriftId = findByThriftId(i);
            if (findByThriftId != null) {
                return findByThriftId;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("Field ", i, " doesn't exist!"));
        }

        @Override // org.apache.thrift.orig.TFieldIdEnum
        public String getFieldName() {
            return this._fieldName;
        }

        @Override // org.apache.thrift.orig.TFieldIdEnum
        public short getThriftFieldId() {
            return this._thriftId;
        }
    }

    static {
        schemes.put(StandardScheme.class, new UpdateStandardSchemeFactory(null));
        schemes.put(TupleScheme.class, new UpdateTupleSchemeFactory(null));
        EnumMap enumMap = new EnumMap(_Fields.class);
        enumMap.put((EnumMap) _Fields.UPDATE_TYPE, (_Fields) new FieldMetaData("updateType", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.PACKAGE_BUILD_VERSION_CODE, (_Fields) new FieldMetaData("packageBuildVersionCode", (byte) 3, new FieldValueMetaData((byte) 10)));
        enumMap.put((EnumMap) _Fields.PACKAGE_NAME, (_Fields) new FieldMetaData(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.PACKAGE_VERSION_NAME, (_Fields) new FieldMetaData("packageVersionName", (byte) 3, new FieldValueMetaData((byte) 11)));
        enumMap.put((EnumMap) _Fields.UPDATE_STATE, (_Fields) new FieldMetaData("updateState", (byte) 3, new EnumMetaData((byte) 16, UpdateState.class)));
        enumMap.put((EnumMap) _Fields.REMOTE_FILE_SIZE, (_Fields) new FieldMetaData("remoteFileSize", (byte) 3, new FieldValueMetaData((byte) 10)));
        enumMap.put((EnumMap) _Fields.DOWNLOAD_PROGRESS, (_Fields) new FieldMetaData("downloadProgress", (byte) 3, new FieldValueMetaData((byte) 4)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        FieldMetaData.addStructMetaDataMap(Update.class, metaDataMap);
    }

    public Update() {
        this.__isset_bitfield = (byte) 0;
    }

    public Update(Update update) {
        this.__isset_bitfield = (byte) 0;
        this.__isset_bitfield = update.__isset_bitfield;
        if (update.isSetUpdateType()) {
            this.updateType = update.updateType;
        }
        this.packageBuildVersionCode = update.packageBuildVersionCode;
        if (update.isSetPackageName()) {
            this.packageName = update.packageName;
        }
        if (update.isSetPackageVersionName()) {
            this.packageVersionName = update.packageVersionName;
        }
        if (update.isSetUpdateState()) {
            this.updateState = update.updateState;
        }
        this.remoteFileSize = update.remoteFileSize;
        this.downloadProgress = update.downloadProgress;
    }

    public Update(String str, long j, String str2, String str3, UpdateState updateState, long j2, double d) {
        this();
        this.updateType = str;
        this.packageBuildVersionCode = j;
        setPackageBuildVersionCodeIsSet(true);
        this.packageName = str2;
        this.packageVersionName = str3;
        this.updateState = updateState;
        this.remoteFileSize = j2;
        setRemoteFileSizeIsSet(true);
        this.downloadProgress = d;
        setDownloadProgressIsSet(true);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e);
        }
    }

    @Override // org.apache.thrift.orig.TBase
    public void clear() {
        this.updateType = null;
        setPackageBuildVersionCodeIsSet(false);
        this.packageBuildVersionCode = 0L;
        this.packageName = null;
        this.packageVersionName = null;
        this.updateState = null;
        setRemoteFileSizeIsSet(false);
        this.remoteFileSize = 0L;
        setDownloadProgressIsSet(false);
        this.downloadProgress = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    @Override // java.lang.Comparable
    public int compareTo(Update update) {
        int compareTo;
        int compareTo2;
        int compareTo3;
        int compareTo4;
        int compareTo5;
        int compareTo6;
        int compareTo7;
        if (!Update.class.equals(update.getClass())) {
            return Update.class.getName().compareTo(Update.class.getName());
        }
        int compareTo8 = Boolean.valueOf(isSetUpdateType()).compareTo(Boolean.valueOf(update.isSetUpdateType()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (isSetUpdateType() && (compareTo7 = TBaseHelper.compareTo(this.updateType, update.updateType)) != 0) {
            return compareTo7;
        }
        int compareTo9 = Boolean.valueOf(isSetPackageBuildVersionCode()).compareTo(Boolean.valueOf(update.isSetPackageBuildVersionCode()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (isSetPackageBuildVersionCode() && (compareTo6 = TBaseHelper.compareTo(this.packageBuildVersionCode, update.packageBuildVersionCode)) != 0) {
            return compareTo6;
        }
        int compareTo10 = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(update.isSetPackageName()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (isSetPackageName() && (compareTo5 = TBaseHelper.compareTo(this.packageName, update.packageName)) != 0) {
            return compareTo5;
        }
        int compareTo11 = Boolean.valueOf(isSetPackageVersionName()).compareTo(Boolean.valueOf(update.isSetPackageVersionName()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (isSetPackageVersionName() && (compareTo4 = TBaseHelper.compareTo(this.packageVersionName, update.packageVersionName)) != 0) {
            return compareTo4;
        }
        int compareTo12 = Boolean.valueOf(isSetUpdateState()).compareTo(Boolean.valueOf(update.isSetUpdateState()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (isSetUpdateState() && (compareTo3 = TBaseHelper.compareTo((Comparable) this.updateState, (Comparable) update.updateState)) != 0) {
            return compareTo3;
        }
        int compareTo13 = Boolean.valueOf(isSetRemoteFileSize()).compareTo(Boolean.valueOf(update.isSetRemoteFileSize()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (isSetRemoteFileSize() && (compareTo2 = TBaseHelper.compareTo(this.remoteFileSize, update.remoteFileSize)) != 0) {
            return compareTo2;
        }
        int compareTo14 = Boolean.valueOf(isSetDownloadProgress()).compareTo(Boolean.valueOf(update.isSetDownloadProgress()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (isSetDownloadProgress() && (compareTo = TBaseHelper.compareTo(this.downloadProgress, update.downloadProgress)) != 0) {
            return compareTo;
        }
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    /* renamed from: deepCopy */
    public TBase<Update, _Fields> deepCopy2() {
        return new Update(this);
    }

    public boolean equals(Update update) {
        if (update == null) {
            return false;
        }
        boolean isSetUpdateType = isSetUpdateType();
        boolean isSetUpdateType2 = update.isSetUpdateType();
        if (((isSetUpdateType || isSetUpdateType2) && (!isSetUpdateType || !isSetUpdateType2 || !this.updateType.equals(update.updateType))) || this.packageBuildVersionCode != update.packageBuildVersionCode) {
            return false;
        }
        boolean isSetPackageName = isSetPackageName();
        boolean isSetPackageName2 = update.isSetPackageName();
        if ((isSetPackageName || isSetPackageName2) && (!isSetPackageName || !isSetPackageName2 || !this.packageName.equals(update.packageName))) {
            return false;
        }
        boolean isSetPackageVersionName = isSetPackageVersionName();
        boolean isSetPackageVersionName2 = update.isSetPackageVersionName();
        if ((isSetPackageVersionName || isSetPackageVersionName2) && (!isSetPackageVersionName || !isSetPackageVersionName2 || !this.packageVersionName.equals(update.packageVersionName))) {
            return false;
        }
        boolean isSetUpdateState = isSetUpdateState();
        boolean isSetUpdateState2 = update.isSetUpdateState();
        return ((!isSetUpdateState && !isSetUpdateState2) || (isSetUpdateState && isSetUpdateState2 && this.updateState.equals(update.updateState))) && this.remoteFileSize == update.remoteFileSize && this.downloadProgress == update.downloadProgress;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Update)) {
            return equals((Update) obj);
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.thrift.orig.TBase
    /* renamed from: fieldForId */
    public _Fields mo3968fieldForId(int i) {
        return _Fields.findByThriftId(i);
    }

    public double getDownloadProgress() {
        return this.downloadProgress;
    }

    @Override // org.apache.thrift.orig.TBase
    public Object getFieldValue(_Fields _fields) {
        switch (_fields.ordinal()) {
            case 0:
                return getUpdateType();
            case 1:
                return Long.valueOf(getPackageBuildVersionCode());
            case 2:
                return getPackageName();
            case 3:
                return getPackageVersionName();
            case 4:
                return getUpdateState();
            case 5:
                return Long.valueOf(getRemoteFileSize());
            case 6:
                return Double.valueOf(getDownloadProgress());
            default:
                throw new IllegalStateException();
        }
    }

    public long getPackageBuildVersionCode() {
        return this.packageBuildVersionCode;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPackageVersionName() {
        return this.packageVersionName;
    }

    public long getRemoteFileSize() {
        return this.remoteFileSize;
    }

    public UpdateState getUpdateState() {
        return this.updateState;
    }

    public String getUpdateType() {
        return this.updateType;
    }

    public int hashCode() {
        return 0;
    }

    @Override // org.apache.thrift.orig.TBase
    public boolean isSet(_Fields _fields) {
        if (_fields != null) {
            switch (_fields.ordinal()) {
                case 0:
                    return isSetUpdateType();
                case 1:
                    return isSetPackageBuildVersionCode();
                case 2:
                    return isSetPackageName();
                case 3:
                    return isSetPackageVersionName();
                case 4:
                    return isSetUpdateState();
                case 5:
                    return isSetRemoteFileSize();
                case 6:
                    return isSetDownloadProgress();
                default:
                    throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isSetDownloadProgress() {
        return EncodingUtils.testBit(this.__isset_bitfield, 2);
    }

    public boolean isSetPackageBuildVersionCode() {
        return EncodingUtils.testBit(this.__isset_bitfield, 0);
    }

    public boolean isSetPackageName() {
        return this.packageName != null;
    }

    public boolean isSetPackageVersionName() {
        return this.packageVersionName != null;
    }

    public boolean isSetRemoteFileSize() {
        return EncodingUtils.testBit(this.__isset_bitfield, 1);
    }

    public boolean isSetUpdateState() {
        return this.updateState != null;
    }

    public boolean isSetUpdateType() {
        return this.updateType != null;
    }

    @Override // org.apache.thrift.orig.TBase
    public void read(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().read(tProtocol, this);
    }

    public Update setDownloadProgress(double d) {
        this.downloadProgress = d;
        setDownloadProgressIsSet(true);
        return this;
    }

    public void setDownloadProgressIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, z);
    }

    @Override // org.apache.thrift.orig.TBase
    public void setFieldValue(_Fields _fields, Object obj) {
        switch (_fields.ordinal()) {
            case 0:
                if (obj == null) {
                    unsetUpdateType();
                    return;
                } else {
                    setUpdateType((String) obj);
                    return;
                }
            case 1:
                if (obj == null) {
                    unsetPackageBuildVersionCode();
                    return;
                } else {
                    setPackageBuildVersionCode(((Long) obj).longValue());
                    return;
                }
            case 2:
                if (obj == null) {
                    unsetPackageName();
                    return;
                } else {
                    setPackageName((String) obj);
                    return;
                }
            case 3:
                if (obj == null) {
                    unsetPackageVersionName();
                    return;
                } else {
                    setPackageVersionName((String) obj);
                    return;
                }
            case 4:
                if (obj == null) {
                    unsetUpdateState();
                    return;
                } else {
                    setUpdateState((UpdateState) obj);
                    return;
                }
            case 5:
                if (obj == null) {
                    unsetRemoteFileSize();
                    return;
                } else {
                    setRemoteFileSize(((Long) obj).longValue());
                    return;
                }
            case 6:
                if (obj == null) {
                    unsetDownloadProgress();
                    return;
                } else {
                    setDownloadProgress(((Double) obj).doubleValue());
                    return;
                }
            default:
                return;
        }
    }

    public Update setPackageBuildVersionCode(long j) {
        this.packageBuildVersionCode = j;
        setPackageBuildVersionCodeIsSet(true);
        return this;
    }

    public void setPackageBuildVersionCodeIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, z);
    }

    public Update setPackageName(String str) {
        this.packageName = str;
        return this;
    }

    public void setPackageNameIsSet(boolean z) {
        if (!z) {
            this.packageName = null;
        }
    }

    public Update setPackageVersionName(String str) {
        this.packageVersionName = str;
        return this;
    }

    public void setPackageVersionNameIsSet(boolean z) {
        if (!z) {
            this.packageVersionName = null;
        }
    }

    public Update setRemoteFileSize(long j) {
        this.remoteFileSize = j;
        setRemoteFileSizeIsSet(true);
        return this;
    }

    public void setRemoteFileSizeIsSet(boolean z) {
        this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, z);
    }

    public Update setUpdateState(UpdateState updateState) {
        this.updateState = updateState;
        return this;
    }

    public void setUpdateStateIsSet(boolean z) {
        if (!z) {
            this.updateState = null;
        }
    }

    public Update setUpdateType(String str) {
        this.updateType = str;
        return this;
    }

    public void setUpdateTypeIsSet(boolean z) {
        if (!z) {
            this.updateType = null;
        }
    }

    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("Update(", "updateType:");
        String str = this.updateType;
        if (str == null) {
            outline112.append("null");
        } else {
            outline112.append(str);
        }
        outline112.append(", ");
        outline112.append("packageBuildVersionCode:");
        outline112.append(this.packageBuildVersionCode);
        outline112.append(", ");
        outline112.append("packageName:");
        String str2 = this.packageName;
        if (str2 == null) {
            outline112.append("null");
        } else {
            outline112.append(str2);
        }
        outline112.append(", ");
        outline112.append("packageVersionName:");
        String str3 = this.packageVersionName;
        if (str3 == null) {
            outline112.append("null");
        } else {
            outline112.append(str3);
        }
        outline112.append(", ");
        outline112.append("updateState:");
        UpdateState updateState = this.updateState;
        if (updateState == null) {
            outline112.append("null");
        } else {
            outline112.append(updateState);
        }
        outline112.append(", ");
        outline112.append("remoteFileSize:");
        outline112.append(this.remoteFileSize);
        outline112.append(", ");
        outline112.append("downloadProgress:");
        return GeneratedOutlineSupport1.outline84(outline112, this.downloadProgress, ")");
    }

    public void unsetDownloadProgress() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
    }

    public void unsetPackageBuildVersionCode() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
    }

    public void unsetPackageName() {
        this.packageName = null;
    }

    public void unsetPackageVersionName() {
        this.packageVersionName = null;
    }

    public void unsetRemoteFileSize() {
        this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
    }

    public void unsetUpdateState() {
        this.updateState = null;
    }

    public void unsetUpdateType() {
        this.updateType = null;
    }

    public void validate() throws TException {
    }

    @Override // org.apache.thrift.orig.TBase
    public void write(TProtocol tProtocol) throws TException {
        schemes.get(tProtocol.getScheme()).mo12846getScheme().write(tProtocol, this);
    }
}
