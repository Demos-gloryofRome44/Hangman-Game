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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Topic fromValue(int value) {
        for (Topic topic : Topic.values()) {
            if (topic.getValue() == value) {
                return topic;
            }
        }
        throw new IllegalArgumentException("Нет темы с таким значением: " + value);
    }

}
