import view.ViewManager;
import util.DBHandler;

/**
 * PlayerMain class, start a new client and connect him to the server
 * @version 1.0
 * @author Adibov & ArminGh
 */
public class PlayerMain {
    /**
     * main method, makes class runnable
     * @param args program args
     */
    public static void main(String[] args) {
        init();
        ViewManager.main(args);
    }

    /**
     * initialize program
     */
    public static void init() {
        DBHandler.initialize();
    }
}
