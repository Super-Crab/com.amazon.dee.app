package org.bouncycastle.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.CompositePrivateKey;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.jcajce.spec.CompositeAlgorithmSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes5.dex */
public class JcaContentSignerBuilder {
    private OperatorHelper helper;
    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;
    private AlgorithmParameterSpec sigAlgSpec;
    private String signatureAlgorithm;

    public JcaContentSignerBuilder(String str) {
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.signatureAlgorithm = str;
        this.sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(str);
        this.sigAlgSpec = null;
    }

    public JcaContentSignerBuilder(String str, AlgorithmParameterSpec algorithmParameterSpec) {
        AlgorithmIdentifier algorithmIdentifier;
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.signatureAlgorithm = str;
        if (algorithmParameterSpec instanceof PSSParameterSpec) {
            PSSParameterSpec pSSParameterSpec = (PSSParameterSpec) algorithmParameterSpec;
            this.sigAlgSpec = pSSParameterSpec;
            algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_RSASSA_PSS, createPSSParams(pSSParameterSpec));
        } else if (!(algorithmParameterSpec instanceof CompositeAlgorithmSpec)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown sigParamSpec: ");
            outline107.append(algorithmParameterSpec == null ? "null" : algorithmParameterSpec.getClass().getName());
            throw new IllegalArgumentException(outline107.toString());
        } else {
            CompositeAlgorithmSpec compositeAlgorithmSpec = (CompositeAlgorithmSpec) algorithmParameterSpec;
            this.sigAlgSpec = compositeAlgorithmSpec;
            algorithmIdentifier = new AlgorithmIdentifier(MiscObjectIdentifiers.id_alg_composite, createCompParams(compositeAlgorithmSpec));
        }
        this.sigAlgId = algorithmIdentifier;
    }

    private ContentSigner buildComposite(CompositePrivateKey compositePrivateKey) throws OperatorCreationException {
        try {
            List<PrivateKey> privateKeys = compositePrivateKey.getPrivateKeys();
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(this.sigAlgId.getParameters());
            final Signature[] signatureArr = new Signature[aSN1Sequence.size()];
            for (int i = 0; i != aSN1Sequence.size(); i++) {
                signatureArr[i] = this.helper.createSignature(AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i)));
                if (this.random != null) {
                    signatureArr[i].initSign(privateKeys.get(i), this.random);
                } else {
                    signatureArr[i].initSign(privateKeys.get(i));
                }
            }
            final OutputStream createStream = OutputStreamFactory.createStream(signatureArr[0]);
            int i2 = 1;
            while (i2 != signatureArr.length) {
                i2++;
                createStream = new TeeOutputStream(createStream, OutputStreamFactory.createStream(signatureArr[i2]));
            }
            return new ContentSigner() { // from class: org.bouncycastle.operator.jcajce.JcaContentSignerBuilder.2
                OutputStream stream;

                {
                    this.stream = createStream;
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return JcaContentSignerBuilder.this.sigAlgId;
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public OutputStream getOutputStream() {
                    return this.stream;
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public byte[] getSignature() {
                    try {
                        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                        for (int i3 = 0; i3 != signatureArr.length; i3++) {
                            aSN1EncodableVector.add(new DERBitString(signatureArr[i3].sign()));
                        }
                        return new DERSequence(aSN1EncodableVector).getEncoded("DER");
                    } catch (IOException e) {
                        throw new RuntimeOperatorException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("exception encoding signature: ")), e);
                    } catch (SignatureException e2) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception obtaining signature: ");
                        outline107.append(e2.getMessage());
                        throw new RuntimeOperatorException(outline107.toString(), e2);
                    }
                }
            };
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create signer: ")), e);
        }
    }

    private static ASN1Sequence createCompParams(CompositeAlgorithmSpec compositeAlgorithmSpec) {
        ASN1Encodable createPSSParams;
        DefaultSignatureAlgorithmIdentifierFinder defaultSignatureAlgorithmIdentifierFinder = new DefaultSignatureAlgorithmIdentifierFinder();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        List<String> algorithmNames = compositeAlgorithmSpec.getAlgorithmNames();
        List<AlgorithmParameterSpec> parameterSpecs = compositeAlgorithmSpec.getParameterSpecs();
        for (int i = 0; i != algorithmNames.size(); i++) {
            AlgorithmParameterSpec algorithmParameterSpec = parameterSpecs.get(i);
            if (algorithmParameterSpec == null) {
                createPSSParams = defaultSignatureAlgorithmIdentifierFinder.find(algorithmNames.get(i));
            } else if (!(algorithmParameterSpec instanceof PSSParameterSpec)) {
                throw new IllegalArgumentException("unrecognized parameterSpec");
            } else {
                createPSSParams = createPSSParams((PSSParameterSpec) algorithmParameterSpec);
            }
            aSN1EncodableVector.add(createPSSParams);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    private static RSASSAPSSparams createPSSParams(PSSParameterSpec pSSParameterSpec) {
        DefaultDigestAlgorithmIdentifierFinder defaultDigestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
        return new RSASSAPSSparams(defaultDigestAlgorithmIdentifierFinder.find(pSSParameterSpec.getDigestAlgorithm()), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, defaultDigestAlgorithmIdentifierFinder.find(((MGF1ParameterSpec) pSSParameterSpec.getMGFParameters()).getDigestAlgorithm())), new ASN1Integer(pSSParameterSpec.getSaltLength()), new ASN1Integer(pSSParameterSpec.getTrailerField()));
    }

    public ContentSigner build(PrivateKey privateKey) throws OperatorCreationException {
        if (privateKey instanceof CompositePrivateKey) {
            return buildComposite((CompositePrivateKey) privateKey);
        }
        try {
            final Signature createSignature = this.helper.createSignature(this.sigAlgId);
            final AlgorithmIdentifier algorithmIdentifier = this.sigAlgId;
            if (this.random != null) {
                createSignature.initSign(privateKey, this.random);
            } else {
                createSignature.initSign(privateKey);
            }
            return new ContentSigner() { // from class: org.bouncycastle.operator.jcajce.JcaContentSignerBuilder.1
                private OutputStream stream;

                {
                    this.stream = OutputStreamFactory.createStream(createSignature);
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return algorithmIdentifier;
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public OutputStream getOutputStream() {
                    return this.stream;
                }

                @Override // org.bouncycastle.operator.ContentSigner
                public byte[] getSignature() {
                    try {
                        return createSignature.sign();
                    } catch (SignatureException e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception obtaining signature: ");
                        outline107.append(e.getMessage());
                        throw new RuntimeOperatorException(outline107.toString(), e);
                    }
                }
            };
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot create signer: ")), e);
        }
    }

    public JcaContentSignerBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaContentSignerBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
