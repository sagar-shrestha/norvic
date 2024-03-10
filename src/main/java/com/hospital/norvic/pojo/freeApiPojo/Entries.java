package com.hospital.norvic.pojo.freeApiPojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entries {

    private String API;
    private String Description;
    private String Auth;
    private boolean HTTPS = true;
    private String Cors;
    private String Link;
    private String Category;

}
