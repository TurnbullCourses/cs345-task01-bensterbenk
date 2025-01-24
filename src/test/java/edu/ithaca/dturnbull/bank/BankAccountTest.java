package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        //E: testing that one letter on each side works B: min no. of characters on each side
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        //E: making sure empty is not taken B: testing empty
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        //invalid prefixes  
        //E: end of prefix is nonvalid B:end is not special char
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //E: double special char is not valid
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //E: Certain special chars are never allowed
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        //E: special chars at beginning not allowed B: testing very start for chars
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));

        //valid prefixes
        //E: special chars allowed in middle
        assertTrue(BankAccount.isEmailValid( "abc_def@mail.com"));
        //E: standard, unaltered email allowed
        assertTrue(BankAccount.isEmailValid( "abc@mail.com"));
        //E: Special chars allowed in middle, repeat of line 44
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
        //E: special chars in middle allowed, repeat of line 44
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));

        //invalid domains
        //E: Checking that address must fit into one if the known ones B: testing for invalid address 
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //E: Checking for no forbidden special chars
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //E: Checking for . present B: Testing that the . is present
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //E: checking for no double special chars
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));

        //valid domains
        //E: Testing that .cc is valid 
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc"));
        //E: testing that special chars are allowed before .
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com"));
        //E: testing that .org is valid
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org"));
        //E: testing that .com is valid
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
    

        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}