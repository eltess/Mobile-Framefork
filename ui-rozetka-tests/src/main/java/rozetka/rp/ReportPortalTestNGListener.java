package rozetka.rp;

import com.codeborne.selenide.Screenshots;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.util.Strings;
import rozetka.config.RPConfiguration;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.function.Supplier;

import static rozetka.BasePage.UI_CONFIGURATION;

public  class ReportPortalTestNGListener extends BaseTestNGListener  {

    private static final RPConfiguration RP_CONFIGURATION = ConfigFactory.create(RPConfiguration.class);

    public ReportPortalTestNGListener() {
        super(new ParamOverrideTestNgService());
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        try {
            ReportPortal.emitLog("Screenshot: " + testResult.getThrowable().getMessage(), "error", new Date(),
                    Screenshots.takeScreenShotAsFile());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            super.onTestFailure(testResult);
        }
    }

    public static class ParamOverrideTestNgService extends TestNGService {

        public ParamOverrideTestNgService() {
            super(getLaunchOverriddenProperties());
        }

        private static Supplier<Launch> getLaunchOverriddenProperties() {
            ReportPortal reportPortal = ReportPortal.builder().withParameters(listenerParameters()).build();
            StartLaunchRQ rq = buildStartLaunch(reportPortal.getParameters());
            return new Supplier<Launch>() {
                @Override
                public Launch get() {
                    return reportPortal.newLaunch(rq);
                }
            };
        }

        private static ListenerParameters listenerParameters() {
            ListenerParameters parameters = new ListenerParameters(PropertiesLoader.load());
            parameters.setApiKey(RP_CONFIGURATION.apiKey());
            parameters.setAttributes(Set.of(
                new ItemAttributesRQ("platform", UI_CONFIGURATION.platformName()),
                new ItemAttributesRQ("language", UI_CONFIGURATION.language())));
            return parameters;
        }

        private static StartLaunchRQ buildStartLaunch(ListenerParameters parameters) {
            StartLaunchRQ rq = new StartLaunchRQ();
            rq.setName(parameters.getLaunchName());
            rq.setStartTime(Calendar.getInstance().getTime());
            rq.setAttributes(parameters.getAttributes());
            rq.setMode(parameters.getLaunchRunningMode());
            if (!Strings.isNullOrEmpty(parameters.getDescription())) {
                rq.setDescription(parameters.getDescription());
            }
            return rq;
        }
    }
}
