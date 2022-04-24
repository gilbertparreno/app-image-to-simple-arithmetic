package com.gilbertparreno.exam.ui.main.entities

import java.math.BigDecimal

data class ArithmeticExpressionResult(
    val expression: String,
    val result: BigDecimal
) {
    override fun toString() = "%s=%s".format(
        expression,
        result.setScale(2).stripTrailingZeros().toPlainString()
    )
}