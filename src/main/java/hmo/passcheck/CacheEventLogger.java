package hmo.passcheck;

import hmo.passcheck.domain.dto.PassClassDto;
import lombok.extern.log4j.Log4j2;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Log4j2
public class CacheEventLogger implements CacheEventListener<String, PassClassDto> {
    @Override
    public void onEvent(CacheEvent<? extends String, ? extends PassClassDto> cacheEvent) {
        log.info("event [{}], old object [{}], new object [{}]",
                cacheEvent.getType(), cacheEvent.getOldValue(),
                cacheEvent.getNewValue() );
    }
}
