package backend.academy;

public enum Topic {
    ANIMAL(1, "Animal"),
    TOWN(2, "Town"),
    PERSON(3, "Person"),
    MIX(4, "Mix");

    private final int value;
    private final String name;

    Topic(int value, String name) {
        this.value = value;
        this.name = name;
    }


}
