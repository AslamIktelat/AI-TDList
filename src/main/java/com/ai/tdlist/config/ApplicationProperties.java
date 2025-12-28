package com.ai.tdlist.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationProperties {

    @Value("${permit.all}")
    private String[] serviceUrls;

    @Value("${token.expiration}")
    private int tokenExpiration;


}
