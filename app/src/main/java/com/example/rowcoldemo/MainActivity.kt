package com.example.rowcoldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Preview(showSystemUi = true)
@Composable
fun RowColDemoScreenPreview() {
    RowColDemoTheme {
        RowColDemoScreen()
    }
}
