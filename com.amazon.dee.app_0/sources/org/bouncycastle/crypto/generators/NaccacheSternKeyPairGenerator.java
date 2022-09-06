package org.bouncycastle.crypto.generators;

import amazon.communication.connection.Channels;
import com.amazon.alexa.comms.mediaInsights.service.transport.HttpRetryHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.telnet.TelnetCommand;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes4.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, TelnetCommand.EOR, TelnetCommand.NOP, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, ExifDirectoryBase.TAG_PREDICTOR, 331, 337, ExifDirectoryBase.TAG_JPEG_TABLES, 349, 353, 359, 367, 373, 379, 383, 389, 397, HttpServletResponse.SC_UNAUTHORIZED, HttpServletResponse.SC_CONFLICT, 419, 421, HttpRetryHelper.UHE_HTTP_CODE, 433, 439, 443, 449, 457, 461, Channels.GMD_CHANNEL, 467, 479, Channels.ODOT, 491, 499, 503, 509, 521, 523, SanyoMakernoteDirectory.TAG_LIGHT_SOURCE_SPECIAL, 547, IptcDirectory.TAG_REFERENCE_SERVICE};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger createRandomPrime;
        do {
            createRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (createRandomPrime.bitLength() != i);
        return createRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((i - 1) + (nextInt - i2) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() != 0) {
                vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
            } else {
                return vector2;
            }
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger generatePrime;
        BigInteger add;
        BigInteger generatePrime2;
        BigInteger bigInteger;
        BigInteger add2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        long j;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        boolean z;
        BigInteger bigInteger7;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        BigInteger bigInteger8;
        BigInteger createRandomPrime;
        int i;
        SecureRandom secureRandom;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            PrintStream printStream2 = System.out;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Fetching first ");
            outline107.append(this.param.getCntSmallPrimes());
            outline107.append(" primes.");
            printStream2.println(outline107.toString());
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger9 = ONE;
        BigInteger bigInteger10 = bigInteger9;
        for (int i2 = 0; i2 < permuteList.size() / 2; i2++) {
            bigInteger10 = bigInteger10.multiply((BigInteger) permuteList.elementAt(i2));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger9 = bigInteger9.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger10.multiply(bigInteger9);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger10).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger9).shiftLeft(1);
        long j2 = 0;
        while (true) {
            j2++;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (!add.isProbablePrime(certainty)) {
                bigInteger = shiftLeft2;
                bigInteger2 = shiftLeft;
            } else {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    bigInteger = shiftLeft2;
                    add2 = generatePrime2.multiply(shiftLeft2).add(ONE);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger;
                }
                bigInteger2 = shiftLeft;
                if (!multiply.gcd(generatePrime.multiply(generatePrime2)).equals(ONE)) {
                    continue;
                } else if (add.multiply(add2).bitLength() >= strength) {
                    break;
                } else if (isDebug) {
                    PrintStream printStream3 = System.out;
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("key size too small. Should be ", strength, " but is actually ");
                    outline109.append(add.multiply(add2).bitLength());
                    printStream3.println(outline109.toString());
                }
            }
            shiftLeft2 = bigInteger;
            shiftLeft = bigInteger2;
        }
        if (isDebug) {
            PrintStream printStream4 = System.out;
            bigInteger3 = generatePrime4;
            printStream4.println("needed " + j2 + " tries to generate p and q.");
        } else {
            bigInteger3 = generatePrime4;
        }
        BigInteger multiply2 = add.multiply(add2);
        BigInteger multiply3 = add.subtract(ONE).multiply(add2.subtract(ONE));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j3 = 0;
        while (true) {
            Vector vector = new Vector();
            int i3 = 0;
            bigInteger4 = add2;
            j = j3;
            while (i3 != permuteList.size()) {
                BigInteger divide = multiply3.divide((BigInteger) permuteList.elementAt(i3));
                while (true) {
                    j++;
                    bigInteger8 = add;
                    createRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    i = strength;
                    secureRandom = random;
                    if (createRandomPrime.modPow(divide, multiply2).equals(ONE)) {
                        add = bigInteger8;
                        strength = i;
                        random = secureRandom;
                    }
                }
                vector.addElement(createRandomPrime);
                i3++;
                add = bigInteger8;
                strength = i;
                random = secureRandom;
            }
            int i4 = strength;
            SecureRandom secureRandom2 = random;
            bigInteger5 = add;
            bigInteger6 = ONE;
            for (int i5 = 0; i5 < permuteList.size(); i5++) {
                bigInteger6 = bigInteger6.multiply(((BigInteger) vector.elementAt(i5)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i5)), multiply2)).mod(multiply2);
            }
            int i6 = 0;
            while (true) {
                if (i6 >= permuteList.size()) {
                    z = false;
                    break;
                } else if (bigInteger6.modPow(multiply3.divide((BigInteger) permuteList.elementAt(i6)), multiply2).equals(ONE)) {
                    if (isDebug) {
                        PrintStream printStream5 = System.out;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("g has order phi(n)/");
                        outline1072.append(permuteList.elementAt(i6));
                        outline1072.append("\n g: ");
                        outline1072.append(bigInteger6);
                        printStream5.println(outline1072.toString());
                    }
                    z = true;
                } else {
                    i6++;
                }
            }
            if (!z) {
                if (!bigInteger6.modPow(multiply3.divide(BigInteger.valueOf(4L)), multiply2).equals(ONE)) {
                    if (!bigInteger6.modPow(multiply3.divide(generatePrime), multiply2).equals(ONE)) {
                        if (!bigInteger6.modPow(multiply3.divide(generatePrime2), multiply2).equals(ONE)) {
                            if (!bigInteger6.modPow(multiply3.divide(generatePrime3), multiply2).equals(ONE)) {
                                bigInteger7 = bigInteger3;
                                if (!bigInteger6.modPow(multiply3.divide(bigInteger7), multiply2).equals(ONE)) {
                                    break;
                                } else if (isDebug) {
                                    PrintStream printStream6 = System.out;
                                    printStream6.println("g has order phi(n)/b\n g: " + bigInteger6);
                                }
                            } else if (isDebug) {
                                printStream = System.out;
                                sb = new StringBuilder();
                                str = "g has order phi(n)/a\n g: ";
                                sb.append(str);
                                sb.append(bigInteger6);
                                printStream.println(sb.toString());
                            }
                        } else if (isDebug) {
                            printStream = System.out;
                            sb = new StringBuilder();
                            str = "g has order phi(n)/q'\n g: ";
                            sb.append(str);
                            sb.append(bigInteger6);
                            printStream.println(sb.toString());
                        }
                    } else if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                        sb.append(str);
                        sb.append(bigInteger6);
                        printStream.println(sb.toString());
                    }
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/4\n g:";
                    sb.append(str);
                    sb.append(bigInteger6);
                    printStream.println(sb.toString());
                }
                bigInteger3 = bigInteger7;
                j3 = j;
                add2 = bigInteger4;
                add = bigInteger5;
                strength = i4;
                random = secureRandom2;
            }
            bigInteger7 = bigInteger3;
            bigInteger3 = bigInteger7;
            j3 = j;
            add2 = bigInteger4;
            add = bigInteger5;
            strength = i4;
            random = secureRandom2;
        }
        if (isDebug) {
            PrintStream printStream7 = System.out;
            printStream7.println("needed " + j + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream8 = System.out;
            printStream8.println("smallPrimes: " + permuteList);
            PrintStream printStream9 = System.out;
            printStream9.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream10 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("a:.......... ");
            sb2.append(generatePrime3);
            printStream10.println(sb2.toString());
            PrintStream printStream11 = System.out;
            printStream11.println("b:.......... " + bigInteger7);
            PrintStream printStream12 = System.out;
            printStream12.println("p':......... " + generatePrime);
            PrintStream printStream13 = System.out;
            printStream13.println("q':......... " + generatePrime2);
            PrintStream printStream14 = System.out;
            printStream14.println("p:.......... " + bigInteger5);
            PrintStream printStream15 = System.out;
            printStream15.println("q:.......... " + bigInteger4);
            PrintStream printStream16 = System.out;
            printStream16.println("n:.......... " + multiply2);
            PrintStream printStream17 = System.out;
            printStream17.println("phi(n):..... " + multiply3);
            PrintStream printStream18 = System.out;
            printStream18.println("g:.......... " + bigInteger6);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger6, multiply2, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger6, multiply2, multiply.bitLength(), permuteList, multiply3));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
