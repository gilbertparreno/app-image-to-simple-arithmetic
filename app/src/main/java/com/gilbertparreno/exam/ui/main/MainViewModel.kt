package com.gilbertparreno.exam.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilbertparreno.exam.core.entities.OperationType
import com.gilbertparreno.exam.core.entities.TaskStatus
import com.gilbertparreno.exam.core.exception.ArithmeticExpressionException
import com.gilbertparreno.exam.core.extensions.SingleLiveEvent
import com.gilbertparreno.exam.core.extensions.getFirstArithmeticExpression
import com.gilbertparreno.exam.core.extensions.launch
import com.gilbertparreno.exam.core.extensions.removeDigits
import com.gilbertparreno.exam.core.providers.CoroutineContextProvider
import com.gilbertparreno.exam.ui.main.entities.ArithmeticExpressionResult
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MainViewModel @Inject constructor(
    private val textRecognizer: TextRecognizer,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    val resultEvent = SingleLiveEvent<TaskStatus<ArithmeticExpressionResult>>()

    fun getTextFromInputImage(inputImage: InputImage) {
        viewModelScope.launch(
            coroutineContextProvider = coroutineContextProvider,
            work = {
                resultEvent.postValue(TaskStatus.loading())
                val expression = recognizeText(inputImage).getFirstArithmeticExpression()
                expression?.let {
                    performOperation(it)
                } ?: run {
                    throw ArithmeticExpressionException()
                }
            },
            onSuccess = {
                resultEvent.value = TaskStatus.successWithResult(it)
            },
            onFailure = {
                resultEvent.value = TaskStatus.error(it)
            }
        )
    }

    private suspend fun recognizeText(
        inputImage: InputImage
    ) = suspendCancellableCoroutine<String> { coroutine ->
        try {
            textRecognizer.process(inputImage)
                .addOnSuccessListener { result ->
                    coroutine.resume(result.text)
                }.addOnFailureListener {
                    coroutine.resumeWithException(it)
                }
        } catch (e: Exception) {
            coroutine.resumeWithException(e)
        }
    }

    private fun performOperation(expression: String): ArithmeticExpressionResult {
        val values = expression.split("[/+\\-*]".toRegex()).map { it.toBigDecimal().setScale(2) }
        val operator = expression.removeDigits()
        return OperationType.getOperationType(
            operator = operator,
            num1 = values[0],
            num2 = values[1]
        )?.let {
            ArithmeticExpressionResult(it.expression, it.perform())
        } ?: throw ArithmeticExpressionException()
    }
}