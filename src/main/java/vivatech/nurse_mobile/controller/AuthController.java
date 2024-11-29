package vivatech.nurse_mobile.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/mobile/nurse")
public class AuthController {
    @PostMapping(path = "/pt-online-nurses", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> ptOnlineNurses(@RequestHeader(name = "X-localization", required = false,defaultValue = "so") Locale locale,
                                            @ModelAttribute LogsNurseNotFoundRequest request) {
        return nursePartnerService.ptOnlineNurses(locale, request.getUser_id());
    }
}
