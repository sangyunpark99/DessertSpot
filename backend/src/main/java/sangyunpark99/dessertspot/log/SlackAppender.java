package sangyunpark99.dessertspot.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Slf4j
public class SlackAppender extends AppenderBase<ILoggingEvent> {

    private static final String SLACK_WEB_HOOK_URL = System.getenv("SLACK_WEB_HOOK_URL");
    private static final String PROFILE = System.getenv("PROFILE");

    @Override
    protected void append(final ILoggingEvent eventObject) {

        log.info("SLACK_WEB_HOOK_URL : {}", SLACK_WEB_HOOK_URL);

        final RestTemplate restTemplate = new RestTemplate();
        final Map<String, Object> body = createSlackErrorBody(eventObject);

        restTemplate.postForEntity(SLACK_WEB_HOOK_URL, body, String.class);
    }

    private Map<String, Object> createSlackErrorBody(final ILoggingEvent eventObject) {
        final String message = createMessage(eventObject);

        return Map.of(
                "attachments", List.of(
                        Map.of(
                                "fallback", "요청 실패 :cry:",
                                "color", "#2eb886",
                                "pretext", "에러 발생 :cry:",
                                "author_name", "DessertSpot",
                                "text", message,
                                "fields", List.of(
                                        Map.of(
                                                "title", "서버 환경",
                                                "value", PROFILE,
                                                "short", false
                                        )
                                ),
                                "ts", eventObject.getTimeStamp()
                        )
                )
        );
    }

    private String createMessage(final ILoggingEvent eventObject) {
        final String baseMessage = "에러가 발생했습니다.";
        final String pattern = baseMessage + "```%s %s %s [%s] - %s```";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return String.format(pattern,
                simpleDateFormat.format(eventObject.getTimeStamp()),
                eventObject.getLevel(),
                eventObject.getThreadName(),
                eventObject.getLoggerName(),
                eventObject.getFormattedMessage());
    }
}
