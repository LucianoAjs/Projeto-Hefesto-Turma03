package android.app;
/**
* System private API for talking with the Phonebook service.
* {@hide}
*/
interface IPhonebookService {
    String getNumberOnWhiteList(String number);
    String[] getAllNumbersOnWhiteList();
    boolean verifyIfNumberExistOnWhileList(String number);
    boolean setNumberOnWhiteList(String number);
    boolean updateNumberOnWhiteList(String number, String newNumber);
    boolean deleteNumberOnWhiteList(String number);
} 