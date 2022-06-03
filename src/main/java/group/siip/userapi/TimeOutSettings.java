package group.siip.userapi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Setter
@Getter
@Builder
public class TimeOutSettings {

    private Duration defaultTimeOut;
}
