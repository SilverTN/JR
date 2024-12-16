package Model;

public enum FileSuffix {
    ENCRYPTED("_encrypted"),
    DECRYPTED("_decrypted");

    private String suffix;

    FileSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
