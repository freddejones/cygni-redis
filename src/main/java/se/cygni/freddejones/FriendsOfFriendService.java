package se.cygni.freddejones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static se.cygni.freddejones.KeyHandler.createFriendshipKey;

@Component
public class FriendsOfFriendService {

    @Autowired
    private StringRedisTemplate template;

    public List<String> fetchFriendOfFriendsForUserId(String userId) {
        return friendOfFriends(userId);
    }

    public String getNameByUserId(String key) {
        return this.template.boundValueOps(key).get();
    }

    private List<String> friendOfFriends(String userId) {
        Set<Set<String>> friendsOfFriends = getFriendsFriends(userId);

        Set<String> allFriendOfFriends = new HashSet<>();
        for (Set<String> membersFriends : friendsOfFriends) {
            allFriendOfFriends.addAll(membersFriends);
        }

        return allFriendOfFriends.stream().map(this::getNameByUserId).collect(Collectors.toList());
    }

    private Set<Set<String>> getFriendsFriends(String userId) {
        String friendshipKey = createFriendshipKey(userId);
        Set<Set<String>> membersFriends = new HashSet<>();
        for (String userIdForFriend : getFriendsForKey(friendshipKey)) {
            Set<String> friends = getFriendsForKey(createFriendshipKey(userIdForFriend));
            friends.remove(userId);
            membersFriends.add(friends);
        }
        return membersFriends;
    }

    private Set<String> getFriendsForKey(String key) {
        return this.template.boundSetOps(key).members();
    }
}
