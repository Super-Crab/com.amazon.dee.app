package com.amazon.alexa.presence.bleconn.identity.encryption;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes9.dex */
public class EncryptionModuleFactoryImpl implements EncryptionModuleFactory {
    private final EncryptionModes encryptionMode;

    /* renamed from: com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactoryImpl$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$presence$bleconn$identity$encryption$EncryptionModuleFactoryImpl$EncryptionModes = new int[EncryptionModes.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$presence$bleconn$identity$encryption$EncryptionModuleFactoryImpl$EncryptionModes[EncryptionModes.NO_ENCRYPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$presence$bleconn$identity$encryption$EncryptionModuleFactoryImpl$EncryptionModes[EncryptionModes.IDENTITY_TOKEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$presence$bleconn$identity$encryption$EncryptionModuleFactoryImpl$EncryptionModes[EncryptionModes.RELATIONSHIP_TOKEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum EncryptionModes {
        NO_ENCRYPTION,
        IDENTITY_TOKEN,
        RELATIONSHIP_TOKEN
    }

    public EncryptionModuleFactoryImpl(EncryptionModes encryptionModes) {
        this.encryptionMode = (EncryptionModes) Objects.requireNonNull(encryptionModes);
    }

    private DecryptionModule buildNoOpDecryptionModule() {
        return $$Lambda$EncryptionModuleFactoryImpl$_mqsEcHYPGBMljmasC8alTXGt2U.INSTANCE;
    }

    private EncryptionModule buildNoOpEncryptionModule() {
        return $$Lambda$EncryptionModuleFactoryImpl$Iuaum2Ax6asL6Mb7HbW4qJvzS4M.INSTANCE;
    }

    static /* synthetic */ byte[] lambda$buildNoOpDecryptionModule$1(byte[] bArr) {
        return bArr;
    }

    static /* synthetic */ byte[] lambda$buildNoOpEncryptionModule$0(byte[] bArr) {
        return bArr;
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactory
    public DecryptionModule buildDecryptionModuleForKey(byte[] bArr) {
        int ordinal = this.encryptionMode.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new IdentityTokenCryptographyModule(null, bArr);
            }
            if (ordinal == 2) {
                return new RelationshipTokenCryptographyModule(bArr);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal Argument, unsupported mode: ");
            outline107.append(this.encryptionMode);
            throw new RuntimeException(outline107.toString());
        }
        return buildNoOpDecryptionModule();
    }

    @Override // com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactory
    public EncryptionModule buildEncryptionModuleForKey(byte[] bArr) {
        int ordinal = this.encryptionMode.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new IdentityTokenCryptographyModule(bArr, null);
            }
            if (ordinal == 2) {
                return new RelationshipTokenCryptographyModule(bArr);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Illegal Argument, unsupported mode: ");
            outline107.append(this.encryptionMode);
            throw new RuntimeException(outline107.toString());
        }
        return buildNoOpEncryptionModule();
    }
}
