package example.ui.tests.loginPage;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static example.ui.enums.Locators.*;
import static example.utils.RandomUtils.*;
import static org.testng.Assert.assertTrue;


public class PasswordRecoveryPhoneNumberTest extends BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }


    @Test(description = "Password recovery using a phone number that is already in the database")
    public void passwordRecoveryPhoneNumberDatabase() {
        helperUi.recoveryPasswordPhone()
                .createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .changePasswordByPhone();
        assertTrue(helperUi.getLoginBox().isEmptyLoginField(), "The field of Login is not empty");
        assertTrue(helperUi.getLoginBox().isEmptyPasswordField(), "The field of Password is not empty");
        helperUi.loginAfterRecoverPasswordByPhone();
        assertTrue(helperUi.getExplorePage().isDisplayedExplorePage(), "Explore page is not opened");
        helperUi.signOut();
    }

     @Test(description = "Password recovery using a phone number that is not in the database")
    public void passwordRecoveryPhoneNumberNoInDatabase() {
        helperUi.recoveryPasswordPhone()
                .createUser()
                .changePasswordByPhoneNoInDatabase();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedPopUpMessage(), " Message \"Number doesn't exist\" doesn't appears");
    }


    @Test(description = "Password recovery with invalid values")
    public void passwordRecoveryPhoneNumberDataBasePasswordsInvalid() {
        helperUi.recoveryPasswordPhone()
                .createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .changePassword()
                .createNewDifferentPasswords();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_MUST_MATCH.getValue()));
        helperUi.createPasswordNewPasswordAndNull();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_REQUIRED.getValue()));
        helperUi.createPasswordNewNullAndPassword();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_REQUIRED.getValue()));
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_MUST_MATCH.getValue()));
        helperUi.createPasswordNewNullAndNull();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_REQUIRED.getValue()));
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_REQUIRED.getValue()));
        helperUi.createPasswordNewSevenSymbols();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_EIGHTCHARACTERS.getValue()));
        helperUi.createPasswordWithPoint();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_CONTAIN.getValue()));
        helperUi.addPasswordSixteenCharacter();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_TOO_MANY.getValue()));
        helperUi.createPasswordNewFirstPoint();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_PASSWORD_CONFIRM_CONTAIN.getValue()));
    }

     @Test(description = "Password recovery by phone number and incorrect verification code")
    public void passwordRecoveryPhoneNumberDataBaseIncorrectVerify() {
        helperUi.recoveryPasswordPhone()
                .createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .changePasswordByPhoneWithoutVerify()
                .inputVerificationCode("");
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_VERIFY_NULL_INPUT.getValue()), "The message Required doesn't appear");
        helperUi.inputVerificationCode(generateVerifyIncorrectCode());
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(POP_UP_MESSAGE_VERIFY_INVALID.getValue()), "Invalid code error pop up message doesn't appear");
        helperUi.clickPopUmMessage()
                .deletePreviousInputVerificationCode(generateVerifyThreeNumberCode());
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_VERIFY_WRONG_CODE.getValue()), "The error message doesn't appear");
        helperUi.deletePreviousInputVerificationCode(generateVerifyFiveNumberCode());
        assertTrue(helperUi.getPasswordChangeBox().checkingCountDigitsVerificationCode(), "There is five digits");
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(POP_UP_MESSAGE_VERIFY_INVALID.getValue()), "Invalid code error pop up message doesn't appear");
        helperUi.clickPopUmMessage()
                .deletePreviousInputVerificationCode("99");
        int attributeBefore = helperUi.getPasswordChangeBox().checkingVerificationCodeBeforeEnter();
        helperUi.inputVerificationCode(" ");
        assertTrue(helperUi.getPasswordChangeBox().checkingVerificationCodeEnterOneIncorrect(attributeBefore), "There is space");
        helperUi.inputVerificationCode("9");
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_VERIFY_WRONG_CODE.getValue()), "Invalid code error pop up message doesn't appear");
        attributeBefore = helperUi.getPasswordChangeBox().deleteAndCheckingVerificationCodeBeforeEnter();
        helperUi.deletePreviousInputVerificationCode(generateVerifyCodeWithLetter());
        assertTrue(helperUi.getPasswordChangeBox().checkingVerificationCodeEnterOneIncorrect(attributeBefore), "There is letter");
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_VERIFY_NULL_INPUT.getValue()), "The message Required doesn't appear");
        helperUi.deletePrevious()
                .deletePreviousInputVerificationCode("9");
        attributeBefore = helperUi.getPasswordChangeBox().checkingVerificationCodeBeforeEnter();
        helperUi.inputVerificationCode("+");
        assertTrue(helperUi.getPasswordChangeBox().checkingVerificationCodeEnterOneIncorrect(attributeBefore), "There is symbol");
        helperUi.inputVerificationCode("9");
        attributeBefore = helperUi.getPasswordChangeBox().checkingVerificationCodeBeforeEnter();
        helperUi.inputVerificationCode("=");
        assertTrue(helperUi.getPasswordChangeBox().checkingVerificationCodeEnterOneIncorrect(attributeBefore), "There is symbol");
        helperUi.clickOnVerify();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedMessage(MESSAGE_VERIFY_WRONG_CODE.getValue()), "The error message doesn't appear");
    }
}
