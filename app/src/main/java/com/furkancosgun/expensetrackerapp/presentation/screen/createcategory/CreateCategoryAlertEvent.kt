package com.furkancosgun.expensetrackerapp.presentation.screen.createcategory

sealed class CreateCategoryAlertEvent {
    data class CategoryChanged(val category: String) : CreateCategoryAlertEvent()
    data object Submit : CreateCategoryAlertEvent()
}