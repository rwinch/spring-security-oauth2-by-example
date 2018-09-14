package sample.inbox.message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Rob Winch
 */
public interface MessageService {
	List<Message> inbox();
	Message findById(String id);
	void deleteById(String id);
}
