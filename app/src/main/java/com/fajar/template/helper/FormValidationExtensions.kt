package com.fajar.template.helper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

suspend fun List<FormField<*>>.validateAll(focusIfError: Boolean = true): Boolean {
    var isvalid = true
    for (field in this) {
        if (!field.validate(focusIfError)) {
            isvalid = false
        }
    }
    return isvalid
}

fun List<FormField<*>>.clearAll() {
    for (field in this) {
        field.clearError()
    }
}

fun List<FormField<*>>.clearAllFocus() {
    for (field in this) {
        field.clearFocus()
    }
}

fun List<FormField<*>>.disableALl() {
    for (field in this) {
        field.disable()
    }
}

fun List<FormField<*>>.enableAll() {
    for (field in this) {
        field.enable()
    }
}

fun List<FormField<*>>.isValidFlow(): Flow<Boolean> {
    return combine(this.map { it.isValid }) { results ->
        results.all { it }
    }
}