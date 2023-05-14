package com.pika.util.JrebelUtil;

import org.apache.commons.lang3.StringUtils;

public class JrebelSign {
    public static final String SERVER_RANDOMNESS = "pikachu";
    private String signature;

    public void toLeaseCreateJson(String clientRandomness, String guid, boolean offline, String validFrom, String validUntil) {
        //服务端随机数,如果要自己生成，务必将其写到json的serverRandomness中
        String s2 = "";
        if (offline) {
            s2 = StringUtils.join(new String[]{clientRandomness, SERVER_RANDOMNESS, guid, String.valueOf(offline), validFrom, validUntil}, ';');
        } else {
            s2 = StringUtils.join(new String[]{clientRandomness, SERVER_RANDOMNESS, guid, String.valueOf(offline)}, ';');
        }
        System.out.println(s2);
        final byte[] a2 = LicenseServer2ToJRebelPrivateKey.a(s2.getBytes());
        this.signature = ByteUtil.a(a2);
    }

    public String getSignature() {
        return signature;
    }

}
