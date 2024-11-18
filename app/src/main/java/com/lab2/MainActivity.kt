package com.lab2

import android.media.RouteListingPreference
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                MainActivityScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen() {
    val itemList = remember { mutableStateListOf<Item>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Color(0xFFBBDEFB), Color(0xFF90CAF9))))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(7.0f)
                    .padding(10.dp)
            ) {
                if (itemList.isEmpty()) {
                    item {
                        Text(
                            text = "Поки немає завдань",
                            modifier = Modifier.padding(vertical = 20.dp),
                            color = Color(0xFF1E88E5),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(itemList) { item ->
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .shadow(elevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF42A5F5))
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Завдання: ${item.name}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Опис: ${item.task}",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = { itemList.remove(item) },
                                    modifier = Modifier.align(Alignment.End),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF5252)
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = "Видалити", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            val textFieldName = remember { mutableStateOf("") }
            val textFieldTask = remember { mutableStateOf("") }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(3.0f)
            ) {
                TextField(
                    value = textFieldName.value,
                    onValueChange = { newName -> textFieldName.value = newName },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp, RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFF1E88E5),
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = "Назва нотатки", color = Color.Gray)
                    }
                )

                TextField(
                    value = textFieldTask.value,
                    onValueChange = { newTask -> textFieldTask.value = newTask },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .shadow(4.dp, RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFF1E88E5),
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(text = "Завдання", color = Color.Gray)
                    }
                )

                Button(
                    onClick = {
                        if (textFieldName.value.isNotBlank() && textFieldTask.value.isNotBlank()) {
                            itemList.add(
                                Item(
                                    name = textFieldName.value,
                                    task = textFieldTask.value
                                )
                            )
                            textFieldName.value = ""
                            textFieldTask.value = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text(
                        text = "Додати завдання",
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

data class Item(val name: String, val task: String)

@Preview
@Composable
fun MainActivityPreview() {
    MainActivityScreen()
}
