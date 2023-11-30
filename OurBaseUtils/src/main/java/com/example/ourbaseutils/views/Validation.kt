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
 * Created by :: Mubarak Ansari
 * method is used for checking valid email id format.
 *
 * @param email email address as String
 * @return boolean true for valid false for invalid
 * Example : mubarakansari@Bacancy.com
 */
fun Context.isEmailValid(email: String): Boolean {
    return !TextUtils.isEmpty(email.trim()) && Patterns.EMAIL_ADDRESS.matcher(email.trim())
        .matches()
}

/**
 * Created by :: Mubarak Ansari
 * method is used for checking password is in valid format.
 *
 * @param password password as String
 * @param minPasswordLength for minimum of the password length
 * @param maxPasswordLength for maximum of the password length
 * @param minUppercase for minimum of userCase in password format
 * @param minLowercase for minimum of lowerCase in password format
 * @param minDigits for minimum of Digit in password format
 * @param minSpecialChars for minimum of special characters in password format
 *
 * By default value ==> 1 Upper case, 1 Lower case, 1 Digit, 1 Special Characters : minimum 6 and maximum 12 characters
 * @return boolean true for valid false for invalid
 * Example : Mubarak@123
 */
fun Context.isValidPassword(
    password: String,
    minPasswordLength: Int = 6,
    maxPasswordLength: Int = 50,
    minUppercase: Int = 1,
    minLowercase: Int = 1,
    minDigits: Int = 1,
    minSpecialChars: Int = 1
): Boolean {
    val patternString =
        "^(?=.*[a-z]{$minLowercase})(?=.*[A-Z]{$minUppercase})(?=.*\\d{$minDigits})(?=.*[\$&+,:;=?@#|_<>.^*()%!-]{$minSpecialChars})[A-Za-z\\d\$&+,:;=?@#|_<>.^*()%!-]{$minPasswordLength,$maxPasswordLength}"

    val pattern = Pattern.compile(patternString)
    val matcher = pattern.matcher(password.trim())
    return matcher.matches()
}

/**
 * Created by :: Mubarak Ansari
 * Method is used for checking mobile number is in valid format
 * @param minLength for minimum length of the mobile number
 * @param maxLength for maximum length of the mobile number
 * By default here minimum length is 10 and maximum length is 50
 * Example : 9876543210
 */
fun String.isValidMobileNumber(minLength: Int = 10, maxLength: Int = 50): Boolean {
    // Define a regex pattern for a valid mobile number within the specified length range
    val pattern = Regex("^\\d{$minLength,$maxLength}$")

    // Use the matches function to check if the string matches the pattern
    return pattern.matches(this)
}
