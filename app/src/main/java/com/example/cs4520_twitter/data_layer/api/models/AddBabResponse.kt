package com.example.cs4520_twitter.data_layer.api.models

import com.example.cs4520_twitter.data_layer.database.BabEntity

data class AddBabResponse(val bab: BabEntity, val allBabsByUser: List<BabEntity>)
