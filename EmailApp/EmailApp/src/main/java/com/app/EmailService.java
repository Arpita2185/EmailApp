package com.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Consider hashing the password
        userRepository.save(user);
        
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void sendMessage(User sender, String recipientUsername, String content) {
        User recipient = userRepository.findByUsername(recipientUsername);
        if (recipient != null) {
            Message message = new Message();
            message.setSender(sender.getUsername());
            message.setContent(content);
            message.setRecipient(recipient);
            message.setIsRead(false);
            messageRepository.save(message);
        }
    }

    public List<Message> getUnreadMessages(User user) {
        return messageRepository.findByRecipient(user);
    }
}

