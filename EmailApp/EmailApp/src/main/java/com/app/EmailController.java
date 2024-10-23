package com.app;

import java.util.List;

import org.elasticsearch.index.engine.DocumentMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	private static final User username = null;
	@Autowired
	private EmailService emailService;

	@RequestMapping("/app")
	public String app() {
		return "Hello App Email!!";

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
	    emailService.registerUser(username, password);
	    return ResponseEntity.ok("User registered successfully");
	}


	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
	    User user = emailService.loginUser(username, password);
	    if (user != null) {
	        return ResponseEntity.ok("Welcome, " + user.getUsername());
	    } else {
	        // Create a response with 401 status and WWW-Authenticate header
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .header("WWW-Authenticate", "Basic realm=\"Access to the staging site\"")
	                             .body("Invalid username or password");
	    }
	}



	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestParam String senderUsername, 
	                                          @RequestParam String recipientUsername, 
	                                          @RequestParam(required = false) String content) {
	    // Validate input parameters
	    if (senderUsername == null || recipientUsername == null || content == null) {
	        return ResponseEntity.badRequest().body("All fields must be provided.");
	    }

	    try {
	        User sender = emailService.loginUser(senderUsername, null); // Replace with actual login logic
	        if (sender != username) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body("Sender not authenticated.");
	        }

	        // Send the message
	        emailService.sendMessage(sender, recipientUsername, content);
	        return ResponseEntity.ok("Message sent successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to send message: " + e.getMessage());
	    }
	}



	@GetMapping("/unread")
	public ResponseEntity<List<Message>> getUnreadMessages(@RequestParam String username) {
	    if (username == null || username.isEmpty()) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    User user = emailService.loginUser(username, null);
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body(null);
	    }

	    try {
	        List<Message> messages = emailService.getUnreadMessages(user);
	        return ResponseEntity.ok(messages);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(null);
	    }
	}

}
