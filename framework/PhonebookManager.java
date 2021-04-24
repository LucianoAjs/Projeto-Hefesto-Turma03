package android.app;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.content.Context;
import android.annotation.SystemService;

@SystemService(Context.PHONEBOOK_SERVICE)
public class PhonebookManager {
    private final String TAG = "Phonebook";
    private IPhonebookService mService;
    private Context mContext;
    /**
    *{@hide} to prevent subclassing from outside of the framework
    */
    public PhonebookManager(Context context, IPhonebookService service) {
        this.mContext = context;
        this.mService = service;
        Log.i("Phonebook", "Constructor PhonebookManager");
    }

    public boolean verifyNumber(String number) {
        try {
            // Log.i(TAG, "verifyNumber on DemoPhonebookMgr");
            return this.mService.verifyIfNumberExistOnWhileList(number);
        } catch(Exception e) {
            Log.i(TAG, "Error verifyNumber: " + e);
        }
        return false;
    }

    public String getNumber(String number) {
        try {
            // Log.i(TAG, "getNumber on DemoPhonebookMgr");
            return this.mService.getNumberOnWhiteList(number);
        } catch (Exception e) {
            Log.i(TAG, "Error getNumber:" + e);
        }
        return "";
    }

    public String[] getAllNumbers() {
        try {
            // Log.i(TAG, "getAllNumbers on DemoPhonebookMgr");
            return this.mService.getAllNumbersOnWhiteList();
        } catch (Exception e) {
            Log.e(TAG, "Error getAllNumbers: " + e);
        }
        return null;
    }

    public boolean setNumber(String number) {
        try {
            // Log.i(TAG, "setNumber on DemoPhonebookMgr");
            return this.mService.setNumberOnWhiteList(number);
        } catch (Exception e) {
            Log.i(TAG, "Error setNumber: " + e);
        }
        return false;
    }

    public boolean updateNumber(String number, String newNumber) {
        try {
            // Log.i(TAG, "updateNumber on DemoPhonebookMgr");
            return this.mService.updateNumberOnWhiteList(number, newNumber);
        } catch (Exception e) {
            Log.i(TAG, "Error setNumber: " + e);
        }
        return false;
    }

    public boolean deleteNumber(String number) {
        try {
            // Log.i(TAG, "deleteNumber on DemoPhonebookMgr");
            return this.mService.deleteNumberOnWhiteList(number);
        } catch (Exception e) {
            Log.i(TAG, "Error setNumber: " + e);
        }
        return false;
    }

}