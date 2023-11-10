package otus.gpb.mylibrary

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object NetworkService {
    private val okHttp = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl("https://dummy.restapiexample.com/api/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val api: Api get() = retrofit.create(Api::class.java)

    suspend fun getEmployees(): Result<List<Employee>> = runCatching {
        api.getEmployees().data
    }
}

@Serializable
data class DataResult(
    val status: String,
    val data: List<Employee>
)

private interface Api {
    @GET("v1/employees")
    suspend fun getEmployees(): DataResult
}