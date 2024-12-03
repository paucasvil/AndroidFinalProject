import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDatabaseDao {
    @Insert
    suspend fun insertMatch(match: Match)

    @Query("SELECT * FROM matches ORDER BY date DESC")
    suspend fun getAllMatches(): List<Match>
}
