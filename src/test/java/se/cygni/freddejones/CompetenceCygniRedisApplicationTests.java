package se.cygni.freddejones;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CompetenceCygniRedisApplication.class)
public class CompetenceCygniRedisApplicationTests {

	@Autowired
	private FriendsOfFriendController controller;

	@Test
	public void should_have_bootstrapped_user() {
		String name = controller.getName("1");

		assertThat(name, is("robert"));
	}

	@Test
	public void should_return_friends_of_friends() {
		List<String> friendOfFriends = controller.getFriendOfFriends("1");

		assertThat(friendOfFriends.size(), is(5));
		assertThat(friendOfFriends.contains("tomas"), is(true));
		assertThat(friendOfFriends.contains("otto"), is(true));
		assertThat(friendOfFriends.contains("per"), is(true));
		assertThat(friendOfFriends.contains("anders"), is(true));
		assertThat(friendOfFriends.contains("lisa"), is(true));
	}
}
