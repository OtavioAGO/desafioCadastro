package enums;

public enum Tipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String type;

    Tipo(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
