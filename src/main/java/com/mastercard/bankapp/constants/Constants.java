package com.mastercard.bankapp.constants;

public class Constants {
    public static final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String ALPHANUMERIC_CONSTANTS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String NUMERIC_CONSTANTS = "0123456789";
    public static final Long DEFAULT_BALANCE = 1000L;
    public static final String ACCOUNT_STATUS_CODE ="ACCSTAT";
    public static final String ACCOUNT_TYPE_CODE ="ACCTYP";
    public static final String CURRENCY_CODE ="CURR";
    public static final String BRANCH_CODE ="BR";
    public static final Integer LENGTH =3;
    public static final Integer ACCOUNT_NUMBER_LENGTH = 16;
    public static final Integer USER_ID_LENGTH = 7;

}
