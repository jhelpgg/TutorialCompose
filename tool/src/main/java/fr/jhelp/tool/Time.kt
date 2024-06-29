package fr.jhelp.tool

val Long.seconds : Long get() = this * 1_000L
val Long.minutes : Long get() = this.seconds * 60L
val Long.hours : Long get() = this.minutes * 60L
val Long.days : Long get() = this.hours * 24L
val Long.weeks : Long get() = this.days * 7
val Long.months : Long get() = this.days * 30L
val Long.years : Long get() = this.days * 365L