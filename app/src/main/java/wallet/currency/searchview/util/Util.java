package wallet.currency.searchview.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import wallet.currency.searchview.UsersItemsModal;

public class Util {

    /**
     * Force hide keyboard if open
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Filtering followers by search char sequence
     * @param list source follower list
     * @param charString searching char sequence
     * @return filtered follower list
     */
    public static List<UsersItemsModal> searchFollowersFilter(List<UsersItemsModal> list, String charString) {
        List<UsersItemsModal> filteredTempList = new ArrayList<>();
        for (UsersItemsModal follower : list) {
            if (follower != null ) {
                // Filter by user name and user id
                if (containsIgnoreCase(follower.getItems_name(), charString)
                        || containsIgnoreCase(String.valueOf(follower.getItems_id()), charString)) {
                    filteredTempList.add(follower);
                }
            }
        }
        return filteredTempList;
    }

    /**
     * Search if substring has char sequence in source string ignore case
     * @param src source string
     * @param charString substring for searching
     * @return true if has coincidence
     */
    public static boolean containsIgnoreCase(String src, String charString) {
        final int length = charString.length();
        if (length == 0) {
            return true;
        }
        for (int i = src.length() - length; i >= 0; i--) {
            if (src.regionMatches(true, i, charString, 0, length)) {
                return true;
            }
        }
        return false;
    }
}
