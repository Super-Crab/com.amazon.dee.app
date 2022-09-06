package com.here.sdk.core;

import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.facebook.imageutils.JfifUtil;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.telnet.TelnetCommand;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes3.dex */
public enum CountryCode {
    ABW(0),
    AFG(1),
    AGO(2),
    AIA(3),
    ALA(4),
    ALB(5),
    AND(6),
    ARE(7),
    ARG(8),
    ARM(9),
    ASM(10),
    ATA(11),
    ATF(12),
    ATG(13),
    AUS(14),
    AUT(15),
    AZE(16),
    BDI(17),
    BEL(18),
    BEN(19),
    BES(20),
    BFA(21),
    BGD(22),
    BGR(23),
    BHR(24),
    BHS(25),
    BIH(26),
    BLM(27),
    BLR(28),
    BLZ(29),
    BMU(30),
    BOL(31),
    BRA(32),
    BRB(33),
    BRN(34),
    BTN(35),
    BVT(36),
    BWA(37),
    CAF(38),
    CAN(39),
    CCK(40),
    CHE(41),
    CHL(42),
    CHN(43),
    CIV(44),
    CMR(45),
    COD(46),
    COG(47),
    COK(48),
    COL(49),
    COM(50),
    CPV(51),
    CRI(52),
    CUB(53),
    CUW(54),
    CXR(55),
    CYM(56),
    CYP(57),
    CZE(58),
    DEU(59),
    DJI(60),
    DMA(61),
    DNK(62),
    DOM(63),
    DZA(64),
    ECU(65),
    EGY(66),
    ERI(67),
    ESH(68),
    ESP(69),
    EST(70),
    ETH(71),
    FIN(72),
    FJI(73),
    FLK(74),
    FRA(75),
    FRO(76),
    FSM(77),
    GAB(78),
    GBR(79),
    GEO(80),
    GGY(81),
    GHA(82),
    GIB(83),
    GIN(84),
    GLP(85),
    GMB(86),
    GNB(87),
    GNQ(88),
    GRC(89),
    GRD(90),
    GRL(91),
    GTM(92),
    GUF(93),
    GUM(94),
    GUY(95),
    HKG(96),
    HMD(97),
    HND(98),
    HRV(99),
    HTI(100),
    HUN(101),
    IDN(102),
    IMN(103),
    IND(104),
    IOT(105),
    IRL(106),
    IRN(107),
    IRQ(108),
    ISL(109),
    ISR(110),
    ITA(111),
    JAM(112),
    JEY(113),
    JOR(114),
    JPN(115),
    KAZ(116),
    KEN(117),
    KGZ(118),
    KHM(119),
    KIR(120),
    KNA(121),
    KOR(122),
    KWT(123),
    LAO(124),
    LBN(125),
    LBR(126),
    LBY(127),
    LCA(128),
    LIE(129),
    LKA(130),
    LSO(131),
    LTU(132),
    LUX(133),
    LVA(134),
    MAC(135),
    MAF(136),
    MAR(137),
    MCO(138),
    MDA(139),
    MDG(140),
    MDV(141),
    MEX(142),
    MHL(143),
    MKD(144),
    MLI(145),
    MLT(146),
    MMR(147),
    MNE(148),
    MNG(149),
    MNP(150),
    MOZ(151),
    MRT(152),
    MSR(153),
    MTQ(154),
    MUS(155),
    MWI(156),
    MYS(157),
    MYT(158),
    NAM(159),
    NCL(160),
    NER(161),
    NFK(162),
    NGA(163),
    NIC(164),
    NIU(165),
    NLD(166),
    NOR(167),
    NPL(168),
    NRU(169),
    NZL(170),
    OMN(171),
    PAK(172),
    PAN(173),
    PCN(174),
    PER(175),
    PHL(176),
    PLW(177),
    PNG(178),
    POL(179),
    PRI(180),
    PRK(181),
    PRT(182),
    PRY(183),
    PSE(184),
    PYF(185),
    QAT(186),
    REU(187),
    ROU(188),
    RUS(189),
    RWA(190),
    SAU(191),
    SDN(192),
    SEN(193),
    SGP(194),
    SGS(195),
    SHN(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256),
    SJM(197),
    SLB(Opcodes.IFNULL),
    SLE(199),
    SLV(200),
    SMR(201),
    SOM(202),
    SPM(203),
    SRB(204),
    SSD(205),
    STP(HttpServletResponse.SC_PARTIAL_CONTENT),
    SUR(207),
    SVK(208),
    SVN(209),
    SWE(210),
    SWZ(211),
    SXM(212),
    SYC(213),
    SYR(214),
    TCA(215),
    TCD(JfifUtil.MARKER_SOI),
    TGO(JfifUtil.MARKER_EOI),
    THA(JfifUtil.MARKER_SOS),
    TJK(219),
    TKL(220),
    TKM(221),
    TLS(222),
    TON(223),
    TTO(224),
    TUN(225),
    TUR(226),
    TUV(227),
    TWN(228),
    TZA(229),
    UGA(230),
    UKR(231),
    UMI(232),
    URY(233),
    USA(234),
    UZB(235),
    VAT(TelnetCommand.EOF),
    VCT(TelnetCommand.SUSP),
    VEN(TelnetCommand.ABORT),
    VGB(TelnetCommand.EOR),
    VIR(240),
    VNM(TelnetCommand.NOP),
    VUT(242),
    WLF(TelnetCommand.BREAK),
    WSM(TelnetCommand.IP),
    YEM(TelnetCommand.AO),
    ZAF(TelnetCommand.AYT),
    ZMB(TelnetCommand.EC),
    ZWE(TelnetCommand.EL);
    
    public final int value;

    CountryCode(int i) {
        this.value = i;
    }
}