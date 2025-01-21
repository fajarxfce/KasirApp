package com.fajar.template.helper

import com.fajar.template.core.model.Example

object InitialDataSource {
    fun getHotelProfile(): Example {
        return Example(
            1,
            "Example Name",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam auctor, nunc nec ultricies ultricies, nunc nisl ultricies nunc, nec ultricies nunc nisl nec nisl. Nullam auctor, nunc nec ultricies ultricies, nunc nisl ultricies nunc, nec ultricies nunc nisl nec nisl."
        )
    }
}