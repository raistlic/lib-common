/*
 * Copyright 2016 Lei Chen (raistlic@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.raistlic.common.assertion;

import org.raistlic.common.precondition.Precondition;
import org.raistlic.common.predicate.CollectionPredicates;
import org.raistlic.common.predicate.Predicates;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Expectations to validate {@link Collection} instances.
 */
final class CollectionAssertionDefault<E> extends GenericAssertionAbstract<Collection<E>, CollectionAssertion<E>>
   implements CollectionAssertion<E> {

   private final Function<String, ? extends RuntimeException> exceptionMapper;

   private Collection<E> candidate;

   CollectionAssertionDefault(Collection<E> candidate, Function<String, ? extends RuntimeException> exceptionMapper) {

      Precondition.assertParam(exceptionMapper != null, "'exceptionMapper' cannot be null.");

      this.candidate = candidate;
      this.exceptionMapper = exceptionMapper;
   }

   @Override
   public CollectionAssertion<E> isEmpty() {

      if (CollectionPredicates.isEmpty().test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply("Collection should be empty but is not.");
   }

   @Override
   public CollectionAssertion<E> isEmpty(String message) {

      if (CollectionPredicates.isEmpty().test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply(message);
   }

   @Override
   public CollectionAssertion<E> isNullOrEmpty() {

      if (IS_NULL_OR_EMPTY.test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply("Collection should be null or empty, but is not.");
   }

   @Override
   public CollectionAssertion<E> isNullOrEmpty(String message) {

      if (IS_NULL_OR_EMPTY.test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply(message);
   }

   @Override
   public CollectionAssertion<E> notEmpty() {

      if (CollectionPredicates.notEmpty().test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply("Collection should not be empty, but is.");
   }

   @Override
   public CollectionAssertion<E> notEmpty(String message) {

      if (CollectionPredicates.notEmpty().test(getCandidate())) {
         return getThis();
      }
      throw getExceptionMapper().apply(message);
   }

   @Override
   public CollectionAssertion<E> hasSize(int size) {

      Collection<E> c = getCandidate();
      if (c != null && c.size() == size) {
         return getThis();
      }
      throw getExceptionMapper().apply("Collection should have size '" + size + "', but does not.");
   }

   @Override
   public CollectionAssertion<E> hasSize(int size, String message) {

      Collection<E> c = getCandidate();
      if (c != null && c.size() == size) {
         return getThis();
      }
      throw getExceptionMapper().apply(message);
   }

   @Override
   public CollectionAssertion<E> contains(E element) {

      Collection<E> c = getCandidate();
      if (c != null && c.contains(element)) {
         return getThis();
      }
      throw getExceptionMapper().apply("Collection should contain '" + element + "', but does not.");
   }

   @Override
   public CollectionAssertion<E> contains(E element, String message) {

      Collection<E> c = getCandidate();
      if (c != null && c.contains(element)) {
         return getThis();
      }
      throw getExceptionMapper().apply(message);
   }

   @Override
   public CollectionAssertion<E> containsAll(Collection<E> elements) {

      Precondition.assertParam(elements != null, "'elements' can not be null.");
      elements.forEach(this::contains);
      return getThis();
   }

   @Override
   public CollectionAssertion<E> containsAll(Collection<E> elements, String message) {

      Precondition.assertParam(elements != null, "'elements' can not be null.");
      elements.forEach(e -> this.contains(e, message));
      return getThis();
   }

   @Override
   public CollectionAssertion<E> isOrderedBy(Comparator<? super E> comparator, String message) {

      Precondition.assertParam(comparator != null, "'comparator' cannot be null.");

      if (this.candidate.isEmpty()) {
         return getThis();
      }
      Iterator<E> iterator = this.candidate.iterator();
      E prev = iterator.next();
      while (iterator.hasNext()) {
         E current = iterator.next();
         if (comparator.compare(prev, current) > 0) {
            throw this.exceptionMapper.apply("Out of order: '" + prev + "' and '" + current + "'");
         }
         prev = current;
      }
      return getThis();
   }

   @Override
   CollectionAssertionDefault<E> getThis() {

      return this;
   }

   @Override
   Collection<E> getCandidate() {

      return candidate;
   }

   CollectionAssertion<E> setCandidate(Collection<E> candidate) {

      this.candidate = candidate;
      return getThis();
   }

   @Override
   Function<String, ? extends RuntimeException> getExceptionMapper() {

      return exceptionMapper;
   }

   private final Predicate<Collection<?>> IS_NULL_OR_EMPTY = Predicates.or(
      Predicates.isNull(),
      CollectionPredicates.isEmpty()
   );
}
