package enums;

public enum Sexo {
    MACHO('M', "Macho"),
    FEMEA('F', "Fêmea");

    private final char ABREV;
    private final String COMPL;

    Sexo(char ABREV, String COMPL) {
        this.ABREV = ABREV;
        this.COMPL = COMPL;
    }

    public char getABREV() {
        return ABREV;
    }

    public String getCOMPL() {
        return COMPL;
    }
}
