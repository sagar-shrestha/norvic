package com.hospital.norvic.pojo.freeApiPojo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntryDetails {

    private int Count;
    private List<Entries> entries;
}
