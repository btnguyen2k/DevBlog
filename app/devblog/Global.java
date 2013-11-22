package devblog;

import play.Application;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;
import play.filters.gzip.GzipFilter;

import com.github.ddth.plommon.bo.BaseDao;

public class Global extends GlobalSettings {

    @SuppressWarnings("unchecked")
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[] { GzipFilter.class };
    }

    @Override
    public void onStart(Application app) {
        BaseDao.init();
        super.onStart(app);

        // AkkaUtils.schedule(FeedDbTableActor.class, 10, TimeUnit.SECONDS, 300,
        // TimeUnit.SECONDS);
        //
        // AkkaUtils
        // .schedule(UpdateActivePagesActor.class, 10, TimeUnit.SECONDS, 10,
        // TimeUnit.SECONDS);
        //
        // AkkaUtils
        // .schedule(UpdateActiveFeedsActor.class, 10, TimeUnit.SECONDS, 10,
        // TimeUnit.SECONDS);
        //
        // AkkaUtils.schedule(UpdateFeedStatsActor.class, 10, TimeUnit.SECONDS,
        // 10, TimeUnit.SECONDS);
    }

    @Override
    public void onStop(Application app) {
        super.onStop(app);
    }

}
