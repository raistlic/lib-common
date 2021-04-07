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

/**
 * A wrapper class that wraps two {@link Predicate} instances, and returns the {@code &&} result
 * of both tests.
 */
public final class PredicateAndWrapper<E> implements Predicate<E> {

   private final Predicate<? super E> left;

   private final Predicate<? super E> right;

   /**
    * Wraps the two predicates to create the wrapper object.
    *
    * @param left  the left operand of the wrapper, cannot be {@code null}.
    * @param right the right operand of the wrapper, cannot be {@code null}.
    * @throws org.raistlic.common.precondition.InvalidParameterException when any of the parameters
    *                                                                    is {@code null}.
    */
   public PredicateAndWrapper(Predicate<? super E> left, Predicate<? super E> right) {

      Precondition.assertParam(left != null, "new PredicateAndWrapper(left, right): left cannot be null.");
      Precondition.assertParam(right != null, "new PredicateAndWrapper(left, right): right cannot be null.");

      this.left = left;
      this.right = right;
   }

   @Override
   public boolean test(E e) {

      return left.test(e) && right.test(e);
   }
}
