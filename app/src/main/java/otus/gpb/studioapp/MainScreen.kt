package otus.gpb.studioapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import otus.gpb.mylibrary.Employee
import otus.gpb.mylibrary.Repository
import otus.gpb.studioapp.ui.theme.StudioAppTheme

@Composable
fun MainScreen(repository: Repository) {
    val employees by repository.employees.collectAsStateWithLifecycle(initialValue = emptyList())
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    scope.launch {
                        repository.update()
                            .onFailure { error ->
                                Toast.makeText(context, "Network error: $error", Toast.LENGTH_SHORT).show()
                                Log.w(MainActivity.TAG, "Network error:", error)
                            }
                            .onSuccess { count ->
                                Toast.makeText(context, "Network success. Total: $count", Toast.LENGTH_SHORT).show()
                                Log.i(MainActivity.TAG, "Network success. Total: $count")
                            }
                    }
                }) {
                    Text("Button")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                HeaderItem()
            }
            items(employees) { employee ->
                EmployeeItem(employee)
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun HeaderItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.name),
            modifier = Modifier.weight(0.7f),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Text(
            text = stringResource(R.string.salary),
            modifier = Modifier.weight(0.3f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun EmployeeItem(employee: Employee) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(backgroundColor)
            .clickable { isSelected = !isSelected }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = employee.name,
            modifier = Modifier.weight(0.7f),
            style = MaterialTheme.typography.bodyLarge
        )
        VerticalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Text(
            text = employee.salary.toString(),
            modifier = Modifier.weight(0.3f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

class EmployeeProvider : PreviewParameterProvider<Employee> {
    override val values = sequenceOf(
        Employee(id = 1, name = "Ivan", salary = 50000, age = 30),
        Employee(
            id = 2,
            name = "Konstantin Konstantinopolsky-Vozdvizhensky",
            salary = 100000,
            age = 45
        )
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun EmployeeItemPreview(
    @PreviewParameter(EmployeeProvider::class) employee: Employee
) {
    StudioAppTheme {
        EmployeeItem(employee)
    }
}
