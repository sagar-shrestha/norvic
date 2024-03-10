package com.hospital.norvic.repository;

import com.hospital.norvic.entity.NurseDetail;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NurseDetailRepository extends JpaRepository<NurseDetail, Integer> {

    @Query(value = "SELECT nd.* FROM nurse_detail AS nd INNER JOIN nurse_info AS ni ON nd.nurse_info=ni.id" +
            "INNER JOIN user_info AS ui ON ni.user_info=ui.id WHERE nd.id = ?1", nativeQuery = true)
    NurseDetail getNurseInformationById(Integer id) throws Exception;
}
