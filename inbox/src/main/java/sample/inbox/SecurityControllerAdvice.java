package sample.inbox;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

/**
 * @author Rob Winch
 */
@ControllerAdvice
public class SecurityControllerAdvice {
	@ModelAttribute("currentUser")
	Principal currentUser(Principal principal) {
		return principal;
	}
}
