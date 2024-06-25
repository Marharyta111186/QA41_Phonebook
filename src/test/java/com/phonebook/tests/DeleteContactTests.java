package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase{

    @BeforeMethod
    public void precondition() {

        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESC));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void deleteContactPositiveTest() {
        //get size of contacts before delete
        int sizeBefore = app.getContact().sizeOfContacts();

        app.getContact().removeContact();
        app.getContact().pause(500);
        //get size of contacts after delete
        int sizeAfter = app.getContact().sizeOfContacts();
        //compare size before and size after with assert
        Assert.assertEquals(sizeAfter,sizeBefore -1);
    }

}
