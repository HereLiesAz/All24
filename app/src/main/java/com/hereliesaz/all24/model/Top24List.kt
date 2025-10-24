package com.hereliesaz.all24.model

data class Top24List(
    val title: String,
    val author: String,
    val items: List<Top24Item>
)

data class Top24Item(
    val name: String,
    val rank: Int,
    val theAll24Take: String,
    val theVitals: String
)
