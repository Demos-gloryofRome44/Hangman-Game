package backend.academy;

public class State_gallows {
    private String[] gallows = {
        "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "  ========\n",
        "  |\n" +
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  ========\n",

        "  +—————+\n" +
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  ========\n",

        "  +—————+\n" +
            "  |   |\n"+
            "  |\n"+
            "  |\n"+
            "  |\n"+
            "  |  ===\n"+
            "  ========\n",

        "  +—————+\n" +
            "  |   |\n"+
            "  |   o\n"+
            "  |  /o\\ \n"+
            "  |   |\n"+
            "  |  ===\n"+
            "  ========\n",

        "  +—————+\n" +
            "  |   |\n"+
            "  |   o\n"+
            "  |  /o\\ \n"+
            "  |   |\n"+
            "  | \n"+
            "  ========\n"
    };

    public void print_state(int error){
        System.out.print(gallows[error]);
    }
}
