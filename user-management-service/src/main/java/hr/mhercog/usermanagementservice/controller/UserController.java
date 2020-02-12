package hr.mhercog.usermanagementservice.controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/service")
public class UserController {

	private ObjectMapper mapper = new ObjectMapper();

	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(OAuth2Authentication authentication) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
		Set<String> roles = authentication.getUserAuthentication().getAuthorities().stream().map(a -> a.getAuthority())
				.collect(Collectors.toSet());
		details.put("roles", roles);
		return ResponseEntity.ok(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(details));
	}
}
