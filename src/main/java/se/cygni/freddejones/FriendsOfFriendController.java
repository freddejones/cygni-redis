package se.cygni.freddejones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FriendsOfFriendController {

    @Autowired
    private FriendsOfFriendService service;

    @RequestMapping("/user/{id}")
    public String getName(@PathVariable String id) {
        return service.getNameByUserId(id);
    }

    @RequestMapping("/user/{id}/fof")
    public List<String> getFriendOfFriends(@PathVariable String id) {
        return service.fetchFriendOfFriendsForUserId(id);
    }

}
