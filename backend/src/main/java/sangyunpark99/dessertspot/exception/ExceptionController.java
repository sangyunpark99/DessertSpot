package sangyunpark99.dessertspot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExceptionController {

    @GetMapping("/hello")
    public void error(){
        log.info("에러 발생");
        throw new IllegalStateException("에러 테스트 입니다.");
    }
}
