package com.qabattle.profile;

import com.qabattle.domain.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Aleksei Stepanov
 */


@DisplayName("Profile page")
public class ProfileTest extends BaseTest {

    private final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("View profile page")
    void checkViewProfilePage() {
        profilePage.
                checkViewPage()
                .checkViewUser()
                .checkViewPayment();
    }

    @Test
    @DisplayName("Save user profile")
    void saveProfile() {
        profilePage.saveProfile();
    }

    @Test
    @DisplayName("Save payment info")
    void savePaymentInfo() {
        profilePage.savePayment();
    }

    @Test
    @DisplayName("Save payment with incorrect data")
    void saveIncorrectData() {
        profilePage.saveIncorrect();
    }

    @Override
    public String getPath() {
        return "/profile.html";
    }
}
