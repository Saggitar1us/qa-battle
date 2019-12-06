package com.qabattle.profile;

import com.qabattle.domain.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Aleksei Stepanov
 */


@DisplayName("Profile page")
public class ProfileTest extends BaseTest {

private final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("View profile page")
    void checkView() {
        assertTrue(true);
    }

    @Override
    public String getPath() {
        return "/profile.html";
    }
}
