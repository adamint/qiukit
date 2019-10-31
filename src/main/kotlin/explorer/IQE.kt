package com.adamratzman.explorer

import com.adamratzman.math.Expression
import com.adamratzman.math.parser.ExpressionEvaluator
import com.adamratzman.qiukit.Circuit
import com.adamratzman.qiukit.ComputationalResult
import com.adamratzman.qiukit.Gate
import com.adamratzman.qiukit.GateWithArgument
import com.adamratzman.qiukit.Qubit
import com.adamratzman.qiukit.QubitAmplitude
import com.adamratzman.qiukit.operators.QubitBinaryOperator
import com.adamratzman.qiukit.operators.QubitOperator
import com.adamratzman.qiukit.operators.one.binary.Phase
import com.adamratzman.qiukit.operators.one.binary.Write
import com.adamratzman.qiukit.operators.one.unary.Hadamard
import com.adamratzman.qiukit.operators.one.unary.Not
import com.adamratzman.qiukit.operators.one.unary.PauliY
import com.adamratzman.qiukit.operators.one.unary.PauliZ
import com.adamratzman.qiukit.operators.one.unary.Read
import com.adamratzman.qiukit.operators.one.unary.RootOfNot
import com.adamratzman.qiukit.operators.one.unary.SPhase
import com.adamratzman.qiukit.operators.one.unary.TPhase
import com.adamratzman.qiukit.operators.two.unary.Swap
import com.google.gson.Gson
import spark.Spark.get
import spark.Spark.init
import spark.Spark.path
import java.lang.Exception
import java.util.Random

val operators = listOf(Phase(), Write(), Hadamard(), Not(), PauliY(), PauliZ(),
        Read(), RootOfNot(), SPhase(), TPhase(), Swap())
val qubitConstants = listOf(
        "zero" to Qubit.getQubit(Qubit.State.ZERO),
        "one" to Qubit.getQubit(Qubit.State.ONE),
        "plus" to Qubit.getPlusQubit(),
        "minus" to Qubit.getMinusQubit()
).toMap()

fun QuantumExplorer.iqe() {
    path("/environment") {
        get("") { request, _ ->
            val map = getMap("Interactive Quantum Environment", "iqe", false)

            val allowedOperators = request.queryParams("operators")?.split(" ")
                    ?.mapNotNull { operators.find { operator -> operator.name == it } }
                    ?: operators

            map["operators"] = allowedOperators
            map["qubitConstants"] = qubitConstants.toList()
                    .map { it.first to Pair(it.second.zero.toDiracString() + "|0⟩", it.second.one.toDiracString() + "|1⟩") }
            handlebars.render(map, "iqe.hbs")
        }
        get("/compute") { request, _ ->
            val map = getMap("","",false)
            val initialQubit = qubitConstants.toList().first { it.first == request.queryParams("initial") }
            val gates = request.queryParams("gates").split(" ").filter { it.isNotBlank() }.map { gateString ->
                val splitByParams = gateString.replace("|", "").removeSuffix(")").split("(")
                val operator = operators.first { it.name == splitByParams.first() }
                val argument = splitByParams.getOrNull(1)?.replace("pi", Math.PI.toString())?.let {
                    try {
                        Expression(it).evaluate().toDouble()
                    } catch (e:Exception){
                            if (it == "zero") Qubit.getQubit(Qubit.State.ZERO) else Qubit.getQubit(Qubit.State.ONE)
                    }
                }

                if (argument == null) Gate<Qubit, Qubit>(operator as QubitOperator<Qubit, Qubit>)
                else GateWithArgument(operator as QubitOperator<javafx.util.Pair<Qubit, Any>, Qubit>, argument)
            }

            val circuit = Circuit(gates)
            val resultList = circuit.evaluate(initialQubit.second)

            val afterResults = if (resultList == null || resultList.isEmpty()) listOf<ComputationalResult<Any, Any>>() else resultList.subList(0, resultList.lastIndex)
            val finalResult = resultList?.lastOrNull()?.after ?: initialQubit.second

            map["afterResults"] = if (afterResults.isEmpty()) null else
                listOf(initialQubit.second.toModel(-2).apply { first = true })+
                        afterResults.mapIndexed {i, it -> (it.after as Qubit).toModel(i).apply { gate = it.gate } } +
                        listOf((resultList.last().after as Qubit).toModel(-3).apply { gate = resultList.last().gate })
            map["finalResult"] = (finalResult as Qubit).toModel(-1)
            map["initial"] = initialQubit.second.toModel(1)
map["circuit"] = "<b>${initialQubit.second.zero.toDiracString()}|0⟩ + ${initialQubit.second.one.toDiracString()}|1></b> " +
        if (request.queryParams("gates").isNotEmpty()) " | " +
                request.queryParams("gates").split(" ").joinToString(" | ") else ""
            handlebars.render(map, "circuit-computation.hbs")
        }

        get("/render") { request, response ->
            val r = request.queryParams("r").toDouble()
            val theta = request.queryParams("theta").toDouble()
            val r1 = request.queryParams("r1").toDouble()
            val theta1 = request.queryParams("theta1").toDouble()
            val id = request.queryParams("id").toInt()

            val map = getMap("", "", false)
            map["qubit"] = Qubit(QubitAmplitude(r, theta), QubitAmplitude(r1, theta1), Random()).toModel(id)
            handlebars.render(map, "iqe-render.hbs")
        }
    }
}