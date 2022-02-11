package com.example.movieapp.domain.response

data class ProvidersResponse (
    val id: Long,
    val results: Region
)

data class Region(
    val MX:ProviderResult
)

data class ProviderResult(
    val link: String,
    val flatrate: List<DataProviders>,
    val rent: List<DataProviders>,
    val buy: List<DataProviders>,
)

data class DataProviders (
    val display_priority: Long,
    val logo_path: String,
    val provider_id: Long,
    val provider_name: String
)