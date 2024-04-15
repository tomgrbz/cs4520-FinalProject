package com.example.cs4520_twitter.composables

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cs4520_twitter.data_layer.database.dummyBabList
import com.example.cs4520_twitter.ui.theme.backgroundBrushBlueYellowTheme
import com.example.cs4520_twitter.vms.BabFeedViewModel

// file for the BabFeed composable. Currently uses a dummy list of babs.
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BabFeed() {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp
    val viewModel: BabFeedViewModel = viewModel(factory = BabFeedViewModel.Factory)

    viewModel.fetchBabs() // fetching babs
    val babs by viewModel.babList.collectAsState()

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
            LazyColumn( // contains the list of Babs. TODO: Have API fetch random babs to display
                modifier = Modifier
                    .padding(innerPadding)
                    .height((maxHeight * 0.8).dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                items(
                    count = babs.size,
                    itemContent = { index ->
                        BabCard(babs[index])
                    })
            }
        }
    }
}