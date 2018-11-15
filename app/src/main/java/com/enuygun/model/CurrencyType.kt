package com.enuygun.model

enum class CurrencyType(val type : String) {
    TRY("TRY");

    companion object {
        private val map = CurrencyType.values().associateBy(CurrencyType::type);
        fun fromString(type: String) = map[type]
    }
}