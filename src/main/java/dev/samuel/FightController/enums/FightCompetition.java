package dev.samuel.FightController.enums;

public enum FightCompetition {

    // MMA
    UFC("Ultimate Fighting Championship", Modality.MMA),
    BELLATOR("Bellator MMA", Modality.MMA),
    ONE_CHAMPIONSHIP("One Championship", Modality.MMA),
    PFL("Professional Fighters League", Modality.MMA),

    // Boxe
    WBC("World Boxing Council", Modality.BOXING),
    WBA("World Boxing Association", Modality.BOXING),
    IBF("International Boxing Federation", Modality.BOXING),
    WBO("World Boxing Organization", Modality.BOXING),

    // Jiu-Jitsu
    IBJJF("Mundials / Pan Championships", Modality.JIU_JITSU),
    ADCC("ADCC Submission Wrestling", Modality.JIU_JITSU),
    F2W("Fight to Win Pro", Modality.JIU_JITSU),
    WNO("Who's Number One", Modality.JIU_JITSU),

    // Muay Thai
    GLORY("Glory Kickboxing", Modality.MUAY_THAI),
    THAI_FIGHT("Thai Fight League", Modality.MUAY_THAI),
    LUMPINEE("Lumpinee Stadium", Modality.MUAY_THAI),

    // Wrestling
    UWW_WORLDS("UWW World Championships", Modality.WRESTLING),
    OLYMPIC_GAMES("Olympic Games", Modality.WRESTLING),
    NCAA_CHAMPIONSHIPS("NCAA Championships", Modality.WRESTLING),

    // Judô
    IJF_WORLDS("IJF World Judo Championships", Modality.JUDO),
    GRAND_SLAM("IJF Grand Slam", Modality.JUDO),
    GRAND_PRIX("IJF Grand Prix", Modality.JUDO),

    // Kickboxing
    K1_WORLD_GP("K-1 World Grand Prix", Modality.KICKBOXING),
    ENFUSION("Enfusion Live", Modality.KICKBOXING),
    RIZIN_FF("Rizin Fighting Federation", Modality.KICKBOXING),

    // Capoeira
    WORLD_CAPOEIRA("World Capoeira Championship", Modality.CAPOEIRA),
    CBCE_NACIONAL("Campeonato Brasileiro de Capoeira", Modality.CAPOEIRA),
    ENCONTRO_MESTRE("Encontro de Mestres", Modality.CAPOEIRA);

    private final String fullName;
    private final Modality modality;

    FightCompetition(String fullName, Modality modality) {
        this.fullName = fullName;
        this.modality = modality;
    }

    public String getFullName() { return fullName; }
    public Modality getModality() { return modality; }

    public enum Modality {
        MMA, BOXING, JIU_JITSU, MUAY_THAI,
        WRESTLING, JUDO, KICKBOXING, CAPOEIRA
    }

}
