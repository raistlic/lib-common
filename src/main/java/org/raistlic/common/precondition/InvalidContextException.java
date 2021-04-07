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

package org.raistlic.common.precondition;

/**
 * Indicates a violation to a method's contract that it is invoked in an invalid context. Context may refer to the
 * target method's related instance state or any related static state including thread context state, etc.
 */
public class InvalidContextException extends PreconditionCheckFailedException {

   public InvalidContextException(String message) {

      super(message);
   }

   public InvalidContextException(String message, Throwable cause) {

      super(message, cause);
   }

   public InvalidContextException(Throwable cause) {

      super(cause);
   }
}
