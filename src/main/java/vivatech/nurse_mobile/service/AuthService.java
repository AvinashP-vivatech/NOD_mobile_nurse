package vivatech.nurse_mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vivatech.nurse_mobile.config.Constants;
import vivatech.nurse_mobile.dto.LoginRequest;
import vivatech.nurse_mobile.dto.Response;

import java.util.Locale;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private NursePartnerService nursePartnerService;

    public ResponseEntity<?> actionLogin(Locale locale, LoginRequest request) {
        if (request.getContact_number() == null || request.getContact_number().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response(
                    Constants.BLANK_DATA_GIVEN,
                    Constants.BLANK_DATA_GIVEN_CODE,
                    messageSource.getMessage(Constants.BLANK_DATA_GIVEN,null,locale)
            ));
        }

        String mobile = request.getContact_number().trim();
        try {
            // Attempt login via the service layer
            Map<String, Object> response = nursePartnerService.attemptLogin(mobile);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(
                    Constants.INTERNAL_SERVER_ERROR_CODE,
                    Constants.INTERNAL_SERVER_ERROR_CODE,
                    "An error occurred while processing the request"
            ));
        }
    }
}
