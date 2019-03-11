package com.example.truongquockhanh.mymovieapp

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
open class BaseTestCase {
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}