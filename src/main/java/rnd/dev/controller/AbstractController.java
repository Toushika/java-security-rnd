package rnd.dev.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import rnd.dev.constant.UrlConstants;

@RequestMapping(value = UrlConstants.SECURITY_BASE_PATH,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class AbstractController {
}
