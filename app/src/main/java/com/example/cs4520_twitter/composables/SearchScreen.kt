package com.example.cs4520_twitter.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cs4520_twitter.R
import com.example.cs4520_twitter.data_layer.database.BabEntity
import com.example.cs4520_twitter.data_layer.database.dummyBab
import com.example.cs4520_twitter.vms.SearchScreenViewModel

@Composable
@Preview(showBackground = true)
fun SearchScreen() {
    val viewModel: SearchScreenViewModel = viewModel()
    var searchInput by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val maxWidth = configuration.screenWidthDp

    Column {
        Text(
            "Search",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(BorderStroke(1.dp, Color(0xFF9BAAF8))),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchInput,
                    placeholder = { Text("Search Babble") },
                    onValueChange = { searchInput = it },
                    singleLine = true,
                    modifier = Modifier.width((maxWidth * .8).dp),
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
                    onClick = { },
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp)
                        .background(Color(0xFFB9C1F1), RoundedCornerShape(15.dp)),
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = "Search button icon",
                            Modifier.size(20.dp)
                        )
                    }
                )
        }
        // List of Babs
        val dummyBabList = listOf<BabEntity>(dummyBab)
        LazyColumn(userScrollEnabled = true,
            modifier = Modifier
        ) {
            items(
                count = dummyBabList.size,
                itemContent = { index ->
                    BabCard(dummyBabList[index])
                }
            )
        }

    }
}
