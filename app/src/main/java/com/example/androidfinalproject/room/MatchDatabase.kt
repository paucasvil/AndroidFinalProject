import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Match::class], version = 1)
abstract class MatchDatabase : RoomDatabase() {
    abstract fun matchDatabaseDao(): MatchDatabaseDao
}
