package com.example.ourbaseutils.views

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern


/**
 * Created by :: Mubarak Ansari
 * Date :: 22/11/2023
 *
 * method is used for checking if string is empty or not.
 *
 * @param mString as String
 * @return boolean true if [mString] is notnull.
 */
fun Context.isNotNull(mString: String?): Boolean {
    return when {
        mString == null -> {
            false
        }

        mString.trim().equals("", ignoreCase = true) -> {
            false
        }

        mString.trim().equals("N/A", ignoreCase = true) -> {
            false
        }

        mString.trim().equals("[]", ignoreCase = true) -> {
            false
        }

        mString.trim().equals("null", ignoreCase = true) -> {
            false
        }

        else -> !mString.trim().equals("{}", ignoreCase = true)
    }
}

/**
 * method is used for checking valid email id format.
 *
 * @param email email address as String
 * @return boolean true for valid false for invalid
 */
fun Context.isEmailValid(email: String): Boolean {
    return !TextUtils.isEmpty(email.trim()) && Patterns.EMAIL_ADDRESS.matcher(email.trim())
        .matches()
}

/**
 * method is used for checking password is in valid format.
 *
 * @param password password as String
 *
 * 1 Upper case, 1 Lower case, 1 Special Characters : minimum 6 characters
 * @return boolean true for valid false for invalid
 */
fun Context.isValidPassword(password: String): Boolean {
    val patn =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\$&+,:;=?@#|_<>.^*()%!-])[A-Za-z\\d\$&+,:;=?@#|_<>.^*()%!-]{6,}"
    val pattern = Pattern.compile(patn)
    val matcher = pattern.matcher(password.trim())
    return matcher.matches()
}
