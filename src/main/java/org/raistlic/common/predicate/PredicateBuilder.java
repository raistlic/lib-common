/*
 * Copyright 2015 Lei CHEN (raistlic@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.raistlic.common.predicate;

import org.raistlic.common.precondition.Precondition;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * For building generic proxy instance to wrap a predicate, in order to negate it, or to combine two predicate instances
 * with logic gate.
 *
 * @param <E> the generic type of predicate instance.
 */
@SuppressWarnings("unchecked")
public final class PredicateBuilder<E> implements Supplier<Predicate<E>> {

   private Predicate<E> predicate;

   public PredicateBuilder(Predicate<? super E> base) {

      Precondition.assertParam(base != null, "new PredicateBuilder(base): base cannot be null.");

      this.predicate = (Predicate<E>) base;
   }

   public PredicateBuilder<E> not() {

      this.predicate = Predicates.not(predicate);
      return this;
   }

   public PredicateBuilder<E> and(Predicate<? super E> predicate) {

      this.predicate = Predicates.and(this.predicate, predicate);
      return this;
   }

   public PredicateBuilder<E> or(Predicate<? super E> predicate) {

      this.predicate = Predicates.or(this.predicate, predicate);
      return this;
   }

   @Override
   public Predicate<E> get() {

      return this.predicate;
   }
}
