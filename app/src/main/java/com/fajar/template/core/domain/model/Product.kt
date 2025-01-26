package com.fajar.template.core.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String,
    val stock: Int,
    val sellPrice: Long,
    val purchasePrice: Long,
    val barcode: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(image)
        dest.writeInt(stock)
        dest.writeLong(sellPrice)
        dest.writeLong(purchasePrice)
        dest.writeString(barcode)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
