package se.cygni.freddejones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import static se.cygni.freddejones.KeyHandler.createFriendshipKey;

@Component
public class BootstrapFriendNetwork {

    @Autowired
    private StringRedisTemplate template;

    public void initialize() {
        template.getConnectionFactory().getConnection().flushAll();
        addUsers();
        setupFriendRelations();
    }

    private void addUsers() {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set("1", "robert");
        ops.set("2", "patrik");
        ops.set("3", "tomas");
        ops.set("4", "lena");
        ops.set("5", "per");
        ops.set("6", "anders");
        ops.set("7", "lisa");
        ops.set("8", "otto");
    }

    private void setupFriendRelations() {
        // robert is friend with patrik and lena
        setupFriendship(createFriendshipKey("1"), "2", "4");

        // patrik is friend with robert, per, anders and tomas
        setupFriendship(createFriendshipKey("2"),"1","5", "6", "3");

        // tomas is friend with patrik and lena
        setupFriendship(createFriendshipKey("3"), "2","4");

        // lena is friend with tomas, lisa and otto
        setupFriendship(createFriendshipKey("4"), "1","3","7","8");

        // per is friend with patrik
        setupFriendship(createFriendshipKey("5"), "2");

        // anders is friend with patrik
        setupFriendship(createFriendshipKey("6"), "2");

        // lisa is friend with lena
        setupFriendship(createFriendshipKey("7"), "4");

        // otto is friend with lena
        setupFriendship(createFriendshipKey("8"), "4");
    }

    private void setupFriendship(String fromKey, String... friendIdKeys) {
        for (String friendIdKey : friendIdKeys) {
            this.template.boundSetOps(fromKey).add(friendIdKey);
        }
    }

}
