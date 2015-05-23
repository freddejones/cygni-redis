package se.cygni.freddejones;

public class KeyHandler {

    public static final String FRIENDSHIP_KEY = "user:%s:friendwith";

    public static String createFriendshipKey(String userId) {
        return String.format(FRIENDSHIP_KEY, userId);
    }

}
