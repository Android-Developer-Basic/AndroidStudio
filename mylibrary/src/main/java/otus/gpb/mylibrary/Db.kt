package otus.gpb.mylibrary

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverter
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class Db(context: Context) {
    private val db = Room.databaseBuilder(context, EmployeeDb::class.java,"employees")
        .fallbackToDestructiveMigration()
        .build()

    val employees: Flow<List<Employee>>
        get() = db.employees().getAll()

    fun replace(employees: List<Employee>) {
        db.employees().replace(employees)
    }
}

@Database(
    entities = [Employee::class],
    version = 2
)
internal abstract class EmployeeDb : RoomDatabase() {
    abstract fun employees(): EmployeeDao
}

@Dao
internal abstract class EmployeeDao {
    @Query("SELECT * FROM Employees")
    abstract fun getAll(): Flow<List<Employee>>

    @Insert
    abstract fun insertAll(employees: List<Employee>)

    @Query("DELETE FROM Employees")
    abstract fun delete()

    @Transaction
    open fun replace(employees: List<Employee>) {
        delete()
        insertAll(employees)
    }
}

internal class Converters {
    @TypeConverter
    fun toDateTime(string: String?): ZonedDateTime? {
        return string?.let { ZonedDateTime.parse(string) }
    }

    @TypeConverter
    fun fromDateTime(dateTime: ZonedDateTime?): String? {
        return dateTime?.toString()
    }
}