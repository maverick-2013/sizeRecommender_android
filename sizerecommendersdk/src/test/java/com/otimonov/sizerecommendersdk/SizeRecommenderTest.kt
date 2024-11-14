package com.otimonov.sizerecommendersdk

import org.junit.Assert.assertEquals
import org.junit.Test

class SizeRecommenderTest {

    @Test
    fun testSizeRecommendation() {
        assertEquals(RecommendedSize.S, SizeRecommender.recommendSize(170.0, 50.0))
        assertEquals(RecommendedSize.M, SizeRecommender.recommendSize(170.0, 65.0))
        assertEquals(RecommendedSize.L, SizeRecommender.recommendSize(170.0, 80.0))
        assertEquals(RecommendedSize.XL, SizeRecommender.recommendSize(170.0, 100.0))
    }
}