package com.adamratzman.qiukit

class Not : QubitUnaryOperation<Qubit> {
    override fun evaluate(argument: Qubit): Qubit {
        return argument.copy(zero = argument.one, one = argument.zero)
    }
}

class Read : QubitUnaryOperation<Qubit> {
    override fun evaluate(argument: Qubit): Qubit {
        val oneProbability = argument.one.probability
        return if (argument.random.nextDouble() < oneProbability) Qubit.getOne(argument.random)
        else Qubit.getZero(argument.random)
    }
}

class Phase : QubitUnaryOperation<Qubit> {
    override fun evaluate(argument: Qubit): Qubit {
        argument.p
    }

}