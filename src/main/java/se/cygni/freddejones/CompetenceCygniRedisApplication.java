package se.cygni.freddejones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompetenceCygniRedisApplication  implements CommandLineRunner {

    @Autowired
    private BootstrapFriendNetwork bootstrapFriendNetwork;

    @Override
    public void run(String... args) throws Exception {
        bootstrapFriendNetwork.initialize();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CompetenceCygniRedisApplication.class, args);
    }
}
