package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface RawMaterialsRepository extends JpaRepository<RawMaterial, Integer> {
    // Find raw materials based on search keyword, manufacturer, and group name
    @Query("SELECT rm FROM RawMaterial rm WHERE " +
            "((:searchKeyword is NULL OR LOWER(rm.description) LIKE %:searchKeyword%) AND " +
            "(:manufacturer IS NULL OR rm.manufacturer = :manufacturer) AND " +
            "(:groupName IS NULL OR rm.groupName = :groupName))")
    List<RawMaterial> findFilteredRawMaterials(
            @Param("searchKeyword") String searchKeyword,
            @Param("manufacturer") String manufacturer,
            @Param("groupName") Integer groupName
    );
}
