package org.erc3.exercise.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Instantiation happens once per class as LoggerFactory has internal caching
 */
inline val <reified T> T.log: Logger get() = LoggerFactory.getLogger(T::class.java)
