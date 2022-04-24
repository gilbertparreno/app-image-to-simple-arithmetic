package com.gilbertparreno.exam.core.entities

import java.math.BigDecimal

sealed class OperationType(
    open val num1: BigDecimal,
    open val num2: BigDecimal,
    open val expression: String
) {

    abstract fun perform(): BigDecimal

    data class Add(
        override val num1: BigDecimal,
        override val num2: BigDecimal
    ) : OperationType(
        num1 = num1,
        num2 = num2,
        expression = "%s+%s".format(
            num1.stripTrailingZeros().toPlainString(),
            num2.stripTrailingZeros().toPlainString()
        )
    ) {
        override fun perform() = num1 + num2
    }

    data class Subtract(
        override val num1: BigDecimal,
        override val num2: BigDecimal
    ) : OperationType(
        num1 = num1,
        num2 = num2,
        expression = "%s-%s".format(
            num1.stripTrailingZeros().toPlainString(),
            num2.stripTrailingZeros().toPlainString()
        )
    ) {
        override fun perform() = num1 - num2
    }

    data class Multiply(
        override val num1: BigDecimal,
        override val num2: BigDecimal
    ) : OperationType(
        num1 = num1,
        num2 = num2,
        expression = "%s*%s".format(
            num1.stripTrailingZeros().toPlainString(),
            num2.stripTrailingZeros().toPlainString()
        )
    ) {
        override fun perform() = num1 * num2
    }

    data class Divide(
        override val num1: BigDecimal,
        override val num2: BigDecimal
    ) : OperationType(
        num1 = num1,
        num2 = num2,
        expression = "%s/%s".format(
            num1.stripTrailingZeros().toPlainString(),
            num2.stripTrailingZeros().toPlainString()
        )
    ) {
        override fun perform() = num1 / num2
    }

    companion object {
        fun getOperationType(
            operator: String,
            num1: BigDecimal,
            num2: BigDecimal
        ): OperationType? {
            return when (operator) {
                "+" -> Add(num1, num2)
                "-" -> Subtract(num1, num2)
                "*" -> Multiply(num1, num2)
                "/" -> Divide(num1, num2)
                else -> null
            }
        }
    }
}