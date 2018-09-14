package sample.message;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends Repository<Message,Long> {
	Message findById(Long id);

	List<Message> findByTo(String id);

	void deleteById(Long id);

	void save(Message message);
}
