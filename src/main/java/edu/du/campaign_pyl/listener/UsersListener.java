package edu.du.campaign_pyl.listener;

import edu.du.campaign_pyl.entity.Users;
import edu.du.campaign_pyl.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsersListener {

    private final UserRepository usersRepository;

    public UsersListener(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RabbitListener(queues = "users.queue")
    public void receiveUser(Users users) {
        usersRepository.save(users);
    }
}
