package com.pika.controller;

import cn.hutool.core.util.IdUtil;
import com.pika.entity.LeaseReq;
import com.pika.entity.LeasesRep;
import com.pika.util.JrebelUtil.JrebelSign;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author pikachu
 * @since 2023/5/12 21:09
 */
@RestController
@Slf4j
public class JrebelController {
    public static final String SERVER_GUID = "c55b5196-b386-4bd2-8318-f9b72bd8bbea";

    @DeleteMapping("/jrebel/leases/1")
    public LeasesRep del(@RequestParam("username") String username) {
        LeasesRep rep = new LeasesRep();
        rep.setServerVersion("9.9.9");
        rep.setServerProtocolVersion("1.1");
        rep.setServerGuid(SERVER_GUID);
        rep.setGroupType("managed");
        rep.setStatusCode("SUCCESS");
        rep.setCompany(username);
        log.info("response:{}", rep);
        return rep;
    }

    @PostMapping("/jrebel/leases")
    public LeasesRep activate(LeaseReq leaseReq) {
        LeasesRep rep = new LeasesRep();
        String clientRandomness = leaseReq.getRandomness();
        String username = leaseReq.getUsername();
        String guid = leaseReq.getGuid();
        boolean offline = leaseReq.getOffline();
        rep.setOffline(offline);
        String validFrom = "null";
        String validUntil = "null";
        if (offline) {
            String clientTime = leaseReq.getClientTime();
            long clientTimeUntil = Long.parseLong(clientTime) + 180L * 24 * 60 * 60 * 1000;
            validFrom = clientTime;
            validUntil = String.valueOf(clientTimeUntil);
            rep.setValidFrom(Long.valueOf(validFrom));
            rep.setValidUntil(Long.valueOf(validUntil));
        }
        if (clientRandomness == null || username == null || guid == null) {
            throw new RuntimeException("clientRandomness == null || username == null || guid == null");
        } else {
            JrebelSign jrebelSign = new JrebelSign();
            jrebelSign.toLeaseCreateJson(clientRandomness, guid, offline, validFrom, validUntil);
            String signature = jrebelSign.getSignature();
            rep.setSignature(signature);
            rep.setCompany(username);
        }
        log.info("response:{}", rep);
        return rep;
    }

    @RequestMapping("guid")
    public String guid(HttpServletRequest request) {
        return request.getRequestURL().toString().replace("guid", "") + IdUtil.simpleUUID();
    }
}
