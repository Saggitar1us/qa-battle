package com.qabattle.domain;

import com.qabattle.extension.TestsConfiguration;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Aleksei Stepanov
 */

@Getter
@ExtendWith(TestsConfiguration.class)
public abstract class BaseTest {

    public String getPath() {
        return "";
    }

}
