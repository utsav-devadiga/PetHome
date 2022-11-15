package util;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern PASSWORD_UPPERCASE_PATTERN =
            Pattern.compile("(?=.*[A-Z])" + ".{6,}");
    public static final Pattern PASSWORD_LOWER_PATTERN =
            Pattern.compile("(?=.*[a-z])" + ".{6,}");
    public static final Pattern PASSWORD_NUMBER_PATTERN =
            Pattern.compile("(?=.*[0-9])" + ".{6,}");
    public static final Pattern PASSWORD_SPECIAL_CHARACTER_PATTERN =
            Pattern.compile("(?=.*[@#$%^!&+=])" + ".{6,}");
    public static final Pattern MY_EMAIL =
            Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    public static final Pattern NUMBER_PATTERN =
            Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$");
    public static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Z][a-z]{2,10}$");
    public static final Pattern NO_SPACE =
            Pattern.compile("^[a-zA-Z0-9]*$");
    public  static  final Pattern PHONE_NUM =
            Pattern.compile("\\d{10}");
}
