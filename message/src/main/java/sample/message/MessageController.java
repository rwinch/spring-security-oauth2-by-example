package sample.message;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rob Winch
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
	private final MessageRepository messages;

	public MessageController(MessageRepository messages) {
		this.messages = messages;
	}

	@GetMapping("/inbox")
	List<Message> inbox(@AuthenticationPrincipal(expression = "claims['user_id']") String currentUserId) {
		return this.messages.findByTo(currentUserId);
	}

	@GetMapping("/{id}")
	Message findById(@PathVariable Long id) {
		return this.messages.findById(id);
	}

	@DeleteMapping("/{id}")
	void deleteById(@PathVariable Long id) {
		this.messages.deleteById(id);
	}
}
