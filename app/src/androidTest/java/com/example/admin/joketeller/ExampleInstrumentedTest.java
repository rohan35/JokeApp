package com.example.admin.joketeller;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.admin.joketeller.Sync.EndPointsAsyncTask;
import com.example.admin.joketeller.Sync.RetrieveResults;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private final CountDownLatch mSignal = new CountDownLatch(1);
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
    }
    public void testJokeRetriever() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        new EndPointsAsyncTask(appContext,new TestJokeListener()).execute();

        try {
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Server not running.");
            }
        } catch (InterruptedException e) {
            fail();
        }
    }

    private class TestJokeListener implements RetrieveResults {



        @Override
        public void OnResultRetrieved(String Result) {
            assertTrue(Result != null && Result.length() > 0);
            mSignal.countDown();
        }

        @Override
        public void OnPreRetrieving() {
            //Nothing to do

        }

    }
}
