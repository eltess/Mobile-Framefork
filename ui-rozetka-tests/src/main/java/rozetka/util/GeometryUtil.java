package rozetka.util;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Point;
import rozetka.enums.RelativePosition;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static rozetka.BasePage.PLATFORM;
import static rozetka.enums.RelativePosition.*;

@SuppressWarnings("PMD")
public class GeometryUtil {

    private static final int FIRST_HORIZONTAL_BORDER;
    private static final int SECOND_HORIZONTAL_BORDER;
    private static final int FIRST_VERTICAL_BORDER;
    private static final int SECOND_VERTICAL_BORDER;

    static {
        int screenWidth = WebDriverRunner.getWebDriver().manage().window().getSize().getWidth();
        int screenHeight = WebDriverRunner.getWebDriver().manage().window().getSize().getHeight();
        FIRST_HORIZONTAL_BORDER = screenWidth / 3;
        SECOND_HORIZONTAL_BORDER = FIRST_HORIZONTAL_BORDER * 2;
        FIRST_VERTICAL_BORDER = screenHeight / 3;
        SECOND_VERTICAL_BORDER = FIRST_VERTICAL_BORDER * 2;
    }

    public static RelativePosition getHorizontalRelativePositionForPoint(Point point) {
        int pointX = point.getX();
        if (pointX < FIRST_HORIZONTAL_BORDER) {
            return LEFT;
        } else if (pointX < SECOND_HORIZONTAL_BORDER) {
            return CENTER;
        } else {
            return RIGHT;
        }
    }

    public static RelativePosition getVerticalRelativePositionForPoint(Point point) {
        int pointY = point.getY();
        if (pointY < FIRST_VERTICAL_BORDER) {
            return TOP;
        } else if (pointY < SECOND_VERTICAL_BORDER) {
            return CENTER;
        } else {
            return BOTTOM;
        }
    }

    public static RelativePosition getRelativePositionSecondElementAccordingToFirstOne(SelenideElement firstElement,
                                                                                       SelenideElement secondElement) {
        double x1 = getElementCenter(firstElement).getX();
        double x2 = getElementCenter(secondElement).getX();
        if (x1 < x2) {
            return RIGHT;
        } else if (x2 < x1) {
            return LEFT;
        } else {
            return CENTER;
        }
    }

    public static RelativePosition getHorizontalRelativePositionForElement(SelenideElement element) {
        return getHorizontalRelativePositionForPoint(getElementCenter(element));
    }

    public static RelativePosition getVerticalRelativePositionForElement(SelenideElement element) {
        return getVerticalRelativePositionForPoint(getElementCenter(element));
    }

    public static Point getElementCenter(SelenideElement element) {
        return new Point(element.getLocation().getX() + element.getSize().getWidth() / 2,
            element.getLocation().getY() + element.getSize().getHeight() / 2);
    }

    public static RelativePosition getHorizontalTextPosition(String text) {
        SelenideElement element = switch (PLATFORM) {
            case ANDROID -> $x(String.format("//*[@text='%s']", text));
            case IOS -> $$x(String.format("//*[@label='%s']", text)).last();
        };
        return getHorizontalRelativePositionForElement(element);
    }

    @SuppressWarnings("PMD")
    public static boolean isElementHoveredBy(SelenideElement element1, SelenideElement element2) {
        int minX = Math.min(element1.getLocation().getX(), element2.getLocation().getX());
        int maxX = Math.max(element1.getLocation().getX(), element2.getLocation().getX());
        int minY = Math.min(element1.getLocation().getY(), element2.getLocation().getY());
        int maxY = Math.max(element1.getLocation().getY(), element2.getLocation().getY());

        return (minX <= maxX && maxX < minX + getElementWithXCoordinate(element1, element2, minX).getSize().getWidth())
            && (minY <= maxY
                && maxY < minY + getElementWithYCoordinate(element1, element2, minY).getSize().getHeight());
    }

    private static SelenideElement getElementWithXCoordinate(SelenideElement element1, SelenideElement element2,
                                                             int x) {
        if (element1.getLocation().getX() == x) {
            return element1;
        } else if (element2.getLocation().getX() == x) {
            return element2;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static SelenideElement getElementWithYCoordinate(SelenideElement element1, SelenideElement element2,
                                                             int y) {
        if (element1.getLocation().getY() == y) {
            return element1;
        } else if (element2.getLocation().getY() == y) {
            return element2;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
