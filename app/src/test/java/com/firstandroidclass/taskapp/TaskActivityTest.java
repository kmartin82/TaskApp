package com.firstandroidclass.taskapp;

/**
 * Created by kmartin82 on 3/30/2017.
 */
import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskActivityTest {
    private static final String PACKAGE_NAME = "com.test.taskapp";

    @Mock
    Context mContext;

    @Test
    public void contactFragmentCreationTest() {
        when(mContext.getPackageName()).thenReturn(PACKAGE_NAME);
        TaskActivity taskActivity = new TaskActivity();
        String packageName = taskActivity.getPackage(mContext);
        assertEquals(PACKAGE_NAME, packageName);
    }


}
