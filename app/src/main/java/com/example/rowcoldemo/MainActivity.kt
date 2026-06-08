package com.example.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rowcoldemo.ui.theme.RowColDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RowColDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RowColDemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RowColDemoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionTitle("BasicColumn")
        BasicColumn()
        SectionTitle("BasicRow")
        BasicRow()
        SectionTitle("Row arrangement variants")
        RowArrangementDemo()
        SectionTitle("Nested Row + Column")
        NestedDemo()
        SectionTitle("Weight modifier (1 : 2 : 1)")
        WeightDemo()
        SectionTitle("UserCard (Row + nested Column)")
        UserCard(name = "Артемий Шубин", subtitle = "Студент СибГУ", initial = "А")
        UserCard(name = "Николай Киреев", subtitle = "Преподаватель", initial = "Н")
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleMedium, color = Color(0xFF555555))
}

/**
 * Дизайн 1. Базовый Column: три текста расположены вертикально, сверху вниз.
 */
@Composable
fun BasicColumn(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color(0xFFE3F2FD)).padding(8.dp)) {
        Text(text = "Column item 1")
        Text(text = "Column item 2")
        Text(text = "Column item 3")
    }
}

/**
 * Дизайн 2. Базовый Row: три текста расположены горизонтально, слева направо.
 */
@Composable
fun BasicRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier.background(Color(0xFFFFF3E0)).padding(8.dp)) {
        Text(text = "Row 1  ")
        Text(text = "Row 2  ")
        Text(text = "Row 3")
    }
}

/**
 * Дизайн 3. Row с разными значениями horizontalArrangement.
 * Каждая строка fillMaxWidth и применяет свой arrangement к одинаковым 3 «чипам».
 */
@Composable
fun RowArrangementDemo(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ArrangedRow("SpaceBetween", Arrangement.SpaceBetween)
        ArrangedRow("SpaceAround", Arrangement.SpaceAround)
        ArrangedRow("SpaceEvenly", Arrangement.SpaceEvenly)
        ArrangedRow("Center", Arrangement.Center)
        ArrangedRow("End", Arrangement.End)
        ArrangedRow("spacedBy(16.dp)", Arrangement.spacedBy(16.dp))
    }
}

@Composable
private fun ArrangedRow(label: String, arrangement: Arrangement.Horizontal) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F8E9))
                .padding(4.dp),
            horizontalArrangement = arrangement
        ) {
            Chip("A"); Chip("B"); Chip("C")
        }
    }
}

@Composable
private fun Chip(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(Color(0xFF8BC34A))
            .padding(horizontal = 12.dp, vertical = 4.dp),
        color = Color.White
    )
}

/**
 * Дизайн 4. Вложенные макеты: Row из двух Column.
 * Каждая Column содержит несколько Text и имеет свой фон — видно границу колонок.
 */
@Composable
fun NestedDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFEDE7F6))
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Left top")
            Text("Left middle")
            Text("Left bottom")
        }
        Column(
            modifier = Modifier
                .background(Color(0xFFE0F2F1))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text("Right 1")
            Text("Right 2")
            Text("Right 3")
        }
    }
}

/**
 * Дизайн 5. Modifier.weight: пропорциональное распределение свободного места.
 * Три элемента с весами 1, 2, 1 — средний получает половину Row, крайние по четверти.
 */
@Composable
fun WeightDemo(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            text = "1",
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color(0xFFEF5350))
                .padding(8.dp),
            color = Color.White
        )
        Text(
            text = "2 (двойная ширина)",
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .background(Color(0xFF42A5F5))
                .padding(8.dp),
            color = Color.White
        )
        Text(
            text = "1",
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color(0xFF66BB6A))
                .padding(8.dp),
            color = Color.White
        )
    }
}

/**
 * Дизайн 6. Карточка пользователя — реальный UI-паттерн.
 * Row с круглым «аватаром» (Box+CircleShape) + Column с двумя строками текста + Box-статус справа.
 */
@Composable
fun UserCard(
    name: String,
    subtitle: String,
    initial: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFFFFF))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFF673AB7)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = initial, color = Color.White, style = MaterialTheme.typography.titleMedium)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, style = MaterialTheme.typography.bodyLarge)
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color(0xFF4CAF50))
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun RowColDemoScreenPreview() {
    RowColDemoTheme {
        RowColDemoScreen()
    }
}
