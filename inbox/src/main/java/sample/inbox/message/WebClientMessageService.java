package sample.inbox.message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sample.inbox.user.User;
import sample.inbox.user.UserService;

import java.util.List;

/**
 * @author Rob Winch
 */
@Component
public class WebClientMessageService implements MessageService {
	private final WebClient webClient;

	private final String messagesUrl;

	private final UserService users;

	public WebClientMessageService(WebClient webClient,
			@Value("${messages-url}") String messagesUrl, UserService users) {
		this.webClient = webClient;
		this.messagesUrl = messagesUrl;
		this.users = users;
	}

	@Override
	public List<Message> inbox() {
		return this.webClient.get()
			.uri(this.messagesUrl + "/inbox")
			.retrieve()
			.bodyToFlux(MessageDto.class)
			.flatMap(this::toMessage)
			.collectList()
			.block();
	}

	@Override
	public Message findById(String id) {
		return this.webClient.get()
				.uri(this.messagesUrl + "/{id}", id)
				.retrieve()
				.bodyToMono(MessageDto.class)
				.flatMap(this::toMessage)
				.block();
	}

	@Override
	public void deleteById(String id) {
		this.webClient.delete()
			.uri(this.messagesUrl + "/{id}", id)
			.exchange()
			.block();
	}

	private Mono<Message> toMessage(MessageDto dto) {
		Mono<User> to = this.users.findById(dto.getTo());
		Mono<User> from = this.users.findById(dto.getFrom());
		return Mono.zip(to, from)
			.map(t2 -> new Message(dto.getId(), t2.getT1(), t2.getT2(), dto.getText()));
	}
}
