package acntech.boundary;

import acntech.domain.Bar;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping(value = "/api/foo")
@RestController
public class Foo {

    private static Bar BAR = new Bar("dummy");

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public Bar get() {
        return BAR;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    @ResponseBody
    public Bar post(@RequestBody @Valid Bar b) {
        BAR = b;
        return BAR;
    }
}
