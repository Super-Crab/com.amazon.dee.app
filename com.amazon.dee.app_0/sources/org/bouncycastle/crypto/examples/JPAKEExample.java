package org.bouncycastle.crypto.examples;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.agreement.jpake.JPAKEParticipant;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroups;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound2Payload;
import org.bouncycastle.crypto.agreement.jpake.JPAKERound3Payload;
import org.bouncycastle.crypto.digests.SHA256Digest;
/* loaded from: classes4.dex */
public class JPAKEExample {
    private static BigInteger deriveSessionKey(BigInteger bigInteger) {
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.update(byteArray, 0, byteArray.length);
        sHA256Digest.doFinal(bArr, 0);
        return new BigInteger(bArr);
    }

    public static void main(String[] strArr) throws CryptoException {
        JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup = JPAKEPrimeOrderGroups.NIST_3072;
        BigInteger p = jPAKEPrimeOrderGroup.getP();
        BigInteger q = jPAKEPrimeOrderGroup.getQ();
        BigInteger g = jPAKEPrimeOrderGroup.getG();
        System.out.println("********* Initialization **********");
        System.out.println("Public parameters for the cyclic group:");
        PrintStream printStream = System.out;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("p (");
        outline107.append(p.bitLength());
        outline107.append(" bits): ");
        outline107.append(p.toString(16));
        printStream.println(outline107.toString());
        PrintStream printStream2 = System.out;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("q (");
        outline1072.append(q.bitLength());
        outline1072.append(" bits): ");
        outline1072.append(q.toString(16));
        printStream2.println(outline1072.toString());
        PrintStream printStream3 = System.out;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("g (");
        outline1073.append(p.bitLength());
        outline1073.append(" bits): ");
        outline1073.append(g.toString(16));
        printStream3.println(outline1073.toString());
        PrintStream printStream4 = System.out;
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("p mod q = ");
        outline1074.append(p.mod(q).toString(16));
        printStream4.println(outline1074.toString());
        PrintStream printStream5 = System.out;
        StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("g^{q} mod p = ");
        outline1075.append(g.modPow(q, p).toString(16));
        printStream5.println(outline1075.toString());
        System.out.println("");
        System.out.println(GeneratedOutlineSupport1.outline77("(Secret passwords used by Alice and Bob: \"", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, "\" and \"", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, "\")\n"));
        SHA256Digest sHA256Digest = new SHA256Digest();
        SecureRandom secureRandom = new SecureRandom();
        JPAKEParticipant jPAKEParticipant = new JPAKEParticipant("alice", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKEParticipant jPAKEParticipant2 = new JPAKEParticipant("bob", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD.toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKERound1Payload createRound1PayloadToSend = jPAKEParticipant.createRound1PayloadToSend();
        JPAKERound1Payload createRound1PayloadToSend2 = jPAKEParticipant2.createRound1PayloadToSend();
        System.out.println("************ Round 1 **************");
        System.out.println("Alice sends to Bob: ");
        PrintStream printStream6 = System.out;
        StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("g^{x1}=");
        outline1076.append(createRound1PayloadToSend.getGx1().toString(16));
        printStream6.println(outline1076.toString());
        PrintStream printStream7 = System.out;
        StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("g^{x2}=");
        outline1077.append(createRound1PayloadToSend.getGx2().toString(16));
        printStream7.println(outline1077.toString());
        PrintStream printStream8 = System.out;
        StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("KP{x1}={");
        outline1078.append(createRound1PayloadToSend.getKnowledgeProofForX1()[0].toString(16));
        outline1078.append("};{");
        outline1078.append(createRound1PayloadToSend.getKnowledgeProofForX1()[1].toString(16));
        outline1078.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream8.println(outline1078.toString());
        PrintStream printStream9 = System.out;
        StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("KP{x2}={");
        outline1079.append(createRound1PayloadToSend.getKnowledgeProofForX2()[0].toString(16));
        outline1079.append("};{");
        outline1079.append(createRound1PayloadToSend.getKnowledgeProofForX2()[1].toString(16));
        outline1079.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream9.println(outline1079.toString());
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        PrintStream printStream10 = System.out;
        StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("g^{x3}=");
        outline10710.append(createRound1PayloadToSend2.getGx1().toString(16));
        printStream10.println(outline10710.toString());
        PrintStream printStream11 = System.out;
        StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("g^{x4}=");
        outline10711.append(createRound1PayloadToSend2.getGx2().toString(16));
        printStream11.println(outline10711.toString());
        PrintStream printStream12 = System.out;
        StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("KP{x3}={");
        outline10712.append(createRound1PayloadToSend2.getKnowledgeProofForX1()[0].toString(16));
        outline10712.append("};{");
        outline10712.append(createRound1PayloadToSend2.getKnowledgeProofForX1()[1].toString(16));
        outline10712.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream12.println(outline10712.toString());
        PrintStream printStream13 = System.out;
        StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("KP{x4}={");
        outline10713.append(createRound1PayloadToSend2.getKnowledgeProofForX2()[0].toString(16));
        outline10713.append("};{");
        outline10713.append(createRound1PayloadToSend2.getKnowledgeProofForX2()[1].toString(16));
        outline10713.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream13.println(outline10713.toString());
        System.out.println("");
        jPAKEParticipant.validateRound1PayloadReceived(createRound1PayloadToSend2);
        System.out.println("Alice checks g^{x4}!=1: OK");
        System.out.println("Alice checks KP{x3}: OK");
        System.out.println("Alice checks KP{x4}: OK");
        System.out.println("");
        jPAKEParticipant2.validateRound1PayloadReceived(createRound1PayloadToSend);
        System.out.println("Bob checks g^{x2}!=1: OK");
        System.out.println("Bob checks KP{x1},: OK");
        System.out.println("Bob checks KP{x2},: OK");
        System.out.println("");
        JPAKERound2Payload createRound2PayloadToSend = jPAKEParticipant.createRound2PayloadToSend();
        JPAKERound2Payload createRound2PayloadToSend2 = jPAKEParticipant2.createRound2PayloadToSend();
        System.out.println("************ Round 2 **************");
        System.out.println("Alice sends to Bob: ");
        PrintStream printStream14 = System.out;
        StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("A=");
        outline10714.append(createRound2PayloadToSend.getA().toString(16));
        printStream14.println(outline10714.toString());
        PrintStream printStream15 = System.out;
        StringBuilder outline10715 = GeneratedOutlineSupport1.outline107("KP{x2*s}={");
        outline10715.append(createRound2PayloadToSend.getKnowledgeProofForX2s()[0].toString(16));
        outline10715.append("},{");
        outline10715.append(createRound2PayloadToSend.getKnowledgeProofForX2s()[1].toString(16));
        outline10715.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream15.println(outline10715.toString());
        System.out.println("");
        System.out.println("Bob sends to Alice");
        PrintStream printStream16 = System.out;
        StringBuilder outline10716 = GeneratedOutlineSupport1.outline107("B=");
        outline10716.append(createRound2PayloadToSend2.getA().toString(16));
        printStream16.println(outline10716.toString());
        PrintStream printStream17 = System.out;
        StringBuilder outline10717 = GeneratedOutlineSupport1.outline107("KP{x4*s}={");
        outline10717.append(createRound2PayloadToSend2.getKnowledgeProofForX2s()[0].toString(16));
        outline10717.append("},{");
        outline10717.append(createRound2PayloadToSend2.getKnowledgeProofForX2s()[1].toString(16));
        outline10717.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        printStream17.println(outline10717.toString());
        System.out.println("");
        jPAKEParticipant.validateRound2PayloadReceived(createRound2PayloadToSend2);
        System.out.println("Alice checks KP{x4*s}: OK\n");
        jPAKEParticipant2.validateRound2PayloadReceived(createRound2PayloadToSend);
        System.out.println("Bob checks KP{x2*s}: OK\n");
        BigInteger calculateKeyingMaterial = jPAKEParticipant.calculateKeyingMaterial();
        BigInteger calculateKeyingMaterial2 = jPAKEParticipant2.calculateKeyingMaterial();
        System.out.println("********* After round 2 ***********");
        PrintStream printStream18 = System.out;
        StringBuilder outline10718 = GeneratedOutlineSupport1.outline107("Alice computes key material \t K=");
        outline10718.append(calculateKeyingMaterial.toString(16));
        printStream18.println(outline10718.toString());
        PrintStream printStream19 = System.out;
        StringBuilder outline10719 = GeneratedOutlineSupport1.outline107("Bob computes key material \t K=");
        outline10719.append(calculateKeyingMaterial2.toString(16));
        printStream19.println(outline10719.toString());
        System.out.println();
        deriveSessionKey(calculateKeyingMaterial);
        deriveSessionKey(calculateKeyingMaterial2);
        JPAKERound3Payload createRound3PayloadToSend = jPAKEParticipant.createRound3PayloadToSend(calculateKeyingMaterial);
        JPAKERound3Payload createRound3PayloadToSend2 = jPAKEParticipant2.createRound3PayloadToSend(calculateKeyingMaterial2);
        System.out.println("************ Round 3 **************");
        System.out.println("Alice sends to Bob: ");
        PrintStream printStream20 = System.out;
        StringBuilder outline10720 = GeneratedOutlineSupport1.outline107("MacTag=");
        outline10720.append(createRound3PayloadToSend.getMacTag().toString(16));
        printStream20.println(outline10720.toString());
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        PrintStream printStream21 = System.out;
        StringBuilder outline10721 = GeneratedOutlineSupport1.outline107("MacTag=");
        outline10721.append(createRound3PayloadToSend2.getMacTag().toString(16));
        printStream21.println(outline10721.toString());
        System.out.println("");
        jPAKEParticipant.validateRound3PayloadReceived(createRound3PayloadToSend2, calculateKeyingMaterial);
        System.out.println("Alice checks MacTag: OK\n");
        jPAKEParticipant2.validateRound3PayloadReceived(createRound3PayloadToSend, calculateKeyingMaterial2);
        System.out.println("Bob checks MacTag: OK\n");
        System.out.println();
        System.out.println("MacTags validated, therefore the keying material matches.");
    }
}
