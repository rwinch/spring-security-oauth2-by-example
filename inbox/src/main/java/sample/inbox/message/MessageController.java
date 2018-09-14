package sample.inbox.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author Rob Winch
 */
@Controller
@RequestMapping("/messages")
public class MessageController {
	private final MessageService messages;

	public MessageController(MessageService messages) {
		this.messages = messages;
	}

	@GetMapping("/inbox")
	String inbox(Map<String, Object> model) {
		model.put("messages", this.messages.inbox());
		return "messages/inbox";
	}

	@GetMapping("/{id}")
	String message(@PathVariable String id, Map<String, Object> model) {
		model.put("message", this.messages.findById(id));
		return "messages/view";
	}

	@DeleteMapping("/{id}")
	String deleteById(@PathVariable String id) {
		this.messages.deleteById(id);
		return "redirect:/messages/inbox?deleted";
	}
}
