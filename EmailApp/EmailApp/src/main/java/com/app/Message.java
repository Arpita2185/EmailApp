package com.app;
import javax.persistence.*;

	@Entity
	public class Message {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String sender;
	    private String content;
	    private boolean isRead;

	    @ManyToOne
	    @JoinColumn(name = "recipient_id")
	    private User recipient;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public boolean isRead() {
			return isRead;
		}

		public void setRead(boolean isRead) {
			this.isRead = isRead;
		}

		public User getRecipient() {
			return recipient;
		}

		public void setRecipient(User recipient) {
			this.recipient = recipient;
		}

		

		public void setIsRead(boolean b) {
			// TODO Auto-generated method stub
			
		}

	
	}

