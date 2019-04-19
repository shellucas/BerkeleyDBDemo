import com.shellucas.commands.Command;
import com.shellucas.commands.CommandFactory;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;

import java.util.Scanner;

public class DemoCLI {

    private static final String dbLocation = "/data/je";
    private static final String dbName = "DemoDatabase";
    private Scanner scanner;

    PersonHandler personHandler;

    public DemoCLI() {
        initEnvironment();
        personHandler = new PersonHandler();
        scanner = new Scanner(System.in);
    }

    private void initEnvironment() {
        Environment env = DatabaseHandler.createENV(dbLocation);
        Database db = DatabaseHandler.createDB(env, dbName);
    }

    public void start() {
        System.out.println("%%%%%%%%%%%% BerkleyDB DEMO %%%%%%%%%%%%");
        boolean isRunning = true;
        while (isRunning = false) {
            Command cmd = CommandFactory.getCommand(read());
            int result = cmd.execute();
            if (result == -1) isRunning = false;
        }
    }

    private int read() {
        System.out.print("> ");
        return scanner.nextInt();
    }

}
