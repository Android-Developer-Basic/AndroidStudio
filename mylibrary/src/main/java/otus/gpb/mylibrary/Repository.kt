package otus.gpb.mylibrary

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.ZoneOffset
import java.time.ZonedDateTime

class Repository(context: Context) {
    private val networkService: NetworkService by lazy { NetworkService() }
    private val db by lazy { Db(context) }

    val employees: Flow<List<Employee>> = db.employees.flowOn(Dispatchers.IO)

    suspend fun update(): Result<Int> = withContext(Dispatchers.IO) {
        networkService.getEmployees()
            .onSuccess { employees ->
                db.replace(
                    employees.map {
                        it.copy(synced = ZonedDateTime.now(ZoneOffset.UTC))
                    }
                )
            }
            .map { it.size }
    }
}