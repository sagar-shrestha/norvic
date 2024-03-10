package com.hospital.norvic.repository;

import com.hospital.norvic.entity.StaffDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffDetailRepository extends JpaRepository<StaffDetail, Integer> {

    @Query(value = "SELECT sd.* FROM staff_detail AS sd INNER JOIN staff_info AS si ON sd.staff_info = si.id" +
            "INNER JOIN user_info AS ui ON si.user_info = ui.id WHERE sd.id = ?1", nativeQuery = true)
    StaffDetail getNurseInformationById(Integer id);
}
