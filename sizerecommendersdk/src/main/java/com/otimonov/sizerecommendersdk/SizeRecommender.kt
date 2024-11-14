package com.otimonov.sizerecommendersdk

public enum class RecommendedSize(val label: String) {
    S("S"),
    M("M"),
    L("L"),
    XL("XL")
}

object SizeRecommender {
    fun recommendSize(height: Double, weight: Double): RecommendedSize {
        val bmi = weight / ((height / 100) * (height / 100))
        return when {
            bmi < 18.5 -> RecommendedSize.S
            bmi in 18.5..24.9 -> RecommendedSize.M
            bmi in 25.0..29.9 -> RecommendedSize.L
            else -> RecommendedSize.XL
        }
    }
}