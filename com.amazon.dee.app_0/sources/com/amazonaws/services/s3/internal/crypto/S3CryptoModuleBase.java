package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.internal.ReleasableInputStream;
import com.amazonaws.internal.ResettableInputStream;
import com.amazonaws.internal.SdkFilterInputStream;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyResult;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.InputSubstream;
import com.amazonaws.services.s3.internal.S3Direct;
import com.amazonaws.services.s3.internal.crypto.MultipartUploadCryptoContext;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.AbstractPutObjectRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.CopyPartRequest;
import com.amazonaws.services.s3.model.CopyPartResult;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.CryptoStorageMode;
import com.amazonaws.services.s3.model.EncryptionMaterials;
import com.amazonaws.services.s3.model.EncryptionMaterialsFactory;
import com.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.InstructionFileId;
import com.amazonaws.services.s3.model.MaterialsDescriptionProvider;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutInstructionFileRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3DataSource;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectId;
import com.amazonaws.services.s3.model.UploadObjectRequest;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.services.s3.util.Mimetypes;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.IOUtils;
import com.amazonaws.util.LengthCheckInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collections;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
@Deprecated
/* loaded from: classes13.dex */
public abstract class S3CryptoModuleBase<T extends MultipartUploadCryptoContext> extends S3CryptoModule<T> {
    protected static final int DEFAULT_BUFFER_SIZE = 2048;
    private static final boolean IS_MULTI_PART = true;
    private static final int MAX_RETRY_COUNT = 9;
    protected final ContentCryptoScheme contentCryptoScheme;
    protected final CryptoConfiguration cryptoConfig;
    protected final S3CryptoScheme cryptoScheme;
    protected final EncryptionMaterialsProvider kekMaterialsProvider;
    protected final AWSKMSClient kms;
    protected final Log log;
    protected final Map<String, T> multipartUploadContexts;
    protected final S3Direct s3;

    /* JADX INFO: Access modifiers changed from: protected */
    public S3CryptoModuleBase(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this.log = LogFactory.getLog(getClass());
        this.multipartUploadContexts = GeneratedOutlineSupport1.outline136();
        if (cryptoConfiguration.isReadOnly()) {
            this.kekMaterialsProvider = encryptionMaterialsProvider;
            this.s3 = s3Direct;
            this.cryptoConfig = cryptoConfiguration;
            this.cryptoScheme = S3CryptoScheme.from(cryptoConfiguration.getCryptoMode());
            this.contentCryptoScheme = this.cryptoScheme.getContentCryptoScheme();
            this.kms = aWSKMSClient;
            return;
        }
        throw new IllegalArgumentException("The crypto configuration parameter is required to be read-only");
    }

    private ContentCryptoMaterial buildContentCryptoMaterial(EncryptionMaterials encryptionMaterials, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        byte[] bArr = new byte[this.contentCryptoScheme.getIVLengthInBytes()];
        this.cryptoScheme.getSecureRandom().nextBytes(bArr);
        if (encryptionMaterials.isKMSEnabled()) {
            Map<String, String> mergeMaterialDescriptions = ContentCryptoMaterial.mergeMaterialDescriptions(encryptionMaterials, amazonWebServiceRequest);
            GenerateDataKeyRequest withKeySpec = new GenerateDataKeyRequest().withEncryptionContext(mergeMaterialDescriptions).withKeyId(encryptionMaterials.getCustomerMasterKeyId()).withKeySpec(this.contentCryptoScheme.getKeySpec());
            withKeySpec.withGeneralProgressListener(amazonWebServiceRequest.getGeneralProgressListener()).withRequestMetricCollector(amazonWebServiceRequest.getRequestMetricCollector());
            GenerateDataKeyResult generateDataKey = this.kms.generateDataKey(withKeySpec);
            return ContentCryptoMaterial.wrap(new SecretKeySpec(BinaryUtils.copyAllBytesFrom(generateDataKey.getPlaintext()), this.contentCryptoScheme.getKeyGeneratorAlgorithm()), bArr, this.contentCryptoScheme, provider, new KMSSecuredCEK(BinaryUtils.copyAllBytesFrom(generateDataKey.getCiphertextBlob()), mergeMaterialDescriptions));
        }
        return ContentCryptoMaterial.create(generateCEK(encryptionMaterials, provider), bArr, encryptionMaterials, this.cryptoScheme, provider, this.kms, amazonWebServiceRequest);
    }

    private ContentCryptoMaterial ccmFromJson(String str) {
        return ContentCryptoMaterial.fromInstructionFile(Collections.unmodifiableMap(JsonUtils.jsonToMap(str)), this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), false, this.kms);
    }

    private ContentCryptoMaterial contentCryptoMaterialOf(S3ObjectWrapper s3ObjectWrapper) {
        if (s3ObjectWrapper.hasEncryptionInfo()) {
            return ContentCryptoMaterial.fromObjectMetadata(s3ObjectWrapper.getObjectMetadata(), this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), false, this.kms);
        }
        S3ObjectWrapper fetchInstructionFile = fetchInstructionFile(s3ObjectWrapper.getS3ObjectId(), null);
        if (fetchInstructionFile != null) {
            if (fetchInstructionFile.isInstructionFile()) {
                return ccmFromJson(fetchInstructionFile.toJsonString());
            }
            throw new AmazonClientException("Invalid instruction file for S3 object: " + s3ObjectWrapper);
        }
        throw new IllegalArgumentException("S3 object is not encrypted: " + s3ObjectWrapper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] getAdjustedCryptoRange(long[] jArr) {
        if (jArr == null || jArr[0] > jArr[1]) {
            return null;
        }
        return new long[]{getCipherBlockLowerBound(jArr[0]), getCipherBlockUpperBound(jArr[1])};
    }

    private static long getCipherBlockLowerBound(long j) {
        long j2 = (j - (j % 16)) - 16;
        if (j2 < 0) {
            return 0L;
        }
        return j2;
    }

    private static long getCipherBlockUpperBound(long j) {
        long j2 = (16 - (j % 16)) + j + 16;
        if (j2 < 0) {
            return Long.MAX_VALUE;
        }
        return j2;
    }

    private ContentCryptoMaterial newContentCryptoMaterial(EncryptionMaterialsProvider encryptionMaterialsProvider, Map<String, String> map, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials = encryptionMaterialsProvider.getEncryptionMaterials(map);
        if (encryptionMaterials == null) {
            return null;
        }
        return buildContentCryptoMaterial(encryptionMaterials, provider, amazonWebServiceRequest);
    }

    private CipherLiteInputStream newS3CipherLiteInputStream(AbstractPutObjectRequest abstractPutObjectRequest, ContentCryptoMaterial contentCryptoMaterial, long j) {
        File file = abstractPutObjectRequest.getFile();
        InputStream inputStream = abstractPutObjectRequest.getInputStream();
        FilterInputStream filterInputStream = null;
        try {
            if (file != null) {
                filterInputStream = new ResettableInputStream(file);
            } else if (inputStream != null) {
                filterInputStream = ReleasableInputStream.wrap(inputStream);
            }
            if (j > -1) {
                filterInputStream = new LengthCheckInputStream(filterInputStream, j, false);
            }
            CipherLite cipherLite = contentCryptoMaterial.getCipherLite();
            if (cipherLite.markSupported()) {
                return new CipherLiteInputStream(filterInputStream, cipherLite, 2048);
            }
            return new RenewableCipherLiteInputStream(filterInputStream, cipherLite, 2048);
        } catch (Exception e) {
            S3DataSource.Utils.cleanupDataSource(abstractPutObjectRequest, file, inputStream, null, this.log);
            throw new AmazonClientException("Unable to create cipher input stream", e);
        }
    }

    private PutObjectResult putObjectUsingInstructionFile(PutObjectRequest putObjectRequest) {
        File file = putObjectRequest.getFile();
        InputStream inputStream = putObjectRequest.getInputStream();
        PutObjectRequest mo6731withInputStream = putObjectRequest.mo6742clone().mo6729withFile((File) null).mo6731withInputStream((InputStream) null);
        mo6731withInputStream.setKey(mo6731withInputStream.getKey() + ".instruction");
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(putObjectRequest);
        PutObjectRequest putObjectRequest2 = (PutObjectRequest) wrapWithCipher(putObjectRequest, createContentCryptoMaterial);
        try {
            PutObjectResult putObject = this.s3.putObject(putObjectRequest2);
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
            this.s3.putObject(updateInstructionPutRequest(mo6731withInputStream, createContentCryptoMaterial));
            return putObject;
        } catch (Throwable th) {
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
            throw th;
        }
    }

    private PutObjectResult putObjectUsingMetadata(PutObjectRequest putObjectRequest) {
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(putObjectRequest);
        File file = putObjectRequest.getFile();
        InputStream inputStream = putObjectRequest.getInputStream();
        PutObjectRequest putObjectRequest2 = (PutObjectRequest) wrapWithCipher(putObjectRequest, createContentCryptoMaterial);
        putObjectRequest.setMetadata(updateMetadataWithContentCryptoMaterial(putObjectRequest.getMetadata(), putObjectRequest.getFile(), createContentCryptoMaterial));
        try {
            return this.s3.putObject(putObjectRequest2);
        } finally {
            S3DataSource.Utils.cleanupDataSource(putObjectRequest, file, inputStream, putObjectRequest2.getInputStream(), this.log);
        }
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final void abortMultipartUploadSecurely(AbortMultipartUploadRequest abortMultipartUploadRequest) {
        this.s3.abortMultipartUpload(abortMultipartUploadRequest);
        this.multipartUploadContexts.remove(abortMultipartUploadRequest.getUploadId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final <X extends AmazonWebServiceRequest> X appendUserAgent(X x, String str) {
        x.getRequestClientOptions().appendUserAgent(str);
        return x;
    }

    abstract CipherLite cipherLiteForNextPart(T t);

    protected abstract long ciphertextLength(long j);

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public CompleteMultipartUploadResult completeMultipartUploadSecurely(CompleteMultipartUploadRequest completeMultipartUploadRequest) {
        appendUserAgent(completeMultipartUploadRequest, AmazonS3EncryptionClient.USER_AGENT);
        String uploadId = completeMultipartUploadRequest.getUploadId();
        T t = this.multipartUploadContexts.get(uploadId);
        if (t != null && !t.hasFinalPartBeenSeen()) {
            throw new AmazonClientException("Unable to complete an encrypted multipart upload without being told which part was the last.  Without knowing which part was the last, the encrypted data in Amazon S3 is incomplete and corrupt.");
        }
        CompleteMultipartUploadResult completeMultipartUpload = this.s3.completeMultipartUpload(completeMultipartUploadRequest);
        if (t != null && this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
            this.s3.putObject(createInstructionPutRequest(t.getBucketName(), t.getKey(), t.getContentCryptoMaterial()));
        }
        this.multipartUploadContexts.remove(uploadId);
        return completeMultipartUpload;
    }

    abstract long computeLastPartSize(UploadPartRequest uploadPartRequest);

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final CopyPartResult copyPartSecurely(CopyPartRequest copyPartRequest) {
        T t = this.multipartUploadContexts.get(copyPartRequest.getUploadId());
        CopyPartResult copyPart = this.s3.copyPart(copyPartRequest);
        if (t != null && !t.hasFinalPartBeenSeen()) {
            t.setHasFinalPartBeenSeen(true);
        }
        return copyPart;
    }

    protected final ContentCryptoMaterial createContentCryptoMaterial(AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials;
        if ((amazonWebServiceRequest instanceof EncryptionMaterialsFactory) && (encryptionMaterials = ((EncryptionMaterialsFactory) amazonWebServiceRequest).getEncryptionMaterials()) != null) {
            return buildContentCryptoMaterial(encryptionMaterials, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
        }
        if (amazonWebServiceRequest instanceof MaterialsDescriptionProvider) {
            Map<String, String> materialsDescription = ((MaterialsDescriptionProvider) amazonWebServiceRequest).getMaterialsDescription();
            ContentCryptoMaterial newContentCryptoMaterial = newContentCryptoMaterial(this.kekMaterialsProvider, materialsDescription, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
            if (newContentCryptoMaterial != null) {
                return newContentCryptoMaterial;
            }
            if (materialsDescription != null && !this.kekMaterialsProvider.getEncryptionMaterials().isKMSEnabled()) {
                throw new AmazonClientException("No material available from the encryption material provider for description " + materialsDescription);
            }
        }
        return newContentCryptoMaterial(this.kekMaterialsProvider, this.cryptoConfig.getCryptoProvider(), amazonWebServiceRequest);
    }

    final GetObjectRequest createInstructionGetRequest(S3ObjectId s3ObjectId) {
        return createInstructionGetRequest(s3ObjectId, null);
    }

    protected final PutObjectRequest createInstructionPutRequest(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        byte[] bytes = contentCryptoMaterial.toJsonString(this.cryptoConfig.getCryptoMode()).getBytes(StringUtils.UTF8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.addUserMetadata(Headers.CRYPTO_INSTRUCTION_FILE, "");
        InstructionFileId instructionFileId = new S3ObjectId(str, str2).instructionFileId();
        return new PutObjectRequest(instructionFileId.getBucket(), instructionFileId.getKey(), byteArrayInputStream, objectMetadata);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final S3ObjectWrapper fetchInstructionFile(S3ObjectId s3ObjectId, String str) {
        try {
            S3Object object = this.s3.getObject(createInstructionGetRequest(s3ObjectId, str));
            if (object != null) {
                return new S3ObjectWrapper(object, s3ObjectId);
            }
            return null;
        } catch (AmazonServiceException e) {
            if (this.log.isDebugEnabled()) {
                Log log = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to retrieve instruction file : ");
                outline107.append(e.getMessage());
                log.debug(outline107.toString());
            }
            return null;
        }
    }

    protected final SecretKey generateCEK(EncryptionMaterials encryptionMaterials, Provider provider) {
        KeyGenerator keyGenerator;
        boolean z;
        String keyGeneratorAlgorithm = this.contentCryptoScheme.getKeyGeneratorAlgorithm();
        try {
            if (provider == null) {
                keyGenerator = KeyGenerator.getInstance(keyGeneratorAlgorithm);
            } else {
                keyGenerator = KeyGenerator.getInstance(keyGeneratorAlgorithm, provider);
            }
            keyGenerator.init(this.contentCryptoScheme.getKeyLengthInBits(), this.cryptoScheme.getSecureRandom());
            KeyPair keyPair = encryptionMaterials.getKeyPair();
            if (keyPair == null || this.cryptoScheme.getKeyWrapScheme().getKeyWrapAlgorithm(keyPair.getPublic(), provider) != null) {
                z = false;
            } else {
                Provider provider2 = keyGenerator.getProvider();
                z = BouncyCastleProvider.PROVIDER_NAME.equals(provider2 == null ? null : provider2.getName());
            }
            SecretKey generateKey = keyGenerator.generateKey();
            if (z && generateKey.getEncoded()[0] == 0) {
                for (int i = 0; i < 9; i++) {
                    SecretKey generateKey2 = keyGenerator.generateKey();
                    if (generateKey2.getEncoded()[0] != 0) {
                        return generateKey2;
                    }
                }
                throw new AmazonClientException("Failed to generate secret key");
            }
            return generateKey;
        } catch (NoSuchAlgorithmException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to generate envelope symmetric key:");
            outline107.append(e.getMessage());
            throw new AmazonClientException(outline107.toString(), e);
        }
    }

    public final S3CryptoScheme getS3CryptoScheme() {
        return this.cryptoScheme;
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public InitiateMultipartUploadResult initiateMultipartUploadSecurely(InitiateMultipartUploadRequest initiateMultipartUploadRequest) {
        appendUserAgent(initiateMultipartUploadRequest, AmazonS3EncryptionClient.USER_AGENT);
        ContentCryptoMaterial createContentCryptoMaterial = createContentCryptoMaterial(initiateMultipartUploadRequest);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.ObjectMetadata) {
            ObjectMetadata objectMetadata = initiateMultipartUploadRequest.getObjectMetadata();
            if (objectMetadata == null) {
                objectMetadata = new ObjectMetadata();
            }
            initiateMultipartUploadRequest.setObjectMetadata(updateMetadataWithContentCryptoMaterial(objectMetadata, null, createContentCryptoMaterial));
        }
        InitiateMultipartUploadResult initiateMultipartUpload = this.s3.initiateMultipartUpload(initiateMultipartUploadRequest);
        T mo6713newUploadContext = mo6713newUploadContext(initiateMultipartUploadRequest, createContentCryptoMaterial);
        if (initiateMultipartUploadRequest instanceof MaterialsDescriptionProvider) {
            mo6713newUploadContext.setMaterialsDescription(((MaterialsDescriptionProvider) initiateMultipartUploadRequest).getMaterialsDescription());
        }
        this.multipartUploadContexts.put(initiateMultipartUpload.getUploadId(), mo6713newUploadContext);
        return initiateMultipartUpload;
    }

    protected final CipherLiteInputStream newMultipartS3CipherInputStream(UploadPartRequest uploadPartRequest, CipherLite cipherLite) {
        InputStream resettableInputStream;
        File file = uploadPartRequest.getFile();
        InputStream inputStream = uploadPartRequest.getInputStream();
        InputStream inputStream2 = null;
        try {
            if (file != null) {
                resettableInputStream = new ResettableInputStream(file);
            } else if (inputStream == null) {
                throw new IllegalArgumentException("A File or InputStream must be specified when uploading part");
            } else {
                resettableInputStream = inputStream;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            InputSubstream inputSubstream = new InputSubstream(resettableInputStream, uploadPartRequest.getFileOffset(), uploadPartRequest.getPartSize(), uploadPartRequest.isLastPart());
            if (cipherLite.markSupported()) {
                return new CipherLiteInputStream(inputSubstream, cipherLite, 2048, true, uploadPartRequest.isLastPart());
            }
            return new RenewableCipherLiteInputStream(inputSubstream, cipherLite, 2048, true, uploadPartRequest.isLastPart());
        } catch (Exception e2) {
            e = e2;
            inputStream2 = resettableInputStream;
            S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, inputStream2, this.log);
            throw new AmazonClientException("Unable to create cipher input stream", e);
        }
    }

    /* renamed from: newUploadContext */
    abstract T mo6713newUploadContext(InitiateMultipartUploadRequest initiateMultipartUploadRequest, ContentCryptoMaterial contentCryptoMaterial);

    protected final long plaintextLength(AbstractPutObjectRequest abstractPutObjectRequest, ObjectMetadata objectMetadata) {
        if (abstractPutObjectRequest.getFile() != null) {
            return abstractPutObjectRequest.getFile().length();
        }
        if (abstractPutObjectRequest.getInputStream() != null && objectMetadata.getRawMetadataValue("Content-Length") != null) {
            return objectMetadata.getContentLength();
        }
        return -1L;
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final PutObjectResult putInstructionFileSecurely(PutInstructionFileRequest putInstructionFileRequest) {
        ContentCryptoMaterial recreate;
        S3ObjectId s3ObjectId = putInstructionFileRequest.getS3ObjectId();
        GetObjectRequest getObjectRequest = new GetObjectRequest(s3ObjectId);
        appendUserAgent(getObjectRequest, AmazonS3EncryptionClient.USER_AGENT);
        S3Object object = this.s3.getObject(getObjectRequest);
        IOUtils.closeQuietly(object, this.log);
        if (object != null) {
            S3ObjectWrapper s3ObjectWrapper = new S3ObjectWrapper(object, s3ObjectId);
            try {
                ContentCryptoMaterial contentCryptoMaterialOf = contentCryptoMaterialOf(s3ObjectWrapper);
                if (ContentCryptoScheme.AES_GCM.equals(contentCryptoMaterialOf.getContentCryptoScheme()) && this.cryptoConfig.getCryptoMode() == CryptoMode.EncryptionOnly) {
                    throw new SecurityException("Lowering the protection of encryption material is not allowed");
                }
                securityCheck(contentCryptoMaterialOf, s3ObjectWrapper);
                EncryptionMaterials encryptionMaterials = putInstructionFileRequest.getEncryptionMaterials();
                if (encryptionMaterials == null) {
                    recreate = contentCryptoMaterialOf.recreate(putInstructionFileRequest.getMaterialsDescription(), this.kekMaterialsProvider, this.cryptoScheme, this.cryptoConfig.getCryptoProvider(), this.kms, putInstructionFileRequest);
                } else {
                    recreate = contentCryptoMaterialOf.recreate(encryptionMaterials, this.kekMaterialsProvider, this.cryptoScheme, this.cryptoConfig.getCryptoProvider(), this.kms, putInstructionFileRequest);
                }
                return this.s3.putObject(updateInstructionPutRequest(putInstructionFileRequest.createPutObjectRequest(object), recreate));
            } catch (Error e) {
                IOUtils.closeQuietly(object, this.log);
                throw e;
            } catch (RuntimeException e2) {
                IOUtils.closeQuietly(object, this.log);
                throw e2;
            }
        }
        throw new IllegalArgumentException("The specified S3 object (" + s3ObjectId + ") doesn't exist.");
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public final void putLocalObjectSecurely(UploadObjectRequest uploadObjectRequest, String str, OutputStream outputStream) throws IOException {
        UploadObjectRequest mo6742clone = uploadObjectRequest.mo6742clone();
        File file = mo6742clone.getFile();
        InputStream inputStream = mo6742clone.getInputStream();
        T t = this.multipartUploadContexts.get(str);
        UploadObjectRequest uploadObjectRequest2 = (UploadObjectRequest) wrapWithCipher(mo6742clone, t.getContentCryptoMaterial());
        try {
            IOUtils.copy(uploadObjectRequest2.getInputStream(), outputStream);
            t.setHasFinalPartBeenSeen(true);
        } finally {
            S3DataSource.Utils.cleanupDataSource(uploadObjectRequest2, file, inputStream, uploadObjectRequest2.getInputStream(), this.log);
            IOUtils.closeQuietly(outputStream, this.log);
        }
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public PutObjectResult putObjectSecurely(PutObjectRequest putObjectRequest) {
        appendUserAgent(putObjectRequest, AmazonS3EncryptionClient.USER_AGENT);
        if (this.cryptoConfig.getStorageMode() == CryptoStorageMode.InstructionFile) {
            return putObjectUsingInstructionFile(putObjectRequest);
        }
        return putObjectUsingMetadata(putObjectRequest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void securityCheck(ContentCryptoMaterial contentCryptoMaterial, S3ObjectWrapper s3ObjectWrapper) {
    }

    protected final PutObjectRequest updateInstructionPutRequest(PutObjectRequest putObjectRequest, ContentCryptoMaterial contentCryptoMaterial) {
        byte[] bytes = contentCryptoMaterial.toJsonString(this.cryptoConfig.getCryptoMode()).getBytes(StringUtils.UTF8);
        ObjectMetadata metadata = putObjectRequest.getMetadata();
        if (metadata == null) {
            metadata = new ObjectMetadata();
            putObjectRequest.setMetadata(metadata);
        }
        metadata.setContentLength(bytes.length);
        metadata.addUserMetadata(Headers.CRYPTO_INSTRUCTION_FILE, "");
        putObjectRequest.setMetadata(metadata);
        putObjectRequest.setInputStream(new ByteArrayInputStream(bytes));
        return putObjectRequest;
    }

    protected final ObjectMetadata updateMetadataWithContentCryptoMaterial(ObjectMetadata objectMetadata, File file, ContentCryptoMaterial contentCryptoMaterial) {
        if (objectMetadata == null) {
            objectMetadata = new ObjectMetadata();
        }
        if (file != null) {
            objectMetadata.setContentType(Mimetypes.getInstance().getMimetype(file));
        }
        return contentCryptoMaterial.toObjectMetadata(objectMetadata, this.cryptoConfig.getCryptoMode());
    }

    abstract void updateUploadContext(T t, SdkFilterInputStream sdkFilterInputStream);

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public UploadPartResult uploadPartSecurely(UploadPartRequest uploadPartRequest) {
        appendUserAgent(uploadPartRequest, AmazonS3EncryptionClient.USER_AGENT);
        int blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        boolean isLastPart = uploadPartRequest.isLastPart();
        String uploadId = uploadPartRequest.getUploadId();
        long partSize = uploadPartRequest.getPartSize();
        boolean z = 0 == partSize % ((long) blockSizeInBytes);
        if (!isLastPart && !z) {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline52("Invalid part size: part sizes for encrypted multipart uploads must be multiples of the cipher block size (", blockSizeInBytes, ") with the exception of the last part."));
        }
        T t = this.multipartUploadContexts.get(uploadId);
        if (t != null) {
            t.beginPartUpload(uploadPartRequest.getPartNumber());
            CipherLite cipherLiteForNextPart = cipherLiteForNextPart(t);
            File file = uploadPartRequest.getFile();
            InputStream inputStream = uploadPartRequest.getInputStream();
            CipherLiteInputStream cipherLiteInputStream = null;
            try {
                CipherLiteInputStream newMultipartS3CipherInputStream = newMultipartS3CipherInputStream(uploadPartRequest, cipherLiteForNextPart);
                try {
                    SdkFilterInputStream mo6714wrapForMultipart = mo6714wrapForMultipart(newMultipartS3CipherInputStream, partSize);
                    uploadPartRequest.setInputStream(mo6714wrapForMultipart);
                    uploadPartRequest.setFile(null);
                    uploadPartRequest.setFileOffset(0L);
                    if (isLastPart) {
                        long computeLastPartSize = computeLastPartSize(uploadPartRequest);
                        if (computeLastPartSize > -1) {
                            uploadPartRequest.setPartSize(computeLastPartSize);
                        }
                        if (t.hasFinalPartBeenSeen()) {
                            throw new AmazonClientException("This part was specified as the last part in a multipart upload, but a previous part was already marked as the last part.  Only the last part of the upload should be marked as the last part.");
                        }
                    }
                    UploadPartResult uploadPart = this.s3.uploadPart(uploadPartRequest);
                    S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, mo6714wrapForMultipart, this.log);
                    t.endPartUpload();
                    if (isLastPart) {
                        t.setHasFinalPartBeenSeen(true);
                    }
                    updateUploadContext(t, mo6714wrapForMultipart);
                    return uploadPart;
                } catch (Throwable th) {
                    th = th;
                    cipherLiteInputStream = newMultipartS3CipherInputStream;
                    S3DataSource.Utils.cleanupDataSource(uploadPartRequest, file, inputStream, cipherLiteInputStream, this.log);
                    t.endPartUpload();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new AmazonClientException(GeneratedOutlineSupport1.outline72("No client-side information available on upload ID ", uploadId));
        }
    }

    /* renamed from: wrapForMultipart */
    abstract <I extends CipherLiteInputStream> SdkFilterInputStream mo6714wrapForMultipart(I i, long j);

    protected final <R extends AbstractPutObjectRequest> R wrapWithCipher(R r, ContentCryptoMaterial contentCryptoMaterial) {
        ObjectMetadata metadata = r.getMetadata();
        if (metadata == null) {
            metadata = new ObjectMetadata();
        }
        if (metadata.getContentMD5() != null) {
            metadata.addUserMetadata(Headers.UNENCRYPTED_CONTENT_MD5, metadata.getContentMD5());
        }
        metadata.setContentMD5(null);
        long plaintextLength = plaintextLength(r, metadata);
        if (plaintextLength >= 0) {
            metadata.addUserMetadata(Headers.UNENCRYPTED_CONTENT_LENGTH, Long.toString(plaintextLength));
            metadata.setContentLength(ciphertextLength(plaintextLength));
        }
        r.setMetadata(metadata);
        r.setInputStream(newS3CipherLiteInputStream(r, contentCryptoMaterial, plaintextLength));
        r.setFile(null);
        return r;
    }

    final GetObjectRequest createInstructionGetRequest(S3ObjectId s3ObjectId, String str) {
        return new GetObjectRequest(s3ObjectId.instructionFileId(str));
    }

    private ContentCryptoMaterial newContentCryptoMaterial(EncryptionMaterialsProvider encryptionMaterialsProvider, Provider provider, AmazonWebServiceRequest amazonWebServiceRequest) {
        EncryptionMaterials encryptionMaterials = encryptionMaterialsProvider.getEncryptionMaterials();
        if (encryptionMaterials != null) {
            return buildContentCryptoMaterial(encryptionMaterials, provider, amazonWebServiceRequest);
        }
        throw new AmazonClientException("No material available from the encryption material provider");
    }

    protected S3CryptoModuleBase(S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this.log = LogFactory.getLog(getClass());
        this.multipartUploadContexts = GeneratedOutlineSupport1.outline136();
        this.kekMaterialsProvider = encryptionMaterialsProvider;
        this.s3 = s3Direct;
        this.cryptoConfig = cryptoConfiguration;
        this.cryptoScheme = S3CryptoScheme.from(cryptoConfiguration.getCryptoMode());
        this.contentCryptoScheme = this.cryptoScheme.getContentCryptoScheme();
        this.kms = null;
    }
}
