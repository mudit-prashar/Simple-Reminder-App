<<<<<<< HEAD:GLM/app/src/androidTest/java/edu/qc/seclass/GLM/ExampleInstrumentedTest.java
package edu.qc.seclass.GLM;
=======
package edu.qc.seclass.RLM;
>>>>>>> dev:GroupProject/RLM/app/src/androidTest/java/edu/qc/seclass/RLM/ExampleInstrumentedTest.java

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("edu.qc.seclass.reminderapp", appContext.getPackageName());
    }
}
