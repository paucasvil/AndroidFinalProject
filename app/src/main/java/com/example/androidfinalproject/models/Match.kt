import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teamA: String,
    val teamB: String,
    val teamAScore: Int,
    val teamBScore: Int,
    val date: String
)
