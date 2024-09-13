package backend.academy;

import java.io.PrintStream;

public class StateGallows {
    private static final String STAND = " ========\n";
    private static final String ROOF = "+—————+\n";
    private static final String LINE = "  |\n";
    private static final String LINE2 = "  |   |\n";
    private static final String HEAD = "  |   o \n";
    private static final String BODY = "  |  /o\\ \n";
    private static final String LENG = "  |  / \\ \n";
    private static final String CHAIR = "  |  ===\n";
    private static final String[] GALLOW_STATE = {
        "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + STAND,
        LINE + LINE + LINE + LINE + LINE + LINE + STAND,
        ROOF + LINE + LINE + LINE + LINE + LINE + STAND,
        ROOF + LINE2 + LINE + LINE + LINE + LINE + CHAIR + STAND,
        ROOF + LINE2 + HEAD + BODY + LENG + CHAIR + STAND,
        ROOF + LINE2 + HEAD + BODY + LENG + LINE + STAND
    };

    public void print_state(int error, PrintStream out){
        out.print(GALLOW_STATE[error]);
    }
}
