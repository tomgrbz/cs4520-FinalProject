package com.example.cs4520_twitter.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cs4520_twitter.R

@Composable
@Preview(showBackground = true)
fun SearchScreen() {
    val viewModel: SearchScreenViewModel = viewModel()
    var searchInput by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchInput,
            placeholder = { Text("Search Babble") },
            onValueChange = { searchInput = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
            ),
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {  },
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFB9C1F1), CircleShape),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search button icon",
                    Modifier.size(20.dp)
                )
            }
        )
        // TODO() add post components here
        // for (Post p in viewModel.results) ...
    }
}
