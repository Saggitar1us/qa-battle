package com.qabattle.domain;

import com.qabattle.extension.TestsConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Aleksei Stepanov
 */

@ExtendWith(TestsConfiguration.class)
public abstract class BaseTest {

    public String getPath() {
        return "";
    }

}
