import android.app.Application
import androidx.room.Room

class VolleyballApp : Application() {
    companion object {
        lateinit var database: MatchDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            MatchDatabase::class.java,
            "volleyball_db"
        ).build()
    }
}
