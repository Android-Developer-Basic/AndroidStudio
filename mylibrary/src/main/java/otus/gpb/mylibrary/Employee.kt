package otus.gpb.mylibrary

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.ZonedDateTime

@Serializable
@Entity(tableName = "Employees")
@TypeConverters(Converters::class)
data class Employee(
    @PrimaryKey
    val id: Int,
    @SerialName("employee_name")
    val name: String,
    @SerialName("employee_salary")
    val salary: Int,
    @SerialName("employee_age")
    val age: Int,
    @Transient
    val synced: ZonedDateTime? = null
)