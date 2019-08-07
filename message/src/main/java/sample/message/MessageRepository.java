package sample.message;

import org.springframework.data.repository.Repository;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.List;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends Repository<Message,Long> {
	@PostAuthorize("returnObject?.to == principal?.claims['user_id']")
	Message findById(Long id);

	List<Message> findByTo(String id);

	void deleteById(Long id);

	void save(Message message);
}
