package com.latency.stats.validation;

import java.util.function.Consumer;

public abstract class Validator <T, R extends ServiceError> implements Consumer<T> {
}
