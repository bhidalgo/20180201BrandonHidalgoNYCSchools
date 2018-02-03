package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SchoolDao {
    @Query("SELECT * FROM schools WHERE borough = (:borough)")
    List<SchoolEntity> getByBorough(String borough);

    @Query("SELECT * FROM schools WHERE name = (:name)")
    SchoolEntity getByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertSchools(List<SchoolEntity> schoolEntities);
}
