package com.hospital.norvic.pojo.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRequest {

    private MultipartFile photo;

}
