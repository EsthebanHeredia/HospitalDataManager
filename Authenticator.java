import java.io.Serializable;

public class Authenticator implements Serializable {
    private static final long serialVersionUID = 1L;
    private final DataHandler dataHandler;

    public Authenticator(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
