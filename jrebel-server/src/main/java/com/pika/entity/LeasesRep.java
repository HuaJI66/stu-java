package com.pika.entity;

import com.pika.util.JrebelUtil.JrebelSign;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author pikachu
 * @since 2023/5/12 20:53
 */
@NoArgsConstructor
@Data
public class LeasesRep {
    private String serverVersion = "9.9.9";
    private String serverProtocolVersion = "1.1";
    private String serverGuid = "c55b5196-b386-4bd2-8318-f9b72bd8bbea";
    private String groupType = "managed";
    private Integer id = 1;
    private Integer licenseType = 1;
    private Boolean evaluationLicense = Boolean.FALSE;
    private String signature;
    private String serverRandomness = JrebelSign.SERVER_RANDOMNESS;
    private String seatPoolType = "standalone";
    private String statusCode = "SUCCESS";
    private Boolean offline;
    private Long validFrom;
    private Long validUntil;
    private String company;
    private String orderId = "";
    private List<Object> zeroIds = Collections.emptyList();
    private Long licenseValidFrom = 1490544001000L;
    private Long licenseValidUntil = 64063152000000L;
}
