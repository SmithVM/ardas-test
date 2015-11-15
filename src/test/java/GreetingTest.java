import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by Dmitry Natalenko on 15.11.2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Greeting.class)
public class GreetingTest {

    @Mock
    private ResourceBundle bundle;

    private Greeting instance;

    @Test
    public final void testMorningMessage() {

        mockStatic(ResourceBundle.class);
        Locale current = Locale.getDefault();
        expect(ResourceBundle.getBundle("message", current)).andReturn(bundle).times(4);

        final String morningMessage = "Доброе утро, мир!";
        final String dayMessage = "Добрый день, мир!";
        final String eveningMessage = "Добрый вечер, мир!";
        final String nightMessage = "Доброй ночи, мир!";

        expect(bundle.getString("morning")).andReturn(morningMessage).times(4);
        expect(bundle.getString("day")).andReturn(dayMessage).times(4);
        expect(bundle.getString("evening")).andReturn(eveningMessage).times(4);
        expect(bundle.getString("night")).andReturn(nightMessage).times(4);

        replayAll();
        String morning = instance.greeting(8);
        String day = instance.greeting(16);
        String evening = instance.greeting(22);
        String night = instance.greeting(1);
        verifyAll();

        assertEquals(morningMessage, morning);
        assertEquals(dayMessage, day);
        assertEquals(eveningMessage, evening);
        assertEquals(nightMessage, night);
    }

}
