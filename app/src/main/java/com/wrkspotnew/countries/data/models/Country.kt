package com.wrkspotnew.countries.data.models

data class Country(
    val abbreviation: String,
    val capital: String,
    val currency: String,
    val name: String,
    val phone: String,
    val population: Int,
    val media: Media
)

data class Media(
    val flag: String,
    val emblem: String,
    val orthographic: String
)