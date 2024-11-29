package vivatech.nurse_mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vivatech.nurse_mobile.service.AuthService;
import vivatech.nurse_mobile.dto.LoginRequest;

import java.util.Locale;

@RestController
@RequestMapping("/mobile/nurse")
public class AuthControllerJson {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> actionLogin(@RequestHeader(name = "X-localization", required = false,defaultValue = "so") Locale locale,
                                            @RequestBody LoginRequest request) {
        return authService.actionLogin(locale, request);
    }
}
