package com.example.cs4520_twitter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cs4520_twitter.data_layer.database.dummyBabList
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme

// file for the BabFeed composable. Currently uses a dummy list of babs.
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BabFeed() {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val maxWidth = configuration.screenWidthDp
    Box(modifier = with (Modifier) {
        fillMaxSize().background(backgroundBrushBlueYellowTheme)
    })
    {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { // Contains the feed title
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text("Babble Feed",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                )
            },
            bottomBar = {},           // navigation bar already on main
            floatingActionButton = {} // we won't use this for the feed screen
        ) { innerPadding ->
            LazyColumn( // contains the list of Babs. TODO: Have API fetch random babs to display, remove dummy bab list
                modifier = Modifier
                    .padding(innerPadding)
                    .height((maxHeight * 0.8).dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {items(
                count = dummyBabList.size,
                itemContent = { index ->
                    BabCard(dummyBabList[index])
                })
            }
        }
    }
}