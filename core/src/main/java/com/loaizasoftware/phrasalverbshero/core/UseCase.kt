package com.loaizasoftware.phrasalverbshero.core

abstract class UseCase<out Type, in Params> {

    abstract fun run(params: Params): Type

}