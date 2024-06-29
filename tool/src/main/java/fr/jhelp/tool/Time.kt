package fr.jhelp.tool

/**
 * Convert seconds to milliseconds
 */
val Long.seconds : Long get() = this * 1_000L
/**
 * Convert minutes to milliseconds
 */
val Long.minutes : Long get() = this.seconds * 60L
/**
 * Convert hours to milliseconds
 */
val Long.hours : Long get() = this.minutes * 60L
/**
 * Convert days to milliseconds
 */
val Long.days : Long get() = this.hours * 24L
/**
 * Convert weeks to milliseconds
 */
val Long.weeks : Long get() = this.days * 7
/**
 * Convert months to milliseconds
 */
val Long.months : Long get() = this.days * 30L
/**
 * Convert years to milliseconds
 */
val Long.years : Long get() = this.days * 365L