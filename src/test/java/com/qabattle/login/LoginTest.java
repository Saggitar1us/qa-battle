package com.qabattle.login;

import com.qabattle.domain.BaseTest;
import com.qabattle.util.annotation.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.qabattle.util.annotation.UserAnnotation.SKIP_AUTH;

/**
 * @author Aleksei Stepanov
 */

@UserType(type = SKIP_AUTH)
@DisplayName("Login page")
public class LoginTest extends BaseTest {

    private LoginPage loginPage = new LoginPage();

    @Test
    @DisplayName("View login form on the site")
    void checkLoginPage() {
        loginPage
                .checkViewForm()
                .checkLoginField()
                .checkPasswordField()
                .checkTitle();
    }

    @Test
    @DisplayName("Sign in on the site with confirm window")
    void passSignIn() {
        loginPage
                .checkSignIn()
                .checkConfirm();
    }

    @Test
    @DisplayName("Sign in on the site with dismiss window")
    void cancelSignIn() {
        loginPage
                .checkSignIn()
                .checkDismiss();
    }

    @Test
    @DisplayName("Sign in on the site after first confirm")
    void cancelSignInAfterConfrim() {
        loginPage
                .checkSignIn()
                .checkAfterConfirm();
    }

    @Test
    @DisplayName("Sign in on the site with empty user data")
    void failSignIn() {
        loginPage
                .checkSignIn()
                .checkWithEmptyData();
    }
}
