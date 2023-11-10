package otus.gpb.mylibrary

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val id: Int,
    @SerialName("employee_name")
    val name: String,
    @SerialName("employee_salary")
    val salary: Int,
    @SerialName("employee_age")
    val age: Int
)