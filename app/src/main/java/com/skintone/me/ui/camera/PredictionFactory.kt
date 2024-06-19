package com.skintone.me.ui.camera

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skintone.me.database.Injection
import com.skintone.me.database.PredictionInjection

class PredictionFactory(private val repository: PredictionRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PredictionViewModel::class.java) -> {
                PredictionViewModel(repository) as T
            }
            else -> throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: PredictionFactory? = null

        @JvmStatic
        fun getInstance(context: Context): PredictionFactory {
            if (instance == null) {
                synchronized(PredictionFactory::class.java){
                    instance = PredictionFactory(
                        PredictionInjection.provideRepository(context)
                    )
                }
            }
            return instance as PredictionFactory
        }
    }

}