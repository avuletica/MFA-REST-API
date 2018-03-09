package com.fesb.mfa.security.constants;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/security/sign-up";
    public static final String TWO_FACTOR_AUTHENTICATION_ENABLED = "/security/twoFactorAuthenticationActiveState/**";
    public static final String VALIDATE_BACKUP_CODE = "/security/validateBackupCode/**";
    public static final String VALIDATE_SECRET_QUESTION = "/security/validateSecretQuestionAnswer/**";
}