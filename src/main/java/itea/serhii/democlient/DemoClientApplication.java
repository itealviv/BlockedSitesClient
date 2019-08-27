package itea.serhii.democlient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import itea.serhii.democlient.service.SiteService;

@EnableRetry
@EnableCaching
@EnableHystrix
@SpringBootApplication
public class DemoClientApplication {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(DemoClientApplication.class, args);
        SiteService siteService = ctx.getBean(SiteService.class);

        for (int i = 0; i < 3; ++i) {
            System.out.println("Blocked urls:");
            siteService.findAllBlockedSites().forEach(site -> System.out.println(site.getUrl()));

            Thread.sleep(5000);
        }
    }
}

