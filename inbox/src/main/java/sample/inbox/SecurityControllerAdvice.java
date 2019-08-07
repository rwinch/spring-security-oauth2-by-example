package sample.inbox;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import sample.inbox.user.User;

import java.security.Principal;

/**
 * @author Rob Winch
 */
@ControllerAdvice
public class SecurityControllerAdvice {
	@ModelAttribute("currentUser")
	User currentUser(@AuthenticationPrincipal User principal) {
		return principal;
	}
}
